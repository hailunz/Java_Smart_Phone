package exception;

/**
 * Hailun Zhu
 * Id: hailunz
 * Date: 09/14/2015
 */
public class NoOptionException extends Exception{
    public NoOptionException(){
        super();
    }

    public NoOptionException(String message){
        super("NoOptionException: "+ message);
    }

}
