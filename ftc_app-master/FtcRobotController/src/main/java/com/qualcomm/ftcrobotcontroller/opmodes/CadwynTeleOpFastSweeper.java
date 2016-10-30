package com.qualcomm.ftcrobotcontroller.opmodes;

import com.kauailabs.navx.ftc.AHRS;
import com.qualcomm.ftcrobotcontroller.opmodes.drivers.GMRMotor;
import com.qualcomm.ftcrobotcontroller.opmodes.drivers.GMRServo;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
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

public class CadwynTeleOpFastSweeper extends OpMode {//initialisations for all motors and servos

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
    GMRServo climberDepositerBlueServo;
    GMRServo climberDepositerRedServo;
    GMRServo winchServo;
    GMRServo hopperDoorBlue;
    GMRServo hopperDoorRed;
    GMRServo hopperEntranceDoor;
    GMRServo redAllClear;
    GMRServo blueAllClear;

    Servo servo1;//left flapper servo
    Servo servo2;//right flapper servo
    Servo servo3;//climber depositor servo
    Servo servo4;//winch servo
    Servo servo5;// hopper door red
    Servo servo6;// hopper door blue
    Servo servo7;// hopper entrance door
    Servo servo8;
    Servo servo11;
    Servo servo12;

    //different servo positions
    double flapperRightRedPosition;
    double flapperLeftBluePosition;
    double climberDepositerBluePosition;
    double climberDepositerRedPosition;
    double winchServoPosition;
    double hopperDoorLeftPosition;
    double hopperDoorRightPosition;
    double hopperEntranceDoorPosition;
    double redAllClearPosition;
    double blueAllClearPosition;

    Double multiplier;
    Double multiplier2;

    //Gyro Sensor
    GyroSensor gyro;// for readings on gyro--- not necessary

    //positions for hold motor methods

    int getArmMotorPosition;

    int sweeperHoldTimer;

    Sleeper sleep;// for pausing

    boolean onTheRamp;

    int NAVX_DIM_I2C_PORT;
    AHRS navx_device;

    ElapsedTime runtime;

    ColorSensorObject colorSensor;
    ColorSensor argColorSensor;

    String onMountain;

    Boolean canChangePositionBlue;
    Boolean canChangePositionRed;

    Integer middleArmPosition;
    Double fullSpeed;
    Double slowSpeed;

