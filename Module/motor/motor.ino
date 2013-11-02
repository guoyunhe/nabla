// define the motor pins, support 3 motors
int motorPin[3][2] = {{3, 5} , {6, 9} , {10, 11}};
// id of motors
int id;
// string and char to store bluetooth data
char input; // every byte
String value; // key's value
String name; // key's name
// flags for bluetooth reading status
const int END = 0;
const int NAME = 1;
const int VALUE = 2;
// initial status of bluetooth reading process
int readProcess = NAME;

void setup() {
  // setup serial port for bluetooth
  Serial.begin(9600);
  // initialize all output pins
  for(int i = 0; i < 3; i++){
    for(int j = 0; j < 2; j++){
      pinMode(motorPin[i][j], OUTPUT);
      digitalWrite(motorPin[i][j], LOW);
    }
  }
}

void loop() {
  // read and process bluetooth data
  if(Serial.available()){
    input = Serial.read();
    process();
  }
}
// read serial data as key and run them
void process() {
  if(input==','){
    readProcess = VALUE;
    Serial.println(name);
  }
  else if(input==';'){
    Serial.println(value);
    run(name, value);
    name = "";
    value = "";
    readProcess = NAME;
  }
  else if(readProcess==NAME){
    name+=input;
  }
  else if(readProcess==VALUE){
    value+=input;
  }
}
// explain and excute key
// id : choose id of current motors
// speed : set speed of current motor
void run(String name, String value){
  if(name=="id"){
    int num = value.toInt();
    id = num;
  }
  else if(name=="speed"){
    int num = value.toInt();
    if(num>=0){
      analogWrite(motorPin[id][1], num);
      analogWrite(motorPin[id][0], 0);
    }
    else{
      analogWrite(motorPin[id][1], 0);
      analogWrite(motorPin[id][0], -num);
    }
  }
}
