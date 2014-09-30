package is.ru.honn.ruber.domain;

/**
 *
 */
public class Trips
{
    private TripStatus status;

    private String uuid;
    private long request_time, start_time, end_time;
    private String product_id;
    private double distance;

    public enum TripStatus
    {
        COMPLETED;

        @Override
        public String toString()
        {
            switch (this)
            {
                case COMPLETED: return "completed";
                default: throw new IllegalArgumentException("not completed");
            }
        }
    }

    public Trips() { }

    public Trips(String uuid, long request_time, long start_time, long end_time, String product_id, double distance)
    {
        this.status = TripStatus.COMPLETED;

        this.uuid = uuid;
        this.request_time = request_time;
        this.start_time = start_time;
        this.end_time = end_time;
        this.product_id = product_id;
        this.distance = distance;
    }

    @Override
    public String toString()
    {
        return "Trips{" +
                "status=" + status +
                ", uuid='" + uuid + '\'' +
                ", request_time=" + request_time +
                ", start_time=" + start_time +
                ", end_time=" + end_time +
                ", product_id='" + product_id + '\'' +
                ", distance=" + distance +
                '}';
    }
}
