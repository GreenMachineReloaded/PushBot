package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
public class TestSleepObject extends LinearOpMode {
    SleepObject s = new SleepObject();
    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();
        s.Sleep(1000);
    }
}
