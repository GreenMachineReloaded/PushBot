package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftcrobotcontroller.opmodes.drivers.GMRServo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.robocol.Telemetry;


public class DropPeople extends LinearOpMode {
    DcMotor argLeftMotor;
    DcMotor argRightMotor;
    AnalogInput AUO;
    GyroSensor gyro;
    Servo argServo;

    GMRServo servo;
    UltrasonicObject UO;
    Sleeper s;
    GyroObject gyroO;
    MoveMotors move;
    Telemetry t;

    double degrees;
    @Override
    public void runOpMode() throws InterruptedException {
        argLeftMotor = hardwareMap.dcMotor.get("leftDriveMotor");
        argRightMotor = hardwareMap.dcMotor.get("rightDriveMotor");
        AUO = hardwareMap.analogInput.get("ultrasonic");
        gyro = hardwareMap.gyroSensor.get("gyro");
        argServo = hardwareMap.servo.get("climberDepositerServo");

        servo = new GMRServo(argServo);
        move = new MoveMotors(argLeftMotor, argRightMotor);
        gyroO = new GyroObject(argLeftMotor, argRightMotor, gyro, t);
        UO = new UltrasonicObject(AUO);
        s = new Sleeper();

        degrees = 0;
        waitForStart();
        servo.moveServo(.01);
        move.moveForward(3000, 100);
        gyroO.rightTurn(45);
        UO.GoDistanceCentimeters(20);
        while(degrees < 10) {
            s.Sleep(50);
            degrees++;
            servo.moveServo(.075);
        }
    }
}
