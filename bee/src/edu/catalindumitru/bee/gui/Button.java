package edu.catalindumitru.bee.gui;

import edu.catalindumitru.bee.content.xml.Element;
import edu.catalindumitru.bee.graphics.Color;
import edu.catalindumitru.bee.graphics.Gradient;
import edu.catalindumitru.bee.graphics.Render2DProvider;
import edu.catalindumitru.bee.math.Rectangle;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 8/25/11
 * Time: 10:46 AM
 */
public class Button extends Label {
    public static final class Builder implements WidgetBuilder {
        //--------------------------------------------------------------------------------------------------------------
        //--------------------------------------------------------------------------------------------------------------
        public static final String LABEL = "button";

        public static final String A_OVERLAY = "overlay";

        /**
         * Creates a new widget from the root element.
         *
         * @param root the root element from the xml we are building.
         * @return the built widget.
         */
        @Override
        public Widget build(Element root) {
            if (!root.getTagName().toLowerCase().equals(this.widgetLabel()))
                return null;

            Button ret = new Button();

            buildProxy(root, ret);

            return ret;
        }

        //--------------------------------------------------------------------------------------------------------------
        //--------------------------------------------------------------------------------------------------------------
        public static final void buildProxy(Element root, Button widget) {
            Label.Builder.builderProxy(root, widget);

            /*check for overlay effect*/
            if (root.hasAttribute(A_OVERLAY))
                widget.setHasOverlay(Boolean.parseBoolean(root.getAttribute(A_OVERLAY)));


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
            return LABEL;
        }
    }

    public static final Color C_DEFAULT_BACKGROUND = new Color(0.3f, 0.3f, 0.3f, 1.0f);
    public static final Color C_DEFAULT_FOREGROUND = new Color(0.9f, 0.9f, 0.9f, 1.0f);
    public static final Color C_DEFAULT_BORDER = new Color(1.0f, 1.0f, 1.0f, 1.0f);
    public static final Color C_DEFAULT_IDLE = new Color(0.2f, 0.2f, 0.2f, 0.3f);
    public static final Color C_DEFAULT_HOVER = new Color(0.8f, 0.3f, 0.3f, 0.7f);
    public static final int C_DEFAULT_BORDER_RADIUS = 1;
    public static final int DEFAULT_RADIUS = 5;

    /*if an overlay over the button should be drawn*/
    protected boolean hasOverlay;
    protected Color overlayIdleColor;
    protected Color overlayHoverColor;

    protected Gradient overlayIdleGradient;
    protected Gradient overlayHoverGradient;

