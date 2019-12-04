package loginDemo.domain;

public class User {
    private String username;
    private Integer age;
    private Integer ID;
    private String password;

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", age=" + age +
                ", ID=" + ID +
                ", password='" + password + '\'' +
                '}';
    }

    public User(String username, Integer age, Integer ID, String password) {
        this.username = username;
        this.age = age;
        this.ID = ID;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String usernmae) {
        this.username = usernmae;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

}
