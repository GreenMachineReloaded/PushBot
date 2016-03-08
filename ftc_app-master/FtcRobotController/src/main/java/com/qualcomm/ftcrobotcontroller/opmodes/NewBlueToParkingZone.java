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

            //move = new MoveMotors(argLeftMotor, argRightMotor);
            argColorSensor = hardwareMap.colorSensor.get("color");
            colorSensor = new ColorSensorObject(argColorSensor, telemetry);
            argRightMotor.setDirection(DcMotor.Direction.REVERSE);
            argUltrasonic = hardwareMap.analogInput.get( "ultrasonic");
            ultrasonic = new UltrasonicObject(argUltrasonic, argLeftMotor, argRightMotor);
            followLine = new FollowLine (colorSensor, argRightMotor, argLeftMotor, ultrasonic, telemetry);

            waitForStart();

            while (true) {
                telemetry.addData("Red",colorSensor.red());
                telemetry.addData("Green",colorSensor.green());
                telemetry.addData("Blue",colorSensor.blue());
            }
        }
    }



//RGB - 60+ for all

//right motors are opposite