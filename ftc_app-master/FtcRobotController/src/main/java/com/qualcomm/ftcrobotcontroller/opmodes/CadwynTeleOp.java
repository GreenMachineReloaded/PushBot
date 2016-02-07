package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftcrobotcontroller.opmodes.drivers.GMRMotor;
import com.qualcomm.ftcrobotcontroller.opmodes.drivers.GMRServo;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;


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

    //specail GMR motor
    GMRMotor armMotor;

    //Servo initialisations
    GMRServo leftFlapperServo;
    GMRServo rightFlapperServo;
    GMRServo climberDepositerServo;
    GMRServo winchServo;
    GMRServo hopperDoorBlue;
    GMRServo hopperDoorRed;

    Servo servo1;//left flapper servo
    Servo servo2;//right flapper servo
    Servo servo3;//climber depositor servo
    Servo servo4;//winch servo
    Servo servo5;// hopper door red
    Servo servo6;// hopper door blue

    //different servo positions
    double flapperRightPosition;
    double flapperLeftPosition;
    double climberDepositerPosition;
    double winchServoPosition;
    double hopperDoorleftRedPosition;
    double hopperDoorRightBluePosition;

    double multiplier;// mulitplier for determining different speeds

    //positions for hold motor methods
    int armMotorPosition;
    int armMotorPosition2;
    int getArmMotorPosition;

    Sleeper sleep;// for pausing

    @Override//?
    public void init() {// begin instructions once button "init" is pressed

        dcArmMotor = hardwareMap.dcMotor.get("armMotor");//says where arm motor is
        armMotor = new GMRMotor(dcArmMotor, telemetry);// changes arm motor to GMR motor

        //Initializing drive motors
        leftDriveMotor = hardwareMap.dcMotor.get("leftDriveMotor");// left motors
        rightDriveMotor = hardwareMap.dcMotor.get("rightDriveMotor");// right motors
        leftDriveMotor.setDirection(DcMotor.Direction.FORWARD);// FWD
        rightDriveMotor.setDirection(DcMotor.Direction.REVERSE);// reverse

        winchMotor = hardwareMap.dcMotor.get("winchMotor");// initialisation winch motor

        sweeperMotor = hardwareMap.dcMotor.get("sweeperMotor");// initialisation for sweeper motor

        // initialisation for all servos- telling where they all are by name in config. file
        leftFlapperServo = new GMRServo(servo1 = hardwareMap.servo.get("leftFlapperServo"));//left flap servo
        rightFlapperServo = new GMRServo(servo2 = hardwareMap.servo.get("rightFlapperServo"));// right flap servo
        climberDepositerServo = new GMRServo(servo3 = hardwareMap.servo.get("climberDepositerServo"));// climber deposit servo
        winchServo = new GMRServo(servo4 = hardwareMap.servo.get("winchServo"));// winch servo
        hopperDoorRed = new GMRServo(servo5 = hardwareMap.servo.get("hopperDoorRed"));// red hopper door servo
        hopperDoorBlue = new GMRServo(servo6 = hardwareMap.servo.get("hopperDoorBlue"));// blue hopper door servo

        //starting positions of servos
        flapperRightPosition =  1;// right flapper
        flapperLeftPosition =  0;// left flapper
        climberDepositerPosition =  0;//climber depositor
        winchServoPosition =  0.21;// winch servo
        hopperDoorleftRedPosition = 0.6;// red hopper door (left)
        hopperDoorRightBluePosition = 0;// blue hopper door (right)

        multiplier = 1;//setting multiplier value

        sleep = new Sleeper();// setting sleeper

        armMotor.motorHandle.setMode(DcMotorController.RunMode.RESET_ENCODERS);// reset encoder for arm

        sleep.Sleep(50);//sleep time

        armMotor.motorHandle.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);//resets encoder for arm

        getArmMotorPosition = 0;//arm position

    }

    @Override
    public void loop() {// begin sequence loop for main program

        armMotorPosition = armMotor.motorHandle.getCurrentPosition();// reading for current arm position

        leftDriveMotor.setPower(gamepad1.left_stick_y);//gamepad 1, left stick Y controls left motors
        rightDriveMotor.setPower(gamepad1.right_stick_y);// gamepad 1, right stick Y controls right motors

        //if statement 1
        if (armMotor.motorHandle.getCurrentPosition() > 625) {//if arm position is greater than 625 then change multiplier by .5
            multiplier = 0.5;
        }else if (armMotor.motorHandle.getCurrentPosition() < 625) {//if arm position less than 625 then change multiplier by 1
            multiplier = 1;
        }
        //if statement 2
        if (gamepad1.x && getArmMotorPosition == 0) {// if gamepad 1  x and arm position is at 0, then move arm to position 1
            getArmMotorPosition = 1;
            armMotorPosition2 = armMotor.motorHandle.getCurrentPosition();
        }else if (gamepad1.x && getArmMotorPosition == 1) {//if gamepad 1 x and arm position is 1, keep at same position
            Double armMotorPower = armMotor.holdMotor(armMotorPosition2);// hold arm position
            armMotor.motorHandle.setPower(armMotorPower);
        } else if (!gamepad1.x) {// if gamepad 1 x is not pressed then resets arm position system
            getArmMotorPosition = 0;

            //if statement in last else if in if statement 2
            if (gamepad1.left_bumper) {// moves arm motor left
                armMotor.motorHandle.setPower(-0.2);//sets power for direction
            } else if (gamepad1.left_trigger > 0){// move arm up
                armMotor.motorHandle.setPower(0.2);
            }else if ( !gamepad1.left_bumper && !(gamepad1.left_trigger > 0) && (getArmMotorPosition == 0)) {// if no of requirements above are met, then arm does nothing
                armMotor.motorHandle.setPower(0);
            }
        }
        String armPosition = String.valueOf(armMotor.motorHandle.getCurrentPosition());//sends message to driver's phone for arm position
        telemetry.addData("", "" + "it made it this far: " + armPosition);

        //if statement
        if (gamepad1.right_bumper) {// if gamepad 1 right bumper is pressed then sets sweeper in reverse
            sweeperMotor.setPower(-1);
        } else if (gamepad1.right_trigger > 0) {// if gamepad 1 right trigger is pressed then sets sweeper FWD
            sweeperMotor.setPower(1);
        } else {
            sweeperMotor.setPower(0);// if no button is pressed then keep sweeper PWR at 0
        }

        //if statement
        if (gamepad1.b) {//if gamepad 1 button b is pressed, then bot goes FWD
            leftDriveMotor.setPower(-0.5);
            rightDriveMotor.setPower(-0.5);
        }
        //if statement
        if (gamepad1.dpad_down) {// if gamepad 1 dpad down is pressed, then moves winch out
            winchMotor.setPower(-0.5);
        }else if (gamepad1.dpad_up) {// if gamepad 1 dpad up is pressed, then move winch in
            winchMotor.setPower( 0.5);
        }else {
            winchMotor.setPower(0);// if nothing pressed then winch does nothing
        }

        //if statement
        if (gamepad1.a) {// if gamepad 1 a is pressed then make winch position by +.001
            winchServoPosition += 0.001;
        }else if (gamepad1.y) {// if gamepad 1 y is pressed, then make winch position by -.001
            winchServoPosition -= 0.001;
        }

        //if statement
        if (gamepad2.right_stick_y > 0) {//if gamepad 2 right stick y is pressed up, then move right flapper up
            flapperRightPosition += 0.01;
        }else if (gamepad2.right_stick_y < 0) {// if gamepad 2 right stick y is pressed down, then move right flapper down
            flapperRightPosition -= 0.01;
        }

        //if statement
        if (gamepad2.left_stick_y > 0) {// if gamepad 2 left stick y is pressed up, then move left flapper up
            flapperLeftPosition -= 0.01;
        }else if (gamepad2.left_stick_y < 0) {// if gamepad 2 left stick y is pressed down, then mve left flapper down
            flapperLeftPosition += 0.01;
        }

        //if statement
        if (gamepad2.y) {// if gamepad 2 y is pressed then, move climber depositor up
            climberDepositerPosition += 0.05;
        }else if (gamepad2.a) {// if gamepad 2 a is pressed, then move climber depositor down
            climberDepositerPosition -= 0.05;
        }

        // if statement
        if (gamepad2.left_bumper) {// if gamepad 2 left bumper is pressed, then move red hopper door (left) up
            hopperDoorleftRedPosition += 0.005;
        }else if (gamepad2.left_trigger > 0) {// if gamepad 2  left trigger is pressed, then move red hopper door (left) down
            hopperDoorleftRedPosition -= 0.005;
        }

        //if statement
        if (gamepad2.right_bumper) {// if gamepad 2 right bumper is pressed, then move blue hopper door (right) up
            hopperDoorRightBluePosition -= 0.005;
        }else if (gamepad2.right_trigger > 0) {// if gamepad 2 right trigger is pressed, then move blue hopper door (right) down
            hopperDoorRightBluePosition += 0.005;
        }

        // make sure servo values doesn't go below the lowest values or above the highest value
        flapperRightPosition =  Range.clip(flapperRightPosition, 0, 1);// right flapper
        rightFlapperServo.moveServo(flapperRightPosition);

        flapperLeftPosition =  Range.clip(flapperLeftPosition, 0, 1);// left flapper

        leftFlapperServo.moveServo(flapperLeftPosition);

        climberDepositerPosition =  Range.clip(climberDepositerPosition, 0, 1);// climber depositor
        climberDepositerServo.moveServo(climberDepositerPosition);

        winchServoPosition =  Range.clip(winchServoPosition, 0, 0.21);// winch
        winchServo.moveServo(winchServoPosition);

        hopperDoorleftRedPosition =  Range.clip(hopperDoorleftRedPosition, 0.14, 0.6);// red hopper door (left)
        hopperDoorRed.moveServo(hopperDoorleftRedPosition);

        hopperDoorRightBluePosition =  Range.clip(hopperDoorRightBluePosition, 0.03, 0.6);// blue hopper door (right)
        hopperDoorBlue.moveServo(hopperDoorRightBluePosition);

        //String servoPositions = String.valueOf(armMotorPosition2);

        //String servoPositions = String.format("%.2f", rightFlapperServo);

        String servoPositions = String.valueOf(hopperDoorRightBluePosition);// printing info to driver station phone
        telemetry.addData("", "current multiplier: " + servoPositions);

    }
}