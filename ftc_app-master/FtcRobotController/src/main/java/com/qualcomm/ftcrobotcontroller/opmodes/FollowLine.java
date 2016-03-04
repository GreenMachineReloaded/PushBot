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
    String startOfProgram;
    public FollowLine(ColorSensorObject cs, DcMotor rm, DcMotor lm, UltrasonicObject us, Telemetry telemetry){
        colorSensor = cs;
        rightMotor = rm;
        leftMotor = lm;
        ultrasonic = us;
        t = telemetry;
        isOnWhite = true;
        sleep = new Sleeper();
        lastDirection = "start";
        mm = new MoveMotors(lm,rm);
        directionCanSwitch = "yes";
        startOfProgram = "start";
    }
    public void traceALine(){
        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        t.addData("", "Trace a line start");
        t.addData("", ultrasonic.getRangeInches());

        while(ultrasonic.getRangeInches() > 5){
            t.addData("", lastDirection);

            if (colorSensor.getColor() == "white") {
                leftMotor.setPower(-0.35);
                rightMotor.setPower(0.1);
            } else if (colorSensor.getColor() == "gray") {
                rightMotor.setPower(-0.35);
                leftMotor.setPower(0.1);
            }

        }
        rightMotor.setPower(0);
        leftMotor.setPower(0);
    }
}
