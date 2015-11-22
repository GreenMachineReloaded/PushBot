package com.qualcomm.ftcrobotcontroller.opmodes;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.ftcrobotcontroller.R;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.LED;
import com.qualcomm.robotcore.hardware.TouchSensor;

/**
 * Created by Kelly on 11/1/2015.
 */

    public class TestColorSensor extends ColorSensorDriver {

        public enum ColorSensorDevice {ADAFRUIT, HITECHNIC_NXT, MODERN_ROBOTICS_I2C};//I2C is the one being used

        public ColorSensorDevice device = ColorSensorDevice.MODERN_ROBOTICS_I2C;

        ColorSensor colorSensor;
//        DeviceInterfaceModule cdim;
//        LED led;
//        TouchSensor t;

        @Override
        public void runOpMode() throws InterruptedException {
            hardwareMap.logDevices();

//            cdim = hardwareMap.deviceInterfaceModule.get("dim");
            switch (device) {
                case HITECHNIC_NXT:
                    colorSensor = hardwareMap.colorSensor.get("nxt");
                    break;
                case ADAFRUIT:
                    colorSensor = hardwareMap.colorSensor.get("brd");
                    break;
                case MODERN_ROBOTICS_I2C:
                    colorSensor = hardwareMap.colorSensor.get("colorSensor");//one used
                    break;
            }
//            led = hardwareMap.led.get("led");
//            t = hardwareMap.touchSensor.get("t");

            waitForStart();

            float hsvValues[] = {0,0,0};
            final float values[] = hsvValues;
            final View relativeLayout = ((Activity) hardwareMap.appContext).findViewById(R.id.RelativeLayout);
            while (opModeIsActive()) {

                enableLed(true);//may need to add to ColorSensorObject

                switch (device) {
                    case HITECHNIC_NXT:
                        Color.RGBToHSV(colorSensor.red(), colorSensor.green(), colorSensor.blue(), hsvValues);
                        break;
                    case ADAFRUIT:
                        Color.RGBToHSV((colorSensor.red() * 255) / 800, (colorSensor.green() * 255) / 800, (colorSensor.blue() * 255) / 800, hsvValues);//color values?
                        break;
                    case MODERN_ROBOTICS_I2C:
                        Color.RGBToHSV(colorSensor.red()*8, colorSensor.green()*8, colorSensor.blue()*8, hsvValues);// one used
                        break;
                }
                telemetry.addData("alpha", colorSensor.alpha());//transparency
                telemetry.addData("Red  ", colorSensor.red());//red
                telemetry.addData("Green", colorSensor.green());//green
                telemetry.addData("Blue ", colorSensor.blue());//blue
                telemetry.addData("Hue", hsvValues[0]);//hue

                relativeLayout.post(new Runnable() {
                    public void run() {
                        relativeLayout.setBackgroundColor(Color.HSVToColor(0xff, values));
                    }
                });
                waitOneFullHardwareCycle();
            }
        }

        private void enableLed(boolean value) {
            switch (device) {
                case HITECHNIC_NXT:
                    colorSensor.enableLed(value);
                    break;
                case ADAFRUIT:
                    led.enable(value);
                    break;
                case MODERN_ROBOTICS_I2C:
                    colorSensor.enableLed(value);
                    break;
            }
        }

    }
