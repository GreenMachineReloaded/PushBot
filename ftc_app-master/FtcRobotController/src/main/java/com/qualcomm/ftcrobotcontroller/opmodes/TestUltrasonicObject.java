package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.robocol.Telemetry;


public class TestUltrasonicObject extends LinearOpMode {
    DcMotor argLeftMotor;
    DcMotor argRightMotor;
    AnalogInput AUO;
    UltrasonicObject UO;
    Sleeper s;
    double range;
    @Override
    public void runOpMode() throws InterruptedException {
        argLeftMotor = hardwareMap.dcMotor.get("leftDriveMotor");
        argRightMotor = hardwareMap.dcMotor.get("rightDriveMotor");
        AUO = hardwareMap.analogInput.get("ultrasonic");
        UO = new UltrasonicObject(AUO);
        s = new Sleeper();
        telemetry.addData("hello", 0);
        waitForStart();
        while(true) {
            s.Sleep(500);
            telemetry.addData("hello2", UO.getRangeCentimeters());
        }
    }
}
