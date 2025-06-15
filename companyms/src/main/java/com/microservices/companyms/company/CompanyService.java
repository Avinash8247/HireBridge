package com.microservices.companyms.company;

import java.util.List;

public interface CompanyService {
    List<company> getallcompanies();
    boolean updatecomany(company company ,Long id);

    void createcompany(company company);

    boolean deletecompanybyid(Long id);

    company getcompanybyid(Long id);



}
