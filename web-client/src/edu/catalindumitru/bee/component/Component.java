/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.catalindumitru.bee.component;

import edu.catalindumitru.bee.world.Node;

/**
 *
 * @author colin
 */
public interface Component {
    int E_STATE_CHANGED = 10;

    public void setParent(Node node);

    public int getType();
    public void setObserver(ComponentObserver observer);

    public void update(float delta);
    public void destroy();
    
}
