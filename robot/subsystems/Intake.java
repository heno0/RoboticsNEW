// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.commands.IntakeCommand;

public class Intake extends SubsystemBase {
  private Spark motor;

  // intake is used to check if the intake motors are on or off
  private boolean intake;
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
    // setting the intake
    if (RobotContainer.joystick.getPOV() == 90) {
      // if joystick dpad is right, turn on
      motor.set(-1);
      intake = true;
    } else if (RobotContainer.joystick.getPOV() == 270) {
      // if its left, turn off
      motor.set(0);
      intake = false;
    }
    // set intake variables
    SmartDashboard.putBoolean("Intake", intake);
  }
}