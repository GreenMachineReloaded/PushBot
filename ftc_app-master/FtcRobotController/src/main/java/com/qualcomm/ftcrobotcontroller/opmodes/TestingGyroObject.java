package com.qualcomm.ftcrobotcontroller.opmodes;
        import com.qualcomm.robotcore.hardware.DcMotor;
        import com.qualcomm.robotcore.hardware.GyroSensor;
        import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
public class TestingGyroObject extends LinearOpMode {
    Sleeper s;
    DcMotor leftDriveMotorArg;
    DcMotor rightDriveMotorArg;
    GyroSensor gyroArg;
    @Override
    public void runOpMode() throws InterruptedException {
        leftDriveMotorArg = hardwareMap.dcMotor.get("leftDriveMotor");
        rightDriveMotorArg = hardwareMap.dcMotor.get("rightDriveMotor");
        gyroArg = hardwareMap.gyroSensor.get("gyro");
        telemetry.addData("name stuff", "");
        s = new Sleeper();
        s.Sleep(5000);
        GyroObject degrees = new GyroObject(leftDriveMotorArg, rightDriveMotorArg, gyroArg);
        telemetry.addData("before start", "");
        waitForStart();
        telemetry.addData("start", "");
        degrees.turnGyro(90);
        telemetry.addData("turn 90", "");
        s.Sleep(1000);
        telemetry.addData("sleep", "");

        degrees.turnGyro(90);
        telemetry.addData("turn 90 2", "");
        s.Sleep(1000);
        telemetry.addData("sleep2", "");

        degrees.turnGyro(90);
        telemetry.addData("turn 90", "");
        s.Sleep(1000);
        telemetry.addData("sleep3", "");

        degrees.turnGyro(90);
        telemetry.addData("turn 90", "");
        s.Sleep(1000);
        telemetry.addData("sleep4", "");




        degrees.turnGyro(-90);
        telemetry.addData("turn -90 1", "");
        s.Sleep(1000);
        telemetry.addData("sleep5", "");

        degrees.turnGyro(-90);
        telemetry.addData("turn -90 2", "");
        s.Sleep(1000);
        telemetry.addData("sleep6", "");

        degrees.turnGyro(-90);
        telemetry.addData("turn -90 3", "");
        s.Sleep(1000);
        telemetry.addData("sleep7", "");

        degrees.turnGyro(-90);
        telemetry.addData("turn -90 4", "");
        s.Sleep(1000);
        telemetry.addData("sleep8", "");
    }
}
