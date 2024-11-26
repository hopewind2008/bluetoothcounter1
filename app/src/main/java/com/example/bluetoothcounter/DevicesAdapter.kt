package com.example.bluetoothcounter

import android.bluetooth.BluetoothDevice
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DevicesAdapter(private val devices: List<BluetoothDevice>) : 
    RecyclerView.Adapter<DevicesAdapter.DeviceViewHolder>() {

    class DeviceViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceViewHolder {
        val textView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_device, parent, false) as TextView
        return DeviceViewHolder(textView)
    }

    override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) {
        val device = devices[position]
        val deviceName = device.name ?: "未知设备"
        holder.textView.text = "$deviceName (${device.address})"
    }

    override fun getItemCount() = devices.size
} 