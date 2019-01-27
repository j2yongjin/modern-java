package modernjava.stream.dto;

/**
 * Created by yjlee on 2019-01-27.
 */
public enum  UserLevel {

    NORMAL(0),GOLD(10),VIP(12);

    int level;

    UserLevel(int level) {
        this.level = level;
    }
}
