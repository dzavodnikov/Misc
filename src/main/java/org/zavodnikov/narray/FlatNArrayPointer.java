package org.zavodnikov.narray;

/**
 * @author Dmitry Zavodnikov (d.zavodnikov@gmail.com)
 */
public class FlatNArrayPointer implements INArrayPointer {

    private final int   size;

    private final int[] dimentsions;

    private final int[] offsets;

    private final int[] pointers;

    private int         pointer;

    public FlatNArrayPointer(final int size, final int... dimensions) {
        if (size < 0) {
            throw new IllegalArgumentException(String.format("Size (=%d) can not be less than 0!", size));
        }
        this.size = size;

        if (dimensions.length == 0) {
            throw new IllegalArgumentException("You are should define at least 1 dimension!");
        }

        // Copy to prevent side effects.
        this.dimentsions = new int[dimensions.length];
        for (int i = 0; i < this.dimentsions.length; ++i) {
            if (dimensions[i] == 0) {
                throw new IllegalArgumentException(
                    String.format("Dimension \"%d\" can not be less or equals that 0 (= %d)!", i, dimensions[i]));
            }

            this.dimentsions[i] = dimensions[i];
        }

        this.offsets = new int[this.dimentsions.length];
        this.offsets[0] = 1;
        for (int i = 1; i < this.dimentsions.length; ++i) {
            this.offsets[i] = this.offsets[i - 1] * this.dimentsions[i];
        }

        this.pointers = new int[this.dimentsions.length];
    }

    @Override
    public int getIndex(final int dimensionNum, final int index) {
        return this.pointers[dimensionNum];
    }

    @Override
    public void setIndex(final int dimensionNum, final int index) {
        if (index < 0) {
            throw new IllegalArgumentException(String.format("Index (=\"%d\") can not be less than 0!", index));
        }
        if (index >= this.dimentsions[dimensionNum]) {
            throw new IllegalArgumentException(
                String.format("Index (=\"%d\") can not be more or equals that dimension size (=%d)!", index,
                    this.dimentsions[dimensionNum]));
        }
        this.pointer += (this.pointers[dimensionNum] - index) * this.offsets[dimensionNum];
        this.pointers[dimensionNum] = index;
    }

    @Override
    public int getPosition() {
        return this.pointer;
    }

    @Override
    public int getLength() {
        return this.size * this.dimentsions[this.dimentsions.length - 1];
    }
}
