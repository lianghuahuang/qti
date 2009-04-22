package pl.wiecek.qti.utils;

import java.io.File;
import java.io.FileFilter;

public class XMLDirectoryFilter implements FileFilter{
	   final static String xml = "xml";
	   private XMLFileFilter fileFilter;
	    public XMLDirectoryFilter() {
		  fileFilter = new XMLFileFilter();
	    }
	    // Accept all directories and xml files;
	    public boolean accept(File f) {
	    	return fileFilter.accept(f);
	    }
	    // The description of this filter
	    public String getDescription() {
	    	return fileFilter.getDescription();
	    }
}
