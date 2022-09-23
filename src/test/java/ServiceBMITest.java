import io.qameta.allure.Story;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import personalData.PersonalData;
import utils.DataUtil;

import java.sql.Connection;

import static Steps.GetBMI.*;
import static Steps.SendData.sendPersonalData;
import static utils.SQLUtil.getInstance;

public class ServiceBMITest {

    @Story("positiveTest")
    @Test(dataProvider = "positiveData", dataProviderClass = DataUtil.class, description = "Positive test to get BMI")
    public void positiveTest(PersonalData personalData) throws Exception {
        sendPersonalData(personalData);
        getBMIPositive(personalData);
    }

    @Story("negativeTestOnNegativeWeightOrHeight")
    @Test(dataProvider = "negativeTestOnNegativeWeightOrHeight", dataProviderClass = DataUtil.class, description = "Negative test to get BMI")
    public void negativeTestOnNegativeWeightOrHeight(PersonalData personalData) throws Exception {
        sendPersonalData(personalData);
        getBMINegativeTestOnNegativeWeightOrHeight(personalData);
    }

    @Story("negativeTestOnNonExistentData")
    @Test(dataProvider = "negativeTestOnNonExistentData", dataProviderClass = DataUtil.class, description = "Negative test to get BMI")
    public void negativeTestOnNonExistentData(PersonalData personalData) throws Exception {
        getBMINegativeTestOnNonExistentData(personalData);
    }

    @AfterMethod
    private void afterMethod() throws Exception {
        Connection connection = getInstance();
        String query = "DELETE FROM personal_data WHERE id = (SELECT id FROM personal_data ORDER BY id DESC LIMIT 1)";
        connection.createStatement().execute(query);
    }
}
