// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Shooter;

public class LimelightShooterAuto extends CommandBase {
  
  static double distance;
  static double shooterSpeed;

  private boolean isFinished;
  private boolean shooterOff;
  /** Creates a new LimelightShooter. */
  public LimelightShooterAuto(Shooter shooter, boolean shooterOff) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(shooter);
    isFinished = false;
    this.shooterOff = shooterOff;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // get area for calculations


    // get target distance from limelight
    distance = Limelight.getTargetDistance();

    // calculate shooter speed from shooter area
    shooterSpeed = Limelight.getShooterSpeed(); 

    if (shooterSpeed > 1) {
      shooterSpeed = 1;
    }
    else if (shooterSpeed < -1) {
      shooterSpeed = -1;
    }

    // set shooter speeds

    if (shooterOff){
      Shooter.setShooterSpeeds(0);
    } else {
      Shooter.setShooterSpeeds(.75);
    }

    isFinished = true;

    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return isFinished;
  }
}