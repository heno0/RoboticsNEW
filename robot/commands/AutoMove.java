// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
  



import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.MecanumSubsystem;

public class AutoMove extends CommandBase {
  private double initialPosition;
  private double currentPosition;
  private double targetPosition;

  private final double xx;
  private final double yy;
  private final double rrotation;

  private PIDController xPID;
  private PIDController yPID;
  private PIDController rotationPID;

  private double moveX;
  private double moveY;
  private double moveRotation;



  /** Creates a new AutoMove. */
  public AutoMove(final double x, final double y, final double rotation, MecanumSubsystem mecanum) {
    // Use addRequirements() here to declare subsystem dependencies.
    xx = x;
    yy = y;
    rrotation = rotation;
    addRequirements(mecanum);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // initial position for get odometry, this will never change
    initialPosition = MecanumSubsystem.RFE.getPosition(); 

    // target position from initial position and the meters of where it wants to go
    targetPosition = initialPosition+yy;

    // creates pid controllers with for X, Y, and rotation for the driving
    //xPID = new PIDController(1, 0, 0);
    yPID = new PIDController(0.5, 0.1, 0.1);
    //rotationPID = new PIDController(1, 0, 0);
    
    // set the point for where the pid controllers want to go, they will calculate for this point
    //xPID.setSetpoint(targetPosition.getX());
    yPID.setSetpoint(targetPosition);
    //rotationPID.setSetpoint(targetPosition.getRotation().getDegrees());
    //yPID.setIntegratorRange(-0.3, 0.3);
  }

  
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // gets where the current position is since its in periodic
    currentPosition = MecanumSubsystem.RFE.getPosition();

    
    
      // move calculations
      moveY = yPID.calculate(currentPosition);

      
      moveY = Constants.maxmin(moveY, 1);

      SmartDashboard.putNumber("move y", moveY);
      SmartDashboard.putNumber("Encoder", currentPosition);
      SmartDashboard.putNumber("error", yPID.getPositionError());

      MecanumSubsystem.setSpeeds(0, moveY, 0, 0.1);
    
  }


  // Called once the command ends or is interrupted.
  @Override
  public void end(final boolean interrupted) {
    MecanumSubsystem.setSpeeds(0, 0, 0, 0.25);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return yPID.atSetpoint();
  }
}