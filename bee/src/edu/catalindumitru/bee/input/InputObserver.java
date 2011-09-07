package edu.catalindumitru.bee.input;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 7/18/11
 * Time: 10:32 AM
 */
public interface InputObserver {
    public void onMouseMove(int x, int y);
    public void onClick(int x, int y);
    public void onDoubleClick(int x, int y);
    public void onMouseUp(int x, int y);
    public void onMouseDown(int x, int y);
    public void onKeyUp(int key);
    public void onKeyDown(int key);
    public void onKeyPress(int key);
}
