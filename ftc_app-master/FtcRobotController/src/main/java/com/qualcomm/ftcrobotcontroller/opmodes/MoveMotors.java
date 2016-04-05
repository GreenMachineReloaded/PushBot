package com.qualcomm.ftcrobotcontroller.opmodes;
import android.graphics.Color;

import com.kauailabs.navx.ftc.AHRS;
import com.kauailabs.navx.ftc.navXPIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.robocol.Telemetry;
import com.qualcomm.robotcore.util.ElapsedTime;

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
    GyroSensor gyro;
    GMROpticDistanceSensor opticSensor;
    int NAVX_DIM_I2C_PORT = 3;
    AHRS navx_device;
    navXPIDController yawPIDController;
    ElapsedTime runtime = new ElapsedTime();
    float turnDegrees;

    //objects
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //constructor
    public MoveMotors(ColorSensorObject cs, DcMotor rm, DcMotor lm, UltrasonicObject us, Telemetry telemetry, GyroSensor gyroInput, GMROpticDistanceSensor os, HardwareMap hardwareMap) {
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
        opticSensor = os;
        navx_device = AHRS.getInstance(hardwareMap.deviceInterfaceModule.get("DIM1"),
                NAVX_DIM_I2C_PORT,
                AHRS.DeviceDataType.kProcessedData);
    }
    //constructor
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //move motors section

    public void turnRight(int sleepTime, double motorPower) {
        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        leftMotor.setPower(-motorPower / 100);
        rightMotor.setPower(motorPower / 100);
        sleep.Sleep(sleepTime);
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }

    public void turnLeft(int sleepTime, double motorPower) {
        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        leftMotor.setPower(motorPower / 100);
        rightMotor.setPower(-motorPower / 100);
        sleep.Sleep(sleepTime);
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }

    public void moveForward(int sleepTime, double motorPower) {
        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        leftMotor.setPower(motorPower / 100);
        rightMotor.setPower(motorPower / 100);
        sleep.Sleep(sleepTime);
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }

    public void moveBackward(int sleepTime, double motorPower) {
        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        leftMotor.setPower(-motorPower / 100);
        rightMotor.setPower(-motorPower / 100);
        sleep.Sleep(sleepTime);
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }

    //move motors section
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //FollowLine
    public void traceALineClose() {
        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        t.addData("", "Trace a line start");
        t.addData("", ultrasonic.getRangeInches());
        while (opticSensor.getDistance() < 0.029) {
            t.addData("", lastDirection);

            if (!(colorSensor.getColor() == "gray")) {
                rightMotor.setPower(0.4);
                leftMotor.setPower(-0.2);
            } else if (colorSensor.getColor() == "gray") {
                rightMotor.setPower(-0.2);
                leftMotor.setPower(0.4);
            }

            //t.addData("Ultrasonic Range Inches ",ultrasonic.getRangeInches());
            //t.addData("Ultrasonic Range Centimeters",ultrasonic.getRangeCentimeters());
            //t.addData("Ultrasonic Range Value",ultrasonic.getRangeValue());

        }
        rightMotor.setPower(0);
        leftMotor.setPower(0);
    }

    //GyroObject
    public void gyroLeft(int degrees) {//GyroTurnLeft
        t.addData("", "");
        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        navx_device.zeroYaw();
        while (navx_device.isCalibrating()) {
            sleep.Sleep(50);
            t.addData("Gyro Is Calibrating", "");
        }
        //turnDegrees = (navx_device.getYaw() + degrees);
        while (degrees < navx_device.getYaw()) {
            leftMotor.setPower(0.3);
            rightMotor.setPower(-0.5);
            t.addData("Gyro Heading", navx_device.getYaw());
            t.addData("Goal Heading", degrees);
            if (degrees < navx_device.getYaw()) {
                t.addData("Turn In Progress", "");
            } else {
                t.addData("Turn (Should Be) Over", "");
            }
        }
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }

    public void gyroRight(int degrees) {//GyroTurnRight
        t.addData("", "");
        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        navx_device.zeroYaw();
        while (navx_device.isCalibrating()) {
            sleep.Sleep(50);
            t.addData("Gyro Is Calibrating", "");
        }
        //turnDegrees = (navx_device.getYaw() + degrees);
        while (degrees > navx_device.getYaw()) {
            leftMotor.setPower(-0.5);
            rightMotor.setPower(0.3);
            t.addData("Gyro Heading", navx_device.getYaw());
            t.addData("Goal Heading", degrees);
            if (degrees > navx_device.getYaw()) {
                t.addData("Right Turn In Progress", "");
            } else {
                t.addData("Right Turn (Should Be) Over", "");
            }
        }
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }

    public float getPosition() {
        return navx_device.getYaw();
    }

    public double getTime() {
        return runtime.time();
    }

    public void resetPosition() {
        navx_device.zeroYaw();
    }

    public String AccurateColor() {
        Integer accurateRed = 0;
        Integer accurateBlue = 0;
        Integer accurateGreen = 0;
        Integer timesTried = 0;
        while (timesTried <= 10) {
            accurateRed=accurateRed+colorSensor.red();
            timesTried++;
            accurateBlue=accurateBlue+colorSensor.blue();
            timesTried++;
            accurateGreen=accurateGreen+colorSensor.green();
                    }
        accurateRed=accurateRed/10;
        accurateBlue=accurateBlue/10;
        accurateGreen=accurateGreen/10;
        float hsvValues[] = {0,0,0};
        String returnColor = "gray";// when return color is asked, it will say GRAY unless otherwise instructed to later

        t.addData("Start Robot","");
        Color.RGBToHSV(accurateRed * 8, accurateGreen * 8, accurateBlue * 8, hsvValues);

        if ((accurateGreen>=0) && (accurateBlue>=2) && (accurateRed<=0)) {
            t.addData("CadwynBlue", accurateBlue);
            t.addData("CadwynBlue!","");
            returnColor = "blue";

        }
        else if ((accurateGreen<=0) && (accurateBlue<=0) && (accurateRed>=2)){
            t.addData("CadwynRed", accurateRed);
            t.addData("CadwynRed!","");
            returnColor = "red";
        }
        else if ((accurateGreen>=2) && (accurateBlue>=0) && (accurateRed<=0)){
            t.addData("CadwynGreen", accurateGreen);
            returnColor = "green";
        }
        else if (accurateGreen>=2 && accurateBlue>=2 && accurateRed>=2){
            returnColor = "white";
        }
        else if ((accurateGreen<=1 && accurateBlue<=1 && accurateRed<=1)){
            returnColor = "gray";

        }
        return returnColor;
    }
}
