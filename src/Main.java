import org.jfree.ui.ApplicationFrame;
import wykres.Wykres;

public class Main {

    public static void main( String[] args ) {

        Wykres wykres = new Wykres( "Wykres", 800, 600 );

        wykres.addPoint(10, 20, "Przez scalanie");
        wykres.addPoint(20, 20, "Przez scalanie");
        wykres.addPoint(30, 20, "Przez scalanie");

        wykres.addPoint(50, 20, "Przez wstawianie");
        wykres.addPoint(60, 20, "Przez wstawianie");
        wykres.addPoint(70, 20, "Przez wstawianie");

        wykres.stworzWykres();


    }

}
