package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftcrobotcontroller.opmodes.drivers.GMRServo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by Amber on 1/29/2016. waffle
 */
public class RedToParkingZone extends LinearOpMode {
    DcMotor leftDriveMotor;
    DcMotor rightDriveMotor;
    Sleeper sleep;
    GyroSensor gyro;
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
    @Override
    public void runOpMode() throws InterruptedException {
        leftDriveMotor = hardwareMap.dcMotor.get("leftDriveMotor");
        rightDriveMotor = hardwareMap.dcMotor.get("rightDriveMotor");
        gyro = hardwareMap.gyroSensor.get("gyro");
        sleep = new Sleeper();

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
        hopperDoorRightBluePosition = 0.03;

        flapperRightPosition =  Range.clip(flapperRightPosition, 0, 1);
        rightFlapperServo.moveServo(flapperRightPosition);

        flapperLeftPosition =  Range.clip(flapperLeftPosition, 0, 1);
        leftFlapperServo.moveServo(flapperLeftPosition);

        climberDepositerPosition =  Range.clip(climberDepositerPosition, 0, 1);
        climberDepositerServo.moveServo(climberDepositerPosition);

        winchServoPosition =  Range.clip(winchServoPosition, 0, 0.21);
        winchServo.moveServo(winchServoPosition);

        hopperDoorleftRedPosition =  Range.clip(hopperDoorleftRedPosition, 0.14, 0.6);
        hopperDoorRed.moveServo(hopperDoorleftRedPosition);

        hopperDoorRightBluePosition =  Range.clip(hopperDoorRightBluePosition, 0.03, 0.6);
        hopperDoorBlue.moveServo(hopperDoorRightBluePosition);

        GyroObject gyroTurn = new GyroObject(leftDriveMotor, rightDriveMotor,gyro,telemetry);
        MoveMotors move = new MoveMotors(leftDriveMotor, rightDriveMotor);

        waitForStart();

        //move.moveForward(500, 40);

        //gyroTurn.leftTurn(45);

        //move.moveForward(3000, 50);

        //gyroTurn.leftTurn(45);

        //move.moveForward(500, 40);

    }
}
