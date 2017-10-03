package RandomText;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by VadymStavskyi on 10/3/2017.
 */
public class MarkovModel {
    private String myText;
    private Random myRandom;
    private int charNum;

    public MarkovModel(int chr) {
        myRandom = new Random();
        charNum = chr;
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
        //setRandom(38);
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-charNum);
        String key = myText.substring(index, index+charNum);
        sb.append(key);

        for(int k=0; k < numChars-charNum; k++){
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
