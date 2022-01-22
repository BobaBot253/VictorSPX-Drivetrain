package frc.robot;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.VictorSPXConfiguration;

public class Util {
    public static VictorSPX createVictorSPX(int port) {
        VictorSPXConfiguration config = new VictorSPXConfiguration();
        VictorSPX victor = new VictorSPX(port);
        victor.configAllSettings(config);
        return victor;
    }
}
