package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.robocol.Telemetry;

public class MoveMotors {
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //objects
    DcMotor leftMotor;
    DcMotor rightMotor;
    Sleeper sleep;
    ColorSensorObject colorSensor;
    UltrasonicObject ultrasonic;
    Telemetry t;
    String lastDirection;
    MoveMotors mm;
    GyroSensor gyro;
    int turnDegrees;
    //objects
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //constructor
    public MoveMotors(ColorSensorObject cs, DcMotor rm, DcMotor lm, UltrasonicObject us, Telemetry telemetry, GyroSensor gyroInput) {
        colorSensor = cs;
        rightMotor = rm;
        leftMotor = lm;
        ultrasonic = us;
        t = telemetry;
        sleep = new Sleeper();
        lastDirection = "left";
        gyro = gyroInput;
        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        gyro.calibrate();
        turnDegrees = 0;
    }
    //constructor
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //move motors section

    public void turnRight(int sleepTime, double motorPower){
        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        leftMotor.setPower(-motorPower/100);
        rightMotor.setPower(motorPower/100);
        sleep.Sleep(sleepTime);
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }
    public void turnLeft(int sleepTime, double motorPower) {
        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        leftMotor.setPower(motorPower/100);
        rightMotor.setPower(-motorPower/100);
        sleep.Sleep(sleepTime);
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }
    public void moveForward(int sleepTime, double motorPower) {
        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        leftMotor.setPower(-motorPower/100);
        rightMotor.setPower(-motorPower/100);
        sleep.Sleep(sleepTime);
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }
    public void moveBackward(int sleepTime, double motorPower){
        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        leftMotor.setPower(motorPower/100);
        rightMotor.setPower(motorPower/100);
        sleep.Sleep(sleepTime);
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }
    //move motors section
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //FollowLine
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
//GyroObject
      public void leftTurn(int degrees) {//GyroTurnLeft
        while (gyro.isCalibrating()) {
            sleep.Sleep(50);
        }
        degrees-=8;
        turnDegrees = (gyro.getHeading() - degrees);
        if (turnDegrees < 0) {
            turnDegrees = (degrees - gyro.getHeading());
            turnDegrees = (359 - turnDegrees);
        }
        while (turnDegrees <= gyro.getHeading() || gyro.getHeading() == 0) {
            leftMotor.setPower(0.4);
            rightMotor.setPower(-0.45);
            t.addData("Gyro heading", gyro.getHeading());
        }
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }

    public void rightTurn(int degrees) {//GyroTurnRight
        while (gyro.isCalibrating()) {
            sleep.Sleep(50);
        }
        turnDegrees = (gyro.getHeading() + degrees);
        if (turnDegrees > 359) {
            turnDegrees = (turnDegrees - 359);
        }
        while (turnDegrees >= gyro.getHeading()) {
            leftMotor.setPower(-0.4);
            rightMotor.setPower(0.4);
            t.addData("Gyro Heading", gyro.getHeading());
        }
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }

    public int getPosition() {
        while (gyro.isCalibrating()) {
            sleep.Sleep(50);
        }
        return gyro.getHeading();
    }
}