// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.kinematics.MecanumDriveWheelSpeeds;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.MecanumCommand;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
// IF  REV  IMPORTS FAIL AT ANY TIME!!!!!!
// click WPILib button, click manage vendor libraries, click install new libraries online
// USE THIS LINK!! 
// https://www.revrobotics.com/content/sw/max/sdk/REVRobotics.json
// for all cansparkmax imports
// https://software-metadata.revrobotics.com/REVLib.json

public class MecanumSubsystem extends SubsystemBase {

  // right motors
  public static CANSparkMax frontRight;
  public static CANSparkMax backRight;
  public static double frontRightPower;
  public static double backRightPower;

  // left motors
  public static CANSparkMax frontLeft;
  public static CANSparkMax backLeft;
  public static double frontLeftPower;
  public static double backLeftPower;

  public static MecanumDriveWheelSpeeds wheelSpeeds;

  // wheel encoders, e.g.(R = right, B = back, E = encoder)
  public static RelativeEncoder RFE;
  public static RelativeEncoder BLE;
  public static RelativeEncoder BRE;
  



  /** Creates a new MecanumSubsystem. */
  public MecanumSubsystem() {

    // declaring motors
    // right
    frontRight = new CANSparkMax(Constants.FRONTRIGHTMOTOR, MotorType.kBrushless);
    backRight = new CANSparkMax(Constants.BACKRIGHTMOTOR, MotorType.kBrushless);

    // left
    frontLeft = new CANSparkMax(Constants.FRONTLEFTMOTOR, MotorType.kBrushless);
    backLeft = new CANSparkMax(Constants.BACKLEFTMOTOR, MotorType.kBrushless);
    


    RFE = frontRight.getEncoder();

    frontRight.setInverted(true);
    backRight.setInverted(true);

    // encoder


    setDefaultCommand(new MecanumCommand(this));
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    

  }

  public static void setSpeeds(double stickX, double stickY, double rotation, double deadzone) {
    // deadzone
    if (Math.abs(stickX) < deadzone) {
      stickX = (double) 0;
    } if (Math.abs(stickY) < deadzone) {
      stickY = (double) 0;
    } if (Math.abs(rotation) < deadzone) {
      rotation = (double) 0;
    }

    


    SmartDashboard.putNumber("rotation", rotation);
    SmartDashboard.putNumber("stick X", stickX);
    SmartDashboard.putNumber("stick yy", stickY);

    // math (thanks damian)
    // right
    frontRightPower = stickY - stickX - rotation;
    backRightPower = stickY + stickX - rotation;

    // left
    frontLeftPower = stickY + stickX + rotation;
    backLeftPower = stickY - stickX + rotation;


    // setting motor speeds
    // right
    frontRight.set(frontRightPower);
    backRight.set(backRightPower);

    // left
    frontLeft.set(frontLeftPower);
    backLeft.set(backLeftPower);
  }

  
}