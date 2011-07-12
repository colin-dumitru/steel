/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.catalindumitru.bee.world;

import edu.catalindumitru.bee.component.Component;
import edu.catalindumitru.bee.core.Logger;
import edu.catalindumitru.bee.core.UniqueNameGenerator;
import edu.catalindumitru.bee.math.*;
import edu.catalindumitru.bee.math.ArithmeticException;
import edu.catalindumitru.bee.math.Vector;

import java.util.*;



/**
 *
 * @author colin
 */
public class Node {
    /*predefined axis*/
    public static final Vector V_UP = new Vector(new float[]{0, 1, 0});
    public static final Vector V_DOWN = new Vector(new float[]{0, -1, 0});
    public static final Vector V_LEFT = new Vector(new float[]{-1, 0, 0});
    public static final Vector V_RIGHT = new Vector(new float[]{1, 0, 0});
    public static final Vector V_FRONT = new Vector(new float[]{0, 0, 1});
    public static final Vector V_BACK = new Vector(new float[]{0, 0, -1});


    /*the transform of this node. The transform is world aligned, and only updated when it is queried - this value is the
    * cached transform, representing the concatenated matrix of the rest of the matrices*/
    protected Matrix4x4 mtransform;
    /*local rotation matrix*/
    protected Matrix4x4 mrotation;
    /*local translation matrix*/
    protected Matrix4x4 mtranslation;
    /*local scale / sheer matrix*/
    protected Matrix4x4 mscale;

    /*vector representing axis rotations of this node*/
    protected Vector rotation;
    /*vector representing object local translation*/
    protected Vector translation;
    /*vector representing local scale*/
    protected Vector scale;



    /*the parent of this transform, if it exists*/
    protected Node parent;
    /*the children of this node, sorted by their name*/
    protected Map<String, Node> children;
    /*The components of this node*/
    protected Map<Integer, Component> components;
    /*the unique id of this node, unique relative to the list of our parrent*/
    protected String name;
    /*when a node's transform is updated, the transform of it's children is invalidated, thus they need to create a new
    * transform when queried*/
    protected boolean needTransformUpdate;


