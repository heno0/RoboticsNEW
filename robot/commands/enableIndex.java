// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Indexer;

public class enableIndex extends CommandBase {
  /** Creates a new enableIndex. */
  boolean fin;

  public enableIndex(Indexer index) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(index);
    fin = false;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Indexer.enableIndexer(0.8);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return fin;
  }
}
