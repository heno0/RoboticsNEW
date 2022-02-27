// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import edu.wpi.first.math.trajectory.TrapezoidProfile.Constraints;
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
    
    if (RobotContainer.joystick2.getRawButton(Constants.RBUMPER)) {
      AutoIndex.resetState();
      enableIndexer(.7);
    }
    else if (RobotContainer.joystick2.getRawButton(Constants.LBUMPER)) {
      enableIndexer(-0.7);
    }

    if (RobotContainer.joystick2.getRawButtonReleased(Constants.LBUMPER)||RobotContainer.joystick2.getRawButtonReleased(Constants.RBUMPER)){
      enableIndexer(0);
    }

    if (RobotContainer.joystick.getRawButtonPressed(7)){
      AutoIndex.resetState();
    }
  }
  


  public static void enableIndexer(double speed) {
    SmartDashboard.putNumber("INDEX enable Speed", speed);
    logMotor.set(-speed);
  }
  private static boolean colorState = false;
  public static boolean colorSensorCheck() {
    // returns true if it is the wanted color, false if it is unwanted
    if (Sensors.determineColour() == Constants.WANTEDCOLOR) {
      colorState = true;
    } 
    else if (Sensors.determineColour() == Constants.OPPS) {
      colorState = false;
    }

    return colorState;
  }

}
