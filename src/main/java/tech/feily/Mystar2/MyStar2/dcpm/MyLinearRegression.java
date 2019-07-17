package tech.feily.Mystar2.MyStar2.dcpm;

import java.util.Random;

import weka.classifiers.evaluation.Evaluation;
import weka.classifiers.functions.LinearRegression;
import weka.core.Instances;

public class MyLinearRegression {

    public static LinearRegression buildModel(Instances instances, String classIndex) throws Exception {
        if (instances != null) {
            instances.setClassIndex(Integer.parseInt(classIndex));
            LinearRegression model = new LinearRegression();
            model.buildClassifier(instances);
            return model;
        }
        return null;
    }
    
    public static Evaluation getEvaluation(Instances instances, LinearRegression model) throws Exception {
        if (instances != null && model != null) {
            Evaluation eval = new Evaluation(instances);
            eval.crossValidateModel(model, instances, 10, new Random(1), new String[] {});
            return eval;
        }
        return null;
    }
    
}
