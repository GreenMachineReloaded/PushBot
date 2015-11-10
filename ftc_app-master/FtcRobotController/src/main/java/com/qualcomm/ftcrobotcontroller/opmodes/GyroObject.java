package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.ftccommon.DbgLog;
import com.qualcomm.ftcrobotcontroller.R;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.GyroSensor;


/**
 * Created by Amber on 11/6/2015.
 */
public class GyroObject extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

    }

    DcMotor leftMotor;
    DcMotor rightMotor;
    GyroSensor DegreesTurned;

    public void GryoObject(double d) {
        setGyroObject(d);
    }

    public void setGyroObject(double degrees) {
        leftMotor = hardwareMap.dcMotor.get("left_drive");
        rightMotor = hardwareMap.dcMotor.get("right_drive");
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        int xVal, zVal;
        DegreesTurned = hardwareMap.gyroSensor.get("gyro");
        DegreesTurned.resetZAxisIntegrator();
        DegreesTurned.calibrate();
        if (degrees > 0) {
            //moves the motors
            leftMotor.setPower(1);
            rightMotor.setPower(-1);
            while (degrees == 0) {
                //in here there needs to be an updater for the number of degrees the robot has turned.
                xVal = DegreesTurned.rawX();
                zVal = DegreesTurned.rawZ();
            }
            //stops the motors
            leftMotor.setPower(0);
            rightMotor.setPower(0);
        } else if (degrees < 0) {
            //moves the motors
            leftMotor.setPower(-1);
            rightMotor.setPower(1);
            while (degrees == 0) {
                //in here there needs to be an updater for the number of degrees the robot has turned.
                xVal = DegreesTurned.rawX();
                zVal = DegreesTurned.rawZ();
            }
            //stops the motors
            leftMotor.setPower(0);
            rightMotor.setPower(0);
        } else {
            //the code here should auto correct itself.
            leftMotor.setPower(1);
            rightMotor.setPower(1);
            xVal = DegreesTurned.rawX();
            zVal = DegreesTurned.rawZ();
            if (zVal < 0) {
                leftMotor.setPower(1);
                rightMotor.setPower(.5);
            }
            if (zVal > 0) {
                leftMotor.setPower(.5);
                rightMotor.setPower(1);
            }
        }
    }
}
