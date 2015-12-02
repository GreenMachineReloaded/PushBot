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
        //gyro.resetZAxisIntegrator();
        if (degrees > 0) {
            //moves the motors

            while (degrees >= gyro.getHeading()) {
                leftMotor.setPower(-1);
                rightMotor.setPower(1);
            }
        }


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        else {
            while (degrees <= gyro.getHeading()) {
                //in here there needs to be an updater for the number of degrees the robot has turned.
                leftMotor.setPower(1);
                rightMotor.setPower(-1);
            }
        }
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }
}

