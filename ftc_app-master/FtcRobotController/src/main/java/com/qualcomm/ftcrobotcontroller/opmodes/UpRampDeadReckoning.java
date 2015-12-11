package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class UpRampDeadReckoning extends LinearOpMode {
    DcMotor leftMotor;
    DcMotor rightMotor;
    @Override
    public void runOpMode() throws InterruptedException {
        leftMotor = hardwareMap.dcMotor.get("leftDriveMotor");
        rightMotor = hardwareMap.dcMotor.get("rightDriveMotor");
        MoveMotorsObject move = new MoveMotorsObject(leftMotor, rightMotor);

        waitForStart();

        move.moveForward(2000, 100);

        move.turnRight(850, 100);

        move.moveForward(9500, 45);
    }
}
