package controlador;

import java.util.ArrayList;

import modelo.Temperatura;

public class DAOFactory {

    private static DAOFactory daoFactory;
	private DAOLecturaXML getLecturaXml;
	private ArrayList<Temperatura> array;
	
	
    private DAOFactory(){}

    public static DAOFactory getInstance() {
        if(daoFactory==null){
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }
    
    public DAOLecturaXML getLecturaXml(){
		String url = "http://www.aemet.es/xml/municipios_h/localidad_h_22125.xml";
    	array = new ArrayList<>();
        if(this.getLecturaXml == null){
            this.getLecturaXml = new DAOLecturaXML(array,url);
        }
        return this.getLecturaXml;
    }

	public ArrayList<Temperatura> getArray() {
		return array;
	}
	
}
 