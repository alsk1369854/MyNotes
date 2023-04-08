#include <SPI.h>
#include <MFRC522.h>
#include "SPIFFS.h"
 
#define SS_PIN 10
#define RST_PIN 7

#define READ_CARD_UID_STORE_PATH "/uid.txt"
 
MFRC522 rfid(SS_PIN, RST_PIN); // Instance of the class
 
MFRC522::MIFARE_Key key; 

void printHex(byte *buffer, byte bufferSize);
void initSPIFFS();
 
void setup() { 
  Serial.begin(115200);
  SPI.begin(); // Init SPI bus
  rfid.PCD_Init(); // Init RC522 
  initSPIFFS();
}
 
void loop() {
 
  // Reset the loop if no new card present on the sensor/reader. This saves the entire process when idle.
  if ( ! rfid.PICC_IsNewCardPresent())
    return;
 
  // Verify if the NUID has been readed
  if ( ! rfid.PICC_ReadCardSerial())
    return;
 
  MFRC522::PICC_Type piccType = rfid.PICC_GetType(rfid.uid.sak);
 
  Serial.print(F("RFID Tag UID:"));
  printHex(rfid.uid.uidByte, rfid.uid.size);
  Serial.println("");
 
  rfid.PICC_HaltA(); // Halt PICC
}
 
//Routine to dump a byte array as hex values to Serial. 
void printHex(byte *buffer, byte bufferSize) {
  String uidStr = "My uid: ";
  for (byte i = 0; i < bufferSize; i++) {
    uidStr += (buffer[i] < 0x10 ? " 0" : " ");
    uidStr += String(buffer[i], HEX);
//    Serial.print(buffer[i] < 0x10 ? " 0" : " ");
//    Serial.print();
  }
  Serial.println(uidStr);
}

void initSPIFFS(){
  if(SPIFFS.begin(true)){
    Serial.println("SPIFFS loading completed");
  }else{
    Serial.println("SPIFFS failed to load");
  }  
}
