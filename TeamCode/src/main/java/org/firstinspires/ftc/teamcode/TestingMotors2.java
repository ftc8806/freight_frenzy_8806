package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
@Disabled
@TeleOp (name = "TestingMotors2")
public class TestingMotors2 extends OpMode {
    DcMotor fr, fl, br, bl;

    @Override
    public void init() {
        fr = hardwareMap.dcMotor.get("fright");
        fl = hardwareMap.dcMotor.get("fleft");
        br = hardwareMap.dcMotor.get("bright");
        bl = hardwareMap.dcMotor.get("bleft");


        fr.setDirection(DcMotorSimple.Direction.REVERSE);
        br.setDirection(DcMotorSimple.Direction.REVERSE);
    }


    @Override
    public void loop() {

        double speed = .2;
        double halfspeed = .20;
        double doublespeed = .4;
        /**Left Stick**/
        fr.setPower(gamepad1.left_stick_y * speed);
        fl.setPower(gamepad1.left_stick_y * speed);
        br.setPower(gamepad1.left_stick_y * speed);
        bl.setPower(gamepad1.left_stick_y * speed);

        fr.setPower(gamepad1.left_stick_x * speed);
        fl.setPower(gamepad1.left_stick_x * -speed);
        br.setPower(gamepad1.left_stick_x * -speed);
        bl.setPower(gamepad1.left_stick_x * speed);

        /**Right Stick**/
        fl.setPower(gamepad1.right_stick_x * -speed);
        bl.setPower(gamepad1.right_stick_x *-speed);
        fr.setPower(gamepad1.right_stick_x *speed);
        br.setPower(gamepad1.right_stick_x *speed);

        /**Spin**/
//        //turn r
//        if (gamepad1.right_bumper) {
//            fl.setPower(-speed);
//            bl.setPower(-speed);
//            fr.setPower(speed);
//            br.setPower(speed);
//        }
//        //turn l
//        else if (gamepad1.left_bumper) {
//            fl.setPower(speed);
//            bl.setPower(speed);
//            fr.setPower(-speed);
//            br.setPower(-speed);
//        }
    }
}