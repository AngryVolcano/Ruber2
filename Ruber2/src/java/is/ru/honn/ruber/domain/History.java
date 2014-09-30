package is.ru.honn.ruber.domain;

import java.util.ArrayList;

/**
 *
 */
public class History
{
    private int offset, limit, count;
    private ArrayList<Trips> trip = new ArrayList<Trips>();

    public History() { }

    public History(int offset, int limit, int count, ArrayList<Trips> trip)
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

    public ArrayList<Trips> getTrip() { return trip; }

    public void setTrip(ArrayList<Trips> trip) { this.trip = trip; }

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
