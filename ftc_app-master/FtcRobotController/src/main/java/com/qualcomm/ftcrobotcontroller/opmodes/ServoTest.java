package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftcrobotcontroller.opmodes.drivers.GMRServo;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;
//import static java.lang.Thread.sleep;

/**
 * Created by Payton on 11/15/2015
 */
public class ServoTest extends OpMode {

    GMRServo servo;

    Servo servo1;

    @Override
    public void init() {

        servo1 = hardwareMap.servo.get("wheelieBarServo");

        servo = new GMRServo(servo1);

        //servo.moveServo(0.1);

    }

   @Override
     public void loop() {

//        servo.moveServo(0.1);
//
//        try {
//            sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        servo.moveServo(0.9);
//
//        try {
//            sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//       }

       if (gamepad1.a) {
           servo.moveServo(360);
       }

       if (gamepad1.y) {
           servo.moveServo(324);
       }

}}
