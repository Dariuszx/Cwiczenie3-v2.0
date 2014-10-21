import algorytmy.InSort;
import algorytmy.MergeSort;
import testowanie.Testowanie;
import wykres.Wykres;

public class Main {

    public static void main( String[] args ) {

        Testowanie testowanie = new Testowanie( 100, 50, 10 );
        testowanie.dodajAlgorytm( "Sortowanie przez wstawianie", new InSort() );
        testowanie.dodajAlgorytm( "Sortowanie przez scalanie", new MergeSort() );

        Wykres wykres = testowanie.testuj();

        wykres.stworzWykres();

    }

}
