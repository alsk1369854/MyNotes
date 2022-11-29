#include "WifiController.h"
#include <ESP8266WiFi.h>
#include <ESP8266WebServer.h>
#include <Servo.h>

#include "PageIndex.h";

#define SystemLED 0
#define ServoPort 2 

// Static IP
IPAddress local_IP(192,168,1,6);
IPAddress gateway(192,168,1,1);
IPAddress subnet(255,255,255,0);

Servo myservo;  

ESP8266WebServer server(80);  //--> Server on port 80

// Server API Operate map
String onOperate = "on";
String offOperate = "off";

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

  // Initialize Switch Servo
  myservo.attach(ServoPort); 
  myservo.write(75);

  // Wifi Connecting
  WiFi.config(local_IP, gateway, subnet);
  WiFiController::init();
  WiFiController::wifiConnect();
  
  // Wifi Connected 
  pinMode(SystemLED, OUTPUT);
  digitalWrite(SystemLED, HIGH);

  // Initialize Server API
  server.on("/",handleRoot);  
  server.on("/operate",handleOperate);
  server.begin();  
  Serial.println("Application Runing...");
}

//----------------------------------------loop------------------------------------------
void loop() {
	server.handleClient();
}
