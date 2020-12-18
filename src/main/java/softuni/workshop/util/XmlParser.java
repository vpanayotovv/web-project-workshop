package softuni.workshop.util;

public interface XmlParser {

    <T> T parseXml(Class<T> objectClass, String filePath);
}

