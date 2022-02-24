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
import frc.robot.commands.LimelightShooter;

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
    // if b button is pressed on driver controller then automatically set controller speed
    if (RobotContainer.joystick2.getRawButton(Constants.ABUTTON)) {
      Limelight.enableLimelight();
      new LimelightShooter(this);
    }
    // at any other time, then turn on intremental increase
    else {
      Limelight.disableLimelight();
      intermittentShooterIncrease();
    }

    // get rpm for shooter motor
    SmartDashboard.putNumber("rpm", encoder.getVelocity());
  

    
    // display number
    SmartDashboard.putNumber("Shooter Speed", pow3);

    
  }
  
  // sposed to be incremental shooter increase
  private void intermittentShooterIncrease() {
    pow3 = RobotContainer.joystick2.getRawAxis(Constants.RT) - RobotContainer.joystick2.getRawAxis(Constants.LT);
 
    if (pow3 > 0) {
      pow3 = (Math.pow(pow3, 2)) / 1.25 + .2;
      if (pow3 < .3) {
        pow3 = 0;
      }
    }
    else if (pow3 < 0) {
      pow3 = -((Math.pow(pow3, 2)) / 1.25) - .2;
      if (pow3 > -.3) {
        pow3 = 0;
      }
    }

    //setting motors
    setShooterSpeeds(pow3);
  }


  // set shooter speeds
  public static void setShooterSpeeds(double speed) {

    motor1.set(-speed);
    motor2.set(speed);
  }

}