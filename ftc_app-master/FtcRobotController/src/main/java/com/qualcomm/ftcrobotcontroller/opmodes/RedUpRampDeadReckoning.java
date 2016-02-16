package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class RedUpRampDeadReckoning extends LinearOpMode {
    DcMotor leftMotor;
    DcMotor rightMotor;
    @Override
    public void runOpMode() throws InterruptedException {
        leftMotor = hardwareMap.dcMotor.get("leftDriveMotor");
        rightMotor = hardwareMap.dcMotor.get("rightDriveMotor");
        MoveMotors move = new MoveMotors(leftMotor, rightMotor);

        waitForStart();

        sleep(5000);

        move.moveForward(2200, 70);

        move.turnLeft(850, 100);

        move.moveForward(9500, 45);
    }
}
