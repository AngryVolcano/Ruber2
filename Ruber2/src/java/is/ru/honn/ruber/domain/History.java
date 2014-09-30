package is.ru.honn.ruber.domain;

import java.util.ArrayList;

/**
 * POJO class containing info on the trip history of a user.
 */
public class History
{
    /**
     * Position in pagination.
     */
    private int offset;
    /**
     * Number of items to retrieve, max 100.
     */
    private int limit;
    /**
     * Total trips taken by the user.
     */
    private int count;
    /**
     * List of trips the user has taken.
     */
    private ArrayList<Trip> trip = new ArrayList<Trip>();

    /**
     * Default constructor.
     */
    public History() { }

    public History(int offset, int limit, int count, ArrayList<Trip> trip)
    {
        this.offset = offset;
        this.limit = limit;
        this.count = count;
        this.trip = trip;
    }

    public int getOffset() { return offset; }

    public void setOffset(int offset) { this.offset = offset; }

    public int getLimit() { return limit; }

    public void setLimit(int limit) { this.limit = limit; }

    public int getCount() { return count; }

    public void setCount(int count) { this.count = count; }

    public ArrayList<Trip> getTrip() { return trip; }

    public void setTrip(ArrayList<Trip> trip) { this.trip = trip; }

    @Override
    public String toString()
    {
        return "History{" +
                "offset=" + offset +
                ", limit=" + limit +
                ", count=" + count +
                ", trip=" + trip +
                '}';
    }
}
