package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.robocol.Telemetry;

public class GMRGyro {


    DcMotor leftMotor;
    DcMotor rightMotor;
    GyroSensor gyro;
    Sleeper s;
    Telemetry t;
    int turnDegrees;

    public GMRGyro(DcMotor lm, DcMotor rm, GyroSensor gyroInput, Telemetry telemetry) throws InterruptedException {
        gyro = gyroInput;
        leftMotor = lm;
        rightMotor = rm;
        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        s = new Sleeper();
        t = telemetry;
        gyro.calibrate();
        turnDegrees = 0;
    }

    public GMRGyro(GyroSensor gyroMap) throws InterruptedException {
        gyro = gyroMap;
        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        s = new Sleeper();
        gyro.calibrate();
        //t.addData("Gyro Calibration Complete","");
    }

    public void leftTurn(int degrees) {//GyroTurnLeft
        while (gyro.isCalibrating()) {
            s.Sleep(50);
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
            s.Sleep(1000);// was 50
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
            s.Sleep(50);
        }
        return gyro.getHeading();
    }
}
