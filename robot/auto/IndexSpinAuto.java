package frc.robot.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Sensors;

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.







public class IndexSpinAuto extends CommandBase {


  private Indexer indexC;
  private boolean isFinished;
  
  
  /** Creates a new Intake. */
  public IndexSpinAuto(Indexer index) {
    // Use addRequirements() here to declare subsystem dependencies.dddr
    indexC = index;
    addRequirements(index);
    isFinished = false;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Indexer.enableIndexer(-0.9);
    isFinished = true;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    System.out.println("FART");
    return isFinished;
  }
  
}