// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Sensors;

public class AutoIndex extends CommandBase {
  static double indexState = 0;
  boolean colorState = false;
  String stateString;

  /** Creates a new AutoIndex. */
  public AutoIndex(Indexer indexer) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(indexer);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    // to get to index state 1 (one ball loaded but can't be shot yet)
    if (indexState == 0) {
      if (Indexer.colorSensorCheck() && Sensors.getDistanceNear()) {
        indexState = 1;
        Indexer.enableIndexer(-.7);
      }
    }

    // to get to index state 2 (one ball loaded to be shot)
    if (indexState == 1) {
      if (Sensors.getDistanceLong()) {
        indexState = 2;
        Indexer.enableIndexer(0);
      }
    }

    // to get to index state 3 (two balls loaded, one can be shot)
    if (indexState == 2) {
      if (Indexer.colorSensorCheck() && Sensors.getDistanceNear() && Sensors.getDistanceLong()) {
        indexState = 3;
        Indexer.enableIndexer(0);
      }
    }

    // if index state 2 or 3, no balls detected then reset state
    if (indexState == 3 || indexState == 2) {
      if (Sensors.getDistanceNear() == false && Sensors.getDistanceLong() == false) {
        indexState = 0;
      }
    }

    SmartDashboard.putString("State", stateToString());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  

  public String stateToString() {
    // gives information about the state and what that means for the robot
    // made because of boredom : (
    if (indexState == 0) {
      stateString = "(0) No ball loaded";
    }
    else if (indexState == 1) {
      stateString = "(1) 1 ball at early distance sensor";
    }
    else if (indexState == 2) {
      stateString = "(2) 1 ball at late distance sensor";
    }
    else if (indexState == 3) {
      stateString = "(3) 2 balls loaded";
    }
    return stateString;
  }

  public static void resetState() {
    indexState = 0;
  }
}
