package softuni.workshop.util;

import org.springframework.stereotype.Component;
import softuni.workshop.error.CustomXmlException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

@Component
public class XmlParserImpl implements XmlParser {
    @Override
    @SuppressWarnings(value = "unchecked")
    public <O> O parseXml(Class<O> objectClass, String path){
        try {

            JAXBContext context = JAXBContext.newInstance(objectClass);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (O) unmarshaller.unmarshal(new File(path));
        }catch (JAXBException ex){
            throw new CustomXmlException(ex.getMessage() , ex);
        }
    }


}