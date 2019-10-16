package org.zavodnikov.narray;

/**
 * @author Dmitry Zavodnikov (d.zavodnikov@gmail.com)
 */
public interface INArrayPointer {
	
	void dimension(int dimensionNum, int dimensionSize);
	
	int dimension(int dimensionNum);
	
	int dimensions();

    int size();
    
	void index(int dimensionNum, int index);

    int index(int dimensionNum);

    int index();
}
