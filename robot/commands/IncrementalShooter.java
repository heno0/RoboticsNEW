// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Shooter;

public class IncrementalShooter extends CommandBase {
  private double pow3;
  /** Creates a new IncrementalShooter. */
  public IncrementalShooter(Shooter shooter) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    pow3 = RobotContainer.joystick2.getRawAxis(Constants.RT) - RobotContainer.joystick2.getRawAxis(Constants.LT);
 
    if (pow3 > 0) {
      pow3 = (Math.pow(pow3, 2)) / 1.25 + .2;
      if (pow3 < .3) {
        pow3 = 0;
      }
    }
    else if (pow3 < 0) {
      pow3 = -((Math.pow(pow3, 2)) / 1.25) - .2;
      if (pow3 > -.3) {
        pow3 = 0;
      }
    }

    //setting motors
    Shooter.setShooterSpeeds(pow3);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
