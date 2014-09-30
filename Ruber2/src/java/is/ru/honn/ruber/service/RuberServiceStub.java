package is.ru.honn.ruber.service;

import is.ru.honn.ruber.domain.*;
import is.ruframework.domain.RuObject;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class RuberServiceStub extends RuObject implements RuberService
{
    @Override
    public List getProducts(double latitude, double longitude) throws ServiceException
    {
        JSONParser parser = new JSONParser();
        List productList = new ArrayList();

        try
        {
            JSONObject json = (JSONObject) parser.parse(new FileReader("products.json"));
            JSONArray products = (JSONArray) json.get("products");

            for (int i = 0;
               i < products.size();
               i++)
            {
                JSONObject jsonProduct = (JSONObject) products.get(i);
                Product product = new Product();
                product.setImage((String) jsonProduct.get("image"));
                product.setProduct_id((String) jsonProduct.get("product_id"));
                product.setDescription((String) jsonProduct.get("description"));
                product.setDisplay_name((String) jsonProduct.get("display_name"));
                product.setCapacity(((Long) jsonProduct.get("capacity")).intValue());
                productList.add(product);
            }
        }
        catch (Exception e)
        {
            String tmp = "Unable to read products.json file.";
            log.severe(tmp);
            throw new ServiceException(tmp, e);
        }

        return productList;
    }

        @Override
        public List<Price> getPriceEstimates(double start_latitude, double start_longitude,
                                           double end_latitude, double end_longitude) throws ServiceException
        {
            List<Price> priceList = new ArrayList<Price>();
            priceList.add(new Price("08f17084-23fd-4103-aa3e-9b660223934b", "USD", "UberBLACK", 23, 29, 1));
            priceList.add(new Price("9af0174c-8939-4ef6-8e91-1a43a0e7c6f6", "USD", "UberSUV", 36, 44, 1.25));
            priceList.add(new Price("aca52cea-9701-4903-9f34-9a2395253acb", null, "uberTAXI", -1, -1, 1));
            priceList.add(new Price("a27a867a-35f4-4253-8d04-61ae80a40df5", "USD", "uberX", 15, 15, 1));
            return priceList;
        }

    /**
     *
     */
    ArrayList<User> userList = new ArrayList<User>();
    /**
     *
     */
    ArrayList<Trip> tripList = new ArrayList<Trip>();

    /**
     *
     * @param trip
     */
    public void addTrip(Trip trip)
    {
        tripList.add(trip);
    }

    /**
     *
     * @param uuid
     * @return
     */
    public History getHistory(String uuid)
    {
        Iterator<Trip> tripIterator = tripList.iterator();
        ArrayList<Trip> userTripList = new ArrayList<Trip>();

        while(tripIterator.hasNext())
        {
            Trip currentTrip = tripIterator.next();
            if(currentTrip.getUuid().equals(uuid))
                userTripList.add(currentTrip);
        }

        History userHistory = new History();

        userHistory.setLimit(0);
        userHistory.setOffset(0);
        userHistory.setCount(userTripList.size());
        userHistory.setTrip(userTripList);

        return userHistory;
    }

    /**
     *
     * @param user
     * @throws UsernameExistsException
     */
    public void signup(User user) throws UsernameExistsException
    {
        Iterator<User> userIterator = userList.iterator();
        while(userIterator.hasNext())
        {
            User currentUser = userIterator.next();
            if(currentUser.getUsername().equals(user.getUsername()))
                throw new UsernameExistsException("Username is taken. Please choose something else.");
        }

        userList.add(user);
    }

    /**
     *
     * @param limit
     * @param offset
     * @return
     * @throws UserNotFoundException
     */
    public List<User> getUsers(int limit, int offset) throws UserNotFoundException
    {
        //
        if(userList.size() <= 0)
            throw new UserNotFoundException("List of users is empty");

        //
        if(userList.size() < offset)
            throw new UserNotFoundException("List of users is shorter than the offset");

        //
        if(100 < limit)
            throw new UserNotFoundException("Limit should not exceed 100");

        //
        return userList.subList(offset, Math.min(offset + limit, 100));
    }

    /**
     *
     * @param username
     * @return
     * @throws UserNotFoundException
     */
    public User getUser(String username) throws UserNotFoundException
    {
        Iterator<User> userIterator = userList.iterator();

        while(userIterator.hasNext())
        {
            User currentUser = userIterator.next();
            if(currentUser.getUsername().equals(username))
                return currentUser;
        }

        throw new UserNotFoundException("There's no user with that username");
    }
}
