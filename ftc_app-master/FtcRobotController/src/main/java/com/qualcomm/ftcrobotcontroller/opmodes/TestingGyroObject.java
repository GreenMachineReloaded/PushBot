package com.qualcomm.ftcrobotcontroller.opmodes;
        import com.qualcomm.robotcore.hardware.DcMotor;
        import com.qualcomm.robotcore.hardware.GyroSensor;
        import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
public class TestingGyroObject extends LinearOpMode {
    Sleeper s;
    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor leftDriveMotor = hardwareMap.dcMotor.get("leftDriveMotor");
        DcMotor rightDriveMotor = hardwareMap.dcMotor.get("rightDriveMotor");
        GyroSensor gyro = hardwareMap.gyroSensor.get("gyro");
        waitForStart();
        GyroObject degrees = new GyroObject(leftDriveMotor, rightDriveMotor, gyro);
        degrees.turnGyro(90);
        s.Sleep(1000);
        degrees.turnGyro(90);
        s.Sleep(1000);
        degrees.turnGyro(90);
        s.Sleep(1000);
        degrees.turnGyro(90);
        s.Sleep(1000);

        degrees.turnGyro(-90);
        s.Sleep(1000);
        degrees.turnGyro(-90);
        s.Sleep(1000);
        degrees.turnGyro(-90);
        s.Sleep(1000);
        degrees.turnGyro(-90);
        s.Sleep(1000);
    }
}
