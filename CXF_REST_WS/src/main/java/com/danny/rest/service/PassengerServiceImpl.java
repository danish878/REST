package com.danny.rest.service;

import com.danny.rest.model.Passenger;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class PassengerServiceImpl implements PassengerService {

    private List<Passenger> passengers = new ArrayList<>();
    private int currentId = 123;

    @Override
    public List<Passenger> getPassengers(int start, int size) {
        System.out.println(start);
        System.out.println(size);
        return passengers;
    }

    @Override
    public Passenger getPassengerById(long id) {
        return null;
    }

    @Override
    public Response createPassenger(long id, String name, String agent, HttpHeaders headers) {
        Passenger passenger = new Passenger();
        passenger.setId(currentId++);
        passenger.setName(name);

        passengers.add(passenger);

        System.out.println(agent);

        MultivaluedMap<String, String> requestHeaders = headers.getRequestHeaders();
        Set<String> headerKeys = requestHeaders.keySet();
        System.out.println("===================== Headers =====================");
        for (String key : headerKeys) {
            System.out.println(key + ": " + requestHeaders.get(key));
        }

        System.out.println("===================== Cookies =====================");
        Map<String, Cookie> cookies = headers.getCookies();
        Set<String> cookieKeys = cookies.keySet();
        for (String cookieKey : cookieKeys) {
            System.out.println(cookieKey + ": " + cookies.get(cookieKey).getValue());
        }


        return Response.status(Response.Status.CREATED).build();
    }

    @Override
    public Response updatePassenger(Passenger passenger) {
        return null;
    }

    @Override
    public Response deletePassenger(long id) {
        return null;
    }
}
