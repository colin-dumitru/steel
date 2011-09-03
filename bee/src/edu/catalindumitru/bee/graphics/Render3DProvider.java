package edu.catalindumitru.bee.graphics;

import edu.catalindumitru.bee.content.ImageResource;
import edu.catalindumitru.bee.core.NativeArray;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: 8/29/11
 * Time: 11:05 AM
 */
public interface Render3DProvider {
    /* What buffer to clear. Can use a combination of each one.*/
    final int DEPTH_BUFFER_BIT = 0x00000100;
    final int STENCIL_BUFFER_BIT = 0x00000400;
    final int COLOR_BUFFER_BIT = 0x00004000;

    /* BeginMode */
    final int POINTS = 0x0000;
    final int LINES = 0x0001;
    final int LINE_LOOP = 0x0002;
    final int LINE_STRIP = 0x0003;
    final int TRIANGLES = 0x0004;
    final int TRIANGLE_STRIP = 0x0005;
    final int TRIANGLE_FAN = 0x0006;

    /* BlendingFactorDest */
    final int ZERO = 0;
    final int ONE = 1;
    final int SRC_COLOR = 0x0300;
    final int ONE_MINUS_SRC_COLOR = 0x0301;
    final int SRC_ALPHA = 0x0302;
    final int ONE_MINUS_SRC_ALPHA = 0x0303;
    final int DST_ALPHA = 0x0304;
    final int ONE_MINUS_DST_ALPHA = 0x0305;

    /* BlendingFactorSrc */
    /*      ZERO */
    /*      ONE */
    final int DST_COLOR = 0x0306;
    final int ONE_MINUS_DST_COLOR = 0x0307;
    final int SRC_ALPHA_SATURATE = 0x0308;
    /*      SRC_ALPHA */
    /*      ONE_MINUS_SRC_ALPHA */
    /*      DST_ALPHA */
    /*      ONE_MINUS_DST_ALPHA */

    /* BlendEquationSeparate */
    final int FUNC_ADD = 0x8006;
    final int BLEND_EQUATION = 0x8009;
    final int BLEND_EQUATION_RGB = 0x8009;   /* same as BLEND_EQUATION */
    final int BLEND_EQUATION_ALPHA = 0x883D;

    /* BlendSubtract */
    final int FUNC_SUBTRACT = 0x800A;
    final int FUNC_REVERSE_SUBTRACT = 0x800B;

    /* Separate Blend Functions */
    final int BLEND_DST_RGB = 0x80C8;
    final int BLEND_SRC_RGB = 0x80C9;
    final int BLEND_DST_ALPHA = 0x80CA;
    final int BLEND_SRC_ALPHA = 0x80CB;
    final int CONSTANT_COLOR = 0x8001;
    final int ONE_MINUS_CONSTANT_COLOR = 0x8002;
    final int CONSTANT_ALPHA = 0x8003;
    final int ONE_MINUS_CONSTANT_ALPHA = 0x8004;
    final int BLEND_COLOR = 0x8005;

    /* Buffer Objects */
    final int ARRAY_BUFFER = 0x8892;
    final int ELEMENT_ARRAY_BUFFER = 0x8893;
    final int ARRAY_BUFFER_BINDING = 0x8894;
    final int ELEMENT_ARRAY_BUFFER_BINDING = 0x8895;

    final int STREAM_DRAW = 0x88E0;
    final int STATIC_DRAW = 0x88E4;
    final int DYNAMIC_DRAW = 0x88E8;

    final int BUFFER_SIZE = 0x8764;
    final int BUFFER_USAGE = 0x8765;

    final int CURRENT_VERTEX_ATTRIBUTE = 0x8626;

    /* CullFaceMode */
    final int FRONT = 0x0404;
    final int BACK = 0x0405;
    final int FRONT_AND_BACK = 0x0408;

    /* TEXTURE_2D */
    final int CULL_FACE = 0x0B44;
    final int BLEND = 0x0BE2;
    final int DITHER = 0x0BD0;
    final int STENCIL_TEST = 0x0B90;
    final int DEPTH_TEST = 0x0B71;
    final int SCISSOR_TEST = 0x0C11;
    final int POLYGON_OFFSET_FILL = 0x8037;
    final int SAMPLE_ALPHA_TO_COVERAGE = 0x809E;
    final int SAMPLE_COVERAGE = 0x80A0;

    /* ErrorCode */
    final int NO_ERROR = 0;
    final int INVALID_ENUM = 0x0500;
    final int INVALID_VALUE = 0x0501;
    final int INVALID_OPERATION = 0x0502;
    final int OUT_OF_MEMORY = 0x0505;

    /* FrontFaceDirection */
    final int CW = 0x0900;
    final int CCW = 0x0901;

    /* GetPName */
    final int LINE_WIDTH = 0x0B21;
    final int ALIASED_POINT_SIZE_RANGE = 0x846D;
    final int ALIASED_LINE_WIDTH_RANGE = 0x846E;
    final int CULL_FACE_MODE = 0x0B45;
    final int FRONT_FACE = 0x0B46;
    final int DEPTH_RANGE = 0x0B70;
    final int DEPTH_WRITEMASK = 0x0B72;
    final int DEPTH_CLEAR_VALUE = 0x0B73;
    final int DEPTH_FUNC = 0x0B74;
    final int STENCIL_CLEAR_VALUE = 0x0B91;
    final int STENCIL_FUNC = 0x0B92;
    final int STENCIL_FAIL = 0x0B94;
    final int STENCIL_PASS_DEPTH_FAIL = 0x0B95;
    final int STENCIL_PASS_DEPTH_PASS = 0x0B96;
    final int STENCIL_REF = 0x0B97;
    final int STENCIL_VALUE_MASK = 0x0B93;
    final int STENCIL_WRITEMASK = 0x0B98;
    final int STENCIL_BACK_FUNC = 0x8800;
    final int STENCIL_BACK_FAIL = 0x8801;
    final int STENCIL_BACK_PASS_DEPTH_FAIL = 0x8802;
    final int STENCIL_BACK_PASS_DEPTH_PASS = 0x8803;
    final int STENCIL_BACK_REF = 0x8CA3;
    final int STENCIL_BACK_VALUE_MASK = 0x8CA4;
    final int STENCIL_BACK_WRITEMASK = 0x8CA5;
    final int VIEWPORT = 0x0BA2;
    final int SCISSOR_BOX = 0x0C10;

    /*      SCISSOR_TEST */
    final int COLOR_CLEAR_VALUE = 0x0C22;
    final int COLOR_WRITEMASK = 0x0C23;
    final int UNPACK_ALIGNMENT = 0x0CF5;
    final int PACK_ALIGNMENT = 0x0D05;
    final int MAX_TEXTURE_SIZE = 0x0D33;
    final int MAX_VIEWPORT_DIMS = 0x0D3A;
    final int SUBPIXEL_BITS = 0x0D50;
    final int RED_BITS = 0x0D52;
    final int GREEN_BITS = 0x0D53;
    final int BLUE_BITS = 0x0D54;
    final int ALPHA_BITS = 0x0D55;
    final int DEPTH_BITS = 0x0D56;
    final int STENCIL_BITS = 0x0D57;
    final int POLYGON_OFFSET_UNITS = 0x2A00;

    /*      POLYGON_OFFSET_FILL */
    final int POLYGON_OFFSET_FACTOR = 0x8038;
    final int TEXTURE_BINDING_2D = 0x8069;
    final int SAMPLE_BUFFERS = 0x80A8;
    final int SAMPLES = 0x80A9;
    final int SAMPLE_COVERAGE_VALUE = 0x80AA;
    final int SAMPLE_COVERAGE_INVERT = 0x80AB;


    final int NUM_COMPRESSED_TEXTURE_FORMATS = 0x86A2;
    final int COMPRESSED_TEXTURE_FORMATS = 0x86A3;

    /* HintMode */
    final int DONT_CARE = 0x1100;
    final int FASTEST = 0x1101;
    final int NICEST = 0x1102;

    /* HintTarget */
    final int GENERATE_MIPMAP_HINT = 0x8192;

    /* DataType */
    final int BYTE = 0x1400;
    final int UNSIGNED_BYTE = 0x1401;
    final int SHORT = 0x1402;
    final int UNSIGNED_SHORT = 0x1403;
    final int INT = 0x1404;
    final int UNSIGNED_INT = 0x1405;
    final int FLOAT = 0x1406;

    /* PixelFormat */
    final int DEPTH_COMPONENT = 0x1902;
    final int ALPHA = 0x1906;
    final int RGB = 0x1907;
    final int RGBA = 0x1908;
    final int LUMINANCE = 0x1909;
    final int LUMINANCE_ALPHA = 0x190A;

    /* PixelType */
    /*      UNSIGNED_BYTE */
    final int UNSIGNED_SHORT_4_4_4_4 = 0x8033;
    final int UNSIGNED_SHORT_5_5_5_1 = 0x8034;
    final int UNSIGNED_SHORT_5_6_5 = 0x8363;

    /* Shaders */
    final int FRAGMENT_SHADER = 0x8B30;
    final int VERTEX_SHADER = 0x8B31;
    final int MAX_VERTEX_ATTRIBS = 0x8869;
    final int MAX_VERTEX_UNIFORM_VECTORS = 0x8DFB;
    final int MAX_VARYING_VECTORS = 0x8DFC;
    final int MAX_COMBINED_TEXTURE_IMAGE_UNITS = 0x8B4D;
    final int MAX_VERTEX_TEXTURE_IMAGE_UNITS = 0x8B4C;
    final int MAX_TEXTURE_IMAGE_UNITS = 0x8872;
    final int MAX_FRAGMENT_UNIFORM_VECTORS = 0x8DFD;
    final int SHADER_TYPE = 0x8B4F;
    final int DELETE_STATUS = 0x8B80;
    final int LINK_STATUS = 0x8B82;
    final int VALIDATE_STATUS = 0x8B83;
    final int ATTACHED_SHADERS = 0x8B85;
    final int ACTIVE_UNIFORMS = 0x8B86;
    final int ACTIVE_ATTRIBUTES = 0x8B89;
    final int SHADING_LANGUAGE_VERSION = 0x8B8C;
    final int CURRENT_PROGRAM = 0x8B8D;

    /* StencilFunction */
    final int NEVER = 0x0200;
    final int LESS = 0x0201;
    final int EQUAL = 0x0202;
    final int LEQUAL = 0x0203;
    final int GREATER = 0x0204;
    final int NOTEQUAL = 0x0205;
    final int GEQUAL = 0x0206;
    final int ALWAYS = 0x0207;

