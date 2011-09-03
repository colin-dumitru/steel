package edu.catalindumitru.bee.content;

import edu.catalindumitru.bee.content.xml.Element;
import edu.catalindumitru.bee.content.xml.Node;
import edu.catalindumitru.bee.content.xml.NodeList;
import edu.catalindumitru.bee.xscript.XScriptCommand;
import edu.catalindumitru.bee.xscript.XScriptHandler;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 8/5/11
 * Time: 12:32 PM
 */
public interface ResourceProvider {
    /*a class which handles preloading of resources in an xml script*/
    public static final class ResourceXCommand implements XScriptCommand {
        public final String COMMAND_NAME = "preload";
        public final String COMMAND_ON_COMPLETE = "oncomplete";
        public final String COMMAND_ON_ERROR = "onerror";

        public final String A_TYPE = "type";
        public final String A_LINK = "link";


        //--------------------------------------------------------------------------------------------------------------
        //--------------------------------------------------------------------------------------------------------------

        /**
         * The name of the command it can handle
         *
         * @return the name of the command the command can handle.
         */
        @Override
        public String commandName() {
            return COMMAND_NAME;
        }
        //--------------------------------------------------------------------------------------------------------------
        //--------------------------------------------------------------------------------------------------------------

        /**
         * Handles the given command.
         *
         * @param root    the element root which contains the rest of the information needed to handle the command.
         * @param handler a script handler need for recursive or tree commands.
         */
        @Override
        public void handleCommand(Element root, XScriptHandler handler) {
            if (!root.getTagName().toLowerCase().equals(COMMAND_NAME))
                return;

            /*check for all params*/
            if (!root.hasAttribute(A_TYPE) || !root.hasAttribute(A_LINK))
                return;

            /*preload the given resource*/
            Resource tmp = new Resource(root.getAttribute(A_TYPE), root.getAttribute(A_LINK));
            /*cache the root element to prevent errror on multiple function calls*/
            final Element rootCache = root;
            /*cache the script handler for the same reason*/
            final XScriptHandler handlerCache = handler;

            /*handle the call backs if they are provided*/
            tmp.addResourceObserver(new ResourceObserver() {
                @Override
                public void stateChanged(Resource from) {
                    /*for call back when completed successfully*/
                    if (from.getStatus() == Resource.STATUS.COMPLETED) {
                        /*get the root nodes for the on complete command*/
                        NodeList rootList = rootCache.getElementsByTagName(COMMAND_ON_COMPLETE);

                        /*for every on complete command, send the commands to the interpreter*/
                        for (int i = 0; i < rootList.getLength(); i++) {
                            /*get all the actions of the root node*/
                            NodeList childList = rootList.item(i).getChildNodes();

                            /*interpret only element nodes*/
                            for (int j = 0; j < childList.getLength(); j++)
                                if (childList.item(j).getNodeType() == Node.ELEMENT_NODE)
                                    handlerCache.handleScript(childList.item(j).castToElement(), false);
                        }

                        /*call backs on errors*/
                    } else if (from.getStatus() == Resource.STATUS.ERROR) {
                        /*get the root nodes for the on error command*/
                        NodeList rootList = rootCache.getElementsByTagName(COMMAND_ON_ERROR);

                        /*for every on complete command, send the commands to the interpreter*/
                        for (int i = 0; i < rootList.getLength(); i++) {
                            /*get all the actions of the root node*/
                            NodeList childList = rootList.item(i).getChildNodes();

                            /*interpret only element nodes*/
                            for (int j = 0; j < childList.getLength(); j++)
                                if (childList.item(j).getNodeType() == Node.ELEMENT_NODE)
                                    handlerCache.handleScript(childList.item(j).castToElement(), false);
                        }
                    }
                }
            });
        }


        //--------------------------------------------------------------------------------------------------------------
        //--------------------------------------------------------------------------------------------------------------
    }

    /**
     * Loads the specified content. If the content has been previously loaded that it will be set immediately. Otherwise
     * the content will be loaded asynchronously. If immediate is set to true, then the content will begin loading
     * immediately and won't wait until the next update cycle.
     *
     * @param resource  which content to load.
     * @param immediate if the content should start loading right now
     */
    public void loadResource(Resource resource, boolean immediate);

    /**
     * Releases the content from memory. If immediate is set too true, then the content will be released immediately.
     * If not, then it will be added to a cache, and kept there until the maximum number of cached items are reached.
     *
     * @param resource  which content to release
     * @param immediate if the content should be released immediately. Use this if you know it will not be used anytime
     *                  soon.
     */
    public void releaseResource(Resource resource, boolean immediate);

    /**
     * Returns the number of resources which currently are loading.
     *
     * @return the number of resources which are currently loading.
     */
    public int resourcesLoading();

    /**
     * Updates the loading cycle.
     */
    public void update();

    /**
     * Updates the garbage collection, to remove old cached resources. This function should be called a lot
     */
    public void updateGC();

    /**
     * Sets the maximum elements which can be cached before garbace collector removes them.
     *
     * @param amount the number of cached elements.
     */
    public void setMaximumCachedElements(int amount);
}
