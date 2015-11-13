package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;


/**
 * Created by Amber on 11/10/2015.
 */
public class MoveMotorsObject extends LinearOpMode{
    DcMotor leftMotor;
    DcMotor rightMotor;


    //     Agein I believe this is right but I am not sure, I know there are error above sorry about that, I don't know how to fix them.



    @Override
    public void runOpMode () {

    }
    public void MoveMotorsObject () throws InterruptedException {
        leftMotor = hardwareMap.dcMotor.get("left_drive");
        rightMotor = hardwareMap.dcMotor.get("right_drive");
    }

    // names of the motors
    public void turnLeft() throws InterruptedException {
        leftMotor.setDirection(DcMotor.Direction.FORWARD);
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
    public void turnRight() throws InterruptedException {
        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        leftMotor.setPower(1.0);
        rightMotor.setPower(1.0);
        //turns the robot right
        sleep(1000);
        //waits while the motors move
        leftMotor.setPower(0);
        rightMotor.setPower(0);
        //stops the motors
    }
    public void moveForward() throws InterruptedException{
        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        leftMotor.setPower(1.0);
        rightMotor.setPower(1.0);
        //makes the robot move forward
        sleep(1000);
        //waits while the motors move
        leftMotor.setPower(0);
        rightMotor.setPower(0);
        //stops the motors
    }
    public void moveBackward() throws InterruptedException {
        leftMotor.setDirection(DcMotor.Direction.FORWARD);
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
    public void stopMotors() {
        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        leftMotor.setPower(0);
        rightMotor.setPower(0);
        //stops the motors if the motors are still being used
    }
}
