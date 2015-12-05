package com.qualcomm.ftcrobotcontroller.opmodes;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import com.qualcomm.ftcrobotcontroller.R;
import com.qualcomm.robotcore.hardware.ColorSensor;

/**
 * Created by Patrick on 11/22/2015.
 */

public class ColorSensorObject{

    ColorSensor colorSensor;


    public ColorSensorObject(ColorSensor cs) {

        this.colorSensor = cs;
    }

    public int red() {

        return colorSensor.red()*18;
    }

    public int blue(){

        return colorSensor.blue()*18;
    }

    public int green() {

        return colorSensor.green()*18;
    }



}

/*  100/8 = 12.5
    225/12.5 = 18
 */

