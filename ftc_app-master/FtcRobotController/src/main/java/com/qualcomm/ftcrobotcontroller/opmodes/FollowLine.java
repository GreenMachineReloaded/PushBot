package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;
import static android.graphics.Color.*;
import com.qualcomm.robotcore.robocol.Telemetry;

/**
 * Created by Patrick on 2/16/2016.
 */
public class FollowLine {

    ColorSensorObject colorSensor;
    DcMotor rightMotor;
    DcMotor leftMotor;
    UltrasonicObject ultrasonic;
    Telemetry telemetry;

    public FollowLine(ColorSensorObject cs, DcMotor rm, DcMotor lm, UltrasonicObject us){

        colorSensor = cs;
        rightMotor = rm;
        leftMotor = lm;
        ultrasonic =us;


    }
    public void traceALine(){

        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        String direction = "left";
        while(ultrasonic.getRangeCentimeters() > 10){

            if(direction.equals("left")) {
                while(colorSensor.getColor() == WHITE ){
                    rightMotor.setPower(0.2);
                    leftMotor.setPower(0.3);
                    telemetry.addData("colorSensor left", "");
                }
                direction = "right";


            } else if(direction.equals("right")){
                while(colorSensor.getColor() == WHITE ){
                    rightMotor.setPower(0.3);
                    leftMotor.setPower(0.2);
                    telemetry.addData("colorSensor right", "");
                }
                direction = "left";

            }
        }
    }

}
