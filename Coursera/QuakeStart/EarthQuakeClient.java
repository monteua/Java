package QuakeStart;
import java.util.*;
import edu.duke.*;


public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();

        for (QuakeEntry data : quakeData) {
            double magCurrent = data.getMagnitude();
            if (magCurrent > magMin) {
                answer.add(data);
            }
        }

        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry data : quakeData) {
            Location currentLoc = data.getLocation();
            double disCurrent = from.distanceTo(currentLoc)/1000;
            if (distMax > disCurrent) {
                answer.add(data);
            }
        }

        return answer;
    }

    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        System.out.println("Find quakes with depth between " + minDepth + " and " + maxDepth);
        for (QuakeEntry data : quakeData) {
            double currentDepth = data.getDepth();
            if (currentDepth > minDepth && currentDepth < maxDepth) {
                answer.add(data);
            }
        }

        return answer;
    }

    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String value, String phrase) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();

        for (QuakeEntry data : quakeData) {
            String title = data.getInfo();
            if (value.equals("start")) {
                if (title.startsWith(phrase)) {
                    answer.add(data);
                }
            }
            else if (value.equals("end")) {
                if (title.endsWith(phrase)) {
                    answer.add(data);
                }
            }
            else {
                if (title.contains(phrase)) {
                    answer.add(data);
                }
            }
        }
        System.out.println("Found "+answer.size()+" quakes that have " + phrase + " at " + value);
        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "src/QuakeStart/data/nov20quakedata.atom";
        String source = "src/QuakeStart/data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> bigList = filterByMagnitude(list, 5.0);
        for (QuakeEntry qe : bigList) {
            System.out.println(qe);
        }
        System.out.println("Found "+bigList.size()+" quakes that match that criteria");

    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "src/QuakeStart/data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        //Location city = new Location(35.988, -78.907);

        // This location is Bridgeport, CA
        Location city =  new Location(38.17, -118.82);

        ArrayList<QuakeEntry> closestList = filterByDistanceFrom(list, 1000.0, city);
        for (QuakeEntry qe : closestList) {
            double distance = qe.getLocation().distanceTo(city);
            System.out.println(distance/1000 + " " + qe.getInfo());
        }
        System.out.println("Found "+closestList.size()+" quakes that match that criteria");
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "src/QuakeStart/data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }

    public void quakesOfDepth() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "src/QuakeStart/data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        ArrayList<QuakeEntry> result  = filterByDepth(list, -10000.0, -8000.0);
        for (QuakeEntry qe : result) {
            System.out.println(qe);
        }
        System.out.println("Found "+result.size()+" quakes that match that criteria");
    }

    public void quakesByPhrase() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "src/QuakeStart/data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        //ArrayList<QuakeEntry> result  = filterByPhrase(list, "end", "California");
        ArrayList<QuakeEntry> result  = filterByPhrase(list, "any", "Creek");
        //ArrayList<QuakeEntry> result  = filterByPhrase(list, "start", "Explosion");
        for (QuakeEntry qe : result) {
            System.out.println(qe);
        }

    }
    
}
