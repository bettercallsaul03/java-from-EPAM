package parser;

import model.Airplane;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.*;
import java.io.File;
import java.util.*;

public class SaxParser {

    public List<Airplane> parse(String file) {

        List<Airplane> list = new ArrayList<>();

        try {
            SAXParser parser =
                    SAXParserFactory.newInstance().newSAXParser();

            DefaultHandler handler = new DefaultHandler() {

                Airplane a;
                String current;

                public void startElement(String uri, String local, String qName, Attributes attributes) {
                    current = qName;
                    if (qName.equals("Airplane")) a = new Airplane();
                }

                public void characters(char[] ch, int start, int length) {
                    String value = new String(ch, start, length).trim();

                    if (value.isEmpty() || a == null) return;

                    switch (current) {
                        case "Model" -> a.setModel(value);
                        case "Origin" -> a.setOrigin(value);
                        case "Price" -> a.setPrice(Integer.parseInt(value));
                    }
                }

                public void endElement(String uri, String local, String qName) {
                    if (qName.equals("Airplane")) list.add(a);
                }
            };

            parser.parse(new File(file), handler);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
