#ifndef WiFiController_h
#define WiFiController_h

#include <Arduino.h>

class WiFiController{

  public:
    static String wifiManagerAPName;
    static String wifiManagerAPPassword;
    static char autoConnectWifiSSID[50];
    static char autoConnectWifiPASS[50];
    static int rebootCountdownTime;
    static unsigned long wifiConnectTimeout;
    static bool shouldSaveWifiConfigFile;
    
    static void init();
    static void initSPIFFS();
    static void wifiConnect();
    static void saveWifiConfigFile();
    static bool loadWifiConfigFile();
    static void initWifiManagerMode();
};

#endif
