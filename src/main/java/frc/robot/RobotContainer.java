package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.Drive;
import frc.robot.subsystems.Drivetrain;

public class RobotContainer {
    private static XboxController driver = new XboxController(0);
    private static RobotContainer instance;
    public static Drivetrain drivetrain;
    private RobotContainer() {
        drivetrain = Drivetrain.getInstance();
        drivetrain.setDefaultCommand(new Drive());
    }
    public static RobotContainer getInstance() {
        if(instance == null) instance = new RobotContainer();
        return instance;
    }
    public static double simpleDeadband(double input, double deadband) {
        if(Math.abs(input) < deadband) {
            return 0;
        } else {
            return input;
        }
    }
    public static double getThrottle() {
        return simpleDeadband(driver.getLeftY(), 0.07);
    }
    public static double getTurn() {
        return simpleDeadband(driver.getRightX(), 0.07);
    }
}
