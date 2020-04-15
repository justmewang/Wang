// IControllerAidl.aidl
package com.example.process;

// Declare any non-default types here with import statements
import com.example.process.DataAidl;
interface IControllerAidl {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    DataAidl getData();
    void setData(inout DataAidl data);
}
