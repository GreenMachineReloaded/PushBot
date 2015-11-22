package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
public class TestSleepObject extends LinearOpMode {
    Sleeper s = new Sleeper();
    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();
        s.Sleep(1000);
    }
}
