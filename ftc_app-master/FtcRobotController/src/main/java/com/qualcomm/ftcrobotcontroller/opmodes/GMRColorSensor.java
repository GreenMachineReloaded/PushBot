package com.qualcomm.ftcrobotcontroller.opmodes;

import android.graphics.Color;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.robocol.Telemetry;

/**
 * Created by Patrick on 11/22/2015.
 */

public class GMRColorSensor {

    com.qualcomm.robotcore.hardware.ColorSensor colorSensor;//creating colorsensor

    Telemetry telemetry;// creating telemetry

    private float hsvValues[] = {0,0,0};//

    
    public GMRColorSensor(com.qualcomm.robotcore.hardware.ColorSensor cs, Telemetry t) {

        this.telemetry = t;//naming telemetry
        this.colorSensor = cs;// naming color sensor

    }

    public int red() {
        return colorSensor.red();
    }

    public int blue(){return colorSensor.blue();}

    public int green() {return colorSensor.green();}

    public String getColor() {


        String returnColor = "gray";// when return color is asked, it will say GRAY unless otherwise instructed to later

        telemetry.addData("Start Robot","");
        Color.RGBToHSV(this.red() * 8, this.green() * 8, this.blue() * 8, hsvValues);

        if ((this.green()>=1) && (this.blue()>=3) && (this.red()<=1)) {//   returnColor = BLUE;
            telemetry.addData("CadwynBlue", this.blue());// return values of the color blue
            telemetry.addData("CadwynBlue!","");

        }

         else if ((green()<=1) && (blue()<=1) && (red()>=3)){//   returnColor = RED;
            telemetry.addData("CadwynRed", this.red());// return the values of the color red
            telemetry.addData("CadwynRed!","");
        }

     else if ((green()>=3) && (blue()>=1) && (red()<=1)){// returnColor = GREEN;
            telemetry.addData("CadwynGreen", this.green());// return the value of the color green
            telemetry.addData("CadwynGreen!","");
        }

      else if ((green()>=2)&&(blue()>=2)&&(red()>=2)){
        returnColor = "white";//tells return color to say WHITE iff it matches the requirements above
        }

      else if ((green()==1&&blue()==1&&red()==1)){
        returnColor = "gray";// tells return color to say GRAY iff it matches the requirements above

        }

        telemetry.addData("Cadwyn's Color Is",returnColor);
        return returnColor;// return color. Will say GRAY, BLUE, RED, GREEN, or WHITE depending on what it matches or if doesn't match any of the colors above
    }
}


/*  100/8 = 12.5
    255/12.5 = 20.4

    WHEN SET TO VALUES OF 0-100 (USED IN CODE)
    have phone say blue iff blue>20, red<4, green>5
    have phone say red iff blue<4, red>35, green<5
    have phone say green iff blue>20, red<10, green>28
    have phone say white iff blue>60, red>60, green>60
    have phone say grey iff blue<10, red<10, green<10

    WHEN SET TO VALUES OF 0-255
    have phone say blue iff blue>50, red<10, green>13
    have phone say red iff blue<10, red>87, green<13
    have phone say green iff blue>50, red<25, green>70
    have phone say white iff blue>150, red>150, green>150
    have phone say grey iff blue<25, red<25, green<25
    */
