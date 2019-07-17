package tech.feily.Mystar2.MyStar2.io;

import java.io.File;
import java.io.IOException;

import weka.core.Instances;
import weka.core.converters.CSVLoader;
import weka.core.converters.ConverterUtils.DataSource;

public class MyDataSourceImpl implements MyDataSource {

    public Instances readFromLocalFileWithDataSource(String fileName) throws Exception {
        if (isLegalExt(fileName)) {
            return DataSource.read(fileName);
        }
        return null;
    }

    public Instances readFromLocalFileWithCSVLoader(String fileName) throws IOException {
        if (isLegalExt(fileName)) {
            CSVLoader loader = MyCSVLoader.getInstance();
            loader.setSource(new File(fileName));
            return loader.getDataSet();
        }
        return null;
    }

    public Instances readFromDatabasesWithInstanceQuery() {
        // TODO Auto-generated method stub
        return null;
    }

    public Instances readFromDatabasesWithDatabaseLoader() {
        // TODO Auto-generated method stub
        return null;
    }

    public static boolean isLegalExt(String fileName) {
        String ext = fileName.substring(fileName.lastIndexOf('.') + 1);
        if (ext.equals("csv") || ext.equals("arff") || ext.equals("xrff")) {
            return true;
        }
        return false;
    }

}
