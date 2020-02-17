package main;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import ant.Ant;
import aresta.Aresta;
import leituraficheiro.LerFicheiro;
import leituraficheiro.VerificarFicheiro;
import simulacao.AntMove;
import simulacao.Event;
import simulacao.Imprimir;
import simulacao.Simulador;
import parametros.ParametrosEntrada;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
/**
 * Main Classe, implementa o simulador
 * 
 * @author Afonso Lopes (83987), Mihir Odhavji (84151) e Tiago Silva (84193)
 *
 */
public class Main{

	/**
	 * Começamos por verificar se foi dado um ficheiro de entrada e se este se encontra com a formatação correta 
	 * Em seguida é efetuada a sua leitura, criação do grafo, formigas e os movimentos iniciais de cada uma das formigas 
	 * Finalmente inicia-se a simulação
	 * 
	 * @param args Ficheiro xml
	 * 
	 * @throws ParserConfigurationException indica se existiu um erro na configuração do parser(xml não corresponde ao dtd)
	 * @throws IOException indica se ocorreu uma IOException
	 * @throws SAXException indica se ocorreu uma SAXException
	 */
	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException{
		
		/* fazer verifcar se tem ficheiro de entrada*/
		if(args.length == 0)
	    {
	        System.out.println("Falta o ficheiro.xml");
	        System.exit(0);
	    }
		if (VerificarFicheiro.verifica_ficheiro_com_DTD(args[0]) == false) {
			System.out.println("Ficheiro xml está mal feito");
	        System.exit(0);
		}
		
		int i,j = 0;
		double current = 0;
		
		SAXParserFactory fact = SAXParserFactory.newInstance();
		SAXParser saxParser = null;
		saxParser = fact.newSAXParser();
		
		// parse the XML document with this handler
		DefaultHandler handler = new LerFicheiro();
		
		try {
			saxParser.parse(args[0], handler);
		} 
		catch (SAXException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
		e.printStackTrace();
		}
		
		ParametrosEntrada entry = ((LerFicheiro) handler).getEntry();
		Aresta [][] grafo = entry.getGrafo();
		
		for (i = 0; i  < entry.getNbnodes(); i++) {
			for (j = i; j  < entry.getNbnodes(); j++) {
				if (grafo[i][j] != null) {
					grafo[i][j].alpha = entry.getAlpha();
					grafo[i][j].beta = entry.getBeta();
					grafo[i][j].rho = entry.getRho();
					grafo[i][j].eta = entry.getEta();
					grafo[i][j].calcC();
					}	
			}
		}
		/*criar as formigas num array*/
		Ant []formiga = new Ant[entry.getAntcolsize()];
		Simulador sim = new Simulador();
		for (i = 0; i <  entry.getAntcolsize(); i++) {
			formiga[i] = new Ant (entry.getNestnode() - 1, entry.getNbnodes(),entry.getW(),entry.getPlevel(),entry.getDelta(),entry.getGrafo());
			Event newEvent = new AntMove(0,formiga[i]);
			sim.events.add(newEvent);
		}
		for (i = 1;i<=20;i++) {
			Event new_Event = new Imprimir(i*(entry.getFinalinst()/20));
			sim.events.add(new_Event);
		}
		double currentTime = 0;
		double simulationEnd = current + entry.getFinalinst();
		Event currentEvent = sim.events.poll();
		while (currentTime <= simulationEnd) {
			currentEvent.funcao_simular(sim);
			currentEvent = sim.events.poll();
			currentTime = currentEvent.getTime();
			}
		System.exit(0);
		
	}
}
 