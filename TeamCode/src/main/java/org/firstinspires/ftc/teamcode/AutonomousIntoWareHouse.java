package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
@Disabled
@Autonomous(name = "AutonomusWareHouse")
public class AutonomousIntoWareHouse extends LinearOpMode {

    DcMotor fr, fl, br, bl, arm, angle;

    double MOTOR_POWER = -0.3;
    double ANGLE_POWEr = -.4;

    @Override
    public void runOpMode() throws InterruptedException {
        fr = hardwareMap.dcMotor.get("fr");
        fl = hardwareMap.dcMotor.get("fl");
        br = hardwareMap.dcMotor.get("br");
        bl = hardwareMap.dcMotor.get("bl");

        arm = hardwareMap.dcMotor.get("arm");
        angle = hardwareMap.dcMotor.get("angle");

        // Ensure all motors run in the same direction
        fr.setDirection(DcMotorSimple.Direction.REVERSE);
        br.setDirection(DcMotorSimple.Direction.REVERSE);

        setAnglePower(ANGLE_POWEr);
        sleep(1200);
        setAnglePower(0);

        // Wait for program start
        telemetry.addData("MODE", "waitForStart()");
        waitForStart();


        setMotorPower(MOTOR_POWER);
        sleep(2000);
        setMotorPower(0);
    }

    void setMotorPower(double p) {
        fr.setPower(p);
        fl.setPower(p);
        br.setPower(p);
        bl.setPower(p);
    }
    void setAnglePower(double a) {
        angle.setPower(a);
    }
}
