package is.ru.honn.ruber.process;

/**
 * Created by siddi on 30/09/14.
 */
public abstract class TripReader implements Reader
{
    protected TripHandler tripHandler;

    /**
     *
     * @param tripHandler
     */
    @Override
    public void setTripHandler(TripHandler tripHandler)
    {
        this.tripHandler = tripHandler;
    }

    /**
     *
     * @param importURL
     * @throws ReadException
     */
    @Override
    public abstract void read(String importURL) throws ReadException;

}
