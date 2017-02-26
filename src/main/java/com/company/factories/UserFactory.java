package com.company.factories;

import com.company.entities.User;
import com.company.requests.NewUserForm;
import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.person.Person;

/**
 * @user siti2017
 * @date 14/02/17
 */
public class UserFactory {
    public static NewUserForm newUserForm() {
        Fairy fairy = Fairy.create();
        Person person = fairy.person();

        NewUserForm newUserForm = new NewUserForm();
        newUserForm.setName(person.getFirstName());
        newUserForm.setLastName(person.getLastName());
        newUserForm.setEmail(person.getEmail());
        newUserForm.setUsername(person.getUsername());
        newUserForm.setPassword(person.getPassword());
        newUserForm.setSex(person.isMale() ? User.Sex.MALE : User.Sex.FEMALE);
        newUserForm.setPhone(person.getTelephoneNumber());
        newUserForm.setCountry(person.getAddress().getCity());

        return newUserForm;
    }

    public static User newUser() {
        return newUserForm().toUser();
    }
}
