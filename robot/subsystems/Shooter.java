// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.math.trajectory.TrapezoidProfile.Constraints;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.commands.IncrementalShooter;


public class Shooter extends SubsystemBase {
  private static CANSparkMax motor1;
  private static CANSparkMax motor2;
  
  
  static double increment = 0.05;
  static double factor = 1;


  private RelativeEncoder encoder;



  //private double rStick;
  private static double pow3;
  /** Creates a new Shooter. */
  public Shooter() {
    // top 2 motors
    motor1 = new CANSparkMax(5, MotorType.kBrushless);
    motor2 = new CANSparkMax(6, MotorType.kBrushless);

    // it will take 2 seconds for the motors to reach maximum speed
    // makes it so the robot does not suffer brownout issues
    motor1.setClosedLoopRampRate(2);
    motor2.setClosedLoopRampRate(2);


    // encoder for seeing speeds
    encoder = motor1.getEncoder();

    setDefaultCommand(new IncrementalShooter(this));
  } 

  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run


    // get rpm for shooter motor
    SmartDashboard.putNumber("rpm", encoder.getVelocity());
  

    

    
  
  }
  

  // set shooter speeds
  public static void setShooterSpeeds(double speed) {
    speed = Constants.maxmin(speed, 1);
    
    // display number
    SmartDashboard.putNumber("Shooter Speed", speed);

    motor1.set(-speed);
    motor2.set(speed);
  }

  public static double getFactor() {
    return factor;
  }
}