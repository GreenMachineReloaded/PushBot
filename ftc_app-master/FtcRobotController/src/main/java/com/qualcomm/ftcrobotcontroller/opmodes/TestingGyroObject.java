package com.qualcomm.ftcrobotcontroller.opmodes;
        import com.qualcomm.robotcore.hardware.DcMotor;
        import com.qualcomm.robotcore.hardware.GyroSensor;
        import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
public class TestingGyroObject extends LinearOpMode {
    Sleeper s;
    DcMotor leftDriveMotorArg;
    DcMotor rightDriveMotorArg;
    GyroSensor gyroArg;
    GyroObjectTest degrees;
    @Override
    public void runOpMode() throws InterruptedException {
        leftDriveMotorArg = hardwareMap.dcMotor.get("leftDriveMotor");
        rightDriveMotorArg = hardwareMap.dcMotor.get("rightDriveMotor");
        gyroArg = hardwareMap.gyroSensor.get("gyro");
        s = new Sleeper();
        waitForStart();
        degrees = new GyroObjectTest(leftDriveMotorArg, rightDriveMotorArg, gyroArg);
        degrees.GyroTurn(90);
        s.Sleep(3000);
        degrees.GyroTurn(90);
        s.Sleep(3000);
        degrees.GyroTurn(90);
        s.Sleep(3000);
        degrees.GyroTurn(90);
        s.Sleep(3000);
        degrees.GyroTurn(-90);
        s.Sleep(3000);
        degrees.GyroTurn(-90);
        s.Sleep(3000);
        degrees.GyroTurn(-90);
        s.Sleep(3000);
        degrees.GyroTurn(-90);
        s.Sleep(3000);
    }
}
