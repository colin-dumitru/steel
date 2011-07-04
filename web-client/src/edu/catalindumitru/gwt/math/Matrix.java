package edu.catalindumitru.gwt.math;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 7/4/11
 * Time: 9:34 AM
 */
public class Matrix {
    protected float elem[][];
    protected int width;
    protected int height;
    /*used by Matrix4v4 to determine if the determinant needs to be reclculated*/

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public Matrix() {
        /*nothing yet fot emypy constructor*/
        this.elem = new float[0][0];
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public Matrix(int width, int height) {
        this.width = width;
        this.height = height;

        this.elem = new float[width][height];
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public Matrix(int size) {
        this(size, size);
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public Matrix(float elem[][]) {
        this.width = elem.length;
        if(this.width > 0)
            this.height = elem[0].length;

        this.elem = new float[this.width][this.height];

        for(int i = 0; i < this.width; i++)
            for(int j = 0; j < this.height; j++)
                this.elem[i][j] = elem[i][j];

    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public Matrix(Matrix other) {
        this(other.elem);
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void set(int x, int y, float value) {
        /*check for index bounds*/
        if((x < 0 || x >= this.width) || (y < 0 || y >= this.height))
            return;

        this.elem[x][y] = value;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public float get(int x, int y){
        if((x < 0 || x >= this.width) || (y < 0 || y >= this.height))
            throw new ArrayIndexOutOfBoundsException();

        return this.elem[x][y];
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void set(float data[][]) {
        /*if the given array is smaller or larger than our array, we take only data that fits into our array*/
        for(int i = 0; i < data.length || i < this.width; i++)
            for (int j = 0; j < data[i].length || j < this.height; j++)
                this.elem[i][j] = data[i][j];
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public float[][] get(){
        return this.elem;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public Matrix clone() {
        return new Matrix(this);
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    /**
     * Computes the transpose of this matrix. The result is not stored locally, but a new Matrix is returned.
     * @return the matrix representing the inverse of this matrix.
     */
    public Matrix transpose() {
        Matrix ret = new Matrix(this.height, this.width);

        for(int i = 0; i < this.width; i++)
            for(int j = 0; j < this.height; j++)
               ret.set(j, i, this.elem[i][j]);

        return ret;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Adds a given matrix to this matrix.
     * @param other the matrix to add.
     * @throws ArithmeticException the exception is thrown if the sizes of the matrices do not match.
     */
    public void add(Matrix other) throws ArithmeticException {
        if((other.width != this.width) || (other.height != this.height))
            throw new ArithmeticException("Matrix dimensions do not match");

        for(int i = 0; i < this.width; i++)
            for (int j = 0; j < this.height; j++)
                this.elem[i][j] += other.elem[i][j];
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Multiplies the given matrix with the current one.
     * @param other the matrix to be multiplied with.
     * @throws ArithmeticException if the sizes of the two matrices do not match, this exception is thrown.
     */
    public void multiply(Matrix other) throws ArithmeticException {
        if(this.width != this.height)
            throw new ArithmeticException("Incompatible matrices for multiplication");

        Matrix ret = new Matrix(other.width, this.height);

        for (int i = 0; i < other.width; i++) {
            for (int j = 0; j < this.height; j++) {
                for (int k = 0; k < this.width; k++) {
                    ret.elem[j][i] += (this.elem[j][k] * other.elem[k][i]);
                }
            }
        }

        /*instead of returning the computed matrix, we save the result as out own*/
        this.elem = ret.elem;
        this.width = ret.width;
        this.height = ret.height;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
}
