package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.ftcrobotcontroller.opmodes.OpticDistanceSensor;
public class TestOpticDistanceSensor extends LinearOpMode {
    DcMotor leftMotor;
    DcMotor rightMotor;

    OpticDistanceSensor opticDistanceSensoR;

    @Override
    public void runOpMode() throws InterruptedException {
        leftMotor = hardwareMap.dcMotor.get("left_drive");
        rightMotor = hardwareMap.dcMotor.get("right_drive");


        waitForStart();


    }
}
