package com.qualcomm.ftcrobotcontroller.opmodes;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import com.qualcomm.ftcrobotcontroller.R;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Patrick on 11/22/2015.
 */

public class ColorSensorObject{

    ColorSensor colorSensor;


    public ColorSensorObject(ColorSensor cs) {

        this.colorSensor = cs;



    }

    public int red() {

        return colorSensor.red();

    }


    public int blue(){

        return colorSensor.blue();


    }
    public int green() {

        return colorSensor.green();
    }



}

