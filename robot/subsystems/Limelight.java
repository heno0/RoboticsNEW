// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class Limelight extends SubsystemBase {
  // table
  private NetworkTable table;

  // other values from table
  private boolean ifTarget;

  private double xOffset;
  private double yOffset;
  private static double area;


  // drive values
  private double adjustX;
  private double adjustRotation;
  
  private static double horizontal;
  private static double vertical;

  private Joystick joystick;

  /** Creates a new Limelight. */
  public Limelight() {
    // assign table value
    table = NetworkTableInstance.getDefault().getTable("limelight");
    
    adjustRotation = 0;
    adjustX = 0;

    joystick = RobotContainer.joystick;
    
  }
  public void enableLimelight() {
    table.getEntry("ledMode").setNumber(3);
  }
  public void disableLimelight() {
    table.getEntry("ledMode").setNumber(1);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    
    // assign values from table
    // offset from crosshair
    xOffset = table.getEntry("tx").getDouble(0.0);
    yOffset = table.getEntry("ty").getDouble(0.0);
    // amount of space the target fills
    area = table.getEntry("ta").getDouble(0.0);

    horizontal = table.getEntry("thor").getDouble(0.0);
    vertical = table.getEntry("tvert").getDouble(0.0);

    ifTarget = table.getEntry("tv").getBoolean(false);

    SmartDashboard.putNumber("vertical", vertical);
    SmartDashboard.putNumber("horizontal", horizontal);
    SmartDashboard.putNumber("x offset", xOffset);
    SmartDashboard.putNumber("y offset", yOffset);
    SmartDashboard.putBoolean("if target", ifTarget);
    
    
    /*
    if (joystick.getRawButtonPressed(Constants.ABUTTON) == true) {
      limelightCenter();
    } else if (joystick.getRawButtonPressed(Constants.ABUTTON) == false) {
      disableLimelight();
    } **/

    MecanumSubsystem.setSpeeds(0, adjustX, adjustRotation, .1);
  }

  public void limelightCenter() {
    enableLimelight();
    if (ifTarget) {
      // https://www.desmos.com/calculator/jmnqcraj3g
      adjustRotation = xOffset*0.05;
    } else if (ifTarget == false) {
      adjustRotation = 0;
    }
  }
  public static double getTargetArea() {
    area = horizontal * vertical;
    return area;
  }
}