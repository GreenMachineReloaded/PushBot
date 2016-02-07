package com.qualcomm.ftcrobotcontroller.opmodes.drivers;

import com.qualcomm.ftcrobotcontroller.opmodes.Sleeper;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.robocol.Telemetry;

/**
 * Created by Amber on 1/29/2016.
 *
 *
 * WARNING! Using this method requires the motor using this to have an encoder
 *
 * 1 degree = (Approx) 6.5 encoder ticks
 *
 *   +MotorPower = +EncoderValue
 */
public class GMRMotor {

    public DcMotor motorHandle;
    Sleeper sleep;
    Telemetry t;

    public GMRMotor (DcMotor m, Telemetry telemetry) {
        this.motorHandle = m;
        t = telemetry;
        sleep = new Sleeper();
    }

    public double holdMotor(int position) {
        this.motorHandle.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);

        if (this.motorHandle.getCurrentPosition() != position) {
            if (this.motorHandle.getCurrentPosition() > 625) {
                if (this.motorHandle.getCurrentPosition() > position) {
                    if (this.motorHandle.getCurrentPosition() > (2 + position)) {
                        return (-0.05);
                    }
                }
            }
            if (this.motorHandle.getCurrentPosition() < 625) {
                if (this.motorHandle.getCurrentPosition() < position) {
                    if (this.motorHandle.getCurrentPosition() < (2 + position)) {
                        return (0.05);
                    }
                }
            }
        }
            return 0;
    }
}
