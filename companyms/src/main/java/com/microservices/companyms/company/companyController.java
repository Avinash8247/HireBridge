package com.microservices.companyms.company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class companyController {
    private CompanyService companyService;

    public companyController(CompanyService companyService) {
        this.companyService = companyService;
    }


    @GetMapping
    public List<company> getcompanies(){
        return companyService.getallcompanies();
    }
    @PutMapping("/{id}")

    public ResponseEntity<String> updatecompany(@PathVariable Long id, @RequestBody company company){
        companyService.updatecomany(company,id);
        return new ResponseEntity<>("company updated successfully", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addcompany(@RequestBody company company){
        companyService.createcompany(company);
        return new ResponseEntity<>("company added successfully",HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletecompany(@PathVariable Long id){
        boolean isdeleted=companyService.deletecompanybyid(id);
        if(isdeleted)
            return new ResponseEntity<>("company deleted successfully",HttpStatus.OK);
        return new ResponseEntity<>("company Not Found",HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<company> findcompanybyid(@PathVariable Long id){
       company company=companyService.getcompanybyid(id);
       if(company!=null){
           return new ResponseEntity<>(company,HttpStatus.OK);
       }
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
