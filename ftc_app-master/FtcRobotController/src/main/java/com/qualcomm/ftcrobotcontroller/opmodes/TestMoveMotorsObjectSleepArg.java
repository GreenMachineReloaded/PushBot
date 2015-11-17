package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class TestMoveMotorsObjectSleepArg extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        MoveMotorsObjectSleepArg move = new MoveMotorsObjectSleepArg();

        waitForStart();

        move.moveForward(1000);

        move.turnRight(2000);

        move.turnLeft(2000);

        move.moveBackward(1000);
    }
}
