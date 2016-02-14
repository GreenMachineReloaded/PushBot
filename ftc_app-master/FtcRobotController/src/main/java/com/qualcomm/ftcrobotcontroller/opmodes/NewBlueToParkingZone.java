package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.robocol.Telemetry;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;


    /**
     * Created by Kelly on 1/29/2016.
     */


    public class NewBlueToParkingZone extends LinearOpMode {
        DcMotor argLeftMotor;
        DcMotor argRightMotor;
        ColorSensorObject colorSensor;
        ColorSensor argColorSensor;
        MoveMotorsObject move;
        Telemetry telemetry;
//    UltrasonicSensor argUltrasonic;
//    UltrasonicObject ultrasonic ;

        @Override
        public void runOpMode() throws InterruptedException {
            argLeftMotor = hardwareMap.dcMotor.get("leftDriveMotor");
            argRightMotor = hardwareMap.dcMotor.get("rightDriveMotor");
            move = new MoveMotorsObject(argLeftMotor, argRightMotor, 1000);
            argColorSensor = hardwareMap.colorSensor.get("color");
            colorSensor = new ColorSensorObject(argColorSensor, telemetry);
            argRightMotor.setDirection(DcMotor.Direction.REVERSE);
//        argUltrasonic = hardwareMap.ultrasonicSensor.get( "ultrasonic");
//        ultrasonic = new UltrasonicObject();


            waitForStart();

            //if color sensor sees grey
            while ((colorSensor.green() <= 59) && (colorSensor.blue() <= 59) && (colorSensor.red() <= 59)) {

                move.turnRight();//has robot turn right
                argLeftMotor.setPower(0.4);//pwr for left motor
                argRightMotor.setPower(-0.4);//pwr for right motor
            }

            //if color sensor sees grey
            while ((colorSensor.green() >= 60) && (colorSensor.blue() >= 60) && (colorSensor.red() >= 60))

                // have robot turn counterclockwise depending on how much the robot turns
                move.moveForward();//move robot fwd
            argLeftMotor.setPower(0.3);//pwr for left motor
            argRightMotor.setPower(0.3);//pwr for right motor
            //ultrasonic.goDistance();//ultrasonic distancefrom wall
            //have move forward until ultrasonic sensor reaches a certain point

        }
    }



//RGB - 60+ for all

//right motors are opposite