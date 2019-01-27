package modernjava.stream.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by yjlee on 2019-01-22.
 */
@Data
@NoArgsConstructor

@Getter
public class User {
    String name;
    int age;
    Type type;
    boolean marriage;
    UserLevel userLevel;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public User(String name, int age, Type type, boolean marriage) {
        this.name = name;
        this.age = age;
        this.type = type;
        this.marriage = marriage;
    }

    public User(String name, int age, Type type, boolean marriage, UserLevel userLevel) {
        this.name = name;
        this.age = age;
        this.type = type;
        this.marriage = marriage;
        this.userLevel = userLevel;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Type getType() {
        return type;
    }

    public boolean isMarriage() {
        return marriage;
    }
}
