#include "WiFiController.h"
#include <ESP8266WiFi.h>
#include <WiFiManager.h> 
#include <FS.h>
#include <ArduinoJson.h>


// WiFi config json file
/*{
  "SSID": "AP_SSID",
  "PASS": "password"
}*/
#define WIFI_CONFIG_JSON_PATH "/WiFi_config.json"


// Static Public Variable
String WiFiController::wifiManagerAPName = "Switch AP";
String WiFiController::wifiManagerAPPassword = "12345678";
char WiFiController::autoConnectWifiSSID[50] = "Switch_SSID";
char WiFiController::autoConnectWifiPASS[50] = "password";
int WiFiController::rebootCountdownTime = 5; // 5 Sec 
unsigned long WiFiController::wifiConnectTimeout = 10000; // 10 Sec
bool WiFiController::shouldSaveWifiConfigFile = false;

void saveWifiConfigCallback(){
    WiFiController::shouldSaveWifiConfigFile = true;
}

void wifiConfigModeCallback(WiFiManager *myWiFiManager){
    Serial.println("Start WiFiManager AP...");
}

void WiFiController::init(){
  initSPIFFS();
}

void WiFiController::initSPIFFS(){
  if(SPIFFS.begin()){
    Serial.println("SPIFFS loading completed");
    bool wifiConfigFileExists = loadWifiConfigFile();
    if(!wifiConfigFileExists){
      saveWifiConfigFile();
    }
  }else{
    Serial.println("SPIFFS failed to load");
  }  
}

void WiFiController::wifiConnect(){
  unsigned long startingTime = millis();
  Serial.println("Wifi Connect info");
  Serial.print("SSID: ");
  Serial.println(autoConnectWifiSSID);
  Serial.print("Password: ");
  Serial.println(autoConnectWifiPASS);
  WiFi.begin(autoConnectWifiSSID, autoConnectWifiPASS);

  while(WiFi.status() != WL_CONNECTED){
    delay(500);
    if((millis() - startingTime) > wifiConnectTimeout){
      initWifiManagerMode();
    }
  }
}


void WiFiController::initWifiManagerMode(){
  WiFiManager wm;
  wm.setDebugOutput(false);
  
  wm.resetSettings();

  wm.setSaveConfigCallback(saveWifiConfigCallback);

  wm.setAPCallback(wifiConfigModeCallback);

  WiFiManagerParameter ssid_text_box("key_ssid", "Enter auto connect SSID", autoConnectWifiSSID, 50);
  WiFiManagerParameter password_text_box("key_pass", "Enter auto connect Password", autoConnectWifiPASS, 50);

  wm.addParameter(&ssid_text_box);
  wm.addParameter(&password_text_box);

//  if(wifiForceConfig){
    if(!wm.startConfigPortal((const char*)wifiManagerAPName.c_str(), (const char*)wifiManagerAPPassword.c_str())){
      Serial.println("Wifi Manager conntect failed");
      ESP.restart();
      delay(5000);
    }
//  }else{
//    if(!wm.autoConnect((const char*)wifiManagerAPName.c_str(), (const char*)wifiManagerAPPassword.c_str())){
//      Serial.println("Wifi Manager conntect failed");
//      ScreenController::displayWifiConnectionFailedAndRebootAfterCountdown(rebootCountdownTime);
//      ESP.restart();
//      delay(5000);
//    }
//  }

  strncpy(autoConnectWifiSSID, ssid_text_box.getValue(), sizeof(autoConnectWifiSSID));
  strncpy(autoConnectWifiPASS, password_text_box.getValue(), sizeof(autoConnectWifiPASS));

  if(shouldSaveWifiConfigFile){
    saveWifiConfigFile();
  }
}


void WiFiController::saveWifiConfigFile(){
  Serial.println("Saving Wifi configuration...");
  // create a JSON document
  StaticJsonDocument<512> json;
  json["SSID"] = autoConnectWifiSSID;
  json["PASS"] = autoConnectWifiPASS;

  File wifiConfigFile = SPIFFS.open(WIFI_CONFIG_JSON_PATH, "w");
  if(wifiConfigFile){
    Serial.println("Start to write to file...");
    serializeJsonPretty(json, Serial);
    if(serializeJson(json, wifiConfigFile) == 0){
      // Error writing file
      Serial.println("Failed to write to file");
    }
  }else{
    // Failed to open WiFi config
    Serial.println("Failed to open WiFi config file for writing");
  }

  wifiConfigFile.close();
}


bool WiFiController::loadWifiConfigFile(){
  Serial.println("Loading Wifi configuration...");

  if(SPIFFS.exists(WIFI_CONFIG_JSON_PATH)){
    // Exists WiFi config json
    File wifiConfigFile = SPIFFS.open(WIFI_CONFIG_JSON_PATH, "r");
    if(wifiConfigFile){
      Serial.println("Start to read to file...");
      StaticJsonDocument<512> json;
      DeserializationError error = deserializeJson(json, wifiConfigFile);
      serializeJsonPretty(json, Serial);
      if(!error){
        // Parsing Json
        strcpy(autoConnectWifiSSID, json["SSID"]);
        strcpy(autoConnectWifiPASS, json["PASS"]);
        // autoConnectWifiPASS = json["PASS"].as<int>();

        Serial.println("Get WiFi Config Json");
        wifiConfigFile.close();
        return true;
      }else{
        // Error loading Json data
        Serial.println("Failed to load json config");
      }
    }else{
      // Failed to open WiFi config
      Serial.println("Failed to open WiFi config file for reading");
    }
    wifiConfigFile.close();
  }else{
    Serial.println("File not exists!");  
  }
  return false;
}
