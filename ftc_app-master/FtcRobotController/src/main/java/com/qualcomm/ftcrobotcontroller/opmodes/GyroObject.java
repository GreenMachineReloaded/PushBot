package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.robocol.Telemetry;
import java.io.*;

public class GyroObject {


    DcMotor leftMotor;
    DcMotor rightMotor;
    GyroSensor gyro;
    Sleeper s;
    Telemetry t;
    PrintWriter gyroFile;
    int turnDegrees;
    public GyroObject(DcMotor leftMotorArg, DcMotor rightMotorArg, GyroSensor gyroArg,Telemetry telemetry) throws InterruptedException {
        gyro = gyroArg;
        leftMotor = leftMotorArg;
        rightMotor = rightMotorArg;
        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        s = new Sleeper();
        t = telemetry;
        gyro.calibrate();
        turnDegrees = 0;
        //t.addData("Gyro Calibration Complete","");
//        try {
//            PrintWriter gyroFile = new PrintWriter("Gyro-File.txt");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
    }

    public GyroObject(GyroSensor gyroArg) throws InterruptedException {
        gyro = gyroArg;
        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        s = new Sleeper();
        gyro.calibrate();
        //t.addData("Gyro Calibration Complete","");
    }

    public void leftTurn(int degrees) {
        while (gyro.isCalibrating()) {
            s.Sleep(50);
        }
        turnDegrees = (gyro.getHeading() - degrees);
        if (turnDegrees < 0) {
            turnDegrees = (359 - degrees);
        }
        while (turnDegrees <= gyro.getHeading()|| gyro.getHeading() == 0) {
            leftMotor.setPower(0.3);
            rightMotor.setPower(-0.3);
            t.addData("Gyro heading", gyro.getHeading());
        }
        leftMotor.setPower(0);
        rightMotor.setPower(0);
        //gyro.resetZAxisIntegrator();
    }

    public void rightTurn (int degrees) {
        while (gyro.isCalibrating()) {
            s.Sleep(50);
        }
        turnDegrees = (gyro.getHeading() + degrees);
        if (turnDegrees > 359) {
            turnDegrees = (turnDegrees - 359);
        }
        while (turnDegrees >= gyro.getHeading()) {
            leftMotor.setPower(-0.3);
            rightMotor.setPower(0.3);
            t.addData("Gyro Heading", gyro.getHeading());
        }
        //s.Sleep(10);
        leftMotor.setPower(0);
        rightMotor.setPower(0);
        //gyro.resetZAxisIntegrator();
    }

    public int getPosition() {
        while (gyro.isCalibrating()) {
            s.Sleep(50);
        }
        return gyro.getHeading();
    }

//    public void turnGyro(int degrees, Telemetry t) {
//        t.addData("przei", gyro.getHeading());
//        gyro.resetZAxisIntegrator();
//        t.addData("pozei", gyro.getHeading());
//        if (degrees > 0) {
//            //moves the motors
//
//            while (degrees >= gyro.getHeading()) {
//                leftMotor.setPower(-0.5);
//                rightMotor.setPower(0.5);
//                t.addData("+h", gyro.getHeading());
//            }
//        }
//
//
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//
//        else {
//            while (degrees <= gyro.getHeading()) {
//                //in here there needs to be an updater for the number of degrees the robot has turned.
//                leftMotor.setPower(0.5);
//                rightMotor.setPower(-0.5);
//                t.addData("-h", gyro.getHeading());
//            }
//        }
//        leftMotor.setPower(0);
//        rightMotor.setPower(0);
//        t.addData("end", gyro.getHeading());
//    }
}


