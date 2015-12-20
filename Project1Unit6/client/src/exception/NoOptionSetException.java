package exception;

/**
 * Hailun Zhu
 * Id: hailunz
 * Date: 09/14/2015
 */
public class NoOptionSetException extends Exception{
    public NoOptionSetException(){
        super();
    }

    public NoOptionSetException(String message){
        super("NoOptionSetException: "+ message);
    }

}