    /* StencilOp */
    /*      ZERO */
    final int KEEP = 0x1E00;
    final int REPLACE = 0x1E01;
    final int INCR = 0x1E02;
    final int DECR = 0x1E03;
    final int INVERT = 0x150A;
    final int INCR_WRAP = 0x8507;
    final int DECR_WRAP = 0x8508;

    /* StringName */
    final int VENDOR = 0x1F00;
    final int RENDERER = 0x1F01;
    final int VERSION = 0x1F02;

    /* TextureMagFilter */
    final int NEAREST = 0x2600;
    final int LINEAR = 0x2601;

    /* TextureMinFilter */
    /*      NEAREST */
    /*      LINEAR */
    final int NEAREST_MIPMAP_NEAREST = 0x2700;
    final int LINEAR_MIPMAP_NEAREST = 0x2701;
    final int NEAREST_MIPMAP_LINEAR = 0x2702;
    final int LINEAR_MIPMAP_LINEAR = 0x2703;

    /* TextureParameterName */
    final int TEXTURE_MAG_FILTER = 0x2800;
    final int TEXTURE_MIN_FILTER = 0x2801;
    final int TEXTURE_WRAP_S = 0x2802;
    final int TEXTURE_WRAP_T = 0x2803;

    /* TextureTarget */
    final int TEXTURE_2D = 0x0DE1;
    final int TEXTURE = 0x1702;

    final int TEXTURE_CUBE_MAP = 0x8513;
    final int TEXTURE_BINDING_CUBE_MAP = 0x8514;
    final int TEXTURE_CUBE_MAP_POSITIVE_X = 0x8515;
    final int TEXTURE_CUBE_MAP_NEGATIVE_X = 0x8516;
    final int TEXTURE_CUBE_MAP_POSITIVE_Y = 0x8517;
    final int TEXTURE_CUBE_MAP_NEGATIVE_Y = 0x8518;
    final int TEXTURE_CUBE_MAP_POSITIVE_Z = 0x8519;
    final int TEXTURE_CUBE_MAP_NEGATIVE_Z = 0x851A;
    final int MAX_CUBE_MAP_TEXTURE_SIZE = 0x851C;

    /* TextureUnit */
    final int TEXTURE0 = 0x84C0;
    final int TEXTURE1 = 0x84C1;
    final int TEXTURE2 = 0x84C2;
    final int TEXTURE3 = 0x84C3;
    final int TEXTURE4 = 0x84C4;
    final int TEXTURE5 = 0x84C5;
    final int TEXTURE6 = 0x84C6;
    final int TEXTURE7 = 0x84C7;
    final int TEXTURE8 = 0x84C8;
    final int TEXTURE9 = 0x84C9;
    final int TEXTURE10 = 0x84CA;
    final int TEXTURE11 = 0x84CB;
    final int TEXTURE12 = 0x84CC;
    final int TEXTURE13 = 0x84CD;
    final int TEXTURE14 = 0x84CE;
    final int TEXTURE15 = 0x84CF;
    final int TEXTURE16 = 0x84D0;
    final int TEXTURE17 = 0x84D1;
    final int TEXTURE18 = 0x84D2;
    final int TEXTURE19 = 0x84D3;
    final int TEXTURE20 = 0x84D4;
    final int TEXTURE21 = 0x84D5;
    final int TEXTURE22 = 0x84D6;
    final int TEXTURE23 = 0x84D7;
    final int TEXTURE24 = 0x84D8;
    final int TEXTURE25 = 0x84D9;
    final int TEXTURE26 = 0x84DA;
    final int TEXTURE27 = 0x84DB;
    final int TEXTURE28 = 0x84DC;
    final int TEXTURE29 = 0x84DD;
    final int TEXTURE30 = 0x84DE;
    final int TEXTURE31 = 0x84DF;
    final int ACTIVE_TEXTURE = 0x84E0;

    /* TextureWrapMode */
    final int REPEAT = 0x2901;
    final int CLAMP_TO_EDGE = 0x812F;
    final int MIRRORED_REPEAT = 0x8370;

    /* Uniform Types */
    final int FLOAT_VEC2 = 0x8B50;
    final int FLOAT_VEC3 = 0x8B51;
    final int FLOAT_VEC4 = 0x8B52;
    final int INT_VEC2 = 0x8B53;
    final int INT_VEC3 = 0x8B54;
    final int INT_VEC4 = 0x8B55;
    final int BOOL = 0x8B56;
    final int BOOL_VEC2 = 0x8B57;
    final int BOOL_VEC3 = 0x8B58;
    final int BOOL_VEC4 = 0x8B59;
    final int FLOAT_MAT2 = 0x8B5A;
    final int FLOAT_MAT3 = 0x8B5B;
    final int FLOAT_MAT4 = 0x8B5C;
    final int SAMPLER_2D = 0x8B5E;
    final int SAMPLER_CUBE = 0x8B60;

    /* Vertex Arrays */
    final int VERTEX_ATTRIB_ARRAY_ENABLED = 0x8622;
    final int VERTEX_ATTRIB_ARRAY_SIZE = 0x8623;
    final int VERTEX_ATTRIB_ARRAY_STRIDE = 0x8624;
    final int VERTEX_ATTRIB_ARRAY_TYPE = 0x8625;
    final int VERTEX_ATTRIB_ARRAY_NORMALIZED = 0x886A;
    final int VERTEX_ATTRIB_ARRAY_POINTER = 0x8645;
    final int VERTEX_ATTRIB_ARRAY_BUFFER_BINDING = 0x889F;

    /* Shader Source */
    final int COMPILE_STATUS = 0x8B81;

    /* Shader Precision-Specified Types */
    final int LOW_FLOAT = 0x8DF0;
    final int MEDIUM_FLOAT = 0x8DF1;
    final int HIGH_FLOAT = 0x8DF2;
    final int LOW_INT = 0x8DF3;
    final int MEDIUM_INT = 0x8DF4;
    final int HIGH_INT = 0x8DF5;

    /* Framebuffer Object. */
    final int FRAMEBUFFER = 0x8D40;
    final int RENDERBUFFER = 0x8D41;

    final int RGBA4 = 0x8056;
    final int RGB5_A1 = 0x8057;
    final int RGB565 = 0x8D62;
    final int DEPTH_COMPONENT16 = 0x81A5;
    final int STENCIL_INDEX = 0x1901;
    final int STENCIL_INDEX8 = 0x8D48;
    final int DEPTH_STENCIL = 0x84F9;

    final int RENDERBUFFER_WIDTH = 0x8D42;
    final int RENDERBUFFER_HEIGHT = 0x8D43;
    final int RENDERBUFFER_INTERNAL_FORMAT = 0x8D44;
    final int RENDERBUFFER_RED_SIZE = 0x8D50;
    final int RENDERBUFFER_GREEN_SIZE = 0x8D51;
    final int RENDERBUFFER_BLUE_SIZE = 0x8D52;
    final int RENDERBUFFER_ALPHA_SIZE = 0x8D53;
    final int RENDERBUFFER_DEPTH_SIZE = 0x8D54;
    final int RENDERBUFFER_STENCIL_SIZE = 0x8D55;

    final int FRAMEBUFFER_ATTACHMENT_OBJECT_TYPE = 0x8CD0;
    final int FRAMEBUFFER_ATTACHMENT_OBJECT_NAME = 0x8CD1;
    final int FRAMEBUFFER_ATTACHMENT_TEXTURE_LEVEL = 0x8CD2;
    final int FRAMEBUFFER_ATTACHMENT_TEXTURE_CUBE_MAP_FACE = 0x8CD3;

    final int COLOR_ATTACHMENT0 = 0x8CE0;
    final int DEPTH_ATTACHMENT = 0x8D00;
    final int STENCIL_ATTACHMENT = 0x8D20;
    final int DEPTH_STENCIL_ATTACHMENT = 0x821A;

    final int NONE = 0;

    final int FRAMEBUFFER_COMPLETE = 0x8CD5;
    final int FRAMEBUFFER_INCOMPLETE_ATTACHMENT = 0x8CD6;
    final int FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT = 0x8CD7;
    final int FRAMEBUFFER_INCOMPLETE_DIMENSIONS = 0x8CD9;
    final int FRAMEBUFFER_UNSUPPORTED = 0x8CDD;

    final int FRAMEBUFFER_BINDING = 0x8CA6;
    final int RENDERBUFFER_BINDING = 0x8CA7;
    final int MAX_RENDERBUFFER_SIZE = 0x84E8;

    final int INVALID_FRAMEBUFFER_OPERATION = 0x0506;

    /* Type of texture unpacking. */
    final int UNPACK_FLIP_Y = 0x9240;
    final int UNPACK_PREMULTIPLY_ALPHA = 0x9241;
    final int CONTEXT_LOST = 0x9242;
    final int UNPACK_COLORSPACE_CONVERSION = 0x9243;

    /* ---------------------------------------Core Functions-----------------------------------------------------------*/

    /**
     * Returns the visible screen width in pixels.
     *
     * @return the screen's width in pixels.
     */
    public int getScreenWidth();

    /**
     * Returns the visible screen height in pixels.
     *
     * @return the visible screen height in pixels.
     */
    public int getScreenHeight();

    /**
     * Sets the blending color for future drawings. The colors range from 0 to 1.
     *
     * @param color the new color to use for blending.
     */
    public void blendColor(Color color);

    /**
     * Sets the blend equations to use for future drawings.
     *
     * @param mode the mode of the blending. It can be one of the fallowing : {@link Render3DProvider#FUNC_ADD},
     *             {@link Render3DProvider#FUNC_SUBTRACT} or {@link Render3DProvider#FUNC_REVERSE_SUBTRACT}.
     */
    public void blendEquation(int mode);

    /**
     * Sets a separate equation for blending for both color information and alpha information. As for
     * {@see Render3DProvider#blendEquation} the modes can be one of the fallowing : {@link Render3DProvider#FUNC_ADD},
     * {@link Render3DProvider#FUNC_SUBTRACT} or {@link Render3DProvider#FUNC_REVERSE_SUBTRACT}.
     *
     * @param modeRGB   the mode for the rgb equation.
     * @param modeAlpha the mode for the alpha equation
     */
    public void blendEquationSeparate(int modeRGB, int modeAlpha);

