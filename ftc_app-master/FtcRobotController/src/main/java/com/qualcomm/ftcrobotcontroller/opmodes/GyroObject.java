package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.robocol.Telemetry;

public class GyroObject {


    DcMotor leftMotor;
    DcMotor rightMotor;
    GyroSensor gyro;
    Sleeper s;
    Telemetry t;
    public GyroObject(DcMotor leftMotorArg, DcMotor rightMotorArg, GyroSensor gyroArg,Telemetry telemetry) throws InterruptedException {
        gyro = gyroArg;
        leftMotor = leftMotorArg;
        rightMotor = rightMotorArg;
        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        s = new Sleeper();
        t = telemetry;
        gyro.calibrate();
        t.addData("Gyro Calibration Complete","");
    }

    public void leftTurn(int degrees) {
        while (gyro.isCalibrating()) {
            s.Sleep(50);
        }
        int leftTurnDegrees = 359 - degrees;
        while (leftTurnDegrees > gyro.getHeading()) {
            leftMotor.setPower(0.5);
            rightMotor.setPower(-0.5);
            t.addData("Gyro heading", leftTurnDegrees);

        }
        s.Sleep(10);
        leftMotor.setPower(0);
        rightMotor.setPower(0);
        gyro.resetZAxisIntegrator();
        t.addData("the gyro sensor is",gyro.getHeading());
    }

    public void rightTurn (int degrees) {
        while (gyro.isCalibrating()) {
            s.Sleep(50);
        }
        while (degrees <= gyro.getHeading()) {
            leftMotor.setPower(-0.5);
            rightMotor.setPower(0.5);
            t.addData("Gyro Heading", gyro.getHeading());
        }
        s.Sleep(10);
        leftMotor.setPower(0);
        rightMotor.setPower(0);
        gyro.resetZAxisIntegrator();
        t.addData("Gyro Heading", gyro.getHeading());
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


