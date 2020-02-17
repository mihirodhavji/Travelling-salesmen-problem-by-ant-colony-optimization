package simulacao;
import java.util.Random;
/**
 * Classe que representa um evento genérico
 * 
 * @author Afonso Lopes (83987), Mihir Odhavji (84151) e Tiago Silva (84193)
 *
 */
public class Event implements FuncaoSimular{
	
	protected final double time;
	static Random random = new Random();
	/**
	 * Construtor
	 * @param time Tempo no qual determinado evento irá decorrer
	 */
	public Event(double time) {
		super();
		this.time = time;
	}
	/**
	 * @return Tempo do evento
	 */
	public double getTime() {
        return this.time;
    }
	/**
	 * @param mean valor médio
	 * @return Tempo entre dois evento do mesmo objeto
	 */
	public static double expRandom(double mean) {
		
		double next = random.nextDouble();
		return -mean*Math.log(1.0-next);
	}
	/**
	 * Método que simula um evento, será modificado na
	 * respetiva subclasse
	 * @param sim : objeto que será atualizado, para depois ser impresso
	 */
	public void funcao_simular(Simulador sim) {}
}
