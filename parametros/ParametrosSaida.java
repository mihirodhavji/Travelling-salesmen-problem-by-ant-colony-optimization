package parametros;
import java.util.Arrays;
/**
 * Classe que representa os dados que serão imprimidos pelo evento Imprimir
 * 
 * @author Afonso Lopes (83987), Mihir Odhavji (84151) e Tiago Silva (84193)
 *
 */
public class ParametrosSaida {

	public int obs_num;
	public int moveEvents;
	public int evaporationEvents;
	public double instant;
	public int [] caminho;
	public boolean caminho_done;
	public int pathWeight;
	
	/**
	 * Construtor
	 */
	public ParametrosSaida() {
		super();
		this.caminho = null;
		caminho_done = false;
		obs_num = 1;
		moveEvents = 0;
		evaporationEvents = 0;
		instant = 0;
	}
	/**
	 * Incrementa o numero de observações
	 */
	public void incObs() {
		this.obs_num++;
	}
	/**
	 * Incrementa o counter de moves
	 */
	public void incMove() {
		this.moveEvents++;
	}
	/**
	 * Incrementa o counter de evaporações
	 */
	public void incEvap() {
		this.evaporationEvents++;
	}
	/**
	 * Método que atualiza o caminho se o peso for menor que aquele que
	 * este objeto está a guardar atualmente
	 * @param caminho Caminho que a formiga descobriu
	 * @param peso Peso do mesmo caminho
	 */
	public void setCaminho(int[] caminho, int peso) {
		if (!this.caminho_done) { //// primeiro ciclo encontrado
			this.pathWeight = peso;
			this.caminho = caminho.clone();
			this.caminho_done = true;
			this.incPathPrint();
		} 
		if (this.caminho_done && peso < this.pathWeight) {
			this.caminho = caminho.clone();
			this.pathWeight = peso;
			this.incPathPrint();
		}
	}
	/**
	 * Como a contagem começa em zero, temos de imprimir a começar em 1
	 */
	public void incPathPrint(){
		for (int i = 0; i < this.caminho.length; i++) {
			this.caminho[i]++;
		}
	}
	/**
	 * Imprime os parametros de saida no modo que o enunciado do projeto indica
	 * Incrementa as observações
	 */
	public void print() {
		System.out.println("Observation number:"+this.obs_num);
		System.out.println("\t\t\tPresent Instant:\t\t"+this.instant);
		System.out.println("\t\t\tNumber of move events:\t\t"+this.moveEvents);
		System.out.println("\t\t\tNumber of evaporation events:\t"+this.evaporationEvents);
		if (this.caminho_done) {
			System.out.println("\t\t\tHamiltonian cycle:\t\t"+Arrays.toString(this.caminho));
		}
		else {
			System.out.println("\t\t\tHamiltonian cycle:");
		}
		this.incObs();
	}
}
