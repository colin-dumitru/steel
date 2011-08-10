package edu.catalindumitru.gwt.graphics;

import com.google.gwt.canvas.dom.client.Context2d;
import edu.catalindumitru.bee.content.ImageResource;
import edu.catalindumitru.bee.graphics.*;
import edu.catalindumitru.bee.math.Point2D;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 8/3/11
 * Time: 9:24 AM
 */
public class GwtRender2DProvider implements Render2DProvider {

    /*connector for javascript*/
    Context2d gwtContext;

    /*active drawing params*/
    protected Color fillColor;
    protected Color strokeColor;
    protected Gradient fillGradient;
    protected Gradient strokeGradient;

    protected STYLE fillStyle;
    protected STYLE strokeStyle;
    /*these 2 are set to true whenever a gradients needs to be recreated for every draw operation*/
    protected boolean dynamicFillGradient;
    protected boolean dynamicStrokeGradient;

    protected float alpha;

    protected CAP capStyle;
    protected JOIN joinStyle;
    protected float miterLimit;
    protected float lineWidth;

    protected TEXT_ALIGN textAlign;
    protected TEXT_BASELINE textBaseline;
    protected Font font;

    /*used to calculate font position when it is drawn at the screen - one unit is the text's height/width*/
    protected float textOffsetX;
    protected float textOffsetY;

    /*Needs context update. Sort of like a cache but for parameters. Some params like style, colors depend on each
         other so it is useful that we don;t have to update all params for a single change of one param.*/
    boolean needsStyleUpdate;
    boolean needsFontUpdate;