    /*The number of node instances which have been created. Used to automatically generate a node name is no one is given*/
     protected static UniqueNameGenerator nameGenerator;

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public Node(String name) {
        this.children = new TreeMap<String, Node> ();
        this.components = new TreeMap<Integer, Component>();
        this.name = name;

        /*reset the transform and matrices*/
        this.resetTransform();
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public Node() {
        this("Node_" + Node.nameGenerator.next("Node"));
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void resetTransform() {
        this.translation = new Vector(new float[]{0, 0, 0});
        this.rotation = new Vector(new float[]{0, 0, 0});
        this.scale = new Vector(new float[]{1, 1, 1});

        this.mrotation = Matrix4x4.IDENTITY.clone();
        this.mtranslation = Matrix4x4.IDENTITY.clone();
        this.mscale = Matrix4x4.IDENTITY.clone();
        this.mtransform = Matrix4x4.IDENTITY.clone();

        this.needTransformUpdate = true;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public static void setNameGenerator(UniqueNameGenerator generator) {
        Node.nameGenerator = generator;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void setParent(Node parent) {
        this.parent = parent;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void removeParent(){
        this.parent = null;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public Node getParent(){
        return this.parent;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    /**
     * Sets the name of this node. Setting the name is very important because it needs to by unique to be correctly
     * identified in the node list of it's parrent. Also no 2 nodes ca exist in the same context with the same name.
     */
    public void setName(String name){
        this.name = name;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Gets the unique name of this Node.
     * @return name of the node.
     */
    public String getName(){
        return this.name;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Adds the given node as a child to this node. If another node with the same name exists it will be replaced.
     * @param child
     */
    public void addChild(Node child){
        this.children.put(child.getName(), child);
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Remove the giver child from the child list. This function will traverse the entire list and do a comparison
     * by reference, which is slower than removing a child by it's name.
     * @param child the node to remove
     */
    public void removeChild(Node child) {
        Set<Map.Entry<String, Node>> entrySet = this.children.entrySet();

        for(Map.Entry<String, Node> entry : entrySet){
            /*check for reference equality*/
            if(entry.getValue() == child) {
                 entrySet.remove(entry);
                return;
            }
        }
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Removes a child with the given name. If no child has that name, no child will be removed.
     * @param name the name of the child to remove
     */
    public void removeChild(String name) {
        this.children.remove(name);
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Searches for the child with the given name. If traverse is set to true, the search is continued to the children's
     * children and so on until the node is found or we run out of nodes.
     * @param name the name of the node to search
     * @param traverse if the search should continue down if no result is found in this node's children
     * @return the node matching the given name, or null if no node is found
     */
    public Node getChild(String name, boolean traverse) {
        for(Map.Entry<String, Node> child : this.children.entrySet()) {
             if(child.getKey().equals(name))
                 return child.getValue();
        }

        /*if we reach this far , that means we haven't found a result yet -- we check whether we have to traverse
         children to, if not we return null*/
        if(!traverse){
            return null;
        } else {
            Node ret = null;

            for(Map.Entry<String, Node> child : this.children.entrySet()) {
                if((ret = child.getValue().getChild(name, true)) != null)
                    return ret;
                }


            return ret;
        }
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Searches whether or not the child with the given name exists in this hierarchy. If traverse is set to true then,
     * if no results are found immediately, then the search continues with the children of this noe and so on
     * @param name the name of the node to search
     * @param traverse whether ot nor we should search withing out children
     * @return true if the child has been found, false otherwise.
     */
    public boolean hasChild(String name, boolean traverse) {
        for(Map.Entry<String, Node> child : this.children.entrySet()) {
             if(child.getKey().equals(name))
                 return true;
        }

        /*if we reach this far , that means we haven't found a result yet -- we check whether we have to traverse
         children to, if not we return false*/
        if(!traverse){
            return false;
        } else {
            for(Map.Entry<String, Node> child : this.children.entrySet()) {
                if(child.getValue().hasChild(name, true))
                    return true;
                }

            return false;
        }
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    /**
     * Searches whether or not the child with the given reference exists in this hierarchy. If traverse is set to true then,
     * if no results are found immediately, then the search continues with the children of this noe and so on
     * @param node the node to search
     * @param traverse whether ot nor we should search withing out children
     * @return true if the child has been found, false otherwise.
     */
    public boolean hasChild(Node node, boolean traverse) {
        for(Map.Entry<String, Node> child : this.children.entrySet()) {
             if(child.getValue() == node)
                 return true;
        }

        /*if we reach this far , that means we haven't found a result yet -- we check whether we have to traverse
         children to, if not we return false*/
        if(!traverse){
            return false;
        } else {
            for(Map.Entry<String, Node> child : this.children.entrySet()) {
                if(child.getValue().hasChild(node, true))
                    return true;
                }

            return false;
        }
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void translate(float x, float y, float z) {
        this.translation.add(Vector.X, x);
        this.translation.add(Vector.Y, y);
        this.translation.add(Vector.Z, z);

        this.needTransformUpdate = true;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void translate(Vector translation){
        try{
            this.translation.add(Vector.X, translation.get(Vector.X));
            this.translation.add(Vector.Y, translation.get(Vector.Y));
            this.translation.add(Vector.Z, translation.get(Vector.Z));
        } catch(ArrayIndexOutOfBoundsException ex) {

        } finally {
            this.needTransformUpdate = true;
        }
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public void rotate(float x, float y, float z) {
        this.rotation.add(Vector.X, x);
        this.rotation.add(Vector.Y, y);
        this.rotation.add(Vector.Z, z);

        this.needTransformUpdate = true;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void rotate(Vector rotation){
        try{
            this.rotation.add(Vector.X, rotation.get(Vector.X));
            this.rotation.add(Vector.Y, rotation.get(Vector.Y));
            this.rotation.add(Vector.Z, rotation.get(Vector.Z));
        } catch(ArrayIndexOutOfBoundsException ex) {

        } finally {
            this.needTransformUpdate = true;
        }
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void rotate(Vector axis, float angle) {
        /*The axis needs to be normalized for this to work*/
        try{
            this.rotation.add(Vector.X, axis.get(Vector.X) * angle);
            this.rotation.add(Vector.Y, axis.get(Vector.Y) * angle);
            this.rotation.add(Vector.Z, axis.get(Vector.Z) * angle);
        } catch(ArrayIndexOutOfBoundsException ex) {

        } finally {
            this.needTransformUpdate = true;
        }
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    public void scale(float x, float y, float z) {
        this.scale.add(Vector.X, x);
        this.scale.add(Vector.Y, y);
        this.scale.add(Vector.Z, z);

        this.needTransformUpdate = true;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void scale(Vector scale){
        try{
            this.scale.add(Vector.X, scale.get(Vector.X));
            this.scale.add(Vector.Y, scale.get(Vector.Y));
            this.scale.add(Vector.Z, scale.get(Vector.Z));
        } catch(ArrayIndexOutOfBoundsException ex) {

        } finally {
            this.needTransformUpdate = true;
        }
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void  setTranslation(float x, float y, float z) {
        this.translation.set(Vector.X, x);
        this.translation.set(Vector.Y, y);
        this.translation.set(Vector.Y, z);

        this.needTransformUpdate = true;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void setTranslation(Vector translation) {
        try{
            this.translation.set(Vector.X, translation.get(Vector.X));
            this.translation.add(Vector.Y, translation.get(Vector.Y));
            this.translation.add(Vector.Z, translation.get(Vector.Z));
        } catch(ArrayIndexOutOfBoundsException ex) {

        } finally {
            this.needTransformUpdate = true;
        }
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void  setRotation(float x, float y, float z) {
        this.rotation.set(Vector.X, x);
        this.rotation.set(Vector.Y, y);
        this.rotation.set(Vector.Y, z);

        this.needTransformUpdate = true;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void setRotation(Vector rotation) {
        try{
            this.rotation.set(Vector.X, rotation.get(Vector.X));
            this.rotation.add(Vector.Y, rotation.get(Vector.Y));
            this.rotation.add(Vector.Z, rotation.get(Vector.Z));
        } catch(ArrayIndexOutOfBoundsException ex) {

        } finally {
            this.needTransformUpdate = true;
        }
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void setRotation(Vector axis, float angle) {
        try{
            this.rotation.set(Vector.X, axis.get(Vector.X) * angle);
            this.rotation.set(Vector.Y, axis.get(Vector.Y) * angle);
            this.rotation.set(Vector.Z, axis.get(Vector.Z) * angle);
        } catch(ArrayIndexOutOfBoundsException ex) {

        } finally {
            this.needTransformUpdate = true;
        }
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void  setScale(float x, float y, float z) {
        this.scale.set(Vector.X, x);
        this.scale.set(Vector.Y, y);
        this.scale.set(Vector.Y, z);

        this.needTransformUpdate = true;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void setScale(Vector scale) {
        try{
            this.scale.set(Vector.X, scale.get(Vector.X));
            this.scale.add(Vector.Y, scale.get(Vector.Y));
            this.scale.add(Vector.Z, scale.get(Vector.Z));
        } catch(ArrayIndexOutOfBoundsException ex) {

        } finally {
            this.needTransformUpdate = true;
        }
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    protected void updateTransform() {
        /*update the translation matrix*/
        this.mtranslation.set(3, 0, this.translation.get(Vector.X));
        this.mtranslation.set(3, 1, this.translation.get(Vector.Y));
        this.mtranslation.set(3, 2, this.translation.get(Vector.Z));

        /*update rotation matrix*/
        this.mrotation.set(0, 0, (float)(Math.cos(this.rotation.get(Vector.Y))*Math.cos(this.rotation.get(Vector.Z))));
        this.mrotation.set(1, 0, (float)(-Math.cos(this.rotation.get(Vector.X))*Math.sin(this.rotation.get(Vector.Y))
                + Math.sin(this.rotation.get(Vector.X))*Math.sin(this.rotation.get(Vector.Y))*Math.cos(this.rotation.get(Vector.Z))));
        this.mrotation.set(2, 0, (float)(Math.sin(this.rotation.get(Vector.X))*Math.sin(this.rotation.get(Vector.Z)) +
                Math.cos(this.rotation.get(Vector.X))*Math.sin(this.rotation.get(Vector.Y))*Math.cos(this.rotation.get(Vector.Z))));

        this.mrotation.set(0, 1, (float)(Math.cos(this.rotation.get(Vector.Y))*Math.sin(this.rotation.get(Vector.Z))));
        this.mrotation.set(1, 1, (float)(Math.cos(this.rotation.get(Vector.X))*Math.cos(this.rotation.get(Vector.Y)) +
                Math.sin(this.rotation.get(Vector.X))*Math.sin(this.rotation.get(Vector.Y))*Math.sin(this.rotation.get(Vector.Z))));
        this.mrotation.set(2, 1,  (float)(-Math.sin(rotation.get(Vector.X))*Math.cos(this.rotation.get(Vector.Z)) +
                Math.cos(this.rotation.get(Vector.X))*Math.sin(this.rotation.get(Vector.Y))*Math.sin(this.rotation.get(Vector.Z))));

        this.mrotation.set(0, 2, (float)-Math.sin(this.rotation.get(Vector.Y)));
        this.mrotation.set(1, 2, (float)(Math.sin(this.rotation.get(Vector.X))*Math.cos(this.rotation.get(Vector.Y))));
        this.mrotation.set(2, 2, (float)(Math.cos(this.rotation.get(Vector.X)) * Math.cos(this.rotation.get(Vector.Y))));

        /*update scale matrix*/
        this.mscale.set(0, 0, this.scale.get(Vector.X));
        this.mscale.set(1, 1, this.scale.get(Vector.Y));
        this.mscale.set(2, 2, this.scale.get(Vector.Z));

        /*create final transform matrix*/
        try {
            this.mtransform.set(this.mscale.get());
            this.mtransform.multiply(this.mrotation);
            this.mtransform.multiply(this.mtranslation);
        } catch (ArithmeticException e) {
            Logger.log(Logger.PRIORITY.WARNING, e.toString());
        }

        /*now we multiply our final transform with that or our parent to get world transform*/
        try {
            this.mtransform.multiply(this.parent.getTransform());
        } catch (ArithmeticException e) {
            Logger.log(Logger.PRIORITY.WARNING, e.toString());
        }


        this.needTransformUpdate = false;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public Matrix4x4 getTransform() {
        if(this.needTransformUpdate)
            this.updateTransform();

        return this.mtransform;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void addComponent(Component component) {
        this.components.put(component.getType(), component);
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public void removeComponent(int type) {
        this.components.remove(type);
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public Component getComponent(int type) {
        return this.components.get(type);
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    
}
