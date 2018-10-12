package org.zavodnikov.narray;

/**
 * @author Dmitry Zavodnikov (d.zavodnikov@gmail.com)
 */
public interface INArrayPointer {

    int getIndex(int dimensionNum, int index);

    void setIndex(int dimensionNum, int index);

    int getLength();

    int getPosition();

    int getDimension(int dimensionNum);

    int getDimensionsNum();
}
