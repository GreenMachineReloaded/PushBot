package com.qualcomm.ftcrobotcontroller.opmodes;
import android.graphics.Color;
//package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.robocol.Telemetry;

import static android.graphics.Color.GRAY;

/**
 * Created by Kelly on 1/29/2016.
 */
//public class BlueColoredBeacon {
//
//    ColorSensor colorSensor;
//
//    Telemetry telemetry;
//
//    private float hsvValues[] = {0, 0, 0};
//
//
//    public ColorSensorObject(ColorSensor cs, Telemetry t) {
//
//        this.telemetry = t;
//        this.colorSensor = cs;
//
//    }
//
//    public int red() {
//        return colorSensor.red();
//    }
//
//    public int blue() {
//        return colorSensor.blue();
//    }
//
//    public int green() {
//        return colorSensor.green();
//    }
//
//    public int getColor() {
//
//
//        int returnColor = Color.GRAY;// when return color is asked, it will say GRAY unless otherwise instructed to later
//
//        telemetry.addData("Start Robot", "");
//        Color.RGBToHSV(this.red() * 8, this.green() * 8, this.blue() * 8, hsvValues);
//
//        if ((this.green() >= 5) && (this.blue() >= 20) && (this.red() <= 4)) {// returnColor = BLUE;
//            telemetry.addData("CadwynBlue", this.blue());// return values of the color blue
//            telemetry.addData("CadwynBlue!", "");
//
//            //servo movements keep to same servo
//
//        } else if ((green() <= 5) && (blue() <= 4) && (red() >= 35)) {//   returnColor = RED;
//            telemetry.addData("CadwynRed", this.red());// return the values of the color red
//            telemetry.addData("CadwynRed!", "");
//
//            // servo movements switch to other servo if seen
//        }
//
//        telemetry.addData("Cadwyn's Color Is", returnColor);
//        return returnColor;// return color. Will say GRAY, BLUE, RED, depending on what it matches or if doesn't match any of the colors above
//    }
//
//}