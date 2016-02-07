package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftcrobotcontroller.opmodes.drivers.GMRServo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by Amber on 1/29/2016. waffle
 */
public class RedToParkingZone extends LinearOpMode {
    DcMotor leftDriveMotor;
    DcMotor rightDriveMotor;
    Sleeper sleep;
    GyroSensor gyro;
    GMRServo leftFlapperServo;
    GMRServo rightFlapperServo;
    Servo servo1;
    Servo servo2;

    double flapperRightPosition;
    double flapperLeftPosition;
    @Override
    public void runOpMode() throws InterruptedException {
        leftDriveMotor = hardwareMap.dcMotor.get("leftDriveMotor");
        rightDriveMotor = hardwareMap.dcMotor.get("rightDriveMotor");
        gyro = hardwareMap.gyroSensor.get("gyro");
        sleep = new Sleeper();
        leftFlapperServo = new GMRServo(servo1 = hardwareMap.servo.get("leftFlapperServo"));
        rightFlapperServo = new GMRServo(servo2 = hardwareMap.servo.get("rightFlapperServo"));

        flapperRightPosition =  1;
        flapperLeftPosition =  0;

        flapperRightPosition =  Range.clip(flapperRightPosition, 0, 1);
        rightFlapperServo.moveServo(flapperRightPosition);

        flapperLeftPosition =  Range.clip(flapperLeftPosition, 0.8, 1);
        leftFlapperServo.moveServo(flapperLeftPosition);

        GyroObject gyroTurn = new GyroObject(leftDriveMotor, rightDriveMotor,gyro,telemetry);
        MoveMotors move = new MoveMotors(leftDriveMotor, rightDriveMotor);

        waitForStart();

        move.moveForward(500, 40);

        gyroTurn.leftTurn(45);

        move.moveForward(3000, 50);

        gyroTurn.leftTurn(45);

        move.moveForward(500, 40);

    }
}
