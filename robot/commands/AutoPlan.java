// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class AutoPlan extends SequentialCommandGroup {
  /** Creates a new AutoPlan. */
  public AutoPlan() {
    // Use addRequirements() here to declare subsystem dependencies.
    addCommands(
      new AutoMove(-2, 10, new Rotation2d(90))
    );
  }
}
