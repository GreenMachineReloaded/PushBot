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
    DcMotor leftMotor;
    DcMotor rightMotor;
    GyroSensor gyroarg;
    Sleeper s;
    @Override
    public void runOpMode() throws InterruptedException {
        leftMotor = hardwareMap.dcMotor.get("left_drive");
        rightMotor = hardwareMap.dcMotor.get("right_drive");
        gyroarg = hardwareMap.gyroSensor.get("gyro");
        MoveMotorsObject move = new MoveMotorsObject(leftMotor, rightMotor);
        GyroObject gyro = new GyroObject(leftMotor, rightMotor, gyroarg);
        waitForStart();
        move.moveForward(4000);
        gyro.turnGyro(45);
    }
}
