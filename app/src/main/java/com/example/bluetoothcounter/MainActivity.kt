package com.example.bluetoothcounter

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bluetoothcounter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bluetoothAdapter: BluetoothAdapter
    private val devices = mutableListOf<BluetoothDevice>()
    private lateinit var devicesAdapter: DevicesAdapter
    
    private val PERMISSIONS = arrayOf(
        Manifest.permission.BLUETOOTH_SCAN,
        Manifest.permission.BLUETOOTH_CONNECT,
        Manifest.permission.ACCESS_FINE_LOCATION
    )
    
    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            when(intent.action) {
                BluetoothDevice.ACTION_FOUND -> {
                    val device = intent.getParcelableExtra<BluetoothDevice>(BluetoothDevice.EXTRA_DEVICE)
                    device?.let {
                        if (!devices.contains(it)) {
                            devices.add(it)
                            updateDeviceCount()
                            devicesAdapter.notifyDataSetChanged()
                        }
                    }
                }
                BluetoothAdapter.ACTION_DISCOVERY_FINISHED -> {
                    binding.btnStartScan.isEnabled = true
                    binding.btnStopScan.isEnabled = false
                    showToast("扫描完成")
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        initBluetooth()
        setupUI()
        registerBluetoothReceiver()
    }

    private fun initBluetooth() {
        val bluetoothManager = getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        bluetoothAdapter = bluetoothManager.adapter ?: run {
            showToast("设备不支持蓝牙")
            finish()
            return
        }
    }

    private fun setupUI() {
        devicesAdapter = DevicesAdapter(devices)
        binding.rvDevices.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = devicesAdapter
        }

        binding.btnStartScan.setOnClickListener {
            startScanning()
        }

        binding.btnStopScan.setOnClickListener {
            stopScanning()
        }
    }

    private fun startScanning() {
        if (!bluetoothAdapter.isEnabled) {
            showToast("请先开启蓝牙")
            return
        }

        if (!hasRequiredPermissions()) {
            requestPermissions()
            return
        }

        devices.clear()
        devicesAdapter.notifyDataSetChanged()
        updateDeviceCount()

        bluetoothAdapter.startDiscovery()
        binding.btnStartScan.isEnabled = false
        binding.btnStopScan.isEnabled = true
        showToast("开始扫描")
    }

    private fun stopScanning() {
        bluetoothAdapter.cancelDiscovery()
        binding.btnStartScan.isEnabled = true
        binding.btnStopScan.isEnabled = false
        showToast("停止扫描")
    }

    private fun hasRequiredPermissions(): Boolean {
        return PERMISSIONS.all {
            ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED
        }
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_REQUEST_CODE)
    }

    private fun registerBluetoothReceiver() {
        val filter = IntentFilter().apply {
            addAction(BluetoothDevice.ACTION_FOUND)
            addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)
        }
        registerReceiver(receiver, filter)
    }

    private fun updateDeviceCount() {
        binding.tvDeviceCount.text = "发现设备数: ${devices.size}"
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                startScanning()
            } else {
                showToast("需要相关权限才能扫描蓝牙设备")
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
        if (bluetoothAdapter.isDiscovering) {
            bluetoothAdapter.cancelDiscovery()
        }
    }

    companion object {
        private const val PERMISSION_REQUEST_CODE = 100
    }
} 