package Steps;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import personalData.PersonalData;

import static personalData.CreateXML.createXmlToSetPersonalData;
import static utils.RestAssuredUtil.getInstance;
import static utils.RestAssuredUtil.getUrl;
import static utils.XMLUtils.getValueByNodeName;

public class SendData {

    public static final Logger LOG = LogManager.getLogger(SendData.class.getSimpleName());

    @Step("Send personal data on server to save them in database, {personalData}")
    public static void sendPersonalData(PersonalData personalData) throws Exception {
        LOG.info("Personal Data in use:" + personalData.toString());
        RequestSpecification request = getInstance();
        request.body(createXmlToSetPersonalData(personalData));
        Response response = request.post(getUrl());
        String result = getValueByNodeName(response.asString(), "SuccessMessage");
        LOG.info("Collected message:" + result);
        Allure.attachment("Collected message", result);
        Assert.assertEquals(result, "Data was successfully saved in database");
    }
}
