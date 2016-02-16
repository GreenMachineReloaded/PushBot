package com.qualcomm.ftcrobotcontroller.opmodes.drivers;

import com.qualcomm.robotcore.hardware.Servo;

public class GMRServo {

    Servo servoHandle;// creates servo

    public GMRServo(Servo s) {// constuctor-- when first making servo

        this.servoHandle = s;// naming servo

    }
    public void moveServo(double pos) {// main method for servo movement

        this.servoHandle.setPosition(pos);//sets servo position

    }

    public double pos(){//reports servo position

        return this.servoHandle.getPosition();

    }

}