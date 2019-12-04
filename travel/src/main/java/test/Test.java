package test;


import java.sql.Date;

public class Test {
    @org.junit.Test
    public void testDate(){
        int total = 40;
        int pagenum = total%8==0?(total/8):(total/8+1);
        System.out.println("pagenum = " + pagenum);
    }
}
