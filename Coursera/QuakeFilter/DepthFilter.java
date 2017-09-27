package QuakeFilter;

/**
 * Created by VadymStavskyi on 9/25/2017.
 */
public class DepthFilter implements Filter {

    private double minDepth;
    private double maxDepth;

    public DepthFilter(double min, double max) {
        minDepth = min;
        maxDepth = max;
    }

    public boolean satisfies(QuakeEntry qe) {
        double currentDepth = qe.getDepth();
        return currentDepth >= minDepth && currentDepth <= maxDepth;
    }
    public String getName() {
        return "Depth Filter";
    }
}
