/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.catalindumitru.bee.type;

/**
 *
 * @author colin
 */

/**
 * A class that really shouldn't be used on it's own. It's more of a definition to be used with
 * typed array than an actual implementation.
 *
 * @author colin
 */
public class UShort extends Number {
    public static final Class<UShort> TYPE = UShort.class;

    protected short _val;
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------

    /**
     * Sets the value of this class. The value which is passed is wrapped to a value between 0 and
     * MAX_VALUE
     *
     * @param val
     */
    public void set(short val) {
        if (val < 0)
            val = (short) (Short.MAX_VALUE + val);

        this._val = val;
    }
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------

    /**
     * Returns the value stored inside of this class.
     *
     * @return
     */
    public short get() {
        return this._val;
    }

    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    @Override
    public int intValue() {
        return (int) this._val;
    }

    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    @Override
    public long longValue() {
        return (long) this._val;
    }

    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    @Override
    public float floatValue() {
        return (float) this._val;
    }

    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    @Override
    public double doubleValue() {
        return (double) this._val;
    }

}
