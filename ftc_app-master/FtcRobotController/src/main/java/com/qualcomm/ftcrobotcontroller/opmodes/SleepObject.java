package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
public class SleepObject extends LinearOpMode{
    @Override
    public void runOpMode () {
    }
    public void MoveMotorsObjectSleepArg (int sleep) throws InterruptedException {
        Sleep(sleep);
        telemetry.addData("mil seconds", sleep);
    }
    public void Sleep(int s) throws InterruptedException {
        sleep(s);
    }
}