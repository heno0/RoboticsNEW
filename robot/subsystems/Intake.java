// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.IntakeCommand;

public class Intake extends SubsystemBase {
  private Spark motor;
  /** Creates a new Intake. */
  public Intake() {
    setDefaultCommand(new IntakeCommand(true));
    motor = new Spark(0);
    motor.set(0.5);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
