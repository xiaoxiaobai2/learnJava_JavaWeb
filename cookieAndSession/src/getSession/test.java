package getSession;

public class test {
    public static void main(String[] args) {
        double p=0;
        for (int i = 1 ; i < 1000000; i++) {
            p=((4-1.0/i)*(4-2.0/i)*(52-4.0/i)*(52-8))/((52-1.0/i)*(52-2.0/i)*(52-3.0/i)*(52-4.0/i));
            System.out.println(p);
        }
    }
}
