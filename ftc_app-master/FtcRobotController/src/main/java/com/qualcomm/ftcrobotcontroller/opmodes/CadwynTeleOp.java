package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftcrobotcontroller.opmodes.drivers.GMRMotor;
import com.qualcomm.ftcrobotcontroller.opmodes.drivers.GMRServo;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import java.text.SimpleDateFormat;
import java.util.Calendar;

//Created by Payton on 11/22/2015.


/**
 * List of motor positions in the real world
 *
 * Drive Motor Controller  (1):
 * leftDriveMotor (1)
 * rightDriveMotor (2)
 *
 *Lift Motor Controller  (2):
 * flipBarMotor (1)
 * liftMotor (2)
 *
 * Servo Controller:
 * wheelBarServo
 * flappperRight
 * flapperRed
 *
 *
 *
 *
 * Motors:
 * Minus = Counter Clockwise
 * Plus = Clockwise
 */

public class CadwynTeleOp extends OpMode {//initialisations for all motors and servos

    //motor initialisations
    DcMotor leftDriveMotor;
    DcMotor rightDriveMotor;
    DcMotor dcArmMotor;
    DcMotor winchMotor;
    DcMotor sweeperMotor;
    DcMotor liftMotor;

    //specail GMR motor
    GMRMotor armMotor;

    //Servo initialisations
    GMRServo leftBlueFlapperServo;
    GMRServo rightRedFlapperServo;
    GMRServo climberDepositerServo;
    GMRServo winchServo;
    GMRServo hopperDoorBlue;
    GMRServo hopperDoorRed;
    GMRServo hopperEntranceDoor;
    GMRServo sweeperLift;
    GMRServo sweeperHold;

    Servo servo1;//left flapper servo
    Servo servo2;//right flapper servo
    Servo servo3;//climber depositor servo
    Servo servo4;//winch servo
    Servo servo5;// hopper door red
    Servo servo6;// hopper door blue
    Servo servo7;// hopper entrance door
    Servo servo8;//sweeper lift
    Servo servo9;//sweeper hold

    //different servo positions
    double flapperRightRedPosition;
    double flapperLeftBluePosition;
    double climberDepositerPosition;
    double winchServoPosition;
    double hopperDoorleftPosition;
    double hopperDoorRightPosition;
    double hopperEntranceDoorPosition;
    double sweeperLiftPosition;
    double sweeperHoldPosition;

    double multiplier;// mulitplier for determining different speeds

    //Gyro Sensor
    GyroSensor gyro;// for readings on gyro--- not necessary

    //positions for hold motor methods
    int armMotorPosition;
    int armMotorPosition2;

    int getArmMotorPosition;

    int sweeperHoldTimer;

    Sleeper sleep;// for pausing

    boolean onTheRamp;

    int currentMinute;
    int baseTime;
    int betterTime;
    String currentTime;
    String currentMinuteString;
    String currentSecondString;
    boolean timeCanChange;

