package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.ftcrobotcontroller.opmodes.drivers.GMRServo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
public class MoveDropPeopleServo extends LinearOpMode {

    //@Override
    //public void init() {

        //s = new Sleeper();
        //servo = new GMRServo(servoArg);
//    }
    @Override
    public void runOpMode() throws InterruptedException {
        Sleeper s = new Sleeper();
        Servo servoClimbersArg = hardwareMap.servo.get("servoClimbers");
        Servo flapperLeftArg = hardwareMap.servo.get("flapperLeft");
        Servo flapperRightArg = hardwareMap.servo.get("flapperRight");
        GMRServo servoClimbers = new GMRServo(servoClimbersArg);
        GMRServo flapperLeft = new GMRServo(flapperLeftArg);
        GMRServo flapperRight = new GMRServo(flapperRightArg);
        waitForStart();
        int degrees = 0;
        flapperLeft.moveServo(0);
        flapperRight.moveServo(0);
        for(int i = 0; i <= 400; i++) {
            degrees += 0.34;
            servoClimbers.moveServo360(degrees);
            s.Sleep(50);
        }
        servoClimbers.moveServo(0);
    }
}
