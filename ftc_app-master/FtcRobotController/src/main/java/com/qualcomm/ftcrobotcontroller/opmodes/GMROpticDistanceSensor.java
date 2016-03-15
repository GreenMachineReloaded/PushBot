package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
public class GMROpticDistanceSensor {


    DcMotor lm;
    DcMotor rm;
    OpticalDistanceSensor opticSensor;

    Sleeper s;
    double distance;


    public GMROpticDistanceSensor(DcMotor leftMotor, DcMotor rightMotor, OpticalDistanceSensor opticDistanceSensor) throws InterruptedException {
        lm = leftMotor;
        rm = rightMotor;
        opticSensor = opticDistanceSensor;
        s = new Sleeper();
    }

    public GMROpticDistanceSensor(OpticalDistanceSensor opticDistanceSensor) throws InterruptedException {
        opticSensor = opticDistanceSensor;
        s = new Sleeper();
    }

    public double getDistance() {
        distance = opticSensor.getLightDetected();
        return distance;
    }
}
