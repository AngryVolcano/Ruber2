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
     * List of users
     */
    ArrayList<User> userList = new ArrayList<User>();

    /**
     * List of trips
     */
    ArrayList<Trip> tripList = new ArrayList<Trip>();

    /**
     * Method to add a trip to the list of trips
     *
     * @param trip: The trip to be added to the trip list
     */
    public void addTrip(Trip trip)
    {
        tripList.add(trip);
    }

    /**
     * A method to get the history of trips for a given user
     * through the user's uuId
     *
     * @param uuid: uuId of the user
     * @return the user's triphistory
     */
    public History getHistory(String uuid)
    {
        // Iterator to iterate through the trip list
        Iterator<Trip> tripIterator = tripList.iterator();
        // New list to contain the user's list
        ArrayList<Trip> userTripList = new ArrayList<Trip>();

        // Iterate through the list og trips
        while(tripIterator.hasNext())
        {
            Trip currentTrip = tripIterator.next();
            // If the user's id on the current trip matches the given uuID add the trip to the new triplist
            if(currentTrip.getUuid().equals(uuid))
                userTripList.add(currentTrip);
        }

        // Create and initialize the user's history
        History userHistory = new History();
        userHistory.setLimit(0);
        userHistory.setOffset(0);
        userHistory.setCount(userTripList.size());
        userHistory.setTrip(userTripList);

        return userHistory;
    }

    /**
     * A method to sign up a new user
     *
     * @param user: The proposed user
     * @throws UsernameExistsException
     */
    public void signup(User user) throws UsernameExistsException
    {
        // Iterater to iterate through the list of users
        Iterator<User> userIterator = userList.iterator();
        while(userIterator.hasNext())
        {
            User currentUser = userIterator.next();
            // If the username of the current user matches the given username throw new exception
            if(currentUser.getUsername().equals(user.getUsername()))
                throw new UsernameExistsException("Username is taken. Please choose something else.");
        }

        // Else add user to the user list
        userList.add(user);
    }

    /**
     * A method to return a list of users
     * max 100 users
     *
     * @param limit: Number of items to retrieve
     * @param offset: Position in pagination
     * @return a list of users
     * @throws UserNotFoundException
     */
    public List<User> getUsers(int limit, int offset) throws UserNotFoundException
    {
        // Throw exception if the userlist is empty
        if(userList.size() <= 0)
            throw new UserNotFoundException("List of users is empty");

        // Throw exception if the given offset is greater than the length of the user list
        if(userList.size() < offset)
            throw new UserNotFoundException("List of users is shorter than the offset");

        // Throw exception if the given limit is greater than the maximum number (100)
        if(100 < limit)
            throw new UserNotFoundException("Limit should not exceed 100");

        // Return a sublist of users, whatever is smaller: the given offset added to the given limit or 100
        return userList.subList(offset, Math.min(offset + limit, 100));
    }

    /**
     * A method to get a single user
     *
     * @param username: username of the User being searched for
     * @return a single User object
     * @throws UserNotFoundException
     */
    public User getUser(String username) throws UserNotFoundException
    {
        // Iterater to iterate through the list of users
        Iterator<User> userIterator = userList.iterator();
        while(userIterator.hasNext())
        {
            User currentUser = userIterator.next();
            // If the given user is found return that user
            if(currentUser.getUsername().equals(username))
                return currentUser;
        }

        throw new UserNotFoundException("There's no user with that username");
    }
}
