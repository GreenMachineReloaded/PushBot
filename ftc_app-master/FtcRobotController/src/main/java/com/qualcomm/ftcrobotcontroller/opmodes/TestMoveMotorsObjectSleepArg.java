package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class TestMoveMotorsObjectSleepArg extends LinearOpMode {
    DcMotor leftMotor;
    DcMotor rightMotor;
    @Override   
    public void runOpMode() throws InterruptedException {
        leftMotor = hardwareMap.dcMotor.get("left_drive");
        rightMotor = hardwareMap.dcMotor.get("right_drive");
        MoveMotorsObject move = new MoveMotorsObject(leftMotor, rightMotor);

        waitForStart();

        move.moveForward();

        move.turnRight();

        move.turnLeft();

        move.moveBackward();
    }
}
