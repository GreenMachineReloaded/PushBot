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

public class RedGetOutOfTheWay extends LinearOpMode {
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
    UltrasonicObject ultrasonic ;
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

        us = hardwareMap.analogInput.get( "ultrasonic");
        ultrasonic = new UltrasonicObject(us, leftDriveMotor, rightDriveMotor);

        argColorSensor = hardwareMap.colorSensor.get("color");
        colorSensor = new ColorSensorObject(argColorSensor, telemetry);
        argUltrasonic = hardwareMap.analogInput.get( "ultrasonic");
        ultrasonic = new UltrasonicObject(argUltrasonic, leftDriveMotor, rightDriveMotor);

        opticSensorRed = new GMROpticDistanceSensor(opticSensorMap = hardwareMap.opticalDistanceSensor.get("RedOptic"));

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

        while (move.getTime() < 9 && opModeIsActive() && !canDeposit) {
            telemetry.addData("Current Time", move.getTime());
            telemetry.addData("Current Distance", (opticSensorRed.getDistance()));
            if ((opticSensorRed.getDistance() < 0.03) && opModeIsActive()) {
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
        telemetry.addData("Current Time", move.getTime());
        while (canDeposit && opModeIsActive()) {
            telemetry.addData("Current Distance", (opticSensorRed.getDistance()));
            telemetry.addData("Current Time", move.getTime());
            sleep.Sleep(1000);
            climberDepositerRedServo.moveServo(0);
            sleep.Sleep(1000);
            climberDepositerRedServo.moveServo(1);
            telemetry.addData("Current Distance", (opticSensorRed.getDistance()));
            canDeposit = false;
        }
        sleep.Sleep(500);
        move.moveBackward(1000, 75);
        while (move.getTime() < 15) {
            if (45 > move.getYaw()) {
                telemetry.addData("Current Time", move.getTime());
                leftDriveMotor.setPower(0.4);
                rightDriveMotor.setPower(-0.7);
                sleep.Sleep(10);
                t.addData("Gyro Heading", move.getYaw());
            } else {
                leftDriveMotor.setPower(0);
                rightDriveMotor.setPower(0);
            }
        }
        leftDriveMotor.setPower(0);
        rightDriveMotor.setPower(0);
        t.addData("Current Time", move.getTime());
        while (opModeIsActive()) {
            t.addData("Current Time", move.getTime());
            leftDriveMotor.setPower(-0.2);
            rightDriveMotor.setPower(-0.35);
        }
        t.addData("Current Time", move.getTime());
    }
}
