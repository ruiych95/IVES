package com.example.ubuntu.ives;

import java.util.List;
import java.util.Map;

public class BruteForceThread implements Runnable
{
    private SSH ssh;
    private String ip;
    private Map.Entry pair;
    private List list;
    private int j;
    private String credentials = "";

    public BruteForceThread(SSH ssh, String ip, Map.Entry pair,
                            List list, int j)
    {
        this.ssh = ssh;
        this.ip = ip;
        this.pair = pair;
        this.list = list;
        this.j = j;
    }

    public void run()
    {
        if(ssh.connect(ip, pair.getKey().toString(), list.get(j).toString()))
        {
            credentials = pair.getKey().toString() + "/" + list.get(j).toString();
            System.out.println("\t"+"Found default credentials : " + credentials);
        }
    }

    public String getValue()
    {
        return credentials;
    }
}
