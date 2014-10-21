package wykres;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

import java.awt.*;
import java.util.*;

public class Wykres extends ApplicationFrame {

    private HashMap<String, Linia> tablicaLinii;
    private JFreeChart wykres;
    private ChartPanel wykresPanel;
    private XYSeriesCollection kolekcjaWykresow;
    private Dimension windowSize;


    public Wykres( String title, int width, int height ) {

        super( title );
        tablicaLinii = new HashMap<String, Linia>();
        windowSize = new Dimension( width, height );
    }

    //dodawanie punktów
    public void addPoint( int czas, int iloscElementow, String key ) {

        Linia linia = tablicaLinii.get( key );

        if( linia != null )
            linia.addPoint(new Punkt(iloscElementow, czas));
        else {
            linia = new Linia( key );
            linia.addPoint( new Punkt( iloscElementow, czas ) );
            tablicaLinii.put( key, linia );
        }
    }

    //tworzę zestaw danych gotowych do wyświetlenia na ekranie
    private void stworzZestawDanych() {

        kolekcjaWykresow = new XYSeriesCollection();

        for( Map.Entry<String, Linia> entry : tablicaLinii.entrySet() ) {

            ArrayList<Punkt> listaPunktow = entry.getValue().getPunkty();

            for( Punkt punkt : listaPunktow )
                entry.getValue().add( punkt.getY(), punkt.getX() ); //dodaję punkt do linii

            kolekcjaWykresow.addSeries( entry.getValue() );
        }

    }

    //wyświetlam wykres
    public void stworzWykres() {

        stworzZestawDanych();

        wykres = ChartFactory.createXYLineChart(
                                                "Działanie poszczególnych algorytmów",      // tytuł
                                                "Ilosc elementow",                          // x
                                                "Czas us",                                  // y
                                                kolekcjaWykresow,                           // dane
                                                PlotOrientation.VERTICAL,
                                                true,                                       // legenda
                                                true,                                       // tooltips
                                                false                                       // urls
                                            );


        wykres.setBackgroundPaint(Color.white);

        final XYPlot plot = wykres.getXYPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);

        final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(2, false);
        renderer.setSeriesShapesVisible(1, false);
        plot.setRenderer(renderer);


        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());


        //Ustawienia okna
        wykresPanel = new ChartPanel( wykres );

        wykresPanel.setPreferredSize( windowSize );

        setContentPane( wykresPanel );
        setSize( windowSize );
        setLocation();
        setVisible( true );
    }

    private void setLocation() {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int)(screenSize.getWidth()/2 - windowSize.width / 2);
        int y = (int)(screenSize.getHeight()/2 - windowSize.height / 2);
        setLocation( x, y );

    }

}
