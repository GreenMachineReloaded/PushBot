package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class TestMoveMotorsObject extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        MoveMotorsObject move = new MoveMotorsObject();

        waitForStart();

        move.MoveForward();

        move.TurnRight();

        move.TurnLeft();

        move.MoveBackward();

        move.StopMotors();

    }
}
