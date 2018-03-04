package com.astar.andy.controllers;

import com.astar.andy.dao.entities.Employee;
import java.util.HashSet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;

import com.astar.andy.dao.entities.Company;
import com.astar.andy.dao.repos.CompanyRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(CompanyController.class)
public class CompanyControllerTest {

    private static final String NEW_COMPANY_JSON_PAYLOAD = "{\"companyName\":\"New Test Company\",\"city\":\"Atlanta\",\"state\":\"GA\",\"zip\":\"30188\",\"phone\":\"7705814365\",\"email\":\"Customer_Service@NTC.com\",\"employees\":[{\"lastName\":\"Smith\",\"firstName\":\"John\",\"directPhone\":\"5129747654\",\"phoneExt\":\"7654\",\"emailAddress\":\"Jason_Kanovlish@TPS.com\"}],\"street1\":\"230 Technology Blvd\"}]}";

    @Autowired
    private MockMvc coController;

    @MockBean
    private CompanyRepository coRepo;


    @Test
    public void read_specific_company_test() throws Exception {
        Company thisCompany = new Company();
        thisCompany.setCompanyName("Transaction Processing Specialists");
        thisCompany.setStreet1("230 Technology Blvd");
        thisCompany.setCity("Austin");
        thisCompany.setState("TX");
        thisCompany.setZip("78701");
        thisCompany.setPhone("5129742000");
        thisCompany.setEmail("TransactionProcessingHelp@TPS.com");

        // Now mock the db layer

        // First, for a good read,
        given(coRepo.findOne(new Long(1))).willReturn(thisCompany);
        String result = coController
                .perform(get("/v1/company/1")
                 .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();
        assertThat(result).contains("230 Technology Blvd");

        // Next, let's test the if to be sure we get a bad message when the companyId supplied is bad
        given(coRepo.findOne(new Long(1))).willReturn(null);
        coController
                .perform(get("/v1/company/1")
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"Status\": \"Company 1 is not in the system\"}"));

    }

    @Test
    public void add_new_company_and_employee_test() throws Exception {
        Company thisCompany = new Company();
        thisCompany.setCompanyName("New Test Company");
        thisCompany.setStreet1("123 Test Street");
        thisCompany.setCity("Atlanta");
        thisCompany.setState("GA");
        thisCompany.setZip("30188");
        thisCompany.setPhone("7705814365");
        thisCompany.setEmail("Customer_Service@NTC.com");
        thisCompany.setEmployees(new HashSet<>());

        Employee thisEmployee = new Employee();
        thisEmployee.setCompany(thisCompany);
        thisEmployee.setFirstName("John");
        thisEmployee.setLastName("Smith");

        thisCompany.getEmployees().add(thisEmployee);

        Company createdCompany = new Company();
        createdCompany.setId((long)99999);
        createdCompany.setCompanyName("New Test Company");

        // Now specify the mocked return value from the company repo
        given(coRepo.save(any(Company.class))).willReturn(createdCompany);

        // Now let's call the controller

        coController
                .perform(
                        post("/v1/company")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(NEW_COMPANY_JSON_PAYLOAD)
                                .accept(MediaType.APPLICATION_JSON_VALUE)
                        )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
                //.andReturn()
                //.getResponse()
                //.getContentAsString();


    }
}