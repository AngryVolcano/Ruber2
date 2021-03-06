package is.ru.honn.ruber.service;

import is.ru.honn.ruber.domain.*;

import java.util.List;

public interface RuberService
{
  public List<Product> getProducts(double latitude, double longitude) throws ServiceException;
  public List<Price> getPriceEstimates(double startLatitude, double startLongitude,
                                       double endLatitude, double endLongitude) throws ServiceException;

    public void addTrip(Trip trip);
    public History getHistory(String uuid);
    public void signup(User user);
    public List<User> getUsers(int limit, int offset);
    public User getUser(String username);

}