    @Override//?
    public void init() {// begin instructions once button "init" is pressed

        dcArmMotor = hardwareMap.dcMotor.get("armMotor");//says where arm motor is
        armMotor = new GMRMotor(dcArmMotor, telemetry);// changes arm motor to GMR motor
        gyro = hardwareMap.gyroSensor.get("gyro");// for readings on gyro sensor---not necessary

        //Initializing drive motors
        leftDriveMotor = hardwareMap.dcMotor.get("leftDriveMotor");// left motors
        rightDriveMotor = hardwareMap.dcMotor.get("rightDriveMotor");// right motors
        leftDriveMotor.setDirection(DcMotor.Direction.FORWARD);// FWD
        rightDriveMotor.setDirection(DcMotor.Direction.REVERSE);// reverse
        winchMotor = hardwareMap.dcMotor.get("winchMotor");// initialisation winch motor
        sweeperMotor = hardwareMap.dcMotor.get("sweeperMotor");// initialisation for sweeper motor
        liftMotor = hardwareMap.dcMotor.get("liftMotor");

        // initialisation for all servos- telling where they all are by name in config. file
        leftBlueFlapperServo = new GMRServo(servo1 = hardwareMap.servo.get("leftFlapperServo"));//left flap servo
        rightRedFlapperServo = new GMRServo(servo2 = hardwareMap.servo.get("rightFlapperServo"));// right flap servo
        climberDepositerServo = new GMRServo(servo3 = hardwareMap.servo.get("climberDepositerServo"));// climber deposit servo
        winchServo = new GMRServo(servo4 = hardwareMap.servo.get("winchServo"));// winch servo
        hopperDoorRed = new GMRServo(servo5 = hardwareMap.servo.get("hopperDoorRed"));// red hopper door servo
        hopperDoorBlue = new GMRServo(servo6 = hardwareMap.servo.get("hopperDoorBlue"));// blue hopper door servo
        hopperEntranceDoor = new GMRServo(servo7 = hardwareMap.servo.get("hopperEntranceDoor"));
        sweeperLift = new GMRServo(servo8 = hardwareMap.servo.get("sweeperLift"));
        sweeperHold = new GMRServo(servo9 = hardwareMap.servo.get("sweeperHold"));

        //starting positions of servos
        flapperRightRedPosition =  1;// right flapper
        flapperLeftBluePosition =  0;// left flapper
        climberDepositerPosition =  0;//climber depositor
        winchServoPosition =  1;// winch servo
        hopperDoorleftPosition = 0.64;// red hopper door (left)
        hopperDoorRightPosition = 0.0882;// blue hopper door (right)
        hopperEntranceDoorPosition = 0.7;
        sweeperLiftPosition = 1;
        sweeperHoldPosition = 0;

        sweeperHoldTimer = 0;

        onTheRamp = false;

        multiplier = 1;//setting multiplier value

        sleep = new Sleeper();// setting sleeper

        armMotor.motorHandle.setMode(DcMotorController.RunMode.RESET_ENCODERS);// reset encoder for arm

        sleep.Sleep(50);//sleep time

        armMotor.motorHandle.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);//resets encoder for arm

        getArmMotorPosition = 0;//arm position

