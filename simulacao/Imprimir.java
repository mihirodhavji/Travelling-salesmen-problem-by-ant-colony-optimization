package simulacao;
/**
 * Classe que representa o evento responsável pela impressão dos dados de saída 
 * 
 * @author Afonso Lopes (83987), Mihir Odhavji (84151) e Tiago Silva (84193)
 *
 */
public class Imprimir extends Event{

	public Imprimir(double time) {
		super(time);
	}
	public void funcao_simular(Simulador sim){
			sim.saida.instant = this.getTime();
			sim.saida.print();
	}
}
