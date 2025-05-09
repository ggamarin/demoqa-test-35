package tests;

import utils.RandomUtils;

public class TestData extends RandomUtils {

    String firstName = getRandomFirstName(),
            lastName = getRandomLastName(),
            email = getRandomEmail(),
            gender = getRandomGender(),
            mobile = getRandomMobile(),
            birthDay = getRandomBirthDay(),
            birthMonth = getRandomBirthMonth(),
            birthYear = getRandomBirthYear(),
            subject = getRandomSubject(),
            hobby = getRandomHobby(),
            address = getRandomAddress(),
            state = getRandomState(),
            city = getRandomCity(state);
}
