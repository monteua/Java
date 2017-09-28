package EfficientSort;

/**
 * Created by VadymStavskyi on 9/28/2017.
 */
public class testEfficientSort {
    public static void main(String[] args) {
        DifferentSorters ds = new DifferentSorters();
        //ds.sortWithCompareTo();
        //ds.sortByTitleAndDepth();
        ds.sortByLastWordInTitleThenByMagnitude();
    }
}
