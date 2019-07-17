package tech.feily.mystar2.ssmlsp.main;

import java.io.File;
import java.util.regex.Pattern;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import tech.feily.mystar2.ssmlsp.filters.MyRemove;
import tech.feily.mystar2.ssmlsp.filters.MyStandardize;
import tech.feily.mystar2.ssmlsp.io.MyDataSource;
import tech.feily.mystar2.ssmlsp.io.MyDataSourceImpl;
import weka.classifiers.evaluation.Evaluation;
import weka.classifiers.functions.LinearRegression;
import weka.core.Instances;

public class MyLinearRegression {

    @SuppressWarnings("resource")
    public MyLinearRegression(String path, String[] remove, String classIndex) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        MyDataSource dataSource = context.getBean("myDataSourceImpl", MyDataSourceImpl.class);
        Instances instances = null;
        Instances instancesAfterRemove = null;
        Instances instancesAfterStandardize = null;
        LinearRegression model = null;
        Evaluation eval = null;
        // Reading data.
        if (dataSource != null) {
            if (new File(path).exists()) {
                instances = dataSource.readFromLocalFileWithDataSource(path);
            } else {
                System.out.println("Error : Data file does not exist.");
                return;
            }
        }
        // Eliminating invalid data.
        if (instances != null) {
            if (isLegalRemove(instances, remove)) {
                String[] rm = new String[remove.length + 1];
                rm[0] = "-R";
                for (int i = 1; i < rm.length; i++) {
                    rm[i] = remove[i - 1];
                }
                instancesAfterRemove = MyRemove.remove(instances, rm);
            } else {
                System.out.println("Error : Eliminate column index errors.");
                return;
            }
        } else {
            instancesAfterRemove = instances;
        }
        // Data standardization is under way.
        if (instancesAfterRemove != null) {
            instancesAfterStandardize = MyStandardize.standardize(instancesAfterRemove);
        } else {
            instancesAfterStandardize = instancesAfterRemove;
        }
        // Establishment of Linear Regression Model.
        if (instancesAfterStandardize != null && isLegalClassIndex(instancesAfterStandardize, classIndex)) {
            model = tech.feily.mystar2.ssmlsp.dcpm.MyLinearRegression.buildModel(instancesAfterStandardize,
                    classIndex);
        } else {
            System.out.println("Error : target index crossing boundaries or other reasons.");
            return;
        }
        // Evaluating Linear Regression Model.
        if (model != null) {
            eval = tech.feily.mystar2.ssmlsp.dcpm.MyLinearRegression.getEvaluation(instancesAfterStandardize, model);
        }
        System.out.println("The established linear regression model is as follows.\n\t" + model);
        System.out.println("The evaluation of linear regression model is as follows.\n\t" + eval.toSummaryString());
    }

    public static boolean isLegalRemove(Instances instances, String[] remove) {
        for (String rm : remove) {
            if (Pattern.compile("[0-9]*").matcher(rm).matches())
                ;
            else
                return false;
        }
        for (int i = 0; i < remove.length; i++) {
            if (Integer.parseInt(remove[i]) >= 0 && Integer.parseInt(remove[i]) < instances.numAttributes()) {
                if (i == remove.length - 1)
                    return true;
            } else
                return false;
        }
        return false;
    }

    public static boolean isLegalClassIndex(Instances instances, String classIndex) {
        if (Pattern.compile("[0-9]*").matcher(classIndex).matches())
            ;
        else
            return false;
        if (Integer.parseInt(classIndex) >= 0 && Integer.parseInt(classIndex) < instances.numAttributes())
            return true;
        return false;
    }
}
