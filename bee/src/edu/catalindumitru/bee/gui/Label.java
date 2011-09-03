package edu.catalindumitru.bee.gui;

import edu.catalindumitru.bee.content.xml.Element;
import edu.catalindumitru.bee.graphics.Color;
import edu.catalindumitru.bee.graphics.Font;
import edu.catalindumitru.bee.graphics.Render2DProvider;
import edu.catalindumitru.bee.math.Rectangle;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 8/10/11
 * Time: 10:33 AM
 */
public class Label extends Widget {

    //--------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------
    public static final class Builder implements WidgetBuilder {
        public static final String A_TEXT = "text";
        public static final String A_FONT_SIZE = "fontSize";
        public static final String A_FONT_WEIGHT = "fontWeight";
        public static final String A_FONT_STYLE = "fontStyle";
        public static final String A_FONT_FAMILY = "fontFamily";
        public static final String A_PADDING_Y = "paddingY";
        public static final String A_PADDING_X = "paddingX";
        public static final String A_PADDING = "padding";

        //--------------------------------------------------------------------------------------------------------------
        //--------------------------------------------------------------------------------------------------------------

        /**
         * Creates a new widget from the root element.
         *
         * @param root the root element from the xml we are building.
         * @return the built widget.
         */
        @Override
        public Widget build(Element root) {
            /*check for correct element node.*/
            if (!root.getTagName().toLowerCase().equals(this.widgetLabel()))
                return null;

            Label ret = new Label();

            Builder.builderProxy(root, ret);

            return ret;
        }
        //--------------------------------------------------------------------------------------------------------------
        //--------------------------------------------------------------------------------------------------------------

        /**
         * Used by function which are a direct descendant of label to use common attributes.
         *
         * @param root   the root element which contains all the attributes of the widget to be built.
         * @param widget the widget to be built.
         */
        public static final void builderProxy(Element root, Label widget) {
            /*pass the widget through the builder proxy provided by the Widget class to get the common attributes*/
            Widget.builderProxy(widget, root);

            /*text to be displayed*/
            if (root.hasAttribute(A_TEXT))
                widget.setText(root.getAttribute(A_TEXT));

            /*font which will contain all attributes given to the widget*/
            Font font = new Font();

            /*font size*/
            if (root.hasAttribute(A_FONT_SIZE))
                font.setSize(Float.parseFloat(root.getAttribute(A_FONT_SIZE)));

            /*font weight*/
            if (root.hasAttribute(A_FONT_WEIGHT)) {
                String weight = root.getAttribute(A_FONT_WEIGHT);

                if (weight.equals("normal"))
                    font.setWeight(Font.WEIGHT.NORMAL);
                else if (weight.equals("BOLD"))
                    font.setWeight(Font.WEIGHT.BOLD);
            }

            /*font style*/
            if (root.hasAttribute(A_FONT_STYLE)) {
                String style = root.getAttribute(A_FONT_SIZE);

                if (style.equals("normal"))
                    font.setStyle(Font.STYLE.NORMAL);
                else if (style.equals("italic"))
                    font.setStyle(Font.STYLE.ITALIC);
                else if (style.equals("oblique"))
                    font.setStyle(Font.STYLE.OBLIQUE);
            }

            /*font family*/
            if (root.hasAttribute(A_FONT_FAMILY))
                font.setFamily(root.getAttribute(A_FONT_FAMILY));

            /*padding on the Y axis*/
            if (root.hasAttribute(A_PADDING_Y))
                widget.setPaddingY(Integer.parseInt(root.getAttribute(A_PADDING_Y)));

            /*padding on the X axis*/
            if (root.hasAttribute(A_PADDING_X))
                widget.setPaddingX(Integer.parseInt(root.getAttribute(A_PADDING_X)));

            /*padding on all axes*/
            if (root.hasAttribute(A_PADDING)) {
                widget.setPaddingY(Integer.parseInt(root.getAttribute(A_PADDING)));
                widget.setPaddingX(Integer.parseInt(root.getAttribute(A_PADDING)));
            }

            widget.setFont(font);

        }
        //--------------------------------------------------------------------------------------------------------------
        //--------------------------------------------------------------------------------------------------------------

        /**
         * Returns the root widget this builder can handle.
         *
         * @return the name of the root widget this builder can handle.
         */
        @Override
        public String widgetLabel() {
            return "label";
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    /*which text to draw*/
    protected String text = "undefined";
    /*the overall dimensions of the label*/
    protected Rectangle dimensions = new Rectangle(0, 0, 0, 0);

    /*font used when drawing the label*/
    protected Font font = Font.DEFAULT;
    /*horizontal text align*/
    protected Render2DProvider.TEXT_ALIGN textAlign = Render2DProvider.TEXT_ALIGN.LEFT;
    /*vertical text align*/
    protected Render2DProvider.TEXT_BASELINE textBaseline = Render2DProvider.TEXT_BASELINE.TOP;
    /*text paddingY*/
    protected int paddingY = 2;
    /*text paddingX*/
    protected int paddingX = 2;


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
    protected void initialise() {
        super.initialise();

        /*default colors*/
        this.background = Color.TRANSPARENT;
        this.foreground = Color.BLACK;
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

        /*if the radius is smaller than 0, then we draw a simple recngle*/
        if (this.radius <= 0) {
            provider.fillRectangle((int) this.dimensions.getX(), (int) this.dimensions.getY(),
                    (int) this.dimensions.getWidth(), (int) this.dimensions.getHeight());
        } else {
            /*otherwise we draw a round rectangle*/
            provider.fillRoundRectangle((int) this.dimensions.getX(), (int) this.dimensions.getY(),
                    (int) this.dimensions.getWidth(), (int) this.dimensions.getHeight(), radius);
        }

        /*draw a border if requested*/
        if (border) {
            /*first fill teh background with the background color*/
            provider.setStrokeStyle(Render2DProvider.STYLE.COLOR);
            provider.setStrokeColor(this.borderColor);
            provider.setLineWidth(this.borderWidth);

            /*if the radius is smaller than 0, then we draw a simple recngle*/
            if (this.radius <= 0) {
                provider.strokeRectangle((int) this.dimensions.getX(), (int) this.dimensions.getY(),
                        (int) this.dimensions.getWidth(), (int) this.dimensions.getHeight());
            } else {
                /*otherwise we draw a round rectangle*/
                provider.strokeRoundRectangle((int) this.dimensions.getX(), (int) this.dimensions.getY(),
                        (int) this.dimensions.getWidth(), (int) this.dimensions.getHeight(), radius);
            }

        }

        provider.setTextAlign(this.textAlign);
        provider.setTextBaseline(this.textBaseline);

        provider.setFillColor(this.foreground);
        provider.setFillStyle(Render2DProvider.STYLE.COLOR);

        provider.setTextFont(this.font);

        provider.fillString((int) this.dimensions.getX() + this.paddingX,
                (int) this.dimensions.getY() + this.paddingY,
                this.text);
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * This method is called after widgets bounds have been modified.
     */
    @Override
    protected void boundsChanged() {
        /*update dimensions*/
        this.dimensions.setAll(this.bounds);
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public Rectangle getDimensions() {
        return dimensions;
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

    public int getPaddingY() {
        return paddingY;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void setPaddingY(int paddingY) {
        this.paddingY = paddingY;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public int getPaddingX() {
        return paddingX;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void setPaddingX(int paddingX) {
        this.paddingX = paddingX;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
}
