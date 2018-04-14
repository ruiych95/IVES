package com.example.ubuntu.ives;

import org.apache.commons.collections4.map.MultiValueMap;

public class OBJ_WordList
{
    MultiValueMap map = new MultiValueMap();

    public OBJ_WordList()
    {
        map.put("", "");

        //IP camera default password https://github.com/jeanphorn/wordlist
        map.put("admin", "");          /*Foscam, March: Networks, Starvedia
                                            Wodsee*/
        map.put("admin", "1234");      /*DRS, Honeywell, Sentry360 (mini),
                                            Speco*/
        map.put("admin", "4321");      //Samsung Electronics, Samsung (new)
        map.put("admin", "9999");      //American Dynamics
        map.put("admin", "12345");     //Hikvision, Panasonic
        map.put("admin", "123456");    //ACTi
        map.put("admin", "1111111");   //Samsung Techwin (old)
        map.put("admin", "jvc");       //JVC
        map.put("admin", "admin");     /*American Dynamics, Avigilon,
                                            Basler, Brickcom, Dahua,
                                            Digital Watchdog, GeoVision,
                                            Grandstream, Pelco Sarix, Pixord,
                                            Sanyo, Sony, Stardot, Trendnet*/
        map.put("admin", "meinsm");    //Mobotix
        map.put("admin", "password");  //Scallop
        map.put("admin", "fliradmin"); //FLIR

        map.put("Admin", "1234");      //DVTel, DynaColor
        map.put("Admin", "123456");    //ACTi

        map.put("root", "");           //Vivotek
        map.put("root", "ikwd");       //Toshiba
        map.put("root", "pass");       //Axis
        map.put("root", "root");       //Samsung Electronics
        map.put("root", "admin");      //IPX-DDK
        map.put("root", "Admin");      //IPX-DDK
        map.put("root", "camera");     //Canon
        map.put("root", "system");     //IQinVision
        map.put("root", "arduino");    //Arduino YUN
        map.put("supervisor", "supervisor");   //VideoIQ
        map.put("ubnt", "ubnt");       //Ubiquiti
    }

    public MultiValueMap getWordList()
    {
        return map;
    }
}
