package edu.catalindumitru.bee.graphics;

import edu.catalindumitru.bee.content.ImageResource;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 7/21/11
 * Time: 4:31 PM
 */
public interface Render2DProvider {
    /*---------------------------------------Static params-------------------------------------------------------------*/
    public enum STYLE {
        COLOR, GRADIENT
    }

    public enum CAP {BUTT, ROUND, SQUARE}

    public enum JOIN {ROUND, BEVEL, MITER}

    public enum TEXT_ALIGN {START, END, LEFT, RIGHT, CENTER}

    public enum TEXT_BASELINE {TOP, HANGING, MIDDLE, ALPHABETIC, IDEOGRAPHIC, BOTTOM}


    /*---------------------------------------Screen details-------------------------------------------------------------*/

    /**
     * Returns the current screen width.
     *
     * @return screen width.
     */
    public int getScreenWidth();

    /**
     * Returns the current screen height.
     *
     * @return screen height.
     */
    public int getScreenHeight();

    /**
     * Converts the formatted length string into the corresponding dimension in pixels. The formatted string includes
     * a number and a suffix, which can be one of the fallowing : px for pixels, % for percent fallowed by w or h (w
     * for a percentage based of the width of the screen and h for a percentage based on the height on the screen).
     *
     * @param dimension a formatted string representing the dimension to be converted.
     * @return the dimension converted to pixels. If the formatted string contains errors , then the function
     *         will return -1.
     */
    public int convertDimension(String dimension);

    /**
     * Converts the given color formatted as a string, into a {@link Color} object. The formatted string can be one of
     * two types:
     * <ul>
     *     <li>
     *         A color comprised of a # symbol fallowed by four pairs of hexadecimal numbers, which represent,
     *         in order : red, green, blue, and alpha. For example #A000007F results in a dark semitransparent red.
     *     </li>
     *     <li>
     *         A color comprised of a function call to rgba(...) which takes 4 params, all numbers between 0 and 255,
     *         representing , in order : red, green , blue and alpha. For example rgba(160, 0, 0, 127) yelds the
     *         same color as before.
     *     </li>
     *     <li>
     *         A color comprised of a function call to rgb(...) which takes 3 params, all numbers between 0 and 255,
     *         representing , in order : red, green and blue. For example rgb(160, 0, 0) yields the same color as before,
     *         except it is now fully opaque. The value of alpha is considered to be fully opaque.
     *     </li>
     * </ul>
     * @param color the color formatted as a string.
     * @return the corresponding {@link Color} object.
     */
    public Color convertColor(String color);

    /**
     * Returns the given string's width when drawn, using the current text metrics.
     * @param text which text to calculate it's dimension.
     * @return the width of the string as it will be rendered on the screen.
     */
    public int getStringWidth(String text);

    /*---------------------------------------Drawing style-------------------------------------------------------------*/


    /**
     * Sets the active fille color.
     *
     * @param color the color to use for future drawings.
     */
    public void setFillColor(Color color);

    /**
     * Sets the active fill gradient.
     */
    public void setFillGradient(Gradient gradient);

    /**
     * Sets the active stroke color.
     *
     * @param color the color to use for future drawings.
     */
    public void setStrokeColor(Color color);

    /**
     * Sets the active stroke gradient.
     */
    public void setStrokeGradient(Gradient gradient);


    /**
     * Sets the active drawing color for filled shapes.
     *
     * @param style drawing style (either color mode or gradient).
     */
    public void setFillStyle(STYLE style);

    /**
     * Sets the active drawing color for stroked shapes.
     *
     * @param style drawing style (either color mode or gradient).
     */
    public void setStrokeStyle(STYLE style);

    /**
     * Cap style for line drawings.
     *
     * @param cap cap style (butt, round or square endings).
     */
    public void setDrawingCap(CAP cap);

    /**
     * Sets the type of join used for line only drawings.
     *
     * @param join the type of line join(round, bevel or miter). Miter size can be set using the setMiterLimit function.
     */
    public void setDrawingJoin(JOIN join);

