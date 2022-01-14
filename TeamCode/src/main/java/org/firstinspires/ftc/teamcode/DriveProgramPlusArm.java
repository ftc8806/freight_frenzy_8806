package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp (name = "DriveProgramPlusArm")
public class DriveProgramPlusArm extends OpMode {

    DcMotor fr, fl, br, bl, arm, angle;

    @Override
    public void init() {
        fr = hardwareMap.dcMotor.get("fr");
        fl = hardwareMap.dcMotor.get("fl");
        br = hardwareMap.dcMotor.get("br");
        bl = hardwareMap.dcMotor.get("bl");

        fr.setDirection(DcMotorSimple.Direction.REVERSE);
        br.setDirection(DcMotorSimple.Direction.REVERSE);

        arm = hardwareMap.dcMotor.get("arm");
        angle = hardwareMap.dcMotor.get("angle");
    }


    @Override
    public void loop() {

        double speed = .75;
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

        /**Arm**/

        arm.setPower(gamepad2.left_stick_y * halfspeed);
        angle.setPower(gamepad2.right_stick_y * halfspeed);

    }
}

