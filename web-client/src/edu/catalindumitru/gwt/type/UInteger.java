/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.catalindumitru.gwt.type;

/**
 * A class that really shouldn't be used on it's own. It's more of a definition to be used with
 * typed array than an actual implementation.
 * @author colin
 */
public class UInteger extends Number{
    public static final Class<UInteger> TYPE = UInteger.class;
    
    protected int _val;
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    /**
     * Sets the value of this class. The value which is passed is wrapped to a value between 0 and
     * MAX_VALUE
     * @param val 
     */
    public void set(int val) {
        if(val < 0)
            val = Integer.MAX_VALUE + val;
        
        this._val = val;
    }
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    /**
     * Returns the value stored inside of this class.
     * @return 
     */
    public int get() {
        return this._val;
    }
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    @Override
    public int intValue() {
        return (int)this._val;
    }
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    @Override
    public long longValue() {
        return (long)this._val;
    }
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    @Override
    public float floatValue() {
        return (float)this._val;
    }
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    @Override
    public double doubleValue() {
        return (double)this._val;
    }
    
}
