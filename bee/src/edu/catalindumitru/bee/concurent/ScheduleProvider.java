package edu.catalindumitru.bee.concurent;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 8/6/11
 * Time: 10:06 AM
 */
public interface ScheduleProvider {
    public enum TYPE {DELAY, PERIODIC}

    /**
     * Schedules a new task. If type is set ti delay, than the task will run once, after the given amount of time. If it
     * set to periodic, then the task will run periodically, once every "amount" time in milliseconds.
     *
     * @param runnable which task to tun.
     * @param type     type of schedule.
     * @param amount   time in milliseconds of which to repeat / delay the schedule.
     */
    public void schedule(Runnable runnable, TYPE type, int amount);

    /**
     * Cancels the task from ir's periodic update / delay.
     *
     * @param runnable which runnable to stop. If no runnable is found, then no action made.
     */
    public void cancel(Runnable runnable);

    /**
     * Returns the current time in milliseconds.
     *
     * @return current time in milliseconds
     */
    public double currentTime();
}
