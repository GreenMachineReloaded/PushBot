package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;


/* Created by Amber on 10/4/2015.
 */
public class test extends LinearOpMode {
    DcMotor leftMotor;
    DcMotor rightMotor;
    @Override
    public void runOpMode() throws InterruptedException {
        leftMotor = hardwareMap.dcMotor.get("left_drive");
        rightMotor = hardwareMap.dcMotor.get("right_drive");
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        leftMotor.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        leftMotor.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);

        waitForStart();
        leftMotor.setPower(0.1);
        rightMotor.setPower(0.1);
        sleep(5000);
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }
}
