package com.astar.andy.dao.repos;

import com.astar.andy.dao.entities.Company;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompanyRepositoryTest {

    @Autowired
    CompanyRepository coRepo;

    @Before
    public void setUp() {

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

    }

    @Test
    public void read_company_one_test() {

        Company thisCompany = coRepo.findOne((long)1);
        assertThat(thisCompany.getCompanyName()).isEqualToIgnoringCase("Applied Systems as Technology");

    }

}