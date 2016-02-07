package com.qualcomm.ftcrobotcontroller.opmodes.drivers;

import com.qualcomm.ftcrobotcontroller.opmodes.Sleeper;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.robocol.Telemetry;
import com.qualcomm.robotcore.util.Range;

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
    double motorPower = 0;

    public GMRMotor (DcMotor m, Telemetry telemetry) {
        this.motorHandle = m;
        t = telemetry;
        sleep = new Sleeper();
    }

    public double holdMotor(int position, Telemetry telemetry) {
        this.motorHandle.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        sleep.Sleep(10);
        if (this.motorHandle.getCurrentPosition() != position) {
            if (this.motorHandle.getCurrentPosition() > position) {
                motorPower = -((this.motorHandle.getCurrentPosition() - position)/20);
                telemetry.addData("", "Motor Power" + motorPower);
                motorPower = Range.clip(motorPower,0,1);
                return motorPower;
            }
            if (this.motorHandle.getCurrentPosition() < position) {
                motorPower = (this.motorHandle.getCurrentPosition() - position)/20;
                telemetry.addData("", "Motor Power" + motorPower);
                motorPower = Range.clip(motorPower,0,1);
                return motorPower;
           }
        }
        return 0;
    }
}
