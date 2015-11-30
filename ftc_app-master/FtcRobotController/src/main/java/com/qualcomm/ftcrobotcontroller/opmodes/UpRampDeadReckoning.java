package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class UpRampDeadReckoning extends LinearOpMode {
    DcMotor leftMotor;
    DcMotor rightMotor;
    @Override
    public void runOpMode() throws InterruptedException {
        leftMotor = hardwareMap.dcMotor.get("leftdriveMotor");
        rightMotor = hardwareMap.dcMotor.get("rightdriveMotor");
        MoveMotorsObject move = new MoveMotorsObject(leftMotor, rightMotor);

        waitForStart();

        move.moveForward(3500);

        move.turnRight(1000);

        move.moveForward(2500);
    }
}
