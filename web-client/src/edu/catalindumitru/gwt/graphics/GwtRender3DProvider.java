package edu.catalindumitru.gwt.graphics;

import com.google.gwt.canvas.dom.client.Context;
import edu.catalindumitru.bee.content.ImageResource;
import edu.catalindumitru.bee.core.NativeArray;
import edu.catalindumitru.bee.graphics.*;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 9/1/11
 * Time: 3:06 PM
 */
public class GwtRender3DProvider implements Render3DProvider {
    protected Context context3d;

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public GwtRender3DProvider(Context context3d) {
        this.setupContext(context3d);
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    protected void setupContext(Context context) {
        this.context3d = context;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Returns the visible screen width in pixels.
     *
     * @return the screen's width in pixels.
     */
    @Override
    public final native int getScreenWidth() /*-{
        return this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.drawingBufferWidth;
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Returns the visible screen height in pixels.
     *
     * @return the visible screen height in pixels.
     */
    @Override
    public final native int getScreenHeight() /*-{
        return this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.drawingBufferHeight;
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Sets the blending color for future drawings. The colors range from 0 to 1.
     *
     * @param color the new color to use for blending.
     */
    @Override
    public final native void blendColor(Color color) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.blendColor(
                color.@edu.catalindumitru.bee.graphics.Color::red,
                color.@edu.catalindumitru.bee.graphics.Color::green,
                color.@edu.catalindumitru.bee.graphics.Color::blue,
                color.@edu.catalindumitru.bee.graphics.Color::alpha
        );
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Sets the blend equations to use for future drawings.
     *
     * @param mode the mode of the blending. It can be one of the fallowing :
     *             {@link edu.catalindumitru.bee.graphics.Render3DProvider#FUNC_ADD},
     *             {@link edu.catalindumitru.bee.graphics.Render3DProvider#FUNC_SUBTRACT}
     *             or {@link edu.catalindumitru.bee.graphics.Render3DProvider#FUNC_REVERSE_SUBTRACT}.
     */
    @Override
    public final native void blendEquation(int mode) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.blendEquation(mode);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Sets a separate equation for blending for both color information and alpha information. As for
     * {@see Render3DProvider#blendEquation} the modes can be one of the fallowing :
     * {@link edu.catalindumitru.bee.graphics.Render3DProvider#FUNC_ADD},
     * {@link edu.catalindumitru.bee.graphics.Render3DProvider#FUNC_SUBTRACT} or
     * {@link edu.catalindumitru.bee.graphics.Render3DProvider#FUNC_REVERSE_SUBTRACT}.
     *
     * @param modeRGB   the mode for the rgb equation.
     * @param modeAlpha the mode for the alpha equation
     */
    @Override
    public final native void blendEquationSeparate(int modeRGB, int modeAlpha) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.blendEquationSeparate(modeRGB, modeAlpha);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Specify per pixel arithmetic.
     *
     * @param sourceFactor      specify how the RGBA source components are computed. Can ve one of the fallowing :
     *                          {@link edu.catalindumitru.bee.graphics.Render3DProvider#ZERO},
     *                          {@link edu.catalindumitru.bee.graphics.Render3DProvider#ONE},
     *                          {@link edu.catalindumitru.bee.graphics.Render3DProvider#SRC_COLOR},
     *                          {@link edu.catalindumitru.bee.graphics.Render3DProvider#ONE_MINUS_SRC_COLOR},
     *                          {@link edu.catalindumitru.bee.graphics.Render3DProvider#DST_COLOR},
     *                          {@link edu.catalindumitru.bee.graphics.Render3DProvider#ONE_MINUS_DST_COLOR},
     *                          {@link edu.catalindumitru.bee.graphics.Render3DProvider#SRC_ALPHA},
     *                          {@link edu.catalindumitru.bee.graphics.Render3DProvider#ONE_MINUS_SRC_ALPHA},
     *                          {@link edu.catalindumitru.bee.graphics.Render3DProvider#DST_ALPHA},
     *                          {@link edu.catalindumitru.bee.graphics.Render3DProvider#ONE_MINUS_DST_ALPHA},
     *                          {@link edu.catalindumitru.bee.graphics.Render3DProvider#CONSTANT_COLOR},
     *                          {@link edu.catalindumitru.bee.graphics.Render3DProvider#ONE_MINUS_CONSTANT_COLOR},
     *                          {@link edu.catalindumitru.bee.graphics.Render3DProvider#CONSTANT_ALPHA},
     *                          {@link edu.catalindumitru.bee.graphics.Render3DProvider#ONE_MINUS_CONSTANT_ALPHA},
     *                          {@link edu.catalindumitru.bee.graphics.Render3DProvider#CONSTANT_COLOR}
     * @param destinationFactor specify how the RGBA destination components are computed. Can ve one of the fallowing :
     *                          {@link edu.catalindumitru.bee.graphics.Render3DProvider#ZERO},
     *                          {@link edu.catalindumitru.bee.graphics.Render3DProvider#ONE},
     *                          {@link edu.catalindumitru.bee.graphics.Render3DProvider#SRC_COLOR},
     *                          {@link edu.catalindumitru.bee.graphics.Render3DProvider#ONE_MINUS_SRC_COLOR},
     *                          {@link edu.catalindumitru.bee.graphics.Render3DProvider#DST_COLOR},
     *                          {@link edu.catalindumitru.bee.graphics.Render3DProvider#ONE_MINUS_DST_COLOR},
     *                          {@link edu.catalindumitru.bee.graphics.Render3DProvider#SRC_ALPHA},
     *                          {@link edu.catalindumitru.bee.graphics.Render3DProvider#ONE_MINUS_SRC_ALPHA},
     *                          {@link edu.catalindumitru.bee.graphics.Render3DProvider#DST_ALPHA},
     *                          {@link edu.catalindumitru.bee.graphics.Render3DProvider#ONE_MINUS_DST_ALPHA},
     *                          {@link edu.catalindumitru.bee.graphics.Render3DProvider#CONSTANT_COLOR},
     *                          {@link edu.catalindumitru.bee.graphics.Render3DProvider#ONE_MINUS_CONSTANT_COLOR},
     *                          {@link edu.catalindumitru.bee.graphics.Render3DProvider#CONSTANT_ALPHA},
     *                          {@link edu.catalindumitru.bee.graphics.Render3DProvider#ONE_MINUS_CONSTANT_ALPHA},
     *                          {@link edu.catalindumitru.bee.graphics.Render3DProvider#CONSTANT_COLOR}
     */
    @Override
    public final native void blendFunction(int sourceFactor, int destinationFactor) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.blendFunc(sourceFactor, destinationFactor);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Specify per pixel arithmetic, separate for each component (RGB and alpha). See {@see Render3DProvider#blendFunc}
     *
     * @param sourceRGB        source RGB function.
     * @param destinationRGB   destination RGB function.
     * @param sourceAlpha      source alpha function.
     * @param destinationAlpha destination alpha function.
     */
    @Override
    public final native void blendFunctionSeparate(int sourceRGB, int destinationRGB, int sourceAlpha,
                                                   int destinationAlpha) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.blendFuncSeparate(sourceRGB, destinationRGB, sourceAlpha, destinationAlpha);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Clears the buffers (color, depth and / or stencil buffers) to a preset values.
     *
     * @param mask bitwise or comprised of one of the fallowing :
     *             {@link edu.catalindumitru.bee.graphics.Render3DProvider#COLOR_BUFFER_BIT},
     *             {@link edu.catalindumitru.bee.graphics.Render3DProvider#DEPTH_BUFFER_BIT} and / or
     *             {@link edu.catalindumitru.bee.graphics.Render3DProvider#STENCIL_BUFFER_BIT}
     */
    @Override
    public final native void clear(int mask) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.clear(mask);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Sets the color to be used when clearing the color buffer.
     *
     * @param color the color to be used when clearing the color buffer.
     */
    @Override
    public final native void clearColor(Color color) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.clearColor(
                color.@edu.catalindumitru.bee.graphics.Color::red,
                color.@edu.catalindumitru.bee.graphics.Color::green,
                color.@edu.catalindumitru.bee.graphics.Color::blue,
                color.@edu.catalindumitru.bee.graphics.Color::alpha
        );
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Sets the default depth value to be used when clearing the depth buffer.
     *
     * @param depth the default depth value.
     */
    @Override
    public final native void clearDepth(float depth) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.clearDepth(depth);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Sets the default value to be used when clearing the stencil buffer.
     *
     * @param s the default value of the setncil buffer.
     */
    @Override
    public final native void clearStencil(int s) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.clearStencil(s);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Sets which colors will be written to the color buffer.
     *
     * @param red   if the red component will be written to the color buffer.
     * @param green if the green component will be written to the color buffer.
     * @param blue  if the blue component will be written to the color buffer.
     * @param alpha if the alpha component will be written to the color buffer.
     */
    @Override
    public final native void colorMask(boolean red, boolean green, boolean blue, boolean alpha) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.colorMask(red, green, blue, alpha);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Specify the type of culling should be done.
     *
     * @param mode the type of culling. Must be one of the fallowing :
     *             {@link edu.catalindumitru.bee.graphics.Render3DProvider#FRONT},
     *             {@link edu.catalindumitru.bee.graphics.Render3DProvider#BACK}
     *             or {@link edu.catalindumitru.bee.graphics.Render3DProvider#FRONT_AND_BACK}.
     */
    @Override
    public final native void cullFace(int mode) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.cullFace(mode);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Specifies the function to use when comparing depth values.
     *
     * @param func the function to use when comparing depth values. Must be one of the fallowing constants :
     *             {@link edu.catalindumitru.bee.graphics.Render3DProvider#NEVER},
     *             {@link edu.catalindumitru.bee.graphics.Render3DProvider#EQUAL},
     *             {@link edu.catalindumitru.bee.graphics.Render3DProvider#LESS},
     *             {@link edu.catalindumitru.bee.graphics.Render3DProvider#LEQUAL},
     *             {@link edu.catalindumitru.bee.graphics.Render3DProvider#GREATER},
     *             {@link edu.catalindumitru.bee.graphics.Render3DProvider#GEQUAL},
     *             {@link edu.catalindumitru.bee.graphics.Render3DProvider#ALWAYS}
     *             or {@link edu.catalindumitru.bee.graphics.Render3DProvider#NOTEQUAL}
     */
    @Override
    public final native void depthFunc(int func) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.depthFunc(func);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Enables or disables writing to the depth buffer.
     *
     * @param flag true to enable writing to the depth buffer, false otherwise.
     */
    @Override
    public final native void depthMask(boolean flag) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.depthMask(flag);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Specifies the depth range in which writing to the depth buffer should occur.
     *
     * @param zNear the near value of the depth range. Anything bellow this value will not be written.
     * @param zFar  the far value of the depth range. Anything above this value will not be written.
     */
    @Override
    public final native void depthRange(float zNear, float zFar) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.depthRange(zNear, zFar);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Disables components used by the render provider. The initial value for all components except for
     * {@link edu.catalindumitru.bee.graphics.Render3DProvider#DITHER} is set to false.
     *
     * @param component the component to disable. Must be one of the fallowing :
     *                  {@link edu.catalindumitru.bee.graphics.Render3DProvider#DITHER},
     *                  {@link edu.catalindumitru.bee.graphics.Render3DProvider#BLEND},
     *                  {@link edu.catalindumitru.bee.graphics.Render3DProvider#CULL_FACE},
     *                  {@link edu.catalindumitru.bee.graphics.Render3DProvider#DEPTH_TEST},
     *                  {@link edu.catalindumitru.bee.graphics.Render3DProvider#POLYGON_OFFSET_FILL},
     *                  {@link edu.catalindumitru.bee.graphics.Render3DProvider#SAMPLE_ALPHA_TO_COVERAGE},
     *                  {@link edu.catalindumitru.bee.graphics.Render3DProvider#SAMPLE_COVERAGE},
     *                  {@link edu.catalindumitru.bee.graphics.Render3DProvider#SCISSOR_TEST} or
     *                  {@link edu.catalindumitru.bee.graphics.Render3DProvider#STENCIL_TEST}.
     */
    @Override
    public final native void disable(int component) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.disable(component);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Enables components used by the render provider. The initial value for all components except for
     * {@link edu.catalindumitru.bee.graphics.Render3DProvider#DITHER} is set to false.
     *
     * @param component the component to enable. Must be one of the fallowing :
     *                  {@link edu.catalindumitru.bee.graphics.Render3DProvider#DITHER},
     *                  {@link edu.catalindumitru.bee.graphics.Render3DProvider#BLEND},
     *                  {@link edu.catalindumitru.bee.graphics.Render3DProvider#CULL_FACE},
     *                  {@link edu.catalindumitru.bee.graphics.Render3DProvider#DEPTH_TEST},
     *                  {@link edu.catalindumitru.bee.graphics.Render3DProvider#POLYGON_OFFSET_FILL},
     *                  {@link edu.catalindumitru.bee.graphics.Render3DProvider#SAMPLE_ALPHA_TO_COVERAGE},
     *                  {@link edu.catalindumitru.bee.graphics.Render3DProvider#SAMPLE_COVERAGE},
     *                  {@link edu.catalindumitru.bee.graphics.Render3DProvider#SCISSOR_TEST} or
     *                  {@link edu.catalindumitru.bee.graphics.Render3DProvider#STENCIL_TEST}.
     */
    @Override
    public final native void enable(int component) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.enable(component);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Blocks until execution is complete.
     */
    @Override
    public final native void finish() /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.finish();
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Forces execution of all queued commands.
     */
    @Override
    public final native void flush() /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.flush();
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Retrieves the current error flag.
     *
     * @return the current error flag. Could be one of the fallowing constants :
     *         {@link edu.catalindumitru.bee.graphics.Render3DProvider#NO_ERROR},
     *         {@link edu.catalindumitru.bee.graphics.Render3DProvider#INVALID_ENUM},
     *         {@link edu.catalindumitru.bee.graphics.Render3DProvider#INVALID_FRAMEBUFFER_OPERATION},
     *         {@link edu.catalindumitru.bee.graphics.Render3DProvider#INVALID_OPERATION},
     *         {@link edu.catalindumitru.bee.graphics.Render3DProvider#INVALID_VALUE} or
     *         {@link edu.catalindumitru.bee.graphics.Render3DProvider#OUT_OF_MEMORY}.
     */
    @Override
    public final native int getError() /*-{
        return this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.getError();
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Specifies the desired implementation for the given component.
     *
     * @param target the target component. Must be one of the fallowing constants :
     *               {@link edu.catalindumitru.bee.graphics.Render3DProvider#GENERATE_MIPMAP_HINT}.
     * @param mode   the mode for the component. Must be one of the fallowing constants :
     *               {@link edu.catalindumitru.bee.graphics.Render3DProvider#FASTEST},
     *               {@link edu.catalindumitru.bee.graphics.Render3DProvider#NICEST}
     *               or {@link edu.catalindumitru.bee.graphics.Render3DProvider#DONT_CARE}.
     */
    @Override
    public final native void hint(int target, int mode) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.hint(target, mode);

    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Determines if a component is enabled or disabled (a component can have it's state switch by using the fallowing
     * two function calls : {@link edu.catalindumitru.bee.graphics.Render3DProvider#enable(int)} or
     * {@link edu.catalindumitru.bee.graphics.Render3DProvider#disable(int)}).
     *
     * @param cap the component to test it's state. Must be one of the fallowing constants :
     *            {@link edu.catalindumitru.bee.graphics.Render3DProvider#DITHER},
     *            {@link edu.catalindumitru.bee.graphics.Render3DProvider#BLEND},
     *            {@link edu.catalindumitru.bee.graphics.Render3DProvider#CULL_FACE},
     *            {@link edu.catalindumitru.bee.graphics.Render3DProvider#DEPTH_TEST},
     *            {@link edu.catalindumitru.bee.graphics.Render3DProvider#POLYGON_OFFSET_FILL},
     *            {@link edu.catalindumitru.bee.graphics.Render3DProvider#SAMPLE_ALPHA_TO_COVERAGE},
     *            {@link edu.catalindumitru.bee.graphics.Render3DProvider#SAMPLE_COVERAGE},
     *            {@link edu.catalindumitru.bee.graphics.Render3DProvider#SCISSOR_TEST}
     *            or {@link edu.catalindumitru.bee.graphics.Render3DProvider#STENCIL_TEST}.
     * @return if the given component is enabled.
     */
    @Override
    public final native boolean isEnabled(int cap) /*-{
        return this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.isEnabled(cap);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Specifies the pixel storage mode.
     *
     * @param pName the parameter to be set. Must be one of the fallowing constants :
     *              {@link edu.catalindumitru.bee.graphics.Render3DProvider#PACK_ALIGNMENT} or
     *              {@link edu.catalindumitru.bee.graphics.Render3DProvider#UNPACK_ALIGNMENT}.
     * @param param the value of the parameter to set to.
     */
    @Override
    public final native void pixelStore(int pName, int param) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.pixelStorei(pName, param);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Specifies the scale factor and units when used to calculate depth values.
     *
     * @param factor the scale factor used when calculating depth values. Initial value is 0;
     * @param offset the offset value used when calculating depth values. Initial value is 0.
     */
    @Override
    public final native void polygonOffset(float factor, float offset) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.polygonOffset(factor, offset);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Specifies the multisample coverage.
     *
     * @param value  the sample coverage value. The value is clamped between 0 and 1, where the initial value is 1.
     * @param invert if the mask should be inverted..
     */
    @Override
    public final native void sampleCoverage(float value, boolean invert) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.sampleCoverage(value, invert);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Defines a scissor box.
     *
     * @param x      the x coordinate for the lower left corner of the rectangle defining the scissor box.
     * @param y      the y coordinate for the lower left corner of the rectangle defining the scissor box.
     * @param width  the width of the rectangle defining the scissor box.
     * @param height the height of the rectangle defining the scissor box.
     */
    @Override
    public final native void scissor(int x, int y, int width, int height) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.scissor(x, y, width, height);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Specifies the stencil function.
     *
     * @param func the stencil test function. Must be one of the fallowing constants :
     *             {@link edu.catalindumitru.bee.graphics.Render3DProvider#NEVER},
     *             {@link edu.catalindumitru.bee.graphics.Render3DProvider#LESS},
     *             {@link edu.catalindumitru.bee.graphics.Render3DProvider#LEQUAL},
     *             {@link edu.catalindumitru.bee.graphics.Render3DProvider#GREATER},
     *             {@link edu.catalindumitru.bee.graphics.Render3DProvider#GEQUAL},
     *             {@link edu.catalindumitru.bee.graphics.Render3DProvider#EQUAL},
     *             {@link edu.catalindumitru.bee.graphics.Render3DProvider#NOTEQUAL} or
     *             {@link edu.catalindumitru.bee.graphics.Render3DProvider#ALWAYS}.
     * @param ref  the reference value for the stencil test function. The values are clamped to values :
     *             0 2 ... n -1 , where n is the number of bitplanes in the stencil buffer.
     * @param mask specify the mask value. The final value will be a result of AND operations between the mask,
     *             reference value and stored stencil value.
     */
    @Override
    public final native void stencilFunction(int func, int ref, int mask) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.stencilFunc(func, ref, mask);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Sets front and / or back function for stencil test function.
     *
     * @param face which side to use (front and / or back). Must be one of the fallowing constats :
     *             {@link edu.catalindumitru.bee.graphics.Render3DProvider#FRONT},
     *             {@link edu.catalindumitru.bee.graphics.Render3DProvider#BACK}
     *             or  {@link edu.catalindumitru.bee.graphics.Render3DProvider#FRONT_AND_BACK}.
     * @param func specifies the stencil test function.  Must be one of the fallowing constants :
     *             {@link edu.catalindumitru.bee.graphics.Render3DProvider#NEVER},
     *             {@link edu.catalindumitru.bee.graphics.Render3DProvider#LESS},
     *             {@link edu.catalindumitru.bee.graphics.Render3DProvider#LEQUAL},
     *             {@link edu.catalindumitru.bee.graphics.Render3DProvider#GREATER},
     *             {@link edu.catalindumitru.bee.graphics.Render3DProvider#GEQUAL},
     *             {@link edu.catalindumitru.bee.graphics.Render3DProvider#EQUAL},
     *             {@link edu.catalindumitru.bee.graphics.Render3DProvider#NOTEQUAL} or
     *             {@link edu.catalindumitru.bee.graphics.Render3DProvider#ALWAYS}.
     * @param ref  the reference value for the stencil test function. The values are clamped to values :
     *             0 2 ... n -1 , where n is the number of bitplanes in the stencil buffer.
     * @param mask specify the mask value. The final value will be a result of AND operations between the mask,
     *             reference value and stored stencil value.
     */
    @Override
    public final native void stencilFuncSeparate(int face, int func, int ref, int mask) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.stencilFuncSeparate(face, func, ref, mask);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Specifies the mask value used for the stencil test function.
     *
     * @param mask specify the mask value. The final value will be a result of AND operations between the mask,
     *             reference value and stored stencil value.
     */
    @Override
    public final native void stencilMask(int mask) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.stencilMask(mask);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Specifies a different mask to be used for font and / or back stencil test functions.
     *
     * @param face which side to specify the mask. Must be one of the fallowing constants :
     *             {link Render3DProvider#FRONT}, {@link edu.catalindumitru.bee.graphics.Render3DProvider#BACK}
     *             or {@link edu.catalindumitru.bee.graphics.Render3DProvider#FRONT_AND_BACK}.
     * @param mask specify the mask value. The final value will be a result of AND operations between the mask,
     *             reference value and stored stencil value.
     */
    @Override
    public final native void stencilMaskSeparate(int face, int mask) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.stencilMaskSeparate(face, mask);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Sets the stencil action for both the front and back function tests.
     *
     * @param fail  what action to take when the test fails. Must be one of the fallowing constants :
     *              {@link edu.catalindumitru.bee.graphics.Render3DProvider#KEEP},
     *              {@link edu.catalindumitru.bee.graphics.Render3DProvider#ZERO},
     *              {@link edu.catalindumitru.bee.graphics.Render3DProvider#REPLACE},
     *              {@link edu.catalindumitru.bee.graphics.Render3DProvider#INCR},
     *              {@link edu.catalindumitru.bee.graphics.Render3DProvider#INCR_WRAP},
     *              {@link edu.catalindumitru.bee.graphics.Render3DProvider#DECR},
     *              {@link edu.catalindumitru.bee.graphics.Render3DProvider#DECR_WRAP} or
     *              {@link edu.catalindumitru.bee.graphics.Render3DProvider#INVERT}
     * @param zFail the action to take when the stencil function succeeds, but the depth test fails. The same constants
     *              as for test fail are accepted.
     * @param zPass the action to take when both tests succeed. The same constants as for the stencil test fail
     *              are accepted.
     */
    @Override
    public final native void stencilOp(int fail, int zFail, int zPass) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.stencilOp(fail, zFail, zPass);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Specifies the action to take when the stencil function succeeds or fails, separate for each face.
     *
     * @param face  which face to set the actions. Must be one of the fallowing constants :
     *              {@link edu.catalindumitru.bee.graphics.Render3DProvider#FRONT},
     *              {@link edu.catalindumitru.bee.graphics.Render3DProvider#BACK}
     *              or {@link edu.catalindumitru.bee.graphics.Render3DProvider#FRONT_AND_BACK}.
     * @param fail  what action to take when the test fails. Must be one of the fallowing constants :
     *              {@link edu.catalindumitru.bee.graphics.Render3DProvider#KEEP},
     *              {@link edu.catalindumitru.bee.graphics.Render3DProvider#ZERO},
     *              {@link edu.catalindumitru.bee.graphics.Render3DProvider#REPLACE},
     *              {@link edu.catalindumitru.bee.graphics.Render3DProvider#INCR},
     *              {@link edu.catalindumitru.bee.graphics.Render3DProvider#INCR_WRAP},
     *              {@link edu.catalindumitru.bee.graphics.Render3DProvider#DECR},
     *              {@link edu.catalindumitru.bee.graphics.Render3DProvider#DECR_WRAP} or
     *              {@link edu.catalindumitru.bee.graphics.Render3DProvider#INVERT}
     * @param zFail the action to take when the stencil function succeeds, but the depth test fails. The same constants
     *              as for test fail are accepted.
     * @param zPass the action to take when both tests succeed. The same constants as for the stencil test fail
     *              are accepted.
     */
    @Override
    public final native void stencilOpSeparate(int face, int fail, int zFail, int zPass) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.stencilOpSeparate(face, fail, zFail, zPass);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Sets the viewport dimensions.
     *
     * @param x      the x coordinate of lower left corner of the viewport. Initial value is 0.
     * @param y      the y coordinate of lower left corner of the viewport. Initial value is 0.
     * @param width  the width of the viewport.
     * @param height the height of the viewport.
     */
    @Override
    public final native void viewport(int x, int y, int width, int height) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.viewport(x, y, width, height);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Render the current primitive from the currently defined arrays (vertex array, normal array etc.).
     *
     * @param mode  the type of drawing to be done. Must be one of the fallowing constants :
     *              {@link edu.catalindumitru.bee.graphics.Render3DProvider#POINTS},
     *              {@link edu.catalindumitru.bee.graphics.Render3DProvider#LINE_STRIP},
     *              {@link edu.catalindumitru.bee.graphics.Render3DProvider#LINE_LOOP},
     *              {@link edu.catalindumitru.bee.graphics.Render3DProvider#LINES},
     *              {@link edu.catalindumitru.bee.graphics.Render3DProvider#TRIANGLE_STRIP},
     *              {@link edu.catalindumitru.bee.graphics.Render3DProvider#TRIANGLE_FAN}
     *              or {@link edu.catalindumitru.bee.graphics.Render3DProvider#TRIANGLES}
     * @param first the first element from the arrays to begin drawing.
     * @param count the number of elements from the arrays to use.
     */
    @Override
    public final native void drawArrays(int mode, int first, int count) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.drawArrays(mode, first, count);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Render the current primitive from the currently defined arrays (vertex array, normal array etc.).
     *
     * @param mode   the type of drawing to be done. Must be one of the fallowing constants :
     *               {@link edu.catalindumitru.bee.graphics.Render3DProvider#POINTS},
     *               {@link edu.catalindumitru.bee.graphics.Render3DProvider#LINE_STRIP},
     *               {@link edu.catalindumitru.bee.graphics.Render3DProvider#LINE_LOOP},
     *               {@link edu.catalindumitru.bee.graphics.Render3DProvider#LINES},
     *               {@link edu.catalindumitru.bee.graphics.Render3DProvider#TRIANGLE_STRIP},
     *               {@link edu.catalindumitru.bee.graphics.Render3DProvider#TRIANGLE_FAN}
     *               or {@link edu.catalindumitru.bee.graphics.Render3DProvider#TRIANGLES}
     * @param count  the number of elements from the arrays to use. If count is greater than zero, than a non null
     *               {@link edu.catalindumitru.bee.graphics.Buffer} must be bound to
     *               {@link edu.catalindumitru.bee.graphics.Render3DProvider#ELEMENT_ARRAY_BUFFER}.
     * @param type   the type of values in the indices. Must be one of the fallowing :
     *               {@link edu.catalindumitru.bee.graphics.Render3DProvider#UNSIGNED_BYTE}
     *               or {@link edu.catalindumitru.bee.graphics.Render3DProvider#UNSIGNED_SHORT}
     * @param offset the offset in bytes, which must be a multiple of the current mode type.
     */
    @Override
    public final native void drawElements(int mode, int count, int type, int offset) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.drawElements(mode, count, type, offse);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Define which way (counter clockwise or clockwise) of drawing polygons define a front facing polygon.
     *
     * @param mode the most which defines front facing polygons. Must be one of the fallowing constants :
     *             {@link edu.catalindumitru.bee.graphics.Render3DProvider#CW} or
     *             {@link edu.catalindumitru.bee.graphics.Render3DProvider#CCW}
     */
    @Override
    public final native void frontFace(int mode) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.frontFace(mode);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Sets the line width for when drawing primitives consisting of only lines.
     *
     * @param width the new line width.
     */
    @Override
    public final native void lineWidth(float width) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.lineWidth(width);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Activates the given texture unit.
     *
     * @param texture the texture id which spans from {@see Render3DProvider#TEXTURE0} to
     *                {@see Render3DProvider#TEXTURE31}
     *                Activating {@see Render3DProvider#ACTIVE_TEXTURE} has no effect on the current texture.
     */
    @Override
    public final native void activateTexture(int texture) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.activeTexture(texture);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Binds the given texture to a texture buffer. The texture buffer location ranges from {@see Render3DProvider#TEXTURE0}
     * to {@see Render3DProvider#TEXTURE31}
     *
     * @param target  the location to bind the texture.
     * @param texture the texture to be bound.
     */
    @Override
    public final native void bindTexture(int target, Texture texture) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.bindTexture(target, texture);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Copies pixels into a texture.
     *
     * @param target         the target texture. Must be one of the fallowing :
     *                       {@link edu.catalindumitru.bee.graphics.Render3DProvider#TEXTURE_2D},
     *                       {@link edu.catalindumitru.bee.graphics.Render3DProvider#TEXTURE_CUBE_MAP_NEGATIVE_X},
     *                       {@link edu.catalindumitru.bee.graphics.Render3DProvider#TEXTURE_CUBE_MAP_POSITIVE_X}
     *                       {@link edu.catalindumitru.bee.graphics.Render3DProvider#TEXTURE_CUBE_MAP_NEGATIVE_Y},
     *                       {@link edu.catalindumitru.bee.graphics.Render3DProvider#TEXTURE_CUBE_MAP_POSITIVE_Y}
     *                       {@link edu.catalindumitru.bee.graphics.Render3DProvider#TEXTURE_CUBE_MAP_NEGATIVE_Z},
     *                       {@link edu.catalindumitru.bee.graphics.Render3DProvider#TEXTURE_CUBE_MAP_POSITIVE_Z}
     * @param level          the level of detail, where 0 is the normal uncompressed image and n is the nth level of mipmap
     *                       compression.
     * @param internalFormat the internal format of the texture. Must be one of the fallowing :
     *                       {@link edu.catalindumitru.bee.graphics.Render3DProvider#ALPHA},
     *                       {@link edu.catalindumitru.bee.graphics.Render3DProvider#LUMINANCE},
     *                       {@link edu.catalindumitru.bee.graphics.Render3DProvider#LUMINANCE_ALPHA},
     *                       {@link edu.catalindumitru.bee.graphics.Render3DProvider#RGB} or
     *                       {@link edu.catalindumitru.bee.graphics.Render3DProvider#RGBA}
     * @param x              the x coordinate for the lower left corner of the rectangle to be copied.
     * @param y              the y coordinate for the lower left corner of the rectangle to be copied.
     * @param width          the width of the rectangle to be copied.
     * @param height         the height of the rectangle to be copied.
     * @param border         the width of the border. Must be 0.
     */
    @Override
    public final native void copyTexImage2D(int target, int level, int internalFormat, int x, int y, int width,
                                            int height, int border) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.copyTexImage2D(target, level, internalFormat, x, y, width, height, border);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Copies pixels into a texture.
     *
     * @param target  the target texture. Must be one of the fallowing :
     *                {@link edu.catalindumitru.bee.graphics.Render3DProvider#TEXTURE_2D},
     *                {@link edu.catalindumitru.bee.graphics.Render3DProvider#TEXTURE_CUBE_MAP_NEGATIVE_X},
     *                {@link edu.catalindumitru.bee.graphics.Render3DProvider#TEXTURE_CUBE_MAP_POSITIVE_X}
     *                {@link edu.catalindumitru.bee.graphics.Render3DProvider#TEXTURE_CUBE_MAP_NEGATIVE_Y},
     *                {@link edu.catalindumitru.bee.graphics.Render3DProvider#TEXTURE_CUBE_MAP_POSITIVE_Y}
     *                {@link edu.catalindumitru.bee.graphics.Render3DProvider#TEXTURE_CUBE_MAP_NEGATIVE_Z},
     *                {@link edu.catalindumitru.bee.graphics.Render3DProvider#TEXTURE_CUBE_MAP_POSITIVE_Z}
     * @param level   the level of detail, where 0 is the normal uncompressed image and n is the nth level of mipmap
     *                compression.
     *                {@link edu.catalindumitru.bee.graphics.Render3DProvider#ALPHA},
     *                {@link edu.catalindumitru.bee.graphics.Render3DProvider#LUMINANCE},
     *                {@link edu.catalindumitru.bee.graphics.Render3DProvider#LUMINANCE_ALPHA},
     *                {@link edu.catalindumitru.bee.graphics.Render3DProvider#RGB} or
     *                {@link edu.catalindumitru.bee.graphics.Render3DProvider#RGBA}
     * @param x       the x coordinate for the lower left corner of the rectangle to be copied.
     * @param y       the y coordinate for the lower left corner of the rectangle to be copied.
     * @param width   the width of the rectangle to be copied.
     * @param height  the height of the rectangle to be copied.
     * @param xOffset the offset on the x axis of where the rectangle should begin.
     * @param yOffset the offset on the y axis of where the rectangle should begin.
     */
    @Override
    public final native void copyTexSubImage2D(int target, int level, int xOffset, int yOffset, int x, int y,
                                               int width, int height) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.copyTexSubImage2D(target, level, xOffset, yOffset, x, y, width, height);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Creates an empty texture.
     *
     * @return an empty texture.
     */
    @Override
    public final native Texture createTexture() /*-{
        return @edu.catalindumitru.gwt.graphics.GwtTexture::wrap(Ledu/catalindumitru/gwt/graphics/GwtTexture;)(
                this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.createTexture()
        );
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Deletes the texture object.
     *
     * @param texture the texture object to be deleted.
     */
    @Override
    public final native void deleteTexture(Texture texture) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.deleteTexture(texture);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Generates mipmaps for the current active texture.
     *
     * @param target the target texture. Must be one of the fallowing constants :
     *               {@link edu.catalindumitru.bee.graphics.Render3DProvider#TEXTURE_2D}
     *               or {@link edu.catalindumitru.bee.graphics.Render3DProvider#TEXTURE_CUBE_MAP}.
     */
    @Override
    public final native void generateMipmap(int target) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.generateMipmap(target);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Retrieves the active texture's parameter.
     *
     * @param target the target texture. Must be one of the fallowing constants :
     *               {@link edu.catalindumitru.bee.graphics.Render3DProvider#TEXTURE_2D} or
     *               {@link edu.catalindumitru.bee.graphics.Render3DProvider#TEXTURE_CUBE_MAP}.
     * @param pName  the name of the parameters to retrieve. Must be one of the fallowing constants :
     *               {@link edu.catalindumitru.bee.graphics.Render3DProvider#TEXTURE_MAG_FILTER},
     *               {@link edu.catalindumitru.bee.graphics.Render3DProvider#TEXTURE_WRAP_S},
     *               {@link edu.catalindumitru.bee.graphics.Render3DProvider#TEXTURE_WRAP_T} or
     *               {@link edu.catalindumitru.bee.graphics.Render3DProvider#TEXTURE_MIN_FILTER},
     * @return the requested texture parameter.
     */
    @Override
    public final native float getTextureParameter(int target, int pName) /*-{
        return this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.getTexParameter(target, pName);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Determines if the given object is a valid {@link edu.catalindumitru.bee.graphics.Texture} object.
     *
     * @param texture the object to test.
     * @return if the given object is a valid {@link edu.catalindumitru.bee.graphics.Texture} object.
     */
    @Override
    public final native boolean isTexture(Texture texture) /*-{
        return this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.isTexture(texture);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Specifies a image source for a texture.
     *
     * @param target         the target texture. Must be one of the fallowing :
     *                       {@link edu.catalindumitru.bee.graphics.Render3DProvider#TEXTURE_2D},
     *                       {@link edu.catalindumitru.bee.graphics.Render3DProvider#TEXTURE_CUBE_MAP_NEGATIVE_X},
     *                       {@link edu.catalindumitru.bee.graphics.Render3DProvider#TEXTURE_CUBE_MAP_POSITIVE_X}
     *                       {@link edu.catalindumitru.bee.graphics.Render3DProvider#TEXTURE_CUBE_MAP_NEGATIVE_Y},
     *                       {@link edu.catalindumitru.bee.graphics.Render3DProvider#TEXTURE_CUBE_MAP_POSITIVE_Y}
     *                       {@link edu.catalindumitru.bee.graphics.Render3DProvider#TEXTURE_CUBE_MAP_NEGATIVE_Z},
     *                       {@link edu.catalindumitru.bee.graphics.Render3DProvider#TEXTURE_CUBE_MAP_POSITIVE_Z}
     * @param level          the mipmap level to be used.
     * @param internalFormat the internal format to be used when storing the image. Must be one of the fallowing constants:
     *                       {@link edu.catalindumitru.bee.graphics.Render3DProvider#ALPHA},
     *                       {@link edu.catalindumitru.bee.graphics.Render3DProvider#LUMINANCE},
     *                       {@link edu.catalindumitru.bee.graphics.Render3DProvider#LUMINANCE_ALPHA},
     *                       {@link edu.catalindumitru.bee.graphics.Render3DProvider#RGB}
     *                       or {@link edu.catalindumitru.bee.graphics.Render3DProvider#RGBA}.
     * @param width          the width of the texture image.
     * @param height         the height of the texture image.
     * @param border         the width of the border. Must be 0.
     * @param format         the format of the texel data which must match the internal format. The same values are accepted.
     * @param type           the type of texel data. Must be one of the fallowing constants :
     *                       {@link edu.catalindumitru.bee.graphics.Render3DProvider#UNSIGNED_BYTE},
     *                       {@link edu.catalindumitru.bee.graphics.Render3DProvider#UNSIGNED_SHORT_5_6_5},
     *                       * {@link edu.catalindumitru.bee.graphics.Render3DProvider#UNSIGNED_SHORT_4_4_4_4} or
     *                       {@link edu.catalindumitru.bee.graphics.Render3DProvider#UNSIGNED_SHORT_5_5_5_1}.
     * @param pixels         the pixel array to be used for the active texture.
     */
    @Override
    public final native void texImage2D(int target, int level, int internalFormat, int width, int height,
                                        int border, int format, int type, NativeArray<?> pixels) /*-{

        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.texImage2D(target, level, internalFormat, width, height, border, format, type, pixels);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Specifies a image source for a texture.
     *
     * @param target         the target texture. Must be one of the fallowing :
     *                       {@link edu.catalindumitru.bee.graphics.Render3DProvider#TEXTURE_2D},
     *                       {@link edu.catalindumitru.bee.graphics.Render3DProvider#TEXTURE_CUBE_MAP_NEGATIVE_X},
     *                       {@link edu.catalindumitru.bee.graphics.Render3DProvider#TEXTURE_CUBE_MAP_POSITIVE_X}
     *                       {@link edu.catalindumitru.bee.graphics.Render3DProvider#TEXTURE_CUBE_MAP_NEGATIVE_Y},
     *                       {@link edu.catalindumitru.bee.graphics.Render3DProvider#TEXTURE_CUBE_MAP_POSITIVE_Y}
     *                       {@link edu.catalindumitru.bee.graphics.Render3DProvider#TEXTURE_CUBE_MAP_NEGATIVE_Z},
     *                       {@link edu.catalindumitru.bee.graphics.Render3DProvider#TEXTURE_CUBE_MAP_POSITIVE_Z}
     * @param level          the mipmap level to be used.
     * @param internalFormat the internal format to be used when storing the image. Must be one of the fallowing constants:
     *                       {@link edu.catalindumitru.bee.graphics.Render3DProvider#ALPHA},
     *                       {@link edu.catalindumitru.bee.graphics.Render3DProvider#LUMINANCE},
     *                       {@link edu.catalindumitru.bee.graphics.Render3DProvider#LUMINANCE_ALPHA},
     *                       {@link edu.catalindumitru.bee.graphics.Render3DProvider#RGB}
     *                       or {@link edu.catalindumitru.bee.graphics.Render3DProvider#RGBA}.
     * @param format         the format of the texel data which must match the internal format. The same values are accepted.
     * @param type           the type of texel data. Must be one of the fallowing constants :
     *                       {@link edu.catalindumitru.bee.graphics.Render3DProvider#UNSIGNED_BYTE},
     *                       {@link edu.catalindumitru.bee.graphics.Render3DProvider#UNSIGNED_SHORT_5_6_5},
     *                       * {@link edu.catalindumitru.bee.graphics.Render3DProvider#UNSIGNED_SHORT_4_4_4_4} or
     *                       {@link edu.catalindumitru.bee.graphics.Render3DProvider#UNSIGNED_SHORT_5_5_5_1}.
     * @param image          the image from which to copy the pixels.
     */
    @Override
    public final native void texImage2D(int target, int level, int internalFormat, int format, int type,
                                        ImageResource image) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.texImage2D(target, level, internalFormat, format, type, image);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Sets the active texture parameter.
     *
     * @param target the target texture. Must be one of the fallowing constants :
     *               {@link edu.catalindumitru.bee.graphics.Render3DProvider#TEXTURE_2D} or
     *               {@link edu.catalindumitru.bee.graphics.Render3DProvider#TEXTURE_CUBE_MAP}.
     * @param pName  the name of the parameter to change. Must be one of the fallowing constants :
     *               {@link edu.catalindumitru.bee.graphics.Render3DProvider#TEXTURE_MIN_FILTER},
     *               {@link edu.catalindumitru.bee.graphics.Render3DProvider#TEXTURE_MAG_FILTER},
     *               {@link edu.catalindumitru.bee.graphics.Render3DProvider#TEXTURE_WRAP_S} or
     *               {@link edu.catalindumitru.bee.graphics.Render3DProvider#TEXTURE_WRAP_T}.
     * @param param  the value of the parameter.
     */
    @Override
    public final native void texParameter(int target, int pName, float param) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.texParameterf(target, pName, param);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Sets the active texture parameter.
     *
     * @param target the target texture. Must be one of the fallowing constants :
     *               {@link edu.catalindumitru.bee.graphics.Render3DProvider#TEXTURE_2D} or
     *               {@link edu.catalindumitru.bee.graphics.Render3DProvider#TEXTURE_CUBE_MAP}.
     * @param pName  the name of the parameter to change. Must be one of the fallowing constants :
     *               {@link edu.catalindumitru.bee.graphics.Render3DProvider#TEXTURE_MIN_FILTER},
     *               {@link edu.catalindumitru.bee.graphics.Render3DProvider#TEXTURE_MAG_FILTER},
     *               {@link edu.catalindumitru.bee.graphics.Render3DProvider#TEXTURE_WRAP_S} or
     *               {@link edu.catalindumitru.bee.graphics.Render3DProvider#TEXTURE_WRAP_T}.
     * @param param  the value of the parameter.
     */
    @Override
    public final native void texParameter(int target, int pName, int param) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.texParameteri(target, pName, param);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Specifies a sub-portion of an image for a texture.
     *
     * @param target  the target texture. Must be one of the fallowing :
     *                {@link edu.catalindumitru.bee.graphics.Render3DProvider#TEXTURE_2D},
     *                {@link edu.catalindumitru.bee.graphics.Render3DProvider#TEXTURE_CUBE_MAP_NEGATIVE_X},
     *                {@link edu.catalindumitru.bee.graphics.Render3DProvider#TEXTURE_CUBE_MAP_POSITIVE_X}
     *                {@link edu.catalindumitru.bee.graphics.Render3DProvider#TEXTURE_CUBE_MAP_NEGATIVE_Y},
     *                {@link edu.catalindumitru.bee.graphics.Render3DProvider#TEXTURE_CUBE_MAP_POSITIVE_Y}
     *                {@link edu.catalindumitru.bee.graphics.Render3DProvider#TEXTURE_CUBE_MAP_NEGATIVE_Z},
     *                {@link edu.catalindumitru.bee.graphics.Render3DProvider#TEXTURE_CUBE_MAP_POSITIVE_Z}
     * @param level   the mipmap level to be used.
     * @param xOffset the x offset of where the cropping of the image should begin.
     * @param yOffset the y offset of where the cropping of the image should begin.
     * @param width   the width of the texture image.
     * @param height  the height of the texture image.
     * @param border  the width of the border. Must be 0.
     * @param format  the format of the texel data which must match the internal format. The same values are accepted.
     * @param type    the type of texel data. Must be one of the fallowing constants :
     *                {@link edu.catalindumitru.bee.graphics.Render3DProvider#UNSIGNED_BYTE},
     *                {@link edu.catalindumitru.bee.graphics.Render3DProvider#UNSIGNED_SHORT_5_6_5},
     *                * {@link edu.catalindumitru.bee.graphics.Render3DProvider#UNSIGNED_SHORT_4_4_4_4} or
     *                {@link edu.catalindumitru.bee.graphics.Render3DProvider#UNSIGNED_SHORT_5_5_5_1}.
     * @param pixels  the pixel array to be used for the active texture.
     */
    @Override
    public final native void texSubImage2D(int target, int level, int xOffset, int yOffset, int width,
                                           int height, int border, int format, int type, NativeArray<?> pixels) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.texSubImage2D(target, level, xOffset, yOffset, width, height, format, type, pixels);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Specifies a sub-portion of an image for a texture.
     *
     * @param target  the target texture. Must be one of the fallowing :
     *                {@link edu.catalindumitru.bee.graphics.Render3DProvider#TEXTURE_2D},
     *                {@link edu.catalindumitru.bee.graphics.Render3DProvider#TEXTURE_CUBE_MAP_NEGATIVE_X},
     *                {@link edu.catalindumitru.bee.graphics.Render3DProvider#TEXTURE_CUBE_MAP_POSITIVE_X}
     *                {@link edu.catalindumitru.bee.graphics.Render3DProvider#TEXTURE_CUBE_MAP_NEGATIVE_Y},
     *                {@link edu.catalindumitru.bee.graphics.Render3DProvider#TEXTURE_CUBE_MAP_POSITIVE_Y}
     *                {@link edu.catalindumitru.bee.graphics.Render3DProvider#TEXTURE_CUBE_MAP_NEGATIVE_Z},
     *                {@link edu.catalindumitru.bee.graphics.Render3DProvider#TEXTURE_CUBE_MAP_POSITIVE_Z}
     * @param level   the mipmap level to be used.
     * @param xOffset the x offset of where the cropping of the image should begin.
     * @param yOffset the y offset of where the cropping of the image should begin.
     * @param format  the format of the texel data which must match the internal format. The same values are accepted.
     * @param type    the type of texel data. Must be one of the fallowing constants :
     *                {@link edu.catalindumitru.bee.graphics.Render3DProvider#UNSIGNED_BYTE},
     *                {@link edu.catalindumitru.bee.graphics.Render3DProvider#UNSIGNED_SHORT_5_6_5},
     *                * {@link edu.catalindumitru.bee.graphics.Render3DProvider#UNSIGNED_SHORT_4_4_4_4} or
     *                {@link edu.catalindumitru.bee.graphics.Render3DProvider#UNSIGNED_SHORT_5_5_5_1}.
     * @param image   the image from which to copy the pixels.
     */
    @Override
    public final native void texImage2D(int target, int level, int xOffset, int yOffset, int format, int type,
                                        ImageResource image) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.texSubImage2D(target, level, xOffset, yOffset, format, type, image);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Attaches the given shader to the shader program. The shader type must be either a compiled pixel shader or a
     * compiled vertex shader. One of each must be added to make the program functional.
     *
     * @param program the shader program to receive the new shader.
     * @param shader  the shader to be added to the program.
     */
    @Override
    public final native void attachShader(ShaderProgram program, Shader shader) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.attachShader(program, shader);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Compiles the given shader. The success status of the compilation will be stored in the shader itself.
     *
     * @param shader the shader to be compiled.
     */
    @Override
    public final native void compileShader(Shader shader) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.compileShader(shader);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Creates an empty shader program.
     *
     * @return an empty shader program.
     */
    @Override
    public final native ShaderProgram createShaderProgram() /*-{
        return @edu.catalindumitru.gwt.graphics.GwtShaderProgram::wrap(Ledu/catalindumitru/gwt/graphics/GwtShaderProgram;)(
                this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.createProgram()
        )
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Creates an empty shader.
     *
     * @param type the type of shader to create. Must be one of the fallowing :
     *             {@link edu.catalindumitru.bee.graphics.Render3DProvider#FRAGMENT_SHADER}
     *             or {@link edu.catalindumitru.bee.graphics.Render3DProvider#VERTEX_SHADER}.
     * @return an empty fragment of vertex shader.
     */
    @Override
    public final native Shader createShader(int type) /*-{
        return @edu.catalindumitru.gwt.graphics.GwtShader::wrap(Ledu/catalindumitru/gwt/graphics/GwtShader;)(
                this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.createShader(type)
        )
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Deletes the shader program from memory.
     *
     * @param program the shader program to be deleted.
     */
    @Override
    public final native void deleteShaderProgram(Shader program) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.deleteProgram(program);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Deletes the shader from memory.
     *
     * @param shader the shader to be deleted.
     */
    @Override
    public final native void deleteShader(Shader shader) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.deleteShader(shader);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Detaches the given shader from the shader program.
     *
     * @param program the program which contains the shader.
     * @param shader  the shader to be detached from the shader program.
     */
    @Override
    public final native void detachShader(ShaderProgram program, Shader shader) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.detachShader(program, shader);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Returns the attached shaders which reside inside the given program.
     *
     * @param program the program which contains the shaders.
     * @return the shaders which reside inside the given program.
     */
    @Override
    public final native List<Shader> getAttachedShaders(ShaderProgram program) /*-{
        var ret = @java.util.LinkedList::new()();

        var shaders = this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.getAttachedShaders();

        for (shader in shaders) {
            ret.add(
                    @edu.catalindumitru.gwt.graphics.GwtShader::wrap(Ledu/catalindumitru/gwt/graphics/GwtShader;)(
                            shaders[shader]
                    )
            )
        }

        return ret;
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Retrieves a param from the given shader program.
     *
     *
     * @param program the shader program which contains the params.
     * @param pName   the name of the param to retrieve. Must be one of the fallowing constants :
     *                {@link edu.catalindumitru.bee.graphics.Render3DProvider#DELETE_STATUS},
     *                {@link edu.catalindumitru.bee.graphics.Render3DProvider#LINK_STATUS},
     *                {@link edu.catalindumitru.bee.graphics.Render3DProvider#VALIDATE_STATUS},
     *                {@link edu.catalindumitru.bee.graphics.Render3DProvider#ATTACHED_SHADERS},
     *                {@link edu.catalindumitru.bee.graphics.Render3DProvider#ACTIVE_ATTRIBUTES} or
     *                {@link edu.catalindumitru.bee.graphics.Render3DProvider#ACTIVE_UNIFORMS}.
     * @return the requested parameter.
     */
    @Override
    public final native Object getProgramParameter(ShaderProgram program, int pName) /*-{
        return this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.getProgramParameter(program, pName);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Retrieves the shader program's information log, updated when linked or validated.
     *
     * @param program the shader program which contains the desired info log.
     * @return the info log of the given shader program.
     */
    @Override
    public final native String getProgramInfoLog(ShaderProgram program) /*-{
        return this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.getProgramInfoLog(program);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Retrieves a shader param.
     *
     *
     * @param shader the shader which contains the desired information.
     * @param pName  the name of the parameter to retrieve. Must be one of the fallowing constants :
     *               {@link edu.catalindumitru.bee.graphics.Render3DProvider#SHADER_TYPE} or
     *               {@link edu.catalindumitru.bee.graphics.Render3DProvider#COMPILE_STATUS}
     * @return the requested parameter.
     */
    @Override
    public final native Object getShaderParameter(ShaderProgram shader, int pName) /*-{
        return this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.getShaderParameter(shader, pName);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Retrieves the info log for the given shader, which is modified when the shader is compiled.
     *
     * @param shader the shader object which contains the desired info log.
     * @return the info log for the given shader.
     */
    @Override
    public final native String getShaderInfoLog(Shader shader) /*-{
        return this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.getShaderInfoLog(shader);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Retrieves the source code for the given shader.
     *
     * @param shader the shader which contains
     * @return the source code for the given shader.
     */
    @Override
    public final native String getShaderSource(Shader shader) /*-{
        return this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.getShaderSource(shader);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Determines if the given object is a valid {@link edu.catalindumitru.bee.graphics.ShaderProgram} object.
     *
     * @param shaderProgram the object to test.
     * @return if the given object is a valid {@link edu.catalindumitru.bee.graphics.ShaderProgram} object.
     */
    @Override
    public final native boolean isShaderProgram(ShaderProgram shaderProgram) /*-{
        return this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.isProgram(shaderProgram);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Determines if the given object is a valid {@link edu.catalindumitru.bee.graphics.Shader} object.
     *
     * @param shader the object to test.
     * @return if the given object is a valid {@link edu.catalindumitru.bee.graphics.Shader} object.
     */
    @Override
    public final native boolean isShader(Shader shader) /*-{
        return this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.isShader(shader);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Links the vertex shader and the fragment shader which reside inside the given shader program. The success
     * status will be stored inside the shader program.
     *
     * @param program the shader program to link.
     */
    @Override
    public final native void linkProgram(ShaderProgram program) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.linkProgram(program);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Replaces the source code in the given shader.
     *
     * @param shader the shader which will contain the new source code.
     * @param source the new source code.
     */
    @Override
    public final native void shaderSource(Shader shader, String source) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.shaderSource(shader, source);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Specifies which shader program to use in the current rendering state. Switching shaders is a costly
     * operation and must be done as infrequently as possible.
     *
     * @param program the shader program to be installed.
     */
    @Override
    public final native void useProgram(ShaderProgram program) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.useProgram(program);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Validates a compiled shader program. The result of the validation will be stored in the shader program's
     * info log.
     *
     * @param program which shader program to validate.
     */
    @Override
    public final native void validateProgram(ShaderProgram program) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.validateProgram(program);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Associates an anonymous attribute, which resides at the given index value, with a user defined string.
     *
     * @param program a handle to the shader program which contains the attribute.
     * @param index   the index of the attribute.
     * @param name    the new user defined id given to teh attribute.
     */
    @Override
    public final native void bindAttributeLocation(ShaderProgram program, int index, String name) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.bindAttribLocation(program, index, name);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Disables a generic vertex attribute array.
     *
     * @param index the index of the vertex array.
     */
    @Override
    public final native void disableVertexAttributeArray(int index) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.disableVertexAttribArray(index);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Enables a generic vertex attribute array.
     *
     * @param index the index of the attribute array.
     */
    @Override
    public final native void enableVertexAttributeArray(int index) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.enableVertexAttribArray(index);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Returns information on the active attribute which resides on the index value provided.
     *
     * @param program the shader program which contains the attribute.
     * @param index   the index of the attribute value inside the shader.
     * @return information on the specified attribute.
     */
    @Override
    public final native ActiveInfo getActiveAttribute(ShaderProgram program, int index) /*-{
        return @edu.catalindumitru.gwt.graphics.GwtActiveInfo::wrap(Ledu/catalindumitru/gwt/graphics/GwtActiveInfo;)(
                this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.getActiveAttrib(program, index)
        );
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Returns information on the active uniform which resides on the index value provided.
     *
     * @param program the shader program which contains the uniform.
     * @param index   the index of the uniform value.
     * @return information on the specified uniform value.
     */
    @Override
    public final native ActiveInfo getActiveUniform(ShaderProgram program, int index) /*-{
        return @edu.catalindumitru.gwt.graphics.GwtActiveInfo::wrap(Ledu/catalindumitru/gwt/graphics/GwtActiveInfo;)(
                this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.getActiveUniform(program, index)
        );
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Retrieves the named attribute location from the shader program.
     *
     * @param program the shader program which contains the attribute.
     * @param name    the name of the attribute to retrieve.
     * @return the index in which the attribute is located. If the attribute is not found, then -1 is returned.
     */
    @Override
    public final native int getAttributeLocation(ShaderProgram program, String name) /*-{
        return this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.getAttribLocation(program, name);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Retrieves the uniform value.
     *
     * @param program  the shader program which contains the desired uniform.
     * @param location a location to the uniform value.
     * @return the value of the uniform.
     */
    @Override
    public final native List<Float> getUniform(ShaderProgram program, UniformLocation location) /*-{
        var ret = @java.util.LinkedList::new()();

        var uniforms = this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.getUniform(program, location);

        for (uni in uniforms) {
            ret.add(uniforms[uni]);
        }

        return ret;
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Retrieves the location of a named uniform. If the uniform is not found, then null is returned.
     *
     * @param program the program which contains the uniform.
     * @param name    the name of the uniform.
     * @return the uniform location.
     */
    @Override
    public final native UniformLocation getUniformLocation(ShaderProgram program, String name) /*-{
        return @edu.catalindumitru.gwt.graphics.GwtUniformLocation::wrap(Ledu/catalindumitru/gwt/graphics/GwtUniformLocation;)(
                this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.getUniformLocation(program, name)
        );
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Retrieves a parameter for a generic vertex attribute.
     *
     * @param index the index of the attribute.
     * @param pName the parameter name to be queried. Must be one of the fallowing constants :
     *              {@link edu.catalindumitru.bee.graphics.Render3DProvider#VERTEX_ATTRIB_ARRAY_BUFFER_BINDING},
     *              {@link edu.catalindumitru.bee.graphics.Render3DProvider#VERTEX_ATTRIB_ARRAY_ENABLED},
     *              {@link edu.catalindumitru.bee.graphics.Render3DProvider#VERTEX_ATTRIB_ARRAY_SIZE},
     *              {@link edu.catalindumitru.bee.graphics.Render3DProvider#VERTEX_ATTRIB_ARRAY_STRIDE},
     *              {@link edu.catalindumitru.bee.graphics.Render3DProvider#VERTEX_ATTRIB_ARRAY_TYPE},
     *              {@link edu.catalindumitru.bee.graphics.Render3DProvider#VERTEX_ATTRIB_ARRAY_NORMALIZED}
     *              or {@link edu.catalindumitru.bee.graphics.Render3DProvider#CURRENT_VERTEX_ATTRIBUTE}.
     * @return the requested parameter value.
     */
    @Override
    public final native Object getVertexAttribute(int index, int pName) /*-{
        return this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.getVertexAttrib(index, pName);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Retrieves the offset of a generic attribute.
     *
     * @param index the index of the attribute to be returned.
     * @param pName the name of the generic attribute parameter to be returned. Must be
     *              {@link edu.catalindumitru.bee.graphics.Render3DProvider#VERTEX_ATTRIB_ARRAY_POINTER}.
     * @return the offset of the given generic attribute.
     */
    @Override
    public final native int getVertexAttributeOffset(int index, int pName) /*-{
        return this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.getVertexAttribOffset(index, pName);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Specifies the value of a uniform in the current shader program. The uniform must be a single float variable.
     *
     * @param location the uniform location of where to set the value.
     * @param x        the new value of the uniform.
     */
    @Override
    public final native void uniform1f(UniformLocation location, float x) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.uniform1f(location, x);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Specifies the value of a uniform in the current shader program. The uniform must be an array of float variables.
     *
     * @param location the uniform location of where to set the value.
     * @param v        the new values for the uniform.
     */
    @Override
    public final native void uniform1f(UniformLocation location, NativeArray<Float> v) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.uniform1fv(location, v);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Specifies the value of a uniform in the current shader program. The uniform must be a single integer variable.
     *
     * @param location the uniform location of where to set the value.
     * @param x        the new value of the uniform.
     */
    @Override
    public final native void uniform1i(UniformLocation location, int x) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.uniform1i(location, x);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Specifies the value of a uniform in the current shader program. The uniform must be an array of float variables.
     *
     * @param location the uniform location of where to set the value.
     * @param v        the new values for the uniform.
     */
    @Override
    public final native void uniform1i(UniformLocation location, NativeArray<Integer> v) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.uniform1iv(location, v);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Specifies the value of a uniform in the current shader program. The uniform must be a structure comprised
     * of two float variables.
     *
     * @param location the uniform location of where to set the value.
     * @param x        the new value for the first variable of the uniform.
     * @param y        the new value for the second variable of the uniform.
     */
    @Override
    public final native void uniform2f(UniformLocation location, float x, float y) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.uniform2f(location, x, y);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Specifies the value of a uniform in the current shader program. The uniform must be an array of structures
     * comprised of two float variables.
     *
     * @param location the uniform location of where to set the value.
     * @param v        the new value of the uniform. The length of the array must be a multiple of two.
     */
    @Override
    public final native void uniform2f(UniformLocation location, NativeArray<Float> v) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.uniform2fv(location, v);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Specifies the value of a uniform in the current shader program. The uniform must be a structure comprised
     * of two integer variables.
     *
     * @param location the uniform location of where to set the value.
     * @param x        the new value for the first variable of the uniform.
     * @param y        the new value for the second variable of the uniform..
     */
    @Override
    public final native void uniform2i(UniformLocation location, int x, int y) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.uniform2i(location, x, y);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Specifies the value of a uniform in the current shader program. The uniform must be an array of structures
     * comprised of two integer variables.
     *
     * @param location the uniform location of where to set the value.
     * @param v        the new value of the uniform. The length of the array must be a multiple of two.
     */
    @Override
    public final native void uniform2i(UniformLocation location, NativeArray<Integer> v) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.uniform2iv(location, v);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Specifies the value of a uniform in the current shader program. The uniform must be a structure comprised
     * of three float variables.
     *
     * @param location the uniform location of where to set the value.
     * @param x        the new value for the first variable of the uniform.
     * @param y        the new value for the second variable of the uniform.
     * @param z        the new value for the third variable of the uniform.
     */
    @Override
    public final native void uniform3f(UniformLocation location, float x, float y, float z) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.uniform3f(location, x, y, z);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Specifies the value of a uniform in the current shader program. The uniform must be an array of structures
     * comprised of three float variables.
     *
     * @param location the uniform location of where to set the value.
     * @param v        the new value of the uniform. The length of the array must be a multiple of three.
     */
    @Override
    public final native void uniform3f(UniformLocation location, NativeArray<Float> v) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.uniform3fv(location, v);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Specifies the value of a uniform in the current shader program. The uniform must be a structure comprised
     * of three integer variables.
     *
     * @param location the uniform location of where to set the value.
     * @param x        the new value for the first variable of the uniform.
     * @param y        the new value for the second variable of the uniform.
     * @param z        the new value for the third variable of the uniform.
     */
    @Override
    public final native void uniform3i(UniformLocation location, int x, int y, int z) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.uniform3i(location, x, y, z);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Specifies the value of a uniform in the current shader program. The uniform must be an array of structures
     * comprised of three integer variables.
     *
     * @param location the uniform location of where to set the value.
     * @param v        the new value of the uniform. The length of the array must be a multiple of three.
     */
    @Override
    public final native void uniform3i(UniformLocation location, NativeArray<Integer> v) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.uniform3iv(location, v);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Specifies the value of a uniform in the current shader program. The uniform must be a structure comprised
     * of four float variables.
     *
     * @param location the uniform location of where to set the value.
     * @param x        the new value for the first variable of the uniform.
     * @param y        the new value for the second variable of the uniform.
     * @param z        the new value for the third variable of the uniform.
     * @param w        the new value for the fourth variable of the uniform.
     */
    @Override
    public final native void uniform4f(UniformLocation location, float x, float y, float z, float w) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.uniform4f(location, x, y, z, w);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Specifies the value of a uniform in the current shader program. The uniform must be an array of structures
     * comprised of four float variables.
     *
     * @param location the uniform location of where to set the value.
     * @param v        the new value of the uniform. The length of the array must be a multiple of four.
     */
    @Override
    public final native void uniform4f(UniformLocation location, NativeArray<Float> v) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.uniform4fv(location, v);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Specifies the value of a uniform in the current shader program. The uniform must be a structure comprised
     * of four integer variables.
     *
     * @param location the uniform location of where to set the value.
     * @param x        the new value for the first variable of the uniform.
     * @param y        the new value for the second variable of the uniform.
     * @param z        the new value for the third variable of the uniform.
     * @param w        the new value for the fourth variable of the uniform.
     */
    @Override
    public final native void uniform4i(UniformLocation location, int x, int y, int z, int w) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.uniform4i(location, x, y, z, w);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Specifies the value of a uniform in the current shader program. The uniform must be an array of structures
     * comprised of four integer variables.
     *
     * @param location the uniform location of where to set the value.
     * @param v        the new value of the uniform. The length of the array must be a multiple of four.
     */
    @Override
    public final native void uniform4i(UniformLocation location, NativeArray<Integer> v) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.uniform4iv(location, v);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Sets the uniform value in the current shader program. The uniform value must be a structure comprised
     * of 4 float values (a 2x2 matrix).
     *
     * @param location  the uniform location in the current shader program.
     * @param transpose if the matrix should be transposed. Should be false.
     * @param value     teh new value of the uniform. The length if the array must be 4 (a 2x2 matrix).
     */
    @Override
    public final native void uniformMatrix2f(UniformLocation location, boolean transpose, NativeArray<Float> value) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.uniformMatrix2fv(location, transpose, value);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Sets the uniform value in the current shader program. The uniform value must be a structure comprised
     * of 9 float values (a 3x2 matrix).
     *
     * @param location  the uniform location in the current shader program.
     * @param transpose if the matrix should be transposed. Should be false.
     * @param value     teh new value of the uniform. The length if the array must be 9 (a 3x3 matrix).
     */
    @Override
    public final native void uniformMatrix3f(UniformLocation location, boolean transpose, NativeArray<Float> value) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.uniformMatrix3fv(location, transpose, value);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Sets the uniform value in the current shader program. The uniform value must be a structure comprised
     * of 16 float values (a 4x4 matrix).
     *
     * @param location  the uniform location in the current shader program.
     * @param transpose if the matrix should be transposed. Should be false.
     * @param value     teh new value of the uniform. The length if the array must be 16 (a 4x4 matrix).
     */
    @Override
    public final native void uniformMatrix4f(UniformLocation location, boolean transpose, NativeArray<Float> value) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.uniformMatrix4fv(location, transpose, value);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Sets the value of a generic attribute which resides at the given index. The attribute must be a single float
     * value.
     *
     * @param index the index of the generic attribute.
     * @param x     the value of the generic attribute.
     */
    @Override
    public final native void vertexAttribute1f(int index, float x) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.vertexAttrib1f(index, x);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Sets the value of a generic attribute which resides at the given index. The attribute must be a single float
     * value.
     *
     * @param index  the index of the generic attribute.
     * @param values the new values of the attribute.
     */
    @Override
    public final native void vertexAttribute1f(int index, NativeArray<Float> values) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.vertexAttrib1fv(index, values);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Sets the value of a generic attribute which resides at the given index. The attribute must be a structure
     * or 2 float values.
     *
     * @param index the index of the generic attribute.
     * @param x     the first value of the generic attribute.
     * @param y     the second value of the generic attribute.
     */
    @Override
    public final native void vertexAttribute2f(int index, float x, float y) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.vertexAttrib2f(index, x, y);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Sets the value of a generic attribute which resides at the given index. The attribute must be a structure
     * or 2 float values.
     *
     * @param index  the index of the generic attribute.
     * @param values the new values of the attribute. The length of the array must be a multiple of 2.
     */
    @Override
    public final native void vertexAttribute2f(int index, NativeArray<Float> values) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.vertexAttrib2fv(index, values);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Sets the value of a generic attribute which resides at the given index. The attribute must be a structure
     * or 3 float values.
     *
     * @param index the index of the generic attribute.
     * @param x     the first value of the generic attribute.
     * @param y     the second value of the generic attribute.
     * @param z     the third value of the generic attribute.
     */
    @Override
    public final native void vertexAttribute3f(int index, float x, float y, float z) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.vertexAttrib3f(index, x, y, z);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Sets the value of a generic attribute which resides at the given index. The attribute must be a structure
     * or 3 float values.
     *
     * @param index  the index of the generic attribute.
     * @param values the new values of the attribute. The length of the array must be a multiple of 3.
     */
    @Override
    public final native void vertexAttribute3f(int index, NativeArray<Float> values) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.vertexAttrib3fv(index, values);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Sets the value of a generic attribute which resides at the given index. The attribute must be a structure
     * or 4 float values.
     *
     * @param index the index of the generic attribute.
     * @param x     the first value of the generic attribute.
     * @param y     the second value of the generic attribute.
     * @param z     the third value of the generic attribute.
     * @param w     the fourth value of the generic attribute.
     */
    @Override
    public final native void vertexAttribute4f(int index, float x, float y, float z, float w) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.vertexAttrib4f(index, x, y, z, w);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Sets the value of a generic attribute which resides at the given index. The attribute must be a structure
     * or 4 float values.
     *
     * @param index  the index of the generic attribute.
     * @param values the new values of the attribute. The length of the array must be a multiple of 4.
     */
    @Override
    public final native void vertexAttribute4f(int index, NativeArray<Float> values) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.vertexAttrib4fv(index, values);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Assigns the current bound buffer to the attribute at the given location.
     *
     * @param index      the index of the attribute to be modified.
     * @param size       the number of components per vertex attribute. Must be a value between 1 and 4.
     * @param type       the data type of the current bound buffer. Must be one of the fallowing constants :
     *                   {@link edu.catalindumitru.bee.graphics.Render3DProvider#BYTE},
     *                   {@link edu.catalindumitru.bee.graphics.Render3DProvider#UNSIGNED_BYTE},
     *                   {@link edu.catalindumitru.bee.graphics.Render3DProvider#SHORT},
     *                   {@link edu.catalindumitru.bee.graphics.Render3DProvider#UNSIGNED_SHORT} or
     *                   {@link edu.catalindumitru.bee.graphics.Render3DProvider#FLOAT}
     *                   GL_BYTE, GL_UNSIGNED_BYTE, GL_SHORT, GL_UNSIGNED_SHORT, GL_FIXED, or GL_FLOAT
     * @param normalized if the given buffer should be normalized.
     * @param stride     the offset in bytes between consecutive vertex attributes.
     * @param offset     the offset in bytes between consecutive vertex attributes.
     */
    @Override
    public final native void vertexAttributePointer(int index, int size, int type, boolean normalized,
                                                    int stride, int offset) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.vertexAttribPointer(index, size, type, normalized, stride, offset);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Sets the active buffer to the given params.
     *
     * @param target the type of buffer binding. Must be either
     *               {@link edu.catalindumitru.bee.graphics.Render3DProvider#ARRAY_BUFFER} or
     *               {@link edu.catalindumitru.bee.graphics.Render3DProvider#ELEMENT_ARRAY_BUFFER}
     * @param buffer the buffer to bind.
     */
    @Override
    public final native void bindBuffer(int target, Buffer buffer) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.bindBuffer(target, buffer);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Fills the current buffer with the data from the native array.
     *
     * @param target the target buffer (either {@link edu.catalindumitru.bee.graphics.Render3DProvider#ARRAY_BUFFER
     *               } or {@link edu.catalindumitru.bee.graphics.Render3DProvider#ELEMENT_ARRAY_BUFFER}).
     * @param data   the array buffer containing the new data.
     * @param usage  the usage pattern of the buffer. Must be either
     *               {@link edu.catalindumitru.bee.graphics.Render3DProvider#STREAM_DRAW},
     *               {@link edu.catalindumitru.bee.graphics.Render3DProvider#STATIC_DRAW} or
     *               {@link edu.catalindumitru.bee.graphics.Render3DProvider#DYNAMIC_DRAW}.
     */
    @Override
    public final native void bufferData(int target, NativeArray<?> data, int usage) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.bufferData(target, data, usage);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Fills a portion of the current buffer with the data from the native array.
     *
     * @param target the target buffer  (either {@link edu.catalindumitru.bee.graphics.Render3DProvider#ARRAY_BUFFER}
     *               or {@link edu.catalindumitru.bee.graphics.Render3DProvider#ELEMENT_ARRAY_BUFFER}).
     * @param offset the offset from the begining of the data store from which to begin filling with the new buffer.
     * @param data   the new data buffer.
     */
    @Override
    public final native void bufferSubData(int target, int offset, NativeArray<?> data) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.bufferSubData(target, offset, data);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Creates an empty buffer.
     *
     * @return an empty buffer.
     */
    @Override
    public final native Buffer createBuffer() /*-{
        return @edu.catalindumitru.gwt.graphics.GwtBuffer::wrap(Ledu/catalindumitru/gwt/graphics/GwtBuffer;)(
                this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.createBuffer()
        )
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Deletes the buffer from memory.
     *
     * @param buffer the buffer to be deleted.
     */
    @Override
    public final native void deleteBuffer(Buffer buffer) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.deleteBuffer(buffer);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Determines if the given object is a valid {@link edu.catalindumitru.bee.graphics.Buffer} object.
     *
     * @param buffer the buffer object to verify.
     * @return whether or not the given object is a valid {@link edu.catalindumitru.bee.graphics.Buffer} object.
     */
    @Override
    public final native boolean isBuffer(Buffer buffer) /*-{
        return this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.isBuffer(buffer);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Sets the active frame buffer to the given params.
     *
     * @param target      the type of buffer binding. Must be either
     *                    {@link edu.catalindumitru.bee.graphics.Render3DProvider#ARRAY_BUFFER} or
     *                    {@link edu.catalindumitru.bee.graphics.Render3DProvider#ELEMENT_ARRAY_BUFFER}
     * @param frameBuffer the frame buffer to bind.
     */
    @Override
    public final native void bindFrameBuffer(int target, FrameBuffer frameBuffer) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.bindFramebuffer(target, frameBuffer);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Checks the completion status of the frame buffer.
     *
     * @param target the target buffer. Must be the constant
     *               {@link edu.catalindumitru.bee.graphics.Render3DProvider#FRAMEBUFFER};
     * @return the completion status of the frame buffer. Will be one of the fallowing :
     *         {@link edu.catalindumitru.bee.graphics.Render3DProvider#FRAMEBUFFER_INCOMPLETE_ATTACHMENT},
     *         {@link edu.catalindumitru.bee.graphics.Render3DProvider#FRAMEBUFFER_INCOMPLETE_DIMENSIONS}
     *         {@link edu.catalindumitru.bee.graphics.Render3DProvider#FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT} or
     *         {@link edu.catalindumitru.bee.graphics.Render3DProvider#FRAMEBUFFER_UNSUPPORTED}
     */
    @Override
    public final native int checkFrameBufferStatus(int target) /*-{
        return this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.checkFramebufferStatus(target);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Creates an empty fame buffer.
     *
     * @return an empty frame buffer.
     */
    @Override
    public final native FrameBuffer createFameBuffer() /*-{
        return @edu.catalindumitru.gwt.graphics.GwtFrameBuffer::wrap(Ledu/catalindumitru/gwt/graphics/GwtFrameBuffer;)(
                this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.createFramebuffer()
        )
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Deletes the frame buffer from memory.
     *
     * @param frameBuffer the frame buffer to be deleted.
     */
    @Override
    public final native void deleteFrameBuffer(FrameBuffer frameBuffer) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.deleteFramebuffer(frameBuffer);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Attach a render buffer to the current frame buffer.
     *
     * @param target             the target frame buffer. Must be the constant :
     *                           {@link edu.catalindumitru.bee.graphics.Render3DProvider#FRAMEBUFFER}
     * @param attachment         the attachment point of which the render buffer should attach. Must be one of the fallowing
     *                           constants : {Render3DProvider#COLOR_ATTACHMENT0}, {Render3DProvider#DEPTH_ATTACHMENT} or
     *                           {Render3DProvider#STENCIL_ATTACHMENT}
     * @param renderBufferTarget the target render buffer. Must be the constant : {Render3DProvider#RENDERBUFFER}
     * @param renderBuffer       the render buffer object to atach.
     */
    @Override
    public final native void frameBufferRenderBuffer(int target, int attachment, int renderBufferTarget,
                                                     RenderBuffer renderBuffer) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.framebufferRenderbuffer(target, attachment, renderBufferTarget, renderBuffer);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Attach a texture to the current frame buffer.
     *
     * @param target        the target frame buffer. Must be the constant {Render3DProvider#FRAMEBUFFER}
     * @param attachment    the target attachment point for the texture.  Must be one of the fallowing
     *                      constants : {Render3DProvider#COLOR_ATTACHMENT0}, {Render3DProvider#DEPTH_ATTACHMENT} or
     *                      {Render3DProvider#STENCIL_ATTACHMENT}
     * @param textureTarget the target texture. Must be one of the fallowing constants :
     *                      {Render3DProvider#TEXTURE_2D},
     *                      {Render3DProvider#TEXTURE_CUBE_MAP},
     *                      {@link edu.catalindumitru.bee.graphics.Render3DProvider#TEXTURE_CUBE_MAP_NEGATIVE_X},
     *                      {@link edu.catalindumitru.bee.graphics.Render3DProvider#TEXTURE_CUBE_MAP_POSITIVE_X},
     *                      {@link edu.catalindumitru.bee.graphics.Render3DProvider#TEXTURE_CUBE_MAP_NEGATIVE_Y},
     *                      {@link edu.catalindumitru.bee.graphics.Render3DProvider#TEXTURE_CUBE_MAP_POSITIVE_Y},
     *                      {@link edu.catalindumitru.bee.graphics.Render3DProvider#TEXTURE_CUBE_MAP_NEGATIVE_Z} or
     *                      {@link edu.catalindumitru.bee.graphics.Render3DProvider#TEXTURE_CUBE_MAP_POSITIVE_Z}
     * @param texture       the texture to attach.
     * @param level         the mipmap level of the texture to attach. Must be 0.
     */
    @Override
    public final native void frameBufferTexture2D(int target, int attachment, int textureTarget,
                                                  Texture texture, int level) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.framebufferTexture2D(target, attachment, textureTarget, texture, level);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Retrieves a param value for the current frame buffer.
     *
     *
     * @param target     the target frame buffer. Must be the constant :
     *                   {@link edu.catalindumitru.bee.graphics.Render3DProvider#FRAMEBUFFER}
     * @param attachment the symbolic frame buffer attachment. Must be one of the fallowing constants :
     *                   {@link edu.catalindumitru.bee.graphics.Render3DProvider#COLOR_ATTACHMENT0},
     *                   {@link edu.catalindumitru.bee.graphics.Render3DProvider#DEPTH_ATTACHMENT},
     *                   {@link edu.catalindumitru.bee.graphics.Render3DProvider#STENCIL_ATTACHMENT}.
     * @param pName      the param name of which to retrieve the information. Must be one of the fallowing constants :
     *                   {@link edu.catalindumitru.bee.graphics.Render3DProvider#FRAMEBUFFER_ATTACHMENT_OBJECT_NAME},
     *                   {@link edu.catalindumitru.bee.graphics.Render3DProvider#FRAMEBUFFER_ATTACHMENT_OBJECT_TYPE},
     *                   {@link edu.catalindumitru.bee.graphics.Render3DProvider#FRAMEBUFFER_ATTACHMENT_TEXTURE_CUBE_MAP_FACE} or
     *                   {@link edu.catalindumitru.bee.graphics.Render3DProvider#FRAMEBUFFER_ATTACHMENT_TEXTURE_LEVEL}.
     * @return the requested parameter.
     */
    @Override
    public final native Object getFrameBufferAttachmentParameter(int target, int attachment, int pName) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.getFramebufferAttachmentParameter(target, attachment, pName);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Determines if the given object is a valid {@link edu.catalindumitru.bee.graphics.FrameBuffer} object.
     *
     * @param frameBuffer the object to test.
     * @return if the given object is a valid {@link edu.catalindumitru.bee.graphics.FrameBuffer} object.
     */
    @Override
    public final native boolean isFrameBuffer(FrameBuffer frameBuffer) /*-{
        return this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.isFramebuffer(frameBuffer);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Reads pixels from the current frame buffer and puts them in the given NativeArray.
     *
     * @param x      the x coordinate for the lower left corner of the rectangle from which to read the pixels.
     * @param y      the y coordinate for the lower left corner of the rectangle from which to read the pixels.
     * @param width  the width of the rectangle from which to read the pixels.
     * @param height the height of the rectangle from which to read the pixels.
     * @param format the format of the pixel data. Must be one of the fallowing constants :
     *               {@link edu.catalindumitru.bee.graphics.Render3DProvider#RGB},
     *               {@link edu.catalindumitru.bee.graphics.Render3DProvider#ALPHA} or
     *               {@link edu.catalindumitru.bee.graphics.Render3DProvider#RGBA}
     * @param type   the type of the pixel data. Must be one of the fallowing constants :
     *               {@link edu.catalindumitru.bee.graphics.Render3DProvider#UNSIGNED_BYTE},
     *               {@link edu.catalindumitru.bee.graphics.Render3DProvider#UNSIGNED_SHORT_5_6_5},
     *               {@link edu.catalindumitru.bee.graphics.Render3DProvider#UNSIGNED_SHORT_4_4_4_4} or
     *               {@link edu.catalindumitru.bee.graphics.Render3DProvider#UNSIGNED_SHORT_5_5_5_1}.
     * @param pixels the array where to store the pixels.
     */
    @Override
    public final native void readPixels(int x, int y, int width, int height, int format,
                                        int type, NativeArray<?> pixels) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.readPixels(x, y, width, height, format, type, pixels);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Sets the active render buffer to the given params.
     *
     * @param target       the type of buffer binding. Must be either
     *                     {@link edu.catalindumitru.bee.graphics.Render3DProvider#ARRAY_BUFFER} or
     *                     {@link edu.catalindumitru.bee.graphics.Render3DProvider#ELEMENT_ARRAY_BUFFER}
     * @param renderBuffer the render buffer to bind.
     */
    @Override
    public final native void bindRenderBuffer(int target, RenderBuffer renderBuffer) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.bindRenderbuffer(target, renderBuffer);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Creates an empty render buffer.
     *
     * @return an empty render buffer.
     */
    @Override
    public final native RenderBuffer createRenderBuffer() /*-{
        return @edu.catalindumitru.gwt.graphics.GwtRenderBuffer::wrap(Ledu/catalindumitru/gwt/graphics/GwtRenderBuffer;)(
                this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.createRenderbuffer()
        )
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Deletes the render buffer from memory.
     *
     * @param buffer the render buffer to be deleted.
     */
    @Override
    public final native void deleteRenderBuffer(RenderBuffer buffer) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.deleteRenderbuffer(buffer);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Retrieves a param from the current render buffer.
     *
     *
     * @param target the target render buffer. Must be the constant :
     *               {@link edu.catalindumitru.bee.graphics.Render3DProvider#RENDERBUFFER}.
     * @param pName  the param name to retrieve. Must be one of the fallowing constants :
     *               {@link edu.catalindumitru.bee.graphics.Render3DProvider#RENDERBUFFER_WIDTH},
     *               {@link edu.catalindumitru.bee.graphics.Render3DProvider#RENDERBUFFER_HEIGHT},
     *               {@link edu.catalindumitru.bee.graphics.Render3DProvider#RENDERBUFFER_INTERNAL_FORMAT},
     *               {@link edu.catalindumitru.bee.graphics.Render3DProvider#RENDERBUFFER_RED_SIZE},
     *               {@link edu.catalindumitru.bee.graphics.Render3DProvider#RENDERBUFFER_GREEN_SIZE},
     *               {@link edu.catalindumitru.bee.graphics.Render3DProvider#RENDERBUFFER_BLUE_SIZE},
     *               {@link edu.catalindumitru.bee.graphics.Render3DProvider#RENDERBUFFER_ALPHA_SIZE},
     *               {@link edu.catalindumitru.bee.graphics.Render3DProvider#RENDERBUFFER_DEPTH_SIZE}
     *               or {@link edu.catalindumitru.bee.graphics.Render3DProvider#RENDERBUFFER_STENCIL_SIZE}.
     * @return the requested param.
     */
    @Override
    public final native Object getRenderBufferParameter(int target, int pName) /*-{
        return this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.getRenderbufferParameter(target, pName);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Determines if the given object is a valid {@link edu.catalindumitru.bee.graphics.RenderBuffer} object.
     *
     * @param renderBuffer the object to test.
     * @return if the given object is a valid {@link edu.catalindumitru.bee.graphics.RenderBuffer} object.
     */
    @Override
    public final native boolean isRenderBuffer(RenderBuffer renderBuffer) /*-{
        return this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.isRenderbuffer(renderBuffer);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Creates an empty render buffer storage.
     *
     * @param target         the target render buffer. Must be the constant :
     *                       {@link edu.catalindumitru.bee.graphics.Render3DProvider#RENDERBUFFER}.
     * @param internalFormat the internal format of the render buffer. Must be one of the fallowing constants :
     *                       {@link edu.catalindumitru.bee.graphics.Render3DProvider#RGBA4},
     *                       {@link edu.catalindumitru.bee.graphics.Render3DProvider#RGB565},
     *                       {@link edu.catalindumitru.bee.graphics.Render3DProvider#RGB5_A1},
     *                       {@link edu.catalindumitru.bee.graphics.Render3DProvider#DEPTH_COMPONENT16} or
     *                       {@link edu.catalindumitru.bee.graphics.Render3DProvider#STENCIL_INDEX8}.
     * @param width          the width of the render buffer.
     * @param height         the height of the render buffer.
     */
    @Override
    public final native void renderBufferStorage(int target, int internalFormat, int width, int height) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender3DProvider::context3d.renderbufferStorage(target, internalFormat, width, height);
    }-*/;

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
}
