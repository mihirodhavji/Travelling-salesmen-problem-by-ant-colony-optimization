package ant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import aresta.Aresta;
import parametros.ParametrosSaida;

/**
 * Classe que representa uma formiga
 * 
 * @author Afonso Lopes (83987), Mihir Odhavji (84151) e Tiago Silva (84193)
 *
 */

public class Ant {

	int node_atual;
	int nestnode;
	int nb_nodes_visitados = 0;
	public int []caminho = null;
	int pathWeight = 0;
	double plevel = 0;
	public Aresta [][]grafo;
	public double delta = 0;
	int W = 0;
	
	/**
	 * Construtor
	 * @param nestnode Como o nome indica nestnode
	 * @param nbnodes O numero total de nodes do grafo
	 * @param w       O peso total do grafo
	 * @param plevel  o nivel de pheromone
	 * @param delta   Delta do ficheiro xml
	 * @param grafo   Como o nome indica é o grafo
	 */
	public Ant(int nestnode, int nbnodes, int w, double plevel,double delta, Aresta [][]grafo) {
		super();
		this.node_atual = nestnode;
		this.nestnode = nestnode;
		this.nb_nodes_visitados = 1;
		this.caminho = new int[nbnodes + 1];
		Arrays.fill(this.caminho, -1);
		this.caminho[0] = node_atual;
		this.pathWeight = 0;
		this.plevel = plevel;
		this.grafo = grafo;
		this.delta = delta;
		this.W = w;
	}
	/**
	 * Método que calcula o peso do caminho que se encontra em saída, atualizando ou nao os parametros de saida a serem impressos regularmente
	 * e coloca pheromones nas arestas
	 * 
	 * @param saida Atualizar este parametro para depois ser impresso regularmente
	 */
	public void endCycle(ParametrosSaida saida) {/*deu a volta e chega ao nestnode, guarda o caminho, mete pheromones e volta a começar de novo*/
		this.calcPathweight();
		saida.setCaminho(this.caminho,this.pathWeight);
		this.layPheromone();
	}
	/**
	 * Método chamado quando um ciclo Hamiltoniano for encontrado, volta as variáveis necessárias 
	 * para os valores iniciais de modo que a formiga possa iniciar a procura de mais um ciclo Hamiltoniano
	 */
	public void reStart(){
		this.nb_nodes_visitados = 1;
		this.pathWeight = 0;
		Arrays.fill(this.caminho, 1, this.caminho.length, -1); 
	}
	/**
	 * Método que implementa o algoritmo da sec 2.3 e 2.4 do enunciado do projeto
	 * Começa-se por verificar quais os nodes que ainda não foram visitados e juntam-se esses numa lista
	 * Caso a lista não esteja vazia, é calculada a probabilidade de cada aresta ser utilizada e depois escolhida uma delas.
	 * Quando a lista estiver vazia, todos os nodes são depois adicionados a lista e é escolhido um ao acaso, com probabilidade igual para todos,
	 * e depois é apagado o loop criado por esta escolha
	 * @return Peso da aresta que irá seguir
	 */
	public int giveNextNode(){

		int i = 0, j = 0;
		double C_sum = 0;
		Random rand = new Random();
		double rf1 = rand.nextFloat(); 
		double previous = 0;
		ArrayList<Integer> list = new ArrayList<Integer>();
		ArrayList<Double> list1 = new ArrayList<Double>();
		Aresta []vizinho = this.grafo[this.node_atual];

		for(j = 0; j < (this.caminho).length - 1;j++ ) {
			if (vizinho[j] != null) {
				for (i = 0; i < this.nb_nodes_visitados; i++) {
					if (j == this.caminho[i]) { //// se o node ja tiver sido visitado previamente
						break;
					}
				}
				if ( i == (this.nb_nodes_visitados)) { /// quando i iguala a numero de nodes visitados, adiciona-se este node à lista dos possiveis nodes seguintes
					list.add(j);
					C_sum += vizinho[j].getC();
				}
			}
		}
		if (!list.isEmpty()){ //// se a lista dos possiveis nodes seguintes nao estiver vazia /// algoritmo da secção 2.3
			previous = 0;

			for (i = 0; i < list.size(); i++){
				list1.add( previous + (((vizinho[list.get(i)]).getC()) / C_sum ));
				previous += ( ( (vizinho[list.get(i)]).getC()) / C_sum) ;
			}
			for (i = 0; i < list1.size(); i++){
				if (rf1 < list1.get(i)) {
					break;
				}
			}
			this.caminho[this.nb_nodes_visitados++] = list.get(i);
			this.node_atual = list.get(i);
			return vizinho[this.node_atual].getWeight();
		}
		else {
			for(j = 0; j < (this.caminho).length - 1;j++ ) { /// por todos os vizinhos na lista de possiveis nodes seguintes
				if (vizinho[j] != null) {
					list.add(j);
				}
			}

			previous = 0;
			double aux = list.size();
			aux = 1 / aux;
			for (j = 0; j < list.size(); j++){ /// todos têm a mesma possibilidade
				list1.add(previous + aux);
				previous += aux;
			}
			for (j = 0; j < list1.size(); j++){
				if (rf1 < list1.get(j)) {
					break;
				}
			}
			int nextNode = list.get(j);
			if (nextNode == this.nestnode && this.nb_nodes_visitados == this.caminho.length - 1 ){
				this.node_atual = nextNode;
				this.caminho[this.nb_nodes_visitados++] = this.node_atual;
				return vizinho[this.node_atual].getWeight();	
			}
			
			boolean flag = false;
			for (j = 0; j < (this.caminho).length - 1; j++){///// retirar o loop do caminho
				if (nextNode == this.caminho[j]) {
					flag = true;
					this.nb_nodes_visitados = ++j;
				}
				if (flag) {
					this.caminho[j] = -1;
				}

			}
			///System.out.println("updated caminho"+ Arrays.toString(this.caminho));
			this.node_atual = nextNode;
			return vizinho[this.node_atual].getWeight();	
		}
	}
	/**
	 * Método que calcula o peso total do ciclo, apenas chamado 
	 * depois de descoberto um ciclo Hamiltoniano
	 */
	public void calcPathweight() {
		int weight = 0;
		for(int i = 0; i != (this.caminho).length - 1 ; i++) {
			weight += this.grafo[this.caminho[i]][this.caminho[i+1]].getWeight();
		}
		this.pathWeight = weight;
	}
	/**
	 * Método que coloca pheromones nas arestas pela quais a formiga passou após um ciclo ter sido encontrado
	 */
	public void layPheromone() {
		double lay = (this.W*this.plevel)/(this.pathWeight);
		for(int i = 0; i != (this.caminho).length - 1 ; i++) {
			this.grafo[this.caminho[i]][this.caminho[i+1]].setPheromone(this.grafo[this.caminho[i]][this.caminho[i+1]].getPheromone()+lay);
		}
	}
	/**
	 * Método que indica se o ciclo Hamiltoniano está concluido ou nao
	 * @return True se concluído, false caso contrário
	 */
	public boolean cicloDone() {
		for(int i = 0; i < (this.caminho).length ; i++) {
			if(this.caminho[i] == -1) {
				return false;
			}
		}
		return true;
	}
}
