// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc100.Robot2017.subsystems;

import org.usfirst.frc100.Robot2017.Robot;
import org.usfirst.frc100.Robot2017.RobotMap;
import org.usfirst.frc100.Robot2017.commands.*;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class PeterssUnbeatableScalingMechanismWithoutpNeumatics extends Subsystem {
	
    //private final SpeedController winchMotor = RobotMap.peterssUnbeatableScalingMechanismWithoutpNeumaticswinchMotor;
    //private final Encoder winchEncoder = RobotMap.peterssUnbeatableScalingMechanismWithoutpNeumaticswinchEncoder;

	public final Encoder climberEncoder = RobotMap.climberEncoder;
	public final VictorSP climberWinch = RobotMap.climberWinch;
	private static final double DEFAULT_PETERSS_UNBEATABLE_SCALING_MECHANISM_WITHOUT_PNEUMATICS_RAMP = 0.1;
	public double peterssUnbeatableScalingMechanismWithoutpNeumatics_ramp;
	
	public void updateDashboard() {
		SmartDashboard.putNumber("PeterssUnbeatableScalingMechanismWithoutpNeumatics/Climber Encoder Raw", climberEncoder.getRaw());
		SmartDashboard.putNumber("PeterssUnbeatableScalingMechanismWithoutpNeumatics/Climber Encoder Count", climberEncoder.get());
		SmartDashboard.putNumber("PeterssUnbeatableScalingMechanismWithoutpNeumatics/Climber Encoder Distance", climberEncoder.getDistance());
		SmartDashboard.putNumber("PeterssUnbeatableScalingMechanismWithoutpNeumatics/Climber Encoder Rate", climberEncoder.getRate());
		
		SmartDashboard.putNumber("PeterssUnbeatableScalingMechanismWithoutpNeumatics/Climber Winch Volt", climberWinch.get());
	}

    public void initDefaultCommand() {
    	setDefaultCommand(new ClimbJoysticks()); 
    }
    
    public PeterssUnbeatableScalingMechanismWithoutpNeumatics(){
		if (!Robot.prefs.containsKey("ballHandling_ramp")) {
			Robot.prefs.putDouble("ballHandling_ramp", DEFAULT_PETERSS_UNBEATABLE_SCALING_MECHANISM_WITHOUT_PNEUMATICS_RAMP);
		}
		peterssUnbeatableScalingMechanismWithoutpNeumatics_ramp = Robot.prefs.getDouble("driveTrain_kP", DEFAULT_PETERSS_UNBEATABLE_SCALING_MECHANISM_WITHOUT_PNEUMATICS_RAMP);
    }
    
    public void climbJoysticks(Joystick joy){
    	RobotMap.climberWinch.set(joy.getRawAxis(3));
    }
    
    public void climbNudge(double value){
    	if(value == 0){
    		climberWinch.set(0);
		}else if(Math.abs(value*peterssUnbeatableScalingMechanismWithoutpNeumatics_ramp + climberWinch.get()) <= 1){
			climberWinch.set(value*peterssUnbeatableScalingMechanismWithoutpNeumatics_ramp + climberWinch.get());
    	}else if(value*peterssUnbeatableScalingMechanismWithoutpNeumatics_ramp + climberWinch.get() > 1){
    		climberWinch.set(1);
    	}else if(value*peterssUnbeatableScalingMechanismWithoutpNeumatics_ramp + climberWinch.get() < -1){
    		climberWinch.set(-1);
    	}else{
    		climberWinch.set(0);
    	}
    }
}

