package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftcrobotcontroller.opmodes.drivers.GMRServo;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by Payton on 11/22/2015.
 */

/**
 * List of motor positions in the real world
 *
 * Drive Motor Controller  (1):
 * leftDriveMotor (1)
 * rightDriveMotor (2)
 *
 *Lift Motor Controller  (2):
 * flipBarMotor (1)
 * liftMotor (2)
 *
 * Servo Controller:
 * wheelBarServo
 * flappperRight
 * flapperLeft
 *
 *
 *
 *
 * Motors:
 * Minus = Counter Clockwise
 * Plus = Clockwise
 */

public class CadwynTeleOp extends OpMode {

    DcMotor leftDriveMotor;
    DcMotor rightDriveMotor;
    DcMotor flipBarMotor;
    DcMotor liftMotor;
    DcMotor winchMotor;

    //GMRServo wheelBarServo;

    GMRServo flapperRight;
    GMRServo flapperLeft;
    GMRServo dumpClimbersServo;
    GMRServo winchServo;

    Servo servo1;
    Servo servo2;
    Servo servo3;
    Servo servo4;

    //float wheelBarPosition;

    float flapperRightPosition;
    float flapperLeftPosition;
    float dumpClimbersServoPosition;
    float winchServoPosition;

    float multiplier;

    @Override
    public void init() {

        flipBarMotor = hardwareMap.dcMotor.get("flipBarMotor");

        liftMotor = hardwareMap.dcMotor.get("liftMotor");

        leftDriveMotor = hardwareMap.dcMotor.get("leftDriveMotor");

        rightDriveMotor = hardwareMap.dcMotor.get("rightDriveMotor");

        leftDriveMotor.setDirection(DcMotor.Direction.REVERSE);

        winchMotor = hardwareMap.dcMotor.get("winchMotor");


        servo1 = hardwareMap.servo.get("flapperLeft");
        flapperRight = new GMRServo(servo1);

        servo2 = hardwareMap.servo.get("flapperRight");
        flapperLeft = new GMRServo(servo2);

        servo3 = hardwareMap.servo.get("dumpClimbersServo");
        dumpClimbersServo = new GMRServo(servo3);

        servo4 = hardwareMap.servo.get("winchServo");
        winchServo = new GMRServo(servo4);


        flapperRightPosition = (float) 0.45;
        flapperLeftPosition = (float) 0.8;
        dumpClimbersServoPosition = (float) 0.5;
        winchServoPosition = (float) 0.96;

    }

    @Override
    public void loop() {

        float moveLeft = -gamepad1.left_stick_y;
        float moveRight = -gamepad1.right_stick_y;

        leftDriveMotor.setPower(moveLeft);
        rightDriveMotor.setPower(moveRight);



        if (gamepad1.right_bumper) {
            winchMotor.setPower(0.5);
        } else if (gamepad1.right_trigger > 0) {
            winchMotor.setPower(-0.5);
        } else {
            winchMotor.setPower(0);
        }

        if (gamepad1.left_bumper) {
            winchServoPosition += 0.01;
        }

        if (gamepad1.left_trigger > 0) {
            winchServoPosition -= 0.01;
        }



        if (gamepad2.left_stick_y > 0) {
            flapperRightPosition += 0.01;
        }

        if (gamepad2.left_stick_y < 0) {
            flapperRightPosition -= 0.01;
        }

        if (gamepad2.right_stick_y > 0) {
            flapperLeftPosition -= 0.01;
        }

        if (gamepad2.right_stick_y < 0) {
            flapperLeftPosition += 0.01;
        }


        if (gamepad1.dpad_down) {
            dumpClimbersServoPosition += 0.01;
        }
        if (gamepad1.dpad_up) {
            dumpClimbersServoPosition -= 0.01;
        }


        boolean rightBumperPressed = false;

        if (gamepad2.right_bumper) {
            flipBarMotor.setPower(0.25);
            rightBumperPressed = true;
        } else if (gamepad2.left_bumper){
            flipBarMotor.setPower(-0.25);
        } else {
            flipBarMotor.setPower(0);
        }
        if (gamepad2.left_trigger > 0) {
            liftMotor.setPower(-0.25);
        } else if (gamepad2.right_trigger > 0) {
            liftMotor.setPower(0.25);
        } else {
            liftMotor.setPower(0);
        }




        flapperLeftPosition = (float) Range.clip(flapperLeftPosition, 0.45, 0.8);
        flapperLeft.moveServo(flapperLeftPosition);

        flapperRightPosition = (float) Range.clip(flapperRightPosition, 0.45, 0.8);
        flapperRight.moveServo(flapperRightPosition);

        dumpClimbersServoPosition = (float) Range.clip(dumpClimbersServoPosition, 0.3, 0.96);
        dumpClimbersServo.moveServo(dumpClimbersServoPosition);

        winchServoPosition = Range.clip(winchServoPosition, 0, 1);
        winchServo.moveServo(winchServoPosition);

        String servoPositions = String.format("%.2f",flapperRightPosition);

        telemetry.addData("", "Servo Positions:  " + servoPositions);

    }
}