package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.ftcrobotcontroller.opmodes.drivers.GMRServo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class DriveParkingZoneBlue extends LinearOpMode {
    DcMotor leftMotorArg;
    DcMotor rightMotorArg;
    Sleeper s;
    @Override
    public void runOpMode() throws InterruptedException {
        leftMotorArg = hardwareMap.dcMotor.get("leftDriveMotor");
        rightMotorArg = hardwareMap.dcMotor.get("rightDriveMotor");
        s = new Sleeper();
        MoveMotorsObject move = new MoveMotorsObject(leftMotorArg, rightMotorArg);
        waitForStart();
        move.moveForward(3600);
        move.turnRight(500);
        move.moveForward(1300);

    }
}

