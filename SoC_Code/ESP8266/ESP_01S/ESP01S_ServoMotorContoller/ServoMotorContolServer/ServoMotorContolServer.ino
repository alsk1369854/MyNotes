#include <ESP8266WiFi.h>
#include <DNSServer.h>
#include <ESP8266WebServer.h>
#include <WiFiClient.h>
#include <Servo.h>
#include "PageIndex.h";

#define SERVO_0 0
#define SERVO_1 1
#define SERVO_2 2
#define SERVO_3 3

//----------------------------------------
const char *SSID = "iotctr";
const char *PASSWORD = "12345678";
//----------------------------------------

const byte DNS_PORT = 53;
const int SERVO_LIST_LEN = 4;
Servo SERVO_LIST[SERVO_LIST_LEN];
const int SERVO_PIN_LIST[SERVO_LIST_LEN] = {SERVO_0, SERVO_1, SERVO_2, SERVO_3};
bool servoState[SERVO_LIST_LEN] = {false, false, false, false};

DNSServer dnsServer;
ESP8266WebServer server(80); //--> Server on port 80

// Servo parameter
int centerAngle = 90;
int switchRotationAngle = 55;
int servoDelay = 15;

// Server API Ctr map
const String SWITCH_OPERATE = "switch";
const String ON_OPERATE = "on";
const String OFF_OPERATE = "off";

void servoAngleToAngle(Servo &servo, int startAngle, int endAngle);
void setupDNSD();
void handleRoot();
void handleCtr();
void servoInit();

void setup()
{
  Serial.begin(115200);
  delay(500);

  servoInit();

  WiFi.softAP(SSID, PASSWORD);
  Serial.print("Soft-AP IP address = ");
  Serial.println(WiFi.softAPIP());

  server.on("/", handleRoot);
  server.on("/ctr", handleCtr);
  setupDNSD();
  server.begin();
  Serial.println("Server begin");
}

void loop()
{
  dnsServer.processNextRequest();
  server.handleClient();
  //  ESP.deepSleep(0);
}

void servoAngleToAngle(Servo &servo, int startAngle, int endAngle)
{
  while (startAngle != endAngle)
  {
    servo.write(startAngle);
    (startAngle < endAngle) ? startAngle++ : startAngle--;
    delay(servoDelay);
  }
}

void setupDNSD()
{
  dnsServer.setErrorReplyCode(DNSReplyCode::NoError);
  dnsServer.start(DNS_PORT, F("www.iotctr.com"), WiFi.softAPIP());
}

void handleRoot()
{
  String s = MAIN_page;
  server.send(200, "text/html", s);
}

void handleCtr()
{
  String operate = server.arg("operate");
  String strDevNum = server.arg("dev_num");
  Serial.println("dev_num: " + strDevNum);
  Serial.println("operate: " + operate);

  // 检查第1个字符是否是数字
  if (isDigit(strDevNum.charAt(0)))
  {
    int devNum = atoi(strDevNum.c_str());
    int targetAngle = centerAngle;

    // switch operate
    if (SWITCH_OPERATE == operate)
    {
      servoState[devNum] = !servoState[devNum];
      operate = (servoState[devNum]) ? ON_OPERATE : OFF_OPERATE;
    }

    // on operate
    if (ON_OPERATE == operate)
    {

      servoState[devNum] = true;
      targetAngle = centerAngle - switchRotationAngle;
    }
    // off operate
    else if (OFF_OPERATE == operate)
    {
      servoState[devNum] = false;
      targetAngle = centerAngle + switchRotationAngle;
    }

    // do servo
    servoAngleToAngle(SERVO_LIST[devNum], centerAngle, targetAngle);
    servoAngleToAngle(SERVO_LIST[devNum], targetAngle, centerAngle);
  }
  else
  {
    Serial.println("Invalid String"); // 字符串无效
  }
  server.send(200, "text/plane", "");
}

void servoInit()
{
  Serial.println("Servo Init...");
  for (int i = 0; i < SERVO_LIST_LEN; i++)
  {
    SERVO_LIST[i].attach(SERVO_PIN_LIST[i]);
    SERVO_LIST[i].write(centerAngle);
  }
}
