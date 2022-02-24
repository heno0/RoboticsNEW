// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveSubsystem;

public class DriveTrain extends CommandBase {
  private final DriveSubsystem driveSubsystem;

  /** Creates a new DriveTrain. */

  public DriveTrain(DriveSubsystem drive) {
    driveSubsystem = drive;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // *creating necessary variables*
  // joystick
  Joystick joystick = new Joystick(Constants.JOYSTICKID);
  
  // triggers
  Double rightTrigger = joystick.getRawAxis(Constants.RT);
  Double leftTrigger = joystick.getRawAxis(Constants.LT);
  leftTrigger = leftTrigger * -1;

  // sticks
  Double leftStick = joystick.getRawAxis(Constants.LEFTSTICK);
  leftStick = leftStick * -1;
  Double rightStick = joystick.getRawAxis(Constants.RIGHTSTICK);
  rightStick = rightStick * -1;


  // *calculations*
  // total forward or back speed
  Double baseSpeed = rightTrigger + leftTrigger;

  // right wheels speed
  Double rightSpeed = baseSpeed + rightStick;

  // left wheels speed 
  Double leftSpeed = baseSpeed + leftStick;

  // set wheel speeds
  driveSubsystem.setMotorSpeeds(rightSpeed, leftSpeed);
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