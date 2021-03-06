// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.MecanumSubsystem;

public class MecanumCommand extends CommandBase {


  // sticks
  public double stickX;
  public double stickY;

  // rotation
  public double rotation;

  // right motors
  public double frontRightPower;
  public double backRightPower;

  // left motors
  public double frontLeftPower;
  public double backLeftPower;


  /** Creates a new MecanumCommand. */
  public MecanumCommand(MecanumSubsystem mecanum) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(mecanum);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    // getting input values
    stickX =-1 *  RobotContainer.joystick.getRawAxis(Constants.LEFTSTICKX);
    stickY = RobotContainer.joystick.getRawAxis(Constants.LEFTSTICKY);
    
    // rotation
    rotation = (RobotContainer.joystick.getRawAxis(Constants.RIGHTSTICKX));

    stickX = 0.9*stickX;
    stickY = 0.9*stickY;

    if (rotation >= 0){
      rotation = Math.pow(rotation,2) * .9;
    } else {
      rotation = -1*Math.pow(rotation, 2) * .9;
    }

    // setting the speeds
    MecanumSubsystem.setSpeeds(stickX, stickY, rotation, 0.2);
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}