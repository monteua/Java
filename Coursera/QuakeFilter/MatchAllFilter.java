package QuakeFilter;

import java.util.ArrayList;

/**
 * Created by VadymStavskyi on 9/25/2017.
 */
public class MatchAllFilter implements Filter {
    private ArrayList<Filter> filters;

    public MatchAllFilter() {
        filters = new ArrayList<Filter>();
    }

    public void addFilter(Filter f) {
        filters.add(f);
    }

    public boolean satisfies(QuakeEntry qe) {
        for (Filter f: filters) {
            if (!f.satisfies(qe)) {
                return false;
            }
        }
        return true;
    }

    public String getName() {
        String result = "";

        for (Filter f: filters) {
            result += f.getName();
            result += ", ";
        }
        return result;
    }
}
