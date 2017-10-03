package RandomText;

import edu.duke.FileResource;
import java.util.ArrayList;

/**
 * Created by VadymStavskyi on 10/3/2017.
 */
public class Tester {
    public void testGetFollows(){
        MarkovOne mo = new MarkovOne();
        String text = "this is a test yes this is a test.";
        mo.setTraining(text);
        ArrayList<String> result = mo.getFollows("t");
        System.out.println(result.size() + "\n" + result);
    }

    public void testGetFollowsWithFile() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovOne markov = new MarkovOne();
        markov.setTraining(st);

        ArrayList<String> result = markov.getFollows("th");
        System.out.println(result.size() + "\n" + result);
    }
}
