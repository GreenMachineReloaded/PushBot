package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class TestOpticDistanceSensor extends LinearOpMode {
    DcMotor leftMotor;
    DcMotor rightMotor;

    GMROpticDistanceSensor opticDistanceSensoR;

    @Override
    public void runOpMode() throws InterruptedException {
        leftMotor = hardwareMap.dcMotor.get("left_drive");
        rightMotor = hardwareMap.dcMotor.get("right_drive");


        waitForStart();


    }
}
