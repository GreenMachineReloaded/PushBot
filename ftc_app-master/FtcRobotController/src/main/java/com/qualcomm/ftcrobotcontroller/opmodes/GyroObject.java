package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;



/**
 * Created by Amber on 11/6/2015.
 */
public class GyroObject {
    DcMotor leftMotor;
    DcMotor rightMotor;
    GyroSensor DegreesTurned;
public void GryoObject(double d) {
        setGyroObject(d);
    }
    public void setGyroObject(double d) {
        leftMotor = hardwareMap.dcMotor.get("left_drive");
        rightMotor = hardwareMap.dcMotor.get("right_drive");
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        DegreesTurned = hardwareMap.gyroSensor.get("gyro");
        double DegreesTurnedDouble = 0;
        if(d > 0) {
            //moves the motors
            leftMotor.setPower(1);
            rightMotor.setPower(-1);
            do{
                //in here there needs to be an updater for the number of degrees the robot has turned.
            DegreesTurnedDouble = 0;
            }while(DegreesTurnedDouble == d);
            //stops the motors
            leftMotor.setPower(0);
            rightMotor.setPower(0);
        }
        else if(d < 0) {
            //moves the motors
            leftMotor.setPower(-1);
            rightMotor.setPower(1);
            do{
                //in here there needs to be an updater for the number of degrees the robot has turned.
                DegreesTurnedDouble = 0;
            }while(DegreesTurnedDouble == d);
            //stops the motors
            leftMotor.setPower(0);
            rightMotor.setPower(0);
        }
        else {
            //the code here should auto correct itself.
            leftMotor.setPower(0);
            rightMotor.setPower(0);

        }
    }
}
