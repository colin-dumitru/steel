package edu.catalindumitru.bee.math;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 7/4/11
 * Time: 3:19 PM
 *
 * The difference between this class and the normal matrix is that this one uses cache to calculate complex results,
 * such as the inverse, transpose only once.
 */
public class CachedMatrix4x4 extends Matrix4x4{
    protected static final int C_INVERSE = 0;
    protected static final int C_DETERMINANT = 1;
    protected static final int C_SIZE = 2;

    /*cache is lazy initialized to conserve memory*/
    private Object cache[];

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public CachedMatrix4x4(){
        super();
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public CachedMatrix4x4(float data[][]){
        super(data);
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public CachedMatrix4x4(Matrix4x4 other) {
        super(other);
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    private void resetCache() {
        this.cache = null;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    private Object getCached(int pos) {
        if(this.cache == null)
            this.cache = new Object[C_SIZE];

        if(pos < 0 || pos >= this.cache.length)
            return null;

        return this.cache[pos];
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    private Object setCache(int pos, Object cache) {
        if(this.cache == null)
            this.cache = new Object[C_SIZE];

        if(pos < 0 || pos >= this.cache.length)
            return null;

        return (this.cache[pos] = cache);
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public float determinant(){
        Float res = null;

        /*if the result doesn't exist in the cache, we compute a new one*/
        if((res = (Float)this.getCached(C_DETERMINANT)) != null)
            return res;
        else
            return (Float)this.setCache(C_DETERMINANT, super.determinant());
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public Matrix4x4 inverse(){
        Matrix4x4 res = null;

        /*if the result doesn't exist in the cache, we compute a new one*/
        if((res = (Matrix4x4)this.getCached(C_INVERSE)) != null)
            return res;
        else
            return (Matrix4x4)this.setCache(C_INVERSE, super.inverse());
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public void set(int x, int y, float value) {
        super.set(x, y, value);
        /*if the values of the matrix have changed than the cached values have changed*/
        this.resetCache();
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public void set(float data[][]){
        super.set(data);
        /*if the values of the matrix have changed than the cached values have changed*/
        this.resetCache();
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public void add(Matrix other) throws ArithmeticException{
        super.add(other);
        this.resetCache();
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public void multiply(Matrix other) throws ArithmeticException {
        super.multiply(other);
        this.resetCache();
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
}
