#include <ESP8266WiFi.h>
#include <ESP8266HTTPClient.h>
#include <WiFiClient.h>

#define BUTTON_PIN 1

const char *SSID = "iotctr";
const char *PASSWORD = "12345678";

const String SERVER_NAME = "http://www.iotctr.com/ctr";

unsigned long lastTime = 0;
unsigned long timerDelay = 1000;

String buttonOption = "off"; // off | on
bool buttonSwitchCtr = false; 

void servoControlHandler(const int &servoId, const String &option);

void setup()
{
    //    Serial.begin(115200);
    pinMode(LED_BUILTIN, OUTPUT);
    pinMode(BUTTON_PIN, INPUT);

    WiFi.begin(SSID, PASSWORD);
    while (WiFi.status() != WL_CONNECTED)
    {
        delay(500);
    }
}

void loop()
{
    // Send an HTTP POST request depending on timerDelay
    if ((millis() - lastTime) > timerDelay)
    {
        // Check WiFi connection status
        if (WiFi.status() == WL_CONNECTED)
        {

                int buttonState = digitalRead(BUTTON_PIN);
                if (buttonState)
                {
                    buttonSwitchCtr = !buttonSwitchCtr;
                    digitalWrite(LED_BUILTIN, buttonSwitchCtr);
                    buttonOption = (buttonSwitchCtr)? "on" : "off"; 
                    servoControlHandler(0, buttonOption);
                }
            
        }

        lastTime = millis();
    }
}

void servoControlHandler(const int &servoId ,const String &option)
{
    WiFiClient client;
    HTTPClient http;

    String url = SERVER_NAME + "?dev_num=" + String(servoId, 10) + "&operate=" + option;
    http.begin(client, url.c_str());

    // Send HTTP GET request
    http.GET();

    http.end();
}
