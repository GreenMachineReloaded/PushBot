package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;


/**
 * Created by Amber on 11/10/2015.
 */
public class MoveMotorsObject extends LinearOpMode{
    @Override
    public void runOpMode () {

    }
    public void MoveMotorsObject () throws InterruptedException {
        TurnLeft(leftMotor = hardwareMap.dcMotor.get("left_drive"); , rightMotor = hardwareMap.dcMotor.get("right_drive"););
        //turns left
        TurnRight(leftMotor = hardwareMap.dcMotor.get("left_drive"); , rightMotor = hardwareMap.dcMotor.get("right_drive"););
        //turns right
        MoveBackward(leftMotor = hardwareMap.dcMotor.get("left_drive"); , rightMotor = hardwareMap.dcMotor.get("right_drive"););
        //moves backwards
        MoveForward(leftMotor = hardwareMap.dcMotor.get("left_drive"); , rightMotor = hardwareMap.dcMotor.get("right_drive"););
        //moves forwards
        StopMotors(leftMotor = hardwareMap.dcMotor.get("left_drive"); , rightMotor = hardwareMap.dcMotor.get("right_drive"););
        //stops, but only when motors are already in use
    }
    DcMotor leftMotor;
    DcMotor rightMotor;
    // names of the motors
    public void TurnLeft() throws InterruptedException {
        //finds the motors
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        //reverses one of the motors
        leftMotor.setPower(-1.0);
        rightMotor.setPower(1.0);
        //turns the robot left
        sleep(1000);
        //waits while the motors move
        leftMotor.setPower(0);
        rightMotor.setPower(0);
        //stops the motors


            }
    public void TurnRight() throws InterruptedException {
        //finds the motors
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        //reverses one of the motors
        leftMotor.setPower(1.0);
        rightMotor.setPower(-1.0);
        //turns the robot right
        sleep(1000);
        //waits while the motors move
        leftMotor.setPower(0);
        rightMotor.setPower(0);
        //stops the motors
    }
    public void MoveForward() throws InterruptedException{
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        //reverses one of the motors
        leftMotor.setPower(1.0);
        rightMotor.setPower(1.0);
        //makes the robot move forward
        sleep(1000);
        //waits while the motors move
        leftMotor.setPower(0);
        rightMotor.setPower(0);
        //stops the motors
    }
    public void MoveBackward() throws InterruptedException {
        //finds the motors
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        //reverses one of the motors
        leftMotor.setPower(-1.0);
        rightMotor.setPower(-1.0);
        //makes the robot move backwards
        sleep(1000);
        //waits while the motors move
        leftMotor.setPower(0);
        rightMotor.setPower(0);
        //stops the motors
    }
    public void StopMotors() {
        //finds the motors and because it dosent matter which way it is going I do not need to reverse the code
        leftMotor.setPower(0);
        rightMotor.setPower(0);
        //stops the motors if the motors are still being used
    }
}
