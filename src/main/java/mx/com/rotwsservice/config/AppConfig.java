package mx.com.rotwsservice.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import static org.hibernate.cfg.Environment.*;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@ComponentScans(value = {
    @ComponentScan("mx.com.rotwsservice.service")
    ,@ComponentScan("mx.com.rotwsservice.dao")})
public class AppConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private Environment env;
    
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "PUT", "DELETE", "POST");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");
    }

    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        System.out.println("mx.com.rotwsservice.config.AppConfig.getSessionFactory()");

        Properties props = new Properties();
        // Setting JDBC properties
        props.put(DRIVER, env.getProperty("mysql.driver"));
        props.put(URL, env.getProperty("mysql.url"));
        props.put(USER, env.getProperty("mysql.user"));
        props.put(PASS, env.getProperty("mysql.password"));

        // Setting Hibernate properties
        props.put(SHOW_SQL, env.getProperty("hibernate.show_sql"));
        props.put(HBM2DDL_AUTO, env.getProperty("hibernate.hbm2ddl.auto"));

        // Setting C3P0 properties
        props.put(C3P0_MIN_SIZE, env.getProperty("hibernate.c3p0.min_size"));
        props.put(C3P0_MAX_SIZE, env.getProperty("hibernate.c3p0.max_size"));
        props.put(C3P0_ACQUIRE_INCREMENT,
                env.getProperty("hibernate.c3p0.acquire_increment"));
        props.put(C3P0_TIMEOUT, env.getProperty("hibernate.c3p0.timeout"));
        props.put(C3P0_MAX_STATEMENTS, env.getProperty("hibernate.c3p0.max_statements"));

        factoryBean.setHibernateProperties(props);
        factoryBean.setPackagesToScan("mx.com.rotwsservice.model");

        return factoryBean;
    }

    @Bean
    public HibernateTransactionManager getTransactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(getSessionFactory().getObject());
        return transactionManager;
    }

    @Bean
    public JavaMailSenderImpl javaMailSenderImpl() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost("smtpout.secureserver.net");
        sender.setPort(25);
        sender.setUsername("no-reply@roomiesoftheworld.com");
        sender.setPassword("roomiematch-1234");

        Properties javaMailProperties = new Properties();

        javaMailProperties.put("mail.store.protocol", "smtp");
        javaMailProperties.put("mail.from", "no-reply@roomiesoftheworld.com");
        javaMailProperties.put("mail.smtp.auth", true);
        javaMailProperties.setProperty("mail.smtp.allow8bitmime", "true");
        javaMailProperties.setProperty("mail.smtps.allow8bitmime", "true");
//        javaMailProperties.put("mail.smtp.ssl.trust", "autodiscover.secureserver.net");
        javaMailProperties.put("mail.debug", "true");//Prints out everything on screen

//        javaMailProperties.setProperty("mail.smtp.auth", "true");
//        javaMailProperties.setProperty("mail.smtp.starttls.enable", "true");
        sender.setJavaMailProperties(javaMailProperties);
        return sender;
    }

    @Bean
    public VelocityEngineFactoryBean velocityEngine() {
        VelocityEngineFactoryBean engin = new VelocityEngineFactoryBean();
        return engin;
    }
}
