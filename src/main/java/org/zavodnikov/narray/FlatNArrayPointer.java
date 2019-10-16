package org.zavodnikov.narray;

/**
 * @author Dmitry Zavodnikov (d.zavodnikov@gmail.com)
 */
public class FlatNArrayPointer implements INArrayPointer {

    // Number of dimensions into the source n-dimension array.
    private final int[] dimentsions;

    // Index values.
    private final int[] indexes;
    
    // Pre-calculated offsets for better performance.
    private final int[] offsets;

    // Pre-calculated pointer in target array for better performance.
    private int         pointer;


    public FlatNArrayPointer(final int... dimensions) {
        if (dimensions == null || dimensions.length == 0) {
            throw new IllegalArgumentException("You are should define at least 1 dimension!");
        }
        
        this.offsets = new int[dimensions.length];
        
        this.dimentsions = new int[dimensions.length];
        for (int i = 0; i < dimensions(); ++i) {
        	dimension(i, dimensions[i]);
        }

        this.indexes = new int[dimensions.length];
        this.pointer = 0;
    }
    
    private void checkDimensionValue(final int dimensionNum) {
        if (dimensionNum < 0) {
            throw new IllegalArgumentException(
                String.format("Dimension number (=\"%d\") can not be less than 0!", dimensionNum));
        }
        if (dimensionNum >= dimensions()) {
            throw new IllegalArgumentException(
                String.format("Dimension number (=\"%d\") can not be more or equals than dimension size (=%d)!",
                    dimensionNum, dimensions()));
        }
    }
    
    private void offset(final int dimensionNum) {
        long offset = 1;

        for (int j = dimensionNum - 1; j >= 0; --j) {
            offset *= dimension(j);

            final long maxPosition = offset * dimension(dimensionNum);
            if (maxPosition > Integer.MAX_VALUE) {
                throw new IllegalArgumentException(
                    "Can not address such array -- size will be more than can be addressed!");
            }
        }

        this.offsets[dimensionNum] = (int) offset;
    }
    
	@Override
	public void dimension(int dimensionNum, int dimensionSize) {
		checkDimensionValue(dimensionNum);
    	if (dimensionSize == 0) {
            throw new IllegalArgumentException(
                String.format("Dimension \"%d\" can not be less or equals that 0 (= %d)!", dimensionNum, dimensionSize));
        }
		
		this.dimentsions[dimensionNum] = dimensionSize;
		offset(dimensionNum);
	}

    @Override
    public int dimension(final int dimensionNum) {
        checkDimensionValue(dimensionNum);

        return this.dimentsions[dimensionNum];
    }

    @Override
    public int dimensions() {
        return this.dimentsions.length;
    }
	
    @Override
    public int size() {
        final int latestDim = dimensions() - 1;
        return this.offsets[latestDim] * dimension(latestDim);
    }
   

    @Override
    public void index(final int dimensionNum, final int index) {
        checkDimensionValue(dimensionNum);
        if (index < 0) {
            throw new IllegalArgumentException(String.format("Index (=\"%d\") can not be less than 0!", index));
        }
        if (index > dimension(dimensionNum)) {
            throw new IllegalArgumentException(String.format(
                "Index (=\"%d\") can not be more that dimension size (=%d)!", index, dimension(dimensionNum)));
        }
        
        this.pointer += (index - this.indexes[dimensionNum]) * this.offsets[dimensionNum];
        
        this.indexes[dimensionNum] = index;
    }
    
    @Override
    public int index(final int dimensionNum) {
    	checkDimensionValue(dimensionNum);
    	
        return this.indexes[dimensionNum];
    }

    @Override
    public int index() {
        return this.pointer;
    }
}
