package simulacao;
import java.util.Comparator;
/**
 * Classe responsável pela ordenação da lista de eventos tendo em conta o tempo 
 * 
 * @author Afonso Lopes (83987), Mihir Odhavji (84151) e Tiago Silva (84193)
 *
 */
public class ComparadorEventos implements Comparator<Event> {
		/**
		 * Método que indica o comparador que será utilizado para ordenar a lista de eventos
		 * 
		 * @param e1 evento a ser comparado
		 * @param e2 evento a ser comparado
		 * return int a dizer qual deles acontece primeiro para ordenar a lista de eventos
		 */
	    public int compare(Event e1, Event e2)  {
	        if (e1.getTime() < e2.getTime()) {
	            return -1;
	        } 
	        else if (e1.getTime() >= e2.getTime()) {
	            return 1;
	        }
	        else {
	        	return 0;
	        }
	        
	    }
}
