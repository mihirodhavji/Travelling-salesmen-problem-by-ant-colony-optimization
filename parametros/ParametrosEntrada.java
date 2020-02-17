package parametros;
import aresta.Aresta;
/**
 * Classe que representa os dados que serão lidos do ficheiro de entrada 
 * 
 * @author Afonso Lopes (83987), Mihir Odhavji (84151) e Tiago Silva (84193)
 *
 */
public class ParametrosEntrada {
	
	private int W = 0;
	
	private double finalinst = 0;
	private int antcolsize = 0;
	private double plevel = 0;

	private int nbnodes = 0;
	private int nestnode = 0;

	private int nodeidx = 0;
	private int targetnode = 0;

	private double alpha = 0;
	private double beta = 0;
	private double delta = 0;

	private double eta = 0;
	private double rho = 0;
	
	public Aresta[][] grafo = null;
	
	/**
	 * Construtor
	 */
	public ParametrosEntrada() {
		super();
	}
	/**
	 * @return Devolve o grafo
	 */
	public Aresta[][] getGrafo() {
		return grafo;
	}
	/**
	 * @return Devolve o peso total do grafo 
	 */
	public int getW() {
		return W;
	}
	/**
	 * @param w Novo valor para o peso total do grafo
	 */
	public void setW(int w) {
		W = w;
	}
	/**
	 * @return Devolve o instante final
	 */
	public double getFinalinst() {
		return finalinst;
	}
	/**
	 * @param finalinst Novo valor para o instante final
	 */
	public void setFinalinst(double finalinst) {
		this.finalinst = finalinst;
	}
	/**
	 * @return O numero de formigas
	 */
	public int getAntcolsize() {
		return antcolsize;
	}
	/**
	 * @param antcolsize Novo valor para o numero de formigas 
	 */
	public void setAntcolsize(int antcolsize) {
		this.antcolsize = antcolsize;
	}
	/**
	 * @return O nivel de pheromones
	 */
	public double getPlevel() {
		return plevel;
	}
	/**
	 * @param plevel Novo valor para o nivel de pheromones
	 */
	public void setPlevel(double plevel) {
		this.plevel = plevel;
	}
	/**
	 * @return O numero total de nodes do grafo
	 */
	public int getNbnodes() {
		return nbnodes;
	}
	/**
	 * @param nbnodes Novo valor para o numero total de nodes do grafo
	 */
	public void setNbnodes(int nbnodes) {
		this.nbnodes = nbnodes;
	}
	/**
	 * @return O node que ser� considerado como nest
	 */
	public int getNestnode() {
		return nestnode;
	}
	/**
	 * @param nestnode define o valor de nestnode
	 */
	public void setNestnode(int nestnode) {
		this.nestnode = nestnode;
	}
	/**
	 * @return último node lido do ficheiro
	 */
	public int getNodeidx() {
		return nodeidx;
	}
	/**
	 * @param nodeidx Novo node a ser analisado
	 */
	public void setNodeidx(int nodeidx) {
		this.nodeidx = nodeidx;
	}
	/**
	 * @return nó de destino do nodeidx
	 */
	public int getTargetnode() {
		return targetnode;
	}
	/**
	 * @param targetnode Novo node de destino
	 */
	public void setTargetnode(int targetnode) {
		this.targetnode = targetnode;
	}
	/**
	 * @return valor de alpha
	 */
	public double getAlpha() {
		return alpha;
	}
	/**
	 * @param alpha Novo valor para alpha
	 */
	public void setAlpha(double alpha) {
		this.alpha = alpha;
	}
	/**
	 * @return beta Valor de beta
	 */
	public double getBeta() {
		return beta;
	}
	/**
	 * @param beta Novo valor para beta 
	 */
	public void setBeta(double beta) {
		this.beta = beta;
	}
	/**
	 * @return delta Valor de delta
	 */
	public double getDelta() {
		return delta;
	}
	/**
	 * @param delta Novo valor de delta
	 */
	public void setDelta(double delta) {
		this.delta = delta;
	}
	/**
	 * @return valor de  eta
	 */
	public double getEta() {
		return eta;
	}
	/**
	 * @param eta Novo valor de eta
	 */
	public void setEta(double eta) {
		this.eta = eta;
	}
	/**
	 * @return Valor de rho
	 */
	public double getRho() {
		return rho;
	}
	/**
	 * @param rho Novo valor de rho
	 */
	public void setRho(double rho) {
		this.rho = rho;
	}
	
}
