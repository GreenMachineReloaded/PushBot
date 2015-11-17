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
    public void GryoObject(double d)
    {
        gyro = hardwareMap.gyroSensor.get("gyro");
        leftMotor = hardwareMap.dcMotor.get("left_drive");
        rightMotor = hardwareMap.dcMotor.get("right_drive");
        setGyroObject(d);

    }

    public void setGyroObject(double degrees) {
        int x, z;
        x = gyro.rawX();
        z = gyro.rawZ();
        leftMotor.setDirection(DcMotor.Direction.REVERSE);
        rightMotor.setDirection(DcMotor.Direction.FORWARD);
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
