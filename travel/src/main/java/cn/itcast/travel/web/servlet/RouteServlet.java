package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.RouteService;
import cn.itcast.travel.service.impl.RouteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {

    private RouteService routeService = new RouteServiceImpl();

    /**
     * 分页展示路线
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findRoute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.err.println("RouteServlet.findRoute");

        //获取参数
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String cidStr = request.getParameter("cid");
        String rnameStr = request.getParameter("rname");

        rnameStr = new String(rnameStr.getBytes("iso-8859-1"), "utf-8");
        System.out.println("RouteServlet.findRoute---rname = " + rnameStr);
        System.out.println(cidStr);
        int cid = 0;//如果没找到值，这个就是默认值
        if (cidStr != null && cidStr.length() > 0 && !cidStr.equalsIgnoreCase("null")) {
            cid = Integer.valueOf(cidStr);
        }

        int currentPage = 1;//如果没找到值，这个就是默认值
        if (currentPageStr != null && currentPageStr.length() > 0) {
            currentPage = Integer.valueOf(currentPageStr);
        }

        int pageSize = 5;//如果没找到值，这个就是默认值
        if (pageSizeStr != null && pageSizeStr.length() > 0) {
            pageSize = Integer.valueOf(pageSizeStr);
        }
        if (rnameStr.equalsIgnoreCase("null")) {
            rnameStr = null;
        }
        //查询路线封装到pageBean
        PageBean<Route> routePageBean = routeService.pageQuery(cid, currentPage, pageSize, rnameStr);

        //封装成json 并输出到字节流
        response.setContentType("Application/json;charset=utf-8");
        writeValue(routePageBean, response);
    }

    /**
     * 根据Id查询线路的详细信息
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findRouteInf(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取线路id
        System.err.println("RouteServlet.findRouteInf");
        String rid = request.getParameter("rid");
        //获取到含有图片列表、卖家信息的路线对象;
        System.out.println("Integer.valueOf(rid) = " + Integer.valueOf(rid));
        Route routeInf = routeService.findRouteInf(Integer.parseInt(rid));
        //封装成json 并输出到字节流
        response.setContentType("Application/json;charset=utf-8");
        writeValue(routeInf, response);
    }

    /**
     * 根据rid查询收藏
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("RouteServlet.findFavorite");
        String rid = request.getParameter("rid");
        System.out.println("rid = " + rid);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        boolean flag;
        if (user == null) {
            flag = false;
            System.err.println("当前未登陆！");
        } else {
            int uid = user.getUid();
            System.out.println("uid = " + uid);
            flag = routeService.isFavorite(Integer.valueOf(rid), uid);
        }
        //封装成json 并输出到字节流
        response.setContentType("Application/json;charset=utf-8");
        writeValue(flag, response);
    }

    /**
     * 添加收藏
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void addFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rid = request.getParameter("rid");
        System.out.println(rid);
        //获取当前登陆的用户，前面已经判断过是否登陆，此处不用再判断
        User user = (User) request.getSession().getAttribute("user");
        int uid = user.getUid();
        routeService.addFavorite(Integer.parseInt(rid), uid);
    }

    /**
     * 查找我收藏的线路
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findMyFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取当前而当路的用户
        User user = (User) request.getSession().getAttribute("user");
        //拿到UID
        int uid = user.getUid();
//        int uid = Integer.valueOf(request.getParameter("uid"));
        //通过Uid找到该用户收藏的线路
        List<Route> routeList = routeService.findMyFavorite(uid);
        //封装成json 并输出到字节流
        response.setContentType("Application/json;charset=utf-8");
        writeValue(routeList, response);
    }


    /**
     * 查找所有符合要求的线路，并按照收藏数量进行排序
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findAllRoute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取参数
        String rnameStr = request.getParameter("rname");
        String maxPriceStr = request.getParameter("maxPrice");
        String minPriceStr = request.getParameter("minPrice");
        String currentPageStr = request.getParameter("currentPage");
        if (rnameStr!=null){
            rnameStr = new String(rnameStr.getBytes("iso-8859-1"), "utf-8");
        }

        System.out.println("rnameStr = " + rnameStr);
        int currentPage = 1;
        if (currentPageStr!=null&&currentPageStr.length()>0){
            currentPage = Integer.parseInt(currentPageStr);
        }
        int maxPrice = -1;
        if (maxPriceStr!=null&&maxPriceStr.length()>0){
            maxPrice = Integer.parseInt(maxPriceStr);
        }
        int minPrice = -1;
        if (minPriceStr!=null&&minPriceStr.length()>0){
            minPrice = Integer.parseInt(minPriceStr);
        }
        int pageSize = 8;
        int cid = 5;
        PageBean<Route> allRoute = routeService.findAllRoute(maxPrice, minPrice, rnameStr, currentPage, cid, pageSize);
        /*
            按价格从大到小进行排序
         */
//        List<Route> list = allRoute.getList();
//        int end = list.size();
//        while (end>0){
//            int flag =0;
//            for (int i=0; i<end;i++ ){
//                if (list.get(flag).getPrice()<list.get(i).getPrice()){
//                    flag = i;
//                }
//            }
//            list.add(list.get(flag));
//            list.remove(flag);
//            end --;
//        }
//        for (Route route : list) {
//            System.out.println(route.getPrice());
//        }
        //封装成json 并输出到字节流
        response.setContentType("application/json;charset=utf-8");
        writeValue(allRoute, response);
    }

}
