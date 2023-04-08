#include <SPI.h>
#include <MFRC522.h>
#include "SPIFFS.h"
#include <ArduinoJson.h>


#define SS_PIN 5   // ESP32 pin GIOP5
#define RST_PIN 27 // ESP32 pin GIOP27

const String UID_STORE_FILE_PATH = "/uid.txt";

void initSPIFFS();
void writeSPIFFS(const String filePath, const String content);
void readSPIFFS(const String filePath);

MFRC522 rfid(SS_PIN, RST_PIN);

void setup()
{
    Serial.begin(115200);
    SPI.begin();     // init SPI bus
    rfid.PCD_Init(); // init MFRC522
    initSPIFFS();

    Serial.println("Tap an RFID/NFC tag on the RFID-RC522 reader");
}

void loop()
{
    if (rfid.PICC_IsNewCardPresent())
    { // new tag is available
        if (rfid.PICC_ReadCardSerial())
        { // NUID has been readed
            MFRC522::PICC_Type piccType = rfid.PICC_GetType(rfid.uid.sak);
            Serial.print("RFID/NFC Tag Type: ");
            Serial.println(rfid.PICC_GetTypeName(piccType));

            // print UID in Serial Monitor in the hex format
            String cardUID = "";
            Serial.print("UID:");
            for (int i = 0; i < rfid.uid.size; i++)
            {
                cardUID += (rfid.uid.uidByte[i] < 0x10 ? " 0" : " ");
                cardUID += String(rfid.uid.uidByte[i], HEX);
            }
            Serial.println(cardUID);
            writeSPIFFS(UID_STORE_FILE_PATH, cardUID);
            
            rfid.PICC_HaltA();      // halt PICC
            rfid.PCD_StopCrypto1(); // stop encryption on PCD
        }
    }
    readSPIFFS(UID_STORE_FILE_PATH);
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

void writeSPIFFS(const String filePath, const String content)
{
    File file = SPIFFS.open(filePath, FILE_WRITE);

    if (!file)
    {
        Serial.println("There was an error opening the file for writing");
        return;
    }

    Serial.println("write file...");
    StaticJsonDocument<512> json;
    json["CONTENT"] = content; 

    serializeJsonPretty(json, Serial);
    if(serializeJson(json, file) == 0){
      Serial.println("Failed to write to file");
    }
    Serial.println();
    
    file.close();
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
//    serializeJsonPretty(json, Serial);
//    Serial.println();

    if(!error){
        char temp[50];
        // Parsing Json
        strcpy(temp, json["CONTENT"]);
        
        Serial.print("File read: ");
        Serial.println(temp);
      }else{
        Serial.println("Failed to load json config");
      }

    file.close();
}
