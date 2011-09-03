package edu.catalindumitru.gwt.core;

import edu.catalindumitru.bee.core.NativeArray;
import edu.catalindumitru.bee.core.NativeArrayProvider;
import edu.catalindumitru.bee.type.UByte;
import edu.catalindumitru.bee.type.UInteger;
import edu.catalindumitru.bee.type.UShort;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 8/30/11
 * Time: 11:13 AM
 */
public class GwtNativeArrayProvider implements NativeArrayProvider {

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public static NativeArray<?> wrap(GwtNativeArray<?> array) {
        return array;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public final native NativeArray<Integer> createIntArray(int length) /*-{
        return @edu.catalindumitru.gwt.core.GwtNativeArrayProvider::wrap(Ledu/catalindumitru/gwt/core/GwtNativeArray;)(
                new Int32Array(length)
        );
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    @Override
    public final native NativeArray<Short> createShortArray(int length) /*-{
        return @edu.catalindumitru.gwt.core.GwtNativeArrayProvider::wrap(Ledu/catalindumitru/gwt/core/GwtNativeArray;)(
                new Int16Array(length)
        );
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    @Override
    public final native NativeArray<Byte> createByteArray(int length) /*-{
        return @edu.catalindumitru.gwt.core.GwtNativeArrayProvider::wrap(Ledu/catalindumitru/gwt/core/GwtNativeArray;)(
                new Int8Array(length)
        );
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    @Override
    public final native NativeArray<UInteger> createUIntArray(int length) /*-{
        return @edu.catalindumitru.gwt.core.GwtNativeArrayProvider::wrap(Ledu/catalindumitru/gwt/core/GwtNativeArray;)(
                new Uint32Array(length)
        );
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    @Override
    public final native NativeArray<UShort> createUShortArray(int length) /*-{
        return @edu.catalindumitru.gwt.core.GwtNativeArrayProvider::wrap(Ledu/catalindumitru/gwt/core/GwtNativeArray;)(
                new Uint16Array(length)
        );
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    @Override
    public final native NativeArray<UByte> createUByteArray(int length) /*-{
        return @edu.catalindumitru.gwt.core.GwtNativeArrayProvider::wrap(Ledu/catalindumitru/gwt/core/GwtNativeArray;)(
                new Uint8Array(length)
        );
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    @Override
    public final native NativeArray<Double> createDoubleArray(int length) /*-{
        return @edu.catalindumitru.gwt.core.GwtNativeArrayProvider::wrap(Ledu/catalindumitru/gwt/core/GwtNativeArray;)(
                new Float64Array(length)
        );
    }-*/;

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public final native NativeArray<Float> createFloatArray(int length) /*-{
        return @edu.catalindumitru.gwt.core.GwtNativeArrayProvider::wrap(Ledu/catalindumitru/gwt/core/GwtNativeArray;)(
                new Float32Array(length)
        );
    }-*/;

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public final native NativeArray<Integer> createIntArray(NativeArray<Integer> other) /*-{
        return @edu.catalindumitru.gwt.core.GwtNativeArrayProvider::wrap(Ledu/catalindumitru/gwt/core/GwtNativeArray;)(
                new Int32Array(other)
        );
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    @Override
    public final native NativeArray<Short> createShortArray(NativeArray<Short> other) /*-{
        return @edu.catalindumitru.gwt.core.GwtNativeArrayProvider::wrap(Ledu/catalindumitru/gwt/core/GwtNativeArray;)(
                new Int16Array(other)
        );
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    @Override
    public final native NativeArray<Byte> createByteArray(NativeArray<Byte> other) /*-{
        return @edu.catalindumitru.gwt.core.GwtNativeArrayProvider::wrap(Ledu/catalindumitru/gwt/core/GwtNativeArray;)(
                new Int8Array(other)
        );
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    @Override
    public final native NativeArray<UInteger> createUIntArray(NativeArray<UInteger> other) /*-{
        return @edu.catalindumitru.gwt.core.GwtNativeArrayProvider::wrap(Ledu/catalindumitru/gwt/core/GwtNativeArray;)(
                new Uint32Array(other)
        );
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    @Override
    public final native NativeArray<UShort> createUShortArray(NativeArray<UShort> other) /*-{
        return @edu.catalindumitru.gwt.core.GwtNativeArrayProvider::wrap(Ledu/catalindumitru/gwt/core/GwtNativeArray;)(
                new Uint16Array(other)
        );
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    @Override
    public final native NativeArray<UByte> createUByteArray(NativeArray<UByte> other) /*-{
        return @edu.catalindumitru.gwt.core.GwtNativeArrayProvider::wrap(Ledu/catalindumitru/gwt/core/GwtNativeArray;)(
                new Uint8Array(other)
        );
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    @Override
    public final native NativeArray<Double> createDoubleArray(NativeArray<Double> other) /*-{
        return @edu.catalindumitru.gwt.core.GwtNativeArrayProvider::wrap(Ledu/catalindumitru/gwt/core/GwtNativeArray;)(
                new Float64Array(other)
        );
    }-*/;

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public final native NativeArray<Float> createFloatArray(NativeArray<Float> other) /*-{
        return @edu.catalindumitru.gwt.core.GwtNativeArrayProvider::wrap(Ledu/catalindumitru/gwt/core/GwtNativeArray;)(
                new Float32Array(other)
        );
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
}
