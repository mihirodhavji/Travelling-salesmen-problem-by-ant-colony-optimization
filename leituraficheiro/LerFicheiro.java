package leituraficheiro;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import aresta.Aresta;
import parametros.ParametrosEntrada;
/**
 * Classe que permite a leitura do ficheiro de entrada 
 * 
 * @author Afonso Lopes (83987), Mihir Odhavji (84151) e Tiago Silva (84193)
 *
 */

public class LerFicheiro extends DefaultHandler{
	
	ParametrosEntrada entry = new ParametrosEntrada();
	boolean b_weight = false;
	String aux;
	int i = 0, weight = 0;
	/**
	 * Método que devolve a entry
	 * @return Estrutura com os parametros de entrada
	 */
	public ParametrosEntrada getEntry() {
		return entry;
	}
	/**
	 * Método que efetua o parsing no ficheiro de entrada e guarda os dados na classe entry,
	 * o método testa a validade dos valores lidos para que não possuam causar erros ao
	 * funcionamento da simulação
	 * 
	 */
	public void startElement(String uri, String tag, String name, Attributes atts) throws SAXException{
		if(name.equalsIgnoreCase("simulation")) {
			for (i = 0; i < atts.getLength(); i++){
				if ( (atts.getLocalName(i)).equalsIgnoreCase("finalinst")){
					entry.setFinalinst(Float.parseFloat(atts.getValue(i)));
					if (entry.getFinalinst() <= 0) {
						System.out.println("Parametro finalinst inválido.");
				        System.exit(0);
					}
				}
				else if ( (atts.getLocalName(i)).equalsIgnoreCase("antcolsize") ){
					entry.setAntcolsize(Integer.parseInt(atts.getValue(i)));
					if (entry.getAntcolsize() <= 0) {
						System.out.println("Parametro Antcolsize inválido.");
				        System.exit(0);
					}
				}
				else if ( (atts.getLocalName(i)).equalsIgnoreCase("plevel") ){
					entry.setPlevel(Float.parseFloat(atts.getValue(i)));
				}
			}
		}
		if(name.equalsIgnoreCase("graph")) {
			for (int i = 0; i < atts.getLength(); i++){
				if ( (atts.getLocalName(i)).equalsIgnoreCase("nbnodes") ){
					entry.setNbnodes(Integer.parseInt(atts.getValue(i)));
					if (entry.getNbnodes() <= 0) {
						System.out.println("Parametro nbnodes inválido.");
				        System.exit(0);
					}
					/* criar grafo*/
					if (entry.grafo == null){
						entry.grafo = new Aresta[entry.getNbnodes()][entry.getNbnodes()];
					}	
				}
				else if ( (atts.getLocalName(i)).equalsIgnoreCase("nestnode") ){
					entry.setNestnode(Integer.parseInt(atts.getValue(i)));
					if (entry.getNestnode() <= 0 || entry.getNestnode() > entry.getNbnodes() ) {
						System.out.println("Parametro Nestnode inválido.");
				        System.exit(0);
					}
				}
			}
		}
		if(name.equalsIgnoreCase("node")) {
			if ( (atts.getLocalName(0)).equalsIgnoreCase("nodeidx") ){
				entry.setNodeidx(Integer.parseInt(atts.getValue(0)) - 1);
				if (entry.getNodeidx() < 0 || entry.getNodeidx() >= entry.getNbnodes() ) {////menor ou igual devido ao -1
					System.out.println("Parametro Nodeidx inválido." );
			        System.exit(0);
				}
			}
		}
		if(name.equalsIgnoreCase("weight")) {
			if ( (atts.getLocalName(0)).equalsIgnoreCase("targetnode") ){
				entry.setTargetnode(Integer.parseInt(atts.getValue(0)) - 1);
				if (entry.getTargetnode() < 0 || entry.getTargetnode() >= entry.getNbnodes() ) {
					System.out.println("Parametro Targetnode inválido.");
			        System.exit(0);
				}
				b_weight = true;
			}
		}
		if(name.equalsIgnoreCase("move")) {
			for (i = 0; i < atts.getLength(); i++){
				if ( (atts.getLocalName(i)).equalsIgnoreCase("alpha") ){
					entry.setAlpha(Float.parseFloat(atts.getValue(i)));
				}
				else if ( (atts.getLocalName(i)).equalsIgnoreCase("beta") ){
					entry.setBeta(Float.parseFloat(atts.getValue(i)));
				}
				else if ( (atts.getLocalName(i)).equalsIgnoreCase("delta") ){
					entry.setDelta(Float.parseFloat(atts.getValue(i)));
				}
			}
		}
		if(name.equalsIgnoreCase("evaporation")) {
			for (int i = 0; i < atts.getLength(); i++){
				if ( (atts.getLocalName(i)).equalsIgnoreCase("eta") ){
					entry.setEta(Float.parseFloat(atts.getValue(i)));
				}
				else if ( (atts.getLocalName(i)).equalsIgnoreCase("rho") ){
					entry.setRho(Float.parseFloat(atts.getValue(i)));
				}
				else{
					/* fazer imprimir erro*/
				}
			}
		}		
	}
	public void endElement(String uri,String name, String tag) {
		
	}
	/**
	 *  Método que retira e guarda a informação do elemento weight no grafo, devido à forma como este está definido a sua
	 *  leitura é efetuada separadamente do restante 
	 */
	public void characters(char[]ch,int start,int length){
		if(b_weight) {
			b_weight = false;
			aux = new String(ch, start, length);
			weight = Integer.parseInt(aux);
			if (weight < 1) {////o peso da aresta tem de no minimo ser 1 porque se for menor pode dar problemas no calculo do tempo do evento seguinte
				System.out.println("Parametro weight inválido.");
		        System.exit(0);
			}
			/* adcionar ao grafo o edge nodeidx e targetnode*/
			if ( entry.grafo != null) {
				entry.grafo[entry.getNodeidx()][entry.getTargetnode()] = new Aresta(weight,0);
				entry.grafo[entry.getTargetnode()][entry.getNodeidx()] = entry.grafo[entry.getNodeidx()][entry.getTargetnode()];
				entry.setW(entry.getW() + weight);
			}
		}
	}
}