package com.qualcomm.ftcrobotcontroller.opmodes;
import android.graphics.Color;

import com.kauailabs.navx.ftc.AHRS;
import com.kauailabs.navx.ftc.navXPIDController;
import com.qualcomm.ftcrobotcontroller.opmodes.drivers.GMRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
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

    GMRServo leftFlapperServo;
    GMRServo rightFlapperServo;
    GMRServo climberDepositerServo;
    GMRServo winchServo;
    GMRServo hopperDoorBlue;
    GMRServo hopperDoorRed;
    GMRServo hopperEntranceDoor;
    GMRServo sweeperLift;
    GMRServo sweeperHold;
    GMRServo colorServo;

    Servo servo1;
    Servo servo2;
    Servo servo3;
    Servo servo4;
    Servo servo5;
    Servo servo6;
    Servo servo7;
    Servo servo8;
    Servo servo9;
    Servo servo10;

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
        opticSensor = os;
        navx_device = AHRS.getInstance(hardwareMap.deviceInterfaceModule.get("DIM1"),
                NAVX_DIM_I2C_PORT,
                AHRS.DeviceDataType.kProcessedData);
        leftFlapperServo = new GMRServo(servo1 = hardwareMap.servo.get("leftFlapperServo"));
        rightFlapperServo = new GMRServo(servo2 = hardwareMap.servo.get("rightFlapperServo"));
        climberDepositerServo = new GMRServo(servo3 = hardwareMap.servo.get("climberDepositerServo"));
        winchServo = new GMRServo(servo4 = hardwareMap.servo.get("winchServo"));
        hopperDoorRed = new GMRServo(servo5 = hardwareMap.servo.get("hopperDoorRed"));
        hopperDoorBlue = new GMRServo(servo6 = hardwareMap.servo.get("hopperDoorBlue"));
        hopperEntranceDoor = new GMRServo(servo7 = hardwareMap.servo.get("hopperEntranceDoor"));
        sweeperLift = new GMRServo(servo8 = hardwareMap.servo.get("sweeperLift"));
        sweeperHold = new GMRServo(servo9 = hardwareMap.servo.get("sweeperHold"));
        colorServo = new GMRServo(servo10 = hardwareMap.servo.get("colorServo"));
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
    public void gyroLeft(double degrees) {//GyroTurnLeft
        double goalDegrees = (navx_device.getYaw() - degrees);
        t.addData("Turn Start", "");
        sleep.Sleep(50);
        t.addData("Degrees Calculated", "");
        while (goalDegrees < navx_device.getYaw()) {
            leftMotor.setPower(-0.5);
            rightMotor.setPower(0.3);
            sleep.Sleep(10);
            t.addData("Gyro Heading", navx_device.getYaw());
            t.addData("Goal Heading", goalDegrees);
//            if (-degrees < navx_device.getYaw()) {
//                t.addData("Turn In Progress", "");
//            } else {
//                t.addData("Turn (Should Be) Over", "");
//            }
        }
        t.addData("Turn Over", "");
        leftMotor.setPower(0);
        rightMotor.setPower(0);
        t.addData("Motor Power Zeroed", "");
    }

    public void gyroRight(double degrees) {//GyroTurnRight
        double goalDegrees = (navx_device.getYaw() + degrees);
        t.addData("Turn Start", "");
        sleep.Sleep(50);
        t.addData("Degrees Calculated", "");
        while (goalDegrees > navx_device.getYaw()) {
            t.addData("Goal Heading", degrees);
            leftMotor.setPower(-0.5);
            rightMotor.setPower(0.3);
            sleep.Sleep(10);
            t.addData("Gyro Heading", navx_device.getYaw());
//            if (-degrees < navx_device.getYaw()) {
//                t.addData("Turn In Progress", "");
//            } else {
//                t.addData("Turn (Should Be) Over", "");
//            }
        }
        t.addData("Turn Over", "");
        leftMotor.setPower(0);
        rightMotor.setPower(0);
        t.addData("Motor Power Zeroed", "");
    }

    public double getYaw() {return navx_device.getYaw();}

    public double getRoll() {return navx_device.getRoll();}

    public double getPitch() {return navx_device.getPitch();}

    public double getTime() {return runtime.time();}

    public double getDistance() {return opticSensor.getDistance();}

    public void resetPosition() {
        navx_device.zeroYaw();
    }

    public String accurateColor() {
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

        if ((accurateGreen>=1) && (accurateBlue>=8) && (accurateRed<=0)) {
            t.addData("CadwynBlue", accurateBlue);
            t.addData("CadwynBlue!","");
            returnColor = "blue";

        }
        else if ((accurateGreen<=0) && (accurateBlue<=0) && (accurateRed>=10)){
            t.addData("CadwynRed", accurateRed);
            t.addData("CadwynRed!","");
            returnColor = "red";
        }
        else if ((accurateGreen>=8) && (accurateBlue>=1) && (accurateRed<=0)){
            t.addData("CadwynGreen", accurateGreen);
            returnColor = "green";
        }
        else if (accurateGreen>=45 && accurateBlue>=45 && accurateRed>=50){
            returnColor = "white";
        }
        else if ((accurateGreen<=4 && accurateBlue<=4 && accurateRed<=4)){
            returnColor = "gray";

        }
        return returnColor;
    }

    public void setServosAuto() {
        rightFlapperServo.moveServo(1);
        leftFlapperServo.moveServo(0);
        climberDepositerServo.moveServo(0);
        winchServo.moveServo(1);
        hopperDoorRed.moveServo(0.64);
        hopperDoorBlue.moveServo(0.03);
        hopperEntranceDoor.moveServo(0.7);
        sweeperLift.moveServo(1);
        sweeperHold.moveServo(0);
        colorServo.moveServo(0.92);
    }
    public void setServosTele() {
        rightFlapperServo.moveServo(1);
        leftFlapperServo.moveServo(0);
        climberDepositerServo.moveServo(0);
        winchServo.moveServo(1);
        hopperDoorRed.moveServo(0.64);
        hopperDoorBlue.moveServo(0.03);
        hopperEntranceDoor.moveServo(0.7);
        sweeperLift.moveServo(1);
        sweeperHold.moveServo(0);
        colorServo.moveServo(0.62);
    }

    public void moveServo (String servoName, double servoPosition) {
        if (servoName == "rightFlapperServo") {
            rightFlapperServo.moveServo(servoPosition);
        } else if (servoName == "leftFlapperServo") {
            leftFlapperServo.moveServo(servoPosition);
        } else if (servoName == "climberDepositerServo") {
            climberDepositerServo.moveServo(servoPosition);
        } else if (servoName == "winchServo") {
            winchServo.moveServo(servoPosition);
        } else if (servoName == "hopperDoorRed") {
            hopperDoorRed.moveServo(servoPosition);
        } else if (servoName == "hopperDoorBlue") {
            hopperDoorBlue.moveServo(servoPosition);
        } else if (servoName == "hopperEntranceDoor") {
            hopperEntranceDoor.moveServo(servoPosition);
        } else if (servoName == "sweeperLift") {
            sweeperLift.moveServo(servoPosition);
        } else if (servoName == "sweeperHold") {
            sweeperHold.moveServo(servoPosition);
        } else if (servoName == "colorServo") {
            colorServo.moveServo(servoPosition);
        }
    }
}
