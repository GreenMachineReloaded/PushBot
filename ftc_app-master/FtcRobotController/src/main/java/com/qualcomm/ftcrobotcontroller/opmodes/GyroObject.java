package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
public class GyroObject {

    DcMotor leftMotor;
    DcMotor rightMotor;
    GyroSensor gyro;
    Sleeper s;

    public GyroObject(DcMotor leftMotorArg, DcMotor rightMotorArg, GyroSensor gyroArg) {
        gyro = gyroArg;
        leftMotor = leftMotorArg;
        rightMotor = rightMotorArg;
        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        s = new Sleeper();
        gyro.calibrate();
        s.Sleep(1000);
    }
    public void turnGyro(int degrees) {
        int forward;
        if (degrees > 0) {
            //moves the motors
            do {
                //in here there needs to be an updater for the number of degrees the robot has turned.
                forward = gyro.rawY();
                if (degrees > forward) {
                    leftMotor.setPower(-1);
                    rightMotor.setPower(1);
                } else if (degrees < forward) {
                    leftMotor.setPower(1);
                    rightMotor.setPower(-1);
                } else {
                }
            } while (degrees != forward);
            //stops the motors
            leftMotor.setPower(0);
            rightMotor.setPower(0);
        }


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        else if (degrees < 0) {
            do {
                //in here there needs to be an updater for the number of degrees the robot has turned.
                forward = gyro.rawY();
                if (degrees > forward) {
                    leftMotor.setPower(1);
                    rightMotor.setPower(-1);
                } else if (degrees < forward) {
                    leftMotor.setPower(-1);
                    rightMotor.setPower(1);
                } else {
                }
            } while (degrees != forward);
            //stops the motors
            leftMotor.setPower(0);
            rightMotor.setPower(0);
        } else {
            leftMotor.setPower(0);
            rightMotor.setPower(0);
        }
    }
}

