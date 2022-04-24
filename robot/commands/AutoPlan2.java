// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.auto.AutoShooter;
import frc.robot.auto.DisableIndexer;
import frc.robot.auto.DoNothing;
import frc.robot.auto.IndexSpinAuto;
import frc.robot.auto.IntakeCommandAuto;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.MecanumSubsystem;
import frc.robot.subsystems.Shooter;

public class AutoPlan2 extends SequentialCommandGroup {
  /** Creates a new AutoPlan2. */
  public AutoPlan2(MecanumSubsystem meca, Indexer index, Shooter shooter, Intake intake) {
    // Use addRequirements() here to declare subsystem dependencies.
    addCommands(
      new IntakeCommandAuto(intake),
      // -65
      new AutoMoveY(meca, -140),
      
      new AutoShooter(shooter, .71),
      new DoNothing(100),
      new IndexSpinAuto(index),
      new DoNothing(200),
      new DisableIndexer(index)
    );
    System.out.println("done");
  }
}
