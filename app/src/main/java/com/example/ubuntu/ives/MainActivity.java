package com.example.ubuntu.ives;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        Button bttnScan = findViewById(R.id.button);
        bttnScan.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(checkWifiOnAndConnected().equals("connected"))
                {
                    Intent loadingActivity = new Intent(MainActivity.this, LoadingActivity.class);
                    startActivity(loadingActivity);
                }
                else if (checkWifiOnAndConnected().equals("no connection"))
                {
                    Toast.makeText(getApplicationContext(), "Please connect to a network.",
                            Toast.LENGTH_LONG).show();
                }
                else if (checkWifiOnAndConnected().equals("off"))
                {
                    Toast.makeText(getApplicationContext(), "Please turn on Wi-Fi.",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private String checkWifiOnAndConnected()
    {
        WifiManager wifiMgr = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        if (wifiMgr.isWifiEnabled())
        {

            WifiInfo wifiInfo = wifiMgr.getConnectionInfo();

            if( wifiInfo.getNetworkId() == -1 )
            {
                return "no connection";
            }
            return "connected";
        }
        else
        {
            return "off";
        }
    }
}
