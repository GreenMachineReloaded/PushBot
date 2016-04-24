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
import com.qualcomm.robotcore.util.ElapsedTime;

public class BlueGetOutOfTheWay extends LinearOpMode {
    DcMotor leftDriveMotor;
    DcMotor rightDriveMotor;

    Sleeper sleep;
    Sleeper s;

    GMRServo leftFlapperServo;
    GMRServo rightFlapperServo;
    GMRServo climberDepositerBlueServo;
    GMRServo climberDepositerRedServo;
    GMRServo winchServo;
    GMRServo hopperDoorBlue;
    GMRServo hopperDoorRed;
    GMRServo hopperEntranceDoor;
    GMRServo redAllClear;
    GMRServo blueAllClear;

    Servo servo1;
    Servo servo2;
    Servo servo3;
    Servo servo4;
    Servo servo5;
    Servo servo6;
    Servo servo7;
    Servo servo8;
    Servo servo11;
    Servo servo12;

    GyroSensor gyro;

    Telemetry t;

    ColorSensor cs;

    AnalogInput us;

    ColorSensorObject colorSensor;
    ColorSensor argColorSensor;
    AnalogInput argUltrasonic;
    UltrasonicObject ultrasonic;
    OpticalDistanceSensor opticSensorMap;
    GMROpticDistanceSensor opticSensorRed;
    ElapsedTime runtime;

    Boolean canDeposit;

    @Override
    public void runOpMode() throws InterruptedException {

        t = telemetry;

        s = new Sleeper();
        sleep = new Sleeper();

        leftDriveMotor = hardwareMap.dcMotor.get("leftDriveMotor");
        rightDriveMotor = hardwareMap.dcMotor.get("rightDriveMotor");

        gyro = hardwareMap.gyroSensor.get("gyro");

        colorSensor = new ColorSensorObject(cs, telemetry);

        us = hardwareMap.analogInput.get("ultrasonic");
        ultrasonic = new UltrasonicObject(us, leftDriveMotor, rightDriveMotor);

        argColorSensor = hardwareMap.colorSensor.get("color");
        colorSensor = new ColorSensorObject(argColorSensor, telemetry);
        argUltrasonic = hardwareMap.analogInput.get("ultrasonic");
        ultrasonic = new UltrasonicObject(argUltrasonic, leftDriveMotor, rightDriveMotor);

        opticSensorRed = new GMROpticDistanceSensor(opticSensorMap = hardwareMap.opticalDistanceSensor.get("blueOptic"));

        leftFlapperServo = new GMRServo(servo1 = hardwareMap.servo.get("leftFlapperServo"));
        rightFlapperServo = new GMRServo(servo2 = hardwareMap.servo.get("rightFlapperServo"));
        climberDepositerBlueServo = new GMRServo(servo3 = hardwareMap.servo.get("climberDepositerBlueServo"));
        climberDepositerRedServo = new GMRServo(servo8 = hardwareMap.servo.get("climberDepositorRedServo"));
        winchServo = new GMRServo(servo4 = hardwareMap.servo.get("winchServo"));
        hopperDoorRed = new GMRServo(servo5 = hardwareMap.servo.get("hopperDoorRed"));
        hopperDoorBlue = new GMRServo(servo6 = hardwareMap.servo.get("hopperDoorBlue"));
        hopperEntranceDoor = new GMRServo(servo7 = hardwareMap.servo.get("hopperEntranceDoor"));
        redAllClear = new GMRServo(servo11 = hardwareMap.servo.get("redallClear"));
        blueAllClear = new GMRServo(servo12 = hardwareMap.servo.get("blueallclear"));

        runtime = new ElapsedTime();

        canDeposit = false;

        MoveMotors move = new MoveMotors(colorSensor, leftDriveMotor, rightDriveMotor, ultrasonic, telemetry, gyro, opticSensorRed, hardwareMap);

        waitForStart();
        rightFlapperServo.moveServo(1);
        leftFlapperServo.moveServo(0);
        climberDepositerBlueServo.moveServo(0);
        climberDepositerRedServo.moveServo(1);
        winchServo.moveServo(1);
        hopperDoorRed.moveServo(0.7755);
        hopperDoorBlue.moveServo(0.0245);
        hopperEntranceDoor.moveServo(0.7);
        redAllClear.moveServo(1);
        blueAllClear.moveServo(0);

        while (move.getTime() < 9 && opModeIsActive()) {
            if (opticSensorRed.getDistance() < 0.03 && opModeIsActive()) {
                leftDriveMotor.setDirection(DcMotor.Direction.FORWARD);
                rightDriveMotor.setDirection(DcMotor.Direction.REVERSE);
                leftDriveMotor.setPower(-0.2);
                rightDriveMotor.setPower(-0.25);
            }else {
                leftDriveMotor.setPower(0);
                rightDriveMotor.setPower(0);
                canDeposit = true;
            }
        }
        leftDriveMotor.setPower(0);
        rightDriveMotor.setPower(0);
        telemetry.addData("", "Stage 3");
        while (canDeposit && opModeIsActive()) {
            sleep.Sleep(1000);
            climberDepositerBlueServo.moveServo(1);
            sleep.Sleep(1000);
            climberDepositerBlueServo.moveServo(0);
            canDeposit = false;
        }
        sleep.Sleep(20);
        move.moveBackward(500, 75);
            while (move.getTime() < 14) {
                if (45 > -move.getYaw()) {
                    t.addData("Goal Heading", 45);
                    leftDriveMotor.setPower(-0.7);
                    rightDriveMotor.setPower(0.4);
                    sleep.Sleep(10);
                    t.addData("Gyro Heading", -move.getYaw());
                } else {
                    leftDriveMotor.setPower(0);
                    rightDriveMotor.setPower(0);
                }
            }
        leftDriveMotor.setPower(0);
        rightDriveMotor.setPower(0);
        if (move.getTime() < 26) {
            move.moveForward(3000, 50);
        }
    }
}