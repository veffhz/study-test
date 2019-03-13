package ru.otus.dao;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import ru.otus.domain.User;

@Getter
@Setter
@Component
public class UserDao {
    private User user;

    public void setNewUser(String firstName, String lastName) {
        this.user = new User(firstName, lastName);
    }

    public String getPrettyUserName() {
        return String.format("%s %s", user.getFirstName(), user.getLastName());
    }

}
