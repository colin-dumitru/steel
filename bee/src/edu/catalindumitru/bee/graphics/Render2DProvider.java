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
     *  Draw the specified imageResource to the canvas. The imageResource will retain it's dimensions when drawn.
     * @param imageResource which imageResource to draw to the canvas.
     * @param destinationX the x coordinate of the top left point on the canvas where the imageResource should be drawn.
     * @param destinationY the y coordinate of the top left point on the canvas where the imageResource should be drawn.
     */
    public void drawImage(ImageResource imageResource, float destinationX, float destinationY);

    /**
     *  Draw the specified imageResource to the canvas.  The source imageResource will be drawn at coordinates destinationX,
     *  destinationY with the dimensions destinationWidth and destinationHeight. If the height or width differ from
     *  the source imageResource, then the imageResource will be scaled accordingly.
     * @param imageResource which imageResource to draw to the canvas.
     * @param destinationX the x coordinate of the top left point on the canvas where the imageResource should be drawn.
     * @param destinationY the y coordinate of the top left point on the canvas where the imageResource should be drawn.
     * @param destinationWidth the destination width of the final imageResource to be drawn.
     * @param destinationHeight the destination height of the final imageResource to be drawn.
     */
    public void drawImage(ImageResource imageResource, float destinationX, float destinationY, float destinationWidth, float destinationHeight );

    /**
     *  Draw the specified imageResource to the canvas. The imageResource will be cropped using a rectangle described the the parameters:
     *  sourceX, sourceY, sourceWidth and sourceHeight. The cropped imageResource will be drawn at coordinates destinationX,
     *  destinationY with the dimensions destinationWidth and destinationHeight. If the height or width differ from
     *  the source imageResource, then the imageResource will be scaled accordingly.
     * @param imageResource which imageResource to draw to the canvas.
     * @param destinationX the x coordinate of the top left point on the canvas where the imageResource should be drawn.
     * @param destinationY the y coordinate of the top left point on the canvas where the imageResource should be drawn.
     * @param destinationWidth the destination width of the final imageResource to be drawn.
     * @param destinationHeight the destination height of the final imageResource to be drawn.
     * @param sourceX the x coordinate of the top left corner of the rectangle which will crop the imageResource.
     * @param sourceY the y coordinate of the top left corner of the rectangle which will crop the imageResource.
     * @param sourceWidth the width of the rectangle which will crop the source imageResource.
     * @param sourceHeight the height of the rectangle which will crop the source imageResource.
     */
    public void drawImage(ImageResource imageResource, float destinationX, float destinationY, float destinationWidth, float destinationHeight,
                          float sourceX, float sourceY, float sourceWidth, float sourceHeight);


}
