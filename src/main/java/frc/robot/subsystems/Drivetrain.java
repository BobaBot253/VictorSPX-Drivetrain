package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Util;

import java.util.List;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

/*
    4 Victor motors
    IDs 1, 2, 3, 5
*/

public class Drivetrain implements Subsystem{
    private static VictorSPX leftMaster = Util.createVictorSPX(1),
                        leftSlave = Util.createVictorSPX(5),
                        rightMaster = Util.createVictorSPX(2),
                        rightSlave =  Util.createVictorSPX(3);
    private static Drivetrain instance;
    public static final List<VictorSPX> motors = List.of(leftMaster, leftSlave, rightMaster, rightSlave);

    private Drivetrain() {
        leftSlave.follow(leftMaster);
        rightSlave.follow(rightMaster);
        
        // Inverting opposite sides of the drivetrain
        List.of(leftMaster, leftSlave).forEach(motor -> motor.setInverted(false));
        List.of(rightMaster, rightSlave).forEach(motor -> motor.setInverted(true));

        register();
    }

    public void setOpenLoop(double left, double right) {
        leftMaster.set(ControlMode.PercentOutput, left);
        rightMaster.set(ControlMode.PercentOutput, right);

    }

    public static Drivetrain getInstance() {
        if(instance == null) instance = new Drivetrain();
        return instance;
    }
}
