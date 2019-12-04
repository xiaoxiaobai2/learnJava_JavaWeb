package service;

import java.util.List;

public interface ProvinceService {
    List findAll();
    String findAllByRedis();
}
