package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class MoveMotorsObjectSleepArg extends LinearOpMode{
    DcMotor leftMotor1;
    DcMotor rightMotor1;
    @Override
    public void runOpMode () {
    }
    public void MoveMotorsObjectSleepArg (int sleep) throws InterruptedException {
        HardwareMap hardwareMap = new HardwareMap();

        turnLeft(sleep);
        turnRight(sleep);
        moveBackward(sleep);
        moveForward(sleep);
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