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
    Sleeper sleep;// create sleeper
    Telemetry t;// create telemetry

    public GMRMotor (DcMotor m, Telemetry telemetry) {
        this.motorHandle = m;// naming motor
        t = telemetry;// naming telemetry
        sleep = new Sleeper();// naming sleeper
    }

    public double holdMotor(int position) {
        this.motorHandle.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        sleep.Sleep(10);

        //if statement
        if (this.motorHandle.getCurrentPosition() != position) {// current position
            double motorPower = 0;// no movement
            motorPower = (this.motorHandle.getCurrentPosition() - position)/20;// make Pwr of motor by dividing position by 20
            motorPower = Range.clip(motorPower,0,1);
            return motorPower;// return motor PWR value
//            if (this.motorHandle.getCurrentPosition() > position) {
//                double motorPower = 0;
//                motorPower = (this.motorHandle.getCurrentPosition() - position)/20;
//                motorPower = Range.clip(motorPower,0,1);
//                return motorPower;
//            }
//            if (this.motorHandle.getCurrentPosition() < position) {
//                double motorPower = 0;
//                motorPower = (this.motorHandle.getCurrentPosition() - position)/20;
//                motorPower = Range.clip(motorPower,0,1);
//                return motorPower;
//            }
        }else {
            return 0;// returned value at driver station
        }
    }
}
