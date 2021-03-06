package edu.catalindumitru.bee.graphics;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 7/21/11
 * Time: 4:33 PM
 */
public class Color {
    //color and alpha values are clamped between 0 and 1
    protected float red;
    protected float green;
    protected float blue;
    protected float alpha;

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public static final Color RED = new Color(1.0f, 0.0f, 0.0f);
    public static final Color GREEN = new Color(0.0f, 1.0f, 0.0f);
    public static final Color BLUE = new Color(0.0f, 0.0f, 1.0f);
    public static final Color YELLOW = new Color(1.0f, 1.0f, 0.0f);
    public static final Color ORANGE = new Color(1.0f, 0.5f, 0.0f);
    public static final Color PURPLE = new Color(1.0f, 0.0f, 1.0f);
    public static final Color CYAN = new Color(0.0f, 1.0f, 1.0f);
    public static final Color BLACK = new Color(0.0f, 0.0f, 0.0f);
    public static final Color WHITE = new Color(1.0f, 1.0f, 1.0f);
    public static final Color TRANSPARENT = new Color(1.0f, 1.0f, 1.0f, (float) 0.0);


    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public Color(float red, float green, float blue, float alpha) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public Color(float red, float green, float blue) {
        this(red, green, blue, 1.0f);
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public Color() {
        this(0, 0, 0, 0);
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public float getRed() {
        return red;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void setRed(float red) {
        this.red = Math.max(0, Math.min(1.0f, red));
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public float getGreen() {
        return green;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void setGreen(float green) {
        this.green = Math.max(0, Math.min(1.0f, green));
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public float getBlue() {
        return blue;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void setBlue(float blue) {
        this.blue = Math.max(0, Math.min(1.0f, blue));
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public float getAlpha() {
        return alpha;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
}
