package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;


    /**
     * Created by Kelly on 1/29/2016.
     */


    public class NewBlueToParkingZone extends LinearOpMode {
        DcMotor argLeftMotor;
        DcMotor argRightMotor;
        ColorSensorObject colorSensor;
        ColorSensor argColorSensor;
        MoveMotors move;
        AnalogInput argUltrasonic;
        UltrasonicObject ultrasonic ;
        FollowLine followLine;

        @Override
        public void runOpMode() throws InterruptedException {
            argLeftMotor = hardwareMap.dcMotor.get("leftDriveMotor");
            argRightMotor = hardwareMap.dcMotor.get("rightDriveMotor");

            move = new MoveMotors(argLeftMotor, argRightMotor);
            argColorSensor = hardwareMap.colorSensor.get("color");

            colorSensor = new ColorSensorObject(argColorSensor, telemetry);
            argRightMotor.setDirection(DcMotor.Direction.REVERSE);
            argUltrasonic = hardwareMap.analogInput.get( "ultrasonic");
            ultrasonic = new UltrasonicObject(argUltrasonic, argLeftMotor, argRightMotor);
            followLine = new FollowLine (colorSensor, argRightMotor, argLeftMotor, ultrasonic,telemetry);

            waitForStart();

            telemetry.addData("","Program Start");

            followLine.traceALine();


   //if color sensor sees grey
//            while ((colorSensor.green() <= 59) && (colorSensor.blue() <= 59) && (colorSensor.red() <= 59)) {
//
//                move.turnRight(10,45);//has robot turn right
//                argLeftMotor.setPower(0.4);//pwr for left motor
//                argRightMotor.setPower(-0.4);//pwr for right motor
//            }
//
//            //if color sensor sees grey
//            while ((colorSensor.green() >= 60) && (colorSensor.blue() >= 60) && (colorSensor.red() >= 60))
//
//                // have robot turn counterclockwise depending on how much the robot turns
//                move.moveForward(10,45);//move robot fwd
//            argLeftMotor.setPower(0.3);//pwr for left motor
//            argRightMotor.setPower(0.3);//pwr for right motor
//            ultrasonic.GoDistanceCentimeters(8);//ultrasonic distance from wall
            //have move forward until ultrasonic sensor reaches a certain point

        }
    }



//RGB - 60+ for all

//right motors are opposite