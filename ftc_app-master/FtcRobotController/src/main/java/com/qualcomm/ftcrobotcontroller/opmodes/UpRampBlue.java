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

public class UpRampBlue extends LinearOpMode {
    DcMotor leftDriveMotor;
    DcMotor rightDriveMotor;
    GMRServo leftFlapperServo;
    GMRServo rightFlapperServo;
    GMRServo climberDepositerServo;
    GMRServo winchServo;
    GMRServo hopperDoorBlue;
    GMRServo hopperDoorRed;
    GMRServo hopperEntranceDoor;
    GMRServo sweeperLift;
    GMRServo sweeperHold;
    GMRServo colorServo;
    Servo servo1;
    Servo servo2;
    Servo servo3;
    Servo servo4;
    Servo servo5;
    Servo servo6;
    Servo servo7;
    Servo servo8;
    Servo servo9;
    Servo servo10;
    Sleeper sleep;
    GyroSensor gyro;
    Telemetry t;
    ColorSensor argColorSensor;
    ColorSensorObject colorSensor;
    AnalogInput argUltrasonic;
    UltrasonicObject ultrasonic;
    OpticalDistanceSensor opticSensorMap;
    GMROpticDistanceSensor opticSensor;
    double colorServoPosition;
    double distance;
    @Override
    public void runOpMode() throws InterruptedException {
        t = telemetry;
        sleep = new Sleeper();
        leftDriveMotor = hardwareMap.dcMotor.get("leftDriveMotor");
        rightDriveMotor = hardwareMap.dcMotor.get("rightDriveMotor");
        gyro = hardwareMap.gyroSensor.get("gyro");
        argColorSensor = hardwareMap.colorSensor.get("color");
        colorSensor = new ColorSensorObject(argColorSensor, telemetry);
        argUltrasonic = hardwareMap.analogInput.get( "ultrasonic");
        ultrasonic = new UltrasonicObject(argUltrasonic, leftDriveMotor, rightDriveMotor);
        opticSensor = new GMROpticDistanceSensor(opticSensorMap = hardwareMap.opticalDistanceSensor.get("optic"));
        leftFlapperServo = new GMRServo(servo1 = hardwareMap.servo.get("leftFlapperServo"));
        rightFlapperServo = new GMRServo(servo2 = hardwareMap.servo.get("rightFlapperServo"));
        climberDepositerServo = new GMRServo(servo3 = hardwareMap.servo.get("climberDepositerServo"));
        winchServo = new GMRServo(servo4 = hardwareMap.servo.get("winchServo"));
        hopperDoorRed = new GMRServo(servo5 = hardwareMap.servo.get("hopperDoorRed"));
        hopperDoorBlue = new GMRServo(servo6 = hardwareMap.servo.get("hopperDoorBlue"));
        hopperEntranceDoor = new GMRServo(servo7 = hardwareMap.servo.get("hopperEntranceDoor"));
        sweeperLift = new GMRServo(servo8 = hardwareMap.servo.get("sweeperLift"));
        sweeperHold = new GMRServo(servo9 = hardwareMap.servo.get("sweeperHold"));
        colorServo = new GMRServo(servo10 = hardwareMap.servo.get("colorServo"));
        colorServoPosition = 0;

        MoveMotors move = new MoveMotors(colorSensor, leftDriveMotor, rightDriveMotor, ultrasonic, telemetry, gyro, opticSensor, hardwareMap);
        waitForStart();
        move.setServosAuto();
        leftDriveMotor.setDirection(DcMotor.Direction.FORWARD);
        rightDriveMotor.setDirection(DcMotor.Direction.REVERSE);
        move.moveForward(3500, 25);
        move.gyroRight(90);
        while(distance == .25) {
            distance = opticSensor.getDistance();
            leftDriveMotor.setPower(.15);
            rightDriveMotor.setPower(.15);
        }
        climberDepositerServo.moveServo(1);
        sleep.Sleep(1000);
        climberDepositerServo.moveServo(0);
        move.moveBackward(1000, 50);
        move.gyroRight(45);
        move.moveForward(3500, 50);
    }
}