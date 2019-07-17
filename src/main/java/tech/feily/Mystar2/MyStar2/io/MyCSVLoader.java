package tech.feily.Mystar2.MyStar2.io;

import weka.core.converters.CSVLoader;

public final class MyCSVLoader {

    private MyCSVLoader() {
        
    }
    
    private static class Loader {
        private static CSVLoader CSVLOADER = new CSVLoader();
    }
    
    public static CSVLoader getInstance() {
        return Loader.CSVLOADER;
    }
    
}
