package com.yghysdr.process;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import com.yghysdr.server.IServerAutoAidlInterface;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.viewBtnAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (autoAidlInterface != null) {
                    try {
                        autoAidlInterface.addCount();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        connectServer();
    }


    private IServerAutoAidlInterface autoAidlInterface;

    private void connectServer() {
        Intent intent = new Intent();
        intent.setAction("com.yghysdr.server.action");
        intent.setPackage("com.yghysdr.server");
        bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.d(TAG, "onServiceConnected: " + name);
                autoAidlInterface = IServerAutoAidlInterface.Stub.asInterface(service);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.d(TAG, "onServiceDisconnected: " + name);

            }
        }, Context.BIND_AUTO_CREATE);
    }
}
