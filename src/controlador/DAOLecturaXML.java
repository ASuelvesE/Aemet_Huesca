package controlador;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import modelo.Temperatura;

public class DAOLecturaXML {

	private	ArrayList<Temperatura> array;
	private String dia_formateado;
	private LocalDateTime actual;
	
	public DAOLecturaXML(ArrayList<Temperatura> array,String url) {
		this.array = array;
		try {
			leeXml(url);
		} catch (SAXException | IOException | ParserConfigurationException e) {
			System.out.println("No está disponible el XML");
		}
	}

	public void leeXml(String url) throws SAXException, IOException, ParserConfigurationException {
		String periodo = "";
		String valor = "";
		
		actual = LocalDateTime.now(ZoneId.systemDefault());
		dia_formateado = actual.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

		Document documento = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(url);
		NodeList root = documento.getChildNodes();

		for (int i = 0; i < root.getLength(); i++) {
			NodeList hijos = root.item(i).getChildNodes();

			for (int k = 0; k < hijos.getLength(); k++) {
				if (hijos.item(k).getNodeName().equals("prediccion")) {
					NodeList prediccion = hijos.item(k).getChildNodes();

					for (int j = 0; j < prediccion.getLength(); j++) {
						if (prediccion.item(j).getNodeName().equals("dia")) {
							NodeList dias = prediccion.item(j).getChildNodes();
							if (prediccion.item(j).hasAttributes()) {
								NamedNodeMap atributos_hora = prediccion.item(j).getAttributes();

								for (int z = 0; z < atributos_hora.getLength(); z++) {
									if (atributos_hora.item(z).getNodeName().equals("fecha")) {
										if (atributos_hora.item(z).getTextContent().equals(dia_formateado)) {
											NodeList hijos_fecha = prediccion.item(j).getChildNodes();

											for (int h = 0; h < hijos_fecha.getLength(); h++) {
												if (hijos_fecha.item(h).getNodeName().equals("temperatura")) {
													NamedNodeMap periodos = hijos_fecha.item(h).getAttributes();
													
													for (int a = 0; a < periodos.getLength(); a++) {
														periodo = periodos.item(a).getTextContent();
													}
													valor = hijos_fecha.item(h).getTextContent();
													array.add(new Temperatura(periodo,valor));
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

	public ArrayList<Temperatura> getArray() {
		return array;
	}

	public String getDia_formateado() {
		return dia_formateado;
	}

	public LocalDateTime getActual() {
		return actual;
	}
	
}
