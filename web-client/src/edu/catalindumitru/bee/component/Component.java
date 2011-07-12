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
    public void setParrent(Node node);
    public void update(float delta);
    public int getType();
    public void destroy();
    
}
