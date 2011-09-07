package edu.catalindumitru.bee.input;

import edu.catalindumitru.bee.core.Logger;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 7/18/11
 * Time: 10:30 AM
 */
public class InputManager implements InputObserver {



    public static enum EVENTS {
        E_MOUSE_CLICK,
        E_MOUSE_DOWN,
        E_MOUSE_UP,
        E_MOUSE_MOVE,
        E_MOUSE_WHEEL,
        E_MOUSE_DOUBLE_CLICK,
        E_KEY_UP,
        E_KEY_DOWN
    }

    protected InputProvider provider;
    protected Set<InputObserver> observers;


    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public InputManager(InputProvider provider) {
        this.observers = new HashSet<InputObserver>();
        this.setInputProvider(provider);
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void addObserver(InputObserver observer) {
        this.observers.add(observer);

        if (this.provider == null)
            Logger.log(Logger.PRIORITY.WARNING, "An input observer has been added but no input provider has been setup");
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void removeObserver(InputObserver observer) {
        this.observers.remove(observer);
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void setInputProvider(InputProvider provider) {
        this.provider = provider;
        this.provider.setInputObserver(this);

    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    @Override
    public void onMouseMove(int x, int y) {
        for (InputObserver observer : this.observers)
            observer.onMouseMove(x, y);
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public void onClick(int x, int y) {
        for (InputObserver observer : this.observers)
            observer.onClick(x, y);
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public void onDoubleClick(int x, int y) {
        for (InputObserver observer : this.observers)
            observer.onDoubleClick(x, y);
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public void onMouseUp(int x, int y) {
        for (InputObserver observer : this.observers)
            observer.onMouseUp(x, y);
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public void onMouseDown(int x, int y) {
        for (InputObserver observer : this.observers)
            observer.onMouseDown(x, y);
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public void onKeyUp(int key) {
        for (InputObserver observer : this.observers)
            observer.onKeyUp(key);
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public void onKeyDown(int key) {
        for (InputObserver observer : this.observers)
            observer.onKeyDown(key);
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public void onKeyPress(int key) {
        for (InputObserver observer : this.observers)
            observer.onKeyPress(key);
    }
}
