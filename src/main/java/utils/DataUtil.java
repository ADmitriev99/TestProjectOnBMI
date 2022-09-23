package utils;

import org.apache.commons.lang.math.RandomUtils;
import org.testng.annotations.DataProvider;
import personalData.PersonalData;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class DataUtil {

    @DataProvider(name = "positiveData")
    public Object[] positiveData() throws Exception {
        PersonalData personalData[] = new PersonalData[20];
        Stream nameStream = Files.lines(Paths.get("./src/test/resources/names.txt"));
        Stream surnameStream = Files.lines(Paths.get("./src/test/resources/surnames.txt"));
        Object[] names = nameStream.toArray();
        Object[] surnames = surnameStream.toArray();
        for (int i = 0; i < personalData.length; i++) {
            String name = (String) names[RandomUtils.nextInt(names.length)];
            String surname = (String) surnames[RandomUtils.nextInt(surnames.length)];
            personalData[i] = new PersonalData(name, surname, 160 + RandomUtils.nextInt(40), 60 + RandomUtils.nextInt(60));
        }
        return personalData;
    }

    @DataProvider(name = "negativeTestOnNegativeWeightOrHeight")
    public Object[] negativeTestOnNegativeWeightOrHeight() throws Exception {
        PersonalData personalData[] = new PersonalData[6];
        Stream nameStream = Files.lines(Paths.get("./src/test/resources/names.txt"));
        Stream surnameStream = Files.lines(Paths.get("./src/test/resources/surnames.txt"));
        Object[] names = nameStream.toArray();
        Object[] surnames = surnameStream.toArray();
        for (int i = 0; i < personalData.length; i++) {
            String name = (String) names[RandomUtils.nextInt(names.length)];
            String surname = (String) surnames[RandomUtils.nextInt(surnames.length)];
            switch (i % 3) {
                case (0):
                    personalData[i] = new PersonalData(name, surname, -40 + RandomUtils.nextInt(40), 60 + RandomUtils.nextInt(60));
                case (1):
                    personalData[i] = new PersonalData(name, surname, 160 + RandomUtils.nextInt(40), -60 + RandomUtils.nextInt(60));
                case (2):
                    personalData[i] = new PersonalData(name, surname, -40 + RandomUtils.nextInt(40), -60 + RandomUtils.nextInt(60));
            }
        }
        return personalData;
    }

    @DataProvider(name = "negativeTestOnNonExistentData")
    public Object[] negativeTestOnNonExistentData() throws Exception {
        PersonalData personalData[] = new PersonalData[6];
        Stream nameStream = Files.lines(Paths.get("./src/test/resources/surnames.txt"));
        Stream surnameStream = Files.lines(Paths.get("./src/test/resources/names.txt"));
        Object[] names = nameStream.toArray();
        Object[] surnames = surnameStream.toArray();
        for (int i = 0; i < personalData.length; i++) {
            String name = (String) names[RandomUtils.nextInt(names.length)];
            String surname = (String) surnames[RandomUtils.nextInt(surnames.length)];
            personalData[i] = new PersonalData(name, surname, 160 + RandomUtils.nextInt(40), 60 + RandomUtils.nextInt(60));
        }
        return personalData;
    }

}
