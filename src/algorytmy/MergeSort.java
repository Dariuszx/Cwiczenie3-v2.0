package algorytmy;


public class MergeSort implements SortingAlgorithm {


    private long start;
    private long end;
    private long dt;

    private double d[], p[];


    private void mergeSort( int ip, int ik ) {

        start = System.nanoTime();
        int i_s, i1, i2, i;


        i_s = (ip + ik + 1) / 2; //Środkowy indeks tablicy

        if(i_s - ip > 1) mergeSort(ip, i_s - 1);
        if(ik - i_s > 0) mergeSort(i_s, ik);

        i1 = ip; i2 = i_s;

        for(i = ip; i <= ik; i++) {

            p[i] = ((i1 == i_s) || ((i2 <= ik) && (d[i1] > d[i2]))) ? d[i2++] : d[i1++];

        }

        for(i = ip; i <= ik; i++)
            d[i] = p[i];

        end = System.nanoTime();

        dt += end - start;

    }

    @Override
    public double[] sort( double[] unsortedVector ) {



        d = unsortedVector;
        p = new double[unsortedVector.length ];

        mergeSort( 0, unsortedVector.length - 1 );




        return new double[0];
    }

    @Override
    public int getTime() {
        return (int)dt;
    }
}
