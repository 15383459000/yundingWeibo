package entity;

public class UserJson {
    private String action = null;
    private String code = null;
    private Users user = new Users ();

    public UserJson(String action, String code, Users users) {
        this.action = action;
        this.code = code;
        this.user = users;
    }

    public String getAction() {
        return action;
    }

    public String getCode() {
        return code;
    }

    public Users getUser() {
        return user;
    }
}
