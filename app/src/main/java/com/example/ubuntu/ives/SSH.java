package com.example.ubuntu.ives;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class SSH
{
    private final int PORT = 22;

    public boolean connect(String ip, String user, String password)
    {
        try
        {
            JSch jsch = new JSch();
            Session session = jsch.getSession(user, ip, PORT);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setTimeout(50000);
            session.connect();
            session.disconnect();
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
}
