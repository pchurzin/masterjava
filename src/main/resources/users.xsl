<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
xmlns:j="http://javaops.ru" exclude-result-prefixes="j">
    <xsl:output indent="yes" method="html"/>
    <xsl:template match="/">
        <xsl:text disable-output-escaping='yes'>&lt;!DOCTYPE html&gt;&#xa;</xsl:text>
        <html>
            <head>
                <title>User List</title>
            </head>
            <body>
                <table>
                    <tr>
                        <th>Username</th>
                        <th>Email</th>
                    </tr>
                    <xsl:for-each select="j:Payload/j:Users/j:User" >
                        <tr>
                            <td><xsl:value-of select="j:fullName" /></td>
                            <td><xsl:value-of select="./@email" /></td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>