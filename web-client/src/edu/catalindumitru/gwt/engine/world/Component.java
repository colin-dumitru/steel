/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.catalindumitru.gwt.engine.world;

/**
 *
 * @author colin
 */
public interface Component {
    public void setParrent(Node node);
    public void update(float delta);
    public void destroy();
    
}
