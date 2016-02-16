package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.robocol.Telemetry;

/**
 * Created by Payton on 2/13/2016. Waffle
 */
public class GyroTest extends LinearOpMode {
    GyroSensor gyro;

    Telemetry t;

    int count;
    @Override
    public void runOpMode() throws InterruptedException {

        gyro = hardwareMap.gyroSensor.get("gyro");

        t = telemetry;

        count = 0;

        GyroObject gyroTest = new GyroObject(gyro);

        waitForStart();

        while(count < 1) {
            t.addData("Current Gyro Heading: ", gyroTest.getPosition());
        }

    }
}
