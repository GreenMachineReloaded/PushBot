package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class BlueCloserUpRampDeadReckoning extends LinearOpMode {
    DcMotor leftDriveMotor;
    DcMotor rightDriveMotor;
    @Override
    public void runOpMode() throws InterruptedException {
        leftDriveMotor = hardwareMap.dcMotor.get("leftDriveMotor");
        rightDriveMotor = hardwareMap.dcMotor.get("rightDriveMotor");
        MoveMotorsObject move = new MoveMotorsObject(leftDriveMotor, rightDriveMotor);

        waitForStart();

        move.moveBackward(2100, 40);

        move.turnLeft(1400, 25);

        move.moveForward(8000, 50);
    }

}
