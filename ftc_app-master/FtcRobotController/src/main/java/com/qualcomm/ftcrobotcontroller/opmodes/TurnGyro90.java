package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;
public class TurnGyro90 extends LinearOpMode {
    boolean forever;
    GyroSensor gyro;
    Sleeper s;
    int heading;
    DcMotor leftMotor;
    DcMotor rightMotor;
    @Override
    public void runOpMode() throws InterruptedException {
        forever = true;
        s = new Sleeper();
        leftMotor = hardwareMap.dcMotor.get("leftDriveMotor");
        rightMotor = hardwareMap.dcMotor.get("rightDriveMotor");
        leftMotor.setDirection(DcMotor.Direction.REVERSE);
        rightMotor.setDirection(DcMotor.Direction.FORWARD);
        gyro = hardwareMap.gyroSensor.get("gyro");
        gyro.calibrate();
        s.Sleep(2000);
        heading = gyro.getHeading();
        s.Sleep(500);
        gyro.resetZAxisIntegrator();
        telemetry.addData("before start", heading);
        waitForStart();
        leftMotor.setPower(.25);
        rightMotor.setPower(-.25);
        while(heading <= 90) {
            s.Sleep(10);
            heading = gyro.getHeading();
            telemetry.addData("heading:", heading);
        }
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }
}
