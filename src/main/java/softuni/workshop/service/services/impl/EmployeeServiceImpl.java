package softuni.workshop.service.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.workshop.data.dtos.EmployeeRootSeedDto;
import softuni.workshop.data.dtos.EmployeeSeedDto;
import softuni.workshop.data.entities.Company;
import softuni.workshop.data.entities.Employee;
import softuni.workshop.data.entities.Project;
import softuni.workshop.data.repositories.CompanyRepository;
import softuni.workshop.data.repositories.EmployeeRepository;
import softuni.workshop.data.repositories.ProjectRepository;
import softuni.workshop.error.CustomXmlException;
import softuni.workshop.error.EntityNotFoundException;
import softuni.workshop.service.services.EmployeeService;
import softuni.workshop.util.XmlParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final CompanyRepository companyRepository;
    private final ProjectRepository projectRepository;
    private final String EMPLOYEE_PATH = "src/main/resources/files/xmls/employees.xml";

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper modelMapper, XmlParser xmlParser, CompanyRepository companyRepository, ProjectRepository projectRepository) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.companyRepository = companyRepository;
        this.projectRepository = projectRepository;
    }


    @Override
    public void importEmployees() {
        EmployeeRootSeedDto employeeRootSeedDto = this.xmlParser.parseXml(EmployeeRootSeedDto.class, EMPLOYEE_PATH);

        for (EmployeeSeedDto employee : employeeRootSeedDto.getEmployees()) {
            Employee mappedEmployee = this.modelMapper.map(employee, Employee.class);
            Company company = this.companyRepository.findByName(employee.getProject().getCompany().getName()).orElseThrow(
                    () -> new EntityNotFoundException(String.format("Company %s not found", employee.getProject().getCompany().getName()))
            );

            Project project = this.projectRepository.findByName(employee.getProject().getName()).orElseThrow(
                    () -> new EntityNotFoundException(String.format("Project %s not found", employee.getProject().getName()))
            );
            project.setCompany(company);
            mappedEmployee.setProject(project);
            this.employeeRepository.saveAndFlush(mappedEmployee);
        }
    }

    @Override
    public boolean areImported() {
        return this.employeeRepository.count() > 0;
    }

    @Override
    public String readEmployeesXmlFile() {
        try {
            return Files.readString(Path.of(EMPLOYEE_PATH));
        } catch (IOException ex) {
            throw new CustomXmlException(ex.getMessage(), ex);
        }
    }

    @Override
    public String exportEmployeesWithAgeAbove() {
        StringBuilder sb = new StringBuilder();
        getEmployeesAbove().forEach(e ->
                sb.append("Name: ").append(e.getFirstName()).append(" ").append(e.getLastName())
                        .append("\n     Age: ").append(e.getAge())
                        .append("\n     Project Name: ").append(e.getProject().getName())
                        .append("\n")
        );
        return sb.toString();
    }


    private List<EmployeeSeedDto> getEmployeesAbove() {
        return this.employeeRepository.findAllByAgeIsGreaterThan(25)
                .stream()
                .map(e -> this.modelMapper.map(e, EmployeeSeedDto.class))
                .collect(Collectors.toList());
    }
}
