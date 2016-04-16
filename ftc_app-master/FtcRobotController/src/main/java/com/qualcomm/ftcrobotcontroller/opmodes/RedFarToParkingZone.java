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
    GMRServo sweeperHold;

    Servo servo1;
    Servo servo2;
    Servo servo3;
    Servo servo4;
    Servo servo5;
    Servo servo6;
    Servo servo7;
    Servo servo8;
    Servo servo9;

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

        MoveMotors move = new MoveMotors(colorSensor, leftDriveMotor, rightDriveMotor, ultrasonic, telemetry, gyro, opticSensor, hardwareMap);

        leftFlapperServo = new GMRServo(servo1 = hardwareMap.servo.get("leftFlapperServo"));
        rightFlapperServo = new GMRServo(servo2 = hardwareMap.servo.get("rightFlapperServo"));
        climberDepositerServo = new GMRServo(servo3 = hardwareMap.servo.get("climberDepositerBlueServo"));
        winchServo = new GMRServo(servo4 = hardwareMap.servo.get("winchServo"));
        hopperDoorRed = new GMRServo(servo5 = hardwareMap.servo.get("hopperDoorRed"));
        hopperDoorBlue = new GMRServo(servo6 = hardwareMap.servo.get("hopperDoorBlue"));
        hopperEntranceDoor = new GMRServo(servo7 = hardwareMap.servo.get("hopperEntranceDoor"));
        sweeperLift = new GMRServo(servo8 = hardwareMap.servo.get("sweeperLift"));
        sweeperHold = new GMRServo(servo9 = hardwareMap.servo.get("sweeperHold"));

        waitForStart();
        rightFlapperServo.moveServo(1);
        leftFlapperServo.moveServo(0);
        climberDepositerServo.moveServo(0);
        winchServo.moveServo(1);
        hopperDoorRed.moveServo(0.64);
        hopperDoorBlue.moveServo(0.03);
        hopperEntranceDoor.moveServo(0.7);
        sweeperLift.moveServo(1);
        sweeperHold.moveServo(0);
        sleep.Sleep(10000);
        telemetry.addData("", "Stage 1");
        sleep.Sleep(50);
        telemetry.addData("", "Stage 2");
        sleep.Sleep(50);
        while (colorSensor.getColor() == "gray" && opModeIsActive()) {
            leftDriveMotor.setDirection(DcMotor.Direction.FORWARD);
            rightDriveMotor.setDirection(DcMotor.Direction.REVERSE);
            leftDriveMotor.setPower(-0.2);
            rightDriveMotor.setPower(-0.2);
        }
        leftDriveMotor.setPower(0);
        rightDriveMotor.setPower(0);
        telemetry.addData("", "Stage 3");
        sleep.Sleep(1000);
        //move.turnLeft(700, 42);
        move.gyroLeft(32);
        sleep.Sleep(1000);
        while (opticSensor.getDistance() < 0.06 && opModeIsActive()) {
            leftDriveMotor.setPower(-0.40);
            rightDriveMotor.setPower(-0.5);
        }
        leftDriveMotor.setPower(0);
        rightDriveMotor.setPower(0);
        sleep.Sleep(50);
        if (opModeIsActive()) {
            climberDepositerServo.moveServo(1);
        }
    }
}