        currentMinute = 0;
        timeCanChange = false;

    }

    @Override
    public void loop() {// begin sequence loop for main program

        armMotorPosition = armMotor.motorHandle.getCurrentPosition();// reading for current arm position

        leftDriveMotor.setPower(gamepad1.left_stick_y);//gamepad 1, left stick Y controls left motors
        rightDriveMotor.setPower(gamepad1.right_stick_y);// gamepad 1, right stick Y controls right motors

        //SlowArm
        if (armMotor.motorHandle.getCurrentPosition() > 500) {//if arm position is greater than 625 then change multiplier by .5
            multiplier = 0.5;
        }else if (armMotor.motorHandle.getCurrentPosition() < 500) {//if arm position less than 625 then change multiplier by 1
            multiplier = 1;
        }
        //if statement 2
        if (gamepad1.x && getArmMotorPosition==0) {// if gamepad 1  x and arm position is at 0, then get current arm position
            getArmMotorPosition = 1;
            armMotorPosition2 = armMotor.motorHandle.getCurrentPosition();
        }else if (gamepad1.x && getArmMotorPosition==1) {//if gamepad 1 x and arm position is 1, keep at same position
            Double armMotorPower = armMotor.holdMotor(armMotorPosition2);// hold arm position
            armMotor.motorHandle.setPower(armMotorPower);
        } else if (!gamepad1.x) {// if gamepad 1 x is not pressed then resets arm position system
            getArmMotorPosition = 0;

            //if statement in last else if in if statement 2
            if (gamepad1.left_bumper) {// moves arm motor left
                armMotor.motorHandle.setPower(-0.2 * multiplier);//sets power for direction
            } else if (gamepad1.left_trigger > 0) {// move arm up
                armMotor.motorHandle.setPower(0.2 * multiplier);
            } else  if (gamepad1.dpad_left) {
                armMotor.motorHandle.setPower(0.8);
            } else {
                armMotor.motorHandle.setPower(0);
            }

        }

        if (sweeperHoldTimer > 310) {
            sweeperHoldTimer = 300;
        }

        //if statement
        if (gamepad1.right_bumper) {// if gamepad 1 right bumper is pressed then sets sweeper in reverse
            sweeperMotor.setPower(-0.8);
        } else if (gamepad1.right_trigger > 0) {// if gamepad 1 right trigger is pressed then sets sweeper FWD
            sweeperMotor.setPower(0.8);
        } else {
            sweeperMotor.setPower(0);// if no button is pressed then keep sweeper PWR at 0
        }

        if (gamepad2.left_bumper) {// if gamepad 1 right bumper is pressed then sets sweeper in reverse
            liftMotor.setPower(-1);
        } else if (gamepad2.left_trigger > 0) {// if gamepad 1 right trigger is pressed then sets sweeper FWD
            liftMotor.setPower(1);
        } else {
            liftMotor.setPower(0);// if no button is pressed then keep sweeper PWR at 0
        }

        //if statement
        if (gamepad1.b) {//if gamepad 1 button b is pressed, then bot goes Forward
            leftDriveMotor.setPower(-0.75);
            rightDriveMotor.setPower(-0.75);
        }

        if (gamepad1.a) {// if gamepad 1 a is pressed then make winch position by +.001
            hopperEntranceDoorPosition = 0.5;
        }else if (gamepad1.y) {// if gamepad 1 y is pressed, then make winch position by -.001
            hopperEntranceDoorPosition = 0;
        }

        if (gamepad2.dpad_left) {// if gamepad 1 dpad down is pressed, then moves winch out
            sweeperLiftPosition += 0.08;
            sweeperHoldTimer = 0;
        } else if (gamepad2.dpad_right) {// if gamepad 1 dpad up is pressed, then move winch in
            sweeperLiftPosition -= 0.08;
            sweeperHoldTimer += 1;
        }

        if (sweeperHoldTimer >= 260) {
            sweeperHoldPosition = 1;
        } else {
            sweeperHoldPosition = 0;
        }

        if (gamepad2.dpad_down) {// if gamepad 1 dpad down is pressed, then moves winch out
            winchMotor.setPower(-1);
        }else if (gamepad2.dpad_up) {// if gamepad 1 dpad up is pressed, then move winch in
            winchMotor.setPower(1);
        }else if (gamepad1.x)  {
            winchMotor.setPower(-1);
        } else {
            winchMotor.setPower(0);// if nothing pressed then winch does nothing
        }

        if (gamepad2.right_bumper) {
            sweeperHoldPosition += 0.001;
        } else if (gamepad2.right_trigger > 0) {
            sweeperHoldPosition -= 0.001;
        }

        if (gamepad2.x) {// if gamepad 1 a is pressed then make winch position by +.001
            winchServoPosition += 0.001;
        }else if (gamepad2.b) {// if gamepad 1 y is pressed, then make winch position by -.001
            winchServoPosition -= 0.001;
        }

        //if statement
        if (gamepad2.right_stick_y > 0) {//if gamepad 2 right stick y is pressed up, then move right flapper up
            flapperRightRedPosition += 0.01;
        }else if (gamepad2.right_stick_y < 0) {// if gamepad 2 right stick y is pressed down, then move right flapper down
            flapperRightRedPosition -= 0.01;
        } else {
            flapperRightRedPosition -= 0;
        }

        //if statement
        if (gamepad2.left_stick_y > 0) {// if gamepad 2 left stick y is pressed up, then move left flapper up
            flapperLeftBluePosition -= 0.01;
        }else if (gamepad2.left_stick_y < 0) {// if gamepad 2 left stick y is pressed down, then mve left flapper down
            flapperLeftBluePosition += 0.01;
        }

        //if statement
        if (gamepad2.y) {// if gamepad 2 y is pressed then, move climber depositor up
            climberDepositerPosition += 0.05;
        }else if (gamepad2.a) {// if gamepad 2 a is pressed, then move climber depositor down
            climberDepositerPosition -= 0.05;
        }

        //if statement
        if (gamepad2.right_bumper) {// if gamepad 2 right bumper is pressed, then move blue hopper door (right) up
            hopperDoorRightPosition -= 0.005;
            hopperDoorleftPosition += 0.005;
        }else if (gamepad2.right_trigger > 0) {// if gamepad 2 right trigger is pressed, then move blue hopper door (right) down
            hopperDoorRightPosition += 0.005;
            hopperDoorleftPosition -= 0.005;
        }else {
            hopperDoorRightPosition += 0;
            hopperDoorleftPosition += 0;
        }

        //UpRamp
        if (gamepad1.dpad_up) {
            hopperEntranceDoorPosition = 0;
            flapperLeftBluePosition = 0.5;
            flapperRightRedPosition = 0.5;
        }else if (gamepad1.dpad_down) {
            hopperEntranceDoorPosition = 0.5;
            flapperLeftBluePosition = 0;
            flapperRightRedPosition = 1;
        }

        // make sure servo values doesn't go below the lowest values or above the highest value
        flapperRightRedPosition =  Range.clip(flapperRightRedPosition, 0, 1);// right flapper
        rightRedFlapperServo.moveServo(flapperRightRedPosition);

        flapperLeftBluePosition =  Range.clip(flapperLeftBluePosition, 0, 1);// left flapper
        leftBlueFlapperServo.moveServo(flapperLeftBluePosition);

        climberDepositerPosition =  Range.clip(climberDepositerPosition, 0, 1);// climber depositor
        climberDepositerServo.moveServo(climberDepositerPosition);

        winchServoPosition =  Range.clip(winchServoPosition, 0.2, 0.45);// winch
        winchServo.moveServo(winchServoPosition);

        hopperDoorleftPosition =  Range.clip(hopperDoorleftPosition, 0.064, 0.64);// red hopper door (left)
        hopperDoorRed.moveServo(hopperDoorleftPosition);

        hopperDoorRightPosition =  Range.clip(hopperDoorRightPosition, 0.0882, 0.6);// blue hopper door (right)
        hopperDoorBlue.moveServo(hopperDoorRightPosition);

        hopperEntranceDoorPosition =  Range.clip(hopperEntranceDoorPosition, 0, 0.5);// blue hopper door (right)
        hopperEntranceDoor.moveServo(hopperEntranceDoorPosition);

        sweeperLiftPosition =  Range.clip(sweeperLiftPosition, 0, 1);// blue hopper door (right)
        sweeperLift.moveServo(sweeperLiftPosition);

        sweeperHoldPosition =  Range.clip(sweeperHoldPosition, 0, 1);// blue hopper door (right)
        sweeperHold.moveServo(sweeperHoldPosition);

        //String servoPositions = String.valueOf(armMotorPosition2);

        //String servoPositions = String.format("%.2f", rightRedFlapperServo);

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("ss");

        baseTime = Integer.valueOf(sdf.format(cal.getTime()));

        if (baseTime == betterTime) {
            baseTime++;
            betterTime = baseTime;
        }

        if (betterTime == 60) {
            currentMinute++;
            timeCanChange = false;
        }
        if (betterTime == 1) {
            timeCanChange = true;
        }

        currentMinuteString = String.valueOf(currentMinute);
        currentSecondString = String.valueOf(betterTime);
        currentTime = currentMinuteString + ":" + betterTime;

        telemetry.addData("Time Left In Match", currentTime);

//        String servoPositions = String.valueOf(sweeperHoldPosition);
//        telemetry.addData("Sweeper Hold Position: ", servoPositions);
//        telemetry.addData("Sweeper Hold Timer", sweeperHoldTimer);

//        int count = 1;
//
//        while (count < 999999999) {
//            count++;
//            telemetry.addData("Count", count);
//        }

    }
}
