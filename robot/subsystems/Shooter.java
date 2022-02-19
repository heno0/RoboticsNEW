// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.beans.IndexedPropertyChangeEvent;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Sensors;

public class Shooter extends SubsystemBase {
  private static CANSparkMax motor1;
  private static CANSparkMax motor2;
  private static Spark logMotor;
  //double speed = 0.5;
  double increment = 0.05;
  double indexSpeed = 0.;

  private String color;
  private String wantedColor = Constants.WANTEDCOLOR;
  private String opps = Constants.OPPS;


  private RelativeEncoder encoder;
  private RelativeEncoder encoder2;

  private boolean distance;
  private String ifballonDor;

  private boolean check;

  //private double rStick;
  private double pow3;
  /** Creates a new Shooter. */
  public Shooter() {
    // top 2 motors
    motor1 = new CANSparkMax(5, MotorType.kBrushless);
    motor2 = new CANSparkMax(6, MotorType.kBrushless);

    // log ride motor
    logMotor = new Spark(1);

    // encoder for seeing speeds
    encoder = motor1.getEncoder();

  } 

  public void distancePoop() {
    // distance sensor conditions
    if (distance == false) {
      indexSpeed = 0;
      ifballonDor = "ball detected, indexer stopped";
    } else if (distance == true) {
      ifballonDor = "no ball detected";
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run


    distance = Sensors.getDistance();
    color = Sensors.determineColour();
  
    // get rpm for shooter motor
    SmartDashboard.putNumber("rpm", encoder.getVelocity());
  
    distancePoop();
    // color sensors conditions
    if (color == wantedColor) {
     indexSpeed = 0.8;
    } else if (color == opps) {
      indexSpeed = -0.5;
    }

    if (RobotContainer.joystick.getRawButtonPressed(6)) {
      // right bumper, increase log motor if right bumper pressed
      pow3 = pow3 + increment;
      if (pow3 >=1.0){
          pow3 = 1.0;
      }
    } else if (RobotContainer.joystick2.getRawButtonPressed(5)) {
      // if left bumper is pressed decrease log motor
      pow3 = pow3 - increment;
      if (pow3 <= 0){
          pow3 = 0;
      }
    } if (RobotContainer.joystick2.getRawButtonPressed(Constants.ABUTTON)) {
      // if the a button is pressed, then decrease the indexer 
      indexSpeed = indexSpeed - increment;
      if (indexSpeed <= -.4) {
        indexSpeed = -.4;
      } 
    } else if (RobotContainer.joystick2.getRawButtonPressed(Constants.YBUTTON)) {
      // if the y button is pressed then increase the indexer
      indexSpeed = indexSpeed + increment;
      if (indexSpeed >= 1.0) {
        indexSpeed = 1.0;
      } 
      // if joystick buttons are clicked
    } else if (RobotContainer.joystick2.getRawButtonPressed(10)) {  
      indexSpeed = 0.7;
    } else if (RobotContainer.joystick2.getRawButtonPressed(9)) {
      indexSpeed = 0;
    } 

  

    // display number
    SmartDashboard.putNumber("Shooter Speed", pow3);
    SmartDashboard.putNumber("Index Speed", indexSpeed);

    //setting motors
    motor1.set(-pow3); 
    motor2.set(pow3);
    logMotor.set(-indexSpeed); 
  }
}