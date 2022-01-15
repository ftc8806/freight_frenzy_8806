package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp (name = "DriveProgramPlusArm")
public class DriveProgramPlusArm extends OpMode {

    DcMotor fr, fl, br, bl, arm, angle;
    double speed = 1;
    double halfspeed = .60;

    @Override
    public void init() {
        fr = hardwareMap.dcMotor.get("fr");
        fl = hardwareMap.dcMotor.get("fl");
        br = hardwareMap.dcMotor.get("br");
        bl = hardwareMap.dcMotor.get("bl");

        fl.setDirection(DcMotorSimple.Direction.REVERSE);
        fr.setDirection(DcMotorSimple.Direction.REVERSE);
        br.setDirection(DcMotorSimple.Direction.REVERSE);

        arm = hardwareMap.dcMotor.get("arm");
        angle = hardwareMap.dcMotor.get("angle");
    }

    @Override
    public void loop() {

        /**
         * Something to note with this implementation:
         * Rotation (with bumpers) takes priority over tank drive movement
         */
        if (gamepad1.left_bumper) {
            // in-place rotate left
            fl.setPower(speed);
            bl.setPower(speed);
            fr.setPower(-speed);
            br.setPower(-speed);
        } else if (gamepad1.right_bumper) {
            // in-place rotate right
            fl.setPower(-speed);
            bl.setPower(-speed);
            fr.setPower(speed);
            br.setPower(speed);
        } else if (gamepad1.left_stick_y != 0 || gamepad1.right_stick_y != 0) {
            // tank drive
            fl.setPower(gamepad1.left_stick_y * speed);
            bl.setPower(gamepad1.left_stick_y * speed);
            br.setPower(gamepad1.right_stick_y * speed);
            fr.setPower(gamepad1.right_stick_y * speed);
        } else {
            fl.setPower(0);
            bl.setPower(0);
            fr.setPower(0);
            br.setPower(0);
        }

        // wacky spinning wheel
//        if (gamepad1.b) {
//
//        } else if (gamepad1.x) {
//
//        }

        /**Arm**/
        arm.setPower(gamepad2.left_stick_y * speed);
        angle.setPower(gamepad2.right_stick_y * halfspeed);

        /**DATA**/
        telemetry.addData("left stick y", gamepad1.left_stick_y);
        telemetry.addData("right stick y", gamepad1.right_stick_y);
    }
}

