package com.qualcomm.ftcrobotcontroller.opmodes.drivers;

import com.qualcomm.robotcore.hardware.Servo;

public class GMRServo {

    Servo servoHandle;

    public GMRServo(Servo s) {

        this.servoHandle = s;

        //servoController = hardwareMap.servoController.get("Servo Controller 1");

    }
    public void moveServo(double pos) {

        this.servoHandle.setPosition(pos);

    }

    public double pos(){

        return this.servoHandle.getPosition();

    }

}