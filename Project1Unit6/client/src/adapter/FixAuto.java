package adapter;

import java.io.IOException;

/**
 * Hailun Zhu
 * ID: hailunz
 * Date: 9/19/15
 */
public interface FixAuto {
    public void fix(int errno);
    public String fixString(int errno) throws IOException;
    public float fixFloat(int errno) throws IOException;
}
