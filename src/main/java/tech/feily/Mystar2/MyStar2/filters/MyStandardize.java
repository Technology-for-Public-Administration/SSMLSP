package tech.feily.Mystar2.MyStar2.filters;

import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Standardize;

public class MyStandardize {

    public static Instances standardize(Instances before) throws Exception {
        if (before != null) {
            Standardize filter = new Standardize();
            filter.setInputFormat(before);
            return Filter.useFilter(before, filter);
        }
        return null;
    }
    
    public static Instances[] standardize(Instances train, Instances test) throws Exception {
        if (train != null && test != null) {
            Standardize filter = new Standardize();
            filter.setInputFormat(train);
            Instances[] instance = new Instances[2];
            instance[0] = Filter.useFilter(train, filter);
            instance[1] = Filter.useFilter(test, filter);
            return instance;
        }
        return null;
    }
    
}
