// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.beans.*;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.commands.AutoIndex;

public class Indexer extends SubsystemBase {
  private double indexSpeed = 0;
  private double increment = 0.1;
  private static Spark logMotor;


  private static int indexState = 0;

  private boolean flag = false;


  /** Creates a new Indexer. */
  public Indexer() {
    
    // log ride motor
    logMotor = new Spark(1);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    
    
    intermittentIndexerIncrease();
    setDefaultCommand(new AutoIndex(this));
  }
  
  private void intermittentIndexerIncrease() {
    indexSpeed = RobotContainer.joystick2.getRawAxis(Constants.LEFTSTICKY);
   
    if (Math.abs(indexSpeed) < 0.25){
      indexSpeed = 0;
      resetState();
    }
    if (RobotContainer.joystick2.getRawButton(Constants.RBUMPER)) {
      indexSpeed = 0.8;
      resetState();
    }
    enableIndexer(indexSpeed);
  }



  public static void enableIndexer(double speed) {
    SmartDashboard.putNumber("INDEX enable Speed", speed);
    logMotor.set(-speed);
  }
  public static void resetState() {
    indexState = 0;
  }

}
