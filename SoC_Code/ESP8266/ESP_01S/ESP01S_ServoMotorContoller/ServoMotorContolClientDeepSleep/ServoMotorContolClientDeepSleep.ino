#include <ESP8266WiFi.h>
#include <ESP8266HTTPClient.h>
#include <WiFiClient.h>

const char *SSID = "iotctr";
const char *PASSWORD = "12345678";

const String SERVER_NAME = "http://www.iotctr.com/ctr";

void servoControlHandler(const int &servoId, const String &option);

void setup()
{

    WiFi.begin(SSID, PASSWORD);
    while (WiFi.status() != WL_CONNECTED)
    {
        delay(500);
    }
}

void loop()
{
    // Check WiFi connection status
    if (WiFi.status() == WL_CONNECTED)
    {
        servoControlHandler(0, "switch");
        ESP.deepSleep(0);
    }
    
}

void servoControlHandler(const int &servoId, const String &option)
{
    WiFiClient client;
    HTTPClient http;

    String url = SERVER_NAME + "?dev_num=" + String(servoId, 10) + "&operate=" + option;
    http.begin(client, url.c_str());

    // Send HTTP GET request
    http.GET();

    http.end();
}
