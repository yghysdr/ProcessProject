package com.yghysdr.serverinterface;

import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.NonNull;

/**
 * Created by yghysdr@163.com on 2019-12-30
 * description: 接口代理 - 静态代理模式
 * <p>
 * 不在同一个进程的时候会创建该代理，完成其功能， 同在是Stub内部自己调用
 */
class ServerProxy implements IServerInterface {

    private IBinder mRemote;

    ServerProxy(IBinder iBinder) {
        mRemote = iBinder;
    }

    @Override
    public int add(int count1, int count2) {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        int _result = 0;
        try {
            _data.writeInterfaceToken(getInterfaceDescriptor());
            _data.writeInt(count1);
            _data.writeInt(count2);
            //Client 进程通过系统调用陷入内核态,等待返回, 最终调用 Server 进程本地对象的 onTransact(),根据函数编号调用相关函数
            mRemote.transact(ServerStub.TRANSACTION_addCount, _data, _reply, 0);
            //返回结果，读取数据
            _reply.readException();
            _result = _reply.readInt();
        } catch (RemoteException e) {
            e.printStackTrace();
        } finally {
            _data.recycle();
            _reply.recycle();
        }
        return _result;
    }

    @Override
    public int add1(@NonNull ServerClass serverClass) throws android.os.RemoteException {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        int _result;
        try {
            _data.writeInterfaceToken(getInterfaceDescriptor());
            serverClass.writeToParcel(_data, 0);
            long startTime = System.currentTimeMillis();
            Log.d("ServerProxy-ServerStub", "add1: " + Thread.currentThread() + "..." + startTime);
            mRemote.transact(ServerStub.TRANSACTION_addCount_1, _data, _reply, 0);
            Log.d("ServerProxy-ServerStub", "add1: " + Thread.currentThread() + "..." +( System.currentTimeMillis() - startTime));
            _reply.readException();
            _result = _reply.readInt();
            serverClass.readFromParcel(_reply);
        } finally {
            _reply.recycle();
            _data.recycle();
        }
        return _result;
    }


    private String getInterfaceDescriptor() {
        return ServerStub.DESCRIPTOR;
    }

    @Override
    public IBinder asBinder() {
        return mRemote;
    }

}
