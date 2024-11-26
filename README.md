# 蓝牙设备计数器

一个简单的Android蓝牙设备扫描和计数应用。

## 功能特点

- 扫描并显示周边蓝牙设备
- 实时统计已发现的设备数量 
- 显示设备名称和地址信息
- 支持开始/停止扫描操作
- Material Design现代界面设计
- 自动处理运行时权限

## 系统要求

- Android 6.0 (API 23)及以上版本
- 设备需支持蓝牙功能
- 需要蓝牙和位置权限

## 安装说明

1. 克隆项目到本地:
```bash
git clone https://github.com/yourusername/bluetooth-counter.git
```

2. 使用Android Studio打开项目
3. 等待Gradle同步完成
4. 运行应用到设备或模拟器上

## 使用说明

1. 首次运行时请授予应用所需权限
2. 点击"开始扫描"按钮开始搜索设备
3. 界面上方显示已发现的设备总数
4. 下方列表显示所有发现的设备详情
5. 点击"停止扫描"按钮可随时停止搜索

## 注意事项

- 请确保设备蓝牙功能已开启
- 扫描过程会消耗较多电量
- 部分设备可能无法显示名称,此时将显示MAC地址
- 需要位置权限才能执行蓝牙扫描

## 开发环境

- Android Studio 
- Kotlin
- Minimum SDK: API 23
- Target SDK: API 31

## 项目结构

```
app/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/bluetoothcounter/
│   │   │       ├── MainActivity.kt        # 主活动类
│   │   │       └── DevicesAdapter.kt      # 设备列表适配器
│   │   ├── res/
│   │   │   ├── layout/
│   │   │   │   ├── activity_main.xml     # 主界面布局
│   │   │   │   └── item_device.xml       # 设备列表项布局
│   │   │   ├── values/
│   │   │   │   ├── colors.xml           # 颜色资源
│   │   │   │   ├── strings.xml          # 字符串资源
│   │   │   │   └── themes.xml           # 主题样式
│   │   │   └── mipmap/                  # 应用图标
│   │   └── AndroidManifest.xml          # 应用配置文件
│   └── test/                            # 测试目录
└── build.gradle                         # 项目构建配置
```

## 主要文件说明

- `MainActivity.kt`: 应用的主界面,包含蓝牙扫描和设备显示逻辑
- `DevicesAdapter.kt`: 用于显示蓝牙设备列表的适配器
- `activity_main.xml`: 主界面布局文件
- `item_device.xml`: 设备列表项的布局文件
- `AndroidManifest.xml`: 声明应用权限和组件

## 贡献指南

欢迎提交问题报告和功能建议。如果您想贡献代码：

1. Fork 项目
2. 创建您的特性分支 (`git checkout -b feature/AmazingFeature`)
3. 提交您的修改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 创建一个 Pull Request

## 开发计划

- [ ] 添加设备过滤功能
- [ ] 支持保存扫描记录
- [ ] 添加设备信号强度显示
- [ ] 优化电池使用效率
- [ ] 添加暗色主题支持

## 常见问题

Q: 为什么应用需要位置权限？
A: Android系统要求蓝牙扫描必须具有位置权限，这是出于用户隐私保护考虑。

Q: 扫描不到设备怎么办？
A: 请确保：
1. 已开启设备蓝牙
2. 已授予所有必要权限
3. 周边确实存在蓝牙设备
4. 尝试重新启动扫描

## 许可证

本项目采用 MIT 许可证 - 查看 [LICENSE](LICENSE) 文件了解详情

## 联系方式

如有问题或建议,请通过以下方式联系：

- 项目Issues页面
- 电子邮件：your.email@example.com

## 致谢

- 感谢所有项目贡献者
- 感谢Android开源社区