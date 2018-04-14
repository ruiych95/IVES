package com.example.ubuntu.ives;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class LoadingActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        final Scan scanForHosts = new Scan();
        Thread thread = new Thread()
        {
            @Override
            public void run()
            {
                try
                {
                    scanForHosts.execute().get();
                    ArrayList<DTO_Host> hostDTOList = scanForHosts.getDTOhostList();
                    Intent hostListsActivity = new Intent
                            (getApplicationContext(), HostListActivity.class);
                    hostListsActivity.putParcelableArrayListExtra("hostDTO", hostDTOList);
                    startActivity(hostListsActivity);
                    finish();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }
}