    /**
     * Sets the miter limit when the join type is miter.
     *
     * @param limit miter limit.
     */
    public void setMiterLimit(float limit);

    /**
     * Sets the line width when drawing only with lines.
     *
     * @param lineWidth line width.
     */
    public void setLineWidth(float lineWidth);

    /**
     * Sets the font for when drawing / stroking text.
     *
     * @param font which font to use given
     */
    public void setTextFont(Font font);

    /**
     * Sets which text align to use when drawing strings.
     *
     * @param align
     */
    public void setTextAlign(TEXT_ALIGN align);

    /**
     * Sets which baseline to use when drawing strings.
     *
     * @param baseline
     */
    public void setTextBaseline(TEXT_BASELINE baseline);

    /**
     * Sets the current drawing alpha. All subsequent drawing operations will be affected by this alpha value.
     *
     * @param alpha a value between 0 and 1 representing drawing alpha.
     */
    public void setAlpha(float alpha);

    /**
     * Returns the current drawing alpha.
     *
     * @return the current drawing alpha.
     */
    public float getAlpha();

    /*---------------------------------------General drawing-----------------------------------------------------------*/

    /**
     * Begins the drawing cycle. Useful for double buffering and predrawing techniques.
     */
    public void beginDrawing();

    /**
     * Ends the current drawing cycle. Useful for flushing the double buffer to the screen.
     */
    public void endDrawing();

    /**
     * This method saves all the current params for the drawing api (like color, clipping area etc). This method
     * should be called before setting custom drawing params.
     */
    public void pushState();

    /**
     * This method restores current drawing params (like color, fill styles etc.). This method should be called
     * after setting cutom params for drawing.
     */
    public void popState();

    /**
     * Clear the entire screen using a complete transparent black.
     */
    public void clearAll();

    /**
     * Clear only a portion of the screen using a complete transparent black.
     *
     * @param x      x coordinate for the top left corner of the rectangle.
     * @param y      y coordinate for the top left corner of the rectangle.
     * @param width  width of the rectangle.
     * @param height height of the rectangle.
     */
    public void clearRect(int x, int y, float width, float height);

    /**
     * Sets clipping area for future drawing. Any drawings outside this rectangle will not be rendered.
     *
     * @param x      x coordinate for the top left corner of the rectangle.
     * @param y      y coordinate for the top left corner of the rectangle.
     * @param width  width of the rectangle.
     * @param height height of the rectangle.
     */
    public void setRectangleClip(int x, int y, float width, float height);

    /**
     * Sets the clipping area for future drawings in the shape of a rounded rectangle.
     * @param x      x coordinate for the top left corner of the rectangle.
     * @param y      y coordinate for the top left corner of the rectangle.
     * @param width  width of the rectangle.
     * @param height height of the rectangle.
     * @param radius the radius of the rounded rectangle.
     */
    public void setRoundedRectangleClip(int x, int y, int width, int height, int radius);

    /**
     * Sets the clipping area for future drawings in the shape of a circle.
     * @param x the x coordinate for the center of the circle.
     * @param y the y coordinate for the center of the circle.
     * @param radius the radius of the circle.
     */
    public void setCircleClip(int x, int y, int radius);

    /**
     * Removes clipping area. Has the same affect as calling {@link Render2DProvider#setRectangleClip(int, int, float, float)}
     * with a rectangle described by coordinates (0, 0) and dimensions (
     * {@link edu.catalindumitru.bee.graphics.Render2DProvider#getScreenWidth(),
     * ({@link edu.catalindumitru.bee.graphics.Render2DProvider#getScreenWidth()}}
     */
    public void removeClip();

    /*-------------------------------------Drawing Transform------------------------------------------------------------*/

    /**
     * Translates the origin of future drawing on the screen.
     *
     * @param x amount on the x axis to translate.
     * @param y amount on the y axis to translate.
     */
    public void translate(float x, float y);

