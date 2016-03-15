package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftcrobotcontroller.opmodes.drivers.GMRServo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.robocol.Telemetry;

import java.util.Calendar;

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
    OpticalDistanceSensor opticSensorMap;
    GMROpticDistanceSensor opticSensor;

    boolean True = true;

    long endTime;

    Calendar rightNow;

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

        argColorSensor = hardwareMap.colorSensor.get("color");
        colorSensor = new ColorSensorObject(argColorSensor, telemetry);
        argUltrasonic = hardwareMap.analogInput.get( "ultrasonic");
        ultrasonic = new UltrasonicObject(argUltrasonic, leftDriveMotor, rightDriveMotor);

        opticSensor = new GMROpticDistanceSensor(opticSensorMap = hardwareMap.opticalDistanceSensor.get("optic"));

        MoveMotors move = new MoveMotors(colorSensor, leftDriveMotor, rightDriveMotor, ultrasonic, telemetry, gyro, opticSensor);

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

        rightNow = Calendar.getInstance();

        endTime = (rightNow.getTimeInMillis() + 30000);

        waitForStart();
        telemetry.addData("", "Stage 1");
        sleep.Sleep(50);
        //move.gyroLeft(51);
        //move.turnRight(4000,30);
        telemetry.addData("", "Stage 2");
        sleep.Sleep(50);
        while (colorSensor.getColor() == "gray" || rightNow.getTimeInMillis() < endTime) {
            move.moveForward(5, 12);
        }
        telemetry.addData("", "Stage 3");
        sleep.Sleep(1000);
        move.gyroLeft(33);
        sleep.Sleep(1000);
        while (opticSensor.getDistance() < 0.035 || rightNow.getTimeInMillis() < endTime) {
            leftDriveMotor.setPower(0.30);
            rightDriveMotor.setPower(0.30);
        }
        if (rightNow.getTimeInMillis() < endTime) {
            sleep.Sleep(50);
            climberDepositerPosition = 1;
            climberDepositerServo.moveServo(climberDepositerPosition);
        }
        leftDriveMotor.setPower(0);
        rightDriveMotor.setPower(0);
    }
}
