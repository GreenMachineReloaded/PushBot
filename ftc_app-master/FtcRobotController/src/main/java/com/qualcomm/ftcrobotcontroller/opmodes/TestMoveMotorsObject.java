package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.robocol.Telemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import static android.os.SystemClock.sleep;

public class TestMoveMotorsObject extends OpMode {
    MoveMotorsObject move;
    Telemetry t = new Telemetry();
    @Override
    public void loop() {
        sleep(100);
        t.addData("1. x", "thing");
    //    move.moveForward();

    }

    public void init() {
        move = new MoveMotorsObject();
    }
}