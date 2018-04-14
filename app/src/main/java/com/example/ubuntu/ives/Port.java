package com.example.ubuntu.ives;

import java.net.InetSocketAddress;
import java.net.Socket;

public class Port
{
    private static final int TIME_OUT = 200;

    public static boolean isOpen(String ip, int port)
    {
        try
        {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(ip, port), TIME_OUT);
            socket.close();
            return true;
        }
        catch (Exception ex)
        {
            return false;
        }
    }
}
