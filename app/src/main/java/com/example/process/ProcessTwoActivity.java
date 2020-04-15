package com.example.process;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.gitflow.R;

import androidx.annotation.Nullable;

public class ProcessTwoActivity extends Activity {

    IControllerAidl controllerAidl;

    boolean isConnect = false;

    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            controllerAidl = IControllerAidl.Stub.asInterface(service);
            if(controllerAidl!=null){
                try {
                    ProcessData.myData = controllerAidl.getData().data1;
                } catch (RemoteException e) {
                    Log.d("whj", "read except:"+e.getMessage());
                }
            }
            isConnect = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d("whj","service disConnect");
            isConnect = false;
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process);
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProcessTwoActivity.this, ProcessOneActivity.class));
                finish();
            }
        });
        bindService();
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("whj","service onResume");

        ((TextView)findViewById(R.id.text)).setText(""+ProcessData.myData);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    void bindService(){
        Intent intent = new Intent();
        ComponentName componentName = new ComponentName("com.example.gitflow", "com.example.process.AidlService");
        intent.setComponent(componentName);
//        intent.setPackage("com.example.gitflow");
        intent.setAction("com.example.process.action");
        Log.d("whj","service bind:"+bindService(intent,connection, Context.BIND_AUTO_CREATE));
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
            Log.d("whj","service unbind");
            unbindService(connection);
        super.onDestroy();
    }
}
