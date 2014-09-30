package is.ru.honn.ruber.process;

import is.ru.honn.ruber.domain.Trip;
import is.ru.honn.ruber.service.RuberService;
import is.ruframework.process.RuAbstractProcess;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.logging.Logger;

/**
 * @author Siddi/Sveinn
 * @since 29.09.2014
 *
 * Process to import trips
 */
public class TripImportProcess extends RuAbstractProcess implements TripHandler
{
    /**
     *
     */
    RuberService ruberService;

    /**
     * The active trip reader
     */
    TripReader reader;

    Logger logger = Logger.getLogger(this.getClass().getName());

    /**
     * Process a trip
     *
     * @param trip
     */
    @Override
    public void processTrip(Trip trip)
    {
        ruberService.addTrip(trip);
    }

    /**
     * Run before process start
     */
    @Override
    public void beforeProcess()
    {
        ApplicationContext context = new FileSystemXmlApplicationContext("app.xml");
        ruberService = (RuberService) context.getBean("RuberService");

        reader = (TripReader) context.getBean("TripReader");

        reader.setTripHandler(this);
    }

    /**
     * Run at the start of the process
     */
    @Override
    public void startProcess()
    {
        logger.info("Start Process");

        reader.read(getProcessContext().getImportURL());

        System.out.println("startProcess");
    }

    /**
     * Run after process
     */
    @Override
    public void afterProcess()
    {
        logger.info("After Process");
    }
}
