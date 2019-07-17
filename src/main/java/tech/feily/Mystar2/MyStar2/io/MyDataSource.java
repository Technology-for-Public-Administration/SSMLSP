package tech.feily.Mystar2.MyStar2.io;

import java.io.IOException;

import weka.core.Instances;

public interface MyDataSource {

    public abstract Instances readFromLocalFileWithDataSource(String fileName) throws Exception;
    public abstract Instances readFromLocalFileWithCSVLoader(String fileName) throws IOException;

    public abstract Instances readFromDatabasesWithInstanceQuery();
    public abstract Instances readFromDatabasesWithDatabaseLoader();
	
}
