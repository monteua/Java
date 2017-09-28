package EfficientSort;

import java.util.Comparator;

/**
 * Created by VadymStavskyi on 9/28/2017.
 */
public class TitleAndDepthComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        String title1 = q1.getInfo();
        String title2 = q2.getInfo();

        if (new String(title1).equals(title2)) {
            double q1Depth = q1.getDepth();
            double q2Depth = q2.getDepth();
            return Double.compare(q1Depth, q2Depth);
        }
        return title1.compareTo(title2);
    }
}
