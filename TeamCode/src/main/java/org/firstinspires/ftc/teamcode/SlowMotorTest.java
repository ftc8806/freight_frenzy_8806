package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp (name = "SlowMotorTest")
public class SlowMotorTest extends OpMode {
    DcMotor motor1, motor2;
    @Override
    public void init() {

        motor1 = hardwareMap.dcMotor.get("m1");
        motor2 = hardwareMap.dcMotor.get("m2");
    }

    double speed1 = .1;
    double speed2 = .3;
    public void loop() {

        motor1.setPower(gamepad1.left_stick_y * speed1);
        motor2.setPower(gamepad1.right_stick_y * speed2);
    }
}
