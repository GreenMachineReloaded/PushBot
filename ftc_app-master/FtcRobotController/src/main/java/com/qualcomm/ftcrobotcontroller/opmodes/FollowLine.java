package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.robocol.Telemetry;

/**
 * Created by Patrick on 2/16/2016.
 */
public class FollowLine {

    ColorSensorObject colorSensor;
    DcMotor rightMotor;
    DcMotor leftMotor;
    UltrasonicObject ultrasonic;
    Telemetry t;
    boolean isOnWhite;
    Sleeper sleep;
    String lastDirection;
    MoveMotors mm;
    String directionCanSwitch;
    public FollowLine(ColorSensorObject cs, DcMotor rm, DcMotor lm, UltrasonicObject us, Telemetry telemetry){
        colorSensor = cs;
        rightMotor = rm;
        leftMotor = lm;
        ultrasonic = us;
        t = telemetry;
        isOnWhite = true;
        sleep = new Sleeper();
        lastDirection = "left";
        mm = new MoveMotors(lm,rm);
        directionCanSwitch = "yes";
    }
    public void traceALine(){
        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        t.addData("", "Trace a line start");
        t.addData("", ultrasonic.getRangeInches());
        while(ultrasonic.getRangeInches() > 2){
            t.addData("", lastDirection);

            if (colorSensor.getColor() == "white") {
                leftMotor.setPower(-0.3);
                rightMotor.setPower(0.1);
            } else if (colorSensor.getColor() == "gray") {
                rightMotor.setPower(-0.3);
                leftMotor.setPower(0.1);
            }

            //t.addData("Ultrasonic Range Inches ",ultrasonic.getRangeInches());
            //t.addData("Ultrasonic Range Centimeters",ultrasonic.getRangeCentimeters());
            //t.addData("Ultrasonic Range Value",ultrasonic.getRangeValue());

        }
        rightMotor.setPower(0);
        leftMotor.setPower(0);
    }
}
