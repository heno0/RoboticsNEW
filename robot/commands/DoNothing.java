// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class DoNothing extends CommandBase {
  /** Creates a new DoNothing. */

  private int skip;
  private int current;

  boolean fin;

  public DoNothing(int s) {
    // Use addRequirements() here to declare subsystem dependencies.
    skip = s;
    fin = false;
    current = 0;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  if (current < skip) {
    current++;
  }
  else {
    fin = true;
  }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return fin;
  }
}
