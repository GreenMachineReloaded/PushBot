package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class TestMoveMotorsObjectSleepArg extends LinearOpMode {
    DcMotor leftMotor;
    DcMotor rightMotor;
    MoveMotors move;
    @Override
    public void runOpMode() throws InterruptedException {
        leftMotor = hardwareMap.dcMotor.get("leftDriveMotor");
        rightMotor = hardwareMap.dcMotor.get("rightDriveMotor");
        move = new MoveMotors(leftMotor, rightMotor);

        waitForStart();

        move.moveForward(1000, 100);

        move.turnRight(1000, 100);

        move.turnLeft(1000, 100);

        move.moveBackward(1000, 100);
    }
}
