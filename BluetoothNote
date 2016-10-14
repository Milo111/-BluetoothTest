# Android Bluetooth Note

## 蓝牙简介

### 基本概念

安卓平台提供对蓝牙的通讯栈的支持，允许设别和其他的设备进行无线传输数据。应用程序层通过安卓API来调用蓝牙的相关功能，这些API使程序无线连接到蓝牙设备，并拥有P2P
或者多端无线连接的特性。

### 蓝牙的功能

1. 扫描其他蓝牙设备
2. 为可配对的蓝牙设备查询蓝牙适配器
3. 建立RFCOMM通道（其实就是尼玛的认证）
4. 通过服务搜索来链接其他的设备
5. 与其他的设备进行数据传输
6. 管理多个连接

### 蓝牙建立连接必须要求

1. 打开蓝牙
2. 查找附近已配对或可用设备
3. 连接设备
4. 设备间数据交互

## 蓝牙代码

### 清单文件的权限

> <uses-permissionandroid:name="android.permission.BLUETOOTH_ADMIN" />
> <uses-permissionandroid:name="android.permission.BLUETOOTH" />

### 代码分布

> packages/apps/Bluetooth/
- 蓝牙应用,主要是关于蓝牙应用协议的表现代码，包括opp、hfp、hdp、a2dp、pan等等

> frameworks/base/core/Java/android/server/
- 4.2以后这个目录虽然还有了，但里面代码已经转移到应用层了，就是前面那个目录，所以4.2.2上的蓝牙这里可以忽略。

> framework/base/core/java/android/bluetooth
- 这个目录里的代码更像一个桥梁，里面有供java层使用一些类，也有对应的aidl文件联系C、C++部分的代码，还是挺重要的。

> kernel\drivers\bluetoothBluetooth
- 具体协议实现。包括hci,hid，rfcomm,sco,SDP等协议

> kernel\net\bluetooth Linux kernel
- 对各种接口的Bluetoothdevice的驱动。例如：USB接口，串口等，上面kernel这两个目录有可能看不到的，但一定会有的。

> external\bluetooth\bluedroid
- 官方蓝牙协议栈

> system\bluetoothBluetooth
- 适配层代码，和framework那个作用类似，是串联framework与协议栈的工具。


### 整体结构
![structure](http://img.blog.csdn.net/20130519210952309)

### 常用类和名词解释

> \packages\apps\Settings\src\com\android\settings\bluetooth
1. BluetoothEnabler.java---------界面上蓝牙开启、关闭的开关就是它了， 
2. BluetoothSettings.java--------主界面，用于管理配对和连接设备
3. LocalBluetoothManager.java----提供了蓝牙API上的简单调用接口，这里只是开始。
4. CachedBluetoothDevice.java----描述蓝牙设备的类，对BluetoothDevice的再封装
5. BluetoothPairingDialog.java---那个配对提示的对话框

>  /packages/apps/Phone/src/com/android/phone/
1. BluetoothPhoneService.java----在phone的目录肯定和电话相关了，蓝牙接听挂断电话会用到这个

> /packages/apps/Bluetooth/src/com/android/bluetooth/btservice/
1. AdapterService.java-----------(4.2后才有的代码)蓝牙打开、关闭、扫描、配对都会走到这里，其实更准确的说它替代了4.1之前的BluetoothService.java，原来的工作
---------------------------------就由这个类来完成了。说到这里不能不说4.2蓝牙的目录变了，在4.1及以前的代码中packages层的代码只有opp协议相关应用的代码，也就
---------------------------------是文件传输那部分，而4.2的代码应用层的代码则丰富了许多，按具体的蓝牙应用协议来区别，分为以下文件夹（这里一并对蓝牙一些名词作
---------------------------------个简单解释）
2. btservice---------------------这个前面AdapterService.java的描述大家应该能猜到一些，关于蓝牙基本操作的目录，一切由此开始。
3. a2dp--------------------------(Advanced Audio Distribution Profile)高级音频传输模式，蓝牙立体声，和蓝牙耳机听歌有关那些，另还有个avrcp--音频/视频远程
---------------------------------控制配置文件，是用来听歌时暂停，上下歌曲选择的。
4. hdp---------------------------(Health Device Profile)蓝牙医疗设备模式，可以创建支持蓝牙的医疗设备，使用蓝牙通信的应用，例如心率监视器，血液，温度计和秤。
5. hfp---------------------------(Hands-free Profile)让蓝牙设备可以控制电话，如接听、挂断、拒接、语音拨号等，拒接、语音拨号要视蓝牙耳机及电话是否支持。
6. hid---------------------------(The Human Interface Device)人机交互接口，蓝牙鼠标键盘什么的就是这个了。该协议改编自USB HID Protocol。
7. opp---------------------------(Object Push Profile)对象存储规范，最为常见的，文件的传输都是使用此协议。
8. pan---------------------------(Personal Area Network)描述了两个或更多个蓝牙设备如何构成一个即时网络，和网络有关还有串行端口功能(SPP)，拨号网络功能(DUN)
9. pbap--------------------------(Phonebook Access Profile)电话号码簿访问协议
-  android 4.2的蓝牙应用层部分代码更丰富了，虽然有些目录还没具体代码，不过说不准哪个版本更新就有了，就像4.0添加了hdp医疗那部分一样。另外原本在framework的JNI
---代码也被移到packages/apps/bluetooth当中。

>  /frameworks/base/core/java/android/bluetooth/

1. BluetoothA2dp.java------------A2DP的功能实现
2. BluetoothAdapter.java---------蓝牙action的定义，虚拟设备属性以及操作方法
3. BluetoothAudioGateway.java----蓝牙语音网关
4. BluetoothClass.java-----------蓝牙设备类型的定义
5. BluetoothDevice.java----------蓝牙设备属性
6. BluetoothDevicePicker.java----定义远程蓝牙设备的特性，比如需要认证，设备类型
7. BluetoothHeadset.java---------定义蓝牙headset功能的属性以及接口
8. BluetoothInputStream.java-----蓝牙流接口的实现（输入流）
9. BluetoothOutputStream.java----蓝牙流接口的实现（输出流）
10. BluetoothServerSocket.java---蓝牙socket服务端具备的方法
11. BluetoothSocket.java---------蓝牙socket的封装
12. BluetoothUuid.java-----------蓝牙uuid的定义以及uuid的解析
- 以上java文件在使用具体功能会用到，现在只是简单描述下，至于具体使用在后续文章用到时再给出。同时代码说明部分也就写这些了。对于C、C++部分的代码一方面没看那么
---多，另一方面根据android JNI的命名习惯，大家找起来也很容易。




























