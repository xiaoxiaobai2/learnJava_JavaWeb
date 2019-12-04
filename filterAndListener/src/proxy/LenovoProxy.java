package proxy;

public class LenovoProxy implements Lenovo {
    @Override
    public String sale(double money) {
        System.out.println("电脑" + money + "元！");
        return "电脑" + money + "元！";
    }

    @Override
    public String show() {
        System.out.println("展示电脑");
        return null;
    }
}
