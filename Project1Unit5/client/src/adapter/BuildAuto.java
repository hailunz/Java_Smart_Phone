package adapter;

import model.Automobile;

import java.io.Serializable;

import scale.EditThread;

/**
 * Hailun Zhu
 * ID: hailunz
 * Date: 9/19/15
 */
public class BuildAuto
        extends proxyAutomobile
        implements CreateAuto, UpdateAuto, FixAuto,
        EditThread, Serializable{
}