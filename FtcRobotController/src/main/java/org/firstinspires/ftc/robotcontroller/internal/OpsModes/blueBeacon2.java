package org.firstinspires.ftc.robotcontroller.internal.OpsModes;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.util.RobotLog;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcontroller.external.samples.HardwarePushbot;
import org.firstinspires.ftc.robotcontroller.external.samples.HardwarePushbotMatrix;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This OpMode uses the common Pushbot hardware class to define the devices on the robot.
 * All device access is managed through the HardwarePushbot class.
 * The code is structured as a LinearOpMode
 *
 * This particular OpMode executes a POV Game style Teleop for a PushBot
 * In this mode the left stick moves the robot FWD and back, the Right stick turns left and right.
 * It raises and lowers the claw using the Gampad Y and A buttons respectively.
 * It also opens and closes the claws slowly using the left and right Bumper buttons.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="Pushbot: Teleop POV", group="Pushbot")
public class blueBeacon2 extends LinearOpMode {

    /* Declare OpMode members. */
    HardwarePushbotTeravoltz robot           = new HardwarePushbotTeravoltz();   // Use a Pushbot's hardware
    String Version = "0.0.3";

    // could also use HardwarePushbotMatrix class.

    @Override
    public void runOpMode() throws InterruptedException {
        //Start Variables
        double step = 0;
        double beacon1;
        double beacon2;
        double platformHorizontal;
        double platformVertical;
        double speed = -1;
        double stop = 0;
        double turnSpeed = -1;
        double distanceFromButtonToPush;
        double distanceFromButtonToTurn;
        double beaconArmPower = 1;
        double shootingDistance;
        double SHOOTER_ON;
        double KICKER_RAISED_POSITION = 0.5;
        double KICKER_LOWERED_POSITION = 0;
        double ELEVATOR_POWER = 1;
        long elevatorTime;
        double pushPosition = 0.2;
        double stillPosition = 0;

        //Timing
        long beaconArmMoveTime;
        long beaconArmRestTime;
        long ninetyTurn;
        long kickerTime;
        long exitTime;
        long pusherMoveTime;


        //End Variables

        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "It is working and you loaded the package.");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();


        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            /*
            if(step ==  ) {

                step++;
            }

            //Live variables
            double distance; //= robot.distanceSensor.getDistance();


            //Steps

            if(step == 0) {
                robot.rightMotor.setPower(speed);
                robot.leftMotor.setPower(speed);
                step++;
            }

            if(step == 1 && robot.distanceSensor <= beacon1) {
                robot.rightMotor.setPower(stop);
                robot.leftMotor.setPower(stop);
                step++;
            }
            if(step == 2) {;
                robot.rightMotor.setPower(-turnSpeed) ;
                robot.leftMotor.setPower(turnSpeed);
                sleep(ninetyTurn);
                robot.rightMotor.setPower(stop);
                robot.leftMotor.setPower(stop);
                step++;
            }

            if(step == 3) {
                robot.rightMotor.setPower(speed);
                robot.leftMotor.setPower(speed);
                step++;
            }


            if(step == 4 && distance <= distanceFromButtonToPush ) {
                robot.rightMotor.setPower(stop);
                robot.leftMotor.setPower(stop);
                step++;
            }

            if(step == 5 && robot.colorSensor.blue() > robot.colorSensor.red()) {
                robot.rightPusher.setPosition(pushPosition);
                sleep(pusherMoveTime);
                robot.rightPusher.setPosition(stillPosition);
                sleep(pusherMoveTime);
            }
            if(step == 5 && robot.colorSensor.blue() < robot.colorSensor.red()) {
                robot.leftPusher.setPosition(pushPosition);
                sleep(pusherMoveTime);
                robot.rightPusher.setPosition(stillPosition);
                sleep(pusherMoveTime);
                step = 15;
            }

            if(step == 15) {;;
                robot.rightMotor.setPower(-speed);
                robot.leftMotor.setPower(-speed);
                step++;
            }

            if(step == 16 && distance >= distanceFromButtonToTurn) {
                robot.rightMotor.setPower(stop);
                robot.leftMotor.setPower(stop);
                step++;
            }

            if(step == 17) {
                robot.rightMotor.setPower(turnSpeed) ;;
                robot.leftMotor.setPower(-turnSpeed);
                sleep(ninetyTurn);
                robot.rightMotor.setPower(stop);
                robot.leftMotor.setPower(stop);
                step++;
            }

            if(step == 18) {
                robot.rightMotor.setPower(speed);
                robot.leftMotor.setPower(speed);
                step++;
            }

            if(step == 19 && distance <= beacon2) {
                robot.rightMotor.setPower(stop);
                robot.leftMotor.setPower(stop);
                step++;
            }
            if(step == 20) {;
                robot.rightMotor.setPower(-turnSpeed) ;
                robot.leftMotor.setPower(turnSpeed);
                sleep(ninetyTurn);
                robot.rightMotor.setPower(stop);
                robot.leftMotor.setPower(stop);
                step++;
            }

            if(step == 21) {
                robot.rightMotor.setPower(speed);
                robot.leftMotor.setPower(speed);
                step++;
            }


            if(step == 22 && distance <= distanceFromButtonToPush ) {
                robot.rightMotor.setPower(stop);
                robot.leftMotor.setPower(stop);
                step++;
            }

            if(step == 23 && robot.colorSensor.blue() > robot.colorSensor.red()) {
                robot.rightPusher.setPosition(pushPosition);
                sleep(pusherMoveTime);
                robot.rightPusher.setPosition(stillPosition);
                sleep(pusherMoveTime);
                step = 32;
            }

            if(step == 23 && robot.colorSensor.blue() < robot.colorSensor.red()) {
                robot.leftPusher.setPosition(pushPosition);
                sleep(pusherMoveTime);
                robot.leftPusher.setPosition(stillPosition);
                sleep(pusherMoveTime);
                step = 32;
            }

            if(step == 32) {;;
                robot.rightMotor.setPower(-speed);
                robot.leftMotor.setPower(-speed);
                step++;
            }
            if(step == 33 && distance == platformHorizontal) {
                robot.rightMotor.setPower(stop);
                robot.leftMotor.setPower(stop);
                step++;
            }
            if(step == 34) {;
                robot.rightMotor.setPower(-turnSpeed) ;
                robot.leftMotor.setPower(turnSpeed);
                robot.leftShooter.setPower(0.5*SHOOTER_ON);
                robot.rightShooter.setPower(0.5*SHOOTER_ON);
                sleep(ninetyTurn);
                robot.rightMotor.setPower(stop);
                robot.leftMotor.setPower(stop);
                robot.leftShooter.setPower(SHOOTER_ON);
                robot.rightShooter.setPower(SHOOTER_ON);
                step++;
            }

            if(step == 35 && distance == shootingDistance) {
                robot.rightMotor.setPower(stop);
                robot.leftMotor.setPower(stop);
                step++;
            }
            if(step == 36) {
                robot.kicker.setPosition(KICKER_RAISED_POSITION);
                sleep(kickerTime);
                robot.kicker.setPosition(KICKER_LOWERED_POSITION);
                sleep(kickerTime);
                step++;
            }
            if(step == 37) {
                robot.elevator.setPower(ELEVATOR_POWER);
                sleep(elevatorTime);
                robot.elevator.setPower(stop);
                step++;
            }
            if(step == 38) {
                robot.kicker.setPosition(KICKER_RAISED_POSITION);
                sleep(kickerTime);
                robot.kicker.setPosition(KICKER_LOWERED_POSITION);
                sleep(kickerTime);
                step++;
            }
            if(step == 39) {
                sleep(exitTime);
                robot.leftShooter.setPower(stop);
                robot.rightShooter.setPower(stop);
            }
            if(step == 40) {
                robot.rightMotor.setPower(speed);
                robot.leftMotor.setPower(speed);
                step++;
            }
            if(step == 41 && distance == platformVertical) {
                robot.rightMotor.setPower(stop);
                robot.leftMotor.setPower(stop);
                step++;
            }
            if(step == 42) {
                break;
            }
            // Pause for metronome tick.  10 mS each cycle = update 100 times a second.
            robot.waitForTick(10);
        }
        */
        }
    }
}
