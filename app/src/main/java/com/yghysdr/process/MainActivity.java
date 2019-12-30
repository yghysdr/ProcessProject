package com.yghysdr.process;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.yghysdr.serverinterface.IServerAutoAidlInterface;
import com.yghysdr.serverinterface.IServerInterface;
import com.yghysdr.serverinterface.ServerClass;
import com.yghysdr.serverinterface.ServerStub;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Button viewBtnAdd1, viewBtnAdd2, viewConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewBtnAdd1 = findViewById(R.id.viewBtnAdd1);
        viewBtnAdd2 = findViewById(R.id.viewBtnAdd2);
        viewConnection = findViewById(R.id.viewConnection);
        viewConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connectServer();
                connectServer2();
            }
        });
        viewBtnAdd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (autoAidlInterface != null) {
                    try {
                        viewBtnAdd1.setText(String.valueOf(autoAidlInterface.getCount1(new ServerClass(1, 2))));
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        viewBtnAdd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (serverInterface != null) {
                    try {
                        viewBtnAdd2.setText(String.valueOf(serverInterface.add1(new ServerClass(1, 2))));
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }


    private IServerAutoAidlInterface autoAidlInterface;
    private IServerInterface serverInterface;

    private void connectServer() {
        Intent intent = new Intent();
        intent.setAction("com.yghysdr.server.action1");
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

    private void connectServer2() {
        Intent intent = new Intent();
        intent.setAction("com.yghysdr.server.action2");
        intent.setPackage("com.yghysdr.server");
        bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.d(TAG, "onServiceConnected: " + name);
                serverInterface = ServerStub.asInterface(service);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.d(TAG, "onServiceDisconnected: " + name);

            }
        }, Context.BIND_AUTO_CREATE);
    }
}
