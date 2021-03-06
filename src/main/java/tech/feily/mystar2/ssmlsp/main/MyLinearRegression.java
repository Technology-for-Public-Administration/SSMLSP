package tech.feily.mystar2.ssmlsp.main;

import java.io.File;
import java.util.regex.Pattern;

import tech.feily.mystar2.ssmlsp.filters.MyRemove;
import tech.feily.mystar2.ssmlsp.filters.MyStandardize;
import tech.feily.mystar2.ssmlsp.io.MyDataSource;
import tech.feily.mystar2.ssmlsp.io.MyDataSourceImpl;
import weka.classifiers.evaluation.Evaluation;
import weka.classifiers.functions.LinearRegression;
import weka.core.Instances;

public class MyLinearRegression {

    public MyLinearRegression(String path, String[] remove, String classIndex) throws Exception {
        MyDataSource dataSource = new MyDataSourceImpl();
        Instances instances = null;
        Instances instancesAfterRemove = null;
        Instances instancesAfterStandardize = null;
        LinearRegression model = null;
        Evaluation eval = null;
        // Reading data.
        if (dataSource != null) {
            if (new File(path).exists() && new File(path).isFile()) {
                instances = dataSource.readFromLocalFileWithDataSource(path);
            } else {
                System.err.print("Error : Data file does not exist.\n");
                return;
            }
        }
        // Eliminating invalid data.
        if (instances != null && remove != null) {
            if (isLegalRemove(instances, remove)) {
                String[] rm = new String[remove.length + 1];
                rm[0] = "-R";
                for (int i = 1; i < rm.length; i++) {
                    rm[i] = remove[i - 1];
                }
                instancesAfterRemove = MyRemove.remove(instances, rm);
            } else {
                System.err.print("Error : Eliminate column index errors.\n");
                return;
            }
        } else {
            instancesAfterRemove = instances;
        }
        // Data standardization is under way.
        if (instancesAfterRemove != null) {
            if (isAllNumerical(instancesAfterRemove)) {
                instancesAfterStandardize = MyStandardize.standardize(instancesAfterRemove);
            } else {
                System.err.print("Error : All data set attributes must be numeric.\n");
            }
        } else {
            instancesAfterStandardize = instancesAfterRemove;
        }
        // Establishment of Linear Regression Model.
        if (instancesAfterStandardize != null && isLegalClassIndex(instancesAfterStandardize, classIndex)) {
            model = tech.feily.mystar2.ssmlsp.dcpm.MyLinearRegression.buildModel(instancesAfterStandardize,
                    classIndex);
        } else {
            System.err.print("Error : target index crossing boundaries or other reasons.\n");
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
            if (Pattern.compile("[0-9]*").matcher(rm).matches());
            else return false;
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
    
    public static boolean isAllNumerical(Instances instances) {
       for (int i = 0; i < instances.numAttributes(); i++) {
           if (!instances.attribute(i).isNumeric()) return false;
       }
       return true;
    }
}
