


const int but = 0;
const int led = 2;

void setup()
{
  Serial.begin(115200);
  // initialize the LED pin as an output:
  pinMode(but, INPUT);
  pinMode(led, OUTPUT);
}

void loop()
{
  int butState = digitalRead(but);
  Serial.println(butState);
  if (butState)
  {
    digitalWrite(led, HIGH);
  }
  else
  {
    digitalWrite(led, LOW);
  }
}
