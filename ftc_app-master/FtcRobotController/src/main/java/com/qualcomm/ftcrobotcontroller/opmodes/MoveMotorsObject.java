package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import static android.os.SystemClock.sleep;

public class MoveMotorsObject {
    DcMotor leftMotor;
    DcMotor rightMotor;



    public MoveMotorsObject (DcMotor leftMotorA, DcMotor rightMotorA) {
        leftMotor = leftMotorA;
        rightMotor = rightMotorA;
    }
    public void turnLeft() {
        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        leftMotor.setPower(-1.0);
        rightMotor.setPower(1.0);
        sleep(1000);
        leftMotor.setPower(0);
        rightMotor.setPower(0);
        }
    public void turnRight() {
        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        leftMotor.setPower(1.0);
        rightMotor.setPower(-1.0);
        sleep(1000);
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }
    public void moveForward() {
        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        leftMotor.setPower(1.0);
        rightMotor.setPower(1.0);
        sleep(1000);
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }
    public void moveBackward() {
        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        leftMotor.setPower(-1.0);
        rightMotor.setPower(-1.0);
        sleep(1000);
        leftMotor.setPower(0);
        rightMotor.setPower(0);

    }
}