    /**
     * Specify per pixel arithmetic.
     *
     * @param sourceFactor      specify how the RGBA source components are computed. Can ve one of the fallowing :
     *                          {@link Render3DProvider#ZERO},
     *                          {@link Render3DProvider#ONE},
     *                          {@link Render3DProvider#SRC_COLOR},
     *                          {@link Render3DProvider#ONE_MINUS_SRC_COLOR},
     *                          {@link Render3DProvider#DST_COLOR},
     *                          {@link Render3DProvider#ONE_MINUS_DST_COLOR},
     *                          {@link Render3DProvider#SRC_ALPHA},
     *                          {@link Render3DProvider#ONE_MINUS_SRC_ALPHA},
     *                          {@link Render3DProvider#DST_ALPHA},
     *                          {@link Render3DProvider#ONE_MINUS_DST_ALPHA},
     *                          {@link Render3DProvider#CONSTANT_COLOR},
     *                          {@link Render3DProvider#ONE_MINUS_CONSTANT_COLOR},
     *                          {@link Render3DProvider#CONSTANT_ALPHA},
     *                          {@link Render3DProvider#ONE_MINUS_CONSTANT_ALPHA},
     *                          {@link Render3DProvider#CONSTANT_COLOR}
     * @param destinationFactor specify how the RGBA destination components are computed. Can ve one of the fallowing :
     *                          {@link Render3DProvider#ZERO},
     *                          {@link Render3DProvider#ONE},
     *                          {@link Render3DProvider#SRC_COLOR},
     *                          {@link Render3DProvider#ONE_MINUS_SRC_COLOR},
     *                          {@link Render3DProvider#DST_COLOR},
     *                          {@link Render3DProvider#ONE_MINUS_DST_COLOR},
     *                          {@link Render3DProvider#SRC_ALPHA},
     *                          {@link Render3DProvider#ONE_MINUS_SRC_ALPHA},
     *                          {@link Render3DProvider#DST_ALPHA},
     *                          {@link Render3DProvider#ONE_MINUS_DST_ALPHA},
     *                          {@link Render3DProvider#CONSTANT_COLOR},
     *                          {@link Render3DProvider#ONE_MINUS_CONSTANT_COLOR},
     *                          {@link Render3DProvider#CONSTANT_ALPHA},
     *                          {@link Render3DProvider#ONE_MINUS_CONSTANT_ALPHA},
     *                          {@link Render3DProvider#CONSTANT_COLOR}
     */
    public void blendFunction(int sourceFactor, int destinationFactor);

    /**
     * Specify per pixel arithmetic, separate for each component (RGB and alpha). See {@see Render3DProvider#blendFunc}
     *
     * @param sourceRGB        source RGB function.
     * @param destinationRGB   destination RGB function.
     * @param sourceAlpha      source alpha function.
     * @param destinationAlpha destination alpha function.
     */
    public void blendFunctionSeparate(int sourceRGB, int destinationRGB, int sourceAlpha, int destinationAlpha);

    /**
     * Clears the buffers (color, depth and / or stencil buffers) to a preset values.
     *
     * @param mask bitwise or comprised of one of the fallowing : {@link Render3DProvider#COLOR_BUFFER_BIT},
     *             {@link Render3DProvider#DEPTH_BUFFER_BIT} and / or {@link Render3DProvider#STENCIL_BUFFER_BIT}
     */
    public void clear(int mask);

    /**
     * Sets the color to be used when clearing the color buffer.
     *
     * @param color the color to be used when clearing the color buffer.
     */
    public void clearColor(Color color);

    /**
     * Sets the default depth value to be used when clearing the depth buffer.
     *
     * @param depth the default depth value.
     */
    public void clearDepth(float depth);

    /**
     * Sets the default value to be used when clearing the stencil buffer.
     *
     * @param s the default value of the setncil buffer.
     */
    public void clearStencil(int s);

    /**
     * Sets which colors will be written to the color buffer.
     *
     * @param red   if the red component will be written to the color buffer.
     * @param green if the green component will be written to the color buffer.
     * @param blue  if the blue component will be written to the color buffer.
     * @param alpha if the alpha component will be written to the color buffer.
     */
    public void colorMask(boolean red, boolean green, boolean blue, boolean alpha);

    /**
     * Specify the type of culling should be done.
     *
     * @param mode the type of culling. Must be one of the fallowing : {@link Render3DProvider#FRONT},
     *             {@link Render3DProvider#BACK} or {@link Render3DProvider#FRONT_AND_BACK}.
     */
    public void cullFace(int mode);

    /**
     * Specifies the function to use when comparing depth values.
     *
     * @param func the function to use when comparing depth values. Must be one of the fallowing constants :
     *             {@link Render3DProvider#NEVER}, {@link Render3DProvider#EQUAL}, {@link Render3DProvider#LESS},
     *             {@link Render3DProvider#LEQUAL}, {@link Render3DProvider#GREATER}, {@link Render3DProvider#GEQUAL},
     *             {@link Render3DProvider#ALWAYS} or {@link Render3DProvider#NOTEQUAL}
     */
    public void depthFunc(int func);

    /**
     * Enables or disables writing to the depth buffer.
     *
     * @param flag true to enable writing to the depth buffer, false otherwise.
     */
    public void depthMask(boolean flag);

    /**
     * Specifies the depth range in which writing to the depth buffer should occur.
     *
     * @param zNear the near value of the depth range. Anything bellow this value will not be written.
     * @param zFar  the far value of the depth range. Anything above this value will not be written.
     */
    public void depthRange(float zNear, float zFar);

    /**
     * Disables components used by the render provider. The initial value for all components except for
     * {@link Render3DProvider#DITHER} is set to false.
     *
     * @param component the component to disable. Must be one of the fallowing :
     *                  {@link Render3DProvider#DITHER},
     *                  {@link Render3DProvider#BLEND},
     *                  {@link Render3DProvider#CULL_FACE},
     *                  {@link Render3DProvider#DEPTH_TEST},
     *                  {@link Render3DProvider#POLYGON_OFFSET_FILL},
     *                  {@link Render3DProvider#SAMPLE_ALPHA_TO_COVERAGE},
     *                  {@link Render3DProvider#SAMPLE_COVERAGE},
     *                  {@link Render3DProvider#SCISSOR_TEST} or
     *                  {@link Render3DProvider#STENCIL_TEST}.
     */
    public void disable(int component);

    /**
     * Enables components used by the render provider. The initial value for all components except for
     * {@link Render3DProvider#DITHER} is set to false.
     *
     * @param component the component to enable. Must be one of the fallowing :
     *                  {@link Render3DProvider#DITHER},
     *                  {@link Render3DProvider#BLEND},
     *                  {@link Render3DProvider#CULL_FACE},
     *                  {@link Render3DProvider#DEPTH_TEST},
     *                  {@link Render3DProvider#POLYGON_OFFSET_FILL},
     *                  {@link Render3DProvider#SAMPLE_ALPHA_TO_COVERAGE},
     *                  {@link Render3DProvider#SAMPLE_COVERAGE},
     *                  {@link Render3DProvider#SCISSOR_TEST} or
     *                  {@link Render3DProvider#STENCIL_TEST}.
     */
    public void enable(int component);

    /**
     * Blocks until execution is complete.
     */
    public void finish();

    /**
     * Forces execution of all queued commands.
     */
    public void flush();

    /**
     * Retrieves the current error flag.
     *
     * @return the current error flag. Could be one of the fallowing constants : {@link Render3DProvider#NO_ERROR},
     *         {@link Render3DProvider#INVALID_ENUM}, {@link Render3DProvider#INVALID_FRAMEBUFFER_OPERATION},
     *         {@link Render3DProvider#INVALID_OPERATION}, {@link Render3DProvider#INVALID_VALUE} or
     *         {@link Render3DProvider#OUT_OF_MEMORY}.
     */
    public int getError();

    /**
     * Specifies the desired implementation for the given component.
     *
     * @param target the target component. Must be one of the fallowing constants :
     *               {@link Render3DProvider#GENERATE_MIPMAP_HINT}.
     * @param mode   the mode for the component. Must be one of the fallowing constants :
     *               {@link Render3DProvider#FASTEST}, {@link Render3DProvider#NICEST}
     *               or {@link Render3DProvider#DONT_CARE}.
     */
    public void hint(int target, int mode);

    /**
     * Determines if a component is enabled or disabled (a component can have it's state switch by using the fallowing
     * two function calls : {@link Render3DProvider#enable(int)} or {@link Render3DProvider#disable(int)}).
     *
     * @param cap the component to test it's state. Must be one of the fallowing constants :
     *            {@link Render3DProvider#DITHER},
     *            {@link Render3DProvider#BLEND},
     *            {@link Render3DProvider#CULL_FACE},
     *            {@link Render3DProvider#DEPTH_TEST},
     *            {@link Render3DProvider#POLYGON_OFFSET_FILL},
     *            {@link Render3DProvider#SAMPLE_ALPHA_TO_COVERAGE},
     *            {@link Render3DProvider#SAMPLE_COVERAGE},
     *            {@link Render3DProvider#SCISSOR_TEST}
     *            or {@link Render3DProvider#STENCIL_TEST}.
     * @return if the given component is enabled.
     */
    public boolean isEnabled(int cap);

    /**
     * Specifies the pixel storage mode.
     *
     * @param pName the parameter to be set. Must be one of the fallowing constants :
     *              {@link Render3DProvider#PACK_ALIGNMENT} or {@link Render3DProvider#UNPACK_ALIGNMENT}.
     * @param param the value of the parameter to set to.
     */
    public void pixelStore(int pName, int param);

    /**
     * Specifies the scale factor and units when used to calculate depth values.
     *
     * @param factor the scale factor used when calculating depth values. Initial value is 0;
     * @param offset the offset value used when calculating depth values. Initial value is 0.
     */
    public void polygonOffset(float factor, float offset);

    /**
     * Specifies the multisample coverage.
     *
     * @param value  the sample coverage value. The value is clamped between 0 and 1, where the initial value is 1.
     * @param invert if the mask should be inverted..
     */
    public void sampleCoverage(float value, boolean invert);

    /**
     * Defines a scissor box.
     *
     * @param x      the x coordinate for the lower left corner of the rectangle defining the scissor box.
     * @param y      the y coordinate for the lower left corner of the rectangle defining the scissor box.
     * @param width  the width of the rectangle defining the scissor box.
     * @param height the height of the rectangle defining the scissor box.
     */
    public void scissor(int x, int y, int width, int height);

