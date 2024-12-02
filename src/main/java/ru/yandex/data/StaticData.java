package ru.yandex.data;

import com.github.javafaker.Faker;

public class StaticData {

    protected static final Faker faker = new Faker();
    protected static final String name = faker.name().firstName();
    protected static final String email = faker.internet().emailAddress();
    protected static final String password = faker.internet().password();
    protected static final String incorrectPassword = faker.internet().password(2, 5);

}
