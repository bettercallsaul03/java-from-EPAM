package parser;

import model.Airplane;

import javax.xml.stream.*;
import java.io.FileInputStream;
import java.util.*;

public class StaxParser {

    public List<Airplane> parse(String file) {

        List<Airplane> list = new ArrayList<>();

        try {
            XMLStreamReader r =
                    XMLInputFactory.newInstance()
                            .createXMLStreamReader(new FileInputStream(file));

            Airplane a = null;
            String current = "";

            while (r.hasNext()) {

                int event = r.next();

                if (event == XMLStreamConstants.START_ELEMENT) {

                    current = r.getLocalName();

                    if (current.equals("Airplane"))
                        a = new Airplane();
                }

                if (event == XMLStreamConstants.CHARACTERS) {

                    String value = r.getText().trim();

                    if (value.isEmpty() || a == null) continue;

                    switch (current) {
                        case "Model" -> a.setModel(value);
                        case "Origin" -> a.setOrigin(value);
                        case "Price" -> a.setPrice(Integer.parseInt(value));
                    }
                }

                if (event == XMLStreamConstants.END_ELEMENT) {
                    if (r.getLocalName().equals("Airplane"))
                        list.add(a);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