    /**
     * Specifies the stencil function.
     *
     * @param func the stencil test function. Must be one of the fallowing constants :
     *             {@link Render3DProvider#NEVER}, {@link Render3DProvider#LESS},
     *             {@link Render3DProvider#LEQUAL}, {@link Render3DProvider#GREATER},
     *             {@link Render3DProvider#GEQUAL},  {@link Render3DProvider#EQUAL},
     *             {@link Render3DProvider#NOTEQUAL} or  {@link Render3DProvider#ALWAYS}.
     * @param ref  the reference value for the stencil test function. The values are clamped to values :
     *             0 2 ... n -1 , where n is the number of bitplanes in the stencil buffer.
     * @param mask specify the mask value. The final value will be a result of AND operations between the mask,
     *             reference value and stored stencil value.
     */
    public void stencilFunction(int func, int ref, int mask);

    /**
     * Sets front and / or back function for stencil test function.
     *
     * @param face which side to use (front and / or back). Must be one of the fallowing constats :
     *             {@link Render3DProvider#FRONT}, * {@link Render3DProvider#BACK}
     *             or * {@link Render3DProvider#FRONT_AND_BACK}.
     * @param func specifies the stencil test function.  Must be one of the fallowing constants :
     *             {@link Render3DProvider#NEVER}, {@link Render3DProvider#LESS},
     *             {@link Render3DProvider#LEQUAL}, {@link Render3DProvider#GREATER},
     *             {@link Render3DProvider#GEQUAL},  {@link Render3DProvider#EQUAL},
     *             {@link Render3DProvider#NOTEQUAL} or  {@link Render3DProvider#ALWAYS}.
     * @param ref  the reference value for the stencil test function. The values are clamped to values :
     *             0 2 ... n -1 , where n is the number of bitplanes in the stencil buffer.
     * @param mask specify the mask value. The final value will be a result of AND operations between the mask,
     *             reference value and stored stencil value.
     */
    public void stencilFuncSeparate(int face, int func, int ref, int mask);

    /**
     * Specifies the mask value used for the stencil test function.
     *
     * @param mask specify the mask value. The final value will be a result of AND operations between the mask,
     *             reference value and stored stencil value.
     */
    public void stencilMask(int mask);

    /**
     * Specifies a different mask to be used for font and / or back stencil test functions.
     *
     * @param face which side to specify the mask. Must be one of the fallowing constants :
     *             {@Link Render3DProvider#FRONT}, {@link Render3DProvider#BACK}
     *             or {@link Render3DProvider#FRONT_AND_BACK}.
     * @param mask specify the mask value. The final value will be a result of AND operations between the mask,
     *             reference value and stored stencil value.
     */
    public void stencilMaskSeparate(int face, int mask);

    /**
     * Sets the stencil action for both the front and back function tests.
     *
     * @param fail  what action to take when the test fails. Must be one of the fallowing constants :
     *              {@link Render3DProvider#KEEP}, {@link Render3DProvider#ZERO},
     *              {@link Render3DProvider#REPLACE}, {@link Render3DProvider#INCR},
     *              {@link Render3DProvider#INCR_WRAP}, {@link Render3DProvider#DECR},
     *              {@link Render3DProvider#DECR_WRAP} or {@link Render3DProvider#INVERT}
     * @param zFail the action to take when the stencil function succeeds, but the depth test fails. The same constants
     *              as for test fail are accepted.
     * @param zPass the action to take when both tests succeed. The same constants as for the stencil test fail
     *              are accepted.
     */
    public void stencilOp(int fail, int zFail, int zPass);

    /**
     * Specifies the action to take when the stencil function succeeds or fails, separate for each face.
     *
     * @param face  which face to set the actions. Must be one of the fallowing constants :
     *              {@link Render3DProvider#FRONT}, {@link Render3DProvider#BACK}
     *              or {@link Render3DProvider#FRONT_AND_BACK}.
     * @param fail  what action to take when the test fails. Must be one of the fallowing constants :
     *              {@link Render3DProvider#KEEP}, {@link Render3DProvider#ZERO},
     *              {@link Render3DProvider#REPLACE}, {@link Render3DProvider#INCR},
     *              {@link Render3DProvider#INCR_WRAP}, {@link Render3DProvider#DECR},
     *              {@link Render3DProvider#DECR_WRAP} or {@link Render3DProvider#INVERT}
     * @param zFail the action to take when the stencil function succeeds, but the depth test fails. The same constants
     *              as for test fail are accepted.
     * @param zPass the action to take when both tests succeed. The same constants as for the stencil test fail
     *              are accepted.
     */
    public void stencilOpSeparate(int face, int fail, int zFail, int zPass);

    /**
     * Sets the viewport dimensions.
     *
     * @param x      the x coordinate of lower left corner of the viewport. Initial value is 0.
     * @param y      the y coordinate of lower left corner of the viewport. Initial value is 0.
     * @param width  the width of the viewport.
     * @param height the height of the viewport.
     */
    public void viewport(int x, int y, int width, int height);

    /* ------------------------------------------Drawing------------------------------------------------------*/

    /**
     * Render the current primitive from the currently defined arrays (vertex array, normal array etc.).
     *
     * @param mode  the type of drawing to be done. Must be one of the fallowing constants :
     *              {@link Render3DProvider#POINTS},
     *              {@link Render3DProvider#LINE_STRIP},
     *              {@link Render3DProvider#LINE_LOOP},
     *              {@link Render3DProvider#LINES},
     *              {@link Render3DProvider#TRIANGLE_STRIP},
     *              {@link Render3DProvider#TRIANGLE_FAN}
     *              or {@link Render3DProvider#TRIANGLES}
     * @param first the first element from the arrays to begin drawing.
     * @param count the number of elements from the arrays to use.
     */
    public void drawArrays(int mode, int first, int count);

    /**
     * Render the current primitive from the currently defined arrays (vertex array, normal array etc.).
     *
     * @param mode   the type of drawing to be done. Must be one of the fallowing constants :
     *               {@link Render3DProvider#POINTS},
     *               {@link Render3DProvider#LINE_STRIP},
     *               {@link Render3DProvider#LINE_LOOP},
     *               {@link Render3DProvider#LINES},
     *               {@link Render3DProvider#TRIANGLE_STRIP},
     *               {@link Render3DProvider#TRIANGLE_FAN}
     *               or {@link Render3DProvider#TRIANGLES}
     * @param count  the number of elements from the arrays to use. If count is greater than zero, than a non null
     *               {@link Buffer} must be bound to {@link Render3DProvider#ELEMENT_ARRAY_BUFFER}.
     * @param type   the type of values in the indices. Must be one of the fallowing :
     *               {@link Render3DProvider#UNSIGNED_BYTE}
     *               or {@link Render3DProvider#UNSIGNED_SHORT}
     * @param offset the offset in bytes, which must be a multiple of the current mode type.
     */
    public void drawElements(int mode, int count, int type, int offset);

    /**
     * Define which way (counter clockwise or clockwise) of drawing polygons define a front facing polygon.
     *
     * @param mode the most which defines front facing polygons. Must be one of the fallowing constants :
     *             {@link Render3DProvider#CW} or {@link Render3DProvider#CCW}
     */
    public void frontFace(int mode);

    /**
     * Sets the line width for when drawing primitives consisting of only lines.
     *
     * @param width the new line width.
     */
    public void lineWidth(float width);

    /* ------------------------------------------Textures------------------------------------------------------*/

    /**
     * Activates the given texture unit.
     *
     * @param texture the texture id which spans from {@see Render3DProvider#TEXTURE0} to {@see Render3DProvider#TEXTURE31}
     *                Activating {@see Render3DProvider#ACTIVE_TEXTURE} has no effect on the current texture.
     */
    public void activateTexture(int texture);

    /**
     * Binds the given texture to a texture buffer. The texture buffer location ranges from {@see Render3DProvider#TEXTURE0}
     * to {@see Render3DProvider#TEXTURE31}
     *
     * @param target  the location to bind the texture.
     * @param texture the texture to be bound.
     */
    public void bindTexture(int target, Texture texture);

    /**
     * Copies pixels into a texture.
     *
     * @param target         the target texture. Must be one of the fallowing :
     *                       {@link Render3DProvider#TEXTURE_2D},
     *                       {@link Render3DProvider#TEXTURE_CUBE_MAP_NEGATIVE_X},
     *                       {@link Render3DProvider#TEXTURE_CUBE_MAP_POSITIVE_X}
     *                       {@link Render3DProvider#TEXTURE_CUBE_MAP_NEGATIVE_Y},
     *                       {@link Render3DProvider#TEXTURE_CUBE_MAP_POSITIVE_Y}
     *                       {@link Render3DProvider#TEXTURE_CUBE_MAP_NEGATIVE_Z},
     *                       {@link Render3DProvider#TEXTURE_CUBE_MAP_POSITIVE_Z}
     * @param level          the level of detail, where 0 is the normal uncompressed image and n is the nth level of mipmap
     *                       compression.
     * @param internalFormat the internal format of the texture. Must be one of the fallowing :
     *                       {@link Render3DProvider#ALPHA},
     *                       {@link Render3DProvider#LUMINANCE},
     *                       {@link Render3DProvider#LUMINANCE_ALPHA},
     *                       {@link Render3DProvider#RGB} or
     *                       {@link Render3DProvider#RGBA}
     * @param x              the x coordinate for the lower left corner of the rectangle to be copied.
     * @param y              the y coordinate for the lower left corner of the rectangle to be copied.
     * @param width          the width of the rectangle to be copied.
     * @param height         the height of the rectangle to be copied.
     * @param border         the width of the border. Must be 0.
     */
    public void copyTexImage2D(int target, int level, int internalFormat, int x, int y, int width, int height,
                               int border);

