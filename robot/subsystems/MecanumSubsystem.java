// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.MecanumDriveKinematics;
import edu.wpi.first.math.kinematics.MecanumDriveOdometry;
import edu.wpi.first.math.kinematics.MecanumDriveWheelSpeeds;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.commands.LimelightRotate;
import frc.robot.commands.MecanumCommand;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
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
  private static RelativeEncoder RFE;
  private static RelativeEncoder RBE;
  private static RelativeEncoder LFE;
  private static RelativeEncoder LBE;

  private static Rotation2d currentAngle;


  private static MecanumDriveOdometry currentPos;

  private static AHRS navx;

  private MecanumDriveKinematics kinematics;

  // wheel locations
  private Translation2d RFL;
  private Translation2d RBL;
  private Translation2d LFL;
  private Translation2d LBL;

  public static MecanumDriveOdometry initialPos;
  private Pose2d initPos2d;
  private Rotation2d initHeading;



  /** Creates a new MecanumSubsystem. */
  public MecanumSubsystem() {

    // declaring motors
    // right
    frontRight = new CANSparkMax(Constants.FRONTRIGHTMOTOR, MotorType.kBrushless);
    backRight = new CANSparkMax(Constants.BACKRIGHTMOTOR, MotorType.kBrushless);

    // left
    frontLeft = new CANSparkMax(Constants.FRONTLEFTMOTOR, MotorType.kBrushless);
    backLeft = new CANSparkMax(Constants.BACKLEFTMOTOR, MotorType.kBrushless);

    frontRight.setInverted(true);
    backRight.setInverted(true);

    // wheel positions in relation to the center of the robot
    RFL = new Translation2d(0.3, -0.25);
    RBL = new Translation2d(-0.3, -0.25);
    LFL = new Translation2d(0.3, 0.25);
    LBL = new Translation2d(-0.3, 0.25);

    // encoders
    RFE = frontRight.getEncoder();
    RBE = backRight.getEncoder();
    LFE = frontLeft.getEncoder();
    LBE = backLeft.getEncoder();


    // creating navx object
    navx = new AHRS(SPI.Port.kMXP, (byte) 50);

    // getting initial direction
    initHeading = new Rotation2d((double) navx.getCompassHeading());

    // creating kinematics object for odometry object
    kinematics = new MecanumDriveKinematics(LFL, RFL, LBL, RBL);

    // start X and Y need to be updated to starting positions (m)
    initPos2d = new Pose2d(Constants.STARTX, Constants.STARTY, initHeading);

    initialPos = new MecanumDriveOdometry(kinematics, initHeading, initPos2d);
    currentPos = new MecanumDriveOdometry(kinematics, initHeading, initPos2d);

    // setting default command
    //setDefaultCommand(new MecanumCommand(this));
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if (RobotContainer.joystick.getRawButton(Constants.ABUTTON)) {
      Limelight.enableLimelight(); 
      new LimelightRotate(this).schedule();
    }
    else if (RobotContainer.joystick.getRawButton(Constants.ABUTTON) == false) {
      Limelight.disableLimelight();
      new MecanumCommand(this).schedule();
    }
  }

  public static void updateOdometry() {
    // get current wheel speeds
    wheelSpeeds = new MecanumDriveWheelSpeeds(RFE.getVelocity(), RBE.getVelocity(), LFE.getVelocity(),
        LBE.getVelocity());

    // get current angle
    currentAngle = new Rotation2d((double) navx.getYaw());

    // update pose
    currentPos.update(currentAngle, wheelSpeeds);
  }

  public static Pose2d getOdometry() {
    updateOdometry();
    Pose2d poop = currentPos.getPoseMeters();
    return new Pose2d(poop.getX(), poop.getY(), poop.getRotation());
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
    SmartDashboard.putNumber("stick y", stickY);

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