package is.ru.honn.ruber.process;

import is.ruframework.http.SimpleHttpRequest;
import is.ru.honn.ruber.domain.History;
import is.ru.honn.ruber.domain.Trip;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by sveinn on 9/30/14.
 */
public class TripHistoryReader extends TripReader
{
    JSONParser parser = new JSONParser();

    /**
     *
     * @param importURL
     */
    @Override
    public void read(String importURL) {
        try {
            History history = new History();
            ArrayList<Trip> tripList = new ArrayList<Trip>();

            JSONObject jsonObject = (JSONObject) parser.parse(SimpleHttpRequest.sendGetRequest(importURL));

            history.setOffset(Integer.parseInt(jsonObject.get("offset").toString()));
            history.setCount(Integer.parseInt(jsonObject.get("count").toString()));
            history.setLimit(Integer.parseInt(jsonObject.get("limit").toString()));

            JSONArray jsonTripArray = (JSONArray) jsonObject.get("history");

            Iterator<JSONObject> iter = jsonTripArray.iterator();
            while(iter.hasNext())
            {
                JSONObject next = iter.next();

            }
        }
        catch(IOException e)
        {

        }
    }
}
