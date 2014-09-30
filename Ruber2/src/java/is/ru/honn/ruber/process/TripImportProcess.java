package is.ru.honn.ruber.process;

import is.ru.honn.ruber.domain.TripHandler;
import is.ruframework.process.RuAbstractProcess;

/**
 * Created by sveinn on 9/30/14.
 */
public class TripImportProcess extends RuAbstractProcess implements TripHandler
{

    public void beforeProcess()
    {
        System.out.println("beforeProcess");
    }

    public void startProcess()
    {
        System.out.println("startProcess");
    }

    public void afterProcess()
    {
        System.out.println("afterProcess");
    }
}
