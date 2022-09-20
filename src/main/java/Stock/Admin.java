package Stock;

public class Admin {
    private String login;
    private String password;
    private boolean status;

    //constructor
    public Admin(String login, String password, boolean status) {
        this.login = login;
        this.password = password;
        this.status = status;
    }

    //empty constructor
    public Admin() {
    }

    //getters and setters
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
