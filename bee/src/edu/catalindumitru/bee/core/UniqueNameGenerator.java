package edu.catalindumitru.bee.core;

import edu.catalindumitru.bee.util.Cell;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 7/6/11
 * Time: 12:09 PM
 * <p/>
 * Provides a way to generate unique names.
 */
public class UniqueNameGenerator {
    /*used to store unique calls to the next function for every tag that was given*/
    protected Map<String, Cell<Integer>> instances;
    /*unique instance of the generator*/
    protected static final UniqueNameGenerator instance = new UniqueNameGenerator();

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    protected UniqueNameGenerator() {
        this.instances = new TreeMap<String, Cell<Integer>>();
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public static UniqueNameGenerator instance() {
        return UniqueNameGenerator.instance;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Return the next unique name for the given tag. If this is the first time this tag has been used, the name
     * starts at 0.
     *
     * @param tag the tag fot wich to get the unique number.
     * @return a concatenation between the tag and the number of queries which have happened for that tag
     *         up until now.
     */
    public String next(String tag) {
        Cell<Integer> value = this.instances.get(tag);

        /*If this is the first time this tag has been queried, we add it with initial instance value of 0*/
        if (value == null) {
            this.instances.put(tag, (value = new Cell<Integer>(0)));
        }

        /*increment the instance value and return a concatenation of that number and the tag*/
        value.setValue(value.getValue() + 1);

        return tag + "_" + value.getValue();

    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Sets the value of instances for the given tag to the number given by parameter.
     *
     * @param tag       the tag to set the instances
     * @param instances the number of instances to set.
     */
    public void setInstances(String tag, int instances) {
        Cell<Integer> value = this.instances.get(tag);

        /*If this is the first time this tag has been queried, we add it with initial instance value of 0*/
        if (value == null) {
            this.instances.put(tag, (value = new Cell<Integer>(instances)));
        } else {
            value.setValue(instances);
        }
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Resets all the instance names to 0.
     */
    public void reset() {
        this.instances.clear();
    }

}
