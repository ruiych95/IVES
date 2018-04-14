package com.example.ubuntu.ives;

import java.net.InetAddress;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class Host
{
    public static Future<OBJ_ScanResult> discovered(final ExecutorService es, final String ip)
    {
        return es.submit(new Callable<OBJ_ScanResult>()
        {
            @Override
            public OBJ_ScanResult call()
            {
                try
                {
                    InetAddress inet = InetAddress.getByName(ip);
                    if (inet.isReachable(5000))
                    {
                        return new OBJ_ScanResult(ip, true);
                    }
                    else
                    {
                        return new OBJ_ScanResult(ip, false);
                    }
                }
                catch ( Exception e )
                {
                    System.out.println("Exception: " + e.getMessage());
                    return new OBJ_ScanResult(ip, false);
                }
            }
        });
    }
}
