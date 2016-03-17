package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TestOpticDistanceSensor extends LinearOpMode {
    DcMotor leftMotor;
    DcMotor rightMotor;

    OpticalDistanceSensor opticSensorMap;
    GMROpticDistanceSensor opticSensor;

    Integer currentTime;
    Integer endTime;

    @Override
    public void runOpMode() throws InterruptedException {
        leftMotor = hardwareMap.dcMotor.get("leftDriveMotor");
        rightMotor = hardwareMap.dcMotor.get("rightDriveMotor");
        opticSensor = new GMROpticDistanceSensor(opticSensorMap = hardwareMap.opticalDistanceSensor.get("optic"));

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("ss");

//        currentTime = Integer.parseInt(sdf.format(cal.getTime()));
//        endTime = (Integer.parseInt(sdf.format(cal.getTime())) + 30);
//        if (endTime > 60) {
//            endTime = (endTime - 60);
//        }

        waitForStart();
        while (true) {
            telemetry.addData("Current time", (sdf.format(cal.getTime())));
            telemetry.addData("Time in Thirty Seconds" , (sdf.format(cal.getTime())));
            telemetry.addData("Curent Sensor Distance", opticSensor.getDistance());
        }
    }
}
