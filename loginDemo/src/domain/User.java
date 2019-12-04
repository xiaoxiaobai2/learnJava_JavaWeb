package domain;

public class User {
    private String username;
    private Integer age;
    private Integer ID;
    private String password;

    public User() {
    }

    public User(String username, Integer age, Integer ID,String password) {
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

    @Override
    public String toString() {
        System.out.println("username = " + username);
        System.out.println("age = " + age);
        System.out.println("ID = " + ID);
        System.out.println("password = " + password);
        return null;
    }
}
