package aresta;
/**
 * Classe que representa uma aresta do grafo
 * 
 * @author Afonso Lopes (83987), Mihir Odhavji (84151) e Tiago Silva (84193)
 *
 */
public class Aresta {
	
	private int weight;
	private double pheromone;
	private double C;
	public double alpha;
	public double beta;
	public double rho;
	public double eta;
	public boolean doing_evap;
	/**
	 * Construtor
	 * 
	 * @param weight Peso da aresta
	 * @param pheromone Nível de pheromones da aresta, inicialmente a 0
	 */
	public Aresta(int weight, double pheromone) {
		super();
		this.weight = weight;
		this.pheromone = pheromone;
		this.setC(0);
		this.doing_evap = false;
	}
	/**
	 * @return Peso da aresta
	 */
	public int getWeight() {
		return weight;
	}
	/**
	 * @return Pheromone da aresta
	 */
	public double getPheromone() {
		return pheromone;
	}
	/**
	 * @param pheromone Novo valor de pheromone da aresta
	 */
	public void setPheromone(double pheromone) {
		this.pheromone = pheromone;
		this.calcC();
	}
	/**
	 * Método que calcula o C(demoninador da fracção da secção 2.3) 
	 */
	public void calcC() {
		this.setC((this.alpha + this.pheromone)/(this.beta + this.weight));
	}
	/**
	 * @return C(numerador da fração da secção 2.3) 
	 */
	public double getC() {
		return C;
	}
	/**
	 * @param c Novo valor
	 */
	public void setC(double c) {
		C = c;
	}
	/**
	 * Método que diminui o nivel de pheromones de uma aresta
	 * indica também se é necessário fazer mais eventos de evaporação
	 * dependendo se o nivel de pheromones atingiu zero ou nao
	 * @return Indica se são necessárias mais evaporações
	 */
	public boolean reducePheromoneLevel(){
		if (this.getPheromone() > 0){
			if (this.getPheromone() - this.rho > 0) {
				this.setPheromone(this.getPheromone() - this.rho);
				return true;
			}
			else {
				this.setPheromone(0);
				this.doing_evap = false;
				return false;
			}
			
		}
		return false;
	}
	
	
	
	
	
	
}
