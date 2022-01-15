package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous(name = "Encoders")
public class AutoIntoWareouseEncoders extends LinearOpMode {

    DcMotor fr, fl, br, bl, arm, angle;

    //    int ENCODER_TARGET = -800;
    double MOTOR_POWER = 0.5;

    @Override
    public void runOpMode() throws InterruptedException {

        fr = hardwareMap.dcMotor.get("fr");
        fl = hardwareMap.dcMotor.get("fl");
        br = hardwareMap.dcMotor.get("br");
        bl = hardwareMap.dcMotor.get("bl");

        // these are reversed in hardware?
//        fr.setDirection(DcMotorSimple.Direction.REVERSE);
//        br.setDirection(DcMotorSimple.Direction.REVERSE);

        telemetry.addData("fl pos", fl.getCurrentPosition());
        telemetry.addData("fr pos", fr.getCurrentPosition());
        telemetry.addData("bl pos", bl.getCurrentPosition());
        telemetry.addData("br pos", br.getCurrentPosition());

        telemetry.addData("MODE", "waitForStart()");
        waitForStart();

        setMotorMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // set up motor mode and encoder target
        setMotorMode(DcMotor.RunMode.RUN_TO_POSITION);
        setEncoderTarget(-2000);
        setMotorPower(0.25);

        while (opModeIsActive() &&
                (fr.isBusy() || fl.isBusy() || br.isBusy() || bl.isBusy()))
        {
            idle();
            telemetry.addData("MODE", "IDLE");
        }

        // stop
        setMotorPower(0.0);
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
