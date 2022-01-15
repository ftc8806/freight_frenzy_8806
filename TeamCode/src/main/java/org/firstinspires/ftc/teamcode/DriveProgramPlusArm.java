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

        fl.setDirection(DcMotorSimple.Direction.REVERSE);
        fr.setDirection(DcMotorSimple.Direction.REVERSE);
        br.setDirection(DcMotorSimple.Direction.REVERSE);

        arm = hardwareMap.dcMotor.get("arm");
        angle = hardwareMap.dcMotor.get("angle");

        setMotorMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    @Override
    public void loop() {

        double speed1 = 1;
        double speed2 = .5;
        double speed3 = .5;
        double halfspeed = .60;



        /**Left Stick**/
        fl.setPower(gamepad1.left_stick_y * speed2);
        bl.setPower(gamepad1.left_stick_y * speed2);

        /**Right Stick**/
        br.setPower(gamepad1.right_stick_y * speed3);
        fr.setPower(gamepad1.right_stick_y * speed2);

        /**Triggers**/
        fl.setPower(gamepad1.left_trigger * speed2);
        bl.setPower(gamepad1.left_trigger * speed2);
        br.setPower(gamepad1.left_trigger * speed3);
        fr.setPower(gamepad1.left_trigger * speed2);

        fl.setPower(gamepad1.right_trigger * -speed2);
        bl.setPower(gamepad1.right_trigger * -speed2);
        br.setPower(gamepad1.right_trigger * -speed3);
        fr.setPower(gamepad1.right_trigger * -speed2);



//        /**Bumpers**/
//        if (gamepad1.right_bumper) {
//            // right
//            fl.setPower(speed2);
//            bl.setPower(speed2);
//            fr.setPower(speed2);
//            br.setPower(speed2);
//
//        } else if (gamepad1.left_bumper) {
//            // left
//            fl.setPower(speed2);
//            bl.setPower(-speed2);
//            fr.setPower(-speed2);
//            br.setPower(speed2);
//        }

        /**Arm**/

        arm.setPower(gamepad2.left_stick_y * speed1);
        angle.setPower(gamepad2.right_stick_y * halfspeed);

        /**DATA**/
        telemetry.addData("left stick y", gamepad1.left_stick_y);
        telemetry.addData("right stick y", gamepad1.right_stick_y);

        telemetry.addData("fl pos", fl.getCurrentPosition());
        telemetry.addData("fr pos", fr.getCurrentPosition());
        telemetry.addData("bl pos", bl.getCurrentPosition());
        telemetry.addData("br pos", br.getCurrentPosition());

    }
    void setMotorMode(DcMotor.RunMode mode) {
        fr.setMode(mode);
        fl.setMode(mode);
        br.setMode(mode);
        bl.setMode(mode);
    }
}

