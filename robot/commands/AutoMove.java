// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
  

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.MecanumSubsystem;

public class AutoMove extends CommandBase {
  private Pose2d initialPosition;
  private Pose2d currentPosition;
  private Pose2d targetPosition;

  private final double xx;
  private final double yy;
  private final Rotation2d rrotation;

  private PIDController xPID;
  private PIDController yPID;
  private PIDController rotationPID;


  private double moveX;
  private double moveY;
  private double moveRotation;


  /** Creates a new AutoMove. */
  public AutoMove(final double x, final double y, final Rotation2d rotation) {
    // Use addRequirements() here to declare subsystem dependencies.
    xx = x;
    yy = y;
    rrotation = rotation;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // creates initial and target position based on values given when command called
    initialPosition = MecanumSubsystem.getOdometry();
    targetPosition = new Pose2d(initialPosition.getX() + xx, initialPosition.getY() + yy, initialPosition.getRotation().plus(rrotation));


    xPID = new PIDController(1, 1, 1);
    yPID = new PIDController(1, 1, 1);
    rotationPID = new PIDController(1, 1, 1);
    
    xPID.setSetpoint(targetPosition.getX());
    yPID.setSetpoint(targetPosition.getY());
    rotationPID.setSetpoint(targetPosition.getRotation().getDegrees());

  }

  
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    currentPosition = MecanumSubsystem.getOdometry();

    if (xPID.atSetpoint() && yPID.atSetpoint() && rotationPID.atSetpoint()) {
      System.out.println("done");
      MecanumSubsystem.setSpeeds(0, 0, 0, 0);
    }
    else {
      // move calculations
      moveX = xPID.calculate(currentPosition.getX());
      moveY = yPID.calculate(currentPosition.getY());
      moveRotation = rotationPID.calculate(currentPosition.getRotation().getDegrees());

      moveX = Constants.maxmin(moveX, 1);
      moveY = Constants.maxmin(moveY, 1);
      moveRotation = Constants.maxmin(moveRotation, 1);

      MecanumSubsystem.setSpeeds(moveX, moveY, moveRotation, 0.1);
    }    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(final boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
