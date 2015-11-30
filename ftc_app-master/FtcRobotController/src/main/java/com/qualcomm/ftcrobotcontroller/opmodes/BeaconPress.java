package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.GyroSensor;

/*
 * An example linear op mode where the pushbot
 * will drive in a square pattern using sleep()
 * and a for loop.
 */
public class BeaconPress extends LinearOpMode {
    DcMotor leftMotorArg;
    DcMotor rightMotorArg;
    GyroSensor gyroArg;
    Sleeper s;
    @Override
    public void runOpMode() throws InterruptedException {
        leftMotorArg = hardwareMap.dcMotor.get("leftDriveMotor");
        rightMotorArg = hardwareMap.dcMotor.get("rightDriveMotor");
        gyroArg = hardwareMap.gyroSensor.get("gyro");
        s = new Sleeper();
        MoveMotorsObject move = new MoveMotorsObject(leftMotorArg, rightMotorArg);
        GyroObject gyro = new GyroObject(leftMotorArg, rightMotorArg, gyroArg);
        waitForStart();
        move.moveForward(4000);
        gyro.turnGyro(45);
    }
}
