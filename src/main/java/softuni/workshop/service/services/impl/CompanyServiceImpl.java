package softuni.workshop.service.services.impl;
import org.springframework.stereotype.Service;
import softuni.workshop.service.services.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Override
    public void importCompanies() {
        //TODO seed in database
    }

    @Override
    public boolean areImported() {
        //TODO check if repository has any records
        return false;
    }

    @Override
    public String readCompaniesXmlFile() {
        //TODO read xmlFile
        return null;
    }
}
