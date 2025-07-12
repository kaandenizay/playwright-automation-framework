package data;

import net.datafaker.Faker;

public class DataFaker {

    private static final Faker faker = new Faker();

    public static String getRandomFirstname(){
        return faker.name().firstName();
    }

    public static String getRandomLastname(){
        return faker.name().lastName();
    }

    public static String getRandomEmail(){
        return faker.internet().emailAddress();
    }

    public static String getRandomSentence(){
        return faker.lorem().sentence(5);
    }
}
