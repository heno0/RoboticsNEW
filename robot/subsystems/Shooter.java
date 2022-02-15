// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class Shooter extends SubsystemBase {
  private static CANSparkMax motor1;
  private static CANSparkMax motor2;
  private static Spark logMotor;
  //double speed = 0.5;
  double increment = 0.05;
  double indexSpeed = 0;

  private String color;
  private String wantedColor = Constants.WANTEDCOLOR;
  private String opps = Constants.OPPS;


  private RelativeEncoder encoder;
  private RelativeEncoder encoder2;


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

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // math calculations
    // link:https://www.desmos.com/calculator/wm5vyubbee
  
    // get rpm for shooter motor
    SmartDashboard.putNumber("rpm", encoder.getVelocity());
    
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
      if (indexSpeed <= 0) {
        indexSpeed = 0;
      } 
    } else if (RobotContainer.joystick2.getRawButtonPressed(Constants.YBUTTON)) {
      // if the y button is pressed then increase the indexer
      indexSpeed = indexSpeed + increment;
      if (indexSpeed >= 1.0) {
        indexSpeed = 1.0;
      }
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