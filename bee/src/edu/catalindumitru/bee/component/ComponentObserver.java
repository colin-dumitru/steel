package edu.catalindumitru.bee.component;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 7/12/11
 * Time: 3:16 PM
 */
public interface ComponentObserver {
    public void onEvent(int id, Object param, Component from);
}
