package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp (name = "DriveProgramPlusArm")
public class DriveProgramPlusArm extends OpMode {

    DcMotor fr, fl, br, bl, arm, angle;

    // full-tilt ix a bit too fast
    // the robot flies and slides all over the place
    // feel free to try and dial this in more specifically, but .75 worked well enough for basic testing
    double speed = 0.75;
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
         *
         * For Ryan:
         *
         * The issue with the code as it was originally was the lack of "if" statements seen below.
         * The program would check for input one one part of the controller, and then set the motor speed
         * accordingly. The issue being that the different "blocks" for each action ended up fighting each
         * other -- the tank drive section would try to run the motor but the code for strafing would set
         * the motor power back to 0. This back and forth is what caused the "jerky" motion you described.
         *
         * Segmenting the code into their own blocks like this ensures that the seperate actions *don't*
         * fight against each other.
         *
         * Not everything needs to be segmented and grouped like this, only sections of code dealing with
         * the same motors. For example, the tank drive and turning need to be in their own section, where
         * the controls for the arm and sucker need to be in their own.
         *
         * I'm not very good at describing things but hopefully this helps a bit lol
         *
         * - Mike
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

