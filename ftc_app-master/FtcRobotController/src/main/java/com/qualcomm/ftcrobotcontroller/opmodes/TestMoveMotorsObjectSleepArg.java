package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class TestMoveMotorsObjectSleepArg extends LinearOpMode {
    DcMotor leftMotor;
    DcMotor rightMotor;
    @Override
    public void runOpMode() throws InterruptedException {
        leftMotor = hardwareMap.dcMotor.get("leftDriveMotor");
        rightMotor = hardwareMap.dcMotor.get("rightDriveMotor");
        MoveMotorsObject move = new MoveMotorsObject(leftMotor, rightMotor);

        waitForStart();

        move.moveForward(1000);

        move.turnRight(1000);

        move.turnLeft(1000);

        move.moveBackward(1000);
    }
}
