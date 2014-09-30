package is.ru.honn.ruber.service;

import is.ru.honn.ruber.domain.Trips;
import is.ru.honn.ruber.domain.User;

/**
 * Created by sveinn on 9/30/14.
 */
public class Runner
{
    public static void main(String[] args) {
        Trips trip = new Trips("123", 123123123,123321321,123123123,"321321", 23.4);
        User user = new User("111","oli","olafur","jonsson","hotcocks","prettyboy@pretty.com","45C4","TVIUND2013");

        System.out.println(trip.toString());
        System.out.println(user.toString());
    }
}
