<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:output method="html" encoding="UTF-8"/>

    <xsl:template match="/">

        <html>
            <body>

                <h2>Самолеты</h2>

                <table border="1">
                    <tr>
                        <th>Модель</th>
                        <th>Страна</th>
                        <th>Тип</th>
                        <th>Цена</th>
                    </tr>

                    <xsl:for-each select="Plane/Airplane">
                        <xsl:sort select="Price" data-type="number"/>

                        <tr>
                            <td><xsl:value-of select="Model"/></td>
                            <td><xsl:value-of select="Origin"/></td>
                            <td><xsl:value-of select="Chars/Type"/></td>
                            <td><xsl:value-of select="Price"/></td>
                        </tr>

                    </xsl:for-each>

                </table>

            </body>
        </html>

    </xsl:template>

</xsl:stylesheet>
