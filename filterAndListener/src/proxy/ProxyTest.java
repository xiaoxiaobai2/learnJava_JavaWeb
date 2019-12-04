package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理增强方法
 */
public class ProxyTest {
    public static void main(String[] args) {
        Lenovo lenovo = new LenovoProxy();
        //动态代理增强方法
        //获取动态代理
        Lenovo proxyInstance = (Lenovo) Proxy.newProxyInstance(lenovo.getClass().getClassLoader(), lenovo.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (method.getName().equals("sale")){
                    System.out.println("原始价格" + args[0]);
                    args[0] = 8000*0.85;
                    method.invoke(lenovo,args);
                }
                System.out.println("invoke方法被执行了！！！");
                return null;
            }
        });
        proxyInstance.show();
        proxyInstance.sale(8000);
    }
}
