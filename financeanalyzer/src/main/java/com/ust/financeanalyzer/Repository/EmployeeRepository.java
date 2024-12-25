
package com.ust.financeanalyzer.Repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.ust.financeanalyzer.Entity.Employee;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
@Repository

public interface EmployeeRepository extends ReactiveMongoRepository<Employee, String> {
        Flux<Employee> findByProjectId(String projectId);
}

