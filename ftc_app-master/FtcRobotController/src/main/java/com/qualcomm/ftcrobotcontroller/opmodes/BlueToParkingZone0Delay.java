package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftcrobotcontroller.opmodes.drivers.GMRServo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.robocol.Telemetry;

public class BlueToParkingZone0Delay extends LinearOpMode {
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

    Servo servo1;
    Servo servo2;
    Servo servo3;
    Servo servo4;
    Servo servo5;
    Servo servo6;
    Servo servo7;
    Servo servo8;
    Servo servo9;

    Sleeper sleep;

    GyroSensor gyro;

    OpticalDistanceSensor opticSensorMap;
    GMROpticDistanceSensor opticSensor;

    Telemetry t;

    Sleeper s;

    ColorSensorObject colorSensor;

    UltrasonicObject ultrasonic;
    @Override
    public void runOpMode() throws InterruptedException {

        t = telemetry;

        s = new Sleeper();

        leftDriveMotor = hardwareMap.dcMotor.get("leftDriveMotor");
        rightDriveMotor = hardwareMap.dcMotor.get("rightDriveMotor");
        gyro = hardwareMap.gyroSensor.get("gyro");
        sleep = new Sleeper();
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

        GyroObject gyroTurn = new GyroObject(leftDriveMotor, rightDriveMotor,gyro,telemetry);
        MoveMotors move = new MoveMotors(colorSensor, leftDriveMotor, rightDriveMotor, ultrasonic, telemetry, gyro, opticSensor, hardwareMap);

        waitForStart();



        //sleep.Sleep(10000);
        rightFlapperServo.moveServo(1);
        leftFlapperServo.moveServo(0);
        climberDepositerServo.moveServo(0);
        winchServo.moveServo(1);
        hopperDoorRed.moveServo(0.64);
        hopperDoorBlue.moveServo(0.03);
        hopperEntranceDoor.moveServo(0.7);
        sweeperLift.moveServo(1);
        sweeperHold.moveServo(0);

        while (opticSensor.getDistance() < 0.03 && opModeIsActive()) {
            leftDriveMotor.setDirection(DcMotor.Direction.FORWARD);
            rightDriveMotor.setDirection(DcMotor.Direction.REVERSE);
            leftDriveMotor.setPower(-0.2);
            rightDriveMotor.setPower(-0.2);
        }
        leftDriveMotor.setPower(0);
        rightDriveMotor.setPower(0);
        telemetry.addData("", "Stage 3");
        sleep.Sleep(1000);
        climberDepositerServo.moveServo(1);
        sleep.Sleep(1000);
        climberDepositerServo.moveServo(0);
//        leftDriveMotor.setDirection(DcMotor.Direction.FORWARD);
//        rightDriveMotor.setDirection(DcMotor.Direction.REVERSE);
//        leftDriveMotor.setPower(0.25);
//        rightDriveMotor.setPower(0.25);
//        sleep.Sleep(750);
//        move.turnLeft(1400, 50);
//        while (colorSensor.getColor() != "red" && opModeIsActive()) {
//            leftDriveMotor.setDirection(DcMotor.Direction.FORWARD);
//            rightDriveMotor.setDirection(DcMotor.Direction.REVERSE);
//            leftDriveMotor.setPower(-0.25);
//            rightDriveMotor.setPower(-0.25);
//        }
    }
}