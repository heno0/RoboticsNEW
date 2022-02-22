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

public class Indexer extends SubsystemBase {
  private double indexSpeed = 0;
  private double increment = 0.1;
  private Spark logMotor;

  private boolean isBallS;
  private boolean check;

  private String color;
  private String wantedColor = Constants.WANTEDCOLOR;
  private String opps = Constants.OPPS;

  private static int indexState = 0;

  private boolean colorCheck;

  private boolean flag = false;


  /** Creates a new Indexer. */
  public Indexer() {
    
    // log ride motor
    logMotor = new Spark(1);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    
    color = Sensors.determineColour();
    
    intermittentIndexerIncrease();
    /*
    if (indexState == 0) {
      if (colorSensorCheck()) {
        enableIndexer(0.7);

      }
      if (Sensors.getDistanceNear() == false || Sensors.getDistanceLong() ==  false) {
        enableIndexer(0);
        indexState = 1;
      }
    } 
    if (indexState == 1) {
      if (colorSensorCheck()) {
        enableIndexer(0.7);
      }
      if (Sensors.getDistanceLong() == false) {
        enableIndexer(0);
        //indexState = 2;
      }
    } 
    SmartDashboard.putNumber("INDEX state", indexState);
    SmartDashboard.putNumber("Index Speed", indexSpeed); **/
  }
  
  private void intermittentIndexerIncrease() {
    indexSpeed = RobotContainer.joystick2.getRawAxis(Constants.LT) - RobotContainer.joystick2.getRawAxis(Constants.RT);
   
    if (Math.abs(indexSpeed) < 0.25){
      indexSpeed = 0;
    }
    /*
    if ( || Constants.WANTEDCOLOR == Sensors.determineColour()) {  
      enableIndexer(0.7);
    } else if (RobotContainer.joystick2.getRawButtonPressed(9)) {
      enableIndexer(0);
    } else if (Constants.OPPS == Sensors.determineColour()) {
      enableIndexer(-0.7);
    }**/
    enableIndexer(-indexSpeed);
  }



  public boolean colorSensorCheck() {
    // color sensors conditions
    if (color == wantedColor) {
        colorCheck = true;
    } else if (color == opps) {
        colorCheck = false;
    }
    // if color is the wanted color, return true, but if it isnt, return false
    return colorCheck;
  }

  private void enableIndexer(double speed) {
    SmartDashboard.putNumber("INDEX enable Speed", speed);
    logMotor.set(-speed);
  }
  public static void resetState() {
    indexState = 0;
  }

}
