package domain;

import org.omg.CORBA.PRIVATE_MEMBER;

public class Province {
    private String name;
    private int ID;

    public Province() {
    }

    public Province(String name, int ID) {
        this.name = name;
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "Province{" +
                "name='" + name + '\'' +
                ", ID=" + ID +
                '}';
    }
}
