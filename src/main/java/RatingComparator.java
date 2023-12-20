import java.util.Comparator;

public class RatingComparator implements Comparator<Double> {

    @Override
    public int compare(Double o1, Double o2) {
        // return o1 > o2 ? 1 : (o1 == o2 ? 0 : -1);
        return o2.compareTo(o1);
    }
}
