package com.yghysdr.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.yghysdr.serverinterface.IServerAutoAidlInterface;
import com.yghysdr.serverinterface.ServerClass;

public class ServerAidlService extends Service {

    private static final String TAG = ServerAidlService.class.getSimpleName();


    public ServerAidlService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return serverStub;
    }

    private IServerAutoAidlInterface.Stub serverStub = new IServerAutoAidlInterface.Stub() {

        @Override
        public int getCount(int count1, int count2) throws RemoteException {
            this.count++;
            Log.d(TAG, "addCount: " + count);
            return Math.max(count1, count2);
        }

        @Override
        public int getCount1(ServerClass serverClass) throws RemoteException {
            this.count++;
            Log.d(TAG, "addCount: " + count);
            return Math.max(serverClass.getCount1(), serverClass.getCount2());
        }

        private int count;
    };
}
