package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.robocol.Telemetry;
import com.qualcomm.robotcore.util.ElapsedTime;
public class RedFarToParkingZone0Delay extends LinearOpMode {
    DcMotor leftDriveMotor;
    DcMotor rightDriveMotor;

    Sleeper sleep;
    Sleeper s;


    GyroSensor gyro;

    Telemetry t;

    ColorSensor cs;

    AnalogInput us;

    ColorSensorObject colorSensor;
    ColorSensor argColorSensor;
    AnalogInput argUltrasonic;
    UltrasonicObject ultrasonic ;
    OpticalDistanceSensor opticSensorMap;
    GMROpticDistanceSensor opticSensor;
    ElapsedTime runtime;

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

        opticSensor = new GMROpticDistanceSensor(opticSensorMap = hardwareMap.opticalDistanceSensor.get("optic"));

        runtime = new ElapsedTime();

        MoveMotors move = new MoveMotors(colorSensor, leftDriveMotor, rightDriveMotor, ultrasonic, telemetry, gyro, opticSensor, hardwareMap);

        waitForStart();
        move.setServosAuto();

        telemetry.addData("", "Stage 1");
        sleep.Sleep(50);

        while (colorSensor.getColor() == "gray" && opModeIsActive()) {
            leftDriveMotor.setDirection(DcMotor.Direction.FORWARD);
            rightDriveMotor.setDirection(DcMotor.Direction.REVERSE);
            leftDriveMotor.setPower(-0.2);
            rightDriveMotor.setPower(-0.2);
            telemetry.addData("Current Red", colorSensor.red());
            telemetry.addData("Current Green", colorSensor.green());
            telemetry.addData("Current Blue", colorSensor.blue());
        }

        leftDriveMotor.setPower(0);
        rightDriveMotor.setPower(0);

        telemetry.addData("", "Stage 3");

        sleep.Sleep(2000);

        move.gyroLeft(45);

        sleep.Sleep(1000);
        telemetry.addData("Turn Complete", "");

        sleep.Sleep(1000);

        while (opticSensor.getDistance() < 0.03 && opModeIsActive()) {
            leftDriveMotor.setPower(-0.40);
            rightDriveMotor.setPower(-0.5);
        }
        leftDriveMotor.setPower(0);
        rightDriveMotor.setPower(0);

        sleep.Sleep(50);
        if (opModeIsActive()) {
            move.moveServo("climberDepositerBlueServo", 1);
        }

        sleep.Sleep(1000);
        move.moveServo("climberDepositerBlueServo", 0);
    }
}
