<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:j="http://javaops.ru"
    exclude-result-prefixes="j">
    <xsl:output indent="yes" method="html" />
    <xsl:template match="/">
        <xsl:param name="projectId" />
        <xsl:text disable-output-escaping='yes'>&lt;!DOCTYPE html&gt;&#xa;</xsl:text>
        <html>
            <head>
                <title>Groups of the project</title>
            </head>
            <body>
                <ol>
                    <xsl:for-each select="j:Payload/j:Projects/j:Project[attribute::id=$projectId]/j:Groups/j:Group">
                        <li>
                            <xsl:value-of select="." />
                        </li>
                    </xsl:for-each>
                </ol>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>