// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.MecanumSubsystem;
import frc.robot.subsystems.Shooter;

public class MecanumCommand extends CommandBase {

  private final MecanumSubsystem mecanumSubsystem;

  // sticks
  public double stickX;
  public double stickY;

  // joystick
  public Joystick joystick;

  // rotation
  public double rotation;

  // right motors
  public double frontRightPower;
  public double backRightPower;

  // left motors
  public double frontLeftPower;
  public double backLeftPower;

  private double rightStick;

  /** Creates a new MecanumCommand. */
  public MecanumCommand(MecanumSubsystem mecanum) {
    mecanumSubsystem = mecanum;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(mecanum);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
    joystick = new Joystick(Constants.JOYSTICKID);
    
  }
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    // getting input values
    stickX = joystick.getRawAxis(Constants.LEFTSTICKX);
    stickY = -1 * joystick.getRawAxis(Constants.LEFTSTICKY);
    rightStick = joystick.getRawAxis(Constants.RIGHTSTICK);
    
    // idfk (thanks damian)
    rotation = (joystick.getRawAxis(Constants.RT) - joystick.getRawAxis(Constants.LT));
    
    // setting the speeds
    MecanumSubsystem.setSpeeds(stickX, stickY, rotation, 0.25);
    Shooter.shooterSpeed(0, rightStick);
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
 