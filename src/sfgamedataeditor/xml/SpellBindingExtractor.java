package sfgamedataeditor.xml;

import javafx.util.Pair;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpellBindingExtractor {

    private static final String SPELL_BINDING_FILE = "spellBind.xml";
    public static final String SPELL_TAG_NAME = "spell";
    public static final String ID_ATTRIBUTE = "id";
    public static final String NAME_ATTRIBUTE = "name";
    // TODO add i18n support
    private static final String LANGUAGE = "en";
    private static final int NUMBER_OF_PARAMETER_FIELDS = 7;
    private static final String FIELD_ATTRIBUTE = "field";

    public static Map<Integer, Pair<String, List<String>>> getSpellMap() {
        File file = new File(SPELL_BINDING_FILE);
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = builderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            return null;
        }

        Document document;
        try {
            document = builder.parse(file);
        } catch (SAXException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        //optional, but recommended
        //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
        document.getDocumentElement().normalize();

        Map<Integer, Pair<String, List<String>>> spellMap = new HashMap<>();

        NodeList spellNodeList = document.getElementsByTagName(SPELL_TAG_NAME);
        String nameAttribute = NAME_ATTRIBUTE + "_" + LANGUAGE;
        for (int i = 0; i < spellNodeList.getLength(); i++) {
            Node node = spellNodeList.item(i);
            NamedNodeMap attributes = node.getAttributes();
            for (int j = 0; j < attributes.getLength(); j++) {
                Node idNode = attributes.getNamedItem(ID_ATTRIBUTE);
                Integer id = Integer.valueOf(idNode.getNodeValue());
                Node nameNode = attributes.getNamedItem(nameAttribute);
                String name = nameNode.getNodeValue();
                List<String> parameterFieldsNames = new ArrayList<>();
                for (int k = 0; k < NUMBER_OF_PARAMETER_FIELDS; k++) {
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
}
