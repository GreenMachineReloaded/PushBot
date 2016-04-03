package com.qualcomm.ftcrobotcontroller.opmodes;

import android.util.Log;

import com.kauailabs.navx.ftc.AHRS;
import com.kauailabs.navx.ftc.navXPIDController;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.robocol.Telemetry;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

public class navXGyroscopeTest extends LinearOpMode {
    DcMotor leftMotor;
    DcMotor rightMotor;

    Telemetry t;

    /* This is the port on the Core Device Interace Module        */
    /* in which the navX-Model Device is connected.  Modify this  */
    /* depending upon which I2C port you are using.               */
    private final int NAVX_DIM_I2C_PORT = 3;
    private AHRS navx_device;
    private navXPIDController yawPIDController;
    private ElapsedTime runtime = new ElapsedTime();

    private final double TARGET_ANGLE_DEGREES = 90.0;
    private final double TOLERANCE_DEGREES = 1.0;
    private final double MIN_MOTOR_OUTPUT_VALUE = -1.0;
    private final double MAX_MOTOR_OUTPUT_VALUE = 1.0;
    private final double YAW_PID_P = 0.005;
    private final double YAW_PID_I = 0.0;
    private final double YAW_PID_D = 0.0;

    @Override
    public void runOpMode() throws InterruptedException {
        t = telemetry;
        leftMotor = hardwareMap.dcMotor.get("leftDriveMotor");
        rightMotor = hardwareMap.dcMotor.get("rightDriveMotor");

        navx_device = AHRS.getInstance(hardwareMap.deviceInterfaceModule.get("DIM1"),
                NAVX_DIM_I2C_PORT,
                AHRS.DeviceDataType.kProcessedData);

        rightMotor.setDirection(DcMotor.Direction.REVERSE);

        /* Create a PID Controller which uses the Yaw Angle as input. */
        yawPIDController = new navXPIDController( navx_device,
                navXPIDController.navXTimestampedDataSource.YAW);

        /* Configure the PID controller */
        yawPIDController.setSetpoint(TARGET_ANGLE_DEGREES);
        yawPIDController.setContinuous(true);
        yawPIDController.setOutputRange(MIN_MOTOR_OUTPUT_VALUE, MAX_MOTOR_OUTPUT_VALUE);
        yawPIDController.setTolerance(navXPIDController.ToleranceType.ABSOLUTE, TOLERANCE_DEGREES);
        yawPIDController.setPID(YAW_PID_P, YAW_PID_I, YAW_PID_D);
        yawPIDController.enable(true);

        waitForStart();

        /* Wait for new Yaw PID output values, then update the motors
           with the new PID value with each new output value.
         */

        final double TOTAL_RUN_TIME_SECONDS = 10.0;
        int DEVICE_TIMEOUT_MS = 500;
        navXPIDController.PIDResult yawPIDResult = new navXPIDController.PIDResult();

        while (runtime.time() < TOTAL_RUN_TIME_SECONDS) {
            if (yawPIDController.waitForNewUpdate(yawPIDResult, DEVICE_TIMEOUT_MS)) {
                if (yawPIDResult.isOnTarget()) {
                    leftMotor.setPower(0);
                    rightMotor.setPower(0);
                } else {
                    double output = yawPIDResult.getOutput();
                    if (output < 0) {
                        /* Rotate Left */
                        output = output + 0.5;
                        output = Range.clip(output, 0 ,1);
                        leftMotor.setPower(-output);
                        rightMotor.setPower(output);
                    } else {
                        /* Rotate Right */
                        output = output + 0.5;
                        output = Range.clip(output, 0 ,1);
                        leftMotor.setPower(output);
                        rightMotor.setPower(-output);
                    }
                }
                double output = yawPIDResult.getOutput();
                telemetry.addData("Current navX Gyro Value", output);
                telemetry.addData("Current Runtime", runtime.time());
            } else {
			          /* A timeout occurred */
                Log.w("navXRotateToAnglePIDOp", "Yaw PID waitForNewUpdate() TIMEOUT.");
            }
//            double output = yawPIDResult.getOutput();
//            telemetry.addData("Current navX Gyro Value", output);
//            telemetry.addData("Current Runtime", runtime.time());
        }
        leftMotor.setPowerFloat();
        rightMotor.setPowerFloat();
    }
}
