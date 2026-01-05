package com.healthcare.patient_service.service;

import com.healthcare.patient_service.model.Patient;
import com.healthcare.patient_service.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public Patient createPatient(Patient patient){
        return patientRepository.save(patient);
    }

    public List<Patient> getAllPatients(){
        return patientRepository.findAll();
    }

    public Optional<Patient> getPatientById(Long id){
        return patientRepository.findById(id);
    }

    public Optional<Patient> updatePatient(Long id, Patient patient){
        Optional<Patient> existingPatient = patientRepository.findById(id);

        if(existingPatient.isPresent()) {
            Patient patientToUpdate = existingPatient.get();
            patientToUpdate.setFirstName(patient.getFirstName());
            patientToUpdate.setLastName(patient.getLastName());
            patientToUpdate.setEmail(patient.getEmail());
            patientToUpdate.setNumber(patient.getNumber());
            patientRepository.save(patientToUpdate);
        }
        return existingPatient;
    }

    public void deletePatient(Long id){
        if(patientRepository.findById(id).isPresent()){
            patientRepository.deleteById(id);
        }
    }
}
