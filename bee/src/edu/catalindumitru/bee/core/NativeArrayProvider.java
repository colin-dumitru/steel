package edu.catalindumitru.bee.core;

import edu.catalindumitru.bee.type.UByte;
import edu.catalindumitru.bee.type.UInteger;
import edu.catalindumitru.bee.type.UShort;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 8/30/11
 * Time: 11:10 AM
 */
public interface NativeArrayProvider {
    public NativeArray<Integer> createIntArray(int length);

    public NativeArray<Short> createShortArray(int length);

    public NativeArray<Byte> createByteArray(int length);

    public NativeArray<UInteger> createUIntArray(int length);

    public NativeArray<UShort> createUShortArray(int length);

    public NativeArray<UByte> createUByteArray(int length);

    public NativeArray<Double> createDoubleArray(int length);

    public NativeArray<Float> createFloatArray(int length);

    public NativeArray<Integer> createIntArray(NativeArray<Integer> other);

    public NativeArray<Short> createShortArray(NativeArray<Short> other);

    public NativeArray<Byte> createByteArray(NativeArray<Byte> other);

    public NativeArray<UInteger> createUIntArray(NativeArray<UInteger> other);

    public NativeArray<UShort> createUShortArray(NativeArray<UShort> other);

    public NativeArray<UByte> createUByteArray(NativeArray<UByte> other);

    public NativeArray<Double> createDoubleArray(NativeArray<Double> other);

    public NativeArray<Float> createFloatArray(NativeArray<Float> other);
}