    /**
     * Copies pixels into a texture.
     *
     * @param target  the target texture. Must be one of the fallowing :
     *                {@link Render3DProvider#TEXTURE_2D},
     *                {@link Render3DProvider#TEXTURE_CUBE_MAP_NEGATIVE_X},
     *                {@link Render3DProvider#TEXTURE_CUBE_MAP_POSITIVE_X}
     *                {@link Render3DProvider#TEXTURE_CUBE_MAP_NEGATIVE_Y},
     *                {@link Render3DProvider#TEXTURE_CUBE_MAP_POSITIVE_Y}
     *                {@link Render3DProvider#TEXTURE_CUBE_MAP_NEGATIVE_Z},
     *                {@link Render3DProvider#TEXTURE_CUBE_MAP_POSITIVE_Z}
     * @param level   the level of detail, where 0 is the normal uncompressed image and n is the nth level of mipmap
     *                compression.
     *                {@link Render3DProvider#ALPHA}, {@link Render3DProvider#LUMINANCE}, {@link Render3DProvider#LUMINANCE_ALPHA},
     *                {@link Render3DProvider#RGB} or {@link Render3DProvider#RGBA}
     * @param x       the x coordinate for the lower left corner of the rectangle to be copied.
     * @param y       the y coordinate for the lower left corner of the rectangle to be copied.
     * @param width   the width of the rectangle to be copied.
     * @param height  the height of the rectangle to be copied.
     * @param xOffset the offset on the x axis of where the rectangle should begin.
     * @param yOffset the offset on the y axis of where the rectangle should begin.
     */
    public void copyTexSubImage2D(int target, int level, int xOffset, int yOffset,
                                  int x, int y, int width, int height);

    /**
     * Creates an empty texture.
     *
     * @return an empty texture.
     */
    public Texture createTexture();

    /**
     * Deletes the texture object.
     *
     * @param texture the texture object to be deleted.
     */
    public void deleteTexture(Texture texture);

    /**
     * Generates mipmaps for the current active texture.
     *
     * @param target the target texture. Must be one of the fallowing constants : {@link Render3DProvider#TEXTURE_2D}
     *               or {@link Render3DProvider#TEXTURE_CUBE_MAP}.
     */
    void generateMipmap(int target);

    /**
     * Retrieves the active texture's parameter.
     *
     * @param target the target texture. Must be one of the fallowing constants :
     *               {@link Render3DProvider#TEXTURE_2D} or {@link Render3DProvider#TEXTURE_CUBE_MAP}.
     * @param pName  the name of the parameters to retrieve. Must be one of the fallowing constants :
     *               {@link Render3DProvider#TEXTURE_MAG_FILTER},  {@link Render3DProvider#TEXTURE_WRAP_S},
     *               {@link Render3DProvider#TEXTURE_WRAP_T}, or {@link Render3DProvider#TEXTURE_MIN_FILTER},
     * @return the requested texture parameter.
     */
    public float getTextureParameter(int target, int pName);

    /**
     * Determines if the given object is a valid {@link Texture} object.
     *
     * @param texture the object to test.
     * @return if the given object is a valid {@link Texture} object.
     */
    public boolean isTexture(Texture texture);

    /**
     * Specifies a image source for a texture.
     *
     * @param target         the target texture. Must be one of the fallowing :
     *                       {@link Render3DProvider#TEXTURE_2D},
     *                       {@link Render3DProvider#TEXTURE_CUBE_MAP_NEGATIVE_X},
     *                       {@link Render3DProvider#TEXTURE_CUBE_MAP_POSITIVE_X}
     *                       {@link Render3DProvider#TEXTURE_CUBE_MAP_NEGATIVE_Y},
     *                       {@link Render3DProvider#TEXTURE_CUBE_MAP_POSITIVE_Y}
     *                       {@link Render3DProvider#TEXTURE_CUBE_MAP_NEGATIVE_Z},
     *                       {@link Render3DProvider#TEXTURE_CUBE_MAP_POSITIVE_Z}
     * @param level          the mipmap level to be used.
     * @param internalFormat the internal format to be used when storing the image. Must be one of the fallowing constants:
     *                       {@link Render3DProvider#ALPHA}, {@link Render3DProvider#LUMINANCE},
     *                       {@link Render3DProvider#LUMINANCE_ALPHA}, {@link Render3DProvider#RGB}
     *                       or {@link Render3DProvider#RGBA}.
     * @param width          the width of the texture image.
     * @param height         the height of the texture image.
     * @param border         the width of the border. Must be 0.
     * @param format         the format of the texel data which must match the internal format. The same values are accepted.
     * @param type           the type of texel data. Must be one of the fallowing constants :
     *                       {@link Render3DProvider#UNSIGNED_BYTE}, {@link Render3DProvider#UNSIGNED_SHORT_5_6_5},
     *                       * {@link Render3DProvider#UNSIGNED_SHORT_4_4_4_4} or
     *                       {@link Render3DProvider#UNSIGNED_SHORT_5_5_5_1}.
     * @param pixels         the pixel array to be used for the active texture.
     */
    void texImage2D(int target, int level, int internalFormat, int width, int height, int border,
                    int format, int type, NativeArray<?> pixels);

    /**
     * Specifies a image source for a texture.
     *
     * @param target         the target texture. Must be one of the fallowing :
     *                       {@link Render3DProvider#TEXTURE_2D},
     *                       {@link Render3DProvider#TEXTURE_CUBE_MAP_NEGATIVE_X},
     *                       {@link Render3DProvider#TEXTURE_CUBE_MAP_POSITIVE_X}
     *                       {@link Render3DProvider#TEXTURE_CUBE_MAP_NEGATIVE_Y},
     *                       {@link Render3DProvider#TEXTURE_CUBE_MAP_POSITIVE_Y}
     *                       {@link Render3DProvider#TEXTURE_CUBE_MAP_NEGATIVE_Z},
     *                       {@link Render3DProvider#TEXTURE_CUBE_MAP_POSITIVE_Z}
     * @param level          the mipmap level to be used.
     * @param internalFormat the internal format to be used when storing the image. Must be one of the fallowing constants:
     *                       {@link Render3DProvider#ALPHA}, {@link Render3DProvider#LUMINANCE},
     *                       {@link Render3DProvider#LUMINANCE_ALPHA}, {@link Render3DProvider#RGB}
     *                       or {@link Render3DProvider#RGBA}.
     * @param format         the format of the texel data which must match the internal format. The same values are accepted.
     * @param type           the type of texel data. Must be one of the fallowing constants :
     *                       {@link Render3DProvider#UNSIGNED_BYTE},* {@link Render3DProvider#UNSIGNED_SHORT_5_6_5},
     *                       * {@link Render3DProvider#UNSIGNED_SHORT_4_4_4_4} or * {@link Render3DProvider#UNSIGNED_SHORT_5_5_5_1}.
     * @param image          the image from which to copy the pixels.
     */
    void texImage2D(int target, int level, int internalFormat, int format, int type, ImageResource image);

    /**
     * Sets the active texture parameter.
     *
     * @param target the target texture. Must be one of the fallowing constants :
     *               {@link Render3DProvider#TEXTURE_2D} or {@link Render3DProvider#TEXTURE_CUBE_MAP}.
     * @param pName  the name of the parameter to change. Must be one of the fallowing constants :
     *               {@link Render3DProvider#TEXTURE_MIN_FILTER}, {@link Render3DProvider#TEXTURE_MAG_FILTER},
     *               {@link Render3DProvider#TEXTURE_WRAP_S} or {@link Render3DProvider#TEXTURE_WRAP_T}.
     * @param param  the value of the parameter.
     */
    void texParameter(int target, int pName, float param);

    /**
     * Sets the active texture parameter.
     *
     * @param target the target texture. Must be one of the fallowing constants :
     *               {@link Render3DProvider#TEXTURE_2D} or {@link Render3DProvider#TEXTURE_CUBE_MAP}.
     * @param pName  the name of the parameter to change. Must be one of the fallowing constants :
     *               {@link Render3DProvider#TEXTURE_MIN_FILTER}, {@link Render3DProvider#TEXTURE_MAG_FILTER},
     *               {@link Render3DProvider#TEXTURE_WRAP_S} or {@link Render3DProvider#TEXTURE_WRAP_T}.
     * @param param  the value of the parameter.
     */
    void texParameter(int target, int pName, int param);

    /**
     * Specifies a sub-portion of an image for a texture.
     *
     * @param target  the target texture. Must be one of the fallowing :
     *                {@link Render3DProvider#TEXTURE_2D},
     *                {@link Render3DProvider#TEXTURE_CUBE_MAP_NEGATIVE_X},
     *                {@link Render3DProvider#TEXTURE_CUBE_MAP_POSITIVE_X}
     *                {@link Render3DProvider#TEXTURE_CUBE_MAP_NEGATIVE_Y},
     *                {@link Render3DProvider#TEXTURE_CUBE_MAP_POSITIVE_Y}
     *                {@link Render3DProvider#TEXTURE_CUBE_MAP_NEGATIVE_Z},
     *                {@link Render3DProvider#TEXTURE_CUBE_MAP_POSITIVE_Z}
     * @param level   the mipmap level to be used.
     * @param xOffset the x offset of where the cropping of the image should begin.
     * @param yOffset the y offset of where the cropping of the image should begin.
     * @param width   the width of the texture image.
     * @param height  the height of the texture image.
     * @param border  the width of the border. Must be 0.
     * @param format  the format of the texel data which must match the internal format. The same values are accepted.
     * @param type    the type of texel data. Must be one of the fallowing constants :
     *                {@link Render3DProvider#UNSIGNED_BYTE},* {@link Render3DProvider#UNSIGNED_SHORT_5_6_5},
     *                * {@link Render3DProvider#UNSIGNED_SHORT_4_4_4_4} or * {@link Render3DProvider#UNSIGNED_SHORT_5_5_5_1}.
     * @param pixels  the pixel array to be used for the active texture.
     */
    void texSubImage2D(int target, int level, int xOffset, int yOffset, int width, int height, int border,
                       int format, int type, NativeArray<?> pixels);

    /**
     * Specifies a sub-portion of an image for a texture.
     *
     * @param target  the target texture. Must be one of the fallowing :
     *                {@link Render3DProvider#TEXTURE_2D},
     *                {@link Render3DProvider#TEXTURE_CUBE_MAP_NEGATIVE_X},
     *                {@link Render3DProvider#TEXTURE_CUBE_MAP_POSITIVE_X}
     *                {@link Render3DProvider#TEXTURE_CUBE_MAP_NEGATIVE_Y},
     *                {@link Render3DProvider#TEXTURE_CUBE_MAP_POSITIVE_Y}
     *                {@link Render3DProvider#TEXTURE_CUBE_MAP_NEGATIVE_Z},
     *                {@link Render3DProvider#TEXTURE_CUBE_MAP_POSITIVE_Z}
     * @param level   the mipmap level to be used.
     * @param xOffset the x offset of where the cropping of the image should begin.
     * @param yOffset the y offset of where the cropping of the image should begin.
     * @param format  the format of the texel data which must match the internal format. The same values are accepted.
     * @param type    the type of texel data. Must be one of the fallowing constants :
     *                {@link Render3DProvider#UNSIGNED_BYTE},* {@link Render3DProvider#UNSIGNED_SHORT_5_6_5},
     *                * {@link Render3DProvider#UNSIGNED_SHORT_4_4_4_4} or * {@link Render3DProvider#UNSIGNED_SHORT_5_5_5_1}.
     * @param image   the image from which to copy the pixels.
     */
    void texImage2D(int target, int level, int xOffset, int yOffset, int format, int type, ImageResource image);


