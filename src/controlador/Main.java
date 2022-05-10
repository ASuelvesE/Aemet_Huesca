package controlador;

import java.util.ArrayList;
import modelo.Temperatura;
import vista.Layout;
import javax.swing.SwingUtilities;

public class Main {

	public static void main(String[] args) {

		DAOFactory.getInstance().getLecturaXml();

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Layout vista = new Layout();
			}
			
		});

	}

}
