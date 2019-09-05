package com.springboot.rong.until.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
 
import javax.sql.DataSource;
 
/**
 * druid参数配置
 */
@Configuration
@PropertySource(value = "classpath:application.yml")
public class DruidConfiguration {
 
    /**
     * @todo 数据源配置
    */
    @Bean(destroyMethod = "close", initMethod = "init")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        return druidDataSource;
    }
 
    /**
     * druid
     * 注册一个StatViewServlet
     * @return
     */
    @Bean
    public ServletRegistrationBean druidStatViewServlet(){
        //org.springframework.boot.context.embedded.ServletRegistrationBean提供类的进行注册.
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
 
        //添加初始化参数：initParams
        //白名单：
        servletRegistrationBean.addInitParameter("allow","127.0.0.1");
        //IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not permitted to view this page.
        //servletRegistrationBean.addInitParameter("deny","192.168.1.73");
        //登录查看信息的账号密码.
        servletRegistrationBean.addInitParameter("loginUsername","admin");
        servletRegistrationBean.addInitParameter("loginPassword","123456");
        //是否能够重置数据.
        servletRegistrationBean.addInitParameter("resetEnable","false");
        return servletRegistrationBean;
    }
 
    /**
     * druid过滤器
     * 注册一个：filterRegistrationBean
     * @return
     */
    @Bean
    public FilterRegistrationBean druidStatFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        //添加过滤规则.
        filterRegistrationBean.addUrlPatterns("/*");
        //添加不需要忽略的格式信息.
        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
 
    
  /*  建立数据库连接耗时耗费资源，一个数据库服务器能够同时建立的连接数也是有限的，在大型的Web应用中，可能同时会有成百上千的访问数据库的请求，如果Web应用程序为每一个客户请求分配一个数据库连接，将导致性能的急剧下降。

    数据库连接池的意义在于，能够重复利用数据库连接（有点类似线程池的部分意义），提高对请求的响应时间和服务器的性能。
    连接池中提前预先建立了多个数据库连接对象，然后将连接对象保存到连接池中，当客户请求到来时，直接从池中取出一个连接对象为客户服务，当请求完成之后，客户程序调用close()方法，将连接对象放回池中。

    其他几个连接池
    Spring 推荐使用dbcp；
    Hibernate 推荐使用c3p0和proxool

    1、 DBCP：apache
    DBCP(DataBase connection pool)数据库连接池。是apache上的一个 java连接池项目，也是 tomcat使用的连接池组件。单独使用dbcp需要3个包：common-dbcp.jar,common-pool.jar,common-collections.jar由于建立数据库连接是一个非常耗时耗资源的行为，所以通过连接池预先同数据库建立一些连接，放在内存中，应用程序需要建立数据库连接时直接到连接池中申请一个就行，用完后再放回去。dbcp没有自动的去回收空闲连接的功能。

    2、 C3P0：
    C3P0是一个开源的jdbc连接池，它实现了数据源和jndi绑定，支持jdbc3规范和jdbc2的标准扩展。c3p0是异步操作的，缓慢的jdbc操作通过帮助进程完成。扩展这些操作可以有效的提升性能。目前使用它的开源项目有Hibernate，Spring等。c3p0有自动回收空闲连接功能。

    3、 Proxool：Sourceforge
    Proxool是一种Java数据库连接池技术。是sourceforge下的一个开源项目,这个项目提供一个健壮、易用的连接池，最为关键的是这个连接池提供监控的功能，方便易用，便于发现连接泄漏的情况。
    综合来说，稳定性是Spring 推荐使用dbcp；
    Hibernate 推荐使用c3p0和proxool
    1、 DBCP：apache
    DBCP(DataBase connection pool)数据库连接池。是apache上的一个 java连接池项目，也是 tomcat使用的连接池组件。单独使用dbcp需要3个包：common-dbcp.jar,common-pool.jar,common-collections.jar由于建立数据库连接是一个非常耗时耗资源的行为，所以通过连接池预先同数据库建立一些连接，放在内存中，应用程序需要建立数据库连接时直接到连接池中申请一个就行，用完后再放回去。dbcp没有自动的去回收空闲连接的功能。

    2、 C3P0：
    C3P0是一个开源的jdbc连接池，它实现了数据源和jndi绑定，支持jdbc3规范和jdbc2的标准扩展。c3p0是异步操作的，缓慢的jdbc操作通过帮助进程完成。扩展这些操作可以有效的提升性能。目前使用它的开源项目有Hibernate，Spring等。c3p0有自动回收空闲连接功能。

    3、 Proxool：Sourceforge
    Proxool是一种Java数据库连接池技术。是sourceforge下的一个开源项目,这个项目提供一个健壮、易用的连接池，最为关键的是这个连接池提供监控的功能，方便易用，便于发现连接泄漏的情况。
    综合来说，稳定性是dbcp>=c3p0>proxool

    Druid介绍
    druid为阿里巴巴的数据源，（数据库连接池），集合了c3p0、dbcp、proxool等连接池的优点，还加入了日志监控，有效的监控DB池连接和SQL的执行情况。
    DRUID的DataSource类为：com.alibaba.druid.pool.DruidDataSource。
    */
}
