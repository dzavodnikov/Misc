package org.zavodnikov.narray;

/**
 * @author Dmitry Zavodnikov (d.zavodnikov@gmail.com)
 */
public class FlatNArrayPointer implements INArrayPointer {

    // Number of dimensions into the source n-dimension array.
    private final int[] dimentsions;

    // Pre-calculated offsets for better performance.
    private final int[] offsets;

    // Index values.
    private final int[] indexes;

    // Pointer in target array.
    private int         pointer;

    // Copy to prevent side effects.
    private void copyDimensionsWithChecking(final int[] dimensions) {
        for (int i = 0; i < getDimensionsNum(); ++i) {
            if (dimensions[i] == 0) {
                throw new IllegalArgumentException(
                    String.format("Dimension \"%d\" can not be less or equals that 0 (= %d)!", i, dimensions[i]));
            }

            this.dimentsions[i] = dimensions[i];
        }
    }

    private void initOffsets() {
        for (int i = 0; i < getDimensionsNum(); ++i) {
            this.offsets[i] = 1;

            for (int j = i + 1; j < getDimensionsNum(); ++j) {
                this.offsets[i] *= getDimension(j);
            }
        }

        // TODO
        for (int i = 0; i < getDimensionsNum(); ++i) {
            System.out.print(String.format("%d ", this.offsets[i]));
        }
        System.out.println();
    }

    public FlatNArrayPointer(final int... dimensions) {
        if (dimensions.length == 0) {
            throw new IllegalArgumentException("You are should define at least 1 dimension!");
        }
        this.dimentsions = new int[dimensions.length];
        copyDimensionsWithChecking(dimensions);

        this.offsets = new int[getDimensionsNum()];
        initOffsets();

        this.indexes = new int[getDimensionsNum()];
    }

    @Override
    public int getIndex(final int dimensionNum, final int index) {
        return this.indexes[dimensionNum];
    }

    private void checkDimensionValue(final int dimensionNum) {
        if (dimensionNum < 0) {
            throw new IllegalArgumentException(
                String.format("Dimension number (=\"%d\") can not be less than 0!", dimensionNum));
        }
        if (dimensionNum >= getDimensionsNum()) {
            throw new IllegalArgumentException(
                String.format("Dimension number (=\"%d\") can not be more or equals than dimension size (=%d)!",
                    dimensionNum, getDimensionsNum()));
        }
    }

    private void checkIndexValue(final int dimensionNum, final int index) {
        if (index < 0) {
            throw new IllegalArgumentException(String.format("Index (=\"%d\") can not be less than 0!", index));
        }
        if (index > getDimension(dimensionNum)) {
            throw new IllegalArgumentException(String.format(
                "Index (=\"%d\") can not be more that dimension size (=%d)!", index, getDimension(dimensionNum)));
        }
    }

    private void updatePointerValue(final int dimensionNum, final int index) {
        this.pointer += (index - this.indexes[dimensionNum]) * this.offsets[dimensionNum];
    }

    private void setPointerValue(final int dimensionNum, final int index) {
        this.indexes[dimensionNum] = index;
    }

    @Override
    public void setIndex(final int dimensionNum, final int index) {
        checkDimensionValue(dimensionNum);
        checkIndexValue(dimensionNum, index);
        updatePointerValue(dimensionNum, index);
        setPointerValue(dimensionNum, index);
    }

    @Override
    public int getPosition() {
        return this.pointer;
    }

    @Override
    public int getDimension(final int dimensionNum) {
        return this.dimentsions[dimensionNum];
    }

    @Override
    public int getDimensionsNum() {
        return this.dimentsions.length;
    }
}
