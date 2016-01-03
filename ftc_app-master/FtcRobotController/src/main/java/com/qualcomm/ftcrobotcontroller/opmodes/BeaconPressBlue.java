
package com.qualcomm.ftcrobotcontroller.opmodes;
        import com.qualcomm.ftcrobotcontroller.opmodes.drivers.GMRServo;
        import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
        import com.qualcomm.robotcore.hardware.ColorSensor;
        import com.qualcomm.robotcore.hardware.DcMotor;
        //import com.qualcomm.robotcore.hardware.DcMotorController;
        //import com.qualcomm.robotcore.hardware.GyroSensor;
        import com.qualcomm.robotcore.hardware.Servo;

public class BeaconPressBlue extends LinearOpMode {
    DcMotor leftMotorArg;
    DcMotor rightMotorArg;
    Sleeper s;
    ColorSensor color;
    Servo leftServoArg;
    Servo rightServoArg;
    double colorD = 0;
    @Override
    public void runOpMode() throws InterruptedException {
        leftMotorArg = hardwareMap.dcMotor.get("leftDriveMotor");
        rightMotorArg = hardwareMap.dcMotor.get("rightDriveMotor");
        s = new Sleeper();
        color = hardwareMap.colorSensor.get("colorSensor");
        leftServoArg = hardwareMap.servo.get("beaconLeft");
        rightServoArg = hardwareMap.servo.get("beaconRight");
        //rename code to with real name
        MoveMotorsObject move = new MoveMotorsObject(leftMotorArg, rightMotorArg);
        GMRServo leftServo = new GMRServo(leftServoArg);
        GMRServo rightServo = new GMRServo(rightServoArg);
        //ColorSensorObject c = new ColorSensorObject(color);
        waitForStart();
        move.moveForward(3600);
        move.turnRight(500);
        move.moveForward(1300);
        //colorD = c.getBlue;
        //if(colorD >= 25 || colorD <= 30) {
        // leftServo.moveServo(180);
        //}
        //else{
        // rightServo.moveServo(180);
        //}
        // rightServo.moveServo(0);
        // leftServo.moveServo(0);
    }
}
