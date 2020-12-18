package softuni.workshop.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.workshop.service.services.CompanyService;
import softuni.workshop.service.services.EmployeeService;
import softuni.workshop.service.services.ProjectService;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

@Controller
@RequestMapping("/import")
public class ImportController extends BaseController {

    private final CompanyService companyService;
    private final EmployeeService employeeService;
    private final ProjectService projectService;

    @Autowired
    public ImportController(CompanyService companyService, EmployeeService employeeService, ProjectService projectService) {
        this.companyService = companyService;
        this.employeeService = employeeService;
        this.projectService = projectService;
    }

    @GetMapping("/xml")
    public ModelAndView xml() {
        ModelAndView modelAndView = super.view("/xml/import-xml");

        boolean[] areImported = new boolean[]{
                this.companyService.areImported(),
                this.projectService.areImported(),
                this.employeeService.areImported()
        };

        modelAndView.addObject("areImported", areImported);
        return modelAndView;
    }

    @GetMapping("/companies")
    public ModelAndView companies() throws IOException {
        ModelAndView modelAndView = super.view("xml/import-companies");
        modelAndView.addObject("companies",this.companyService.readCompaniesXmlFile());
        return modelAndView;
    }

    @PostMapping("/companies")
    public ModelAndView companiesConfirm() throws JAXBException, FileNotFoundException {
        this.companyService.importCompanies();
        return super.redirect("/import/xml");
    }

    @GetMapping("/projects")
    public ModelAndView projects(){
        ModelAndView modelAndView = super.view("xml/import-projects");
        modelAndView.addObject("projects",this.projectService.readProjectsXmlFile());
        return modelAndView;
    }

    @PostMapping("/projects")
    public ModelAndView projectsConfirm(){
        this.projectService.importProjects();
        return super.redirect("/import/xml");
    }

    @GetMapping("/employees")
    public ModelAndView employees(){
        ModelAndView modelAndView = super.view("xml/import-employees");
        modelAndView.addObject("employees",this.employeeService.readEmployeesXmlFile());
        return modelAndView;
    }

    @PostMapping("/employees")
    public ModelAndView employeesConfirm() {
        this.employeeService.importEmployees();
        return super.redirect("/import/xml");
    }
}
