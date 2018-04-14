package com.example.ubuntu.ives;

public class OBJ_ScanResult
{
    private final String ip;
    private final boolean isDiscovered;

    public OBJ_ScanResult(String ip, boolean isDiscovered)
    {
        this.ip = ip;
        this.isDiscovered = isDiscovered;
    }

    public String getIP()
    {
        return ip;
    }

    public boolean getDiscovered()
    {
        return isDiscovered;
    }
}
