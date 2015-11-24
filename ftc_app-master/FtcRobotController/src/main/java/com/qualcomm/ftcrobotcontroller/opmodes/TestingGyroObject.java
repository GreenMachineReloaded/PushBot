package com.qualcomm.ftcrobotcontroller.opmodes;
        import com.qualcomm.robotcore.hardware.GyroSensor;
        import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
public class TestingGyroObject extends LinearOpMode {
    GyroSensor gyro;
    int x, y, z;
    @Override
    public void runOpMode() throws InterruptedException {
        gyro = hardwareMap.gyroSensor.get("gyro");
        waitForStart();
        gyro.calibrate();
        sleep(1000);
        GyroObject degrees = new GyroObject();
        degrees.turnGyro(90);
        sleep(1000);
        degrees.turnGyro(90);
        sleep(1000);
        degrees.turnGyro(90);
        sleep(1000);
        degrees.turnGyro(90);
        sleep(1000);

        degrees.turnGyro(-90);
        sleep(1000);
        degrees.turnGyro(-90);
        sleep(1000);
        degrees.turnGyro(-90);
        sleep(1000);
        degrees.turnGyro(-90);
        sleep(1000);
    }
}
