package modelo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class WordExtractor {
	
	private String text;
	
	public WordExtractor(String File) throws FileNotFoundException, IOException{
		String currentDir = System.getProperty("user.dir");
		XWPFDocument docx = new XWPFDocument(new FileInputStream(currentDir+"/SourceFiles/"+File));
		XWPFWordExtractor we = new XWPFWordExtractor(docx);
		text = we.getText();
		we.close();
	}
	
	public String getText(){
		return this.text;
	}
	
}
