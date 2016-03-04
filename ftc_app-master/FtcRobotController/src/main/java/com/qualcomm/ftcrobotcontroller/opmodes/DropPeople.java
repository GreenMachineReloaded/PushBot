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

    GMRServo leftBlueFlapperServo;
    GMRServo rightRedFlapperServo;
    GMRServo climberDepositorServo;
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

    double flapperRightRedPosition;
    double flapperLeftBluePosition;
    double climberDepositorPosition;
    double winchServoPosition;
    double hopperDoorLeftPosition;
    double hopperDoorRightPosition;
    double hopperEntranceDoorPosition;
    double sweeperLiftPosition;


    UltrasonicObject UO;
    Sleeper s;
    GyroObject gyroO;
    MoveMotors move;

    double degrees;
    @Override
    public void runOpMode() throws InterruptedException {
        argLeftMotor = hardwareMap.dcMotor.get("leftDriveMotor");
        argRightMotor = hardwareMap.dcMotor.get("rightDriveMotor");
        AUO = hardwareMap.analogInput.get("ultrasonic");
        gyro = hardwareMap.gyroSensor.get("gyro");

        leftBlueFlapperServo = new GMRServo(servo1 = hardwareMap.servo.get("leftFlapperServo"));
        rightRedFlapperServo = new GMRServo(servo2 = hardwareMap.servo.get("rightFlapperServo"));
        climberDepositorServo = new GMRServo(servo3 = hardwareMap.servo.get("climberDepositerServo"));
        winchServo = new GMRServo(servo4 = hardwareMap.servo.get("winchServo"));
        hopperDoorRed = new GMRServo(servo5 = hardwareMap.servo.get("hopperDoorRed"));
        hopperDoorBlue = new GMRServo(servo6 = hardwareMap.servo.get("hopperDoorBlue"));
        hopperEntranceDoor = new GMRServo(servo7 = hardwareMap.servo.get("hopperEntranceDoor"));
        sweeperLift = new GMRServo(servo8 = hardwareMap.servo.get("sweeperLift"));

        flapperRightRedPosition = 1;
        flapperLeftBluePosition = 0;
        climberDepositorPosition = 0;
        winchServoPosition = 1;
        hopperDoorLeftPosition = 0.64;
        hopperDoorRightPosition = 0.882;
        hopperEntranceDoorPosition = 0.7;
        sweeperLiftPosition = 1;

        move = new MoveMotors(argLeftMotor, argRightMotor);
        gyroO = new GyroObject(argLeftMotor, argRightMotor, gyro, telemetry);
        UO = new UltrasonicObject(AUO);
        s = new Sleeper();


        degrees = 0;
        waitForStart();
        climberDepositorServo.moveServo(.01);
        move.moveForward(3000, 100);
        gyroO.rightTurn(45);
        UO.GoDistanceCentimeters(20);
        while(degrees < 10) {
            s.Sleep(50);
            degrees++;
            climberDepositorServo.moveServo(.075);
        }
    }
}
