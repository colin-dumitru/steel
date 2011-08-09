package edu.catalindumitru.bee.gui;

import edu.catalindumitru.bee.graphics.Render2DProvider;
import edu.catalindumitru.bee.math.Rectangle;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 7/16/11
 * Time: 9:21 AM
 */
public class Panel extends Widget {

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public Panel() {
        super();
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public Panel(String id) {
        super(id);
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    /**
     * Function is called when a widget needs to be redrawn.
     *
     * @param provider which provider to use to draw the widget.
     * @param region   what region needs to be redrawn. This is useful for composite widgets which might not need to redraw
     *                 all widgets it encloses.
     */
    @Override
    public void draw(Render2DProvider provider, Rectangle region) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
}
