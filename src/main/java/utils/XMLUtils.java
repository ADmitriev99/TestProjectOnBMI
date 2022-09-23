package utils;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringReader;
import java.io.StringWriter;

public class XMLUtils {
    private XMLUtils(){}

    public static String getValueByNodeName(String xmlString, String nodeName) throws Exception {
        Document document = stringToDocument(xmlString);
        NodeList nodeList = document.getElementsByTagName(nodeName);
        return nodeList.item(0).getTextContent();
    }

    public static String changeXmlElementByName(String xmlString, String elementName, String value) throws Exception {
        Document document = stringToDocument(xmlString);
        NodeList nodeList = document.getElementsByTagName(elementName);
        nodeList.item(0).setTextContent(value);
        return documentToString(document);
    }

    public static Document stringToDocument(String xmlString)throws Exception{
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        return documentBuilder.parse(new InputSource(new StringReader(xmlString)));
    }

    public static String documentToString(Document document) throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        StringWriter stringWriter = new StringWriter();
        transformer.transform(new DOMSource(document), new StreamResult(stringWriter));
        return  stringWriter.getBuffer().toString();
    }
}
