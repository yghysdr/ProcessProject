package com.yghysdr.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;

import com.yghysdr.serverinterface.ServerClass;
import com.yghysdr.serverinterface.ServerStub;

public class ServerService extends Service {

    private static final String TAG = ServerService.class.getSimpleName();


    public ServerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return serverStub;
    }

    private ServerStub serverStub = new ServerStub() {

        @Override
        public int add(int count1, int count2) {
            this.count++;
            Log.d(TAG, "addCount: " + count);
            return Math.max(count1, count2);
        }

        @Override
        public int add1(@NonNull ServerClass serverClass) {
            this.count++;
            Log.d(TAG, "addCount: " + count);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return Math.max(serverClass.getCount1(), serverClass.getCount2());
        }

        private int count;
    };
}
