package tech.feily.Mystar2.MyStar2.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import tech.feily.Mystar2.MyStar2.io.MyDataSource;
import tech.feily.Mystar2.MyStar2.io.MyDataSourceImpl;
import weka.core.Instances;

/**
 * Hello world!
 *
 */
public class App {
    
    @SuppressWarnings("resource")
    public static void main( String[] args ) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        MyDataSource dataSource = context.getBean("myDataSourceImpl", MyDataSourceImpl.class);
        Instances instances = dataSource.readFromLocalFileWithDataSource("C:\\Users\\Administrator\\Desktop\\doc\\data.csv");
        System.out.println(instances.size());
    }
    
}
