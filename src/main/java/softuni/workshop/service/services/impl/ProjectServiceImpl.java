package softuni.workshop.service.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.workshop.data.dtos.ProjectRootSeedDto;
import softuni.workshop.data.dtos.ProjectSeedDto;
import softuni.workshop.data.entities.Project;
import softuni.workshop.data.repositories.CompanyRepository;
import softuni.workshop.data.repositories.ProjectRepository;
import softuni.workshop.error.CustomXmlException;
import softuni.workshop.error.EntityNotFoundException;
import softuni.workshop.service.services.ProjectService;
import softuni.workshop.util.XmlParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final CompanyRepository companyRepository;
    private final String PROJECT_PATH = "src/main/resources/files/xmls/projects.xml";

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository, ModelMapper modelMapper, XmlParser xmlParser, CompanyRepository companyRepository) {
        this.projectRepository = projectRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.companyRepository = companyRepository;
    }

    @Override
    public void importProjects() {
        ProjectRootSeedDto projectRootSeedDto = this.xmlParser.parseXml(ProjectRootSeedDto.class, PROJECT_PATH);
        for (ProjectSeedDto project : projectRootSeedDto.getProjects()) {
            Project mappedProject = this.modelMapper.map(project, Project.class);
            mappedProject.setCompany(
                    this.companyRepository.findByName(
                            mappedProject.getCompany().getName()).orElseThrow(() ->
                            new EntityNotFoundException(
                                    String.format(
                                            "Project %s not found.", project.getCompany().getName()
                                    )
                            )
                    )
            );
            this.projectRepository.saveAndFlush(mappedProject);
        }
    }

    @Override
    public boolean areImported() {
        return this.projectRepository.count() > 0;
    }

    @Override
    public String readProjectsXmlFile() {
        try {
            return Files.readString(Path.of(PROJECT_PATH));
        } catch (IOException ex) {
            throw new CustomXmlException(ex.getMessage(), ex);
        }
    }

    @Override
    public String exportFinishedProjects() {
        //TODO export finished projects
        return null;
    }
}
