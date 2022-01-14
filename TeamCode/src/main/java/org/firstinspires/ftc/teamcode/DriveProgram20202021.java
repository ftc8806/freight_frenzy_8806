package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
@Disabled
@TeleOp (name = "2020-2021 Drive Program")
public class DriveProgram20202021 extends OpMode {
    DcMotor fr, fl, br, bl,  launcher, conveyor, arm;

    @Override
    public void init() {
        fr = hardwareMap.dcMotor.get("fright");
        fl = hardwareMap.dcMotor.get("fleft");
        br = hardwareMap.dcMotor.get("bright");
        bl = hardwareMap.dcMotor.get("bleft");


        launcher = hardwareMap.dcMotor.get("l");
        conveyor = hardwareMap.dcMotor.get("c");
        arm = hardwareMap.dcMotor.get("a");

        fr.setDirection(DcMotorSimple.Direction.REVERSE);
        br.setDirection(DcMotorSimple.Direction.REVERSE);
    }


    @Override
    public void loop() {

        double speed = .9;
        double halfspeed = .60;
        /**Left Stick**/
        fr.setPower(gamepad1.left_stick_y * speed);
        fl.setPower(gamepad1.left_stick_y * speed);
        br.setPower(gamepad1.left_stick_y * speed);
        bl.setPower(gamepad1.left_stick_y * speed);

        /**Right Stick**/
        if (gamepad1.right_stick_x > 0) {
            // right
            fl.setPower(-speed);
            bl.setPower(speed);
            fr.setPower(speed);
            br.setPower(-speed);

        } else if (gamepad1.right_stick_x < 0) {
            // left
            fl.setPower(speed);
            bl.setPower(-speed);
            fr.setPower(-speed);
            br.setPower(speed);
        }

        /**Spin**/
        //turn r
        if (gamepad1.right_bumper) {
            fl.setPower(-halfspeed);
            bl.setPower(-halfspeed);
            fr.setPower(halfspeed);
            br.setPower(halfspeed);
        }
        //turn l
        else if (gamepad1.left_bumper) {
            fl.setPower(halfspeed);
            bl.setPower(halfspeed);
            fr.setPower(-halfspeed);
            br.setPower(-halfspeed);
        }
        /**Launching**/
        double flywheelspeed = -.355;
        if (gamepad2.a) {
            launcher.setPower(flywheelspeed);
        }
        else if (gamepad2.b) {
            launcher.setPower(0);
        }

        conveyor.setPower(gamepad2.right_stick_y * -.65);

        if (gamepad2.dpad_up) {
            arm.setPower(.5);
        }
        else if (gamepad2.dpad_down) {
            arm.setPower(-.5);
        }
        else {
            arm.setPower(0);
        }

        telemetry.addData("left stick x", gamepad1.left_stick_x);
        telemetry.addData("left stick y", gamepad1.left_stick_y);
        telemetry.addData("right stick x", gamepad1.right_stick_x);
        telemetry.addData("right stick y", gamepad1.right_stick_y);
        telemetry.addData("flywheel speed", launcher.getPower());
    }
}