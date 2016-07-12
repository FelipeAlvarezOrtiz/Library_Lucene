package modelo;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queries.mlt.MoreLikeThis;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;

public class BusquedaAvanzada {

	public BusquedaAvanzada(){
		
	}
	//@param query es el string que se busca, esto cambiara ya que pretendo hacer busqueda por cada palabra
	//@param indexDir es la direccion del indice, sobrecargar metodos para uno en RAM y otro en disco
	public void busqueda(String query, Directory indexDir,StandardAnalyzer analyzer) throws IOException{
		IndexReader lector = DirectoryReader.open(indexDir);
		IndexSearcher indexBuscador = new IndexSearcher(lector);
		
		MoreLikeThis mlt = new MoreLikeThis(lector);
		mlt.setMinTermFreq(0); //La cantidad minima de terminos que podemos encontrar son 0
		mlt.setMinDocFreq(0);
		mlt.setFieldNames(new String[]{"Titulo","Contenido"});
		mlt.setAnalyzer(analyzer);
		
		Reader sReader = new StringReader(query);
		Query consulta = mlt.like(sReader, "Nombre");
		
		TopDocs topDocs = indexBuscador.search(consulta, 10);
		
		for(ScoreDoc scoreDoc : topDocs.scoreDocs){
			Document aSimilar = indexBuscador.doc(scoreDoc.doc);
			String titleMatch = aSimilar.get("Titulo");
			System.out.println("Titulo similar "+titleMatch);
			//Muestra solo el titulo agregar metodo para sobrecargar en contenido
		}
	}
	
	public void busqueda(){
		
	}
}
