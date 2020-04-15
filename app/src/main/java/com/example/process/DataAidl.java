package com.example.process;

import android.os.Parcel;
import android.os.Parcelable;

public class DataAidl implements Parcelable {
    int data1;
    int data2;
    public DataAidl(){}

    public void setData1(int value){
        data1 = value;
    }

    public int getData1(){
        return data1;
    }

    public void setData2(int value){
        data2 = value;
    }

    public int getData2(){
        return data2;
    }

    protected DataAidl(Parcel in) {
        //这里注意读的顺序
        data1 = in.readInt();
        data2 = in.readInt();
        //如果定义了一个Parcelable的class,比如叫Book
        //book = in.readParcelable(Book.class.getClassLoader());
        //如果有List对象，一定要初始化，new一个先，不能是null
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        //这里注意写的顺序
        dest.writeInt(data1);
        dest.writeInt(data2);
    }

    void readFromParcel(Parcel src){
        data1 = src.readInt();
        data2 = src.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DataAidl> CREATOR = new Creator<DataAidl>() {
        @Override
        public DataAidl createFromParcel(Parcel in) {
            return new DataAidl(in);
        }

        @Override
        public DataAidl[] newArray(int size) {
            return new DataAidl[size];
        }
    };
}
