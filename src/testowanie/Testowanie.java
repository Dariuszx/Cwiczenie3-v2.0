package testowanie;

import algorytmy.MergeSort;
import algorytmy.SortingAlgorithm;
import wykres.Wykres;

import java.util.HashMap;
import java.util.Map;

public class Testowanie {

    private HashMap<String, SortingAlgorithm> mapaAlgorytmow;
    private int wielkoscStart, skok, iloscTestow;

    public Testowanie( int wielkoscStart, int skok, int iloscTestow ) {

        this.wielkoscStart = wielkoscStart;
        this.skok = skok;
        this.iloscTestow = iloscTestow;
        mapaAlgorytmow = new HashMap<String, SortingAlgorithm>();

    }

    public void dodajAlgorytm( String nazwa, SortingAlgorithm algorytm ) {

        mapaAlgorytmow.put( nazwa, algorytm );
    }

    public Wykres testuj() {

        Wykres wykres = new Wykres( "Wykres", 800, 600 );

        for( int i = 0; i < iloscTestow; i++ ) {

            for( Map.Entry<String, SortingAlgorithm> entry : mapaAlgorytmow.entrySet() ) {

                int size = wielkoscStart * skok * (i+1);
                LosoweDane losoweDane = new LosoweDane();
                double[] tablica = losoweDane.generuj( size ); //generuję tablice danych
                String key = entry.getKey();
                SortingAlgorithm algorytmSortowania = entry.getValue();

                if( algorytmSortowania instanceof MergeSort ) {

                    System.out.println();
                    for( int j=80; j < 85; j++ )
                        System.out.print( tablica[j] + " ");
                }

                algorytmSortowania.sort( tablica );



                if( i > 0 && i < (iloscTestow - 1) )
                    wykres.addPoint( size, algorytmSortowania.getTime(), key );

            }

        }

        return wykres;

    }

}
