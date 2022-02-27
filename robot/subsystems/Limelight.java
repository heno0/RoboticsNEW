// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.LimelightShooter;


public class Limelight extends SubsystemBase {
  // table
  private static NetworkTable table;

  // other values from table
  private static boolean ifTarget;

  private double xOffset;
  private double yOffset;
  private static double area;


  // drive values
  private double adjustRotation;

  private double threshold;
  
  private static double horizontal;
  private static double vertical;
  
  static double[] horizontalAve = {0,0,0,0,0,0,0,0,0,0};
  static double[] verticalAve = {0,0,0,0,0,0,0,0,0,0};
  static int counter = 0;
  static int counter2 = 0;

  static double averageH;
  static double averageV;

  double total = 0;
  double total2 = 0;

  int count = 0;
  int count2 = 0;

  static double distance = 0;

  double x;

  /** Creates a new Limelight. */
  public Limelight() {
    // assign table value
    table = NetworkTableInstance.getDefault().getTable("limelight");
    
    adjustRotation = 0;
    threshold = 3;
    disableLimelight();


    
  }
  public static void enableLimelight() {
    table.getEntry("ledMode").setNumber(3);
  }
  public static void disableLimelight() {
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
    
    
    // get average of 10 values of horizontal
   if (horizontalAve.length == 10) {
      horizontalAve[counter2] = horizontal;
      counter2++;
      if (counter2 == 10) {
        counter2 = 0;
      }
    }
    for (int i = 0; i < (horizontalAve.length-1); i++) {
      total =+ horizontalAve[i];
    }
    averageH = total/(horizontalAve.length-1);

    // get average of 10 values of vertical
   if (verticalAve.length == 10) {
      verticalAve[count2] = vertical;
      count2++;
      if (count2 == 10) {
        count2 = 0;
      }
    }
    for (int u = 0; u < (verticalAve.length-1); u++) {
      total2 =+ verticalAve[u];
    }
    averageV = total2/(verticalAve.length-1);

    // get horizontal from limelight
    x = horizontal;
    // (a) - (bx) + (cx * x^2)
    distance = (43.2) - (0.389 * (x)) + ((0.0011) * x*x);


    SmartDashboard.putNumber("detected distance", distance);
    SmartDashboard.putNumber("average vertical", averageV);
    SmartDashboard.putNumber("average horizontal", averageH);

    //MecanumSubsystem.setSpeeds(0, adjustX, adjustRotation, .1);
  }


  public static boolean ifTarget(){
    return ifTarget;
  }
  public static double getTX(){
    return table.getEntry("tx").getDouble(0);
  }
  public void limelightCenter() {
    enableLimelight();
    if (Math.abs(xOffset) > threshold) {
      // https://www.desmos.com/calculator/jmnqcraj3g
      adjustRotation = xOffset*0.005;
      if (adjustRotation > 1) {
        adjustRotation = 1;
      }
      if (adjustRotation < -1) {
        adjustRotation = -1;
      }
      SmartDashboard.putNumber("limelight rotation", adjustRotation);
      //MecanumSubsystem.setSpeeds(0, 0, adjustRotation, .1);
      
    } else {
      adjustRotation = 0;
      MecanumSubsystem.setSpeeds(0, 0, 0, .1);
    }
  }
  public static double getTargetArea() {
    area = horizontal * vertical;
    return area;
  }
  public static double getTargetDistance() {
    return distance;
  }

}