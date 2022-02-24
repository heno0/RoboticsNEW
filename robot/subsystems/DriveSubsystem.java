// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.DriveTrain;

public class DriveSubsystem extends SubsystemBase {
  /** Creates a new DriveSubsystem. */
  // declaring motors
  // declaring right motors

  final Spark frontRight;
  final Spark backRight;

  // declaring left motors
  final Spark frontLeft;
  final Spark backLeft;

  MotorControllerGroup rightMotors;
  MotorControllerGroup leftMotors;

  public DriveSubsystem() {
    // assigning variables
    // assigning right
    frontRight = new Spark(Constants.FRONTRIGHTMOTOR);
    backRight  = new Spark(Constants.BACKRIGHTMOTOR);

    // assigning left
    frontLeft = new Spark(Constants.FRONTLEFTMOTOR);
    backLeft = new Spark(Constants.BACKLEFTMOTOR);

    // creating motor groups
    leftMotors = new MotorControllerGroup(frontLeft, backLeft);
    rightMotors = new MotorControllerGroup(frontRight, backRight);

    rightMotors.setInverted(true);

    setDefaultCommand(new DriveTrain(this));
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setMotorSpeeds(double rightSpeed, double leftSpeed) {
    rightMotors.set(rightSpeed);
    leftMotors.set(leftSpeed);
  }
}