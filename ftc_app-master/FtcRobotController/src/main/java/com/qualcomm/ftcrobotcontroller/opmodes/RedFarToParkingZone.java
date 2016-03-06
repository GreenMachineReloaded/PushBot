package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftcrobotcontroller.opmodes.drivers.GMRServo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.robocol.Telemetry;

/**
 * Created by Payton on 2/16/2016.
 */
public class RedFarToParkingZone extends LinearOpMode {
    DcMotor leftDriveMotor;
    DcMotor rightDriveMotor;

    Sleeper sleep;
    Sleeper s;


    GyroSensor gyro;

    GMRServo leftFlapperServo;
    GMRServo rightFlapperServo;
    GMRServo climberDepositerServo;
    GMRServo winchServo;
    GMRServo hopperDoorBlue;
    GMRServo hopperDoorRed;
    GMRServo hopperEntranceDoor;
    GMRServo sweeperLift;

    Servo servo1;
    Servo servo2;
    Servo servo3;
    Servo servo4;
    Servo servo5;
    Servo servo6;
    Servo servo7;
    Servo servo8;

    double flapperRightPosition;
    double flapperLeftPosition;
    double climberDepositerPosition;
    double winchServoPosition;
    double hopperDoorleftPosition;
    double hopperDoorRightPosition;
    double hopperEntranceDoorPosition;
    double sweeperLiftPosition;

    Telemetry t;

    ColorSensor cs;

    AnalogInput us;

    ColorSensorObject colorSensor;
    ColorSensor argColorSensor;
    AnalogInput argUltrasonic;
    UltrasonicObject ultrasonic ;
    FollowLine followLine;

    @Override
    public void runOpMode() throws InterruptedException {

        t = telemetry;

        s = new Sleeper();
        sleep = new Sleeper();

        leftDriveMotor = hardwareMap.dcMotor.get("leftDriveMotor");
        rightDriveMotor = hardwareMap.dcMotor.get("rightDriveMotor");

        gyro = hardwareMap.gyroSensor.get("gyro");

        colorSensor = new ColorSensorObject(cs, telemetry);

        us = hardwareMap.analogInput.get( "ultrasonic");
        ultrasonic = new UltrasonicObject(us, leftDriveMotor, rightDriveMotor);

        followLine = new FollowLine (colorSensor, rightDriveMotor, leftDriveMotor, ultrasonic, t);

        GyroObject gyroTurn = new GyroObject(leftDriveMotor, rightDriveMotor, gyro, telemetry);

        MoveMotors move = new MoveMotors(leftDriveMotor, rightDriveMotor);

        leftFlapperServo = new GMRServo(servo1 = hardwareMap.servo.get("leftFlapperServo"));
        rightFlapperServo = new GMRServo(servo2 = hardwareMap.servo.get("rightFlapperServo"));
        climberDepositerServo = new GMRServo(servo3 = hardwareMap.servo.get("climberDepositerServo"));
        winchServo = new GMRServo(servo4 = hardwareMap.servo.get("winchServo"));
        hopperDoorRed = new GMRServo(servo5 = hardwareMap.servo.get("hopperDoorRed"));
        hopperDoorBlue = new GMRServo(servo6 = hardwareMap.servo.get("hopperDoorBlue"));
        hopperEntranceDoor = new GMRServo(servo7 = hardwareMap.servo.get("hopperEntranceDoor"));
        sweeperLift = new GMRServo(servo8 = hardwareMap.servo.get("sweeperLift"));

        flapperRightPosition = 1;
        flapperLeftPosition = 0;
        climberDepositerPosition = 0;
        winchServoPosition = 1;
        hopperDoorleftPosition = 0.64;
        hopperDoorRightPosition = 0.03;
        hopperEntranceDoorPosition = 0.7;
        sweeperLiftPosition = 1;

        rightFlapperServo.moveServo(flapperRightPosition);
        leftFlapperServo.moveServo(flapperLeftPosition);
        climberDepositerServo.moveServo(climberDepositerPosition);
        winchServo.moveServo(winchServoPosition);
        hopperDoorRed.moveServo(hopperDoorleftPosition);
        hopperDoorBlue.moveServo(hopperDoorRightPosition);
        hopperEntranceDoor.moveServo(hopperEntranceDoorPosition);
        sweeperLift.moveServo(sweeperLiftPosition);

        argColorSensor = hardwareMap.colorSensor.get("color");
        colorSensor = new ColorSensorObject(argColorSensor, telemetry);
        argUltrasonic = hardwareMap.analogInput.get( "ultrasonic");
        ultrasonic = new UltrasonicObject(argUltrasonic, leftDriveMotor, rightDriveMotor);
        followLine = new FollowLine (colorSensor, rightDriveMotor, leftDriveMotor, ultrasonic, telemetry);

        waitForStart();

        move.moveForward(250, 25);

        sleep.Sleep(50);

        move.moveForward(500, 40);

        sleep.Sleep(50);

    }
}
