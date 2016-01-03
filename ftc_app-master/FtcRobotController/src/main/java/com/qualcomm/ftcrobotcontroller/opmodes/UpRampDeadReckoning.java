package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftcrobotcontroller.opmodes.drivers.GMRServo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class UpRampDeadReckoning extends LinearOpMode {
    DcMotor leftMotor;
    DcMotor rightMotor;
    Servo servo;
    @Override
    public void runOpMode() throws InterruptedException {
        leftMotor = hardwareMap.dcMotor.get("leftdriveMotor");
        rightMotor = hardwareMap.dcMotor.get("rightdriveMotor");
        servo = hardwareMap.servo.get("servo");
        MoveMotorsObject move = new MoveMotorsObject(leftMotor, rightMotor);
        GMRServo Servo = new GMRServo(servo);
        waitForStart();

        move.moveForward(2500);

        move.turnRight(1000);
        Servo.moveServo(0);
        move.moveForward(2500);
    }
}
