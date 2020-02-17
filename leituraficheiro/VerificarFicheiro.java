package leituraficheiro;

import java.io.IOException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.InputSource;
/**
 * Classe que verifica se o ficheiro de entrada possuí o formatação correta
 * 
 * @author Afonso Lopes (83987), Mihir Odhavji (84151) e Tiago Silva (84193)
 *
 */
public class VerificarFicheiro{

	/**
	 * Método que verifica se o ficheiro xml de entrada apresenta o formato correto
	 * através da sua comparação com o ficheiro dtd indicado  
	 * 
	 * @param ficheiro_xml ficheiro de input em .xml
	 * 
	 * @return true, caso o ficheiro xml se encontre no formato correto,
	 * caso contrário retorna false
	 * 
	 * @throws ParserConfigurationException indica se existiu um erro na configuração do parser(xml não corresponde ao dtd)
	 * @throws IOException indica se ocorreu uma IOException
	 */
	public static boolean verifica_ficheiro_com_DTD(String ficheiro_xml) 
			throws ParserConfigurationException, IOException
	{
		try {

			SAXParserFactory factory = SAXParserFactory.newInstance();
			factory.setValidating(true);
			factory.setNamespaceAware(true);

			SAXParser parser = factory.newSAXParser();

			XMLReader reader = parser.getXMLReader();
			reader.setErrorHandler(new ErrorHandler() {
				public void warning(SAXParseException e) throws SAXException {
					System.out.println("warning at line " + e.getLineNumber() + " " + e.getMessage());
				}

				public void error(SAXParseException e) throws SAXException {
					System.out.println("error at line " + e.getLineNumber() + " "  + e.getMessage());
					throw e;
				}

				public void fatalError(SAXParseException e) throws SAXException {
					System.out.println("fatal error at line " + e.getLineNumber() + " "  + e.getMessage());
					throw e;
				}
			}
					);
			reader.parse(new InputSource(ficheiro_xml));
			return true;
		}
		catch (ParserConfigurationException manda1) {
			throw manda1;
		} 
		catch (IOException manda2) {
			throw manda2;
		}
		catch (SAXException manda3){
			return false;
		}
	}


}


