package softuni.workshop.service.services.impl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.workshop.data.dtos.CompanyRootSeedDto;
import softuni.workshop.data.dtos.CompanySeedDto;
import softuni.workshop.data.entities.Company;
import softuni.workshop.data.repositories.CompanyRepository;
import softuni.workshop.service.services.CompanyService;
import softuni.workshop.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final String COMPANIES_PATH = "src/main/resources/files/xmls/companies.xml";

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository, ModelMapper modelMapper, XmlParser xmlParser) {
        this.companyRepository = companyRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
    }

    @Override
    public void importCompanies() throws JAXBException, FileNotFoundException {
        CompanyRootSeedDto companyRootSeedDto = this.xmlParser.parseXml(
                CompanyRootSeedDto.class, COMPANIES_PATH
        );

        for (CompanySeedDto company : companyRootSeedDto.getCompanies()) {
            Company mappedCompany = this.modelMapper.map(company, Company.class);
            this.companyRepository.saveAndFlush(mappedCompany);
        }
    }

    @Override
    public boolean areImported() {
        return this.companyRepository.count() > 0;
    }

    @Override
    public String readCompaniesXmlFile() throws IOException {
        return Files.readString(Path.of(COMPANIES_PATH));
    }
}
