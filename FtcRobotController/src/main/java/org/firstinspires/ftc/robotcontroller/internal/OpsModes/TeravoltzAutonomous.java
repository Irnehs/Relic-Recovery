/*
Copyright (c) 2016 Robert Atkinson

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Robert Atkinson nor the names of his contributors may be used to
endorse or promote products derived from this software without specific prior
written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESSFOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package org.firstinspires.ftc.robotcontroller.internal.OpsModes;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcontroller.internal.OpsModes.JacensTestHardwarePushbotTeravoltz;

/**
 * This file illustrates the concept of driving a path based on time.
 * It uses the common Pushbot hardware class to define the drive on the robot.
 * The code is structured as a LinearOpMode
 *
 * The code assumes that you do NOT have encoders on the wheels,
 *   otherwise you would use: PushbotAutoDriveByEncoder;
 *
 *   The desired path in this example is:
 *   - Drive forward for 3 seconds
 *   - Spin right for 1.3 seconds
 *   - Drive Backwards for 1 Second
 *   - Stop and close the claw.
 *
 *  The code is written in a simple form with no optimizations.
 *  However, there are several ways that this type of sequence could be streamlined,
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@Autonomous(name="Autonomous", group="Pushbot")
@Disabled
public class TeravoltzAutonomous extends LinearOpMode {

    /* Declare OpMode members. */
    HardwarePushbotTeravoltz robot = new HardwarePushbotTeravoltz();   // Use a Pushbot's hardware
    private ElapsedTime runtime = new ElapsedTime();

    //All variables listed for easy access

    //POWER Variables]
    double DRIVE_FORWARD = 1;
    double DRIVE_BACKWARD = -DRIVE_FORWARD;
    double DRIVE_STOP = 0;

    double ELEVATOR_ON = 1;
    double ELEVATOR_OFF = 0;

    double SHOOTER_ON = 0.9;
    double SHOOTER_OFF = 0;

    double KICKER_RAISED_POSITION = 0.5;
    double KICKER_LOWERED_POSITION = 0;

    double ARM_EXTEND = 1;
    double ARM_RETRACT = -ARM_EXTEND;
    double ARM_STILL = 0;

    double reset = 0;

    //TIME/DELAY Variables
    long ONE_DISTANCE = 70;        // Step 1
    //long TURN_ONE;            // Step 2
    //long TWO_DISTANCE;        // Step 3
    //long THREE_DISTANCE;      // Step 9

    long ELEVATOR_TIME = 10000;       // Step 5
    long KICKER_TIME = 2000;         // Steps 6 & 7
    long ARM_MOVEMENT_TIME = 2500;   // Steps 8 & 10

    long MSPIN = 434058935 * (10^-8);    // time in millseconds to travel one inch
    long STEP_VIEW_PAUSE = 500;  // time alloted for viewing of step

    @Override
    public void runOpMode() throws InterruptedException {

        /*
         * Initialize the drive system variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to run");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        //Stop all movement
        telemetry.addData("STEP 0", "Setting all power to 0");
        telemetry.update();
        robot.arm.setPower(reset);
        //robot.leftMotor.setPower(reset);
        //robot.rightMotor.setPower(reset);
        robot.elevator.setPower(reset);
        robot.leftShooter.setPower(reset);
        robot.rightShooter.setPower(reset);
        robot.kicker.setPosition(reset);
        sleep(STEP_VIEW_PAUSE);


        // Steps with telemetry to track progress and provide reference point for malfunctions

        /* Step 1:  Drive forward to desired parallel line
        telemetry.addData("STEP 1", "Driving forward");
        telemetry.update();
        robot.leftMotor.setPower(DRIVE_FORWARD);
        robot.rightMotor.setPower(DRIVE_FORWARD);
        sleep(ONE_DISTANCE * MSPIN);


        // Step 2:  Spin left 90 degrees
        telemetry.addData("STEP 2", "Spinning left 90 degrees");
        telemetry.update();
        robot.leftMotor.setPower(DRIVE_BACKWARD);
        robot.rightMotor.setPower(DRIVE_FORWARD);
        sleep(TURN_ONE);




        // Step 1:  Drive forward to position for shooting
        telemetry.addData("STEP 1", "Driving to shooting position");
        telemetry.update();
        robot.leftMotor.setPower(DRIVE_FORWARD);
        robot.rightMotor.setPower(DRIVE_FORWARD);
        sleep(TWO_DISTANCE * MSPIN);

        // Step 2:  Stop wheels
        telemetry.addData("STEP 2", "Stopping driving");
        telemetry.update();
        robot.leftMotor.setPower(DRIVE_STOP);
        robot.rightMotor.setPower(DRIVE_STOP);
        sleep(STEP_VIEW_PAUSE);
        /*


        // Step 1: Start Elevator and ball shooters
        telemetry.addData("STEP 1", "Carrying ball through elevator and activating shooters");
        telemetry.update();
        robot.elevator.setPower(ELEVATOR_ON);
        robot.leftShooter.setPower(SHOOTER_ON);
        robot.rightShooter.setPower(SHOOTER_ON);
        sleep(ELEVATOR_TIME);


        // Step 2: Stop elevator and activate kicker
        telemetry.addData("STEP 2", "Stopping Elevator and activating kicker");
        telemetry.update();
        robot.elevator.setPower(ELEVATOR_OFF);
        robot.kicker.setPosition(KICKER_RAISED_POSITION);
        sleep(KICKER_TIME);

        // Step 3: Stop shooters and lower kicker
        telemetry.addData("STEP 3", "Stopping shooter and lowering kicker");
        telemetry.update();
        robot.leftShooter.setPower(SHOOTER_OFF);
        robot.rightShooter.setPower(SHOOTER_OFF);
        robot.kicker.setPosition(KICKER_LOWERED_POSITION);
        sleep(STEP_VIEW_PAUSE);

        // Step 4: Extend arm
        telemetry.addData("STEP 4", "Extending arm");
        telemetry.update();
        robot.arm.setPower(ARM_EXTEND);
        sleep(ARM_MOVEMENT_TIME);
        robot.arm.setPower(ARM_STILL);
        sleep(STEP_VIEW_PAUSE);


        // Step 5: Knock off Ball and Park
        telemetry.addData("STEP 5", "Knocking off ball and parking");
        telemetry.update();
        robot.leftMotor.setPower(DRIVE_FORWARD);
        robot.rightMotor.setPower(DRIVE_FORWARD);
        sleep(ONE_DISTANCE * MSPIN);
        robot.leftMotor.setPower(DRIVE_STOP);
        robot.rightMotor.setPower(DRIVE_STOP);
        sleep(STEP_VIEW_PAUSE);

        //Step 6: Retract arm
        telemetry.addData("STEP 6", "Retracting arm");
        telemetry.update();
        robot.arm.setPower(ARM_RETRACT);
        sleep(ARM_MOVEMENT_TIME);
        sleep(STEP_VIEW_PAUSE);

        //Finished (7)
        telemetry.addData("COMPLETE","YAY!");
        telemetry.update();
        sleep(STEP_VIEW_PAUSE);
        */
    }
}

