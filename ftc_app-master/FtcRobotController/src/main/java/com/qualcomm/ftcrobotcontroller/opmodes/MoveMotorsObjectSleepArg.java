package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
public class MoveMotorsObjectSleepArg extends LinearOpMode{
    DcMotor leftMotor;
    DcMotor rightMotor;
    @Override
    public void runOpMode () {
    }
    public void MoveMotorsObjectSleepArg (int sleep) throws InterruptedException {
        int sleepSecond = sleep / 1000;
        leftMotor = hardwareMap.dcMotor.get("left_drive");
        rightMotor = hardwareMap.dcMotor.get("right_drive");
        turnLeft(sleepSecond);
        turnRight(sleepSecond);
        moveBackward(sleepSecond);
        moveForward(sleepSecond);
    }
    public void turnLeft(int sleep) throws InterruptedException {
        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        leftMotor.setPower(-1.0);
        rightMotor.setPower(1.0);
        sleep(sleep);
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }
    public void turnRight(int sleep) throws InterruptedException {
        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        leftMotor.setPower(1.0);
        rightMotor.setPower(1.0);
        sleep(sleep);
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }
    public void moveForward(int sleep) throws InterruptedException{
        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        leftMotor.setPower(1.0);
        rightMotor.setPower(1.0);
        sleep(sleep);
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }
    public void moveBackward(int sleep) throws InterruptedException {
        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        leftMotor.setPower(-1.0);
        rightMotor.setPower(-1.0);
        sleep(sleep);
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }
}