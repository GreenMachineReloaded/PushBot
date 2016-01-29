package com.qualcomm.ftcrobotcontroller.opmodes;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.ftcrobotcontroller.R;
import com.qualcomm.robotcore.hardware.ColorSensor;

/** * Created by Patrick on 1/10/2016.
 */
public class TestColorSensorObject extends TestColorSensor { public enum ColorSensorDevice {MODERN_ROBOTICS_I2C};

    public ColorSensorDevice device = ColorSensorDevice.MODERN_ROBOTICS_I2C;

    ColorSensor colorSensor;


    @Override
    public void runOpMode() throws InterruptedException {
        hardwareMap.logDevices();

        switch (device) {
            case MODERN_ROBOTICS_I2C:
                colorSensor = hardwareMap.colorSensor.get("colorSensor");
                break;
        }

        waitForStart();

        float hsvValues[] = {0,0,0};
        final float values[] = hsvValues;
        final View relativeLayout = ((Activity) hardwareMap.appContext).findViewById(R.id.RelativeLayout);
        while (opModeIsActive()) {

            //enableLed(true);

            switch (device) {
                case MODERN_ROBOTICS_I2C:
                    Color.RGBToHSV(colorSensor.red()*8, colorSensor.green()*8, colorSensor.blue()*8, hsvValues);

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

//   private void enableLed(boolean value) {
//       switch (device) {
//
//           case MODERN_ROBOTICS_I2C:
//               colorSensor.enableLed(value);
//               break;
//        }
//    }
}
