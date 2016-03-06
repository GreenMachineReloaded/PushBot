package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.robocol.Telemetry;

/**
 * Created by Payton on 2/19/2016.
 */
public class BlueFarToParkingZone extends LinearOpMode {
    DcMotor leftDriveMotor;
    DcMotor rightDriveMotor;

    Sleeper sleep;

    GyroSensor gyro;

    Telemetry t;

    ColorSensorObject colorSensor;
    ColorSensor argColorSensor;

    AnalogInput argUltrasonic;
    UltrasonicObject ultrasonic;

    @Override
    public void runOpMode() throws InterruptedException {

        t = telemetry;

        sleep = new Sleeper();

        leftDriveMotor = hardwareMap.dcMotor.get("leftDriveMotor");
        rightDriveMotor = hardwareMap.dcMotor.get("rightDriveMotor");
        gyro = hardwareMap.gyroSensor.get("gyro");
        sleep = new Sleeper();

        argColorSensor = hardwareMap.colorSensor.get("color");
        colorSensor = new ColorSensorObject(argColorSensor, telemetry);

        gyro = hardwareMap.gyroSensor.get("gyro");

        argUltrasonic = hardwareMap.analogInput.get( "ultrasonic");
        ultrasonic = new UltrasonicObject(argUltrasonic, leftDriveMotor, rightDriveMotor);

        //GyroObject gyroTurn = new GyroObject(leftDriveMotor, rightDriveMotor, gyro, telemetry);
        MoveMotors move = new MoveMotors(colorSensor, leftDriveMotor, rightDriveMotor, ultrasonic, telemetry, gyro);
        waitForStart();

        sleep.Sleep(10000);

        move.moveForward(1000, 40);
        move.moveBackward(1000, 40);
        move.gyroRight(45);
        move.gyroLeft(45);
        move.traceALine();
    }
}
