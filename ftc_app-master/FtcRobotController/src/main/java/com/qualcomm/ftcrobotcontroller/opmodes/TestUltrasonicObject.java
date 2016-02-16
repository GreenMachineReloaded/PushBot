package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.AnalogOutput;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;
import com.qualcomm.robotcore.robocol.Telemetry;

public class TestUltrasonicObject extends LinearOpMode {
    DcMotor argLeftMotor;
    DcMotor argRightMotor;
    AnalogInput AUO;
    UltrasonicObject UO;
    Sleeper s;
    Telemetry t;
    @Override
    public void runOpMode() throws InterruptedException {
        argLeftMotor = hardwareMap.dcMotor.get("leftDriveMotor");
        argRightMotor = hardwareMap.dcMotor.get("rightDriveMotor");
        AUO = hardwareMap.analogInput.get("ultrasonic");
        UO = new UltrasonicObject(AUO, argLeftMotor, argRightMotor);
        s = new Sleeper();
        t.addData("TUSO before start", UO.getRangeCentimeters());
        waitForStart();
        t.addData("TUSO after start", UO.getRangeCentimeters());
        s.Sleep(2000);
        UO.GoDistanceCentimeters(5);
    }
}
