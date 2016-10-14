package com.example.milo.bluetoothtest;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;

/**
 * Created by milo on 16-10-9.
 * 蓝牙控制类
 */
public class BluetoothController {
    private BluetoothAdapter mBluetoothAdapter;

    public BluetoothController() {
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    /**
     * 判断当前设备是否支持蓝牙
     *
     * @return
     */
    public boolean isSupportBluetooth() {
        if (mBluetoothAdapter != null) {
            return true;
        }
        return false;
    }

    /**
     * 获取蓝牙状态
     *
     * @return
     */
    public boolean getBluetoothStatus() {
        if (mBluetoothAdapter != null) {
            return mBluetoothAdapter.isEnabled();
        }
        return false;
    }

    /**
     * 打开蓝牙
     *
     * @param activity
     * @param requestCode
     */
    public void turnOnBluetooth(Activity activity, int requestCode) {
        if (mBluetoothAdapter != null && !mBluetoothAdapter.isEnabled()) {
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            activity.startActivityForResult(intent, requestCode);

        }
    }

    /**
     * 关闭蓝牙
     */
    public void turnOffBluetooth() {
        if (mBluetoothAdapter != null && mBluetoothAdapter.isEnabled()) {
            mBluetoothAdapter.disable();
        }
    }

    /**
     * 开始扫描
     */
    public void discoveryBluetooth() {
        if (mBluetoothAdapter != null && mBluetoothAdapter.isEnabled()) {
            mBluetoothAdapter.startDiscovery();
        }
    }
}
