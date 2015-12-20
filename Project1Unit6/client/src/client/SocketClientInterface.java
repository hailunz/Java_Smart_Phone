package client;

/**
 * Hailun Zhu
 * ID: hailunz
 * Date: 10/4/15
 */
public interface SocketClientInterface {
    boolean openConnection();
    void handleSession();
    void closeSession();

}