    @Override//?
    public void init() {// begin instructions once button "init" is pressed

        dcArmMotor = hardwareMap.dcMotor.get("armMotor");//says where arm motor is
        armMotor = new GMRMotor(dcArmMotor, telemetry);// changes arm motor to GMR motor
        gyro = hardwareMap.gyroSensor.get("gyro");// for readings on gyro sensor---not necessary

        //Initializing drive motors
        leftDriveMotor = hardwareMap.dcMotor.get("leftDrive");// left motors
        rightDriveMotor = hardwareMap.dcMotor.get("rightDrive");// right motors
        leftDriveMotor.setDirection(DcMotor.Direction.FORWARD);// FWD
        rightDriveMotor.setDirection(DcMotor.Direction.REVERSE);// reverse
        winchMotor = hardwareMap.dcMotor.get("winchMotor");// initialisation winch motor
        sweeperMotor = hardwareMap.dcMotor.get("sweeperMotor");// initialisation for sweeper motor
        liftMotor = hardwareMap.dcMotor.get("liftMotor");

        // initialisation for all servos- telling where they all are by name in config. file
        leftBlueFlapperServo = new GMRServo(servo1 = hardwareMap.servo.get("leftFlapperServo"));//left flap servo
        rightRedFlapperServo = new GMRServo(servo2 = hardwareMap.servo.get("rightFlapperServo"));// right flap servo
        climberDepositerBlueServo = new GMRServo(servo3 = hardwareMap.servo.get("climberDepositerBlueServo"));// climber deposit servo
        climberDepositerRedServo = new GMRServo(servo8 = hardwareMap.servo.get("climberDepositorRedServo"));
        winchServo = new GMRServo(servo4 = hardwareMap.servo.get("winchServo"));// winch servo
        hopperDoorRed = new GMRServo(servo5 = hardwareMap.servo.get("hopperDoorRed"));// red hopper door servo
        hopperDoorBlue = new GMRServo(servo6 = hardwareMap.servo.get("hopperDoorBlue"));// blue hopper door servo
        hopperEntranceDoor = new GMRServo(servo7 = hardwareMap.servo.get("hopperEntranceDoor"));
        redAllClear = new GMRServo(servo11 = hardwareMap.servo.get("redallClear"));
        blueAllClear = new GMRServo(servo12 = hardwareMap.servo.get("blueallclear"));

        //starting positions of servos
        flapperRightRedPosition =  1;// right flapper
        flapperLeftBluePosition =  0;// left flapper
        climberDepositerBluePosition =  0;//climber depositor
        winchServoPosition =  0.45;// winch servo
        hopperDoorLeftPosition = 0.7755;// red hopper door (left)
        hopperDoorRightPosition = 0.0445;// blue hopper door (right)
        hopperEntranceDoorPosition = 0.45;
        climberDepositerRedPosition = 1;
        redAllClearPosition = 1;
        blueAllClearPosition = 0;

        sweeperHoldTimer = 0;

        onTheRamp = false;

        multiplier = 1.0;
        multiplier2 = 1.0;

        sleep = new Sleeper();// setting sleeper

        getArmMotorPosition = 0;//arm position;

        NAVX_DIM_I2C_PORT = 3;
        navx_device = AHRS.getInstance(hardwareMap.deviceInterfaceModule.get("DIM1"),
                NAVX_DIM_I2C_PORT,
                AHRS.DeviceDataType.kProcessedData);
        runtime = new ElapsedTime();

        argColorSensor = hardwareMap.colorSensor.get("color");
        colorSensor = new ColorSensorObject(argColorSensor, telemetry);

        onMountain = "no";

        canChangePositionBlue = false;
        canChangePositionRed = false;

        getArmMotorPosition = 0; // if gamepad 1  middleArmPosition and arm position is at 0, then get current arm position

        middleArmPosition = 1;
        fullSpeed = 1.0;
        slowSpeed = 1.0;

        armMotor.motorHandle.setMode(DcMotorController.RunMode.RESET_ENCODERS);

        sleep.Sleep(20);

        armMotor.motorHandle.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);

