package com.ust.financeanalyzer.controller;

import com.ust.financeanalyzer.Entity.Employee;
import com.ust.financeanalyzer.Entity.Project;
import com.ust.financeanalyzer.Repository.EmployeeRepository;
import com.ust.financeanalyzer.Repository.ProjectRepository;
import com.ust.financeanalyzer.dto.Responsedto;
import com.ust.financeanalyzer.service.FinancialApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/financial-app/")
public class FinancialAppController {

    @Autowired
    private FinancialApplicationService financialApplicationService;



    @PostMapping("/addEmployee/")
    public Mono<Employee> addEmployee(@RequestBody Employee employee) {
        return financialApplicationService.addEmployee(employee);
    }

    @PostMapping("/addProject/")
    public Mono<Project> addProject(@RequestBody Project project) {
        return financialApplicationService.addProject(project);
    }

    @GetMapping("/project/{projectId}")
    public Mono<Responsedto> getProject(@PathVariable String projectId) {
        return  financialApplicationService.getStatisticsOfProject(projectId);
    }

    @PostMapping("/{projectId}/assign-employee/{employeeId}")
    public Mono<ResponseEntity<String>> assignSingleEmployeeToProject(
            @PathVariable String employeeId,
            @PathVariable String projectId) {

        return financialApplicationService.assignProjectToEmployee(employeeId, projectId)
                .map(employee -> ResponseEntity.ok("Employee assigned to project successfully"))
                .onErrorResume(e -> Mono.just(ResponseEntity.badRequest().body(e.getMessage())));
    }
    @PostMapping("/{projectId}/assign-employees")
    public Mono<ResponseEntity<String>> assignMultipleEmployeesToProject(
            @PathVariable String projectId,
            @RequestBody List<String> employeeIds) {

        return financialApplicationService.assignEmployeeToProject(projectId, employeeIds)
                .map(response -> ResponseEntity.ok(response))
                .onErrorResume(e -> Mono.just(ResponseEntity.badRequest().body(e.getMessage())));
    }

    @GetMapping("/getAllProjects")
    public Flux<Project> getAllProjects() {
        return financialApplicationService.getAllProjects()
                ;
    }

    @GetMapping("/getEmployee/{id}")
    public  Mono<Employee> getEmployee(@PathVariable String id) {
        return financialApplicationService.getEmployee(id);
    }
}
