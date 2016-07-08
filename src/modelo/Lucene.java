package modelo;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.queryparser.flexible.standard.parser.ParseException;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class Lucene {
	
	private Directory indice;
	private StandardAnalyzer analizador;
	private IndexWriter indiceWriter;
	private IndexSearcher buscador;
	
	public Lucene(){

	}
	
	//FilesPath es el path donde se buscaran los archivos a indexar, y IndexPath es el path donde se creara el nuevo directorio del index
	public void CrearIndice(String IndexPath,String FilesPath) throws IOException{
		File Directorio_Archivos = new File(FilesPath); //creamos el directorio de Archivos
		File Directorio_Indice = new File(IndexPath); //creamos directorio del Indice
		analizador = new StandardAnalyzer(Version.LUCENE_47);
		indice = FSDirectory.open(Directorio_Indice); //abrimos el directorio del indice
		IndexWriterConfig configuracion_Indice = new IndexWriterConfig(Version.LUCENE_47,analizador);
		indiceWriter = new IndexWriter(indice,configuracion_Indice);
		File[] archivos = Directorio_Archivos.listFiles();
		agregarDocumentos(archivos,FilesPath);
		this.indiceWriter.close();
	}
	
	public void agregarAlIndice(String FilesPath, String IndexPath) throws IOException{
		File Directorio_Archivos = new File(FilesPath);
		File Directorio_Indice = new File(IndexPath);
		analizador = new StandardAnalyzer(Version.LUCENE_47);
		indice = FSDirectory.open(Directorio_Indice);
		IndexWriterConfig configuracion_Indice = new IndexWriterConfig(Version.LUCENE_47,analizador);
		indiceWriter = new IndexWriter(indice,configuracion_Indice);
		File[] archivos = Directorio_Archivos.listFiles();
		agregarDocumentos(archivos,IndexPath);
		this.indiceWriter.close();
	}
	
	public void cargarIndice(String IndexFolderPath) throws IOException, ParseException{
		System.out.println(""+IndexFolderPath); 
		indice = FSDirectory.open(new File(IndexFolderPath));
		IndexWriterConfig configuracion_Indice = new IndexWriterConfig(Version.LUCENE_47,analizador);
		indiceWriter = new IndexWriter(indice,configuracion_Indice);
		this.indiceWriter.close();
	}
	
	//Agregar Documento al Indice
	//Parametros File_Name nombre del archivo
	//Parametros File_Contain contenido del archivo
	
	public void agregarDocumento(String File_Name, String File_Contain,String IndexFolderPath) throws IOException{
		/*indice = FSDirectory.open(new File(IndexFolderPath));
		IndexWriterConfig configuracion_Indice = new IndexWriterConfig(Version.LUCENE_47,analizador);
		indiceWriter = new IndexWriter(indice,configuracion_Indice);*/
		Document documento = new Document(); //documento agregado al index de Lucene
		FieldType configuracion_campo = new FieldType();
		configuracion_campo.setTokenized(true);
		configuracion_campo.setOmitNorms(true);
		configuracion_campo.setIndexed(true);
		configuracion_campo.setStored(true);
		Field[] campo = new Field[2];
		campo[0] = new Field("Nombre",File_Name,configuracion_campo);
		campo[1] = new Field("Contenido", File_Contain,configuracion_campo);
		//Agregar campos al documento
		for(int i = 0;i<campo.length;i++){
			documento.add(campo[i]);
		}
		this.indiceWriter.addDocument(documento); //se agrega el documento al indice de lucene
	}
	
	//Agrega una lista gigantes de Documentos de tipo FILE
	//@param File Array archivos
	
	public void agregarDocumentos(File[] fileList,String indexPath) throws IOException{
		for(int i= 0;i<fileList.length;i++){
			File archivo = fileList[i];
			if(!archivo.isDirectory() && !archivo.isHidden() && archivo.exists() && archivo.canRead() && (archivo.getName().endsWith(".txt"))){
				/*//filtramos los archivos en txt, el archivo que puede procesar Lucene
				if(archivo.getName().endsWith(".txt")){*/
					String fileName = archivo.getName();
					String fileContain = obtenerContenido(archivo);
					agregarDocumento(fileName,fileContain,indexPath);
			}
			else{
				JOptionPane.showMessageDialog(null, "El archivo no cumple con ciertas especificaciones, formato no permitido?");
			}
		}
	}
	
	public String obtenerContenido(File archivo) throws IOException{
		//leemos todas las lineas y las agregamos en una lista, y obtenemos el enconding del sistema
		List<String> lineas = Files.readAllLines(Paths.get(archivo.getPath()), Charset.defaultCharset());
		String contain = null;
		//juntamos las lineas del archivo, y las retornamos como un solo string largo
		for(int j= 0; j<lineas.size();j++){
			String linea = lineas.get(j);
			contain += linea;
		}
		return contain; //retornamos el contenido	
	}
	
	/*Buscar en el indice
	 *@param string SearchedString
	 *@param field campo donde se buscara la cadena
	 *Este metodo busca cuando hay root en el disco, si hay en RAM, crear otro metodo.
	 */
	//ScoreDoc mantiene los hits devueltos por topDocs, que representa los hits retornados por IndexSearch
	public ScoreDoc[] buscar(String SearchedString, String campo,String indexFolder) throws ParseException,IOException{
		try {
			Query consulta = new QueryParser(Version.LUCENE_47,campo,analizador).parse(SearchedString);
			int hitsPorPagina = 99999; //NxumeroMaximo de hits/campos a retornar
			
			DirectoryReader directorio = DirectoryReader.open(indice);
			buscador = new IndexSearcher(directorio);
			TopScoreDocCollector collector = TopScoreDocCollector.create(hitsPorPagina,true);
			buscador.search(consulta, collector);
			return (collector.topDocs().scoreDocs);
		} catch (org.apache.lucene.queryparser.classic.ParseException e) {
			JOptionPane.showMessageDialog(null, "Imposible realizar la consulta");
		}
		return null;
	}
	
	public void MostrarBusqueda(ScoreDoc[] SearchResults){
		if(SearchResults.length==0){
			JOptionPane.showMessageDialog(null, "La palabra no pudo ser encontrada en ningun campo");
		}
		else{
			for(int i=0;i< SearchResults.length;i++){
				try{
					int DocId = SearchResults[i].doc;
					Document documento = this.buscador.doc(DocId);
					String nombre_Archivo = documento.get("Nombre");
					System.out.println((i+1)+"= "+ nombre_Archivo);
				}catch(IOException ex){
					JOptionPane.showMessageDialog(null, "Resultados no disponibles, hizo busquedas ?");
				}
			}
		}
	}

	public boolean Verificador_Archivos(String Path){
		String archivos;
		File carpeta = new File(Path);
		File[] listOfFiles = carpeta.listFiles();
		for(int i = 0; i<listOfFiles.length;i++){
			archivos = listOfFiles[i].getName();
			if(archivos.endsWith(".pdf") || archivos.endsWith(".PDF")){
				return false;
			}
		}
		return true;
	}
	
	public File[] FolderFiles(String Path){
		File carpeta = new File(Path);
		File[] archivos = carpeta.listFiles();
		return archivos;
	}

	public void SetList(JList<String> lista,ScoreDoc[] resultados){
		DefaultListModel<String> model = new DefaultListModel<String>();
		if(resultados.length>0){
			for(int i = 0;i<resultados.length;i++){
				try{
					int DocId = resultados[i].doc;
					Document documento = this.buscador.doc(DocId);
					String Nombre_Archivo = documento.get("Nombre");
					model.addElement(Nombre_Archivo);
				}catch(IOException ex){
					JOptionPane.showMessageDialog(null, "Resultados no disponibles");
				}
			}
			lista.setModel(model);
		}
		else{
			JOptionPane.showMessageDialog(null, "No hay resultados");
		}
	}
	
	public void SetList(JList<String> lista,ScoreDoc[] resultadosNombre,ScoreDoc[] resultadosContenido){
		DefaultListModel<String> model = new DefaultListModel<String>();
		if(resultadosNombre.length>0 && resultadosContenido.length>0){
			for(int i = 0;i<resultadosNombre.length;i++){
				try{
					int DocId = resultadosNombre[i].doc;
					Document documento = this.buscador.doc(DocId);
					String Nombre_Archivo = documento.get("Nombre");
					model.addElement(Nombre_Archivo);
				}catch(IOException ex){
					
				}
			}
			for(int j =0;j<resultadosContenido.length;j++){
				try{
					int DocId = resultadosContenido[j].doc;
					Document documento = this.buscador.doc(DocId);
					String Nombre_Archivo = documento.get("Contenido");
					model.addElement(Nombre_Archivo);
				}catch(IOException ex){
					
				}
				
			}
			lista.setModel(model);
		}
		else{
			if(resultadosNombre.length==0 && resultadosContenido.length>0){
				JOptionPane.showMessageDialog(null, "No se Encontraron resultados en Nombre");
				
			}
			if(resultadosNombre.length>0 && resultadosContenido.length==0){
				JOptionPane.showMessageDialog(null, "No se encontraron resultados en Contenido");
			}
			else{
				JOptionPane.showMessageDialog(null, "No hay resultados en ningun campo");
			}
		}
	}
}
