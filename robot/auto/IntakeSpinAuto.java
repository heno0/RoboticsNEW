package frc.robot.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Sensors;




public class IntakeSpinAuto extends CommandBase{

  private Intake intakeC;
  private boolean isFinished;
  
  /** Creates a new Intake. */
  public IntakeSpinAuto(Intake intake) {
    // Use addRequirements() here to declare subsystem dependencies.dddr
    intakeC = intake;
    addRequirements(intake);
    isFinished = false;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    Intake.setIntake(-0.9);


  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Intake.setIntake(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
  
}