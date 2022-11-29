#include <ESP8266WiFi.h>
#include <DNSServer.h>
#include <ESP8266WebServer.h>
#include <WiFiClient.h>
#include <Servo.h>

#include "PageIndex.h";

#define SystemLED 0
#define ServoPort 2 

//----------------------------------------
const char* ssid = "Switch AP";
const char* password = "12345678";
//----------------------------------------
String onOperate = "on";
String offOperate = "off";

const byte DNS_PORT = 53;

DNSServer dnsServer;
Servo myservo;  

//IPAddress local_IP(192,168,4,22);
//IPAddress gateway(192,168,4,9);
//IPAddress subnet(255,255,255,0);

ESP8266WebServer server(80);  //--> Server on port 80

void servoAngleToAngle(int angle1, int angle2){
  if(angle1 <= angle2){
     for (int i = angle1; i <= angle2; i++) {
      myservo.write(i);
      delay(15); 
    }
  }else{
    for (int i = angle1; i >= angle2; i--) {
      myservo.write(i);
      delay(15); 
    }
  }
}


void setupDNSD(){
  dnsServer.setErrorReplyCode(DNSReplyCode::NoError); 
  dnsServer.start(DNS_PORT, F("www.switch.com"), WiFi.softAPIP());
}

void handleRoot() {
 String s = MAIN_page; 
 server.send(200, "text/html", s); 
}


void handleOperate(){
  String operate = server.arg("operate");
  
  if(onOperate == operate){
    servoAngleToAngle(75, 25);
    delay(15);
    servoAngleToAngle(25, 75);
  }else if(offOperate == operate){
    servoAngleToAngle(75, 125);
    delay(15);
    servoAngleToAngle(125, 75);
  }
  server.send(200, "text/plane","");
}



//----------------------------------------Setup----------------------------------------
void setup() {
  Serial.begin(115200);
  delay(500);

  myservo.attach(ServoPort); 
  
//  WiFi.softAPConfig(local_IP, gateway, subnet);
  WiFi.softAP(ssid, password);  
  Serial.print("Soft-AP IP address = ");
  Serial.println(WiFi.softAPIP());

  server.on("/",handleRoot);  
  server.on("/operate",handleOperate); 
  setupDNSD();
  server.begin();  
  
  pinMode(SystemLED, OUTPUT);
  digitalWrite(SystemLED, HIGH);
  myservo.write(75);
}
//----------------------------------------loop------------------------------------------
void loop() {
 dnsServer.processNextRequest();
 server.handleClient();
}
