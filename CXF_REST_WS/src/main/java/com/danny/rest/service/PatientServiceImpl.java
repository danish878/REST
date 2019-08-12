package com.danny.rest.service;

import com.danny.rest.exception.PatientBusinessException;
import com.danny.rest.model.Patient;
import org.springframework.stereotype.Service;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.*;

@Service
public class PatientServiceImpl implements PatientService {

    private Map<Long, Patient> patients = new HashMap<>();
    private long currentId = 122;

    public PatientServiceImpl() {
        init();
    }

    private void init() {
        Patient p1 = new Patient(++currentId, "Jameel");
        Patient p2 = new Patient(++currentId, "Aslam");

        patients.put(p1.getId(), p1);
        patients.put(p2.getId(), p2);
    }

    @Override
    public List<Patient> getPatients() {
        Collection<Patient> values = patients.values();
        List<Patient> response = new ArrayList<>(values);
        return response;
    }

    @Override
    public Patient getPatientById(long id) {
        if (patients.get(id) == null)
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        return patients.get(id);
    }

    @Override
    public Response createPatient(Patient patient) {
        patient.setId(++currentId);
        patients.put(patient.getId(), patient);

        return Response.ok(patient).build();
    }

    @Override
    public Response updatePatient(Patient patient) {
        Patient currentPatient = patients.get(patient.getId());

        Response response;
        if (currentPatient != null) {
            patients.put(patient.getId(), patient);
            response = Response.ok().build();
        } else {
//            response = Response.notModified().build();
            throw new PatientBusinessException();
        }

        return response;
    }

    @Override
    public Response deletePatient(long id) {
        Patient currentPatient = patients.get(id);

        Response response;
        if (currentPatient != null) {
            patients.remove(id);
            response = Response.ok().build();
        } else
            response = Response.notModified().build();

        return response;
    }

}
