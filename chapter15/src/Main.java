/* 
1. Создать файл XML и соответствующую ему схему XSD. 
2. При разработке XSD использовать простые и комплексные типы, перечи- 
сления, шаблоны и предельные значения. 
3. Сгенерировать класс, соответствующий данному описанию. 
4. Создать приложение для разбора XML-документа и инициализации кол- 
лекции объектов информацией из XML-файла. Для разбора использовать 
SAX, DOM и StAX-парсеры. Для сортировки объектов использовать интер- 
фейс Comparator. 
5. Произвести проверку XML-документа с привлечением XSD. 
6. Определить метод, производящий преобразование раз


Самолеты. 
Самолеты можно описать по следующей схеме: 
— Model — название модели; 
— Origin — страна производства; 
— Chars (должно быть несколько) — характеристики, могут быть сле- 
дующими: тип (пассажирский, грузовой, почтовый, пожарный, сель- 
скохозяйственный), количество мест для экипажа, характеристики (гру- 
зоподъемность, число пассажиров), наличие радара; 
— Parameters — длина (в метрах), ширина (в метрах), высота (в метрах); 
— Price — цена (в талерах). 
Корневой элемент назвать Plane. 
С помощью XSL преобразовать XML-файл в формат HTML, при выводе 
отсортировать по стоимости.

*/

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
