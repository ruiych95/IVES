package com.example.ubuntu.ives;

import android.os.AsyncTask;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Scan extends AsyncTask<Void, Void, Void>
{
    private static final int TIME_OUT = 200;
    private DTO_Host dto_host;
    private ArrayList<DTO_Host> hostDTOList = new ArrayList<>();;
    private final List<Future<OBJ_ScanResult>> futures = new ArrayList<>();
    private BruteForce bruteForce = new BruteForce();
    private Port portCheck = new Port();
    private String hostIP;
    private int hostAmount = 0;
    private String subnetMask = "";
    private int ports[] = {22, 23};

    protected Void doInBackground(Void...params)
    {
        final ExecutorService executorService = Executors.newFixedThreadPool(20);
        Host host = new Host();

        getSubnetMask();

        for (int i = 0; i <= 255; i++)
        {
            String ip = subnetMask + i;
            futures.add(host.discovered(executorService, ip));
        }
        executorService.shutdown();

        checkPort();

        System.out.println(hostAmount + " Hosts discovered.");
        return null;
    }

    public ArrayList<DTO_Host> getDTOhostList()
    {
        return hostDTOList;
    }

    private void getSubnetMask()
    {
        try (final DatagramSocket socket = new DatagramSocket())
        {
            socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
            String ip = socket.getLocalAddress().getHostAddress();
            System.out.println("ip : " + ip);
            subnetMask = ip.substring(0, ip.lastIndexOf(".")) + ".";

            System.out.println("subnet : " + subnetMask);
        }
        catch (SocketException e)
        {
            e.printStackTrace();
        }
        catch (UnknownHostException e)
        {
            e.printStackTrace();

        }
    }

    private void checkPort()
    {
        for (final Future<OBJ_ScanResult> f : futures)
        {
            try
            {
                if (f.get().getDiscovered())
                {
                    dto_host = new DTO_Host();
                    hostIP = f.get().getIP();
                    System.out.println("Host : " + hostIP + "\t" + " discovered.");
                    InetAddress addr = null;
                    try
                    {
                        addr = InetAddress.getByName(hostIP);
                    }
                    catch (UnknownHostException e)
                    {
                        e.printStackTrace();
                    }
                    String host = addr.getHostName();
                    System.out.println("Host name : " + host);
                    dto_host.setIp(hostIP);
                    dto_host.setName(host);

                    for (Integer port : ports)
                    {
                        String portName;
                        if (portCheck.isOpen(hostIP, port))
                        {
                            switch (port)
                            {
                                case 22:
                                    portName = "SSH";
                                    System.out.println("\t" + "port : "
                                            + port + "[" + portName + "] " + "is open");
                                    dto_host.addPort(portName, true);
                                    dto_host.setPassword(bruteForce.ssh(hostIP));
                                    break;
                                case 23:
                                    portName = "Telnet";
                                    System.out.println("\t" + "port : "
                                            + port + "[" + portName + "] " + "is open");
                                    dto_host.addPort(portName, true);
                                    break;
                            }

                        }
                    }
                    hostDTOList.add(dto_host);
                    hostAmount++;
                }
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            catch (ExecutionException e)
            {
                e.printStackTrace();
            }
        }
    }

}
