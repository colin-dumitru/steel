package edu.catalindumitru.gwt.math;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 7/4/11
 * Time: 1:59 PM
 */
public class ArithmeticException extends Exception{
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public ArithmeticException () {
        super("An aritmetic error has occured");
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public ArithmeticException(String error) {
        super("An arithmetic exception has occured : " + error);
    }

}
