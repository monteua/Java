package RandomText;

/**
 * Created by VadymStavskyi on 10/3/2017.
 */
public class testMarkovRunner {
    public static void main(String[] args) {
        MarkovRunner mr = new MarkovRunner();
        //mr.runMarkovZero();
        //mr.runMarkovOne();
        //mr.runMarkovFour();
        mr.runMarkovModel();

        Tester test = new Tester();
        //test.testGetFollows();
        //test.testGetFollowsWithFile();
    }
}
