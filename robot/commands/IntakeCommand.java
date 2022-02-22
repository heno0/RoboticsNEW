// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.





// BAD BAD BAD BAD BAD BAD DO NOT USE BAD 






  private Compressor compressor;
  private DoubleSolenoid sol2;
  private Intake intakeC;
  int pov;
  
  /** Creates a new Intake. */
  public IntakeCommand(Intake intake) {
    // Use addRequirements() here to declare subsystem dependencies.dddr
    intakeC = intake;
    addRequirements(intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    compressor = new Compressor(0, PneumaticsModuleType.CTREPCM);
    compressor.enableDigital();

    sol2 = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 0, 3); 
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (RobotContainer.joystick2.getPOV() == 0) {
      setSolenoid(Value.kReverse);
    } if (RobotContainer.joystick2.getPOV() == 180) {
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
    sol2.set(value);
  }
}
