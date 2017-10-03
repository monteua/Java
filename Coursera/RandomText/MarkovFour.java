package RandomText;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by VadymStavskyi on 10/3/2017.
 */
public class MarkovFour {
    private String myText;
    private Random myRandom;

    public MarkovFour() {
        myRandom = new Random();
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

    public void setTraining(String s){
        myText = s.trim();
    }

    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        //setRandom(25);
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-4);
        String key = myText.substring(index, index+4);
        sb.append(key);

        for(int k=0; k < numChars-4; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1) + next;
        }

        return sb.toString();
    }

    public ArrayList<String> getFollows(String key) {
        ArrayList<String> result = new ArrayList<>();
        try {
            int idx = myText.indexOf(key);
            while (idx != -1) {
                result.add(myText.substring(idx+key.length(), idx+key.length()+1));
                idx = myText.indexOf(key, idx+1);
            }
        }
        finally {
            return result;
        }
    }
}
