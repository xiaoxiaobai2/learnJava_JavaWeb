package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.dao.ImageDao;
import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.dao.SellerDao;
import cn.itcast.travel.dao.impl.FavoriteDaoImpl;
import cn.itcast.travel.dao.impl.ImageDaoImpl;
import cn.itcast.travel.dao.impl.RouteDaoImpl;
import cn.itcast.travel.dao.impl.SellerDaoImpl;
import cn.itcast.travel.domain.*;
import cn.itcast.travel.service.RouteService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class RouteServiceImpl implements RouteService {

    private RouteDao routeDao = new RouteDaoImpl();
    private ImageDao imageDao = new ImageDaoImpl();
    private SellerDao sellerDao = new SellerDaoImpl();
    private FavoriteDao favoriteDao = new FavoriteDaoImpl();

    /**
     * 查询分页
     * @param cid
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize, String rname) {
        PageBean<Route> routePageBean = new PageBean<>();

        //查询总路线数
        int totalRoute = routeDao.findTotalRoute(cid,rname);

        routePageBean.setTotalSize(totalRoute);
        routePageBean.setPageSize(pageSize);
        routePageBean.setCurrentPage(currentPage);
        routePageBean.setTotalPage((int)Math.floor(totalRoute/pageSize)+1);

        //查询当前页面要展示的路线
        int start = (currentPage-1)*pageSize;
        List<Route> routes = routeDao.findRouteByPage(cid, start, pageSize, rname);
        routePageBean.setList(routes);

        return routePageBean;
    }

    /**
     * 根据线路id查询线路详细信息
     * @param rid
     * @return
     */
    @Override
    public Route findRouteInf(int rid) {
        System.out.println("----------------------RouteServiceImpl.findRouteInf");
        Route route = routeDao.findRouteById(rid);

        //查询图片列表并封装到Route中
        List<RouteImg> routeImgs = imageDao.findImg(rid);
        System.out.println("routeImgs = " + routeImgs);
        route.setRouteImgList(routeImgs);

        //查询商家信息并封装到Route中
        Seller seller = sellerDao.findById(route.getSid());
        System.out.println("seller = " + seller);
        route.setSeller(seller);

        return route;
    }

    /**
     * 判断该线路是否被当前用户收藏
     * @param rid
     * @param uid
     * @return
     */
    @Override
    public Boolean isFavorite(int rid, int uid) {
        System.out.println("RouteServiceImpl.isFavorite");
        Favorite favorite = favoriteDao.findById(rid, uid);
        System.out.println("favorite = " + favorite);
        if (favorite==null){
            return false;
        }
        return true;
    }

    /**
     * 添加收藏
     * @param rid
     * @param uid
     */
    @Override
    public void addFavorite(int rid, int uid) {
        Favorite favorite = new Favorite();
        favorite.setUid(uid);
        favorite.setRid(rid);
        favorite.setDate(new Date(System.currentTimeMillis()));
        //更新该线路被收藏的次数
        routeDao.updateCount(rid);
        favoriteDao.add(favorite);
    }

    /**
     * 根据当前登陆的用户查询收藏的所有线路信息
     * @param uid
     * @return
     */
    @Override
    public List<Route> findMyFavorite(int uid) {
        System.out.println("RouteServiceImpl.findMyFavorite");
        //查询所有收藏的线路
        List<Favorite> ridList = favoriteDao.findByuid(uid);
        System.out.println("ridList = " + ridList);

        //根据 rid 查询 线路信息，并封装到list
        List<Route> routeList = new ArrayList<>();
        for (Favorite favorite : ridList) {
            Route route = routeDao.findRouteById(favorite.getRid());
            routeList.add(route);
        }
        return routeList;
    }

    /**
     * 查询符合要求的数据
     * @param maxPrice
     * @param minPrice
     * @param rname
     * @param currentPage
     * @return
     */
    @Override
    public PageBean<Route> findAllRoute(int maxPrice, int minPrice, String rname, int currentPage,int cid,int pageSize) {
        PageBean<Route> routePageBean = new PageBean<>();

//        //查询要求的总路线数
        int totalRoute = routeDao.findAllRouteNum(cid,maxPrice,minPrice,rname);
//
        routePageBean.setTotalSize(totalRoute);

        routePageBean.setPageSize(pageSize);
        routePageBean.setCurrentPage(currentPage);
        routePageBean.setMaxPrice(maxPrice);
        routePageBean.setMinPrice(minPrice);
        routePageBean.setRname(rname);

        routePageBean.setTotalPage(totalRoute%pageSize==0?(totalRoute/pageSize):(totalRoute/pageSize+1));

        //查询当前页面要展示的路线
        int start = (currentPage-1)*pageSize;
        List<Route> routes = routeDao.findAllRoutePage(cid,maxPrice,minPrice,rname,start,pageSize);
        routePageBean.setList(routes);

        return routePageBean;
    }


}
