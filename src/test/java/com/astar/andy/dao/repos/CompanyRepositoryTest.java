package com.astar.andy.dao.repos;

import com.astar.andy.dao.entities.Company;
import com.astar.andy.dao.entities.Employee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompanyRepositoryTest {

    @Autowired
    CompanyRepository coRepo;

    @Autowired
    EmployeeRepository emRepo;

    @Before
    //@Transactional
    public void setUp() {

        emRepo.deleteAll();
        coRepo.deleteAll();

        Company thisCompany = new Company();
        thisCompany.setCompanyName("Applied Systems as Technology");
        thisCompany.setStreet1("One Easton Highway");
        thisCompany.setStreet2("PO Box 25");
        thisCompany.setCity("Roswell");
        thisCompany.setState("GA");
        thisCompany.setZip("30011");
        thisCompany.setPhone("4049972309");
        thisCompany.setEmail("DoNotReply@Astar.com");
        coRepo.save(thisCompany);

        thisCompany = new Company();
        thisCompany.setCompanyName("Apex Supply Company");
        thisCompany.setStreet1("2398 Laurenceville Highway");
        thisCompany.setCity("Doraville");
        thisCompany.setState("GA");
        thisCompany.setZip("22812");
        thisCompany.setPhone("4048792992");
        thisCompany.setEmail("CustomerService@ApexSupply.com");
        thisCompany.setEmployees(new HashSet<>());

        Employee thisEmp = new Employee();
        thisEmp.setCompany(thisCompany);
        thisEmp.setLastName("William");
        thisEmp.setFirstName("Harry");
        thisEmp.setDirectPhone("4047763412");
        thisEmp.setPhoneExt("2487");
        thisEmp.setEmailAddress("Harry.William@ApexSupply.com");
        thisCompany.getEmployees().add(thisEmp);

        thisEmp = new Employee();
        thisEmp.setCompany(thisCompany);
        thisEmp.setLastName("Lenord");
        thisEmp.setFirstName("Charles");
        thisEmp.setDirectPhone("4047658976");
        thisEmp.setPhoneExt("2488");
        thisEmp.setEmailAddress("Charles.Lenord@ApexSupply.com");
        thisCompany.getEmployees().add(thisEmp);

        coRepo.save(thisCompany);

    }

    @Test
    public void read_company_one_test() {

        Company thisCompany = coRepo.findOne((long)1);
        assertThat(thisCompany.getCompanyName()).isEqualToIgnoringCase("Applied Systems as Technology");

    }

}