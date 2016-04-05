package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.robocol.Telemetry;

/**
 * Created by Payton on 4/5/2016.
 */
public class ColorSensorTest extends LinearOpMode {
    ColorSensor colorSensor;
    Telemetry telemetry;
    ColorSensorObject color;
    @Override
    public void runOpMode() throws InterruptedException {
        colorSensor = hardwareMap.colorSensor.get("color");
        color = new ColorSensorObject(colorSensor, telemetry);
        waitForStart();
        while (true) {
            String currentColor = color.getColor();
            telemetry.addData("Current Color", currentColor);
        }
    }

}
