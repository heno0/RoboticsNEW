// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import org.opencv.core.Mat;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.MecanumSubsystem;

public class MecanumCommand extends CommandBase {

  private final MecanumSubsystem mecanumSubsystem;

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
    mecanumSubsystem = mecanum;
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

    stickX = 0.888*(stickX-0.1)+0.2;
    stickY = 0.888*(stickY-0.1)+0.2;

    if (rotation >= 0){
      rotation = Math.pow((rotation-0.1),2) + 0.2;
    } else {
      rotation = -1*Math.pow((rotation+0.1), 2) - 0.2;
    }

    if (Math.abs(rotation) > 0 || Math.abs(stickX) > 0 || Math.abs(stickY) > 0) {
      // setting the speeds


      MecanumSubsystem.setSpeeds(stickX, stickY, rotation, 0.25);
    }
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