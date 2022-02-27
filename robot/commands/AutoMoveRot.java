// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.MecanumSubsystem;

public class AutoMoveRot extends CommandBase {
  private AHRS navx;
  private double initialRotation;
  private double currentRotation;

  private double target;
  private double targetPos;

  private PIDController rPID;

  private double moveR;
  private boolean isFinished;

  private int counter = 0;
  /** Creates a new AutoMoveRot. */
  public AutoMoveRot(MecanumSubsystem meca) {
    // Use addRequirements() here to declare subsystem dependencies.
    isFinished = false;
    addRequirements(meca);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    MecanumSubsystem.setSpeeds(0, 0, 0.25, 0.2);
    Limelight.enableLimelight();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    if(Limelight.ifTarget()){
      
      counter++;
      if (counter > 5){
        isFinished = true;
      }
    } else {
      counter=0;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    MecanumSubsystem.setSpeeds(0, 0, 0, 0);
    Limelight.disableLimelight();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return isFinished;
  }

  public void putNumby(String str, float num) {
    SmartDashboard.putNumber(str, num);
  }
}