    /* --------------------------------------------Shaders---------------------------------------------------*/

    /**
     * Attaches the given shader to the shader program. The shader type must be either a compiled pixel shader or a
     * compiled vertex shader. One of each must be added to make the program functional.
     *
     * @param program the shader program to receive the new shader.
     * @param shader  the shader to be added to the program.
     */
    public void attachShader(ShaderProgram program, Shader shader);

    /**
     * Compiles the given shader. The success status of the compilation will be stored in the shader itself.
     *
     * @param shader the shader to be compiled.
     */
    public void compileShader(Shader shader);

    /**
     * Creates an empty shader program.
     *
     * @return an empty shader program.
     */
    public ShaderProgram createShaderProgram();

    /**
     * Creates an empty shader.
     *
     * @param type the type of shader to create. Must be one of the fallowing : {@link Render3DProvider#FRAGMENT_SHADER}
     *             or {@link Render3DProvider#VERTEX_SHADER}.
     * @return an empty fragment of vertex shader.
     */
    public Shader createShader(int type);

    /**
     * Deletes the shader program from memory.
     *
     * @param program the shader program to be deleted.
     */
    public void deleteShaderProgram(Shader program);

    /**
     * Deletes the shader from memory.
     *
     * @param shader the shader to be deleted.
     */
    public void deleteShader(Shader shader);

    /**
     * Detaches the given shader from the shader program.
     *
     * @param program the program which contains the shader.
     * @param shader  the shader to be detached from the shader program.
     */
    public void detachShader(ShaderProgram program, Shader shader);

    /**
     * Returns the attached shaders which reside inside the given program.
     *
     * @param program the program which contains the shaders.
     * @return the shaders which reside inside the given program.
     */
    public List<Shader> getAttachedShaders(ShaderProgram program);

    /**
     * Retrieves a param from the given shader program.
     *
     *
     * @param program the shader program which contains the params.
     * @param pName   the name of the param to retrieve. Must be one of the fallowing constants :
     *                {@link edu.catalindumitru.bee.graphics.Render3DProvider#DELETE_STATUS},{@link edu.catalindumitru.bee.graphics.Render3DProvider#LINK_STATUS},
     *                {@link edu.catalindumitru.bee.graphics.Render3DProvider#VALIDATE_STATUS}, {@link edu.catalindumitru.bee.graphics.Render3DProvider#ATTACHED_SHADERS},
     *                {@link edu.catalindumitru.bee.graphics.Render3DProvider#ACTIVE_ATTRIBUTES} or  {@link edu.catalindumitru.bee.graphics.Render3DProvider#ACTIVE_UNIFORMS}.
     * @return the requested parameter.
     */
    Object getProgramParameter(ShaderProgram program, int pName);

    /**
     * Retrieves the shader program's information log, updated when linked or validated.
     *
     * @param program the shader program which contains the desired info log.
     * @return the info log of the given shader program.
     */
    String getProgramInfoLog(ShaderProgram program);

    /**
     * Retrieves a shader param.
     *
     *
     * @param shader the shader which contains the desired information.
     * @param pName  the name of the parameter to retrieve. Must be one of the fallowing constants :
     *               {@link edu.catalindumitru.bee.graphics.Render3DProvider#SHADER_TYPE} or {@link edu.catalindumitru.bee.graphics.Render3DProvider#COMPILE_STATUS}
     * @return the requested parameter.
     */
    Object getShaderParameter(ShaderProgram shader, int pName);

    /**
     * Retrieves the info log for the given shader, which is modified when the shader is compiled.
     *
     * @param shader the shader object which contains the desired info log.
     * @return the info log for the given shader.
     */
    String getShaderInfoLog(Shader shader);

    /**
     * Retrieves the source code for the given shader.
     *
     * @param shader the shader which contains
     * @return the source code for the given shader.
     */
    String getShaderSource(Shader shader);

    /**
     * Determines if the given object is a valid {@link ShaderProgram} object.
     *
     * @param shaderProgram the object to test.
     * @return if the given object is a valid {@link ShaderProgram} object.
     */
    boolean isShaderProgram(ShaderProgram shaderProgram);

    /**
     * Determines if the given object is a valid {@link Shader} object.
     *
     * @param shader the object to test.
     * @return if the given object is a valid {@link Shader} object.
     */
    boolean isShader(Shader shader);

    /**
     * Links the vertex shader and the fragment shader which reside inside the given shader program. The success
     * status will be stored inside the shader program.
     *
     * @param program the shader program to link.
     */
    public void linkProgram(ShaderProgram program);

    /**
     * Replaces the source code in the given shader.
     *
     * @param shader the shader which will contain the new source code.
     * @param source the new source code.
     */
    public void shaderSource(Shader shader, String source);

    /**
     * Specifies which shader program to use in the current rendering state. Switching shaders is a costly
     * operation and must be done as infrequently as possible.
     *
     * @param program the shader program to be installed.
     */
    public void useProgram(ShaderProgram program);

    /**
     * Validates a compiled shader program. The result of the validation will be stored in the shader program's
     * info log.
     *
     * @param program which shader program to validate.
     */
    public void validateProgram(ShaderProgram program);

    /* --------------------------------------Attributes and Uniforms---------------------------------------------------*/

    /**
     * Associates an anonymous attribute, which resides at the given index value, with a user defined string.
     *
     * @param program a handle to the shader program which contains the attribute.
     * @param index   the index of the attribute.
     * @param name    the new user defined id given to teh attribute.
     */
    public void bindAttributeLocation(ShaderProgram program, int index, String name);

    /**
     * Disables a generic vertex attribute array.
     *
     * @param index the index of the vertex array.
     */
    public void disableVertexAttributeArray(int index);

    /**
     * Enables a generic vertex attribute array.
     *
     * @param index the index of the attribute array.
     */
    public void enableVertexAttributeArray(int index);

    /**
     * Returns information on the active attribute which resides on the index value provided.
     *
     * @param program the shader program which contains the attribute.
     * @param index   the index of the attribute value inside the shader.
     * @return information on the specified attribute.
     */
    public ActiveInfo getActiveAttribute(ShaderProgram program, int index);

    /**
     * Returns information on the active uniform which resides on the index value provided.
     *
     * @param program the shader program which contains the uniform.
     * @param index   the index of the uniform value.
     * @return information on the specified uniform value.
     */
    public ActiveInfo getActiveUniform(ShaderProgram program, int index);

    /**
     * Retrieves the named attribute location from the shader program.
     *
     * @param program the shader program which contains the attribute.
     * @param name    the name of the attribute to retrieve.
     * @return the index in which the attribute is located. If the attribute is not found, then -1 is returned.
     */
    public int getAttributeLocation(ShaderProgram program, String name);

    /**
     * Retrieves the uniform value.
     *
     *
     * @param program  the shader program which contains the desired uniform.
     * @param location a location to the uniform value.
     * @return the value of the uniform.
     */
    public List<Float> getUniform(ShaderProgram program, UniformLocation location);

    /**
     * Retrieves the location of a named uniform. If the uniform is not found, then null is returned.
     *
     * @param program the program which contains the uniform.
     * @param name    the name of the uniform.
     * @return the uniform location.
     */
    public UniformLocation getUniformLocation(ShaderProgram program, String name);

    /**
     * Retrieves a parameter for a generic vertex attribute.
     *
     * @param index the index of the attribute.
     * @param pName the parameter name to be queried. Must be one of the fallowing constants :
     *              {@link Render3DProvider#VERTEX_ATTRIB_ARRAY_BUFFER_BINDING},
     *              {@link Render3DProvider#VERTEX_ATTRIB_ARRAY_ENABLED},
     *              {@link Render3DProvider#VERTEX_ATTRIB_ARRAY_SIZE},
     *              {@link Render3DProvider#VERTEX_ATTRIB_ARRAY_STRIDE},
     *              {@link Render3DProvider#VERTEX_ATTRIB_ARRAY_TYPE},
     *              {@link Render3DProvider#VERTEX_ATTRIB_ARRAY_NORMALIZED}
     *              or {@link Render3DProvider#CURRENT_VERTEX_ATTRIBUTE}.
     * @return the requested parameter value.
     */
    public Object getVertexAttribute(int index, int pName);

    /**
     * Retrieves the offset of a generic attribute.
     *
     * @param index the index of the attribute to be returned.
     * @param pName the name of the generic attribute parameter to be returned. Must be
     *              {@link Render3DProvider#VERTEX_ATTRIB_ARRAY_POINTER}.
     * @return the offset of the given generic attribute.
     */
    public int getVertexAttributeOffset(int index, int pName);

    /**
     * Specifies the value of a uniform in the current shader program. The uniform must be a single float variable.
     *
     * @param location the uniform location of where to set the value.
     * @param x        the new value of the uniform.
     */
    public void uniform1f(UniformLocation location, float x);

    /**
     * Specifies the value of a uniform in the current shader program. The uniform must be an array of float variables.
     *
     * @param location the uniform location of where to set the value.
     * @param v        the new values for the uniform.
     */
    public void uniform1f(UniformLocation location, NativeArray<Float> v);

    /**
     * Specifies the value of a uniform in the current shader program. The uniform must be a single integer variable.
     *
     * @param location the uniform location of where to set the value.
     * @param x        the new value of the uniform.
     */
    public void uniform1i(UniformLocation location, int x);

    /**
     * Specifies the value of a uniform in the current shader program. The uniform must be an array of float variables.
     *
     * @param location the uniform location of where to set the value.
     * @param v        the new values for the uniform.
     */
    public void uniform1i(UniformLocation location, NativeArray<Integer> v);

    /**
     * Specifies the value of a uniform in the current shader program. The uniform must be a structure comprised
     * of two float variables.
     *
     * @param location the uniform location of where to set the value.
     * @param x        the new value for the first variable of the uniform.
     * @param y        the new value for the second variable of the uniform.
     */
    public void uniform2f(UniformLocation location, float x, float y);

