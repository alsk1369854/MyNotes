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


const byte DNS_PORT = 53;

DNSServer dnsServer;
Servo myservo;  
ESP8266WebServer server(80);  //--> Server on port 80

// Servo parameter
int centerAngle = 90;
int switchRotationAngle = 40;
int servoDelay = 15;

// Server API Operate map
String onOperate = "on";
String offOperate = "off";

void servoAngleToAngle(int startAngle, int endAngle){
  while(startAngle != endAngle){
    myservo.write(startAngle);
    (startAngle < endAngle)? startAngle++ : startAngle--;
    delay(servoDelay); 
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
  
  int targetAngle = centerAngle;
  if(onOperate == operate){
    targetAngle = centerAngle - switchRotationAngle;
  }else if(offOperate == operate){
    targetAngle = centerAngle + switchRotationAngle;
  }
  
  servoAngleToAngle(centerAngle, targetAngle);
  servoAngleToAngle(targetAngle, centerAngle);
  
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
  myservo.write(centerAngle);
}
//----------------------------------------loop------------------------------------------
void loop() {
 dnsServer.processNextRequest();
 server.handleClient();
}
