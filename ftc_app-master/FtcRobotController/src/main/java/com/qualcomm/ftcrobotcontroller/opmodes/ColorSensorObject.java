package com.qualcomm.ftcrobotcontroller.opmodes;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import com.qualcomm.ftcrobotcontroller.R;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;

/**
 * Created by Patrick on 11/22/2015.
 */
public class ColorSensorObject extends ColorSensorDriver {public enum ColorSensorDevice { MODERN_ROBOTICS_I2C};//I2C is the one being used

    public ColorSensorDevice device = ColorSensorDevice.MODERN_ROBOTICS_I2C;

    ColorSensor colorSensor;
    DeviceInterfaceModule cdim;

    @Override
    public void runOpMode() throws InterruptedException {
        hardwareMap.logDevices();

        cdim = hardwareMap.deviceInterfaceModule.get("dim");
        switch (device) {
                        case MODERN_ROBOTICS_I2C:
                colorSensor = hardwareMap.colorSensor.get("wym");//one used
                break;
        }


        waitForStart();

        float hsvValues[] = {0,0,0};
        final float values[] = hsvValues;
        final View relativeLayout = ((Activity) hardwareMap.appContext).findViewById(R.id.RelativeLayout);
        while (opModeIsActive()) {


            switch (device) {
                              case MODERN_ROBOTICS_I2C:
                    Color.RGBToHSV(colorSensor.red()*8, colorSensor.green()*8, colorSensor.blue()*8, hsvValues);// one used. takes values of colors seen and multiplies it by 8
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
            case MODERN_ROBOTICS_I2C://one used
                colorSensor.enableLed(value);// show values and progress on phone screen
                break;
        }
    }

}
