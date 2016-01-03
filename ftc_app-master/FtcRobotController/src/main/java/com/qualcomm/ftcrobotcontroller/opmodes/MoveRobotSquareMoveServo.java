package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftcrobotcontroller.opmodes.drivers.GMRServo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class MoveRobotSquareMoveServo extends LinearOpMode {
    DcMotor leftMotor;
    DcMotor rightMotor;
    Servo servo;
    @Override
    public void runOpMode() throws InterruptedException {
        leftMotor = hardwareMap.dcMotor.get("leftDriveMotor");
        rightMotor = hardwareMap.dcMotor.get("rightDriveMotor");
        servo = hardwareMap.servo.get("wheelBarServo");
        MoveMotorsObject move = new MoveMotorsObject(leftMotor, rightMotor);
        GMRServo servoObject = new GMRServo(servo);
        Sleeper s = new Sleeper();
        waitForStart();

        move.moveForward(250, 100);
        servoObject.moveServo(270);
        s.Sleep(1000);
        servoObject.moveServo(90);
        s.Sleep(1000);
        move.turnRight(250, 100);
        move.moveForward(250, 100);
        servoObject.moveServo(270);
        s.Sleep(1000);
        servoObject.moveServo(90);
        s.Sleep(1000);
        move.turnRight(250, 100);
        move.moveForward(250, 100);
        servoObject.moveServo(270);
        s.Sleep(1000);
        servoObject.moveServo(90);
        s.Sleep(1000);
        move.turnRight(250, 100);
    }
}
