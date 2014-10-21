package wykres;

import org.jfree.data.xy.XYSeries;

import java.util.ArrayList;

public class Linia extends XYSeries {

    private ArrayList<Punkt> punkty;

    public Linia( String key ) {
        super(key);
        punkty = new ArrayList<Punkt>();

    }

    public void addPoint( Punkt punkt ) {
        punkty.add( punkt );
    }

    public ArrayList<Punkt> getPunkty() { return punkty; }

}
