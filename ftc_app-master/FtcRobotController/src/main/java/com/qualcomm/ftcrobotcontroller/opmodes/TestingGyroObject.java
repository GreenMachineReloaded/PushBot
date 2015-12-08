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
        waitForStart();
        GyroObject degrees = new GyroObject(leftDriveMotorArg, rightDriveMotorArg, gyroArg);
        telemetry.addData("before start", gyroArg.getHeading());
        degrees.turnGyro(90, telemetry);

    }
}
