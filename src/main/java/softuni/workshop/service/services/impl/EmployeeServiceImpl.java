package softuni.workshop.service.services.impl;

import org.springframework.stereotype.Service;
import softuni.workshop.service.services.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {


    @Override
    public void importEmployees() {
        //TODO seed in database
    }

    @Override
    public boolean areImported() {
        //TODO check if repository has any records
       return true;
    }

    @Override
    public String readEmployeesXmlFile() {
        //TODO read xml file
        return null;
    }

    @Override
    public String exportEmployeesWithAgeAbove() {
        //TODO export employees with age above 25
        return null;
    }
}
