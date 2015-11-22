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
    public MoveMotorsObject (DcMotor leftMotorArg, DcMotor rightMotorArg, int sleep) {
        leftMotor = leftMotorArg;
        rightMotor = rightMotorArg;
        s = new Sleeper();
        turnLeft();
        turnRight();
        moveBackward();
        moveForward();
        stop();
        turnLeftSleep(sleep);
        turnRightSleep(sleep);
        moveBackwardSleep(sleep);
        moveForwardSleep(sleep);
    }
    //constructor
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //move motors section
    public void turnLeft() {
        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        leftMotor.setPower(-1.0);
        rightMotor.setPower(1.0);
        }
    public void turnRight() {
        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        leftMotor.setPower(1.0);
        rightMotor.setPower(-1.0);
    }
    public void moveForward() {
        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        leftMotor.setPower(1.0);
        rightMotor.setPower(1.0);
    }
    public void moveBackward() {
        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        leftMotor.setPower(-1.0);
        rightMotor.setPower(-1.0);
    }
    public void stop() {
        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }
    //move motors section
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //sleep move motors section

    public void turnLeftSleep(int sleep){
        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        leftMotor.setPower(-1.0);
        rightMotor.setPower(1.0);
        s.Sleep(sleep);
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }
    public void turnRightSleep(int sleep) {
        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        leftMotor.setPower(-1.0);
        rightMotor.setPower(1.0);
        s.Sleep(sleep);
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }
    public void moveBackwardSleep(int sleep) {
        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        leftMotor.setPower(1.0);
        rightMotor.setPower(1.0);
        s.Sleep(sleep);
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }
    public void moveForwardSleep(int sleep){
        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        leftMotor.setPower(1.0);
        rightMotor.setPower(1.0);
        s.Sleep(sleep);
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }
    //sleep move motors section
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
