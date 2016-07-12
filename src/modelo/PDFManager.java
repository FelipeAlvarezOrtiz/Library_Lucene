package modelo;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PDFManager {

	private PDFParser parser;
	private PDFTextStripper pdfStripper;
	private PDDocument pdDoc;
	private COSDocument cosDoc;
	
	private File file;
	private String text;
	
	public PDFManager(){
		
	}
	
	public String ToText(String filePath) throws IOException{
		this.pdfStripper = null;
		this.pdDoc = null;
		this.cosDoc = null;
		
		file = new File(filePath);
		parser = new PDFParser(new RandomAccessFile(file,"r"));
		parser.parse();
		
		cosDoc = parser.getDocument();
		pdfStripper = new PDFTextStripper();
		pdDoc = new PDDocument(cosDoc);
		pdfStripper.setStartPage(1);
		pdfStripper.setEndPage(pdDoc.getNumberOfPages());
		text = pdfStripper.getText(pdDoc);
		cosDoc.close();
		return text;
	}
	
	
}
