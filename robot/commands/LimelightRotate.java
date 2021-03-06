// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.MecanumSubsystem;
import frc.robot.subsystems.Shooter;

public class LimelightRotate extends CommandBase {
  /** Creates a new LimelightRotate. */

  double xOffset;
  double threshold = .5;
  double adjustRotation;


  static MecanumSubsystem _mecanum;

  
  
  public LimelightRotate(MecanumSubsystem mecanum) {
    // Use addRequirements() here to declare subsystem dependencies.
    _mecanum = mecanum;
    addRequirements(mecanum);
  }


  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Limelight.enableLimelight();
    // x offset from target and limelight crosshair
    xOffset = Limelight.getTX();


    if (Math.abs(xOffset) > threshold) {
      // https://www.desmos.com/calculator/jmnqcraj3g
      // if x offset deg is greater than threshold then turn at offset/83
      adjustRotation = xOffset/83;

      SmartDashboard.putNumber("limelight rotation", adjustRotation);
      
      MecanumSubsystem.setSpeeds(0, 0, adjustRotation, .020);
    } else {
      adjustRotation = 0;
      MecanumSubsystem.setSpeeds(0, 0, adjustRotation, .1);
    }
    SmartDashboard.putNumber("limelight TX 2", xOffset);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Limelight.disableLimelight();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
