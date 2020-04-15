package com.example.process;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

public class AidlService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("whj","service data onBind,"+ProcessData.myData);
        return stub;
    }

    DataAidl dataAidl = new DataAidl();

    private final IControllerAidl.Stub stub = new IControllerAidl.Stub() {
        @Override
        public DataAidl getData() throws RemoteException {
            dataAidl.data1 = ProcessData.myData;
            Log.d("whj","data get:"+ProcessData.myData);
            return dataAidl;
        }

        @Override
        public void setData(DataAidl data) throws RemoteException {
            dataAidl.data1 = data.data1;
            ProcessData.myData = data.data1;
        }
    };

}
