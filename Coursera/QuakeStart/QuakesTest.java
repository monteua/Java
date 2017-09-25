package QuakeStart;


/**
 * Created by VadymStavskyi on 9/22/2017.
 */
public class QuakesTest {
    public static void main(String[] args) {
        ClosestQuakes cq = new ClosestQuakes();
        //cq.findClosestQuakes();

        EarthQuakeClient eqc = new EarthQuakeClient();
        //eqc.bigQuakes();
        //eqc.closeToMe();
        //eqc.quakesOfDepth();
        //eqc.quakesByPhrase();

        //ClosestQuakes cq = new ClosestQuakes();
        //cq.findClosestQuakes();

        LargestQuakes lq = new LargestQuakes();
        lq.findLargestQuakes();
    }
}
