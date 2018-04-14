package com.example.ubuntu.ives;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.HashMap;

public class HostDetailActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_detail);

        TextView ipTextView = findViewById(R.id.ipTextView);
        TextView nameTextView = findViewById(R.id.nameTextView);
        TextView sshTextView = findViewById(R.id.sshTextView);
        TextView telnetTextView = findViewById(R.id.telnetTextView);
        TextView pwdTextView = findViewById(R.id.pwdTextView);
        Button sshInfo = findViewById(R.id.sshInfo);
        Button pwdInfo = findViewById(R.id.pwdInfo);
        Button telnetInfo = findViewById(R.id.telnetInfo);
        DTO_Host hostDTO = getIntent().getParcelableExtra("hostDTO");
        String ip = hostDTO.getIp();
        String name = hostDTO.getName();
        String credentials = hostDTO.getPassword();
        HashMap<String, Boolean> ports = hostDTO.getPorts();
        if(ports.get("SSH"))
        {
            sshTextView.setText(R.string.sshOpen);
        }
        else
        {
            sshTextView.setText(R.string.sshClose);
        }
        if(ports.get("Telnet"))
        {
            telnetTextView.setText(R.string.telnetOpen);
        }
        else
        {
            telnetTextView.setText(R.string.telnetClose);
        }

        ipTextView.setText(ip);
        ipTextView.setTextColor(Color.parseColor("#000000"));
        nameTextView.setText(name);
        sshInfo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent sshInfoActivity = new Intent(getApplicationContext(), SSHInfoActivity.class);
                startActivity(sshInfoActivity);
            }
        });
        telnetInfo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent telnetInfoActivity = new Intent(getApplicationContext(), TelnetInfoActivity.class);
                startActivity(telnetInfoActivity);
            }
        });

        if(credentials.equals("No default credentials found."))
        {
            pwdTextView.setText(credentials);
            pwdTextView.setTextColor(Color.parseColor("#303F9F"));
            pwdInfo.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Intent pwdInfoActivity = new Intent(getApplicationContext(), PwdInfoActivity.class);
                    startActivity(pwdInfoActivity);
                }
            });
        }
        else
        {
            pwdTextView.setText(R.string.pwdFound + credentials);
            pwdTextView.setTextColor(Color.RED);
            pwdInfo.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Intent foundInfoActivity = new Intent(getApplicationContext(), FoundInfoActivity.class);
                    startActivity(foundInfoActivity);
                }
            });
        }
    }
}
