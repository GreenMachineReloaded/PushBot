package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;

public class SonicTest extends LinearOpMode {
    boolean forever = true;
    Sleeper s;
    DcMotor leftMotor;
    DcMotor rightMotor;
    UltrasonicSensor USS;
    double stop;
    @Override
    public void runOpMode() throws InterruptedException {
        s = new Sleeper();
        leftMotor = hardwareMap.dcMotor.get("leftDriveMotor");
        rightMotor = hardwareMap.dcMotor.get("rightDriveMotor");
        leftMotor.setDirection(DcMotor.Direction.REVERSE);
        rightMotor.setDirection(DcMotor.Direction.FORWARD);
        USS = hardwareMap.ultrasonicSensor.get("USS");
        waitForStart();
        while(true) {
            stop = USS.getUltrasonicLevel();
            telemetry.addData("USS reading", stop);
            if(stop < .5) {
                leftMotor.setPower(-.50);
                rightMotor.setPower(-.50);
            }
            if(stop > .5) {
                leftMotor.setPower(0);
                rightMotor.setPower(0);
            }
        }
    }
}
