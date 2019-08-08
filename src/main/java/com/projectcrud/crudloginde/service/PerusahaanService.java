package com.projectcrud.crudloginde.service;

import com.projectcrud.crudloginde.exception.RecordNotFoundException;
import com.projectcrud.crudloginde.model.Perusahaan;
import com.projectcrud.crudloginde.repository.PerusahaanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PerusahaanService {
    @Autowired
    PerusahaanRepository repository;

    public List<Perusahaan> getAllPerusahaan()
    {
        List<Perusahaan> result = (List<Perusahaan>) repository.findAll();

        if (result.size() > 0){
            return result;
        } else {
            return new ArrayList<Perusahaan>();
        }
    }

    public Perusahaan getCompanyByKodePerusahaan(Long kode_perusahaan) throws RecordNotFoundException
    {
        Optional<Perusahaan> perusahaan = repository.findById(kode_perusahaan);

        if (perusahaan.isPresent()){
            return perusahaan.get();
        } else {
            throw new RecordNotFoundException("No Company record exist for given ID / Company's Code");
        }
    }

    public Perusahaan createOrUpdatePerusahaan(Perusahaan perusahaan)
    {
        if (perusahaan.getKode_perusahaan() == null)
        {
            perusahaan = repository.save(perusahaan);

            return perusahaan;
        } else {
            Optional<Perusahaan> entity = repository.findById(perusahaan.getKode_perusahaan());

            if (entity.isPresent())
            {
                Perusahaan newCompany = entity.get();
                newCompany.setNamaPerusahaan(perusahaan.getNamaPerusahaan());

                newCompany =repository.save(newCompany);

                return newCompany;
            } else {
                perusahaan = repository.save(perusahaan);

                return perusahaan;
            }
        }
    }

    public void deleteCompanyByKodePerusahaan(Long kode_perusahaan) throws RecordNotFoundException
    {
        Optional<Perusahaan> entity = repository.findById(kode_perusahaan);

        if (entity.isPresent())
        {
            repository.deleteById(kode_perusahaan);
        } else {
            throw new RecordNotFoundException("No Company Record Exist For Given ID");
        }
    }
}
