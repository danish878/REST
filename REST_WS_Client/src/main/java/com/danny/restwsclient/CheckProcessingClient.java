package com.danny.restwsclient;

import com.danny.restwsclient.model.ChecksList;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.client.AsyncInvoker;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class CheckProcessingClient {

    private static final String CHECKS_SERVICE_URL = "http://localhost:8080/cxf_rest_ws/services/checkProcessingService/checks";
    private static final String PROCESS_CHECKS_SERVICE_URL = "http://localhost:8080/cxf_rest_ws/services/checkProcessingService/processChecks";

    public static void main(String[] args) {

        Client client = ClientBuilder.newClient();

//        AsyncInvoker invoker = client.target(CHECKS_SERVICE_URL).request().async();
        AsyncInvoker invoker = client.target(PROCESS_CHECKS_SERVICE_URL).request().async();

//        long start = System.currentTimeMillis();

//        invoker.get(new InvocationCallback<Response>() {
//
//            @Override
//            public void completed(Response response) {
//                String responseString = response.readEntity(String.class);
//                System.out.println("***************************************************");
//                System.out.println("response: " + responseString);
//                System.out.println("time taken: " + (System.currentTimeMillis() - start));
//            }
//
//            @Override
//            public void failed(Throwable throwable) {
//                throwable.printStackTrace();
//            }
//        });

//        System.out.println("Request returned");


        Future<Boolean> response = invoker.post(
                Entity.entity(
                        new ChecksList(),
                        MediaType.APPLICATION_XML),
                Boolean.class); // because CheckProcessorImpl returns "response.resume(true)" "Boolean" value.

        try {
            System.out.println(response.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            if (e.getCause() instanceof BadRequestException) {
//                BadRequestException bre = (BadRequestException) e.getCause();
                System.out.println("Please send a valid list of checks");
            }
        }

//        client.close();
    }
}
