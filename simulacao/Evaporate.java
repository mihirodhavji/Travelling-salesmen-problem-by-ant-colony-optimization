package simulacao;
import aresta.Aresta;
/**
 * Classe que representa o evento de evaporação das pheromones
 * 
 * @author Afonso Lopes (83987), Mihir Odhavji (84151) e Tiago Silva (84193)
 *
 */
public class Evaporate extends Event{
	
	Aresta aresta;
	/**
	 * Construtor
	 * @param time Tempo no qual irá decorrer a evaporação
	 * @param aresta Onde irá ocorrer a evaporação
	 */
	public Evaporate(double time, Aresta aresta) {
		super(time);
		this.aresta = aresta;
	}
	/**
	 * Método que reduz o nivel de pheromones na aresta e se necessário gera um novo evento de
	 * evaporação
	 */
	public void funcao_simular(Simulador sim){
		sim.saida.incEvap();
		if (this.aresta.reducePheromoneLevel()) {
			Event newEvent = new Evaporate(this.time + expRandom(this.aresta.eta),this.aresta);
			sim.events.add(newEvent);
		}
		
	}
	

}