    /**
     * Specifies the value of a uniform in the current shader program. The uniform must be an array of structures
     * comprised of two float variables.
     *
     * @param location the uniform location of where to set the value.
     * @param v        the new value of the uniform. The length of the array must be a multiple of two.
     */
    public void uniform2f(UniformLocation location, NativeArray<Float> v);

    /**
     * Specifies the value of a uniform in the current shader program. The uniform must be a structure comprised
     * of two integer variables.
     *
     * @param location the uniform location of where to set the value.
     * @param x        the new value for the first variable of the uniform.
     * @param y        the new value for the second variable of the uniform..
     */
    public void uniform2i(UniformLocation location, int x, int y);

    /**
     * Specifies the value of a uniform in the current shader program. The uniform must be an array of structures
     * comprised of two integer variables.
     *
     * @param location the uniform location of where to set the value.
     * @param v        the new value of the uniform. The length of the array must be a multiple of two.
     */
    public void uniform2i(UniformLocation location, NativeArray<Integer> v);

    /**
     * Specifies the value of a uniform in the current shader program. The uniform must be a structure comprised
     * of three float variables.
     *
     * @param location the uniform location of where to set the value.
     * @param x        the new value for the first variable of the uniform.
     * @param y        the new value for the second variable of the uniform.
     * @param z        the new value for the third variable of the uniform.
     */
    public void uniform3f(UniformLocation location, float x, float y, float z);

    /**
     * Specifies the value of a uniform in the current shader program. The uniform must be an array of structures
     * comprised of three float variables.
     *
     * @param location the uniform location of where to set the value.
     * @param v        the new value of the uniform. The length of the array must be a multiple of three.
     */
    public void uniform3f(UniformLocation location, NativeArray<Float> v);

    /**
     * Specifies the value of a uniform in the current shader program. The uniform must be a structure comprised
     * of three integer variables.
     *
     * @param location the uniform location of where to set the value.
     * @param x        the new value for the first variable of the uniform.
     * @param y        the new value for the second variable of the uniform.
     * @param z        the new value for the third variable of the uniform.
     */
    public void uniform3i(UniformLocation location, int x, int y, int z);

    /**
     * Specifies the value of a uniform in the current shader program. The uniform must be an array of structures
     * comprised of three integer variables.
     *
     * @param location the uniform location of where to set the value.
     * @param v        the new value of the uniform. The length of the array must be a multiple of three.
     */
    public void uniform3i(UniformLocation location, NativeArray<Integer> v);

    /**
     * Specifies the value of a uniform in the current shader program. The uniform must be a structure comprised
     * of four float variables.
     *
     * @param location the uniform location of where to set the value.
     * @param x        the new value for the first variable of the uniform.
     * @param y        the new value for the second variable of the uniform.
     * @param z        the new value for the third variable of the uniform.
     * @param w        the new value for the fourth variable of the uniform.
     */
    public void uniform4f(UniformLocation location, float x, float y, float z, float w);

    /**
     * Specifies the value of a uniform in the current shader program. The uniform must be an array of structures
     * comprised of four float variables.
     *
     * @param location the uniform location of where to set the value.
     * @param v        the new value of the uniform. The length of the array must be a multiple of four.
     */
    public void uniform4f(UniformLocation location, NativeArray<Float> v);

    /**
     * Specifies the value of a uniform in the current shader program. The uniform must be a structure comprised
     * of four integer variables.
     *
     * @param location the uniform location of where to set the value.
     * @param x        the new value for the first variable of the uniform.
     * @param y        the new value for the second variable of the uniform.
     * @param z        the new value for the third variable of the uniform.
     * @param w        the new value for the fourth variable of the uniform.
     */
    public void uniform4i(UniformLocation location, int x, int y, int z, int w);

    /**
     * Specifies the value of a uniform in the current shader program. The uniform must be an array of structures
     * comprised of four integer variables.
     *
     * @param location the uniform location of where to set the value.
     * @param v        the new value of the uniform. The length of the array must be a multiple of four.
     */
    public void uniform4i(UniformLocation location, NativeArray<Integer> v);

    /**
     * Sets the uniform value in the current shader program. The uniform value must be a structure comprised
     * of 4 float values (a 2x2 matrix).
     *
     * @param location  the uniform location in the current shader program.
     * @param transpose if the matrix should be transposed. Should be false.
     * @param value     teh new value of the uniform. The length if the array must be 4 (a 2x2 matrix).
     */
    public void uniformMatrix2f(UniformLocation location, boolean transpose, NativeArray<Float> value);

    /**
     * Sets the uniform value in the current shader program. The uniform value must be a structure comprised
     * of 9 float values (a 3x2 matrix).
     *
     * @param location  the uniform location in the current shader program.
     * @param transpose if the matrix should be transposed. Should be false.
     * @param value     teh new value of the uniform. The length if the array must be 9 (a 3x3 matrix).
     */
    public void uniformMatrix3f(UniformLocation location, boolean transpose, NativeArray<Float> value);

    /**
     * Sets the uniform value in the current shader program. The uniform value must be a structure comprised
     * of 16 float values (a 4x4 matrix).
     *
     * @param location  the uniform location in the current shader program.
     * @param transpose if the matrix should be transposed. Should be false.
     * @param value     teh new value of the uniform. The length if the array must be 16 (a 4x4 matrix).
     */
    public void uniformMatrix4f(UniformLocation location, boolean transpose, NativeArray<Float> value);

    /**
     * Sets the value of a generic attribute which resides at the given index. The attribute must be a single float
     * value.
     *
     * @param index the index of the generic attribute.
     * @param x     the value of the generic attribute.
     */
    public void vertexAttribute1f(int index, float x);

    /**
     * Sets the value of a generic attribute which resides at the given index. The attribute must be a single float
     * value.
     *
     * @param index  the index of the generic attribute.
     * @param values the new values of the attribute.
     */
    public void vertexAttribute1f(int index, NativeArray<Float> values);

    /**
     * Sets the value of a generic attribute which resides at the given index. The attribute must be a structure
     * or 2 float values.
     *
     * @param index the index of the generic attribute.
     * @param x     the first value of the generic attribute.
     * @param y     the second value of the generic attribute.
     */
    public void vertexAttribute2f(int index, float x, float y);

    /**
     * Sets the value of a generic attribute which resides at the given index. The attribute must be a structure
     * or 2 float values.
     *
     * @param index  the index of the generic attribute.
     * @param values the new values of the attribute. The length of the array must be a multiple of 2.
     */
    public void vertexAttribute2f(int index, NativeArray<Float> values);

    /**
     * Sets the value of a generic attribute which resides at the given index. The attribute must be a structure
     * or 3 float values.
     *
     * @param index the index of the generic attribute.
     * @param x     the first value of the generic attribute.
     * @param y     the second value of the generic attribute.
     * @param z     the third value of the generic attribute.
     */
    public void vertexAttribute3f(int index, float x, float y, float z);

    /**
     * Sets the value of a generic attribute which resides at the given index. The attribute must be a structure
     * or 3 float values.
     *
     * @param index  the index of the generic attribute.
     * @param values the new values of the attribute. The length of the array must be a multiple of 3.
     */
    public void vertexAttribute3f(int index, NativeArray<Float> values);

    /**
     * Sets the value of a generic attribute which resides at the given index. The attribute must be a structure
     * or 4 float values.
     *
     * @param index the index of the generic attribute.
     * @param x     the first value of the generic attribute.
     * @param y     the second value of the generic attribute.
     * @param z     the third value of the generic attribute.
     * @param w     the fourth value of the generic attribute.
     */
    public void vertexAttribute4f(int index, float x, float y, float z, float w);

    /**
     * Sets the value of a generic attribute which resides at the given index. The attribute must be a structure
     * or 4 float values.
     *
     * @param index  the index of the generic attribute.
     * @param values the new values of the attribute. The length of the array must be a multiple of 4.
     */
    public void vertexAttribute4f(int index, NativeArray<Float> values);

    /**
     * Assigns the current bound buffer to the attribute at the given location.
     *
     * @param index      the index of the attribute to be modified.
     * @param size       the number of components per vertex attribute. Must be a value between 1 and 4.
     * @param type       the data type of the current bound buffer. Must be one of the fallowing constants :
     *                   {@link Render3DProvider#BYTE}, {@link Render3DProvider#UNSIGNED_BYTE},
     *                   {@link Render3DProvider#SHORT}, {@link Render3DProvider#UNSIGNED_SHORT} or
     *                   {@link Render3DProvider#FLOAT}
     *                   GL_BYTE, GL_UNSIGNED_BYTE, GL_SHORT, GL_UNSIGNED_SHORT, GL_FIXED, or GL_FLOAT
     * @param normalized if the given buffer should be normalized.
     * @param stride     the offset in bytes between consecutive vertex attributes.
     * @param offset     the offset in bytes between consecutive vertex attributes.
     */
    public void vertexAttributePointer(int index, int size, int type, boolean normalized, int stride, int offset);


    /* ------------------------------------------- Buffers -------------------------------------------------------*/

    /**
     * Sets the active buffer to the given params.
     *
     * @param target the type of buffer binding. Must be either {@link Render3DProvider#ARRAY_BUFFER} or
     *               {@link Render3DProvider#ELEMENT_ARRAY_BUFFER}
     * @param buffer the buffer to bind.
     */
    public void bindBuffer(int target, Buffer buffer);

    /**
     * Fills the current buffer with the data from the native array.
     *
     * @param target the target buffer (either {@link Render3DProvider#ARRAY_BUFFER} or
     *               {@link Render3DProvider#ELEMENT_ARRAY_BUFFER}).
     * @param data   the array buffer containing the new data.
     * @param usage  the usage pattern of the buffer. Must be either {@link Render3DProvider#STREAM_DRAW},
     *               {@link Render3DProvider#STATIC_DRAW} or {@link Render3DProvider#DYNAMIC_DRAW}.
     */
    public void bufferData(int target, NativeArray<?> data, int usage);

    /**
     * Fills a portion of the current buffer with the data from the native array.
     *
     * @param target the target buffer  (either {@link Render3DProvider#ARRAY_BUFFER} or
     *               {@link Render3DProvider#ELEMENT_ARRAY_BUFFER}).
     * @param offset the offset from the begining of the data store from which to begin filling with the new buffer.
     * @param data   the new data buffer.
     */
    public void bufferSubData(int target, int offset, NativeArray<?> data);

