package com.qualcomm.ftcrobotcontroller.opmodes;
        import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;
public class TestingGyroObject extends LinearOpMode {
    Sleeper s;
    DcMotor leftDriveMotorArg;
    DcMotor rightDriveMotorArg;
    GyroSensor gyroArg;
    //int count = 0;
    @Override
    public void runOpMode() throws InterruptedException {
        leftDriveMotorArg = hardwareMap.dcMotor.get("leftDriveMotor");
        rightDriveMotorArg = hardwareMap.dcMotor.get("rightDriveMotor");
        gyroArg = hardwareMap.gyroSensor.get("gyro");
        //count++;
        //telemetry.addData("the number being counted: ",count);
        s = new Sleeper();
        GyroObject gyroTurn = new GyroObject(leftDriveMotorArg, rightDriveMotorArg, gyroArg, telemetry);
        waitForStart();
        //telemetry.addData("before start", gyroArg.getHeading());
        //leftDriveMotorArg.setPower(-0.25);
        //rightDriveMotorArg.setPower(-0.25);
        //sleep((long) 2);
        gyroTurn.gyroRightTurn(90);
        //sleep((long) 2);
        //leftDriveMotorArg.setPower(-0.2);
        //rightDriveMotorArg.setPower(-0.2);
        //sleep((long) 2);
        //leftDriveMotorArg.setPower(0);
        //rightDriveMotorArg.setPower(0);
        telemetry.addData("Victory! (Fanfare)","");


    }
}
