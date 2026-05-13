package validator;

import javax.xml.validation.*;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

public class XmlValidator {

    public static boolean validate(String xml, String xsd) {
        try {
            SchemaFactory factory =
                    SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");

            Schema schema = factory.newSchema(new File(xsd));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xml)));

            return true;

        } catch (Exception e) {
            System.out.println("Ошибка XSD: " + e.getMessage());
            return false;
        }
    }
}