    /**
     * Creates an empty buffer.
     *
     * @return an empty buffer.
     */
    Buffer createBuffer();

    /**
     * Deletes the buffer from memory.
     *
     * @param buffer the buffer to be deleted.
     */
    public void deleteBuffer(Buffer buffer);

    /**
     * Determines if the given object is a valid {@link Buffer} object.
     *
     * @param buffer the buffer object to verify.
     * @return whether or not the given object is a valid {@link Buffer} object.
     */
    boolean isBuffer(Buffer buffer);

    /* -----------------------------------------Frame Buffer-----------------------------------------------------------*/

    /**
     * Sets the active frame buffer to the given params.
     *
     * @param target      the type of buffer binding. Must be either {@link Render3DProvider#ARRAY_BUFFER} or
     *                    {@link Render3DProvider#ELEMENT_ARRAY_BUFFER}
     * @param frameBuffer the frame buffer to bind.
     */
    public void bindFrameBuffer(int target, FrameBuffer frameBuffer);

    /**
     * Checks the completion status of the frame buffer.
     *
     * @param target the target buffer. Must be the constant {@link Render3DProvider#FRAMEBUFFER};
     * @return the completion status of the frame buffer. Will be one of the fallowing :
     *         {@link Render3DProvider#FRAMEBUFFER_INCOMPLETE_ATTACHMENT},
     *         {@link Render3DProvider#FRAMEBUFFER_INCOMPLETE_DIMENSIONS}
     *         {@link Render3DProvider#FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT} or
     *         {@link Render3DProvider#FRAMEBUFFER_UNSUPPORTED}
     */
    public int checkFrameBufferStatus(int target);

    /**
     * Creates an empty fame buffer.
     *
     * @return an empty frame buffer.
     */
    public FrameBuffer createFameBuffer();

    /**
     * Deletes the frame buffer from memory.
     *
     * @param frameBuffer the frame buffer to be deleted.
     */
    public void deleteFrameBuffer(FrameBuffer frameBuffer);

    /**
     * Attach a render buffer to the current frame buffer.
     *
     * @param target             the target frame buffer. Must be the constant : {@link Render3DProvider#FRAMEBUFFER}
     * @param attachment         the attachment point of which the render buffer should attach. Must be one of the fallowing
     *                           constants : {Render3DProvider#COLOR_ATTACHMENT0}, {Render3DProvider#DEPTH_ATTACHMENT} or
     *                           {Render3DProvider#STENCIL_ATTACHMENT}
     * @param renderBufferTarget the target render buffer. Must be the constant : {Render3DProvider#RENDERBUFFER}
     * @param renderBuffer       the render buffer object to atach.
     */
    public void frameBufferRenderBuffer(int target, int attachment, int renderBufferTarget,
                                        RenderBuffer renderBuffer);

    /**
     * Attach a texture to the current frame buffer.
     *
     * @param target        the target frame buffer. Must be the constant {Render3DProvider#FRAMEBUFFER}
     * @param attachment    the target attachment point for the texture.  Must be one of the fallowing
     *                      constants : {Render3DProvider#COLOR_ATTACHMENT0}, {Render3DProvider#DEPTH_ATTACHMENT} or
     *                      {Render3DProvider#STENCIL_ATTACHMENT}
     * @param textureTarget the target texture. Must be one of the fallowing constants :
     *                      {Render3DProvider#TEXTURE_2D},
     *                      {Render3DProvider#TEXTURE_CUBE_MAP},
     *                      {@link Render3DProvider#TEXTURE_CUBE_MAP_NEGATIVE_X},
     *                      {@link Render3DProvider#TEXTURE_CUBE_MAP_POSITIVE_X},
     *                      {@link Render3DProvider#TEXTURE_CUBE_MAP_NEGATIVE_Y},
     *                      {@link Render3DProvider#TEXTURE_CUBE_MAP_POSITIVE_Y},
     *                      {@link Render3DProvider#TEXTURE_CUBE_MAP_NEGATIVE_Z} or
     *                      {@link Render3DProvider#TEXTURE_CUBE_MAP_POSITIVE_Z}
     * @param texture       the texture to attach.
     * @param level         the mipmap level of the texture to attach. Must be 0.
     */
    public void frameBufferTexture2D(int target, int attachment, int textureTarget, Texture texture, int level);

    /**
     * Retrieves a param value for the current frame buffer.
     *
     *
     * @param target     the target frame buffer. Must be the constant : {@link edu.catalindumitru.bee.graphics.Render3DProvider#FRAMEBUFFER}
     * @param attachment the symbolic frame buffer attachment. Must be one of the fallowing constants :
     *                   {@link edu.catalindumitru.bee.graphics.Render3DProvider#COLOR_ATTACHMENT0}, {@link edu.catalindumitru.bee.graphics.Render3DProvider#DEPTH_ATTACHMENT},
     *                   {@link edu.catalindumitru.bee.graphics.Render3DProvider#STENCIL_ATTACHMENT}.
     * @param pName      the param name of which to retrieve the information. Must be one of the fallowing constants :
     *                   {@link edu.catalindumitru.bee.graphics.Render3DProvider#FRAMEBUFFER_ATTACHMENT_OBJECT_NAME},
     *                   {@link edu.catalindumitru.bee.graphics.Render3DProvider#FRAMEBUFFER_ATTACHMENT_OBJECT_TYPE},
     *                   {@link edu.catalindumitru.bee.graphics.Render3DProvider#FRAMEBUFFER_ATTACHMENT_TEXTURE_CUBE_MAP_FACE} or
     *                   {@link edu.catalindumitru.bee.graphics.Render3DProvider#FRAMEBUFFER_ATTACHMENT_TEXTURE_LEVEL}.
     * @return the requested parameter.
     */
    Object getFrameBufferAttachmentParameter(int target, int attachment, int pName);

    /**
     * Determines if the given object is a valid {@link FrameBuffer} object.
     *
     * @param frameBuffer the object to test.
     * @return if the given object is a valid {@link FrameBuffer} object.
     */
    boolean isFrameBuffer(FrameBuffer frameBuffer);

    /**
     * Reads pixels from the current frame buffer and puts them in the given NativeArray.
     *
     * @param x      the x coordinate for the lower left corner of the rectangle from which to read the pixels.
     * @param y      the y coordinate for the lower left corner of the rectangle from which to read the pixels.
     * @param width  the width of the rectangle from which to read the pixels.
     * @param height the height of the rectangle from which to read the pixels.
     * @param format the format of the pixel data. Must be one of the fallowing constants :
     *               {@link Render3DProvider#RGB}, {@link Render3DProvider#ALPHA} or {@link Render3DProvider#RGBA}
     * @param type   the type of the pixel data. Must be one of the fallowing constants :
     *               {@link Render3DProvider#UNSIGNED_BYTE}, {@link Render3DProvider#UNSIGNED_SHORT_5_6_5},
     *               {@link Render3DProvider#UNSIGNED_SHORT_4_4_4_4} or {@link Render3DProvider#UNSIGNED_SHORT_5_5_5_1}.
     * @param pixels the array where to store the pixels.
     */
    public void readPixels(int x, int y, int width, int height, int format, int type, NativeArray<?> pixels);

    /* -----------------------------------------Render Buffer-----------------------------------------------------------*/

    /**
     * Sets the active render buffer to the given params.
     *
     * @param target       the type of buffer binding. Must be either {@link Render3DProvider#ARRAY_BUFFER} or
     *                     {@link Render3DProvider#ELEMENT_ARRAY_BUFFER}
     * @param renderBuffer the render buffer to bind.
     */
    public void bindRenderBuffer(int target, RenderBuffer renderBuffer);

    /**
     * Creates an empty render buffer.
     *
     * @return an empty render buffer.
     */
    public RenderBuffer createRenderBuffer();

    /**
     * Deletes the render buffer from memory.
     *
     * @param buffer the render buffer to be deleted.
     */
    public void deleteRenderBuffer(RenderBuffer buffer);

    /**
     * Retrieves a param from the current render buffer.
     *
     *
     * @param target the target render buffer. Must be the constant : {@link edu.catalindumitru.bee.graphics.Render3DProvider#RENDERBUFFER}.
     * @param pName  the param name to retrieve. Must be one of the fallowing constants :
     *               {@link edu.catalindumitru.bee.graphics.Render3DProvider#RENDERBUFFER_WIDTH}, {@link edu.catalindumitru.bee.graphics.Render3DProvider#RENDERBUFFER_HEIGHT},
     *               {@link edu.catalindumitru.bee.graphics.Render3DProvider#RENDERBUFFER_INTERNAL_FORMAT}, {@link edu.catalindumitru.bee.graphics.Render3DProvider#RENDERBUFFER_RED_SIZE},
     *               {@link edu.catalindumitru.bee.graphics.Render3DProvider#RENDERBUFFER_GREEN_SIZE}, {@link edu.catalindumitru.bee.graphics.Render3DProvider#RENDERBUFFER_BLUE_SIZE},
     *               {@link edu.catalindumitru.bee.graphics.Render3DProvider#RENDERBUFFER_ALPHA_SIZE}, {@link edu.catalindumitru.bee.graphics.Render3DProvider#RENDERBUFFER_DEPTH_SIZE}
     *               or {@link edu.catalindumitru.bee.graphics.Render3DProvider#RENDERBUFFER_STENCIL_SIZE}.
     * @return the requested param.
     */
    Object getRenderBufferParameter(int target, int pName);


    /**
     * Determines if the given object is a valid {@link RenderBuffer} object.
     *
     * @param renderBuffer the object to test.
     * @return if the given object is a valid {@link RenderBuffer} object.
     */
    public boolean isRenderBuffer(RenderBuffer renderBuffer);

    /**
     * Creates an empty render buffer storage.
     *
     * @param target         the target render buffer. Must be the constant : {@link Render3DProvider#RENDERBUFFER}.
     * @param internalFormat the internal format of the render buffer. Must be one of the fallowing constants :
     *                       {@link Render3DProvider#RGBA4}, {@link Render3DProvider#RGB565},
     *                       {@link Render3DProvider#RGB5_A1}, {@link Render3DProvider#DEPTH_COMPONENT16} or
     *                       {@link Render3DProvider#STENCIL_INDEX8}.
     * @param width          the width of the render buffer.
     * @param height         the height of the render buffer.
     */
    public void renderBufferStorage(int target, int internalFormat, int width, int height);

}
