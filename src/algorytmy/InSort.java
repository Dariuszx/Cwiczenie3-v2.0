package algorytmy;

public class InSort implements SortingAlgorithm {

    private long start;
    private long end;

    private long dt;

    @Override
    public double[] sort( double[] unsortedVector ) {

        start = System.nanoTime();

        int i, j, size = unsortedVector.length;
        double x;

        for(j = size - 2; j >= 0; j--)
        {
            x = unsortedVector[j];
            i = j + 1;

            while( (i < size) && (x > unsortedVector[i]) )
            {
                unsortedVector[i - 1] = unsortedVector[i];
                i++;
            }

            unsortedVector[i - 1] = x;
        }


        end = System.nanoTime();

        return unsortedVector;
    }

    public int getTime() {
        return (int)(end - start);
    }

}
