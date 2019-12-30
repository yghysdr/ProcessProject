package com.yghysdr.serverinterface;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yghysdr@163.com on 2019-12-30
 * description:
 * error: cannot find symbol variable CREATOR
 */
public class ServerClass implements Parcelable {

    private int count1;
    private int count2;

    public ServerClass(int count1, int count2) {
        this.count1 = count1;
        this.count2 = count2;
    }

    protected ServerClass(Parcel in) {
        count1 = in.readInt();
        count2 = in.readInt();
    }

    public int getCount1() {
        return count1;
    }

    public int getCount2() {
        return count2;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(count1);
        dest.writeInt(count2);
    }

    public void readFromParcel(Parcel parcel) {
        count1 = parcel.readInt();
        count2 = parcel.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ServerClass> CREATOR = new Creator<ServerClass>() {
        @Override
        public ServerClass createFromParcel(Parcel in) {
            return new ServerClass(in);
        }

        @Override
        public ServerClass[] newArray(int size) {
            return new ServerClass[size];
        }
    };
}
