package edu.catalindumitru.bee.math;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 7/4/11
 * Time: 9:58 AM
 * <p/>
 * Specialized class for 3d graohics, which has extra methods from a normal Matrix class
 */
public class Matrix4x4 extends Matrix {
    public static final Matrix4x4 IDENTITY = new Matrix4x4(new float[][]{
            new float[]{1, 0, 0, 0},
            new float[]{0, 1, 0, 0},
            new float[]{0, 0, 1, 0},
            new float[]{0, 0, 0, 1}
    });

    public static final Matrix4x4 ZERO = new Matrix4x4(new float[][]{
            new float[]{0, 0, 0, 0},
            new float[]{0, 0, 0, 0},
            new float[]{0, 0, 0, 0},
            new float[]{0, 0, 0, 0}
    });

    protected float determinant;

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public Matrix4x4() {
        super(4);
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public Matrix4x4(float data[][]) {
        super(4);

        this.set(data);
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public Matrix4x4(Matrix4x4 other) {
        super(other);
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Produces the determinant of the matrix. The result is calculated for every call of this method.
     *
     * @return the determinant
     */
    public float determinant() {
        return elem[0][3] * elem[1][2] * elem[2][1] * elem[3][0] - elem[0][2] * elem[1][3] * elem[2][1] * elem[3][0] -
                elem[0][3] * elem[1][1] * elem[2][2] * elem[3][0] + elem[0][1] * elem[1][3] * elem[2][2] * elem[3][0] +
                elem[0][2] * elem[1][1] * elem[2][3] * elem[3][0] - elem[0][1] * elem[1][2] * elem[2][3] * elem[3][0] -
                elem[0][3] * elem[1][2] * elem[2][0] * elem[3][1] + elem[0][2] * elem[1][3] * elem[2][0] * elem[3][1] +
                elem[0][3] * elem[1][0] * elem[2][2] * elem[3][1] - elem[0][0] * elem[1][3] * elem[2][2] * elem[3][1] -
                elem[0][2] * elem[1][0] * elem[2][3] * elem[3][1] + elem[0][0] * elem[1][2] * elem[2][3] * elem[3][1] +
                elem[0][3] * elem[1][1] * elem[2][0] * elem[3][2] - elem[0][1] * elem[1][3] * elem[2][0] * elem[3][2] -
                elem[0][3] * elem[1][0] * elem[2][1] * elem[3][2] + elem[0][0] * elem[1][3] * elem[2][1] * elem[3][2] +
                elem[0][1] * elem[1][0] * elem[2][3] * elem[3][2] - elem[0][0] * elem[1][1] * elem[2][3] * elem[3][2] -
                elem[0][2] * elem[1][1] * elem[2][0] * elem[3][3] + elem[0][1] * elem[1][2] * elem[2][0] * elem[3][3] +
                elem[0][2] * elem[1][0] * elem[2][1] * elem[3][3] - elem[0][0] * elem[1][2] * elem[2][1] * elem[3][3] -
                elem[0][1] * elem[1][0] * elem[2][2] * elem[3][3] + elem[0][0] * elem[1][1] * elem[2][2] * elem[3][3];
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Produces the inverse of the matrix. The result is not stored in the matrix, but a new one is returned.
     *
     * @return a new matrix representing the inverse if this matrix.
     */
    public Matrix4x4 inverse() {

        /*I know it's but ugly to read, but it's faster this way*/
        return new Matrix4x4(new float[][]{
                new float[]{
                        //------------------------------------------------------------------------------------------------------
                        elem[1][1] * elem[2][2] * elem[3][3] + elem[1][2] * elem[2][3]
                                * elem[3][1] + elem[1][3] * elem[2][1] * elem[3][2]
                                - elem[1][1] * elem[2][3] * elem[3][2] - elem[1][2]
                                * elem[2][1] * elem[3][3] - elem[1][3] * elem[2][2]
                                * elem[3][1],
                        //------------------------------------------------------------------------------------------------------
                        elem[0][1] * elem[2][3] * elem[3][2] + elem[0][2] * elem[2][1]
                                * elem[3][3] + elem[0][3] * elem[2][2] * elem[3][1]
                                - elem[0][1] * elem[2][2] * elem[3][3] - elem[0][2]
                                * elem[2][3] * elem[3][1] - elem[0][3] * elem[2][1]
                                * elem[3][2],
                        //------------------------------------------------------------------------------------------------------
                        elem[0][1] * elem[1][2] * elem[3][3] + elem[0][2] * elem[1][3]
                                * elem[3][1] + elem[0][3] * elem[1][1] * elem[3][2]
                                - elem[0][1] * elem[1][3] * elem[3][2] - elem[0][2]
                                * elem[1][1] * elem[3][3] - elem[0][3] * elem[1][2]
                                * elem[3][1],
                        //------------------------------------------------------------------------------------------------------
                        elem[0][1] * elem[1][3] * elem[2][2] + elem[0][2] * elem[1][1]
                                * elem[2][3] + elem[0][3] * elem[1][2] * elem[2][1]
                                - elem[0][1] * elem[1][2] * elem[2][3] - elem[0][2]
                                * elem[1][3] * elem[2][1] - elem[0][3] * elem[1][1]
                                * elem[2][2]},
                //------------------------------------------------------------------------------------------------------
                new float[]{
                        elem[1][0] * elem[2][3] * elem[3][2] + elem[1][2] * elem[2][0]
                                * elem[3][3] + elem[1][3] * elem[2][2] * elem[3][0]
                                - elem[1][0] * elem[2][2] * elem[3][3] - elem[1][2]
                                * elem[2][3] * elem[3][0] - elem[1][3] * elem[2][0]
                                * elem[3][2],
                        //------------------------------------------------------------------------------------------------------,
                        elem[0][0] * elem[2][2] * elem[3][3] + elem[0][2] * elem[2][3]
                                * elem[3][0] + elem[0][3] * elem[2][0] * elem[3][2]
                                - elem[0][0] * elem[2][3] * elem[3][2] - elem[0][2]
                                * elem[2][0] * elem[3][3] - elem[0][3] * elem[2][2]
                                * elem[3][0],
                        //------------------------------------------------------------------------------------------------------
                        elem[0][0] * elem[1][3] * elem[3][2] + elem[0][2] * elem[1][0]
                                * elem[3][3] + elem[0][3] * elem[1][2] * elem[3][0]
                                - elem[0][0] * elem[1][2] * elem[3][3] - elem[0][2]
                                * elem[1][3] * elem[3][0] - elem[0][3] * elem[1][0]
                                * elem[3][2],
                        //------------------------------------------------------------------------------------------------------
                        elem[0][0] * elem[1][2] * elem[2][3] + elem[0][2] * elem[1][3]
                                * elem[2][0] + elem[0][3] * elem[1][0] * elem[2][2]
                                - elem[0][0] * elem[1][3] * elem[2][2] - elem[0][2]
                                * elem[1][0] * elem[2][3] - elem[0][3] * elem[1][2]
                                * elem[2][0]},
                //------------------------------------------------------------------------------------------------------
                new float[]{
                        elem[1][0] * elem[2][1] * elem[3][3] + elem[1][1] * elem[2][3]
                                * elem[3][0] + elem[1][3] * elem[2][0] * elem[3][1]
                                - elem[1][0] * elem[2][3] * elem[3][1] - elem[1][1]
                                * elem[2][0] * elem[3][3] - elem[1][3] * elem[2][1]
                                * elem[3][0],
                        //------------------------------------------------------------------------------------------------------
                        elem[0][0] * elem[2][3] * elem[3][1] + elem[0][1] * elem[2][0]
                                * elem[3][3] + elem[0][3] * elem[2][1] * elem[3][0]
                                - elem[0][0] * elem[2][1] * elem[3][3] - elem[0][1]
                                * elem[2][3] * elem[3][0] - elem[0][3] * elem[2][0]
                                * elem[3][1],
                        //------------------------------------------------------------------------------------------------------
                        elem[0][0] * elem[1][1] * elem[3][3] + elem[0][1] * elem[1][3]
                                * elem[3][0] + elem[0][3] * elem[1][0] * elem[3][1]
                                - elem[0][0] * elem[1][3] * elem[3][1] - elem[0][1]
                                * elem[1][0] * elem[3][3] - elem[0][3] * elem[1][1]
                                * elem[3][0],
                        //------------------------------------------------------------------------------------------------------
                        elem[0][0] * elem[1][3] * elem[2][1] + elem[0][1] * elem[1][0]
                                * elem[2][3] + elem[0][3] * elem[1][1] * elem[2][0]
                                - elem[0][0] * elem[1][1] * elem[2][3] - elem[0][1]
                                * elem[1][3] * elem[2][0] - elem[0][3] * elem[1][0]
                                * elem[2][1]},
                //------------------------------------------------------------------------------------------------------
                new float[]{
                        elem[1][0] * elem[2][2] * elem[3][1] + elem[1][1] * elem[2][0]
                                * elem[3][2] + elem[1][2] * elem[2][1] * elem[3][0]
                                - elem[1][0] * elem[2][1] * elem[3][2] - elem[1][1]
                                * elem[2][2] * elem[3][0] - elem[1][2] * elem[2][0]
                                * elem[3][1],
                        //------------------------------------------------------------------------------------------------------
                        elem[0][0] * elem[2][1] * elem[3][2] + elem[0][1] * elem[2][2]
                                * elem[3][0] + elem[0][2] * elem[2][0] * elem[3][1]
                                - elem[0][0] * elem[2][2] * elem[3][1] - elem[0][1]
                                * elem[2][0] * elem[3][2] - elem[0][2] * elem[2][1]
                                * elem[3][0],
                        //------------------------------------------------------------------------------------------------------
                        elem[0][0] * elem[1][2] * elem[3][1] + elem[0][1] * elem[1][0]
                                * elem[3][2] + elem[0][2] * elem[1][1] * elem[3][0]
                                - elem[0][0] * elem[1][1] * elem[3][2] - elem[0][1]
                                * elem[1][2] * elem[3][0] - elem[0][2] * elem[1][0]
                                * elem[3][1],
                        //------------------------------------------------------------------------------------------------------
                        elem[0][0] * elem[1][1] * elem[2][2] + elem[0][1] * elem[1][2]
                                * elem[2][0] + elem[0][2] * elem[1][0] * elem[2][1]
                                - elem[0][0] * elem[1][2] * elem[2][1] - elem[0][1]
                                * elem[1][0] * elem[2][2] - elem[0][2] * elem[1][1]
                                * elem[2][0]}});

    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public static Matrix4x4 perspectiveMatrix(float fieldOfView, float aspectRatio, float znear, float zfar) {

        return new Matrix4x4(
                new float[][]{
                        new float[]{(float) (Math.tan(fieldOfView * Math.PI / 180.0) / aspectRatio), 0, 0, 0},
                        new float[]{0, (float) (1 / Math.tan(fieldOfView * Math.PI / 180.0 * Math.PI / 180.0)), 0, 0},
                        new float[]{0, 0, (znear + zfar) / (znear - zfar), 2 * znear * zfar / (znear - zfar), 0},
                        new float[]{0, 0, -1, 0}
                }
        );
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public static Matrix4x4 rotationMatrix(float x, float y, float z) {
        /*return a new matrix constructor for the 3 axis vectors*/
        return new Matrix4x4(new float[][]{
                new float[]{(float) (Math.cos(y) * Math.cos(z)), (float) (-Math.cos(y) * Math.sin(z) + Math.sin(x) * Math.sin(y) * Math.cos(z)),
                        (float) (Math.sin(x) * Math.sin(z) + Math.cos(x) * Math.sin(y) * Math.cos(z)), 0},
                new float[]{(float) (Math.cos(y) * Math.sin(z)), (float) (Math.cos(x) * Math.cos(y) + Math.sin(x) * Math.sin(y) * Math.sin(z)),
                        (float) (-Math.sin(x) * Math.cos(z) + Math.cos(x) * Math.sin(y) * Math.sin(z)), 0},
                new float[]{(float) -Math.sin(y), (float) (Math.sin(x) * Math.cos(y)), (float) (Math.cos(x) * Math.cos(y)), 0},
                new float[]{0, 0, 0, 1}
        });
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public static Matrix4x4 rotationMatrix(Vector axis, float angle) throws ArithmeticException {
        if (axis.size() != 3)
            throw new ArithmeticException("invalid axis specified.");

        /*create a new normalized vector*/
        Vector naxis = axis.clone();
        naxis.normalize();

        /*create the new matrix*/
        return new Matrix4x4(new float[][]{
                new float[]{(float) (Math.cos(angle) + (naxis.get(Vector.X) * naxis.get(Vector.X) * (1 - Math.cos(angle)))),
                        (float) ((naxis.get(Vector.X) * naxis.get(Vector.Y)) * (1 - Math.cos(angle)) - naxis.get(Vector.Z) * Math.sin(angle)),
                        (float) ((naxis.get(Vector.X) * naxis.get(Vector.Z)) * (1 - Math.cos(angle)) - naxis.get(Vector.Y) * Math.sin(angle)), 0},
                new float[]{(float) ((naxis.get(Vector.X) * naxis.get(Vector.Y)) * (1 - Math.cos(angle)) - naxis.get(Vector.Z) * Math.sin(angle)),
                        (float) (Math.cos(angle) + (naxis.get(Vector.Y) * naxis.get(Vector.Y) * (1 - Math.cos(angle)))),
                        (float) ((naxis.get(Vector.Y) * naxis.get(Vector.Z)) * (1 - Math.cos(angle)) - naxis.get(Vector.X) * Math.sin(angle)), 0},
                new float[]{(float) ((naxis.get(Vector.X) * naxis.get(Vector.Z)) * (1 - Math.cos(angle)) - naxis.get(Vector.Y) * Math.sin(angle)),
                        (float) ((naxis.get(Vector.Y) * naxis.get(Vector.Z)) * (1 - Math.cos(angle)) - naxis.get(Vector.X) * Math.sin(angle)),
                        (float) (Math.cos(angle) + (naxis.get(Vector.Z) * naxis.get(Vector.Z) * (1 - Math.cos(angle)))), 0},
                new float[]{0, 0, 0, 1}
        });
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public static Matrix4x4 translationMatrix(float x, float y, float z) {
        return new Matrix4x4(
                new float[][]{
                        new float[]{1, 0, 0, x},
                        new float[]{0, 1, 0, y},
                        new float[]{0, 0, 1, z},
                        new float[]{0, 0, 0, 1}
                }
        );
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public Matrix4x4 clone() {
        return new Matrix4x4(this.elem);
    }

}
