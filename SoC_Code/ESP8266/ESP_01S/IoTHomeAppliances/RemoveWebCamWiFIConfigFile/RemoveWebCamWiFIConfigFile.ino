#include <FS.h>

#define WIFI_CONFIG_JSON_PATH "/WiFi_config.json"

void setup() {
  Serial.begin(115200);
  delay(5000);
  if(SPIFFS.begin()){
     SPIFFS.remove(WIFI_CONFIG_JSON_PATH);
    Serial.println("Success");
  }else{
    Serial.println("Failed");  
  };
}

void loop() {
//  Serial.println("Done");
}
