package tech.feily.mystar2.ssmlsp.filters;

import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

public class MyRemove {

    public static Instances remove(Instances instances, String[] options) throws Exception {
        if (options[0].equalsIgnoreCase("R") && options.length > 2) {
            Remove remove = new Remove();
            remove.setOptions(options);
            remove.setInputFormat(instances);
            return Filter.useFilter(instances, remove);
        }
        return instances;

    }
}
