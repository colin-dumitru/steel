/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.catalindumitru.bee.type;

/**
 *
 * @author colin
 */
public class UByte extends Number {
    public static final Class<UByte> TYPE = UByte.class;
    
    protected byte _val;
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    /**
     * Sets the value of this class. The value which is passed is wrapped to a value between 0 and
     * MAX_VALUE
     * @param val 
     */
    public void set(byte val) {
        if(val < 0)
            val = (byte) (Byte.MAX_VALUE + val);
        
        this._val = val;
    }
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    /**
     * Returns the value stored inside of this class.
     * @return 
     */
    public byte get() {
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