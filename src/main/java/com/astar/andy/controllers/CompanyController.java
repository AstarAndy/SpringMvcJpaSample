package com.astar.andy.controllers;

import com.astar.andy.dao.entities.Company;
import com.astar.andy.dao.entities.Employee;
import com.astar.andy.dao.repos.CompanyRepository;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

/**
 * Primary entry point for accessing Company Data
 *
 */
@Log4j
@RestController
@RequestMapping(value = "/v1/company", produces = MediaType.APPLICATION_JSON_VALUE)
public class CompanyController {

    private CompanyRepository coRepo;

    @Autowired
    public CompanyController(CompanyRepository coRepo) {
        this.coRepo = coRepo;
    }


    @GetMapping("/{companyId}")
    @ResponseBody
    public ResponseEntity<?> getCompany(@PathVariable Long companyId) {

        Company thisCompany = coRepo.findOne(companyId);
        if (thisCompany == null) {
            return new ResponseEntity<String>(String.format("{\"Status\": \"Company %d is not in the system\"}", companyId), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<Company>(thisCompany, HttpStatus.OK);
        }


    }

    @GetMapping("/new")
    @ResponseBody
    public ResponseEntity<Company> getCompanyTemplate() {

        // New company, 1 employee
        Company thisCompany = new Company();
        thisCompany.setCompanyName("Transaction Processing Specialists");
        thisCompany.setStreet1("230 Technology Blvd");
        thisCompany.setCity("Austin");
        thisCompany.setState("TX");
        thisCompany.setZip("78701");
        thisCompany.setPhone("5129742000");
        thisCompany.setEmail("TransactionProcessingHelp@TPS.com");
        thisCompany.setEmployees(new HashSet<>());

        Employee thisEmp = new Employee();
        thisEmp.setCompany(thisCompany);
        thisEmp.setLastName("Jason");
        thisEmp.setFirstName("Kanovlish");
        thisEmp.setDirectPhone("5129747654");
        thisEmp.setPhoneExt("7654");
        thisEmp.setEmailAddress("Jason_Kanovlish@TPS.com");
        thisCompany.getEmployees().add(thisEmp);

        return new ResponseEntity<Company>(thisCompany, HttpStatus.OK);

    }


    @DeleteMapping("/{companyId}")
    @ResponseBody
    public ResponseEntity<?> deleteCompany(@PathVariable Long companyId) {

        return null;

    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> addCompany(@RequestBody Company newCompany) {

        log.info(String.format("----Persisting new company: " + newCompany.toString()));

        Company companyAdded = coRepo.save(newCompany);
        log.info(companyAdded == null ? "---The returned co object is NULL" : "---Ok we saved the new company");

        log.info(String.format("Company Saved.  The id for company %s is ", companyAdded.getCompanyName()));

        return new ResponseEntity<Company>(companyAdded, HttpStatus.OK);

    }


}
