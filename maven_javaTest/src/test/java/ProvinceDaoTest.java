import dao.ProvinceDao;
import dao.impl.ProvinceDaoImpl;
import domain.Province;
import org.junit.Test;

import java.util.List;

public class ProvinceDaoTest {
    @Test
    public void ProvinceDaoTest(){
        ProvinceDaoImpl provinceDao = new ProvinceDaoImpl();
        List<Province> all = provinceDao.findAll();
        System.out.println(all);
    }
}
