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
 * flapperRed
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
    DcMotor armMotor;
    DcMotor winchMotor;
    DcMotor sweeperMotor;

    GMRServo leftFlapperServo;
    GMRServo rightFlapperServo;
    GMRServo climberDepositerServo;
    GMRServo winchServo;

    Servo servo1;
    Servo servo2;
    Servo servo3;
    Servo servo4;

    //float wheelBarPosition;

    float flapperRightPosition;
    float flapperLeftPosition;
    float climberDepositerPosition;
    float winchServoPosition;

    float multiplier;

    @Override
    public void init() {

        armMotor = hardwareMap.dcMotor.get("armMotor");

        leftDriveMotor = hardwareMap.dcMotor.get("leftDriveMotor");
        rightDriveMotor = hardwareMap.dcMotor.get("rightDriveMotor");

        leftDriveMotor.setDirection(DcMotor.Direction.FORWARD);
        rightDriveMotor.setDirection(DcMotor.Direction.REVERSE);

        winchMotor = hardwareMap.dcMotor.get("winchMotor");

        sweeperMotor = hardwareMap.dcMotor.get("sweeperMotor");


        servo1 = hardwareMap.servo.get("flapperLeft");
        leftFlapperServo = new GMRServo(servo1);

        servo2 = hardwareMap.servo.get("flapperRight");
        rightFlapperServo = new GMRServo(servo2);

        servo3 = hardwareMap.servo.get("climberDepositerServo");
        climberDepositerServo = new GMRServo(servo3);

        servo4 = hardwareMap.servo.get("winchServo");
        winchServo = new GMRServo(servo4);

        flapperRightPosition = (float) 0;
        flapperLeftPosition = (float) 0;
        climberDepositerPosition = (float) 0;
        winchServoPosition = (float) 0;

        multiplier = 1;

    }

    @Override
    public void loop() {

        float moveLeft = -gamepad1.left_stick_y;
        float moveRight = -gamepad1.right_stick_y;

        leftDriveMotor.setPower(moveLeft);
        rightDriveMotor.setPower(moveRight);



        if (gamepad1.right_bumper) {
            sweeperMotor.setPower(0.5);
        } else if (gamepad1.right_trigger > 0) {
            sweeperMotor.setPower(-0.5);
        } else {
            sweeperMotor.setPower(0);
        }

        if (gamepad1.left_bumper) {
            winchServoPosition += 0.001;
        }

        if (gamepad1.left_trigger > 0) {
            winchServoPosition -= 0.001;
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
            winchMotor.setPower(0.5);
        }else if (gamepad1.dpad_up) {
            winchMotor.setPower(-0.5);
        }else {
            winchMotor.setPower(0);
        }

        if (gamepad1.y) {
            winchServoPosition + 0.005;
        }
        if (gamepad1.a) {
            winchServoPosition - 0.005;
        }

        if (gamepad2.right_bumper) {
            armMotor.setPower(-1);
        } else if (gamepad2.left_bumper){
            armMotor.setPower(1);
        } else {
            armMotor.setPower(0);
        }
        if (gamepad2.left_trigger > 0) {
            liftAimPosition -= 0.005;
        } else if (gamepad2.right_trigger > 0) {
            liftAimPosition += 0.005;
        }

        if (gamepad1.b) {
            leftDriveMotor.setPower(-0.5);
            rightDriveMotor.setPower(-0.5);
        }
//        if (gamepad1.b) {
//            leftDriveMotor.setPower(0.5);
//            rightDriveMotor.setPower(-0.5);
//        }

        flapperRightPosition = (float) Range.clip(flapperRedPosition, 0, 1);
        flapperRed.moveServo(flapperRedPosition);

        flapperLeftPosition = (float) Range.clip(flapperBluePosition, 0, 1);
        flapperBlue.moveServo(flapperBluePosition);

        climberDepositerPosition = (float) Range.clip(dumpClimbersServoPosition, 0, 01);
        dumpClimbersServo.moveServo(dumpClimbersServoPosition);

        winchServoPosition = (float) Range.clip(winchServoPosition, 0, 1);
        winchServo.moveServo(winchServoPosition);

        String servoPositions = String.format("%.2f",winchServoPosition);
        telemetry.addData("", "Winch Position:  " + servoPositions);

    }
}


