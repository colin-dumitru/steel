package edu.catalindumitru.bee.world;

import edu.catalindumitru.bee.component.Component;
import edu.catalindumitru.bee.component.ComponentResolver;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 7/13/11
 * Time: 11:24 AM
 */
public class ComponentResolverVisitor implements NodeVisitor {
    protected ComponentResolver resolver;

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public ComponentResolverVisitor() {
        this.resolver = null;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public ComponentResolverVisitor(ComponentResolver resolver) {
        this.resolver = resolver;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void visit(Node node) {
        if (this.resolver != null) {
            for (Map.Entry<Integer, Component> entry : node.getComponents().entrySet())
                this.resolver.addComponent(entry.getValue());
        }

    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void setComponentResolver(ComponentResolver resolver) {
        this.resolver = resolver;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
}
