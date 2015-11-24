package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
public class GyroObject extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
    }

    DcMotor leftMotor;
    DcMotor rightMotor;
    GyroSensor gyro;
    public void GryoObject(DcMotor leftMotorArg, DcMotor rightMotorArg, GyroSensor gyroArg)
    {
        gyro = gyroArg;
        leftMotor = leftMotorArg;
        rightMotor = rightMotorArg;
    }

    public void turnGyro(double degrees) {
        int x, z;
        x = gyro.rawX();
        z = gyro.rawZ();
        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        if (degrees > 0) {
            //moves the motors
            leftMotor.setPower(1);
            rightMotor.setPower(-1);
            do{
                //in here there needs to be an updater for the number of degrees the robot has turned.
                x = gyro.rawX();
                z = gyro.rawZ();
            }while (degrees == 0);
            //stops the motors
            leftMotor.setPower(0);
            rightMotor.setPower(0);
        } else if (degrees < 0) {
            //moves the motors
            leftMotor.setPower(-1);
            rightMotor.setPower(1);
            do {
                //in here there needs to be an updater for the number of degrees the robot has turned.
                x = gyro.rawX();
                z = gyro.rawZ();
            }while (degrees == 0);
            //stops the motors
            leftMotor.setPower(0);
            rightMotor.setPower(0);
        } else {
            //the code here should auto correct itself.
            leftMotor.setPower(1);
            rightMotor.setPower(1);
            x = gyro.rawX();
            z = gyro.rawZ();
            if (z < 0) {
                leftMotor.setPower(1);
                rightMotor.setPower(.5);
            }
            if (z > 0) {
                leftMotor.setPower(.5);
                rightMotor.setPower(1);
            }
        }
    }
}
