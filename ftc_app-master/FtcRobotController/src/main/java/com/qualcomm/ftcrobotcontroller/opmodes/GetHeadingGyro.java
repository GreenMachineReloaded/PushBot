package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.GyroSensor;
public class GetHeadingGyro extends LinearOpMode {
    boolean forever;
    GyroSensor gyro;
    Sleeper s;
    int heading;
    @Override
    public void runOpMode() throws InterruptedException {
        forever = true;
        s = new Sleeper();
        gyro = hardwareMap.gyroSensor.get("gyro");
        gyro.calibrate();
        while(gyro.isCalibrating() == true) {
            s.Sleep(100);
        }
        gyro.resetZAxisIntegrator();
        heading = gyro.getHeading();
        telemetry.addData("before start", heading);
        while(forever == true) {
            s.Sleep(100);
            heading = gyro.getHeading();
            telemetry.addData("new heading:", heading);
        }
    }
}
