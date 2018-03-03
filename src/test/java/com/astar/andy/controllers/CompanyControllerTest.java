package com.astar.andy.controllers;

import com.astar.andy.dao.repos.CompanyRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@WebMvcTest(CompanyController.class)
public class CompanyControllerTest {

    @Autowired
    private MockMvc coController;

    @MockBean
    private CompanyRepository coRepo;


    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getCompany() {
    }

    @Test
    public void getCompanyTemplate() {
    }

    @Test
    public void deleteCompany() {
    }

    @Test
    public void addCompany() {
    }
}