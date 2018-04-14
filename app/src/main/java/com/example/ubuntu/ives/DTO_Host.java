package com.example.ubuntu.ives;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.HashMap;

public class DTO_Host implements Parcelable
{
    private String ip;
    private String name;
    private HashMap<String, Boolean> ports;
    private String password;

    public DTO_Host()
    {
        ports = new HashMap<>();
        ports.put("SSH", false);
        ports.put("Telnet", false);
        password = "No default credentials found.";
    }

    public DTO_Host(String ip)
    {
        this.ip = ip;
        ports = new HashMap<>();
    }

    protected DTO_Host(Parcel in) {
        ports = new HashMap<>();
        ip = in.readString();
        name = in.readString();
        ports = in.readHashMap(Boolean.class.getClassLoader());
        password = in.readString();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void addPort(String port, boolean state)
    {
        ports.put(port, state);
    }

    public HashMap<String, Boolean> getPorts()
    {
        return ports;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static final Creator<DTO_Host> CREATOR = new Creator<DTO_Host>()
    {
        @Override
        public DTO_Host createFromParcel(Parcel in) {
            return new DTO_Host(in);
        }

        @Override
        public DTO_Host[] newArray(int size) {
            return new DTO_Host[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ip);
        dest.writeString(name);
        dest.writeMap(ports);
        dest.writeString(password);
    }
}
