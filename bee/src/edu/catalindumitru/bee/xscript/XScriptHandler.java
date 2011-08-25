package edu.catalindumitru.bee.xscript;

import edu.catalindumitru.bee.content.Resource;
import edu.catalindumitru.bee.content.ResourceObserver;
import edu.catalindumitru.bee.content.XmlResource;
import edu.catalindumitru.bee.content.xml.Element;
import edu.catalindumitru.bee.content.xml.Node;
import edu.catalindumitru.bee.content.xml.NodeList;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 8/22/11
 * Time: 8:18 AM
 */
public class XScriptHandler {
    protected final static String ROOT_ELEMENT = "xscript";

    /*a map sorted by the command name which contains all the commands which can be handled.*/
    protected Map<String, XScriptCommand> commandHandlers = new TreeMap<String, XScriptCommand>();
    /*a list of xml scripts which will be handled on the next call to update*/
    protected Queue<Element> queuedElements = new LinkedList<Element>();
    /*unique instance*/
    protected static XScriptHandler instance = new XScriptHandler();

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    protected XScriptHandler() {

    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public static XScriptHandler instance() {
        return instance;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void addCommand(XScriptCommand command) {
        this.commandHandlers.put(command.commandName().toLowerCase(), command);
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void removeCommand(XScriptCommand command) {
        this.commandHandlers.remove(command.commandName().toLowerCase());
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void removeCommand(String command) {
        this.commandHandlers.remove(command.toLowerCase());
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void handleScript(Element script, boolean async) {
        if (async)
            this.queuedElements.add(script);
        else
            this.translateScript(script);
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void handleScript(Element script) {
        this.queuedElements.add(script);
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void handleScript(String fileName) {
        Resource resource = new Resource("xml", fileName);

        if (resource.getStatus() == Resource.STATUS.COMPLETED)
            this.handleScript(((XmlResource) resource.getResource()).getRootNode().castToElement());
        else
            resource.addResourceObserver(new ResourceObserver() {
                @Override
                public void stateChanged(Resource from) {
                    if (from.getStatus() == Resource.STATUS.COMPLETED)
                        handleScript(((XmlResource) from.getResource()).getRootNode().castToElement());
                }
            });
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void update() {
        while (this.queuedElements.size() > 0) {
            Element top = this.queuedElements.remove();

            this.translateScript(top);
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    private void translateScript(Element rootElement) {
        /*if the root element is of type "xscript" thn we interpret it's children*/
        if (rootElement.getTagName().toLowerCase().equals(ROOT_ELEMENT)) {
            NodeList children = rootElement.getChildNodes();

            for (int i = 0; i < children.getLength(); i++) {
                Node child = children.item(i);

                /*check for correct type*/
                if (child.getNodeType() != Node.ELEMENT_NODE)
                    continue;

                /*interpret the child*/
                translateScript(child.castToElement());
            }
            /*if not then we simply interpret the element*/
        } else {
            /*get the command associated with the element*/
            XScriptCommand command = this.commandHandlers.get(rootElement.getNodeName().toLowerCase());

            if (command == null)
                return;

            /*if the command exists, then interpret the element*/
            command.handleCommand(rootElement, this);
        }


    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------


}
