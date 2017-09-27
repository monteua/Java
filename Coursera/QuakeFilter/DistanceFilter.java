package QuakeFilter;

/**
 * Created by VadymStavskyi on 9/25/2017.
 */
public class DistanceFilter implements Filter {

    private Location givenLoc;
    private double maxDistance;

    public DistanceFilter(Location loc, double max) {
        givenLoc = loc;
        maxDistance = max;
    }

    public boolean satisfies(QuakeEntry qe) {
        double currentDis = qe.getLocation().distanceTo(givenLoc);
        return currentDis <= maxDistance;
    }
    public String getName() {
        return "Distance Filter";
    }
}
