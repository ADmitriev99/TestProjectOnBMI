package personalData;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import static utils.FileUtils.readString;
import static utils.XMLUtils.changeXmlElementByName;

public class CreateXML {

    public static final Logger LOG = LogManager.getLogger(CreateXML.class.getSimpleName());

    @Step("Create XML to send to set personal data")
    public static String createXmlToSetPersonalData(PersonalData personalData) throws Exception {
        String xmlString = readString("./src/main/resources/personal.xml");
        xmlString = xmlString.replace("\r\n", "");
        xmlString = changeXmlElementByName(xmlString, "bmi:Name", personalData.name);
        xmlString = changeXmlElementByName(xmlString, "bmi:Surname", personalData.surname);
        xmlString = changeXmlElementByName(xmlString, "bmi:Height", String.valueOf(personalData.height));
        xmlString = changeXmlElementByName(xmlString, "bmi:Weight", String.valueOf(personalData.weight));
        LOG.info("xmlString to be sent: " + xmlString);
        Allure.attachment("xmlString to be sent: ", xmlString);
        return xmlString;
    }

    @Step("Create XML to send to get BMI")
    public static String createXmlToGetBMI(PersonalData personalData) throws Exception {
        String xmlString = readString("./src/main/resources/getBMI.xml");
        xmlString = xmlString.replace("\r\n", "");
        xmlString = changeXmlElementByName(xmlString, "bmi:Name", personalData.name);
        xmlString = changeXmlElementByName(xmlString, "bmi:Surname", personalData.surname);
        LOG.info("xmlString to be sent: " + xmlString);
        Allure.attachment("xmlString to be sent: ", xmlString);
        return xmlString;
    }
}
