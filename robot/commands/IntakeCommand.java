// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class IntakeCommand extends CommandBase {
  private boolean activate;
  
  private Compressor compressor;
  private DoubleSolenoid sol1;
  DoubleSolenoid sol2;

  private Joystick joystick;

  int pov;
  
  /** Creates a new Intake. */
  public IntakeCommand(boolean active) {
    // Use addRequirements() here to declare subsystem dependencies.
    active = activate;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    compressor = new Compressor(PneumaticsModuleType.CTREPCM);

    sol1 = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 0, 1);
    sol2 = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 2, 3); 

    joystick = new Joystick(0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (joystick.getPOV() == 0 || activate == true) {
      setSolenoid(Value.kReverse);
    } if (joystick.getPOV() == 180 || activate == false) {
      setSolenoid(Value.kForward);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
  public void setSolenoid(Value value) {
    sol1.set(value);
    sol2.set(value);
  }
}
