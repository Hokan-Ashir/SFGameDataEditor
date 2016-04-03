package sfgamedataeditor.dataextraction;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import sfgamedataeditor.databind.Pair;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class XMLExtractor {

    private static final Logger LOGGER = Logger.getLogger(XMLExtractor.class);

    private static final String SPELL_TAG_NAME = "spell";
    private static final String ID_ATTRIBUTE = "id";
    private static final String NAME_ATTRIBUTE = "name";
    private static final int NUMBER_OF_PARAMETER_FIELDS = 9;
    private static final String FIELD_ATTRIBUTE = "field";
    private static String CONFIGURATION_XML;
    private static Document document;

    private XMLExtractor() {

    }

    public static Map<Integer, Pair<String, List<String>>> getSpellMap() {
        Document document = getXMLDocument();
        if (document == null) {
            return null;
        }

        Map<Integer, Pair<String, List<String>>> spellMap = new HashMap<>();

        NodeList spellNodeList = document.getElementsByTagName(SPELL_TAG_NAME);
        for (int i = 0; i < spellNodeList.getLength(); i++) {
            Node node = spellNodeList.item(i);
            NamedNodeMap attributes = node.getAttributes();
            for (int j = 0; j < attributes.getLength(); j++) {
                Node idNode = attributes.getNamedItem(ID_ATTRIBUTE);
                Integer id = Integer.valueOf(idNode.getNodeValue());
                Node nameNode = attributes.getNamedItem(NAME_ATTRIBUTE);
                String name = nameNode.getNodeValue();
                List<String> parameterFieldsNames = new ArrayList<>();
                for (int k = 1; k <= NUMBER_OF_PARAMETER_FIELDS; k++) {
                    Node fieldNode = attributes.getNamedItem(FIELD_ATTRIBUTE + String.valueOf(k));
                    if (fieldNode != null) {
                        parameterFieldsNames.add(fieldNode.getNodeValue());
                    }
                }
                spellMap.put(id, new Pair<>(name, parameterFieldsNames));
            }
        }

        return spellMap;
    }

    private static Document getXMLDocument() {
        if (document != null) {
            return document;
        }

        File file = new File(CONFIGURATION_XML);
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = builderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }

        Document document;
        try {
            document = builder.parse(file);
        } catch (SAXException | IOException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }

        //optional, but recommended
        //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
        document.getDocumentElement().normalize();
        XMLExtractor.document = document;
        return document;
    }

    public static void setConfigurationXml(String configurationXml) {
        CONFIGURATION_XML = configurationXml;
    }
}
