package com.yghysdr.serverinterface;

import android.os.IInterface;

import androidx.annotation.NonNull;

import com.yghysdr.serverinterface.ServerClass;

/**
 * Created by yghysdr@163.com on 2019-12-30
 * description:  定义有什么能力
 */
public interface IServerInterface extends IInterface {

    int add(int count1, int count2);

    int add1(@NonNull ServerClass serverClass) throws android.os.RemoteException;
}
