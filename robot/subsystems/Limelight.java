// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Limelight extends SubsystemBase {
  // table
  private NetworkTable table;

  // other values from table
  private boolean ifTarget;

  private double rotation;
  private double xOffset;
  private double yOffset;
  private double area;

  private Joystick joystick;


  private double moveRotation;

  private double error;
  private double adjust;
  
  /** Creates a new Limelight. */
  public Limelight() {
    // assign table value
    table = NetworkTableInstance.getDefault().getTable("limelight");
    
    joystick = new Joystick(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    
    // assign values from table
    ifTarget = table.getEntry("tv").getBoolean(false);
    rotation = table.getEntry("ts").getDouble(0.0);
    xOffset = table.getEntry("tx").getDouble(0.0);
    yOffset = table.getEntry("ty").getDouble(0.0);
    area = table.getEntry("ta").getDouble(0.0);

    if (joystick.getRawButtonPressed(Constants.ABUTTON)) {
      error = -xOffset;
      adjust = 0.0;

      if (xOffset > 1.0) {
        adjust = -0.1 * error - .5;
      } 
      if (xOffset < 1.0) {
        adjust = -0.1* error + .5;
      }
      MecanumSubsystem.setSpeeds(0, 0, adjust, 0.1);
    }
  }
}
