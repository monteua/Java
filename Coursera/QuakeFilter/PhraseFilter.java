package QuakeFilter;

/**
 * Created by VadymStavskyi on 9/25/2017.
 */
public class PhraseFilter implements Filter {

    private String position;
    private String phrase;

    public PhraseFilter(String pos, String phr) {
        position = pos;
        phrase = phr;
    }

    public boolean satisfies(QuakeEntry qe) {
        String title = qe.getInfo();
        if (position.equals("start")) {
            return title.startsWith(phrase);
        } else if (position.equals("end")) {
            return title.endsWith(phrase);
        } else {
            return title.contains(phrase);
        }
    }

    public String getName() {
        return "Phrase Filter";
    }
}
