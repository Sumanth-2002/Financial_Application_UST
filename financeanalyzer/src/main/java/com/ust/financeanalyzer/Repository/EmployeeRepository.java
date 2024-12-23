
package com.ust.financeanalyzer.Repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.ust.financeanalyzer.Entity.Employee;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
@Repository
public interface EmployeeRepository extends ReactiveMongoRepository<Employee,String>{
        // Corrected method
        Flux<Employee> findByProjectidAndSalary(String projectId, Double salary);

        Flux<Employee> findEmployeeAssignedToprojectid(String projectId);
}
