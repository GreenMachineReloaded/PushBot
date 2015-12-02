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
    Sleeper s;
    @Override
    public void runOpMode() throws InterruptedException {
        leftMotorArg = hardwareMap.dcMotor.get("leftDriveMotor");
        rightMotorArg = hardwareMap.dcMotor.get("rightDriveMotor");
        s = new Sleeper();
        MoveMotorsObject move = new MoveMotorsObject(leftMotorArg, rightMotorArg);
        waitForStart();
        move.moveForward(3500);
        move.turnRight(500);
        move.moveForward(1000);

    }
}
