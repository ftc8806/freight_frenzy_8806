package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp (name = "Basic Drive Program 123021")
public class BasicDriveProgram123021 extends OpMode {

    DcMotor fr, fl, br, bl;

    @Override
    public void init() {
        fr = hardwareMap.dcMotor.get("fr");
        fl = hardwareMap.dcMotor.get("fl");
        br = hardwareMap.dcMotor.get("br");
        bl = hardwareMap.dcMotor.get("bl");

        fr.setDirection(DcMotorSimple.Direction.REVERSE);
        br.setDirection(DcMotorSimple.Direction.REVERSE);
    }


    @Override
    public void loop() {

        double speed = .9;
        double halfspeed = .60;
        /**Left Stick**/
        fl.setPower(gamepad1.left_stick_y * speed);
        bl.setPower(gamepad1.left_stick_y * speed);

        /**Right Stick**/
        br.setPower(gamepad1.right_stick_y * speed);
        fr.setPower(gamepad1.right_stick_y * speed);

        /**Bumpers**/
        if (gamepad1.right_bumper) {
            // right
            fl.setPower(-speed);
            bl.setPower(speed);
            fr.setPower(speed);
            br.setPower(-speed);

        } else if (gamepad1.left_bumper) {
            // left
            fl.setPower(speed);
            bl.setPower(-speed);
            fr.setPower(-speed);
            br.setPower(speed);
        }
    }
}
