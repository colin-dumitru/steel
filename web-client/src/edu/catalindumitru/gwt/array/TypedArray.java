/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.catalindumitru.gwt.array;

import com.google.gwt.core.client.JavaScriptObject;
import edu.catalindumitru.bee.type.UByte;
import edu.catalindumitru.bee.type.UInteger;
import edu.catalindumitru.bee.type.UShort;

/**
 * Container for typed arrays found in JavaScript.
 * @author colin
 */
public class TypedArray<T extends Number> extends JavaScriptObject{
    
    
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    protected TypedArray() {        
    }
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    public static<U extends Number> TypedArray<U> create(String type, int length) {
        if(type.equals( Integer.class.getName())) {
           return (TypedArray<U>) createIntArray(length);
        } else if(type.equals(Short.class.getName())) {
           return (TypedArray<U>) createShortArray(length);
        } else if(type.equals(Byte.class.getName())) {
           return (TypedArray<U>) createByteArray(length);
        } else if(type.equals(Float.class.getName())) {
           return (TypedArray<U>) createFloatArray(length);
        } else if(type.equals(Double.class.getName())) {
           return (TypedArray<U>) createDoubleArray(length);
        } else if(type.equals(UInteger.class.getName())) {
           return (TypedArray<U>) createUIntArray(length);
        } else if(type.equals(UByte.class.getName())) {
           return (TypedArray<U>) createUByteArray(length);
        } else if(type.equals(UShort.class.getName())) {
           return (TypedArray<U>) createUShortArray(length);
        }
        
        return null;
    }
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    public static native TypedArray<Integer> createIntArray(int length)/*-{
             return new Int32Array(length);
    }-*/ ;
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    public static native TypedArray<Integer> createIntArray(TypedArray<Integer> array)/*-{
             return new Int32Array(array);
    }-*/ ;
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    public static native TypedArray<Short> createShortArray(int length)/*-{
             return new Int16Array(length);
    }-*/ ;
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    public static native TypedArray<Short> createShortArray(TypedArray<Short> array)/*-{
             return new Int16Array(array);
    }-*/ ;
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    public static native TypedArray<Byte> createByteArray(int length)/*-{
             return new Int8Array(length);
    }-*/ ;
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    public static native TypedArray<Byte> createByteArray(TypedArray<Byte> array)/*-{
             return new Int8Array(array);
    }-*/ ;
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    public static native TypedArray<Float> createFloatArray(int length)/*-{
             return new Float32Array(length);
    }-*/ ;
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    public static native TypedArray<Float> createFloatArray(TypedArray<Integer> array)/*-{
             return new Float32Array(array);
    }-*/ ;
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    public static native TypedArray<Double> createDoubleArray(int length)/*-{
             return new Float64Array(length);
    }-*/ ;
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    public static native TypedArray<Double> createDoubleArray(TypedArray<Double> array)/*-{
             return new Float64Array(array);
    }-*/ ;
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    public static native TypedArray<UInteger> createUIntArray(int length)/*-{
             return new Uint32Array(length);
    }-*/ ;
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    public static native TypedArray<UInteger> createUIntArray(TypedArray<UInteger> array)/*-{
             return new Uint32Array(array);
    }-*/ ;
     //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    public static native TypedArray<UShort> createUShortArray(int length)/*-{
             return new Uint16Array(length);
    }-*/ ;
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    public static native TypedArray<UShort> createUShortArray(TypedArray<UShort> array)/*-{
             return new Int16Array(array);
    }-*/ ;
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    public static native TypedArray<UByte> createUByteArray(int length)/*-{
             return new Uint8Array(length);
    }-*/ ;
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    public static native TypedArray<UByte> createUByteArray(TypedArray<UByte> array)/*-{
             return new Uint8Array(array);
    }-*/ ;
}
