package utils;

import personalData.PersonalData;

public class BMIUtil {

    private BMIUtil() {
    }

    public static String bodyMassIndex(int height, int weight) {
        double result = weight * 10000.0 / height / height;
        return String.format("%.2f", result);
    }

    public static String bodyMassIndex(PersonalData personalData) {
        return bodyMassIndex(personalData.getHeight(), personalData.getWeight());
    }
}
