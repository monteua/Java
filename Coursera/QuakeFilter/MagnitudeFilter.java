package QuakeFilter;

/**
 * Created by VadymStavskyi on 9/25/2017.
 */
public class MagnitudeFilter implements Filter {
    private double magMin;
    private double magMax;

    public MagnitudeFilter(double min, double max) {
        magMin = min;
        magMax = max;
    }

    public boolean satisfies(QuakeEntry qe) {
        double currentMag = qe.getMagnitude();
        return currentMag >= magMin && currentMag <= magMax;
    }
    public String getName() {
        return "Magnitude Filter";
    }
}
