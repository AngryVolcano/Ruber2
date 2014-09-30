package is.ru.honn.ruber.domain;

/**
 * POJO class containing trip information.
 */
public class Trip
{
    /**
     * The status of the trip.
     */
    private TripStatus status;
    /**
     * The id of the user who bought the trip.
     */
    private String uuid;
    /**
     * The time when trip was requested.
     */
    private long request_time;
    /**
     * The time when trip was started.
     */
    private long start_time;
    /**
     * The time when trip was finished.
     */
    private long end_time;
    /**
     * The trip's product ID.
     */
    private String product_id;
    /**
     * The total distance of the trip.
     */
    private double distance;
    /**
     * Possible trip statuses.
     */
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
    /**
     * Default constructor.
     */
    public Trip() { }

    public Trip(String uuid, long request_time, long start_time, long end_time, String product_id, double distance)
    {
        this.status = TripStatus.COMPLETED;

        this.uuid = uuid;
        this.request_time = request_time;
        this.start_time = start_time;
        this.end_time = end_time;
        this.product_id = product_id;
        this.distance = distance;
    }

    public TripStatus getStatus() {
        return status;
    }

    public void setStatus(TripStatus status) {
        this.status = status;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public long getRequest_time() {
        return request_time;
    }

    public void setRequest_time(long request_time) {
        this.request_time = request_time;
    }

    public long getStart_time() {
        return start_time;
    }

    public void setStart_time(long start_time) {
        this.start_time = start_time;
    }

    public long getEnd_time() {
        return end_time;
    }

    public void setEnd_time(long end_time) {
        this.end_time = end_time;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
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
