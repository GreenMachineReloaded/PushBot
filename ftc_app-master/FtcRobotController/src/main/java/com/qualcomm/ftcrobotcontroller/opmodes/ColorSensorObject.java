package com.qualcomm.ftcrobotcontroller.opmodes;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import com.qualcomm.ftcrobotcontroller.R;
import com.qualcomm.robotcore.hardware.ColorSensor;

import static android.graphics.Color.*;

/**
 * Created by Patrick on 11/22/2015.
 */

public class ColorSensorObject{

    ColorSensor colorSensor;


    public ColorSensorObject(ColorSensor cs) {

        this.colorSensor = cs;
    }

    public int red() {

        return colorSensor.red()*20;
    }

    public int blue(){

        return colorSensor.blue()*20;
    }

    public int green() {

     return colorSensor.green()*20;
    }
    
}


/*  100/8 = 12.5
    255/12.5 = 20.4


    BEFORE VALUES SET TO 0-255
    have phone say blue iff blue>20, red<4, green>5
    have phone say red iff blue<4, red>35, green<5
    have phone say green iff blue>20, red<10, green>28
    have phone say white iff blue>60, red>60, green>60
    have phone say grey iff blue<10, red<10, green<10


    AFTER VALUES SET TO 0-255
    have phone say blue iff blue>50, red<10, green>13
    have phone say red iff blue<10, red>87, green<13
    have phone say green iff blue>50, red<25, green>70
    have phone say white iff blue>150, red>150, green>150
    have phone say grey iff blue<25, red<25, green<25





 Public int{


    if((green()>13) && (blue()>20) && (red()<4))

    return BLUE;


        else ((green()<13) && (blue()<10)&& (red()>87))

    return RED;


        else ((green()>70) && (blue()>50)&& (red()<25))

    return GREEN;


        else ((green()>150) && (blue()>150)&& (red()>150))

    return WHITE;


        else ((green()>25) && (blue()>25)&& (red()>25))

    return GRAY;
}

 */





























