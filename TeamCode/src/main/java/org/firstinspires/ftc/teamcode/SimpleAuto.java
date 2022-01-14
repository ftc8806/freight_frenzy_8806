package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import java.security.DomainCombiner;

@Autonomous(name = "Autonomous (do something?)")
public class SimpleAuto extends LinearOpMode {

    DcMotor fr, fl, br, bl, launcher, conveyor, arm;

//    int ENCODER_TARGET = -800;
    double MOTOR_POWER = 0.5;

    @Override
    public void runOpMode() throws InterruptedException {

        // Hardware mapping
        fr = hardwareMap.dcMotor.get("fright");
        fl = hardwareMap.dcMotor.get("fleft");
        br = hardwareMap.dcMotor.get("bright");
        bl = hardwareMap.dcMotor.get("bleft");


        launcher = hardwareMap.dcMotor.get("l");
        conveyor = hardwareMap.dcMotor.get("c");
        arm = hardwareMap.dcMotor.get("a");

        // Ensure all motors run in the same direction
        fr.setDirection(DcMotorSimple.Direction.REVERSE);
        br.setDirection(DcMotorSimple.Direction.REVERSE);

        // Wait for program start
        telemetry.addData("MODE", "waitForStart()");
        waitForStart();

        // Reset encoder positions
        setMotorMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // move robot small forward (500 ticks)
        // this is to account for the arm starting in the "down" positions
        // we drive forward a small bit then raise up the arm
        setEncoderTarget(-500);
        setMotorMode(DcMotor.RunMode.RUN_TO_POSITION);
        setMotorPower(0.25);

        // wait for move to complete
        while (opModeIsActive() && fr.isBusy()) { idle(); telemetry.addData("MODE", "wasting time with idle()"); }

        // stop motors
        setMotorPower(0.0);

        // run arm for a small amount of time (look ma, no encoders!)
        arm.setPower(-0.2);
        sleep(500);
        arm.setPower(0);

        // move robot the rest of the way forward
        // Set target for encoders
        setEncoderTarget(-3600);

        // setup is done and arm is clear, go!
        telemetry.addData("MODE", "Go!");
        setMotorPower(MOTOR_POWER);

        // start getting the flywheel up to speed
        launcher.setPower(-0.355);

        // spin idly for a bit while the motors et where they need to be
        while (opModeIsActive() && fr.isBusy()) { idle(); telemetry.addData("MODE", "wasting time with idle()"); }

        // stop the motors since we'e either stopped running auto
        // or he motor hs reached it's destination
        setMotorPower(0.0);

        // go to the right, line up with goals
        strafeRight(1500);
        setMotorMode(DcMotor.RunMode.RUN_TO_POSITION);

        setMotorPower(MOTOR_POWER);

        // idle() some more. maybe it's fun
        while (opModeIsActive() && fr.isBusy()) { idle(); telemetry.addData("MODE", "wasting time with idle()"); }

        // come to a stop again
        setMotorPower(0.0);

        // wait longer because the flywheel is an old lady
        // she'll get there (eventually)
        // give her an extra 5 seconds to get up to speed
        sleep(5 * 1000);

        // hopefully we remembered to pre-load a disc, otherwise this gonna look hella dumb
        // I don't feel like using an encoder for this one, so just run the conveyor for a few secons
        conveyor.setPower(-.75);
        sleep((long)3 * 1000); // 3 seconds

        // the disc should be launched, yay!
        // stop the conveyor and flywheel
        conveyor.setPower(0);
        launcher.setPower(0);

        // go forward small amount to "park" robot on the line
        setMotorMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setEncoderTarget(-500);
        setMotorMode(DcMotor.RunMode.RUN_TO_POSITION);
        setMotorPower(0.25);

        while (opModeIsActive() && fr.isBusy()) { idle(); telemetry.addData("MODE", "wasting time with idle()"); }

        setMotorPower(0);
    }

    void strafeRight(int t) {
        // Reset encoders
        setMotorMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // Set target positions (accounting for inverse motor directions)
        fl.setTargetPosition(-t);
        bl.setTargetPosition(t);
        fr.setTargetPosition(t);
        br.setTargetPosition(-t);
    }

    void strafeLeft(int t) {
        // Reset encoders
        setMotorMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // Set target positions (accounting for inverse motor directions)
        fl.setTargetPosition(t);
        bl.setTargetPosition(-t);
        fr.setTargetPosition(-t);
        br.setTargetPosition(t);
    }

    void setEncoderTarget(int target) {
        fr.setTargetPosition(target);
        fl.setTargetPosition(target);
        br.setTargetPosition(target);
        bl.setTargetPosition(target);
    }

    void setMotorMode(DcMotor.RunMode mode) {
        fr.setMode(mode);
        fl.setMode(mode);
        br.setMode(mode);
        bl.setMode(mode);
    }

    void setMotorPower(double p) {
        fr.setPower(p);
        fl.setPower(p);
        br.setPower(p);
        bl.setPower(p);
    }
}
