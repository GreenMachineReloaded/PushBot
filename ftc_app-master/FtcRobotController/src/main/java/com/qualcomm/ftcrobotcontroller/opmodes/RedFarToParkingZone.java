package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftcrobotcontroller.opmodes.drivers.GMRServo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.robocol.Telemetry;

/**
 * Created by Payton on 2/16/2016.
 */
public class RedFarToParkingZone extends LinearOpMode {
    DcMotor leftDriveMotor;
    DcMotor rightDriveMotor;

    Sleeper sleep;
    Sleeper s;


    GyroSensor gyro;

<<<<<<< HEAD
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
=======
//    GMRServo leftFlapperServo;
//    GMRServo rightFlapperServo;
//    GMRServo climberDepositerServo;
//    GMRServo winchServo;
//    GMRServo hopperDoorBlue;
//    GMRServo hopperDoorRed;
//    GMRServo hopperEntranceDoor;
//    GMRServo sweeperLift;

//    Servo servo1;
//    Servo servo2;
//    Servo servo3;
//    Servo servo4;
//    Servo servo5;
//    Servo servo6;
//    Servo servo7;
//    Servo servo8;

//    double flapperRightPosition;
//    double flapperLeftPosition;
//    double climberDepositerPosition;
//    double winchServoPosition;
//    double hopperDoorleftPosition;
//    double hopperDoorRightPosition;
//    double hopperEntranceDoorPosition;
//    double sweeperLiftPosition;
>>>>>>> 45c990b77cfeaa19e63fb3e8cf33212c82a016f6

    Telemetry t;

    FollowLine followLine;

    ColorSensorObject colorSensor;
    ColorSensor cs;

    UltrasonicObject ultrasonic;
    AnalogInput us;

    ColorSensorObject colorSensor;
    ColorSensor argColorSensor;
    AnalogInput argUltrasonic;
    UltrasonicObject ultrasonic ;
    FollowLine followLine;

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

        followLine = new FollowLine (colorSensor, rightDriveMotor, leftDriveMotor, ultrasonic, t);

        GyroObject gyroTurn = new GyroObject(leftDriveMotor, rightDriveMotor, gyro, telemetry);

        MoveMotors move = new MoveMotors(leftDriveMotor, rightDriveMotor);


<<<<<<< HEAD
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
=======
//        leftFlapperServo = new GMRServo(servo1 = hardwareMap.servo.get("leftFlapperServo"));
//        rightFlapperServo = new GMRServo(servo2 = hardwareMap.servo.get("rightFlapperServo"));
//        climberDepositerServo = new GMRServo(servo3 = hardwareMap.servo.get("climberDepositerServo"));
//        winchServo = new GMRServo(servo4 = hardwareMap.servo.get("winchServo"));
//        hopperDoorRed = new GMRServo(servo5 = hardwareMap.servo.get("hopperDoorRed"));
//        hopperDoorBlue = new GMRServo(servo6 = hardwareMap.servo.get("hopperDoorBlue"));
//        hopperEntranceDoor = new GMRServo(servo7 = hardwareMap.servo.get("hopperEntranceDoor"));
//        sweeperLift = new GMRServo(servo8 = hardwareMap.servo.get("sweeperLift"));

//        flapperRightPosition = 1;
//        flapperLeftPosition = 0;
//        climberDepositerPosition = 0;
//        winchServoPosition = 1;
//        hopperDoorleftPosition = 0.64;
//        hopperDoorRightPosition = 0.03;
//        hopperEntranceDoorPosition = 0.7;
//        sweeperLiftPosition = 1;
//
//        flapperRightPosition = Range.clip(flapperRightPosition, 0, 1);
//        rightFlapperServo.moveServo(flapperRightPosition);
//
//        flapperLeftPosition = Range.clip(flapperLeftPosition, 0, 1);
//        leftFlapperServo.moveServo(flapperLeftPosition);
//
//        climberDepositerPosition = Range.clip(climberDepositerPosition, 0, 1);
//        climberDepositerServo.moveServo(climberDepositerPosition);
//
//        winchServoPosition = Range.clip(winchServoPosition, 0.8, 1);
//        winchServo.moveServo(winchServoPosition);
//
//        hopperDoorleftPosition = Range.clip(hopperDoorleftPosition, 0.064, 0.64);
//        hopperDoorRed.moveServo(hopperDoorleftPosition);
//
//        hopperDoorRightPosition = Range.clip(hopperDoorRightPosition, 0.03, 0.6);
//        hopperDoorBlue.moveServo(hopperDoorRightPosition);
>>>>>>> 45c990b77cfeaa19e63fb3e8cf33212c82a016f6


        argColorSensor = hardwareMap.colorSensor.get("color");
        colorSensor = new ColorSensorObject(argColorSensor, telemetry);
        argUltrasonic = hardwareMap.analogInput.get( "ultrasonic");
        ultrasonic = new UltrasonicObject(argUltrasonic, leftDriveMotor, rightDriveMotor);
        followLine = new FollowLine (colorSensor, rightDriveMotor, leftDriveMotor, ultrasonic, telemetry);

        waitForStart();

<<<<<<< HEAD
        sleep.Sleep(10000);

        move.moveForward(250, 25);

        sleep.Sleep(50);
=======
        move.moveForward(250, 25);
>>>>>>> 45c990b77cfeaa19e63fb3e8cf33212c82a016f6

        gyroTurn.leftTurn(45);

        sleep.Sleep(50);

        move.moveForward(4300, 50);

        sleep.Sleep(50);

        gyroTurn.leftTurn(70);

<<<<<<< HEAD
        sleep.Sleep(50);

        move.moveForward(500, 40);

        sleep.Sleep(50);

//        followLine.traceALine();
//
//        rightDriveMotor.setPower(-0.5);
//        leftDriveMotor.setPower(0.08);
//        sleep.Sleep(1500);
//        rightDriveMotor.setPower(0);
//
//        climberDepositerPosition = 1;
//        climberDepositerServo.moveServo(climberDepositerPosition);
=======
        move.moveForward(750, 40);

        followLine.traceALine();
>>>>>>> 45c990b77cfeaa19e63fb3e8cf33212c82a016f6

    }
}
