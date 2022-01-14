package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
@Disabled
@TeleOp (name = "MecanumWheels")
public class MecanumWheel2019_2020 extends OpMode {
    DcMotor fr, fl, br, bl, lift;
    Servo wrist, latch;
    CRServo extend;
    double extendPos = 0.00;

    @Override
    public void init() {
        fr = hardwareMap.dcMotor.get("fright");
        fl = hardwareMap.dcMotor.get("fleft");
        br = hardwareMap.dcMotor.get("bright");
        bl = hardwareMap.dcMotor.get("bleft");
        lift = hardwareMap.dcMotor.get("lift");

        //hardwareMap.crservo.
        wrist = hardwareMap.servo.get("wrist");
        latch = hardwareMap.servo.get("latch");

        extend = hardwareMap.crservo.get("extend");

        fr.setDirection(DcMotorSimple.Direction.REVERSE);
        br.setDirection(DcMotorSimple.Direction.REVERSE);

        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // MAKE SURE YOU REMOVE THIS
        //extend.setPosition(0);
    }


    @Override
    public void loop() {

        double speed = 1;
        double halfspeed = .75;
        /**Left Stick**/
        fr.setPower(gamepad1.left_stick_y * speed);
        fl.setPower(gamepad1.left_stick_y * speed);
        br.setPower(gamepad1.left_stick_y * speed);
        bl.setPower(gamepad1.left_stick_y * speed);

        fr.setPower(gamepad1.left_stick_x * speed);
        fl.setPower(gamepad1.left_stick_x * -speed);
        br.setPower(gamepad1.left_stick_x * -speed);
        bl.setPower(gamepad1.left_stick_x * speed);

//            //North
//            if (gamepad1.dpad_up) {
//                fl.setPower(speed);
//                fr.setPower(speed);
//                bl.setPower(speed);
//                br.setPower(speed);
//            }
//            //South
//            else if (gamepad1.dpad_down) {
//                fl.setPower(-speed);
//                fr.setPower(-speed);
//                bl.setPower(-speed);
//                br.setPower(-speed);
//            }
//            //West
//            else if (gamepad1.dpad_left) {
//                fl.setPower(-speed);
//                fr.setPower(speed);
//                bl.setPower(speed);
//                br.setPower(-speed);
//            }
//            //East
//            else if (gamepad1.dpad_right) {
//                fl.setPower(speed);
//                fr.setPower(-speed);
//                bl.setPower(-speed);
//                br.setPower(speed);
//            }
//            else {
//                fl.setPower(0);
//                fr.setPower(0);
//                bl.setPower(0);
//                br.setPower(0);
//            }

        /**Right Stick**/
        //NW
        if (gamepad1.right_stick_y > 0 & gamepad1.right_stick_x < 0) {
            fl.setPower(speed);
            br.setPower(speed);
        }
        //NE
        else if (gamepad1.right_stick_y > 0 & gamepad1.right_stick_x > 0) {
            fr.setPower(speed);
            bl.setPower(speed);
        }
        //SW
        else if (gamepad1.right_stick_y < 0 & gamepad1.right_stick_x > 0) {
            fl.setPower(-speed);
            br.setPower(-speed);
        }
        //SE
        else if (gamepad1.right_stick_y < 0 & gamepad1.right_stick_x < 0) {
            fr.setPower(-speed);
            bl.setPower(-speed);
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

        /**Lift**/
        if (lift.getCurrentPosition() > -2350 && gamepad2.right_stick_y < 0){
            lift.setPower(-gamepad2.right_stick_y);
            telemetry.addData("lift", "should be going up");
            telemetry.addData("lift CurrentPosition", lift.getCurrentPosition());
            telemetry.addData("right_stick_y", gamepad2.right_stick_y);
            telemetry.addData("lift.getPower():", lift.getPower());
        } else if (lift.getCurrentPosition() < 0 && gamepad2.right_stick_y > 0) {
            lift.setPower(-gamepad2.right_stick_y);
            telemetry.addData("lift", "should be going down");
            telemetry.addData("lift CurrentPosition", lift.getCurrentPosition());
            telemetry.addData("right_stick_y", gamepad2.right_stick_y);
            telemetry.addData("lift.getPower():", lift.getPower());
        } else {
            lift.setPower(0);
            telemetry.addData("lift", "should be going nowhere");
            telemetry.addData("lift CurrentPosition", lift.getCurrentPosition());
            telemetry.addData("right_stick_y", gamepad2.right_stick_y);
            telemetry.addData("lift.getPower():", lift.getPower());
        }

        /**Claw**/
        if (gamepad1.dpad_up) {
            // Close
            wrist.setPosition(0);
        } else if (gamepad1.dpad_down) {
            // Open
            wrist.setPosition(.4);
        }
        /**Extender**/
        if (gamepad2.dpad_down) {
            // Out
            extend.setPower(1);
        } else if (gamepad2.dpad_up) {
            // In
            extend.setPower(-1);
        } else {
            // No
            extend.setPower(0);
        }

        /**Latch**/
        if (gamepad2.y) {
            //
            latch.setPosition(0.8);
        }
        else if (gamepad2.a) {
            //
            latch.setPosition(0.2);
        }

        // BIG RED EMERGENCY BOOBOO BUTTON
        if (gamepad1.start && gamepad2.start) {
            lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            lift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
        telemetry.addData("left stick x", gamepad1.left_stick_x);
        telemetry.addData("left stick y", gamepad1.left_stick_y);
        telemetry.addData("right stick x", gamepad1.right_stick_x);
        telemetry.addData("right stick y", gamepad1.right_stick_y);

        //telemetry.addData("lift", gamepad2.right_stick_y);

        telemetry.addData("lift pos", lift.getCurrentPosition());
        telemetry.addData("latch", latch.getPosition());
        telemetry.addData("wrist", wrist.getPosition());

    }
}