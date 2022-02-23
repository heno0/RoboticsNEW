// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.







public class IntakeSpinAuto extends CommandBase {


  private Intake intakeC;
  private boolean isFinished;
  
  /** Creates a new Intake. */
  public IntakeCommand(Intake intake) {
    // Use addRequirements() here to declare subsystem dependencies.dddr
    intakeC = intake;
    addRequirements(intake);
    isFinished = False;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Intake.setIntakeSpeed(0.9);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    if (Index.colorSensorCheck()){
      isFinished = true;
    } 


  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Intake.setIntakeSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return isFinished;
  }
  
}
