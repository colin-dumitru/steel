package edu.catalindumitru.bee.gui;

import edu.catalindumitru.bee.graphics.Font;
import edu.catalindumitru.bee.graphics.Render2DProvider;
import edu.catalindumitru.bee.math.Rectangle;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 8/10/11
 * Time: 10:33 AM
 */
public class Label extends Widget{
    /*which text to draw*/
    protected String text = "undefined";
    /*the overall dimensions of the label*/
    protected Rectangle dimensions = new Rectangle(0, 0, 0, 0);

    /*font used when drawing the label*/
    protected Font font = Font.DEFAULT;
    /*horizontal text align*/
    protected Render2DProvider.TEXT_ALIGN textAlign;
    /*vertical text align*/
    protected Render2DProvider.TEXT_BASELINE textBaseline;
    /*text padding*/
    protected int padding = 5;


    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public Label() {
        super();
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public Label(String id) {
        super(id);
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    /**
     * Function is called when a widget needs to be redrawn.
     *
     * @param provider which provider to use to draw the widget.
     * @param region   what region needs to be redrawn. This is useful for composite widgets which might not need to redraw
     *                 all widgets it encloses. If the region is null then the entire widget should be redrawn.
     */
    @Override
    public void draw(Render2DProvider provider, Rectangle region) {
        /*first fill the background*/
        provider.setFillColor(this.background);
        provider.setFillStyle(Render2DProvider.STYLE.COLOR);
        provider.fillRectangle((int)this.dimensions.getX(), (int)this.dimensions.getY(),
                (int)this.dimensions.getWidth(), (int)this.dimensions.getHeight());

        provider.setTextAlign(this.textAlign);
        provider.setTextBaseline(this.textBaseline);

        provider.setFillColor(this.foreground);
        provider.setFillStyle(Render2DProvider.STYLE.COLOR);

        provider.fillString(this.padding, this.padding, this.text);
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public String getText() {
        return text;
    }
     //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public void setText(String text) {
        this.text = text;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public Rectangle getDimensions() {
        return dimensions;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public void setDimensions(Rectangle dimensions) {
        this.dimensions = dimensions;
        this.bounds.setAll(dimensions.getX(), dimensions.getY(), dimensions.getWidth(), dimensions.getHeight());
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void setDimensions(int x, int y, int width, int height) {
        this.dimensions.setAll(x, y, width,  height);
        this.bounds.setAll(x, y, width, height);
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public Font getFont() {
        return font;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void setFont(Font font) {
        this.font = font;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public Render2DProvider.TEXT_ALIGN getTextAlign() {
        return textAlign;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public void setTextAlign(Render2DProvider.TEXT_ALIGN textAlign) {
        this.textAlign = textAlign;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public Render2DProvider.TEXT_BASELINE getTextBaseline() {
        return textBaseline;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public void setTextBaseline(Render2DProvider.TEXT_BASELINE textBaseline) {
        this.textBaseline = textBaseline;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
}
