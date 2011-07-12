package edu.catalindumitru.bee.component;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 7/12/11
 * Time: 3:01 PM
 *
 */

/**
 * A class which can alter or use the state of a component to acheive a goal. One example is a render provider which
 * renders every renderable component which has been added. This is useful if we want to do specific tasks, or separate
 * components based on their state (like sorting renderables by their shader to reduce the number of shader swapping).
 */
public interface ComponentResolver {
    /*Predetermined priorities*/
    public static final int P_LOGIC = 10;
    public static final int P_NORMAL_RENDER = 20;
    /**
     * Adds a new component to be resolved
     * @param component which component to add
     */
    public void addComponent(Component component);

    /**
     * Removes the component from it's list.
     * @param component which component ro remove.
     */
    public void removeComponent(Component component);

    /**
     * Update the state of the every component from it's list.
     * @param delta the delta time from the call of this method.
     */
    public void updateCycle(float delta);

    /**
     * Ends the current cycle of the component provider. Meaning it should be called after all component provider have had
     * a chance to update. This is useful when, for example, a component resolver saves a state for each cycle which can
     * be used by other component provider (for example the collision detection component resolver might save which object
     * intersect, information which can be used by the logic component resolver).
     */

    public void endCycle();
    /**
         * The priority of the component resolver. Every component resolver will have their update method called in the order
         * of their priority. For example you want the logic update component resolver to fire up before the one for rendering.
         * @return the priority of the component resolver.
         */

    public int priority();
}
