package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftcrobotcontroller.opmodes.drivers.GMRServo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoController;

public class TestPWMServoEnableDisable extends LinearOpMode {
    ServoController Servo;
    Sleeper s;
    @Override
    public void runOpMode() throws InterruptedException {
        Servo = hardwareMap.servoController.get("");
        s = new Sleeper();
        waitForStart();
        Servo.pwmEnable();
        s.Sleep(1000);
        Servo.setServoPosition(10, .25);
        s.Sleep(3000);
        Servo.pwmDisable();
    }
}
