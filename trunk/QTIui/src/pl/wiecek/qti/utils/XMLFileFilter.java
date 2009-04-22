package pl.wiecek.qti.utils;

import java.io.File;
import javax.swing.filechooser.FileFilter;


public class XMLFileFilter extends FileFilter {
    final static String xml = "xml";
    
    // Accept all directories and xml files;
    public boolean accept(File f) {

        if (f.isDirectory()) {
            return true;
        }

        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            String extension = s.substring(i+1).toLowerCase();
            if (xml.equals(extension)) {
                return true;
            } else {
                return false;
            }
        }

        return false;
    }
    
    // The description of this filter
    public String getDescription() {
        return "Just XML files (*.xml)";
    }
}

