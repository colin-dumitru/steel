package edu.catalindumitru.bee.content;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 8/9/11
 * Time: 1:53 PM
 */

/**
 * Abstract image type which doesn't allow direct manipulation. Due to some security restrictions, we are not allowed
 * to modify or even access the image data. That is why it must be "blindly" handled. The image is created by loading
 * a {@link Resource} using the {@link ResourceProvider} and converting the Object returned by the method
 * {@link edu.catalindumitru.bee.content.Resource#getResource()} to this interface.
 */
public interface ImageResource extends NativeResource {

    /**
     * Returns the height of the unmodified image.
     *
     * @return height of the image
     */
    public int height();

    /**
     * Returns the width of the unmodified image.
     *
     * @return width of the image.
     */
    public int width();

    /**
     * Returns whether or not the image has been fully loaded and converted.
     *
     * @return if the image is complete and ready to use.
     */
    public boolean complete();
}
