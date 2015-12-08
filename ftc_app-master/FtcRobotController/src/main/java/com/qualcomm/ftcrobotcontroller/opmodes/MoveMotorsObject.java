package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.hardware.DcMotor;
public class MoveMotorsObject {
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //objects
    DcMotor leftMotor;
    DcMotor rightMotor;
    Sleeper s;
    //objects
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //constructor
    public MoveMotorsObject (DcMotor leftMotorArg, DcMotor rightMotorArg) {
        leftMotor = leftMotorArg;
        rightMotor = rightMotorArg;
        s = new Sleeper();
    }
    //constructor
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //move motors section

    public void turnRight(int sleep){
        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        leftMotor.setPower(-1.0);
        rightMotor.setPower(1.0);
        s.Sleep(sleep);
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }
    public void turnLeft(int sleep) {
        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        leftMotor.setPower(1.0);
        rightMotor.setPower(-1.0);
        s.Sleep(sleep);
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }
    public void moveBackward(int sleep) {
        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        leftMotor.setPower(-1.0);
        rightMotor.setPower(-1.0);
        s.Sleep(sleep);
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }
    public void moveForward(int sleep){
        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        leftMotor.setPower(1.0);
        rightMotor.setPower(1.0);
        s.Sleep(sleep);
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }
    //move motors section
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
