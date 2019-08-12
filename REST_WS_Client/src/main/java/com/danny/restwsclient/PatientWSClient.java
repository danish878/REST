package com.danny.restwsclient;

import com.danny.restwsclient.model.Patient;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class PatientWSClient {

    private static final String PATIENT_SERVICE_URL = "http://localhost:8080/cxf_rest_ws/services/patientService/patients";

    public static void main(String[] args) {

        Client client = ClientBuilder.newClient();

        WebTarget target = client.target(PATIENT_SERVICE_URL).path("/{id}").resolveTemplate("id", 124);

        Invocation.Builder request = target.request();

        Patient patient = request.get(Patient.class);

        System.out.println(patient.getId());
        System.out.println(patient.getName());


        patient.setName("Kuta Aslam");

        WebTarget putTarget = client.target(PATIENT_SERVICE_URL);
        Invocation.Builder putRequest = putTarget.request();
        Response putResponse = putRequest.put(Entity.entity(patient, MediaType.APPLICATION_XML));
        System.out.println(putResponse.getStatus());
        putResponse.close();


        patient.setName("Naya Navela Patient");
        Response postResponse = client.target(PATIENT_SERVICE_URL).request().post(Entity.entity(patient, MediaType.APPLICATION_XML));
        System.out.println(postResponse.getStatus());


        Response deleteResponse = client.target(PATIENT_SERVICE_URL).path("/{id}").resolveTemplate("id", 123).request().delete();
        System.out.println(deleteResponse.getStatus());
        deleteResponse.close();


        client.close();
    }
}
