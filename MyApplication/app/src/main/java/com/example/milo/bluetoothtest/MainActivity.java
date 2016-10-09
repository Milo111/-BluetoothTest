package com.example.milo.bluetoothtest;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final int REQUESTCODE_OPEN = 1;
    private BluetoothController mController = new BluetoothController();
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, 1);
            switch (state) {
                case BluetoothAdapter.STATE_OFF:
                    Toast.makeText(MainActivity.this, "蓝牙已关闭", Toast.LENGTH_SHORT).show();
                    break;
                case BluetoothAdapter.STATE_ON:
                    Toast.makeText(MainActivity.this, "蓝牙已开启", Toast.LENGTH_SHORT).show();
                    break;
                case BluetoothAdapter.STATE_TURNING_ON:
                    Toast.makeText(MainActivity.this, "正在开启蓝牙", Toast.LENGTH_SHORT).show();
                    break;
                case BluetoothAdapter.STATE_TURNING_OFF:
                    Toast.makeText(MainActivity.this, "正在关闭蓝牙", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    Toast.makeText(MainActivity.this, "未知状态", Toast.LENGTH_SHORT).show();
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IntentFilter intentFilter = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        registerReceiver(mReceiver, intentFilter);
    }

    public void click(View view) {
        switch (view.getId()) {
            case R.id.is_support_btn:
                boolean flag = mController.isSupportBluetooth();
                Toast.makeText(MainActivity.this, "flag" + flag, Toast.LENGTH_SHORT).show();
                break;
            case R.id.is_opened_btn:
                boolean isTurnOn = mController.getBluetoothStatus();
                Toast.makeText(MainActivity.this, "isTurnOn" + isTurnOn, Toast.LENGTH_SHORT).show();
                break;
            case R.id.open_btn:
                mController.turnOnBluetooth(this, REQUESTCODE_OPEN);
                break;
            case R.id.close_btn:
                mController.turnOffBluetooth();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_OK) {
            Toast.makeText(MainActivity.this, "终于打开了", Toast.LENGTH_SHORT).show();
        }
    }
}
