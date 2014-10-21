import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;
import java.util.*;

public class Wykres {

    public HashMap<String, Linia> tablicaLinii;
    private JFreeChart wykres;
    private ChartPanel wykresPanel;
    private XYSeriesCollection kolekcjaWykresow;


    public Wykres() {

        tablicaLinii = new HashMap<String, Linia>();
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
                entry.getValue().add( punkt.getX(), punkt.getY() ); //dodaję punkt do linii

            kolekcjaWykresow.addSeries( entry.getValue() );
        }

    }

    //wyświetlam wykres
    public ChartPanel stworzWykres() {

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

        wykresPanel.setPreferredSize(new java.awt.Dimension( 500, 270 ) );

        return wykresPanel;
    }

}
