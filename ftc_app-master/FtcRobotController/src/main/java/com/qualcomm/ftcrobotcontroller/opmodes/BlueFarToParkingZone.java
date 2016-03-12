package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftcrobotcontroller.opmodes.drivers.GMRServo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.robocol.Telemetry;

public class BlueFarToParkingZone extends LinearOpMode {
    DcMotor leftDriveMotor;
    DcMotor rightDriveMotor;

    GMRGyro gyroTurn;
    MoveMotors move;
    Sleeper sleep;

    GyroSensor gyro;

    Telemetry t;


    GMRColorSensor colorSensor;

    GMRUltrasonic ultrasonic;


    @Override
    public void runOpMode() throws InterruptedException {

        t = telemetry;

        sleep = new Sleeper();

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
//
//        flapperRightPosition = 1;
//        flapperLeftPosition = 0;
//        climberDepositerPosition = 0;
//        winchServoPosition = 1;
//        hopperDoorleftPosition = 0.64;
//        hopperDoorRightPosition = 0.03;
//
//        flapperRightPosition = Range.clip(flapperRightPosition, 0, 1);
//        rightFlapperServo.moveServo(flapperRightPosition);
//
//        flapperLeftPosition = Range.clip(flapperLeftPosition, 0, 1);
//        leftFlapperServo.moveServo(flapperLeftPosition);
//
//        climberDepositerPosition = Range.clip(climberDepositerPosition, 0, 1);
//        climberDepositerServo.moveServo(climberDepositerPosition);
//
//        winchServoPosition = Range.clip(winchServoPosition, 0.8, 1);
//        winchServo.moveServo(winchServoPosition);
//
//        hopperDoorleftPosition = Range.clip(hopperDoorleftPosition, 0.064, 0.64);
//        hopperDoorRed.moveServo(hopperDoorleftPosition);
//
//        hopperDoorRightPosition = Range.clip(hopperDoorRightPosition, 0.03, 0.6);
//        hopperDoorBlue.moveServo(hopperDoorRightPosition);

        gyroTurn = new GMRGyro(leftDriveMotor, rightDriveMotor, gyro, telemetry);
        move = new MoveMotors(colorSensor, leftDriveMotor, rightDriveMotor, ultrasonic, telemetry, gyro);
        waitForStart();

        sleep.Sleep(10000);


        gyro = hardwareMap.gyroSensor.get("gyro");
        //GyroObject gyroTurn = new GyroObject(leftDriveMotor, rightDriveMotor, gyro, telemetry);

        waitForStart();

        sleep.Sleep(10000);

        move.moveForward(1000, 40);
        move.moveBackward(1000, 40);
        move.gyroRight(45);
        move.gyroLeft(45);
        move.traceALine();
    }
}
