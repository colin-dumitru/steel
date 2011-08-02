package edu.catalindumitru.bee.graphics;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 8/2/11
 * Time: 11:27 AM
 */
public class Gradient {
    /*A pair of doubles and colors, representing each step color and it's offset. The offset is a value between 0 and 1*/
    protected Map<Double, Color> gradientMap;

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public Gradient() {
        this.gradientMap = new TreeMap<>();
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void addColorStop(double offset, Color color){
        this.gradientMap.put(offset, color);
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void clearColorStops() {
        this.gradientMap.clear();
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public Set<Map.Entry<Double, Color>> getColorMap() {
        return this.gradientMap.entrySet();
    }
}
