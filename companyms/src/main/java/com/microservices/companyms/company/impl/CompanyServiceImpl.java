package com.microservices.companyms.company.impl;

import com.microservices.companyms.company.CompanyRepository;
import com.microservices.companyms.company.CompanyService;
import com.microservices.companyms.company.company;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    private CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<company> getallcompanies() {
        return companyRepository.findAll();

    }

    @Override
    public boolean updatecomany(company company, Long id) {
        Optional<company> companyOptional=companyRepository.findById(id);
        if(companyOptional.isPresent()){
            company company1=companyOptional.get();

            company1.setDescription(company.getDescription());
            company1.setName(company.getName());

            companyRepository.save(company1);
            return true;

        }
        return false;
    }

    @Override
    public void createcompany(company company) {
        companyRepository.save(company);
    }

    @Override
    public boolean deletecompanybyid(Long id) {
        if(companyRepository.existsById(id)){
            companyRepository.deleteById(id);
            return true;
        }else{
            return false;
        }

    }

    @Override
    public company getcompanybyid(Long id) {
        return companyRepository.findById(id).orElse(null);
    }


}
