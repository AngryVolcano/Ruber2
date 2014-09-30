package is.ru.honn.ruber.service;

import is.ru.honn.ruber.domain.Trip;
import is.ru.honn.ruber.domain.User;

/**
 * Simple test class
 */
public class Runner
{
    public static void main(String[] args) {
        Trip trip = new Trip("123", 123123123,123321321,123123123,"321321", 23.4);
        User user = new User("111","jonsi","jon","jonsson","nonnabiti","jon@jonar.com","45C4","RUBER2014");

        System.out.println(trip.toString());
        System.out.println(user.toString());
    }
}
