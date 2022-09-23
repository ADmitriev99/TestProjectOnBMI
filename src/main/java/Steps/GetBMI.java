package Steps;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import personalData.PersonalData;

import static personalData.CreateXML.createXmlToGetBMI;
import static utils.BMIUtil.bodyMassIndex;
import static utils.RestAssuredUtil.getInstance;
import static utils.RestAssuredUtil.getUrl;
import static utils.XMLUtils.getValueByNodeName;


public class GetBMI {

    public static final Logger LOG = LogManager.getLogger(GetBMI.class.getSimpleName());

    @Step("Get person BMI, {personalData}")
    public static void getBMIPositive(PersonalData personalData) throws Exception {
        LOG.info("Personal Data in use:" + personalData.toString());
        RequestSpecification request = getInstance();
        request.body(createXmlToGetBMI(personalData));
        Response response = request.post(getUrl());
        String result = getValueByNodeName(response.asString(), "BodyMassIndex");
        result = result.replace(".", ",");
        LOG.info("Collected message:" + result);
        Allure.attachment("Collected message", result);
        Assert.assertEquals(bodyMassIndex(personalData), result);
    }

    @Step("Attempt to get person BMI, {personalData}")
    public static void getBMINegativeTestOnNegativeWeightOrHeight(PersonalData personalData) throws Exception {
        LOG.info("Personal Data in use:" + personalData.toString());
        RequestSpecification request = getInstance();
        request.body(createXmlToGetBMI(personalData));
        Response response = request.post(getUrl());
        String result = getValueByNodeName(response.asString(), "BodyMassIndex");
        LOG.info("Collected message:" + result);
        Allure.attachment("Collected message", result);
        Assert.assertEquals(result, "Height or weight is not a positive number");
    }

    @Step("Attempt to get person BMI, {personalData}")
    public static void getBMINegativeTestOnNonExistentData (PersonalData personalData) throws Exception {
        LOG.info("Personal Data in use:" + personalData.toString());
        RequestSpecification request = getInstance();
        request.body(createXmlToGetBMI(personalData));
        Response response = request.post(getUrl());
        String result = getValueByNodeName(response.asString(), "BodyMassIndex");
        LOG.info("Collected message:" + result);
        Allure.attachment("Collected message", result);
        Assert.assertEquals(result, "Failed to get data");
    }
}
