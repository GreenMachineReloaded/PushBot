package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftcrobotcontroller.opmodes.drivers.GMRServo;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Payton on 11/22/2015.
 */

/**
 * List of motor positions in the real world
 *
 * Drive Motor Controller:
 * leftDriveMotor 1
 * rightDriveMotor 2
 *
 *Lift Motor Controller:
 * flipBarMotor 2
 * liftMotor 1
 *
 * Servo Controller:
 * wheelBarServo
 */

public class CadwynTeleOp extends OpMode {

    DcMotor leftDriveMotor;
    DcMotor rightDriveMotor;

    DcMotor flipBarMotor;
    DcMotor liftMotor;

    GMRServo wheelBarServo;

    Servo servo1;

    @Override
    public void init() {

        flipBarMotor = hardwareMap.dcMotor.get("flipBarMotor");
        liftMotor = hardwareMap.dcMotor.get("liftMotor");

        leftDriveMotor = hardwareMap.dcMotor.get("leftDriveMotor");
        rightDriveMotor = hardwareMap.dcMotor.get("rightDriveMotor");
        leftDriveMotor.setDirection(DcMotor.Direction.REVERSE);

        //servo1 = hardwareMap.servo.get("wheelBarServo");
        //wheelBarServo = new GMRServo(servo1);

    }

    @Override
    public void loop() {

        float moveLeft = -gamepad1.left_stick_y;
        float moveRight = -gamepad1.right_stick_y;

        leftDriveMotor.setPower(moveLeft);
        rightDriveMotor.setPower(moveRight);

//        if (gamepad2.right_bumper) {
//            flipBarMotor.setPower(0.5);
//        }
//
//        if (gamepad2.left_bumper) {
//            flipBarMotor.setPower(-0.5);
//        }

//        telemetry.addData("Text", "*** Robot Data***");
//        telemetry.addData("arm", "gamepadleft:  " + String.format("%.2f", moveLeft));
//        telemetry.addData("arm", "gamepadright:  " + String.format("%.2f", moveRight));

//        if (gamepad1.a) {
//            wheelBarServo.moveServo(0);
//        }
//
//        if (gamepad1.y) {
//            wheelBarServo.moveServo(1);
//        }

    }
}
