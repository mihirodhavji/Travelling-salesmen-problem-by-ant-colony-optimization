package simulacao;
import ant.Ant;
/**
 * Classe que representa o movimento da formiga 
 * 
 * @author Afonso Lopes (83987), Mihir Odhavji (84151) e Tiago Silva (84193)
 *
 */
public class AntMove extends Event{

	Ant formiga;
	/**
	 * Construtor
	 * @param time Tempo no qual se irá executar
	 * @param formiga A formiga que irá mover
	 */
	public AntMove(double time, Ant formiga) {
		super(time);
		this.formiga = formiga;
	}
	/**
	 * Método que movimenta uma formiga e coloca o proximo movimento na lista de eventos
	 * também verifica se o ciclo Hamiltoniano foi concluído ou não
	 * @param sim Objeto simulador que será atualizado
	 */
	public void funcao_simular(Simulador sim){
		int weight = 0;
		sim.saida.incMove();
		if (this.formiga.cicloDone()) {
			this.formiga.endCycle(sim.saida);
			for(int i = 0; i != (this.formiga.caminho).length - 1 ; i++) {
				if ((this.formiga.grafo[this.formiga.caminho[i]][this.formiga.caminho[i+1]]).doing_evap == false) {
					Event evap = new Evaporate(this.time + expRandom(this.formiga.grafo[this.formiga.caminho[i]][this.formiga.caminho[i+1]].eta),
							this.formiga.grafo[this.formiga.caminho[i]][this.formiga.caminho[i+1]]);
					sim.events.add(evap);
					(this.formiga.grafo[this.formiga.caminho[i]][this.formiga.caminho[i+1]]).doing_evap = true;
				}
			}
			this.formiga.reStart();
		}
		weight = this.formiga.giveNextNode();
		Event newEvent = new AntMove(this.time + expRandom(weight*this.formiga.delta),this.formiga);
		sim.events.add(newEvent);
		
	}

}