        sleep.Sleep(20);

        }

    @Override
    public void loop() {// begin sequence loop for main program

        leftDriveMotor.setPower(gamepad1.left_stick_y);//gamepad 1, left stick Y controls left motors
        rightDriveMotor.setPower(gamepad1.right_stick_y);// gamepad 1, right stick Y controls right motors

        if (armMotor.motorHandle.getCurrentPosition() >= middleArmPosition) {
            if (gamepad1.left_trigger > 0) {// moves arm motor left
                armMotor.motorHandle.setPower(-slowSpeed);//sets power for direction
            } else if (gamepad1.left_bumper) {// move arm up
                armMotor.motorHandle.setPower(fullSpeed);
            } else {
                armMotor.motorHandle.setPower(0);
            }
        } else if (armMotor.motorHandle.getCurrentPosition() <= middleArmPosition) {
            if (gamepad1.left_trigger > 0) {// moves arm motor left
                armMotor.motorHandle.setPower(-fullSpeed);//sets power for direction
            } else if (gamepad1.left_bumper) {// move arm up
                armMotor.motorHandle.setPower(slowSpeed);
            } else {
                armMotor.motorHandle.setPower(0);
            }
        }

        if (sweeperHoldTimer > 310) {
            sweeperHoldTimer = 300;
        }

        //if statement
        if (gamepad1.right_bumper) {// if gamepad 1 right bumper is pressed then sets sweeper in reverse
            sweeperMotor.setPower(-0.6);
        } else if (gamepad1.right_trigger > 0) {// if gamepad 1 right trigger is pressed then sets sweeper FWD
            sweeperMotor.setPower(0.6);
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

        if (gamepad1.dpad_down) {// if gamepad 1 a is pressed then make winch position by +.001
            hopperEntranceDoorPosition = 0.45;
        }else if (gamepad1.dpad_up) {// if gamepad 1 y is pressed, then make winch position by -.001
            hopperEntranceDoorPosition = 0;
        }

        if (gamepad2.a) {// if gamepad 1 dpad down is pressed, then moves winch out
            winchMotor.setPower(-1);
        } else if (gamepad2.y) {// if gamepad 1 dpad up is pressed, then move winch in
            winchMotor.setPower(1);
        } else {
            winchMotor.setPower(0);// if nothing pressed then winch does nothing
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
        if (gamepad2.dpad_up) {// if gamepad 2 y is pressed then, move climber depositor up
            climberDepositerBluePosition += 0.05;
            climberDepositerRedPosition -= 0.05;
        }else {// if gamepad 2 a is pressed, then move climber depositor down
            climberDepositerBluePosition -= 0.05;
            climberDepositerRedPosition += 0.05;
        }

        //if statement
        if (gamepad2.right_bumper) {// if gamepad 2 right bumper is pressed, then move blue hopper door (right) up
            hopperDoorRightPosition -= 0.05;
            hopperDoorLeftPosition += 0.05;
        }else if (gamepad2.right_trigger > 0) {// if gamepad 2 right trigger is pressed, then move blue hopper door (right) down
            hopperDoorRightPosition += 0.05;
            hopperDoorLeftPosition -= 0.05;
        }else {
            hopperDoorRightPosition += 0;
            hopperDoorLeftPosition += 0;
        }

        //UpRamp
        if ((navx_device.getPitch() > 14 && onMountain == "no")) {
            hopperEntranceDoorPosition = 0;
            flapperLeftBluePosition = 0.5;
            flapperRightRedPosition = 0.5;
            onMountain = "yes";
        } else if ((navx_device.getPitch() < 14 && onMountain == "yes")) {
            hopperEntranceDoorPosition = 0.45;
            flapperLeftBluePosition = 0;
            flapperRightRedPosition = 1;
            onMountain = "no";
        } else if (gamepad1.y) {
            hopperEntranceDoorPosition = 0;
            flapperLeftBluePosition = 0.5;
            flapperRightRedPosition = 0.5;
        } else if (gamepad1.a) {
            hopperEntranceDoorPosition = 0.45;
            flapperLeftBluePosition = 0;
            flapperRightRedPosition = 1;
        }

        if (gamepad1.x) {
            redAllClearPosition = 0;
            blueAllClearPosition = 1;
        } else {
            redAllClearPosition = 1;
            blueAllClearPosition = 0;
        }

        // make sure servo values doesn't go below the lowest values or above the highest value
        flapperRightRedPosition =  Range.clip(flapperRightRedPosition, 0, 1);// right flapper
        rightRedFlapperServo.moveServo(flapperRightRedPosition);

        flapperLeftBluePosition =  Range.clip(flapperLeftBluePosition, 0, 1);// left flapper
        leftBlueFlapperServo.moveServo(flapperLeftBluePosition);

        climberDepositerBluePosition =  Range.clip(climberDepositerBluePosition, 0, 1);// climber depositor
        climberDepositerBlueServo.moveServo(climberDepositerBluePosition);

        winchServoPosition =  Range.clip(winchServoPosition, 0.36, 0.56);// winch
        winchServo.moveServo(winchServoPosition);

        hopperDoorLeftPosition =  Range.clip(hopperDoorLeftPosition, 0.129, 0.7755);// red hopper door (left)
        hopperDoorRed.moveServo(hopperDoorLeftPosition);

        hopperDoorRightPosition =  Range.clip(hopperDoorRightPosition, 0.0445, 0.7600);// blue hopper door (right)
        hopperDoorBlue.moveServo(hopperDoorRightPosition);

        hopperEntranceDoorPosition =  Range.clip(hopperEntranceDoorPosition, 0, 0.5);// blue hopper door (right)
        hopperEntranceDoor.moveServo(hopperEntranceDoorPosition);

        climberDepositerRedPosition = Range.clip(climberDepositerRedPosition, 0, 1);
        climberDepositerRedServo.moveServo(climberDepositerRedPosition);

        redAllClearPosition = Range.clip(redAllClearPosition, 0, 1);
        redAllClear.moveServo(redAllClearPosition);

        blueAllClearPosition = Range.clip(blueAllClearPosition, 0, 1);
        blueAllClear.moveServo(blueAllClearPosition);

        telemetry.addData("Current Pitch", navx_device.getPitch());

    }
}
