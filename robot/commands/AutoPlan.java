// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.auto.DoNothing;
import frc.robot.auto.IndexSpinAuto;
import frc.robot.auto.IntakeCommandAuto;
import frc.robot.auto.IntakeSpinAuto;
import frc.robot.auto.LimelightRotateAuto;
import frc.robot.auto.LimelightShooterAuto;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.MecanumSubsystem;
import frc.robot.subsystems.Shooter;

public class AutoPlan extends SequentialCommandGroup {
  /** Creates a new AutoPlan. */
  public AutoPlan(MecanumSubsystem meca, Indexer index, Intake intake, Shooter shooter) {
    // Use addRequirements() here to declare subsystem dependencies.
    addCommands(
      new IntakeCommandAuto(intake),

      new ParallelRaceGroup(
        new AutoMoveY(meca, 100),
        new IntakeSpinAuto(intake)
      ),
      //new AutoMoveY(meca, 50), //distance in feet
      new ParallelRaceGroup(
        new DoNothing(75), 
        new AutoMoveRot(meca)),
        new ParallelCommandGroup(
          new LimelightRotateAuto(meca),
          new DoNothing(50)
      ),
      new LimelightShooterAuto(shooter, false),
      new DoNothing(50),
      new ParallelRaceGroup(
        new DoNothing(200),
        new IndexSpinAuto(index)
      ),
      new LimelightShooterAuto(shooter, true)

    );
  }
}
