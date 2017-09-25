package QuakeStart;
import java.util.*;
/**
 * Created by VadymStavskyi on 9/22/2017.
 */
public class LargestQuakes {

    public void findLargestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "src/QuakeStart/data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("EarthQuakes found: "+list.size());

        ArrayList<QuakeEntry> largest = getLargest(list, 5);
        for (QuakeEntry qe : largest) {
            System.out.println(qe);
        }
    }

    public int indexOfLargest(ArrayList<QuakeEntry> data) {
        double maxMag = 0.0;
        int idx = 0;

        for (int i=0; i<data.size(); i++) {
            double currentMag = data.get(i).getMagnitude();
            if (currentMag > maxMag) {
                maxMag = currentMag;
                idx = i;
            }
        }
        return idx;
    }

    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany) {
        ArrayList<QuakeEntry> result = new ArrayList<>();

        for (int i=0; i<howMany; i++) {
            int idx = indexOfLargest(quakeData);
            result.add(quakeData.get(idx));
            quakeData.remove(quakeData.get(idx));
        }
        return result;
    }
}
