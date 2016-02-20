package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.hardware.DcMotor;
public class MoveMotors {
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //objects
    DcMotor leftMotor;
    DcMotor rightMotor;
    Sleeper s;
    //objects
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //constructor
    public MoveMotors(DcMotor leftMotorArg, DcMotor rightMotorArg) {
        leftMotor = leftMotorArg;
        rightMotor = rightMotorArg;
        s = new Sleeper();
    }
    //constructor
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //move motors section

    public void turnRight(int sleep, double motorPower){
        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        leftMotor.setPower(-motorPower/100);
        rightMotor.setPower(motorPower/100);
        s.Sleep(sleep);
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }
    public void turnLeft(int sleep, double motorPower) {
        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        leftMotor.setPower(motorPower/100);
        rightMotor.setPower(-motorPower/100);
        s.Sleep(sleep);
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }
    public void moveForward(int sleep, double motorPower) {
        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        leftMotor.setPower(-motorPower/100);
        rightMotor.setPower(-motorPower/100);
        s.Sleep(sleep);
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }
    public void moveBackward(int sleep, double motorPower){
        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        leftMotor.setPower(motorPower/100);
        rightMotor.setPower(motorPower/100);
        s.Sleep(sleep);
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }
    //move motors section
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
