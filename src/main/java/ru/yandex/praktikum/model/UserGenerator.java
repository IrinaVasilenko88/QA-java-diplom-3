package ru.yandex.praktikum.model;

import org.apache.commons.lang3.RandomStringUtils;

public class UserGenerator {
    public static UserProfile getRandomUser() {
        UserProfile userProfile = new UserProfile();
        userProfile.setName(RandomStringUtils.randomAlphabetic(7) + "Irina");
        userProfile.setEmail(RandomStringUtils.randomAlphabetic(7) + "@yandex.ru");
        userProfile.setPassword("12345II!!!");
        return userProfile;
    }

    public static UserProfile getUserWithInvalidPassword() {
        UserProfile userProfile = new UserProfile();
        userProfile.setName(RandomStringUtils.randomAlphabetic(7) + "Irina");
        userProfile.setEmail(RandomStringUtils.randomAlphabetic(7) + "@yandex.ru");
        userProfile.setPassword(RandomStringUtils.randomAlphabetic(5));
        return userProfile;
    }

    public static UserLogin from(UserProfile userProfile) {
        UserLogin userLogin = new UserLogin();
        userLogin.setEmail(userProfile.getEmail());
        userLogin.setPassword(userProfile.getPassword());
        return userLogin;
    }
}
