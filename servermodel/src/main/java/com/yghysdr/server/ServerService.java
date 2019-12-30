package com.yghysdr.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class ServerService extends Service {

    private static final String TAG = ServerService.class.getSimpleName();

    private int count;

    public ServerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return iServerAutoAidlInterface;
    }

    private IServerAutoAidlInterface.Stub iServerAutoAidlInterface = new IServerAutoAidlInterface.Stub() {
        @Override
        public void addCount() throws RemoteException {
            count++;
            Log.d(TAG, "addCount: " + count);
        }
    };


}
