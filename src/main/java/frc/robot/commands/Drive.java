package frc.robot.commands;

import java.util.Set;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Drivetrain;

public class Drive implements Command {
    private Drivetrain drivetrain = Drivetrain.getInstance();
    private Subsystem[] requirements = {drivetrain};
    public Drive() {

    }

    public void execute() {
        double left, right;
        double throttle, turn;
        final double driveSens = 0.05;
        final double turnSens = 0.02;
        final double turnInPlaceSens = 0.03;
        throttle = RobotContainer.getThrottle();
        turn = RobotContainer.getTurn();
        if(throttle != 0) {
            left = (throttle + throttle * turn * turnSens) * driveSens;
            right = (throttle - throttle * turn * turnSens) * driveSens;
            // Normalize
            double maxMagnitude = Math.max(Math.abs(left), Math.abs(right));
                    
            if(maxMagnitude > driveSens) {
                left = left / maxMagnitude * driveSens;
                right = right / maxMagnitude * driveSens;
            } 
         } else {
            // Turns in place when there is no throttle input
            left = turn * turnInPlaceSens;
            right = -turn * turnInPlaceSens;
        }
        drivetrain.setOpenLoop(left, right);
    }

    public Set<Subsystem> getRequirements() {
        return Set.of(requirements);
    }
}
