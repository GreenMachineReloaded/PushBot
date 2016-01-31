package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;

/**
 * Created by Amber on 1/28/2016.
 */
public class BlueToParkingZone extends LinearOpMode {
    DcMotor leftDriveMotor;
    DcMotor rightDriveMotor;
    Sleeper sleep;
    GyroSensor gyro;
    @Override
    public void runOpMode() throws InterruptedException {
        leftDriveMotor = hardwareMap.dcMotor.get("leftDriveMotor");
        rightDriveMotor = hardwareMap.dcMotor.get("rightDriveMotor");
        gyro = hardwareMap.gyroSensor.get("gyro");
        sleep = new Sleeper();

        GyroObject gyroTurn = new GyroObject(leftDriveMotor, rightDriveMotor, gyro, telemetry);
        MoveMotors move = new MoveMotors(leftDriveMotor, rightDriveMotor);

        waitForStart();

    }
}