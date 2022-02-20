// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.beans.IndexedPropertyChangeEvent;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

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
  double indexSpeed = 0.;


  private RelativeEncoder encoder;
  private RelativeEncoder encoder2;


  //private double rStick;
  private static double pow3;
  /** Creates a new Shooter. */
  public Shooter() {
    // top 2 motors
    motor1 = new CANSparkMax(5, MotorType.kBrushless);
    motor2 = new CANSparkMax(6, MotorType.kBrushless);


    // encoder for seeing speeds
    encoder = motor1.getEncoder();

  } 

  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if (RobotContainer.joystick2.getRawButtonPressed(7)) {
      shootShootShootShoot();
    }
    else if (RobotContainer.joystick2.getRawButtonPressed(8)) {
      resetShooter();
    }

    // get rpm for shooter motor
    SmartDashboard.putNumber("rpm", encoder.getVelocity());
  


    intermittentShooterIncrease();
    
    // display number
    SmartDashboard.putNumber("Shooter Speed", pow3);

    //setting motors
    motor1.set(-pow3); 
    motor2.set(pow3);
  }
  
  private void intermittentShooterIncrease() {

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
    }
  }

  private void shootShootShootShoot() {
    pow3 = .9;
    Indexer.resetState();
  }
  public static void resetShooter() {
    pow3 = 0;
  }

}