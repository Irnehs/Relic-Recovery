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
@Disabled
public class Coordinate extends LinearOpMode {

    /* Declare OpMode members. */
    HardwarePushbotTeravoltz robot           = new HardwarePushbotTeravoltz();   // Use a Pushbot's hardware
    String Version = "0.0.3";

    // could also use HardwarePushbotMatrix class.

    @Override
    public void runOpMode() throws InterruptedException {
        //Start Variables
        double speed = 1;



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
        public void forward(double speed) {
            robot.leftMotor.setPower(speed);
            robot.rightMotor.setPower(speed);
        }

        public void backward(double speed) {
            robot.leftMotor.setPower(-speed);
            robot.rightMotor.setPower(-speed);
        }

        public void still() {
            robot.leftMotor.setPower(0);
            robot.rightMotor.setPower(0);
        }

        public void turnLeft(double TurnSpeed, long angle) {
            robot.leftMotor.setPower(-TurnSpeed);
            robot.rightMotor.setPower(TurnSpeed);
            sleep((360/angle)*3600);
            still();
        }

        public void turnRight(double TurnSpeed, long angle) {
            robot.leftMotor.setPower(TurnSpeed);
            robot.rightMotor.setPower(-TurnSpeed);
            sleep((360/angle)*3600);
            still();
        }

        public void point(double x, double y) {
            while(robot.frontSensor.getLevel() > y) {
                forward(1);
            }
            still();
            if(robot.sideSensor.getLevel() == x) {
                return;
            }
            if(robot.sideSensor.getLevel() > x){
                turnRight(1,90);
                while(robot.frontSensor.getLevel() > x) {
                    forward(1);
                }
                still();
            }
            else if(robot.sideSensor.getLevel() < x) {
                turnLeft(1,90);
                while(robot.frontSensor.getLevel() < x) {
                    forward(1);
                }
                still();
            }
            return;

    }


        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            /*
            if(step ==  ) {

                step++;
            }
            */
            //Live variables



            //Steps
            point(10,10);


            // Pause for metronome tick.  10 mS each cycle = update 100 times a second.
            robot.waitForTick(10);
        }
    }
}