    /*screen dimensions*/
    protected int screenWidth;
    protected int screenHeight;


    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public GwtRender2DProvider(Context2d gwtContext) {
        this.setGwtContext(gwtContext);

        /*Initial drawing colors*/
        this.fillColor = Color.WHITE;
        this.strokeColor = Color.BLACK;
        this.fillGradient = Gradient.BABY_BLUE;
        this.strokeGradient = Gradient.BABY_BLUE;

        this.fillStyle = STYLE.COLOR;
        this.strokeStyle = STYLE.COLOR;

        /*Initial drawing styles*/
        this.capStyle = CAP.BUTT;
        this.joinStyle = JOIN.MITER;

        /*Initial text drawing params*/
        this.textAlign = TEXT_ALIGN.RIGHT;
        this.textBaseline = TEXT_BASELINE.ALPHABETIC;
        this.font = Font.DEFAULT;

        /*Needs context update. Sort of like a cache but for parameters. Some params like style, colors depend on each
         other so it is useful that we don;t have to update all params for a single change of one param.*/
        this.needsStyleUpdate = true;
        this.needsFontUpdate = true;

    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public GwtRender2DProvider() {
        this(null);
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public Context2d getGwtContext() {
        return gwtContext;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public void setGwtContext(Context2d gwtContext) {
        this.gwtContext = gwtContext;

        if (this.gwtContext != null) {
            /*Update screen dimensions*/
            this.screenWidth = gwtContext.getCanvas().getWidth();
            this.screenHeight = gwtContext.getCanvas().getHeight();
        }

        this.needsFontUpdate = true;
        this.needsStyleUpdate = true;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Returns the current screen width.
     *
     * @return screen width.
     */
    @Override
    public int getScreenWidth() {
        return this.screenWidth;  //To change body of implemented methods use File | Settings | File Templates.
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Returns the current screen height.
     *
     * @return screen height.
     */
    @Override
    public int getScreenHeight() {
        return this.screenHeight;  //To change body of implemented methods use File | Settings | File Templates.
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Sets the active fille color.
     *
     * @param color the color to use for future drawings.
     */
    @Override
    public void setFillColor(Color color) {
        this.fillColor = color;

        this.needsStyleUpdate = true;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Sets the active fill gradient.
     */
    @Override
    public void setFillGradient(Gradient gradient) {
        this.fillGradient = gradient;

        this.needsStyleUpdate = true;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Sets the active stroke color.
     *
     * @param color the color to use for future drawings.
     */
    @Override
    public void setStrokeColor(Color color) {
        this.strokeColor = color;

        this.needsStyleUpdate = true;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Sets the active stroke gradient.
     */
    @Override
    public void setStrokeGradient(Gradient gradient) {
        this.strokeGradient = gradient;

        this.needsStyleUpdate = true;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Sets the active drawing color for filled shapes.
     *
     * @param style drawing style (either color mode or gradient).
     */
    @Override
    public void setFillStyle(STYLE style) {
        this.fillStyle = style;

        this.needsStyleUpdate = true;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Sets the active drawing color for stroked shapes.
     *
     * @param style drawing style (either color mode or gradient).
     */
    @Override
    public void setStrokeStyle(STYLE style) {
        this.strokeStyle = style;

        this.needsStyleUpdate = true;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Sets the current drawing alpha. All subsequent drawing operations will be affected by this alpha value.
     *
     * @param alpha a value between 0 and 1 representing drawing alpha.
     */
    @Override
    public void setAlpha(float alpha) {
        this.alpha = alpha;

        if (gwtContext != null)
            this.gwtContext.setGlobalAlpha(alpha);
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Returns the current drawing alpha.
     *
     * @return the current drawing alpha.
     */
    @Override
    public float getAlpha() {
        return this.alpha;  //To change body of implemented methods use File | Settings | File Templates.
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Cap style for line drawings.
     *
     * @param cap cap style (butt, round or square endings).
     */
    @Override
    public void setDrawingCap(CAP cap) {
        this.capStyle = cap;

        if (this.gwtContext != null) {
            switch (cap) {
                case BUTT:
                    this.gwtContext.setLineJoin("butt");
                    break;
                case ROUND:
                    this.gwtContext.setLineJoin("butt");
                    break;
                case SQUARE:
                    this.gwtContext.setLineJoin("square");
                    break;
            }
        }
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Sets the type of join used for line only drawings.
     *
     * @param join the type of line join(round, bevel or miter). Miter size can be set using the setMiterLimit function.
     */
    @Override
    public void setDrawingJoin(JOIN join) {
        this.joinStyle = join;

        if (this.gwtContext != null) {
            switch (join) {

                case ROUND:
                    this.gwtContext.setLineJoin("round");
                    break;
                case BEVEL:
                    this.gwtContext.setLineJoin("bevel");
                    break;
                case MITER:
                    this.gwtContext.setLineJoin("miter");
                    break;
            }
        }

    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Sets the miter limit when the join type is miter.
     *
     * @param limit miter limit.
     */
    @Override
    public void setMiterLimit(float limit) {
        this.miterLimit = limit;

        if (this.gwtContext != null)
            this.gwtContext.setMiterLimit(limit);
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Sets the line width when drawing only with lines.
     *
     * @param lineWidth line width.
     */
    @Override
    public void setLineWidth(float lineWidth) {
        this.lineWidth = lineWidth;

        if (this.gwtContext != null)
            this.gwtContext.setLineWidth(lineWidth);
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Sets the font for when drawing / stroking text.
     *
     * @param font which font to use given
     */
    @Override
    public void setTextFont(Font font) {
        this.font = font;

        this.needsFontUpdate = true;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Sets which text align to use when drawing strings.
     *
     * @param align
     */
    @Override
    public void setTextAlign(TEXT_ALIGN align) {
        this.textAlign = align;

        switch (align) {

            case START:
                this.gwtContext.setTextAlign("start");
                this.textOffsetX = 0.0f;
                break;
            case END:
                this.gwtContext.setTextAlign("end");
                this.textOffsetX = -1.0f;
                break;
            case LEFT:
                this.gwtContext.setTextAlign("left");
                this.textOffsetX = 0.0f;
                break;
            case RIGHT:
                this.gwtContext.setTextAlign("right");
                this.textOffsetX = -1.0f;
                break;
            case CENTER:
                this.gwtContext.setTextAlign("center");
                this.textOffsetX = -0.5f;
                break;
        }

    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Sets which baseline to use when drawing strings. See {@link Render2DProvider.TEXT_BASELINE} for addition information
     * about the different types of baselines.
     *
     * @param baseline which baseline to use when drawing strings.
     */
    @Override
    public void setTextBaseline(TEXT_BASELINE baseline) {
        this.textBaseline = baseline;

        switch (baseline) {

            case TOP:
                this.gwtContext.setTextBaseline("top");
                this.textOffsetY = 1.0f;
                break;
            case HANGING:
                this.gwtContext.setTextBaseline("hanging");
                this.textOffsetY = 1.0f;
                break;
            case MIDDLE:
                this.gwtContext.setTextBaseline("middle");
                this.textOffsetY = 0.5f;
                break;
            case ALPHABETIC:
                this.gwtContext.setTextBaseline("alphabetic");
                this.textOffsetY = 0.0f;
                break;
            case IDEOGRAPHIC:
                this.gwtContext.setTextBaseline("ideographic");
                this.textOffsetY = -0.5f;
                break;
            case BOTTOM:
                this.gwtContext.setTextBaseline("bottom");
                this.textOffsetY = -0.5f;
                break;
        }
    }


    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    protected void updateFont() {
        if (this.gwtContext == null)
            return;

        String fonts = "";

        /*font style*/
        switch (this.font.getStyle()) {

            case NORMAL:
                /*We add nothing to the string*/
                break;
            case ITALIC:
                fonts += "italic ";
                break;
            case OBLIQUE:
                fonts += "oblique ";
                break;
        }

        /*font weight*/
        switch (this.font.getWeight()) {

            case NORMAL:
                /*nothing*/
                break;
            case BOLD:
                fonts += "bold ";
                break;
        }

        /*font size*/
        fonts += font.getSize() + "em/" + font.getHeight() + " ";

        /*font family*/
        fonts += font.getFamily();

        this.gwtContext.setFont(fonts);
        this.needsFontUpdate = false;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Creates a gradient at the specified location.
     *
     * @param gradient which gradient to create
     * @param x        x coordinate for the left top corner of the gradient
     * @param y        y coordinate for the left top corner of the gradient
     * @param width    width of the gradient
     * @param height   height of the gradient
     * @param type     type of gradient (1 linear, non 1 radial)
     */
    private native void createNativeGradient(Gradient gradient, int x, int y, int width, int height, int type) /*-{
        var context = this.@edu.catalindumitru.gwt.graphics.GwtRender2DProvider::gwtContext;

        //native gradient object
        var nativeGradient = null;

        //caching of variabiles
        var offsetX = gradient.@edu.catalindumitru.bee.graphics.Gradient::getOffsetX()();
        var offsetY = gradient.@edu.catalindumitru.bee.graphics.Gradient::getOffsetY()();
        var angle = gradient.@edu.catalindumitru.bee.graphics.Gradient::getAngle()();
        var scale = gradient.@edu.catalindumitru.bee.graphics.Gradient::getScale()();

        if (gradient.@edu.catalindumitru.bee.graphics.Gradient::getType()().@edu.catalindumitru.bee.graphics.Gradient.TYPE::toString()()
                == "LINEAR") {

            //offset for x and y considering angle
            var x1 = width * Math.sin(angle);
            var y1 = height * Math.cos(angle);

            //depending which one is closer to the edge of the rectangle, use overextend that one to make sure
            //that the gradient rectangle is fit snuggly inside the actual bounds
            if (Math.abs(x1 / width) > Math.abs(y1 / height)) {
                y1 = y1 * (width / x1) * scale;
                x1 = width * scale;
            } else {
                x1 = x1 * (height / y1) * scale;
                y1 = height * scale;
            }

            nativeGradient = context.createLinearGradient(
                    x + offsetX,
                    y + offsetY,
                    x + offsetX + x1,
                    y + offsetY + y1);
        } else {
            nativeGradient = context.createRadialGradient(
                    x + width / 2 + offsetX,
                    y + height / 2 + offsetY,
                    0,
                    x + width / 2 + offsetX,
                    y + height / 2 + offsetY,
                    Math.max(width, height) * scale);
        }

        //color set
        var set = gradient.@edu.catalindumitru.bee.graphics.Gradient::getColorMap()();
        //iterator to iterate through all the colors and add them to the native gradient
        var iter = set.@java.util.Set::iterator()();

        while (iter.@java.util.Iterator::hasNext()()) {
            var entry = iter.@java.util.Iterator::next()();

            nativeGradient.addColorStop(entry.@java.util.Map.Entry::getKey()(), "rgba("
                    + parseInt(255 * entry.@java.util.Map.Entry::getValue()().@edu.catalindumitru.bee.graphics.Color::getRed()()) + ", "
                    + parseInt(255 * entry.@java.util.Map.Entry::getValue()().@edu.catalindumitru.bee.graphics.Color::getGreen()()) + ", "
                    + parseInt(255 * entry.@java.util.Map.Entry::getValue()().@edu.catalindumitru.bee.graphics.Color::getBlue()()) + ", "
                    + entry.@java.util.Map.Entry::getValue()().@edu.catalindumitru.bee.graphics.Color::getAlpha()() + ")");
        }

        if (type == 1)
            context.fillStyle = nativeGradient;
        else
            context.strokeStyle = nativeGradient;
    }-*/;


    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Doing it this way because gwt does some funky stuff with the string which doesn't allow me to use rgba.
     *
     * @param style
     */
    private native void setFillStyle(String style) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender2DProvider::gwtContext.fillStyle = style;
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Doing it this way because gwt does some funky stuff with the string which doesn't allow me to use rgba.
     *
     * @param style
     */
    private native void setStrokeStyle(String style) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender2DProvider::gwtContext.strokeStyle = style;
    }-*/;

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    protected void updateStyle() {
        if (this.gwtContext == null)
            return;

        /*---------------------Fill style ------------------------------------*/
        switch (this.fillStyle) {
            /*Gradients need to be updated for every shape that uses them because of a stupid way they are implemented*/
            case COLOR:
                this.setFillStyle("rgba(" + (int) (this.fillColor.getRed() * 255) + ", " +
                        (int) (this.fillColor.getGreen()) * 255 + "," + (int) (this.fillColor.getBlue() * 255) +
                        ", " + this.fillColor.getAlpha() + ")");

                this.dynamicFillGradient = false;
                break;
            case GRADIENT:
                /*gradients are created when a shape is drawn*/
                this.dynamicFillGradient = true;
                break;
        }

        /*-------------------------Stroke Style----------------------------------*/
        switch (this.strokeStyle) {

            case COLOR:
                this.setStrokeStyle("rgba(" + (int) (this.strokeColor.getRed()) * 255 + ", " +
                        (int) (this.strokeColor.getGreen() * 255) + "," + (int) (this.strokeColor.getBlue() * 255)
                        + ", " + this.strokeColor.getAlpha() + ")");
                this.dynamicStrokeGradient = false;
                break;
            case GRADIENT:
                this.dynamicStrokeGradient = true;
                break;
        }

        this.needsStyleUpdate = false;

    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Begins the drawing cycle. Useful for double buffering and predrawing techniques.
     */
    @Override
    public void beginDrawing() {

        /*check if we need to update style or font params, and act accordingly*/
        if (this.needsFontUpdate)
            this.updateFont();
        if (this.needsStyleUpdate)
            this.updateStyle();

        this.gwtContext.save();
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Ends the current drawing cycle. Useful for flushing the double buffer to the screen.
     */
    @Override
    public void endDrawing() {
        /*TODO if there isn't anything more complicated to be added to this function convert to a JSNI function*/
        this.gwtContext.restore();
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Clear the entire screen using the pre-set color.
     */
    @Override
    public native void clearAll() /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender2DProvider::gwtContext.clearRect(
                0, 0, this.@edu.catalindumitru.gwt.graphics.GwtRender2DProvider::screenWidth,
                this.@edu.catalindumitru.gwt.graphics.GwtRender2DProvider::screenHeight);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Clear only a portion of the screen using the pre-set color.
     *
     * @param x      x coordinate for the top left corner of the rectangle.
     * @param y      y coordinate for the top left corner of the rectangle.
     * @param width  width of the rectangle.
     * @param height height of the rectangle.
     */
    @Override
    public native void clearRect(int x, int y, float width, float height) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender2DProvider::gwtContext.clearRect(x, y, width, height);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Sets clipping area for future drawing. Any drawings outside this rectangle will not be rendered.
     *
     * @param x      x coordinate for the top left corner of the rectangle.
     * @param y      y coordinate for the top left corner of the rectangle.
     * @param width  width of the rectangle.
     * @param height height of the rectangle.
     */
    @Override
    public native void setClip(int x, int y, float width, float height) /*-{
        var context = this.@edu.catalindumitru.gwt.graphics.GwtRender2DProvider::gwtContext;

        context.beginPath();
        context.rect(x, y, width, height);
        context.clip();
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Removes clipping area. Has the same affect as calling
     * {@link edu.catalindumitru.bee.graphics.Render2DProvider#setClip(int, int, float, float)}
     * with a rectangle described by coordinates (0, 0) and dimensions (
     * {@link edu.catalindumitru.bee.graphics.Render2DProvider#getScreenWidth(),
     * ({@link edu.catalindumitru.bee.graphics.Render2DProvider#getScreenWidth()}}
     */
    @Override
    public native void removeClip() /*-{
        var context = this.@edu.catalindumitru.gwt.graphics.GwtRender2DProvider::gwtContext;

        context.beginPath();
        context.rect(0, 0, this.@edu.catalindumitru.gwt.graphics.GwtRender2DProvider::screenWidth,
                this.@edu.catalindumitru.gwt.graphics.GwtRender2DProvider::screenHeight);
        context.clip();
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    /**
     * Translates the origin of future drawing on the screen.
     *
     * @param x amount on the x axis to translate.
     * @param y amount on the y axis to translate.
     */
    @Override
    public native void translate(float x, float y) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender2DProvider::gwtContext.translate(x, y);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Rotates the drawing api which will affect all future drawings.
     *
     * @param angle the amount to rotate in radians.
     */
    @Override
    public native void rotate(float angle) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender2DProvider::gwtContext.rotate(angle);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Scales the drawing api which will affect all future drawings.
     *
     * @param x amount on the x axis to scale.
     * @param y amount on the y axis to scale.
     */
    @Override
    public native void scale(float x, float y) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender2DProvider::gwtContext.scale(x, y);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    /**
     * Adds the given values to the transform of the drawing api, which will affect all future drawings.
     * Values (a11, a21, a12, a22) affect scale and rotation and values (x, y) affect translation.
     *
     * @param a11 value from position (1,1) of the transform matrix.
     * @param a21 value from position (2,1) of the transform matrix.
     * @param a12 value from position (2,1) of the transform matrix.
     * @param a22 value from position (2,2) of the transform matrix.
     * @param x   value from position (3,1) of the transform matrix.
     * @param y   value from position (3,2) of the transform matrix.
     */
    @Override
    public native void transform(float a11, float a12, float a21, float a22, float x, float y) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender2DProvider::gwtContext.transform(a11, a12, a21, a22, x, y);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    /**
     * Sets the transform of the drawing api to the given values, which will affect all future drawings.
     * Values (a11, a21, a12, a22) affect scale and rotation and values (x, y) affect translation.
     *
     * @param a11 value from position (1,1) of the transform matrix.
     * @param a21 value from position (2,1) of the transform matrix.
     * @param a12 value from position (2,1) of the transform matrix.
     * @param a22 value from position (2,2) of the transform matrix.
     * @param x   value from position (3,1) of the transform matrix.
     * @param y   value from position (3,2) of the transform matrix.
     */
    @Override
    public native void setTransform(float a11, float a12, float a21, float a22, float x, float y) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender2DProvider::gwtContext.setTransform(a11, a12, a21, a22, x, y);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Fills a rectangle using the current drawing color.
     *
     * @param x      x coordinate for the left bottom vertex of the rectangle.
     * @param y      x coordinate for the left bottom vertex of the rectangle.
     * @param width  width of the rectangle
     * @param height height of the rectangle
     */
    @Override
    public native void fillRectangle(int x, int y, int width, int height) /*-{
        //if the current drawing style is a gradient then we need to create a gradient specific for this rectangles
        //dimensions
        if (this.@edu.catalindumitru.gwt.graphics.GwtRender2DProvider::dynamicFillGradient) {
            this.@edu.catalindumitru.gwt.graphics.GwtRender2DProvider::createNativeGradient(Ledu/catalindumitru/bee/graphics/Gradient;IIIII)(
                    this.@edu.catalindumitru.gwt.graphics.GwtRender2DProvider::fillGradient,
                    x, y, width, height, 1);
        }

        this.@edu.catalindumitru.gwt.graphics.GwtRender2DProvider::gwtContext.fillRect(x, y, width, height);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Fills a rectangle using the current drawing color. Ignores whether or not the shape has the "filled" and "stroked"
     * params sets to true or false.
     */
    @Override
    public void fillRectangle(RectangleShape rectangle) {
        this.fillRectangle((int) rectangle.getX(), (int) rectangle.getY(), (int) rectangle.getWidth(), (int) rectangle.getHeight());
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Strokes a rectangle using the current drawing color.
     *
     * @param x      x coordinate for the left bottom vertex of the rectangle.
     * @param y      x coordinate for the left bottom vertex of the rectangle.
     * @param width  width of the rectangle
     * @param height height of the rectangle
     */
    @Override
    public native void strokeRectangle(int x, int y, int width, int height) /*-{
        //if the current drawing style is a gradient then we need to create a gradient specific for this rectangles
        //dimensions
        if (this.@edu.catalindumitru.gwt.graphics.GwtRender2DProvider::dynamicStrokeGradient) {
            this.@edu.catalindumitru.gwt.graphics.GwtRender2DProvider::createNativeGradient(Ledu/catalindumitru/bee/graphics/Gradient;IIIII)(
                    this.@edu.catalindumitru.gwt.graphics.GwtRender2DProvider::strokeGradient,
                    x, y, width, height, 0);
        }

        this.@edu.catalindumitru.gwt.graphics.GwtRender2DProvider::gwtContext.strokeRect(x, y, width, height);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Strokes a rectangle using the current drawing color. Ignores whether or not the shape has the "filled" and "stroked"
     * params sets to true or false.
     */
    @Override
    public void strokeRectangle(RectangleShape rectangle) {
        this.strokeRectangle((int) rectangle.getX(), (int) rectangle.getY(), (int) rectangle.getWidth(), (int) rectangle.getHeight());
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Fills an arc using the current drawing color.
     *
     * @param x          x coordinate of where the center of the arc should be.
     * @param y          y coordinate of where the center of the arc should be.
     * @param radius     radius of the arc.
     * @param startAngle staring angle of the arc (measured in radian).
     * @param endAngle   ending angle of the arc (measured in radian).
     */
    @Override
    public native void fillArc(float x, float y, float radius, float startAngle, float endAngle) /*-{
        //if the current drawing style is a gradient then we need to create a gradient specific for this rectangles
        //dimensions
        if (this.@edu.catalindumitru.gwt.graphics.GwtRender2DProvider::dynamicFillGradient) {
            this.@edu.catalindumitru.gwt.graphics.GwtRender2DProvider::createNativeGradient(Ledu/catalindumitru/bee/graphics/Gradient;IIIII)(
                    this.@edu.catalindumitru.gwt.graphics.GwtRender2DProvider::fillGradient,
                    x - (radius / 2), y - (radius / 2), radius, radius, 1);
        }

        //we draw the arc using the draw api
        this.@edu.catalindumitru.gwt.graphics.GwtRender2DProvider::gwtContext.beginPath();

        this.@edu.catalindumitru.gwt.graphics.GwtRender2DProvider::gwtContext.arc(x, y, radius, startAngle, endAngle)
        this.@edu.catalindumitru.gwt.graphics.GwtRender2DProvider::gwtContext.fill();

        this.@edu.catalindumitru.gwt.graphics.GwtRender2DProvider::gwtContext.closePath();

    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Strokes an arc using the current drawing color.
     *
     * @param x          x coordinate of where the center of the arc should be.
     * @param y          y coordinate of where the center of the arc should be.
     * @param radius     radius of the arc.
     * @param startAngle staring angle of the arc (measured in radian).
     * @param endAngle   ending angle of the arc (measured in radian).
     */
    @Override
    public native void strokeArc(float x, float y, float radius, float startAngle, float endAngle)  /*-{

        //if the current drawing style is a gradient then we need to create a gradient specific for this rectangles
        //dimensions
        if (this.@edu.catalindumitru.gwt.graphics.GwtRender2DProvider::dynamicStrokeGradient) {
            this.@edu.catalindumitru.gwt.graphics.GwtRender2DProvider::createNativeGradient(Ledu/catalindumitru/bee/graphics/Gradient;IIIII)(
                    this.@edu.catalindumitru.gwt.graphics.GwtRender2DProvider::strokeGradient,
                    x - (radius / 2), y - (radius / 2), radius, radius, 0);
        }

        //we draw the arc using the draw api
        this.@edu.catalindumitru.gwt.graphics.GwtRender2DProvider::gwtContext.beginPath();

        this.@edu.catalindumitru.gwt.graphics.GwtRender2DProvider::gwtContext.arc(x, y, radius, startAngle, endAngle);
        this.@edu.catalindumitru.gwt.graphics.GwtRender2DProvider::gwtContext.stroke();

        this.@edu.catalindumitru.gwt.graphics.GwtRender2DProvider::gwtContext.closePath();

    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Creates a filled shape using the current drawing color.Ignores whether or not the shape has the "filled" and "stroked"
     * params sets to true or false.
     *
     * @param shape which shape to draw.
     */
    @Override
    public void fillShape(Shape shape) {
        if (this.dynamicFillGradient)
            this.createNativeGradient(this.fillGradient, (int) shape.getBounds().getX(), (int) shape.getBounds().getY(),
                    (int) shape.getBounds().getWidth(), (int) shape.getBounds().getHeight(), 1);

        this.gwtContext.beginPath();

        for (Point2D point : shape.getPointList()) {
            this.gwtContext.lineTo(point.getX(), point.getY());
        }

        this.gwtContext.fill();
        this.gwtContext.closePath();
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Creates a stroked shape using the current drawing color.Ignores whether or not the shape has the "filled" and "stroked"
     * params sets to true or false.
     *
     * @param shape which shape to draw.
     */
    @Override
    public void strokeShape(Shape shape) {
        if (this.dynamicFillGradient)
            this.createNativeGradient(this.strokeGradient, (int) shape.getBounds().getX(), (int) shape.getBounds().getY(),
                    (int) shape.getBounds().getWidth(), (int) shape.getBounds().getHeight(), 0);

        this.gwtContext.beginPath();

        for (Point2D point : shape.getPointList()) {
            this.gwtContext.lineTo(point.getX(), point.getY());
        }

        this.gwtContext.stroke();
        this.gwtContext.closePath();
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Draws the given shape using the current fill and stroke color. This uses the shapes own "filled" and "stroked"
     * params to determine whether or not it should be filled and / or stroked.
     *
     * @param shape which shape to draw.
     */
    @Override
    public void drawShape(Shape shape) {
        if (shape.isFilled()) {
            this.createNativeGradient(this.fillGradient, (int) shape.getBounds().getX(), (int) shape.getBounds().getY(),
                    (int) shape.getBounds().getWidth(), (int) shape.getBounds().getHeight(), 1);
        }
        if (shape.isStroked()) {
            this.createNativeGradient(this.strokeGradient, (int) shape.getBounds().getX(), (int) shape.getBounds().getY(),
                    (int) shape.getBounds().getWidth(), (int) shape.getBounds().getHeight(), 0);
        }

        this.gwtContext.beginPath();

        for (Point2D point : shape.getPointList()) {
            this.gwtContext.lineTo(point.getX(), point.getY());
        }

        if (shape.isFilled())
            this.gwtContext.fill();
        if (shape.isStroked())
            this.gwtContext.stroke();

        this.gwtContext.closePath();
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Draws the given text at the given location using preset font, text align, and text baseline.
     *
     * @param x    x coordinate for text placement.
     * @param y    y coordinate for text placement.
     * @param text which text to draw.
     */
    @Override
    public native void fillString(float x, float y, String text) /*-{
        //if the current drawing style is a gradient then we need to create a gradient specific for this rectangles
        //dimensions

        //calculate text dimensions based on text align/baseline and font
        var height = this.@edu.catalindumitru.gwt.graphics.GwtRender2DProvider::font.@edu.catalindumitru.bee.graphics.Font::getSize()()
                * 16.0;
        var width = this.@edu.catalindumitru.gwt.graphics.GwtRender2DProvider::gwtContext.measureText(text).width;

        if (this.@edu.catalindumitru.gwt.graphics.GwtRender2DProvider::dynamicFillGradient) {
            this.@edu.catalindumitru.gwt.graphics.GwtRender2DProvider::createNativeGradient(Ledu/catalindumitru/bee/graphics/Gradient;IIIII)(
                    this.@edu.catalindumitru.gwt.graphics.GwtRender2DProvider::fillGradient,
                    x + (width * this.@edu.catalindumitru.gwt.graphics.GwtRender2DProvider::textOffsetX),
                    y - height + (height * this.@edu.catalindumitru.gwt.graphics.GwtRender2DProvider::textOffsetY),
                    width,
                    height,
                    1);
        }


        this.@edu.catalindumitru.gwt.graphics.GwtRender2DProvider::gwtContext.fillText(text, x, y);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Strokes the given text at the given location using preset font, text align, and text baseline.
     *
     * @param x    x coordinate for text placement.
     * @param y    y coordinate for text placement.
     * @param text which text to stroke.
     */
    @Override
    public native void strokeString(float x, float y, String text) /*-{
        //if the current drawing style is a gradient then we need to create a gradient specific for this rectangles
        //dimensions
        if (this.@edu.catalindumitru.gwt.graphics.GwtRender2DProvider::dynamicStrokeGradient) {
            this.@edu.catalindumitru.gwt.graphics.GwtRender2DProvider::createNativeGradient(Ledu/catalindumitru/bee/graphics/Gradient;IIIII)(
                    this.@edu.catalindumitru.gwt.graphics.GwtRender2DProvider::strokeGradient,
                    x - (radius / 2), y - (radius / 2), radius, radius, 0);
        }


        this.@edu.catalindumitru.gwt.graphics.GwtRender2DProvider::gwtContext.strokeText(text, x, y);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Draw the specified imageResource to the canvas. The imageResource will retain it's dimensions when drawn.
     *
     * @param imageResource        which imageResource to draw to the canvas.
     * @param destinationX the x coordinate of the top left point on the canvas where the imageResource should be drawn.
     * @param destinationY the y coordinate of the top left point on the canvas where the imageResource should be drawn.
     */
    @Override
    public native void drawImage(ImageResource imageResource, float destinationX, float destinationY) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender2DProvider::gwtContext.drawImage(imageResource,
            destinationX, destinationY);
    }-*/;
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Draw the specified imageResource to the canvas.  The source imageResource will be drawn at coordinates destinationX,
     * destinationY with the dimensions destinationWidth and destinationHeight. If the height or width differ from
     * the source imageResource, then the imageResource will be scaled accordingly.
     *
     * @param imageResource             which imageResource to draw to the canvas.
     * @param destinationX      the x coordinate of the top left point on the canvas where the imageResource should be drawn.
     * @param destinationY      the y coordinate of the top left point on the canvas where the imageResource should be drawn.
     * @param destinationWidth  the destination width of the final imageResource to be drawn.
     * @param destinationHeight the destination height of the final imageResource to be drawn.
     */
    @Override
    public native void drawImage(ImageResource imageResource, float destinationX, float destinationY, float destinationWidth,
                                 float destinationHeight) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender2DProvider::gwtContext.drawImage(imageResource,
            destinationX, destinationY, destinationWidth, destinationHeight);
    }-*/;

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Draw the specified imageResource to the canvas. The imageResource will be cropped using a rectangle described the the parameters:
     * sourceX, sourceY, sourceWidth and sourceHeight. The cropped imageResource will be drawn at coordinates destinationX,
     * destinationY with the dimensions destinationWidth and destinationHeight. If the height or width differ from
     * the source imageResource, then the imageResource will be scaled accordingly.
     *
     * @param imageResource             which imageResource to draw to the canvas.
     * @param destinationX      the x coordinate of the top left point on the canvas where the imageResource should be drawn.
     * @param destinationY      the y coordinate of the top left point on the canvas where the imageResource should be drawn.
     * @param destinationWidth  the destination width of the final imageResource to be drawn.
     * @param destinationHeight the destination height of the final imageResource to be drawn.
     * @param sourceX           the x coordinate of the top left corner of the rectangle which will crop the imageResource.
     * @param sourceY           the y coordinate of the top left corner of the rectangle which will crop the imageResource.
     * @param sourceWidth       the width of the rectangle which will crop the source imageResource.
     * @param sourceHeight      the height of the rectangle which will crop the source imageResource.
     */
    @Override
    public native void drawImage(ImageResource imageResource, float destinationX, float destinationY, float destinationWidth,
                                 float destinationHeight, float sourceX, float sourceY, float sourceWidth, float sourceHeight) /*-{
        this.@edu.catalindumitru.gwt.graphics.GwtRender2DProvider::gwtContext.drawImage(imageResource,
                sourceX, sourceY, sourceWidth, sourceHeight,
                destinationX, destinationY, destinationWidth, destinationHeight);
    }-*/;
}
