// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class Intake extends SubsystemBase {
  private static Spark motor;

  // intake is used to check if the intake motors are moving in, out, or not moving
  private String intake;

  private double speed;

  private DoubleSolenoid climberSolenoid;

  static boolean check = true;
  //double intakeSpeed = 0.0;
  /** Creates a new Intake. */
  public Intake() {
    // set motor
    motor = new Spark(0);

    // set climbing solenoid
    climberSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 0, 3);


  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run


    // setting the intake
    if (RobotContainer.joystick2.getRawButtonPressed(Constants.BBUTTON)) {
      speed = -1;
    } else if (RobotContainer.joystick2.getRawButtonPressed(Constants.XBUTTON)) {
      speed = .75;
    } else {
      intake = "";
      speed = 0;
    }

    if (speed > 0) {
      intake = "rotating out";
    } else if (speed < 0) {
      intake = "rotating in";
    }

    if (RobotContainer.joystick2.getRawButtonPressed(Constants.YBUTTON)) {
      // toggles
      // get pressed so it doesnt keep toggling
      climberSolenoid.toggle();
    }


    setIntake(speed);
    // set intake variables
    SmartDashboard.putString("Intake", intake);
  }
  public static void setIntake(double speed) {
    
    motor.set(speed);
    
  }
}