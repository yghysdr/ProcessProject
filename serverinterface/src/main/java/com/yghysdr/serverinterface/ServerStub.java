package com.yghysdr.serverinterface;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by yghysdr@163.com on 2019-12-30
 * description:  服务端实现的存根
 */
public abstract class ServerStub extends Binder implements IServerInterface {

    static final String DESCRIPTOR = "com.yghysdr.server.IServerInterface";
    //方法标示
    static final int TRANSACTION_addCount = (IBinder.FIRST_CALL_TRANSACTION);
    static final int TRANSACTION_addCount_1 = (IBinder.FIRST_CALL_TRANSACTION + 1);

    public ServerStub() {
        this.attachInterface(this, DESCRIPTOR);
    }

    /**
     * 重写该方法，根据code来判断远程调用的方法
     *
     * @param code  函数验证
     * @param data  客户端传递过来的数据
     * @param reply 返回给服务端的数据
     * @param flags
     * @return
     * @throws RemoteException
     */
    @Override
    protected boolean onTransact(int code, @NonNull Parcel data, @Nullable Parcel reply, int flags) throws RemoteException {
        if (code == TRANSACTION_addCount) {
            data.enforceInterface(DESCRIPTOR);
            //读取参数
            int _arg0 = data.readInt();
            int _arg1 = data.readInt();
            //计算结果
            int _result = this.add(_arg0, _arg1);
            //返回结果
            if (reply != null) {
                reply.writeNoException();
                reply.writeInt(_result);
            }
            return true;
        } else if (code == TRANSACTION_addCount_1) {
            data.enforceInterface(DESCRIPTOR);
            ServerClass _arg0 = ServerClass.CREATOR.createFromParcel(data);
            long timeMillis = System.currentTimeMillis();
            Log.d("ServerProxy-ServerStub", "onTransact: " + Thread.currentThread() + "..." + timeMillis);
            int _result = this.add1(_arg0);
            Log.d("ServerProxy-ServerStub", "onTransact: " + Thread.currentThread() + "..." + (System.currentTimeMillis() - timeMillis));
            if (reply != null) {
                reply.writeNoException();
                reply.writeInt(_result);
                _arg0.writeToParcel(reply, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
            }
            return true;
        }
        return super.onTransact(code, data, reply, flags);
    }

    /**
     * @param iBinder 根据iBinder 转化为 服务端接口 IServerInterface
     * @return
     */
    public static IServerInterface asInterface(IBinder iBinder) {
        if ((iBinder == null)) {
            return null;
        }
        //查询本地是否存在代理
        IInterface iin = iBinder.queryLocalInterface(DESCRIPTOR);
        if (iin instanceof ServerStub) {
            return (IServerInterface) iin;
        }
        return new ServerProxy(iBinder);
    }

    @Override
    public IBinder asBinder() {
        return this;
    }

}
