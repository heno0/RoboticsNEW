// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.commands.AutoIndex;

public class Indexer extends SubsystemBase {
  private double indexSpeed = 0;
  private static Spark logMotor;




  /** Creates a new Indexer. */
  public Indexer() {
    
    // log ride motor
    logMotor = new Spark(1);
    
    setDefaultCommand(new AutoIndex(this));
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    
    
    intermittentIndexerIncrease();
  }
  
  private void intermittentIndexerIncrease() {
    indexSpeed = RobotContainer.joystick2.getRawAxis(Constants.LEFTSTICKY);
   
    if (Math.abs(indexSpeed) < 0.25){
      indexSpeed = 0;
      AutoIndex.resetState();
    }
    if (RobotContainer.joystick2.getRawButton(Constants.RBUMPER)) {
      indexSpeed = 0.4;
      AutoIndex.resetState();
    }
    enableIndexer(indexSpeed);
  }



  public static void enableIndexer(double speed) {
    SmartDashboard.putNumber("INDEX enable Speed", speed);
    logMotor.set(-speed);
  }

}
