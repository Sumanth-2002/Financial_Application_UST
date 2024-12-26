package com.ust.financeanalyzer.service;

import com.ust.financeanalyzer.Entity.Employee;
import com.ust.financeanalyzer.Entity.Project;
import com.ust.financeanalyzer.Repository.EmployeeRepository;
import com.ust.financeanalyzer.Repository.ProjectRepository;
import com.ust.financeanalyzer.dto.Employeedto;
import com.ust.financeanalyzer.dto.Projectdto;
import com.ust.financeanalyzer.dto.Responsedto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FinancialApplicationService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ProjectRepository projectRepository;

    // Add a new project
    public Mono<Project> addProject(Project project) {
        return projectRepository.save(project);
    }

    // Add a new employee
    public Mono<Employee> addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    // Assign a project to an employee
    public Mono<Employee> assignProjectToEmployee(String id, String projectId) {
        return employeeRepository.findById(id)
                .flatMap(emp -> {
                    emp.setProjectId(projectId);
                    return employeeRepository.save(emp);
                });
    }

    // Get employee details by ID
    public Mono<Employee> getEmployee(String id) {
        return employeeRepository.findById(id);
    }

    // Get all projects
    public Flux<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    // Assign multiple employees to a project
    public Mono<String> assignEmployeeToProject(String projectId, List<String> idList) {
        return projectRepository.findById(projectId).flatMap(project -> {
            int teamSize = project.getTeamSize();

            if (teamSize <= 0) {
                return Mono.error(new RuntimeException("Project team size exceeded"));
            }

            List<Mono<Employee>> assignmentList = idList.stream()
                    .map(employeeId -> employeeRepository.findById(employeeId)
                            .flatMap(employee -> {
                                employee.setProjectId(projectId);
                                return employeeRepository.save(employee);
                            }))
                    .collect(Collectors.toList());

            return Mono.when(assignmentList)
                    .then(Mono.just("Employees assigned to project successfully"));
        });
    }

    // Get project statistics including employee details and salaries
    public Mono<Responsedto> getStatisticsOfProject(String projectId) {
        Mono<Project> projectMono = projectRepository.findById(projectId);

        // Get all employees related to the project
        Flux<Employee> employeesFlux = employeeRepository.findByProjectId(projectId);

        // Calculate total salary (if needed, else it can be removed)
        Mono<Double> totalSalaries = employeesFlux.map(Employee::getSalary).reduce(0.0, Double::sum);

        return projectMono.zipWith(totalSalaries).flatMap(tuple -> {
            Project project = tuple.getT1();
            Double totalSalary = tuple.getT2();

            // Prepare Projectdto object
            Projectdto projectDto = new Projectdto(
                    project.getProjectid(),
                    project.getProjectname(),
                    project.getBudget(),
                    project.getBudgetduration(),
                    project.getTeamSize(),
                    0.0, // Expenditure
                    0.0  // Income
            );

            // Collect employee details and calculate total expenditure
            return employeesFlux.collectList().flatMap(employeeList -> {
                // Use reduce to calculate total expenditure (salary + tax)
                Mono<Double> totalExpenditureMono = Flux.fromIterable(employeeList)
                        .map(employee -> {
                            double salary = employee.getSalary();
                            double tax = salary * 0.10; // 10% tax
                            double adjustedSalary = calculateAdjustedSalary(salary, project.getBudgetduration());
                            return adjustedSalary + tax;
                        })
                        .reduce(0.0, Double::sum);

                // After calculating total expenditure, build employee DTOs and response
                return totalExpenditureMono.flatMap(totalExpenditure -> {
                    // Prepare employee DTOs
                    Flux<Employeedto> employeeDtos = Flux.fromIterable(employeeList).map(employee -> {
                        Employeedto dto = new Employeedto();
                        dto.setId(employee.getId());
                        dto.setName(employee.getName());
                        dto.setContact(employee.getContact());
                        dto.setEmail(employee.getEmail());
                        dto.setProjectId(employee.getProjectId());

                        double salary = employee.getSalary();
                        double tax = salary * 0.10; // 10% tax
                        dto.setTax(tax);

                        double adjustedSalary = calculateAdjustedSalary(salary, project.getBudgetduration());
                        dto.setSalary(adjustedSalary);

                        return dto;
                    });

                    // Calculate income and update project DTO
                    double income = project.getBudget() - totalExpenditure;
                    projectDto.setExpenditure(totalExpenditure);
                    projectDto.setIncome(income);

                    // Create final response DTO
                    return employeeDtos.collectList().map(employeeDtoList -> {
                        Responsedto response = new Responsedto();
                        response.setProjectdto(projectDto);
                        response.setEmpdto(employeeDtoList);
                        return response;
                    });
                });
            });
        });
    }

    // Utility function to adjust salary based on budget duration
    private double calculateAdjustedSalary(double salary, String budgetDuration) {
        switch (budgetDuration.toLowerCase()) {
            case "yearly":
                return salary;
            case "monthly":
                return salary / 12;
            case "quarterly":
                return salary / 4;
            case "halfyearly":
                return salary / 2;
            default:
                return salary;
        }
    }
}
