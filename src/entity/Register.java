package entity;

public class Register {
    private String action = null;
    private String code = null;
    private Users users = new Users ();

    public Register(String action, String code, Users users) {
        this.action = action;
        this.code = code;
        this.users = users;
    }
}
