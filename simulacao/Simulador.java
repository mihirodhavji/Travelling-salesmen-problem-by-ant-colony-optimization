package simulacao;
import java.util.PriorityQueue;

import parametros.ParametrosSaida;
/**
 * Classe que representa o simulador 
 * 
 * @author Afonso Lopes (83987), Mihir Odhavji (84151) e Tiago Silva (84193)
 *
 */

public class Simulador {
	
	
	public PriorityQueue<Event> events;
	public ParametrosSaida saida = null;
	/**
	 * Construtor
	 */
	public Simulador() {
		super();
		this.events = new PriorityQueue<>(new ComparadorEventos());
		this.saida = new ParametrosSaida();
	}
}
