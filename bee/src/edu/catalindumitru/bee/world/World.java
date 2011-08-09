package edu.catalindumitru.bee.world;

import edu.catalindumitru.bee.component.ComponentResolver;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 7/12/11
 * Time: 2:53 PM
 */
public class World implements NodeObserver {
    /*Root level nodes*/
    protected Map<String, Node> rootNodes;
    /*component resolvers*/
    protected Map<Integer, ComponentResolver> componentResolvers;

    /*Used to traverse Nodes to add components to the component resolver*/
    protected NodeVisitor componentResolverVisitor;


    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void World() {
        /*initialize containers*/
        this.rootNodes = new TreeMap<String, Node>();
        this.componentResolvers = new TreeMap<Integer, ComponentResolver>();

        /*initialize visitors*/
        this.componentResolverVisitor = new ComponentResolverVisitor();
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void addRootNode(Node node) {
        this.rootNodes.put(node.getName(), node);
        node.addObserver(this);

        /*Traverse new node to add it's component*/
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void removeRootNode(Node node) {
        this.rootNodes.remove(node.getName());
        node.removeObserver(this);
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void removeRootNode(String name) {
        this.rootNodes.remove(name).removeObserver(this);
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Send an event to the node observer. This event could be something like removed from the tree structure, a new
     * component has been added, removed etc.
     *
     * @param event  event id. (See @Node).
     * @param param  additional abstract object which can be send as a param (Eg you can send which component has been
     *               added / removed).
     * @param source which node has triggered the event.
     */
    public void onEvent(int event, Object param, Node source) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------


}
