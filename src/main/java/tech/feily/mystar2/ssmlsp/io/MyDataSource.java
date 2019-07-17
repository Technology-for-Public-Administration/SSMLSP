package tech.feily.mystar2.ssmlsp.io;

import java.io.IOException;

import weka.core.Instances;

public interface MyDataSource {

    public abstract Instances readFromLocalFileWithDataSource(String fileName) throws Exception;

    public abstract Instances readFromLocalFileWithCSVLoader(String fileName) throws IOException;

    public abstract Instances readFromDatabasesWithInstanceQuery();

    public abstract Instances readFromDatabasesWithDatabaseLoader();

}
