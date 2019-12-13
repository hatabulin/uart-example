package com.example.uartishe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    private InputStream mInputStream;
    private OutputStream mOutputStream;
    protected SerialPort mSerialPort = null;
    protected SerialPortFinder serialPortFinder = new SerialPortFinder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String data_ports[] = serialPortFinder.getAllDevices();

        try {
            mSerialPort = new SerialPort(new File("/dev/ttyS0"), 9600, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }

        mOutputStream = mSerialPort.getOutputStream();
        mInputStream = mSerialPort.getInputStream();

        byte[] data = {10,11,12,13,14,15,(byte)200,(byte)210,(byte)220,(byte)230 };
        try {
            mOutputStream.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }

        mSerialPort.close();
    }
}
