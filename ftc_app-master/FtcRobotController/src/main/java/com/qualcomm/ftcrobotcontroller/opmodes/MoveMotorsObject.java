package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.robocol.Telemetry;
import static android.os.SystemClock.sleep;

public class MoveMotorsObject {
    DcMotor leftMotor;
    DcMotor rightMotor;
    HardwareMap hardwareMap = new HardwareMap();
    public void MoveMotorsObject () {
        leftMotor = hardwareMap.dcMotor.get("left_drive");
        rightMotor = hardwareMap.dcMotor.get("right_drive");
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