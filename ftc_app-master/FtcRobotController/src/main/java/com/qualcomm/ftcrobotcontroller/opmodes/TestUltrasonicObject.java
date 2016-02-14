package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.AnalogOutput;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;

public class TestUltrasonicObject extends LinearOpMode {
    DcMotor argLeftMotor;
    DcMotor argRightMotor;
    AnalogInput AUO;
    UltrasonicObject UO;
    Sleeper s;
    @Override
    public void runOpMode() throws InterruptedException {
        argLeftMotor = hardwareMap.dcMotor.get("leftDriveMotor");
        argRightMotor = hardwareMap.dcMotor.get("rightDriveMotor");
        AUO = hardwareMap.analogInput.get("ultrasonic");
        UO = new UltrasonicObject(AUO, argLeftMotor, argRightMotor);
        s = new Sleeper();
        waitForStart();
        UO.GoDistanceCentimeters(20);
    }
}
