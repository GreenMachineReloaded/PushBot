package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftcrobotcontroller.opmodes.drivers.GMRMotor;
import com.qualcomm.ftcrobotcontroller.opmodes.drivers.GMRServo;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;


 //Created by Payton on 11/22/2015.


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
    DcMotor dcArmMotor;
    DcMotor winchMotor;
    DcMotor sweeperMotor;

    GMRMotor armMotor;

    GMRServo leftFlapperServo;
    GMRServo rightFlapperServo;
    GMRServo climberDepositerServo;
    GMRServo winchServo;
    GMRServo hopperDoorBlue;
    GMRServo hopperDoorRed;

    Servo servo1;
    Servo servo2;
    Servo servo3;
    Servo servo4;
    Servo servo5;
    Servo servo6;

    double flapperRightPosition;
    double flapperLeftPosition;
    double climberDepositerPosition;
    double winchServoPosition;
    double hopperDoorleftRedPosition;
    double hopperDoorRightBluePosition;

    double multiplier;

    int armMotorPosition;
    int armMotorPosition2;

    int getArmMotorPosition;

    Sleeper sleep;

    @Override
    public void init() {

        dcArmMotor = hardwareMap.dcMotor.get("armMotor");
        armMotor = new GMRMotor(dcArmMotor, telemetry);

        leftDriveMotor = hardwareMap.dcMotor.get("leftDriveMotor");
        rightDriveMotor = hardwareMap.dcMotor.get("rightDriveMotor");

        leftDriveMotor.setDirection(DcMotor.Direction.FORWARD);
        rightDriveMotor.setDirection(DcMotor.Direction.REVERSE);

        winchMotor = hardwareMap.dcMotor.get("winchMotor");

        sweeperMotor = hardwareMap.dcMotor.get("sweeperMotor");

        leftFlapperServo = new GMRServo(servo1 = hardwareMap.servo.get("leftFlapperServo"));
        rightFlapperServo = new GMRServo(servo2 = hardwareMap.servo.get("rightFlapperServo"));
        climberDepositerServo = new GMRServo(servo3 = hardwareMap.servo.get("climberDepositerServo"));
        winchServo = new GMRServo(servo4 = hardwareMap.servo.get("winchServo"));
        hopperDoorRed = new GMRServo(servo5 = hardwareMap.servo.get("hopperDoorRed"));
        hopperDoorBlue = new GMRServo(servo6 = hardwareMap.servo.get("hopperDoorBlue"));

        flapperRightPosition =  1;
        flapperLeftPosition =  0;
        climberDepositerPosition =  0;
        winchServoPosition =  0.21;
        hopperDoorleftRedPosition = 0.6;
        hopperDoorRightBluePosition = 0;

        multiplier = 1;

        sleep = new Sleeper();

        armMotor.motorHandle.setMode(DcMotorController.RunMode.RESET_ENCODERS);

        sleep.Sleep(50);

        armMotor.motorHandle.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);

        getArmMotorPosition = 0;

    }

    @Override
    public void loop() {

        armMotorPosition = armMotor.motorHandle.getCurrentPosition();

        leftDriveMotor.setPower(gamepad1.left_stick_y);
        rightDriveMotor.setPower(gamepad1.right_stick_y);

        if (armMotor.motorHandle.getCurrentPosition() > 625) {
            multiplier = 0.5;
        }else if (armMotor.motorHandle.getCurrentPosition() < 625) {
            multiplier = 1;
        }

        if (gamepad1.x && getArmMotorPosition == 0) {
            getArmMotorPosition = 1;
            armMotorPosition2 = armMotor.motorHandle.getCurrentPosition();
        }else if (gamepad1.x && getArmMotorPosition == 1) {
            Double armMotorPower = armMotor.holdMotor(armMotorPosition2);
            armMotor.motorHandle.setPower(armMotorPower);
        } else if (!gamepad1.x) {
            getArmMotorPosition = 0;
            if (gamepad1.left_bumper) {
                armMotor.motorHandle.setPower(-0.2);
            } else if (gamepad1.left_trigger > 0){
                armMotor.motorHandle.setPower(0.2);
            }else if ( !gamepad1.left_bumper && !(gamepad1.left_trigger > 0) && (getArmMotorPosition == 0)) {
                armMotor.motorHandle.setPower(0);
            }
        }
        String armPosition = String.valueOf(armMotor.motorHandle.getCurrentPosition());
        telemetry.addData("", "" + "it made it this far: " + armPosition);

        if (gamepad1.right_bumper) {
            sweeperMotor.setPower(-1);
        } else if (gamepad1.right_trigger > 0) {
            sweeperMotor.setPower(1);
        } else {
            sweeperMotor.setPower(0);
        }

        if (gamepad1.b) {
            leftDriveMotor.setPower(-0.5);
            rightDriveMotor.setPower(-0.5);
        }

        if (gamepad1.dpad_down) {
            winchMotor.setPower(-0.5);
        }else if (gamepad1.dpad_up) {
            winchMotor.setPower( 0.5);
        }else {
            winchMotor.setPower(0);
        }

        if (gamepad1.a) {
            winchServoPosition += 0.001;
        }else if (gamepad1.y) {
            winchServoPosition -= 0.001;
        }



        if (gamepad2.right_stick_y > 0) {
            flapperRightPosition += 0.01;
        }else if (gamepad2.right_stick_y < 0) {
            flapperRightPosition -= 0.01;
        }

        if (gamepad2.left_stick_y > 0) {
            flapperLeftPosition -= 0.01;
        }else if (gamepad2.left_stick_y < 0) {
            flapperLeftPosition += 0.01;
        }

        if (gamepad2.y) {
            climberDepositerPosition += 0.05;
        }else if (gamepad2.a) {
            climberDepositerPosition -= 0.05;
        }

        if (gamepad2.left_bumper) {
            hopperDoorleftRedPosition += 0.005;
        }else if (gamepad2.left_trigger > 0) {
            hopperDoorleftRedPosition -= 0.005;
        }

        if (gamepad2.right_bumper) {
            hopperDoorRightBluePosition -= 0.005;
        }else if (gamepad2.right_trigger > 0) {
            hopperDoorRightBluePosition += 0.005;
        }


        flapperRightPosition =  Range.clip(flapperRightPosition, 0, 1);
        rightFlapperServo.moveServo(flapperRightPosition);

        flapperLeftPosition =  Range.clip(flapperLeftPosition, 0.8, 1);
        leftFlapperServo.moveServo(flapperLeftPosition);

        climberDepositerPosition =  Range.clip(climberDepositerPosition, 0, 1);
        climberDepositerServo.moveServo(climberDepositerPosition);

        winchServoPosition =  Range.clip(winchServoPosition, 0, 0.21);
        winchServo.moveServo(winchServoPosition);

        hopperDoorleftRedPosition =  Range.clip(hopperDoorleftRedPosition, 0.14, 0.6);
        hopperDoorRed.moveServo(hopperDoorleftRedPosition);

        hopperDoorRightBluePosition =  Range.clip(hopperDoorRightBluePosition, 0.03, 0.6);
        hopperDoorBlue.moveServo(hopperDoorRightBluePosition);

        //String servoPositions = String.valueOf(armMotorPosition2);

        //String servoPositions = String.format("%.2f", rightFlapperServo);
        String servoPositions = String.valueOf(hopperDoorRightBluePosition);
        telemetry.addData("", "current multiplier: " + servoPositions);

    }
}