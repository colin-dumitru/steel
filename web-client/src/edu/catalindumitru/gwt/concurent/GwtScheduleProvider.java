package edu.catalindumitru.gwt.concurent;

import com.google.gwt.user.client.Timer;
import edu.catalindumitru.bee.concurent.ScheduleProvider;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 8/6/11
 * Time: 10:15 AM
 */
public class GwtScheduleProvider implements ScheduleProvider {
    protected Map<Runnable, Timer> schedules;


    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public GwtScheduleProvider() {
        this.schedules = new HashMap<Runnable, Timer>();
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Schedules a new task. If type is set ti delay, than the task will run once, after the given amount of time. If it
     * set to periodic, then the task will run periodically, once every "amount" time in milliseconds.
     *
     * @param runnable which task to tun.
     * @param type     type of schedule.
     * @param amount   time in milliseconds of which to repeat / delay the schedule.
     */
    @Override
    public void schedule(final Runnable runnable, TYPE type, int amount) {
        Timer tmp = new Timer() {
            @Override
            public void run() {
                runnable.run();
            }
        };
        this.schedules.put(runnable, tmp);

        switch (type) {

            case DELAY:
                tmp.schedule(amount);
                break;
            case PERIODIC:
                tmp.scheduleRepeating(amount);
                break;
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Cancels the task from ir's periodic update / delay.
     *
     * @param runnable which runnable to stop. If no runnable is found, then no action made.
     */
    @Override
    public void cancel(Runnable runnable) {
        Timer tmp = null;

        if ((tmp = this.schedules.get(runnable)) == null)
            return;

        tmp.cancel();
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Returns the current time in milliseconds.
     *
     * @return current time in milliseconds
     */
    @Override
    public native double currentTime() /*-{
        return Date.now();
    }-*/;
}
