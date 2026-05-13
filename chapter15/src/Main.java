import parser.*;
import validator.XmlValidator;
import transform.XmlToHtmlConverter;
import model.Airplane;
import comparator.PriceComparator;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        String xml = "planes.xml";
        String xsd = "planes.xsd";
        String xsl = "planes.xsl";

        if (!XmlValidator.validate(xml, xsd)) {
            System.out.println("Ошибка XSD");
            return;
        }

        DomParser dom = new DomParser();
        List<Airplane> list = dom.parse(xml);

        list.sort(new PriceComparator());

        System.out.println("DOM:");
        for (Airplane a : list)
            System.out.println(a);

        System.out.println("\nSAX:");
        for (Airplane a : new SaxParser().parse(xml))
            System.out.println(a);

        System.out.println("\nStAX:");
        for (Airplane a : new StaxParser().parse(xml))
            System.out.println(a);

        XmlToHtmlConverter.convert(xml, xsl, "output.html");

        System.out.println("\nHTML создан");
    }
}
