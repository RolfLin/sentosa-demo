package edu.illinois.adsc.sentosa.query.naive;

import edu.illinois.adsc.sentosa.config.Config;
import edu.illinois.adsc.sentosa.query.Interface.Attraction;
import edu.illinois.adsc.sentosa.query.Interface.IQuery;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.SimpleFormatter;

/**
 * A naive implementation of the IQuery interface
 */
public class NaiveQueryImpl implements IQuery {

    Map<Integer, Attraction> attractions = new HashMap<>();

    final private static NaiveQueryImpl instance = new NaiveQueryImpl();

    private FlowGenerator flowGenerator;

    Calendar date = Calendar.getInstance();

    public static NaiveQueryImpl instance() {
            return instance;
    }

    private NaiveQueryImpl() {
        attractions.put(0, new Attraction(0, 1.254028, 103.823806, "Universal Studio", 9, 30, 18, 0,
                "Universal Studio Singapore is a theme park located within Resorts World Sentosa on Sentosa Island, Singapore."));
        attractions.put(1, new Attraction(1, 1.258549, 103.819314, "Adventure Cove Waterpark", 10, 0, 20, 0));
        attractions.put(2, new Attraction(2, 1.253336, 103.818853, "Sentosa Merlion", 8, 0, 22, 0));
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd,HH:mm");
        try {
            date.setTime(format.parse("2016-12-30,14:00"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        flowGenerator = new FlowGenerator(attractions);
    }

    @Override
    public Collection<Attraction> queryAllAttractions() {
        return attractions.values();
    }

    @Override
    public Collection<Attraction> queryNearByAttractions(final double x, final double y, int max) {
        List<Attraction> ret = new ArrayList<>();
        ret.addAll(attractions.values());
        Collections.sort(ret, new Comparator<Attraction>() {
            @Override
            public int compare(Attraction o1, Attraction o2) {
                double d1 = Math.sqrt((x - o1.x) * (x - o1.x) + (y - o1.y) * (y - o1.y));
                double d2 = Math.sqrt((x - o2.x) * (x - o2.x) + (y - o2.y) * (y - o2.y));
                return Double.compare(d1, d2);
            }
        });
        return ret;
    }

    @Override
    public Attraction getAttractionInfo(int id) {
        return attractions.get(id);
    }

    @Override
    public List<Integer> predicateFlow(int id) {
        return flowGenerator.predicteFlow(id, date.get(Calendar.YEAR), date.get(Calendar.MONTH),
                date.get(Calendar.DAY_OF_MONTH), date.get(Calendar.HOUR_OF_DAY), date.get(Calendar.MINUTE), Config
                        .NumberOfPredicates + 1, Config.PredicateStepInMins);
    }

    @Override
    public List<Integer> predicateQueuingTime(int id) {
        return flowGenerator.predicateQueuingTime(id, date.get(Calendar.YEAR), date.get(Calendar.MONTH),
                date.get(Calendar.DAY_OF_MONTH), date.get(Calendar.HOUR_OF_DAY), date.get(Calendar.MINUTE), Config
                        .NumberOfPredicates + 1, Config.PredicateStepInMins);
    }

    @Override
    public List<Integer> retrieveFlowHistory(int id, int nthDayToReview) {
        Calendar historicalDay = Calendar.getInstance();
        historicalDay.setTime(date.getTime());
        historicalDay.add(Calendar.DATE, -nthDayToReview);
        return flowGenerator.historyFlow(id, historicalDay.get(Calendar.YEAR), historicalDay.get(Calendar.MONTH),
                historicalDay.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public List<Integer> retrieveQueuingTimeHistory(int id, int nthDayToReview) {
        Calendar historicalDay = Calendar.getInstance();
        historicalDay.setTime(date.getTime());
        historicalDay.add(Calendar.DATE, -nthDayToReview);
        return flowGenerator.historyQueuingTime(id, historicalDay.get(Calendar.YEAR), historicalDay.get(Calendar.MONTH),
                historicalDay.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public Calendar getCurrentTime() {
        return date;
    }
}
