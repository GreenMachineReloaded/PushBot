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
        gyro = hardwareMap.gyroSensor.get("gyro");
        heading = 0;
        gyro.calibrate();
        while(forever == true) {
            s.Sleep(100);
            if(gyro.isCalibrating() == false) {
                telemetry.addData("before start", gyro.getHeading());
                if(forever) {
                    s.Sleep(100);
                    telemetry.addData("new heading:", gyro.getHeading());

                }
            }
        }
    }
}
