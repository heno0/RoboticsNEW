// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
       
    // RIGHT MOTORS
    public static final int FRONTRIGHTMOTOR = 2;
    public static final int BACKRIGHTMOTOR = 1;

    // LEFT MOTORS
    public static final int FRONTLEFTMOTOR = 3;
    public static final int BACKLEFTMOTOR = 4;

    // JOYSTICK
    public static final int JOYSTICKID = 1;
    public static final int SECONDARYJOYSTICK = 4;
    public static final int RIGHTSTICKY = 5;
    public static final int RIGHTSTICKX = 4;
    public static final int LEFTSTICKY = 1;
    public static final int LEFTSTICKX = 0;
    public static final int RT = 3;
    public static final int LT = 2;
    public static final int RIGHTSTICK = 5;
    public static final int ABUTTON = 1;
    public static final int BBUTTON = 2;
    public static final int XBUTTON = 3;
    public static final int YBUTTON = 4;

    public static final double DEADZONES = 0.15;
    public static final double DEADZONER = 0.2;

    public static final int RBUMPER = 6;
    public static final int LBUMPER = 5;

    // PNUEMATICS + SOLENOIDS
    public static final int COMPRESSORID = 0;
    public static final int RIGHTSWITCH = 0;
    public static final int LEFTSWITCH = 1;

    // STARTING POS
    public static final float STARTX = 0;
    public static final float STARTY = 0;

    public static final float WHEELCIRCUMFRENCE = 18.84956f;
    public static final float ROTATIONSRATIO = 13.09f;



    public static double maxmin(double value, double maxmin) {
        if (value > maxmin) {
            value = maxmin;
        }
    
        if (value < -maxmin) {
            value = -maxmin;
        }
        

        return value;
    }

}