    /*last state of the hovered param, to check whether we should redraw on hover in or out*/
    protected boolean lastHovered;

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public void initialise() {
        super.initialise();

        this.background = C_DEFAULT_BACKGROUND;
        this.foreground = C_DEFAULT_FOREGROUND;
        this.borderColor = C_DEFAULT_BORDER;
        this.borderWidth = C_DEFAULT_BORDER_RADIUS;
        this.border = true;

        this.radius = DEFAULT_RADIUS;

        this.overlayIdleGradient = new Gradient(Gradient.TYPE.LINEAR);
        this.overlayHoverGradient = new Gradient(Gradient.TYPE.LINEAR);

        this.hasOverlay = true;
        this.setOverlayIdleColor(C_DEFAULT_IDLE);
        this.setOverlayHoverColor(C_DEFAULT_HOVER);

        /*enabled by default to ensure hovering effects*/
        this.enabled = true;
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

        /*overlay*/
        if (this.hasOverlay) {
            /*first fill teh background with the background color*/
            provider.setFillStyle(Render2DProvider.STYLE.GRADIENT);

            if (this.hovered)
                provider.setFillGradient(this.overlayHoverGradient);
            else
                provider.setFillGradient(this.overlayIdleGradient);

            /*if the radius is smaller than 0, then we draw a simple recngle*/
            if (this.radius <= 0) {
                provider.fillRectangle((int) this.dimensions.getX(), (int) this.dimensions.getY(),
                        (int) this.dimensions.getWidth(), (int) this.dimensions.getHeight());
            } else {
                /*otherwise we draw a round rectangle*/
                provider.fillRoundRectangle((int) this.dimensions.getX(), (int) this.dimensions.getY(),
                        (int) this.dimensions.getWidth(), (int) this.dimensions.getHeight(), radius);
            }
        }

        /*draw a border if requested*/
        if (border) {
            /*first fill teh background with the background color*/
            provider.setStrokeStyle(Render2DProvider.STYLE.COLOR);
            provider.setStrokeColor(this.borderColor);
            provider.setLineWidth(this.borderWidth);

            /*if the radius is smaller than 0, then we draw a simple rectangle. We translate the rectangle to ensure
            * that the stroked border will be inside the clipping area. Also we subtract 1 for coordinates and add 2
            * for dimensions because the border has the center one pixel in the rectangle.*/
            if (this.radius <= 0) {
                /*otherwise we draw a round rectangle*/
                provider.strokeRectangle((int) this.dimensions.getX() + this.radius / 2 - 1,
                        (int) this.dimensions.getY() + this.radius / 2 - 1,
                        (int) this.dimensions.getWidth() - this.radius + 2,
                        (int) this.dimensions.getHeight() - this.radius + 2);
            } else {
                /*otherwise we draw a round rectangle*/
                provider.strokeRoundRectangle((int) this.dimensions.getX() + this.radius / 2 - 1,
                        (int) this.dimensions.getY() + this.radius / 2 - 1,
                        (int) this.dimensions.getWidth() - this.radius + 2,
                        (int) this.dimensions.getHeight() - this.radius + 2,
                        radius);
            }

        }

        provider.setTextAlign(this.textAlign);
        provider.setTextBaseline(this.textBaseline);

        provider.setFillColor(this.foreground);
        provider.setFillStyle(Render2DProvider.STYLE.COLOR);

        provider.setTextFont(this.font);

        int textWidth = provider.getStringWidth(this.text);
        /*draw the string at the center of the button*/
        provider.fillString((int) (this.dimensions.getX() + (this.dimensions.getWidth() / 2) - (textWidth / 2)),
                (int) (this.dimensions.getY() + (this.dimensions.getHeight() / 2) - (this.font.getSize() * 8)),
                this.text);
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public boolean isHasOverlay() {
        return hasOverlay;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public void setHasOverlay(boolean hasOverlay) {
        this.hasOverlay = hasOverlay;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public Color getOverlayIdleColor() {
        return overlayIdleColor;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void setOverlayIdleColor(Color overlayIdleColor) {
        this.overlayIdleColor = overlayIdleColor;

        /*create overlay gradient*/
        this.overlayIdleGradient.clearColorStops();
        this.overlayIdleGradient.addColorStop(0.0, new Color(this.overlayIdleColor.getRed(), this.overlayIdleColor.getGreen()
                , this.overlayIdleColor.getBlue(), 0.0f));
        this.overlayIdleGradient.addColorStop(1.0, this.overlayIdleColor);

    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public Color getOverlayHoverColor() {
        return overlayHoverColor;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public void setOverlayHoverColor(Color overlayHoverColor) {
        this.overlayHoverColor = overlayHoverColor;

        /*create overlay gradient*/
        this.overlayHoverGradient.clearColorStops();
        this.overlayHoverGradient.addColorStop(0.0, new Color(this.overlayHoverColor.getRed(), this.overlayHoverColor.getGreen()
                , this.overlayHoverColor.getBlue(), 0.0f));
        this.overlayHoverGradient.addColorStop(1.0, this.overlayHoverColor);
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public void onEvent(UiEvent event) {
        this.lastHovered = this.hovered;

        super.onEvent(event);

        /*check for state changed*/
        if (this.lastHovered != this.hovered)
            /*call a redraw*/
            UiManager.instance().getEventDispatcher().dispatchEvent(new UiEvent(this.bounds,
                    null, UiEvent.TYPE.REDRAW_REGION));
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

}
