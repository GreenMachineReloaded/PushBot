package com.qualcomm.ftcrobotcontroller.opmodes.drivers;

import com.qualcomm.ftcrobotcontroller.opmodes.Sleeper;
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
    double motorPower = 0;

    public GMRMotor (DcMotor m, Telemetry telemetry) {
        this.motorHandle = m;// naming motor
        t = telemetry;// naming telemetry
        sleep = new Sleeper();// naming sleeper
    }

    public double holdMotor(int position) {
        this.motorHandle.setMode(DcMotorController.RunMode.RESET_ENCODERS);// reset encoder for arm

        sleep.Sleep(50);//sleep time

        this.motorHandle.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);//resets encoder for arm
        sleep.Sleep(10);
        if (this.motorHandle.getCurrentPosition() != position) {// current position
            if (this.motorHandle.getCurrentPosition() > position) {
                motorPower = -((this.motorHandle.getCurrentPosition() - position)/20);// make Pwr of motor by dividing position by 20
                t.addData("", "Motor Power" + motorPower);
                motorPower = Range.clip(motorPower,0,1);
                return motorPower;
            }
            if (this.motorHandle.getCurrentPosition() < position) {
                motorPower = (this.motorHandle.getCurrentPosition() - position)/20;// make Pwr of motor by dividing position by 20
                t.addData("", "Motor Power" + motorPower);
                motorPower = Range.clip(motorPower,0,1);
                return motorPower;
           }
        }
        return 0;// returned value at driver station
    }
}
