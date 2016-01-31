package com.qualcomm.ftcrobotcontroller.opmodes.drivers;

import com.qualcomm.ftcrobotcontroller.opmodes.Sleeper;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

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

    public GMRMotor (DcMotor m) {
        this.motorHandle = m;
    }

    public void holdMotor (int position) {
        sleep = new Sleeper();
        //this.motorHandle = motor;
        this.motorHandle.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        sleep.Sleep(20);
        this.motorHandle.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        if (this.motorHandle.getCurrentPosition() != position){
            if (this.motorHandle.getCurrentPosition() > position){
                if (this.motorHandle.getCurrentPosition() > (9.75+position)){
                    this.motorHandle.setPower(-0.3);
                    if (this.motorHandle.getCurrentPosition() > (6.5+position)){
                        this.motorHandle.setPower(-0.2);
                        if (this.motorHandle.getCurrentPosition() > (3.25+position)){
                            this.motorHandle.setPower(-0.1);
                        }
                    }
                }
            }
            if (this.motorHandle.getCurrentPosition() < position) {
                if (this.motorHandle.getCurrentPosition() < (9.75+position)){
                    this.motorHandle.setPower(0.3);
                    if (this.motorHandle.getCurrentPosition() < (6.5+position)){
                        this.motorHandle.setPower(0.2);
                        if (this.motorHandle.getCurrentPosition() < (3.25+position)){
                            this.motorHandle.setPower(0.1);
                        }
                    }
                }
            }
        }
    }
}
