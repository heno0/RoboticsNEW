// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.



// DO NOT USE THIS COMMAND! TAKEN OUT OF USE IN v2.3 AND ONWARDS


package frc.robot.commands;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;

public class Shooter extends CommandBase {

  private static CANSparkMax motor1;
  private static CANSparkMax motor2;
  private static Spark logMotor;

  private static Joystick joystick;


  private double rStick;
  private double pow3;

  private boolean activate;

  /** Creates a new Shooter. */
  public Shooter(boolean active) {
    // Use addRequirements() here to declare subsystem dependencies.
    active = activate;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // top 2 motors
    motor1 = new CANSparkMax(5, MotorType.kBrushless);
    motor2 = new CANSparkMax(6, MotorType.kBrushless);

    // bottom motor and rollercoaster
    logMotor = new Spark(1);

    joystick = new Joystick(0);

    rStick = joystick.getRawAxis(Constants.RIGHTSTICK);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    // math calculations
    // link: https://www.desmos.com/calculator/vd0jecbamp
    rStick = (Math.pow(rStick, 2)) + .1;
    pow3 = rStick/2.5;

    // maxmin
    rStick = Constants.maxmin(rStick, 1);
    pow3 = Constants.maxmin(pow3, 1);

    // final if statements
    if (activate == true) {
      // setting motors
      motor1.set(1);
      motor2.set(-1);
      logMotor.set(.44);
    } else {
      // setting motors
      motor1.set(rStick);
      motor2.set(-rStick);
      logMotor.set(pow3);
    } 
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