    /**
     * Rotates the drawing api which will affect all future drawings.
     *
     * @param angle the amount to rotate in radians.
     */
    public void rotate(float angle);

    /**
     * Scales the drawing api which will affect all future drawings.
     *
     * @param x amount on the x axis to scale.
     * @param y amount on the y axis to scale.
     */
    public void scale(float x, float y);

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
    public void transform(float a11, float a12, float a21, float a22, float x, float y);

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
    public void setTransform(float a11, float a12, float a21, float a22, float x, float y);
    /*---------------------------------------Shape drawing ------------------------------------------------------------*/

    /**
     * Fills a rectangle using the current drawing color.
     *
     * @param x      x coordinate for the left bottom vertex of the rectangle.
     * @param y      x coordinate for the left bottom vertex of the rectangle.
     * @param width  width of the rectangle
     * @param height height of the rectangle
     */
    public void fillRectangle(int x, int y, int width, int height);

    /**
     * Fills a rectangle using the current drawing color. Ignores whether or not the shape has the "filled" and "stroked"
     * params sets to true or false.
     */
    public void fillRectangle(RectangleShape rectangle);

    /**
     * Fills the rectangle where the corners are rounded to the given radius.
     * @param x the x coordinate of where the rectangle should begin.
     * @param y the y coordinate of where the rectangle should begin.
     * @param width the width of the rectangle
     * @param height the height of the rectangle.
     * @param radius the radius of the rectangle.
     */
    public void fillRoundRectangle(int x, int y, int width, int height, int radius);

    /**
     * Fills the rectangle where the corners are rounded to the given radius.
     * @param rectangle the rectangle to fill.
     * @param radius the radius of the corners.
     */
    public void fillRoundRectangle(RectangleShape rectangle, int radius);


    /**
     * Strokes a rectangle using the current drawing color.
     *
     * @param x      x coordinate for the left bottom vertex of the rectangle.
     * @param y      x coordinate for the left bottom vertex of the rectangle.
     * @param width  width of the rectangle
     * @param height height of the rectangle
     */
    public void strokeRectangle(int x, int y, int width, int height);

    /**
     * Strokes a rectangle using the current drawing color. Ignores whether or not the shape has the "filled" and "stroked"
     * params sets to true or false.
     */
    public void strokeRectangle(RectangleShape rectangle);

    /**
     * Strokes the rectangle where the corners are rounded to the given radius.
     * @param x the x coordinate of where the rectangle should begin.
     * @param y the y coordinate of where the rectangle should begin.
     * @param width the width of the rectangle
     * @param height the height of the rectangle.
     * @param radius the radius of the rectangle.
     */
    public void strokeRoundRectangle(int x, int y, int width, int height, int radius);

    /**
     * Strokes the rectangle where the corners are rounded to the given radius.
     * @param rectangle the rectangle to stroke.
     * @param radius the radius of the corners.
     */
    public void strokeRoundRectangle(RectangleShape rectangle, int radius);

    /**
     * Fills an arc using the current drawing color.
     *
     * @param x          x coordinate of where the center of the arc should be.
     * @param y          y coordinate of where the center of the arc should be.
     * @param radius     radius of the arc.
     * @param startAngle staring angle of the arc (measured in radian).
     * @param endAngle   ending angle of the arc (measured in radian).
     */
    public void fillArc(float x, float y, float radius, float startAngle, float endAngle);

    /**
     * Strokes an arc using the current drawing color.
     *
     * @param x          x coordinate of where the center of the arc should be.
     * @param y          y coordinate of where the center of the arc should be.
     * @param radius     radius of the arc.
     * @param startAngle staring angle of the arc (measured in radian).
     * @param endAngle   ending angle of the arc (measured in radian).
     */
    public void strokeArc(float x, float y, float radius, float startAngle, float endAngle);

