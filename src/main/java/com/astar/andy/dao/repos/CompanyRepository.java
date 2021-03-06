package com.astar.andy.dao.repos;

import com.astar.andy.dao.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

	Company findByCompanyNameIgnoreCase(String coName);


}
