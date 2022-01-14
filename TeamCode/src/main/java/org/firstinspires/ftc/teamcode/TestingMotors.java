package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
@Disabled
@TeleOp (name = "TestingMotors")
public class TestingMotors extends OpMode {
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
        if (gamepad1.a) {
            fr.setPower(1);
            telemetry.addData("running motor", "front right");
        } else if (gamepad1.b) {
            fl.setPower(1);
            telemetry.addData("running motor", "front left");
        } else if (gamepad1.x) {
            br.setPower(1);
            telemetry.addData("running motor", "back right");
        } else if (gamepad1.y) {
            bl.setPower(1);
            telemetry.addData("running motor", "back left");
        }

    }
}