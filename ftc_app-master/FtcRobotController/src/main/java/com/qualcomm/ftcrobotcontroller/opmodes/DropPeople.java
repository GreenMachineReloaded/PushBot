package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftcrobotcontroller.opmodes.drivers.GMRServo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.Servo;

/*
 * An example linear op mode where the pushbot
 * will drive in a square pattern using sleep()
 * and a for loop.
 */
public class DropPeople extends LinearOpMode {
    DcMotor leftMotorArg;
    DcMotor rightMotorArg;
    Sleeper s;
    Servo servoArg;
    @Override
    public void runOpMode() throws InterruptedException {
        leftMotorArg = hardwareMap.dcMotor.get("leftDriveMotor");
        rightMotorArg = hardwareMap.dcMotor.get("rightDriveMotor");
        servoArg = hardwareMap.servo.get("rightDriveMotor");
        s = new Sleeper();
        MoveMotorsObject move = new MoveMotorsObject(leftMotorArg, rightMotorArg);
        GMRServo servo = new GMRServo(servoArg);
        waitForStart();
        move.moveForward(3600);
        move.turnRight(500);
        move.moveForward(1300);
        move.turnLeft(1000);
        servo.moveServo(135);
        s.Sleep(1000);
        servo.moveServo360(5);
    }
}
