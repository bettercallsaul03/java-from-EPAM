package transform;

import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import java.io.File;

public class XmlToHtmlConverter {

    public static void convert(String xml, String xsl, String out) {

        try {
            Transformer t =
                    TransformerFactory.newInstance()
                            .newTransformer(new StreamSource(new File(xsl)));

            t.transform(
                    new StreamSource(new File(xml)),
                    new StreamResult(new File(out))
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
