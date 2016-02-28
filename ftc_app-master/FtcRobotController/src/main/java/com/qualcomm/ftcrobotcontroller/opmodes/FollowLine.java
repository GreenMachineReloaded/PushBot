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
<<<<<<< HEAD
    Telemetry t;
    boolean isOnWhite;
    Sleeper sleep;
    String lastDirection;
    MoveMotors mm;
    String directionCanSwitch;
    public FollowLine(ColorSensorObject cs, DcMotor rm, DcMotor lm, UltrasonicObject us, Telemetry telemetry){
=======
    Telemetry telemetry;

    public FollowLine(ColorSensorObject cs, DcMotor rm, DcMotor lm, UltrasonicObject us) {

>>>>>>> 4944ab70686b3a08783f5ae8e326c89081ce825a
        colorSensor = cs;
        rightMotor = rm;
        leftMotor = lm;
        ultrasonic = us;
<<<<<<< HEAD
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
        while(ultrasonic.getRangeInches() > 5){
            t.addData("", lastDirection);

            rightMotor.setPower(-0.3);
            leftMotor.setPower(-0.3);

            while (colorSensor.getColor() == "gray" && lastDirection == "left") {
                mm.turnLeft(10,40);
                directionCanSwitch = "yes";
            }
            while (colorSensor.getColor() == "gray" && lastDirection == "right") {
                mm.turnRight(10,40);
                directionCanSwitch = "yes";
            }
            if (colorSensor.getColor() == "white" && lastDirection == "right" && directionCanSwitch == "yes") {
                lastDirection = "left";
                directionCanSwitch = "no";
            } else if (colorSensor.getColor() == "white" && lastDirection == "left" && directionCanSwitch == "yes") {
                lastDirection = "right";
                directionCanSwitch = "no";
            }




//            if (direction == "left") {
//                rightMotor.setPower(0.2);
//                leftMotor.setPower(-0.3);
//                //sleep.Sleep(200);
//            }else if (direction=="right") {
//                rightMotor.setPower(-0.3);
//                leftMotor.setPower(0.2);
//                //sleep.Sleep(200);
//            }
//            if (colorSensor.getColor() != "white" && direction == "left" && isOnWhite) {
//                direction = "right";
//                isOnWhite = false;
//            }else if (colorSensor.getColor() != "white" && direction == "right" && isOnWhite) {
//                direction = "left";
//                isOnWhite = false;
//            }
//            if (colorSensor.getColor() == "white") {
//                isOnWhite = true;
//            }
//            if(direction=="left") {
//                t.addData("Turning left", "");
//                while(colorSensor.getColor()=="white" ){
//                    rightMotor.setPower(-0.05);
//                    leftMotor.setPower(-0.2);
//                }
//            } else if(direction=="right"){
//                t.addData("Turning right", "");
//                while(colorSensor.getColor()=="white" ){
//                    rightMotor.setPower(-0.2);
//                    leftMotor.setPower(-0.05);
//                    t.addData("colorSensor right", "");
//                }
//            }
//            if (colorSensor.getColor()=="gray" && direction=="left") {
//                direction = "right";
//            } else if (colorSensor.getColor()=="gray" && direction=="right") {
//                direction = "left";
//            }
=======


    }

    public void traceALine() {

        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        String direction = "left";
        while (ultrasonic.getRangeCentimeters() > 10) {
            if (direction.equals("left")) {
                while (colorSensor.getColor() == WHITE) {
                    rightMotor.setPower(0.2);
                    leftMotor.setPower(0.3);
                    telemetry.addData("colorSensor left", "");
                }
                direction = "right";
            } else if (direction.equals("right")) {
                while (colorSensor.getColor() == WHITE) {
                    rightMotor.setPower(0.3);
                    leftMotor.setPower(0.2);
                    telemetry.addData("colorSensor right", "");
                }
                direction = "left";
            }
            telemetry.addData("ultrasonic values", ultrasonic.getRangeCentimeters());
>>>>>>> 4944ab70686b3a08783f5ae8e326c89081ce825a
        }
        rightMotor.setPower(0);
        leftMotor.setPower(0);
    }
}
