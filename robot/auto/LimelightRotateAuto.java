// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.auto;

import java.lang.invoke.ConstantBootstraps;

import com.fasterxml.jackson.databind.introspect.ConcreteBeanPropertyBase;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.commands.LimelightRotate;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.MecanumSubsystem;

public class LimelightRotateAuto extends CommandBase {
  /** Creates a new LimelightRotate. */

  double xOffset;
  double threshold = 4;
  double adjustRotation;

  static MecanumSubsystem _mecanum;

  private boolean isFinished;
  
  public LimelightRotateAuto(MecanumSubsystem mecanum) {
    // Use addRequirements() here to declare subsystem dependencies.
    _mecanum = mecanum;
    addRequirements(mecanum);

    isFinished = false;
  }


  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Limelight.enableLimelight();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    xOffset = Limelight.getTX();

    if (Math.abs(xOffset) > threshold) {
      // https://www.desmos.com/calculator/jmnqcraj3g
      // if x offset deg is greater than threshold rotate at offset/83
      adjustRotation = xOffset/83;
      SmartDashboard.putNumber("limelight rotation", adjustRotation);
      
      adjustRotation = Constants.maxmin(adjustRotation, 1);

      MecanumSubsystem.setSpeeds(0, 0, adjustRotation, .036);
    } else if (Math.abs(xOffset) < threshold) {
      MecanumSubsystem.setSpeeds(0, 0, 0, .1);
      isFinished = true;
    } else {
      MecanumSubsystem.setSpeeds(0, 0, .25, .01);
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
    return isFinished;
  }
}