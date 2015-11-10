package com.qualcomm.ftcrobotcontroller.opmodes;
import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.ftccommon.DbgLog;
import com.qualcomm.ftcrobotcontroller.R;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.GyroSensor;

/**
 * Created by Amber on 11/8/2015.
 */
public class TestingTheGyroSensors extends LinearOpMode{
    GyroSensor gyro;
    int x, y, z;
    boolean forever = true;
    @Override
    public void runOpMode() throws InterruptedException {

        gyro = hardwareMap.gyroSensor.get("gyro");
        gyro.calibrate();
        waitForStart();
        while(forever = true) {
            x = gyro.rawX();
            y = gyro.rawY();
            z = gyro.rawZ();
            telemetry.addData("1. x", String.format("%03d", x));
            telemetry.addData("2. y", String.format("%03d", y));
            telemetry.addData("3. z", String.format("%03d", z));
            sleep(100);
        }
    }
}
