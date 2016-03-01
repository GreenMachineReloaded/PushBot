package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftcrobotcontroller.opmodes.drivers.GMRServo;
import com.qualcomm.robotcore.robocol.Telemetry;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Patrick on 3/1/2016.
 */
public class BeaconPress {
    ColorSensorObject colorSensor;
    GMRServo servo;
    UltrasonicObject ultraSonic;
    Telemetry t;
    Sleeper sleep;

    public BeaconPress(ColorSensorObject cs, GMRServo gmrServo, UltrasonicObject us, Telemetry telemetry) {
        colorSensor = cs;
        servo = gmrServo;
        ultraSonic = us;
        t = telemetry;
        sleep = new Sleeper();
    }

    public void pressBeacon() {

//    while(ultrasonic.getRangeInches() > 5){
//
//        if(){
//
//        }
//        else if(){
//
//        }
//    }
//}
//
//}
    }
}