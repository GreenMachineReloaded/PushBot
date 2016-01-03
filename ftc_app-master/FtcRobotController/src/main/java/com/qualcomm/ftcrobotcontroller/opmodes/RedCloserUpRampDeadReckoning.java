package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class RedCloserUpRampDeadReckoning extends LinearOpMode {
    DcMotor leftDriveMotor;
    DcMotor rightDriveMotor;
    @Override
    public void runOpMode() throws InterruptedException {
        leftDriveMotor = hardwareMap.dcMotor.get("leftDriveMotor");
        rightDriveMotor = hardwareMap.dcMotor.get("rightDriveMotor");
        MoveMotorsObject move = new MoveMotorsObject(leftDriveMotor, rightDriveMotor);

        waitForStart();

        sleep(10000);

        move.moveBackward(1400, 40);

        move.turnRight(1000, 35);

        move.moveForward(8000, 55);
    }

}


