package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Payton on 1/10/2016.
 */
public class CadwynSimpleDrive extends OpMode {

    DcMotor leftDriveMotor;
    DcMotor rightDriveMotor;

    @Override
    public void init() {
        leftDriveMotor = hardwareMap.dcMotor.get("leftDriveMotor");
        rightDriveMotor = hardwareMap.dcMotor.get("rightDriveMotor");

        leftDriveMotor.setDirection(DcMotor.Direction.FORWARD);
        rightDriveMotor.setDirection(DcMotor.Direction.REVERSE);
    }

    @Override
    public void loop() {
        float moveLeft = gamepad1.left_stick_y;
        float moveRight = gamepad1.right_stick_y;

        leftDriveMotor.setPower(moveLeft);
        rightDriveMotor.setPower(moveRight);
    }
}
