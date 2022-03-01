// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Shooter;

public class LimelightShooter extends CommandBase {
  
  static double distance;
  static double shooterSpeed;

  static double[] distanceAverageArray = {0,0,0,0,0,0,0,0,0,0};
  static int counter;

  static double total;
  static double average;
  
  static double factor = 0;
  
  /** Creates a new LimelightShooter. */
  public LimelightShooter(Shooter shooter) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Limelight.enableLimelight();
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Limelight.enableLimelight();
    
    // get area for calculations
    distance = Limelight.getTargetDistance();

    // calculate shooter speed from shooter area
    shooterSpeed = (0.7) - (0.03 * distance) + (0.002 * (distance*distance) + 0.02);

    // factor calculations
    if (RobotContainer.joystick2.getRawButtonPressed(8)) {
      factor = factor + 0.05;
    }
    else if (RobotContainer.joystick2.getRawButtonPressed(7)) {
      factor = factor - 0.05;
    }

    shooterSpeed = shooterSpeed + factor;

    // display factor
    SmartDashboard.putNumber("factor", factor);


    // set shooter speeds
    Shooter.setShooterSpeeds(shooterSpeed);

    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Shooter.setShooterSpeeds(0);
    AutoIndex.resetState();
    Limelight.disableLimelight();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  public static double getLimelightDistanceAverage() {
    if (distanceAverageArray.length == 10) {
      distanceAverageArray[counter] = Limelight.getTargetDistance();
      counter++;
      if (counter == 10) {
        counter = 0;
      } 
    }
    for (int i = 0; i < (distanceAverageArray.length); i++) {
      total = distanceAverageArray[i];
    }
    return total/distanceAverageArray.length;
  }
}
