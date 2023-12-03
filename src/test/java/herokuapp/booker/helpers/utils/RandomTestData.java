package herokuapp.booker.helpers.utils;

import com.github.javafaker.Faker;

import java.util.Locale;

public class RandomTestData {

    Faker faker = new Faker(new Locale("ru"));

    public String
            checkinDate = "2023-11-29",
            checkoutDate = "2023-11-30",
            firstname = randomFirstName(),
            lastname = randomLastName(),
            additionalNeeds = randomNeeds();
    public int price = randomPrise();
    public Boolean depositPaid = randomDepositPaid();

    public String randomFirstName() {
        return faker.name().firstName();
    }

    public String randomLastName() {
        return faker.name().lastName();
    }

    public String randomNeeds() {
        String[] needs = {"breakfast in the room", "wash the clothes", "order cleaning", "dinner", "order a taxi to the airport"};
        return faker.options().option(needs);
    }

    public int randomPrise() {
        return faker.number().numberBetween(4500, 10000);
    }

    public Boolean randomDepositPaid() {
        Boolean[] deposit = {true, false};
        return faker.options().option(deposit);
    }
}

