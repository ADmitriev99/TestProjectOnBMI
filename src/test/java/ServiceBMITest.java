import io.qameta.allure.Story;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import personalData.PersonalData;
import utils.DataUtil;

import java.sql.Connection;

import static Steps.GetBMI.getBMINegative;
import static Steps.GetBMI.getBMIPositive;
import static Steps.SendData.sendPersonalData;
import static utils.SQLUtil.getInstance;

public class ServiceBMITest {

    @Story("positiveTest")
    @Test(dataProvider = "positiveData", dataProviderClass = DataUtil.class, description = "Positive test to get BMI")
    public void positiveTest(PersonalData personalData) throws Exception {
        sendPersonalData(personalData);
        getBMIPositive(personalData);
    }

    @Story("negativeTest")
    @Test(dataProvider = "negativeData", dataProviderClass = DataUtil.class, description = "Negative test to get BMI")
    public void negativeTest(PersonalData personalData) throws Exception {
        sendPersonalData(personalData);
        getBMINegative(personalData);
    }

    @AfterMethod
    private void afterMethod() throws Exception {
        Connection connection = getInstance();
        String query = "DELETE FROM personal_data WHERE id = (SELECT id FROM personal_data ORDER BY id DESC LIMIT 1)";
        connection.createStatement().execute(query);
    }
}
