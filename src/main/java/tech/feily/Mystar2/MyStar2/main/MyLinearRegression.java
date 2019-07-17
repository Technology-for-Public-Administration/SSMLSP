package tech.feily.Mystar2.MyStar2.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import tech.feily.Mystar2.MyStar2.filters.MyRemove;
import tech.feily.Mystar2.MyStar2.filters.MyStandardize;
import tech.feily.Mystar2.MyStar2.io.MyDataSource;
import tech.feily.Mystar2.MyStar2.io.MyDataSourceImpl;
import weka.classifiers.evaluation.Evaluation;
import weka.classifiers.functions.LinearRegression;
import weka.core.Instances;

public class MyLinearRegression {

    @SuppressWarnings("resource")
    public MyLinearRegression(String path, String[] remove, int classIndex) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        MyDataSource dataSource = context.getBean("myDataSourceImpl", MyDataSourceImpl.class);
        Instances instances = null;
        Instances instancesAfterRemove = null;
        Instances instancesAfterStandardize = null;
        LinearRegression model = null;
        Evaluation eval = null;
        if (dataSource != null) {
            instances = dataSource.readFromLocalFileWithDataSource(path);
        }
        if (instances != null) {
            instancesAfterRemove = MyRemove.remove(instances, remove);
        }
        if (instancesAfterRemove !=  null) {
            instancesAfterStandardize = MyStandardize.standardize(instancesAfterRemove);
        }
        if (instancesAfterStandardize != null) {
            model = tech.feily.Mystar2.MyStar2.dcpm.MyLinearRegression.buildModel(instancesAfterStandardize, classIndex);
        }
        if (model != null) {
            eval = tech.feily.Mystar2.MyStar2.dcpm.MyLinearRegression.getEvaluation(instancesAfterStandardize, model);
        }
        System.out.println("The established linear regression model is as follows.\n\t" + model);
        System.out.println("The evaluation of linear regression model is as follows.\n\t" + eval.toSummaryString());
    }
    
}
