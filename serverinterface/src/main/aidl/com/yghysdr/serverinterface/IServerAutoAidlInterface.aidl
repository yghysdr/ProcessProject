// IServerAutoAidlInterface.aidl
package com.yghysdr.serverinterface;

import com.yghysdr.serverinterface.ServerClass;
// Declare any non-default types here with import statements

interface IServerAutoAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    int getCount(int count1, int count2);

    int getCount1(inout ServerClass serverClass);
}
