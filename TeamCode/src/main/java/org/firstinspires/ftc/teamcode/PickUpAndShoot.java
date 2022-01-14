package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
@Disabled
@TeleOp (name = "PickUpAndShootAndLift")
public class PickUpAndShoot extends OpMode{
    DcMotor launcher, conveyor, arm; //sweeper;
    @Override
    public void init() {
        launcher = hardwareMap.dcMotor.get("l");
        conveyor = hardwareMap.dcMotor.get("c");
        arm = hardwareMap.dcMotor.get("a");
        //sweeper = hardwareMap.dcMotor.get("s");
    }

    public void loop() {
        launcher.setPower(-gamepad1.left_stick_y * .60);

        conveyor.setPower(gamepad1.right_stick_y * .75);

        if (gamepad1.dpad_up) {
            arm.setPower(.3);
        }
        else if (gamepad1.dpad_down) {
            arm.setPower(-.3);
        }
        else {
            arm.setPower(0);
        }
       // if (gamepad1.a) {
      //      sweeper.setPower(1);
      //  }
      //  if (gamepad1.x){
      //      sweeper.setPower(-1);
      //  }
      //  if (gamepad1.b) {
      //      sweeper.setPower(0);
       // }
    }
}

