package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftcrobotcontroller.opmodes.drivers.GMRServo;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Payton on 11/22/2015.
 */
public class CadwynTeleOp extends OpMode {

    DcMotor leftDriveMotor;
    DcMotor rightDriveMotor;

    DcMotor flipBarMotor;
    DcMotor liftMotor;

    //GMRServo wheelBarServo;

    //Servo servo1;

    @Override
    public void init() {

        //flipBarMotor = hardwareMap.dcMotor.get("flipBarMotor");
        //liftMotor = hardwareMap.dcMotor.get("liftMotor");

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

        telemetry.addData("Text", "*** Robot Data***");
        telemetry.addData("arm", "gamepadleft:  " + String.format("%.2f", moveLeft));
        telemetry.addData("arm", "gamepadright:  " + String.format("%.2f", moveRight));

    }
}
