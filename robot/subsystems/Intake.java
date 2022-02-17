// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.commands.IntakeCommand;

public class Intake extends SubsystemBase {
  private Spark motor;
  private String wantedColour = Constants.WANTEDCOLOR;
  private String opps = Constants.OPPS;

  private String color;
  // intake is used to check if the intake motors are moving in, out, or not moving
  private String intake;
  //double intakeSpeed = 0.0;
  /** Creates a new Intake. */
  public Intake() {
    // set motor
    motor = new Spark(0);


    // set command for the intake command
    setDefaultCommand(new IntakeCommand(this));

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    // get color from color sensor
    color = Sensors.determineColour();
    // setting the intake
    if (RobotContainer.joystick2.getPOV() == 90/* || color == wantedColour*/) {
      // if joystick dpad is right, turn on
      // if sensors say that the wanted color is detected, pull ball in
      motor.set(-1);
      intake = "Rotating in";
    } else if (RobotContainer.joystick2.getPOV() == 270/* || color == opps*/) {
      // if its left, turn off;
      // if sensors say that the wanted color is not detected, push ball away
      motor.set(.6  );
      intake = "Rotating out";
    } else {
      // if there is no input then turn off the motor
      intake = "n/a";
      motor.set(0);
    }
    // set intake variables
    SmartDashboard.putString("Intake", intake);
  }
  
}