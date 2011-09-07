package edu.catalindumitru.bee.render;

import edu.catalindumitru.bee.graphics.Color;
import edu.catalindumitru.bee.graphics.Render3DProvider;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 9/5/11
 * Time: 11:12 AM
 */
public class RenderManager {
    protected final static Color D_CLEAR_COLOR = new Color(0.5f, 0.8f, 0.8f, 1.0f);
    protected final static int D_CLEAR_MASK = Render3DProvider.COLOR_BUFFER_BIT;

    protected Render3DProvider provider;


    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public RenderManager(Render3DProvider provider) {
        this.provider = provider;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public boolean initialize() {
        if(this.provider == null)
            return false;

        /*set provider parameters*/
        this.provider.clearColor(D_CLEAR_COLOR);

        return true;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void update() {

        if(this.beginRenderCycle()) {
            try{
                this.updateRenderCycle();
            } catch (Exception ex) {

            } finally {
                this.endRenderCycle();
            }
        }

    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    protected boolean beginRenderCycle() {
        if(this.provider == null)
            return false;

        /*pre render stuff*/
        //this.provider.clearColor(new Color((float)Math.random(), (float)Math.random(), (float)Math.random(), 1.0f));
        this.provider.clear(D_CLEAR_MASK);

        return true;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    protected void endRenderCycle() {

    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    protected void updateRenderCycle() {
    }

}
