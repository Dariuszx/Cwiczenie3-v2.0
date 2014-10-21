package testowanie;


import java.util.Random;

public class LosoweDane {

    private static double generujLosowaLiczbe() {

        Random random = new Random();

        double randomNumber = random.nextDouble();

        return randomNumber;
    }

    public double[] generuj(  int n ) {

        double[] tablica = new double[n];
        Random random = new Random();

        for( int i = 0; i < n; i++ ) {

            tablica[i] = random.nextDouble() * 100;
        }

        return tablica;
    }

    public static void wypisz( double[] tablica ) {

        System.out.println( "Wypisuję podaną tablicę: ");
        System.out.println();

        for( int i=0; i < tablica.length; i++ ) {
            System.out.print( tablica[i] + " ");
        }

    }

}
