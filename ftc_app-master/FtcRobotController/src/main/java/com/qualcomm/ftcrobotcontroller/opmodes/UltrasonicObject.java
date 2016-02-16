package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;
import com.qualcomm.robotcore.robocol.Telemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class UltrasonicObject {
    AnalogInput sonic;
    DcMotor RightMotor;
    DcMotor LeftMotor;
    double range;
    Sleeper s;
    Telemetry t;
    public UltrasonicObject(AnalogInput ArgUltrasonicSensor) {
        sonic = ArgUltrasonicSensor;
        s = new Sleeper();
    }
    public UltrasonicObject(AnalogInput ArgUltrasonicSensor, DcMotor ArgLeftMotor,DcMotor ArgRightMotor) {
        sonic = ArgUltrasonicSensor;
        s = new Sleeper();
        RightMotor = ArgRightMotor;
        LeftMotor = ArgLeftMotor;

    }
    public double getRangeValue() {
        range = sonic.getValue();
        return range;
    }
    public double getRangeInches() {
        range = getRangeValue() / 6;
        return range;
    }
    public double getRangeCentimeters() {
        range = getRangeInches() / 2.54;
        return range;
    }
    public void GoDistanceValue(double distance) {
        RightMotor.setPower(1.00);
        LeftMotor.setPower(1.00);
        while(range >= distance) {
            s.Sleep(50);
            range = getRangeValue();
        }
        RightMotor.setPower(0);
        LeftMotor.setPower(0);
    }
    public void GoDistanceInches(double distance) {
        RightMotor.setPower(1.00);
        LeftMotor.setPower(1.00);
        while(range >= distance) {
            s.Sleep(50);
            range = getRangeInches();
        }
        RightMotor.setPower(0);
        LeftMotor.setPower(0);
    }
    public void GoDistanceCentimeters(double distance) {

        RightMotor.setPower(.50);
        LeftMotor.setPower(.50);
        t.addData("range centimeter", getRangeCentimeters());
        s.Sleep(2000);
        while(range >= distance) {
            t.addData("range centimeter loup", getRangeCentimeters());
            s.Sleep(50);
            range = getRangeCentimeters();
        }
        RightMotor.setPower(0);
        LeftMotor.setPower(0);
    }
}