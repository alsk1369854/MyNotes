#include "SPIFFS.h"
#include <ArduinoJson.h>

const String UID_STORE_FILE_PATH = "/uid.txt";

void setup()
{
  Serial.begin(115200);
  initSPIFFS();
  readSPIFFS(UID_STORE_FILE_PATH);
}

void loop()
{
}

void initSPIFFS()
{
  if (SPIFFS.begin(true))
  {
    Serial.println("SPIFFS loading completed");
  }
  else
  {
    Serial.println("SPIFFS failed to load");
  }
}

void readSPIFFS(const String filePath)
{
    File file = SPIFFS.open(filePath, "r");

    if (!file)
    {
        Serial.println("There was an error opening the file for writing");
        return;
    }

    Serial.println("read file...");
    StaticJsonDocument<512> json;
    DeserializationError error = deserializeJson(json, file);
    serializeJsonPretty(json, Serial);
    Serial.println();

    if(!error){
        char temp[50];
        // Parsing Json
        strcpy(temp, json["CONTENT"]);
        
        Serial.print("File read:");
        Serial.println(temp);
      }else{
        Serial.println("Failed to load json config");
      }

    file.close();
}
