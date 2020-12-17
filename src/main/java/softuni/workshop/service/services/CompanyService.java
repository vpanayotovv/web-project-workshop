package softuni.workshop.service.services;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface CompanyService {

    void importCompanies() throws JAXBException, FileNotFoundException;

    boolean areImported();

    String readCompaniesXmlFile() throws IOException;
}
