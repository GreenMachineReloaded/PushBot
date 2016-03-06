package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.robocol.Telemetry;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
public class OpticDistanceSensor {


    DcMotor leftMotor;
    DcMotor rightMotor;
    OpticDistanceSensor opticDistanceSensoR;
    Sleeper s;
    double distance;


    public OpticDistanceSensor(DcMotor leftMotorArg, DcMotor rightMotorArg, OpticDistanceSensor opticDistanceSensorArg) throws InterruptedException {
        leftMotor = leftMotorArg;
        rightMotor = rightMotorArg;
        opticDistanceSensoR = opticDistanceSensorArg;
        s = new Sleeper();
    }

    public OpticDistanceSensor (OpticDistanceSensor opticDistanceSensorArg) throws InterruptedException {
        opticDistanceSensoR = opticDistanceSensorArg;
        s = new Sleeper();
    }

    public double OpticDistanceSensorGetDistance() {
        return distance;
    }
}
