package edu.catalindumitru.bee.gui;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 8/13/11
 * Time: 10:09 AM
 */
public class AbsoluteLayout extends Layout {
    public static class Creator implements Layout.LayoutCreator {
        @Override
        public Layout create() {
            return new AbsoluteLayout();
        }

        @Override
        public String getName() {
            return NAME;
        }
    }

    public static final String NAME = "absolute";

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public AbsoluteLayout() {

    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Given the root widget and the widgets to pack, this method calculates each widgets dimensions based on the layout
     * type, root widgets dimensions, order and widgets position preferences.
     */
    public void pack() {
        int width = (int) this.root.getBounds().getWidth();
        int height = (int) this.root.getBounds().getHeight();

        int baseX = (int) this.root.getBounds().getX();
        int baseY = (int) this.root.getBounds().getY();

        int finalX = 0;
        int finalY = 0;

        for (Widget child : this.children) {
            switch (child.getHorizontalAlign()) {

                case LEFT:
                    finalX = baseX + child.getHorizontalOffset();
                    break;
                case CENTER:
                    finalX = baseX + (int) (((width / 2) - (child.getBounds().getWidth() / 2)) + child.getHorizontalOffset());
                    break;
                case RIGHT:
                    finalX = baseX + (int) (width - child.bounds.getWidth()) + child.getHorizontalOffset();
                    break;
            }

            switch (child.getVerticalAlign()) {

                case TOP:
                    finalY = baseY + child.getVerticalOffset();
                    break;
                case CENTER:
                    finalY = baseY + (int) (((height / 2) - (child.getBounds().getHeight() / 2)) + child.getVerticalOffset());
                    break;
                case BOTTOM:
                    finalY = baseY + (int) (height - child.bounds.getHeight()) + child.getVerticalOffset();
                    break;
            }

            child.setBounds(finalX, finalY);
        }


    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    @Override
    public String getName() {
        return NAME;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
}
