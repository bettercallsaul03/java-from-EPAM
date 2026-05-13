package parser;

import model.Airplane;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.File;
import java.util.*;

public class DomParser {

    public List<Airplane> parse(String file) {

        List<Airplane> list = new ArrayList<>();

        try {
            DocumentBuilder builder =
                    DocumentBuilderFactory.newInstance().newDocumentBuilder();

            Document doc = builder.parse(new File(file));

            NodeList nodes = doc.getElementsByTagName("Airplane");

            for (int i = 0; i < nodes.getLength(); i++) {

                Element el = (Element) nodes.item(i);

                Airplane a = new Airplane();
                a.setModel(el.getElementsByTagName("Model").item(0).getTextContent());
                a.setOrigin(el.getElementsByTagName("Origin").item(0).getTextContent());
                a.setPrice(Integer.parseInt(el.getElementsByTagName("Price").item(0).getTextContent()));

                list.add(a);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
