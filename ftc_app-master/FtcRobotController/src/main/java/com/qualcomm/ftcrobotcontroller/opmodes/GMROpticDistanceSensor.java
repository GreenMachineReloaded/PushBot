package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
public class GMROpticDistanceSensor {


    DcMotor leftMotor;
    DcMotor rightMotor;
    OpticalDistanceSensor opticalDistanceSensoR;

    Sleeper s;
    double distance;


    public GMROpticDistanceSensor(DcMotor leftMotorArg, DcMotor rightMotorArg, OpticalDistanceSensor opticDistanceSensorArg) throws InterruptedException {
        leftMotor = leftMotorArg;
        rightMotor = rightMotorArg;
        opticalDistanceSensoR = opticDistanceSensorArg;
        s = new Sleeper();
    }

    public GMROpticDistanceSensor(OpticalDistanceSensor opticDistanceSensorArg) throws InterruptedException {
        opticalDistanceSensoR = opticDistanceSensorArg;
        s = new Sleeper();
    }

    public double OpticDistanceSensorGetDistance() {
        distance = opticalDistanceSensoR.getLightDetected();
        return distance;
    }
}
