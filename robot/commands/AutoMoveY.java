// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
  



import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.revrobotics.SparkMaxPIDController;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.MecanumSubsystem;

public class AutoMoveY extends CommandBase {
  private double initialPosition;
  private double currentPosition;
  private double targetPosition;

  private double yy;

  private PIDController yPID;

  private double moveY;




  /** Creates a new AutoMove. */
  public AutoMoveY(MecanumSubsystem mecanum, final double yy) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.yy = (yy/Constants.WHEELCIRCUMFRENCE)*Constants.ROTATIONSRATIO;

    addRequirements(mecanum);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // initial position for get odometry, this will never change
    initialPosition = MecanumSubsystem.RFE.getPosition();

    yy = yy / (0.5*Math.PI);  //feet divided by feed per rotation
    // target position from initial position and the meters of where it wants to go
    targetPosition = initialPosition-yy;

    // creates pid controllers with for X, Y, and rotation for the driving
    yPID = new PIDController(0.5, 0.1, 0.1);

    yPID.setTolerance(3);
    
    // set the point for where the pid controller wants to go, they will calculate for this point
    yPID.setSetpoint(targetPosition);
  }

  
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // gets where the current position is since its in periodic
    currentPosition = MecanumSubsystem.RFE.getPosition();

    // move calculations
    moveY = yPID.calculate(currentPosition);
    
    moveY = Constants.maxmin(moveY, .3);

    SmartDashboard.putNumber("current position", currentPosition);
    SmartDashboard.putNumber("target", targetPosition);

    SmartDashboard.putNumber("y", moveY*Math.signum(yy));
    MecanumSubsystem.setSpeeds(0, Math.abs(moveY)*Math.signum(yy)*-1, 0, 0.1);
  
  }


  // Called once the command ends or is interrupted.
  @Override
  public void end(final boolean interrupted) {
    MecanumSubsystem.setSpeeds(0, 0, 0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return yPID.atSetpoint();
  }
}