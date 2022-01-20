package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
//import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class CameraSubsystem extends SubsystemBase{
 
    NetworkTable limelightTableEntry; 
    
    public CameraSubsystem(){

        limelightTableEntry = NetworkTableInstance.getDefault().getTable("limelight");
        IntializeLimelight();

    }

    public void IntializeLimelight()
    {
        SetLedMode(0);
        SetCamMode(0);
    }

    public boolean HasValidTarget()
    {
        double tv = limelightTableEntry.getEntry("tv").getDouble(0);

        if(tv == 1)
        {
            return true;
        }

        return false;
    }

    public double GetTargetHorizontalOffset()
    {
        if(HasValidTarget())
        {
            return limelightTableEntry.getEntry("tx").getDouble(0);
        }

        return Double.NaN;
    }

    public double GetTargetVerticalOffset()
    {
        if(HasValidTarget())
        {
            return limelightTableEntry.getEntry("ty").getDouble(0);
        }

        return Double.NaN;
    }

    public double GetTargetArea()
    {
        if(HasValidTarget())
        {
            return limelightTableEntry.getEntry("ta").getDouble(0);
        }

        return Double.NaN;
    }

    public void GetCamMode(double defaultValue)
    {
        limelightTableEntry.getEntry("camMode").getDouble(defaultValue);
    }

    public void GetLedMode(double defaultValue)
    {
        limelightTableEntry.getEntry("ledMode").getDouble(defaultValue);
    }

    public void SetCamMode(double camMode)
    {
        limelightTableEntry.getEntry("camMode").setNumber(camMode);
    }

    public void SetLedMode(double ledMode)
    {
        limelightTableEntry.getEntry("ledMode").setNumber(ledMode);
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("tv", limelightTableEntry.getEntry("tv").getDouble(0));
        SmartDashboard.putNumber("tx", limelightTableEntry.getEntry("tx").getDouble(0));
        SmartDashboard.putNumber("ty", limelightTableEntry.getEntry("ty").getDouble(0));
        SmartDashboard.putNumber("ta", limelightTableEntry.getEntry("ta").getDouble(0));
        SmartDashboard.putNumber("camMode", limelightTableEntry.getEntry("camMode").getDouble(0));
        SmartDashboard.putNumber("ledmode", limelightTableEntry.getEntry("ledMode").getDouble(0));
    }
}
