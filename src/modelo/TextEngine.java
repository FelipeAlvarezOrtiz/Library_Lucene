package modelo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;


public class TextEngine {
	
	Lucene searchEngine = new Lucene();
	
	public ArrayList<File> docxFiles;
	public ArrayList<File> pdfFiles;
	public ArrayList<File> txtFiles;
	
	//@PATH ES LA CARPETA DONDE SE ENCUENTRAN LOS ARCHIVOS A CONVERTIR
	public TextEngine(String path){
		File archivo = new File(path);
		File[] files = archivo.listFiles();
		if(files.length == 0){
			JOptionPane.showMessageDialog(null, "No hay archivos");
		}
		String archivos;
		pdfFiles = new ArrayList<File>();
		txtFiles = new ArrayList<File>();
		docxFiles = new ArrayList<File>();
		for(int i=0;i<files.length;i++){
			archivos = files[i].getName();
			if(archivos.endsWith(".pdf") || archivos.endsWith(".PDF")){
				pdfFiles.add(files[i]);
			}
			else if(archivos.endsWith(".doc") || archivos.endsWith(".docx") || archivos.endsWith(".DOC")){
				docxFiles.add(files[i]);
			}
			else if(archivos.endsWith(".txt")){
				txtFiles.add(files[i]);
			}
		}
		TextWritter(docxFiles,pdfFiles);
	}
	
	public void TextWritter(ArrayList<File> docxFiles, ArrayList<File> pdfFiles){
		for(int i = 0;i<pdfFiles.size();i++){
			try{
			PDFManager pdfConverter = new PDFManager();
			System.out.println("Escribiendo "+pdfFiles.get(i).getName());
			String nameAux = pdfFiles.get(i).getName().substring(0, pdfFiles.get(i).getName().length()-5);
			File archivoAux = new File("C:/Users/Falva/Java Work/Library_Lucene/ConvertedTexts/"+nameAux+".txt");
			BufferedWriter bw = new BufferedWriter(new FileWriter(archivoAux));
			bw.write(pdfConverter.ToText("C:/Users/Falva/Java Work/Library_Lucene/SourceFiles/"+pdfFiles.get(i).getName()));
			bw.close();
			}catch(IOException ex){
				JOptionPane.showMessageDialog(null, "Imposible leer archivos PDF");
				ex.printStackTrace();	
			}
		}
		for(int j=0;j<docxFiles.size();j++){
			try{
				System.out.println("Escribiendo "+docxFiles.get(j).getName());
				WordExtractor extractor = new WordExtractor(docxFiles.get(j).getName());
				String nameAux = docxFiles.get(j).getName().substring(0, docxFiles.get(j).getName().length()-5);
				File archivoAux = new File("C:/Users/Falva/Java Work/Library_Lucene/ConvertedTexts/"+nameAux+".txt");
				BufferedWriter bw = new BufferedWriter(new FileWriter(archivoAux));
				bw.write(extractor.getText());
				bw.close();
			}catch(IOException d){
				JOptionPane.showMessageDialog(null, "Imposible leer archivos DOCX");
			}
		}
		
		
	}
	
	public ArrayList<File> getDocx(){
		return this.docxFiles;
	}
	
	public ArrayList<File> getPdfs(){
		return this.pdfFiles;
	}
	
}
