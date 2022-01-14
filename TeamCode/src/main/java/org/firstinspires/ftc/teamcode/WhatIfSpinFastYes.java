package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp (name = "OneMotorTest")
public class WhatIfSpinFastYes extends OpMode {
    DcMotor motor1;
    @Override
    public void init() {

        motor1 = hardwareMap.dcMotor.get("m1");
    }

    double speed = .5;
    public void loop() {

        motor1.setPower(gamepad1.left_stick_y);
    }
}
