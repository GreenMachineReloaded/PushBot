package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftcrobotcontroller.opmodes.drivers.GMRServo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.robocol.Telemetry;

public class RedStayInParkingZone extends LinearOpMode {
    DcMotor leftDriveMotor;
    DcMotor rightDriveMotor;

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

    Sleeper sleep;

    GyroSensor gyro;

    OpticalDistanceSensor opticSensorMap;
    GMROpticDistanceSensor opticSensorRed;

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

        GyroObject gyroTurn = new GyroObject(leftDriveMotor, rightDriveMotor,gyro,telemetry);
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

        while (opticSensorRed.getDistance() < 0.03 && opModeIsActive()) {
            leftDriveMotor.setDirection(DcMotor.Direction.FORWARD);
            rightDriveMotor.setDirection(DcMotor.Direction.REVERSE);
            leftDriveMotor.setPower(-0.2);
            rightDriveMotor.setPower(-0.25);
        }
        leftDriveMotor.setPower(0);
        rightDriveMotor.setPower(0);
        telemetry.addData("", "Stage 3");
        sleep.Sleep(1000);
        climberDepositerRedServo.moveServo(0);
        sleep.Sleep(1000);
        climberDepositerRedServo.moveServo(1);
    }
}
