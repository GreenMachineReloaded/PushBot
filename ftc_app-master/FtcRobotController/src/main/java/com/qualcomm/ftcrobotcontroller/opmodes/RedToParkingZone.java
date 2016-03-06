package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.robocol.Telemetry;

/**
 * Created by Amber on 1/29/2016. waffle
 */
public class RedToParkingZone extends LinearOpMode {
    DcMotor leftDriveMotor;
    DcMotor rightDriveMotor;

    Sleeper sleep;

    GyroSensor gyro;

//    GMRServo leftFlapperServo;
//    GMRServo rightFlapperServo;
//    GMRServo climberDepositerServo;
//    GMRServo winchServo;
//    GMRServo hopperDoorBlue;
//    GMRServo hopperDoorRed;

//    Servo servo1;
//    Servo servo2;
//    Servo servo3;
//    Servo servo4;
//    Servo servo5;
//    Servo servo6;

//    double flapperRightPosition;
//    double flapperLeftPosition;
//    double climberDepositerPosition;
//    double winchServoPosition;
//    double hopperDoorleftPosition;
//    double hopperDoorRightPosition;

    Telemetry t;

    Sleeper s;
    @Override
    public void runOpMode() throws InterruptedException {

        t = telemetry;

        s = new Sleeper();

        leftDriveMotor = hardwareMap.dcMotor.get("leftDriveMotor");
        rightDriveMotor = hardwareMap.dcMotor.get("rightDriveMotor");
        gyro = hardwareMap.gyroSensor.get("gyro");
        sleep = new Sleeper();

//        leftFlapperServo = new GMRServo(servo1 = hardwareMap.servo.get("leftFlapperServo"));
//        rightFlapperServo = new GMRServo(servo2 = hardwareMap.servo.get("rightFlapperServo"));
//        climberDepositerServo = new GMRServo(servo3 = hardwareMap.servo.get("climberDepositerServo"));
//        winchServo = new GMRServo(servo4 = hardwareMap.servo.get("winchServo"));
//        hopperDoorRed = new GMRServo(servo5 = hardwareMap.servo.get("hopperDoorRed"));
//        hopperDoorBlue = new GMRServo(servo6 = hardwareMap.servo.get("hopperDoorBlue"));

//        flapperRightPosition =  1;
//        flapperLeftPosition =  0;
//        climberDepositerPosition =  0;
//        winchServoPosition =  1;
//        hopperDoorleftPosition = 0.64;
//        hopperDoorRightPosition = 0.03;

//        flapperRightPosition =  Range.clip(flapperRightPosition, 0, 1);
//        rightFlapperServo.moveServo(flapperRightPosition);
//
//        flapperLeftPosition =  Range.clip(flapperLeftPosition, 0, 1);
//        leftFlapperServo.moveServo(flapperLeftPosition);
//
//        climberDepositerPosition =  Range.clip(climberDepositerPosition, 0, 1);
//        climberDepositerServo.moveServo(climberDepositerPosition);
//
//        winchServoPosition =  Range.clip(winchServoPosition, 0.8, 1);
//        winchServo.moveServo(winchServoPosition);
//
//        hopperDoorleftPosition =  Range.clip(hopperDoorleftPosition, 0.064, 0.64);
//        hopperDoorRed.moveServo(hopperDoorleftPosition);
//
//        hopperDoorRightPosition =  Range.clip(hopperDoorRightPosition, 0.03, 0.6);
//        hopperDoorBlue.moveServo(hopperDoorRightPosition);

        GyroObject gyroTurn = new GyroObject(leftDriveMotor, rightDriveMotor,gyro,telemetry);
        MoveMotors move = new MoveMotors(leftDriveMotor, rightDriveMotor);

        waitForStart();

        sleep.Sleep(10000);

        telemetry.addData("", "Program Start");

        move.moveForward(800, 50);

        s.Sleep(50);

        telemetry.addData("", "Forward 1 Complete");

        gyroTurn.leftTurn(45);

        s.Sleep(50);

        telemetry.addData("", "Left turn 1 complete");

        move.moveForward(3000, 50);

        s.Sleep(50);

        telemetry.addData("", "Foreward 1 Complete");

        gyroTurn.leftTurn(45);

        s.Sleep(50);

        telemetry.addData("", "Left turn 2 complete");

        move.moveForward(300, 40);

        telemetry.addData("", "Last forward complete, program end.");

//        move.moveForward(500, 30);
//        s.Sleep(1000);
//        gyroTurn.leftTurn(45);
//        s.Sleep(1000);
//        move.moveForward(500, 30);
//        s.Sleep(1000);
//        gyroTurn.leftTurn(45);
//        s.Sleep(1000);
//        move.moveForward(500, 30);
//        s.Sleep(1000);
//        gyroTurn.leftTurn(45);
//        s.Sleep(1000);
//        move.moveForward(500, 30);//Original Position
//        s.Sleep(1000);
//        move.moveForward(500, 30);
//        s.Sleep(1000);
//        gyroTurn.rightTurn(45);
//        s.Sleep(1000);
//        move.moveForward(500, 30);
//        s.Sleep(1000);
//        gyroTurn.rightTurn(45);
//        s.Sleep(1000);
//        move.moveForward(500, 30);
//        s.Sleep(1000);
//        gyroTurn.rightTurn(45);
//        s.Sleep(1000);
//        move.moveForward(500, 30);//Original Position

    }
}
