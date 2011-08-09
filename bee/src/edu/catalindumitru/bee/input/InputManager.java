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
public class InputManager implements InputResolver {


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

    protected static InputManager instance = new InputManager();
    protected InputProvider provider;
    protected Set<InputObserver> observers;


    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    protected InputManager() {
        this.observers = new HashSet<InputObserver>();
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public static InputManager instance() {
        return instance;
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
        this.provider.setInputResolver(this);

    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    protected void setupInputProvider(InputProvider provider) {
        provider.setInputResolver(this);

        this.setupInputProvider(provider);
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void onEvent(EVENTS event, float[] params) {
        for (InputObserver observer : this.observers)
            observer.onEvent(event, params);
    }
}