    /**
     * Creates a filled shape using the current drawing color.Ignores whether or not the shape has the "filled" and "stroked"
     * params sets to true or false.
     *
     * @param shape which shape to draw.
     */
    public void fillShape(Shape shape);

    /**
     * Creates a stroked shape using the current drawing color.Ignores whether or not the shape has the "filled" and "stroked"
     * params sets to true or false.
     *
     * @param shape which shape to draw.
     */
    public void strokeShape(Shape shape);

    /**
     * Draws the given shape using the current fill and stroke color. This uses the shapes own "filled" and "stroked"
     * params to determine whether or not it should be filled and / or stroked.
     *
     * @param shape which shape to draw.
     */
    public void drawShape(Shape shape);

    /*-------------------------------------------Text Drawing ---------------------------------------------------------*/

    /**
     * Draws the given text at the given location using preset font, text align, and text baseline.
     *
     * @param x    x coordinate for text placement.
     * @param y    y coordinate for text placement.
     * @param text which text to draw.
     */
    public void fillString(float x, float y, String text);

    /**
     * Strokes the given text at the given location using preset font, text align, and text baseline.
     *
     * @param x    x coordinate for text placement.
     * @param y    y coordinate for text placement.
     * @param text which text to stroke.
     */
    public void strokeString(float x, float y, String text);

    /*---------------------------------------------ImageResource Drawing-------------------------------------------------------*/

    /**
     * Draw the specified imageResource to the canvas. The imageResource will retain it's dimensions when drawn.
     *
     * @param imageResource which imageResource to draw to the canvas.
     * @param destinationX  the x coordinate of the top left point on the canvas where the imageResource should be drawn.
     * @param destinationY  the y coordinate of the top left point on the canvas where the imageResource should be drawn.
     */
    public void drawImage(ImageResource imageResource, float destinationX, float destinationY);

    /**
     * Draw the specified imageResource to the canvas.  The source imageResource will be drawn at coordinates destinationX,
     * destinationY with the dimensions destinationWidth and destinationHeight. If the height or width differ from
     * the source imageResource, then the imageResource will be scaled accordingly.
     *
     * @param imageResource     which imageResource to draw to the canvas.
     * @param destinationX      the x coordinate of the top left point on the canvas where the imageResource should be drawn.
     * @param destinationY      the y coordinate of the top left point on the canvas where the imageResource should be drawn.
     * @param destinationWidth  the destination width of the final imageResource to be drawn.
     * @param destinationHeight the destination height of the final imageResource to be drawn.
     */
    public void drawImage(ImageResource imageResource, float destinationX, float destinationY, float destinationWidth, float destinationHeight);

    /**
     * Draw the specified imageResource to the canvas. The imageResource will be cropped using a rectangle described the the parameters:
     * sourceX, sourceY, sourceWidth and sourceHeight. The cropped imageResource will be drawn at coordinates destinationX,
     * destinationY with the dimensions destinationWidth and destinationHeight. If the height or width differ from
     * the source imageResource, then the imageResource will be scaled accordingly.
     *
     * @param imageResource     which imageResource to draw to the canvas.
     * @param destinationX      the x coordinate of the top left point on the canvas where the imageResource should be drawn.
     * @param destinationY      the y coordinate of the top left point on the canvas where the imageResource should be drawn.
     * @param destinationWidth  the destination width of the final imageResource to be drawn.
     * @param destinationHeight the destination height of the final imageResource to be drawn.
     * @param sourceX           the x coordinate of the top left corner of the rectangle which will crop the imageResource.
     * @param sourceY           the y coordinate of the top left corner of the rectangle which will crop the imageResource.
     * @param sourceWidth       the width of the rectangle which will crop the source imageResource.
     * @param sourceHeight      the height of the rectangle which will crop the source imageResource.
     */
    public void drawImage(ImageResource imageResource, float destinationX, float destinationY, float destinationWidth, float destinationHeight,
                          float sourceX, float sourceY, float sourceWidth, float sourceHeight);


}
