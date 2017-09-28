package EfficientSort;

import java.util.Comparator;

/**
 * Created by VadymStavskyi on 9/28/2017.
 */
public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        String[] title1 = q1.getInfo().split(" ");
        String[] title2 = q2.getInfo().split(" ");

        String lastWord1 = title1[title1.length-1];
        String lastWord2 = title2[title2.length-1];
        if (new String(lastWord1).equals(lastWord2)) {
            double q1Depth = q1.getMagnitude();
            double q2Depth = q2.getMagnitude();
            return Double.compare(q1Depth, q2Depth);
        }
        return lastWord1.compareTo(lastWord2);
    }
}
