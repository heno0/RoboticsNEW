// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.auto.DoNothing;
import frc.robot.auto.IndexSpinAuto;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.MecanumSubsystem;
import frc.robot.subsystems.Shooter;

public class AutoPlan extends SequentialCommandGroup {
  /** Creates a new AutoPlan. */
  public AutoPlan(MecanumSubsystem meca, Indexer index, Intake intake, Shooter shooter) {
    // Use addRequirements() here to declare subsystem dependencies.
    addCommands(
      new IntakeDown(intake), //intake down

      new ParallelRaceGroup(
        new AutoMoveY(meca, 100), //have to figure out distance 100 belongs to. Conversion didn't work. Should never reach this distance unless Color not detected
        new enableIntake(intake) //intake balls till color found, triggers end of race group
      ),
      //new AutoMoveY(meca, 50), //distance in feet
       new ParallelRaceGroup(
        new DoNothing(75), //could remove this and just have AutoMoveRot keep spinning till Limelight found
        new AutoMoveRot(meca)),
      
        new ParallelCommandGroup(
          new LimelightRotate(meca), //commented out because Windows would serve as target
          new DoNothing(50)
        ),
      new LimelightShooter(shooter, false), //spin shooter
      new DoNothing(50),
      new ParallelRaceGroup(
        new DoNothing(200),
        new enableIndex(index) //spin index for 200 count
      ),
      new LimelightShooter(shooter, true) //disable shooter

    );
  }
}
