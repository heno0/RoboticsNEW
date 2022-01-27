// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class IntakeCommand extends CommandBase {
  private boolean activate;
  
  private Compressor compressor;
  private DoubleSolenoid sol1;
  private DoubleSolenoid sol2;
  
  /** Creates a new Intake. */
  public IntakeCommand(boolean active) {
    // Use addRequirements() here to declare subsystem dependencies.
    active = activate;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    compressor = new Compressor();
    compressor.setClosedLoopControl(true);

    sol1 = new DoubleSolenoid(0, 1);
    sol2 = new DoubleSolenoid(2, 3);

    if (activate) {
      sol1.set(Value.kForward);
      sol2.set(Value.kForward);
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
