package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;
public class GyroObjectTest {
    DcMotor leftMotor;
    DcMotor rightMotor;
    GyroSensor gyro;
    Sleeper s;
    int heading;
    public GyroObjectTest(DcMotor leftMotorArg, DcMotor rightMotorArg, GyroSensor gyroArg) {
        gyro = gyroArg;
        leftMotor = leftMotorArg;
        rightMotor = rightMotorArg;
        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        s = new Sleeper();
        gyro.calibrate();
    }

    public void GyroTurn(int degrees) {
        gyro.resetZAxisIntegrator();
        if(degrees > 0) {
            leftMotor.setPower(.25);
            rightMotor.setPower(-.25);
            while (heading <= degrees) {
                s.Sleep(10);
                heading = gyro.getHeading();
            }
        }
        if(degrees < 0) {
            degrees = 359 + degrees;
            leftMotor.setPower(-.25);
            rightMotor.setPower(.25);
            while (heading <= degrees) {
                s.Sleep(10);
                heading = gyro.getHeading();
            }
        }
        leftMotor.setPower(0);
        rightMotor.setPower(0);
        gyro.resetZAxisIntegrator();
        }
    }




