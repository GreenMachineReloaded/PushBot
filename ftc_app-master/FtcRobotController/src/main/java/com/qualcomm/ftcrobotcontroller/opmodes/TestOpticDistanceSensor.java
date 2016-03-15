package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;

import java.util.Calendar;

public class TestOpticDistanceSensor extends LinearOpMode {
    DcMotor leftMotor;
    DcMotor rightMotor;

    OpticalDistanceSensor opticSensorMap;
    GMROpticDistanceSensor opticSensor;

    int one = 1;

    Long endTime;

    Calendar rightNow;

    Integer longToInt;

    @Override
    public void runOpMode() throws InterruptedException {
        leftMotor = hardwareMap.dcMotor.get("leftDriveMotor");
        rightMotor = hardwareMap.dcMotor.get("rightDriveMotor");
        opticSensor = new GMROpticDistanceSensor(opticSensorMap = hardwareMap.opticalDistanceSensor.get("optic"));

        rightNow = Calendar.getInstance();

        endTime = (rightNow.getTimeInMillis());

        waitForStart();
        while (one == 1) {
            longToInt = endTime.intValue();
            //telemetry.addData("Current Time" , rightNow.getTimeInMillis());
            telemetry.addData("Optical Sensor Distance", longToInt);
        }
    }
}
