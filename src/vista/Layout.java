package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controlador.DAOFactory;
import modelo.Temperatura;

public class Layout extends JFrame implements ActionListener {

	JPanel principal, nav,momento,main,main_hora,main_temperatura, footer;
	JButton actualizar;
	JLabel ciudad, dia,hora;

	public Layout() {
		this.setTitle("Aemet Huesca");
		Toolkit pantalla = Toolkit.getDefaultToolkit();
		Dimension tamanoPantalla = new Dimension(pantalla.getScreenSize().width, pantalla.getScreenSize().height);
		this.setBounds(pantalla.getScreenSize().width / 3, pantalla.getScreenSize().height / 3,
				pantalla.getScreenSize().width / 2, pantalla.getScreenSize().height / 2);

		nav = new JPanel(new FlowLayout(FlowLayout.LEFT, pantalla.getScreenSize().width /8, 50));
		dia = new JLabel("Fecha: " + DAOFactory.getInstance().getLecturaXml().getDia_formateado());
		hora = new JLabel("Hora: " + DAOFactory.getInstance().getLecturaXml().getActual().format(DateTimeFormatter.ofPattern("hh-mm-ss")));
		momento = new JPanel(new GridLayout(2,1,10,10));
		momento.add(dia);
		momento.add(hora);
				
		ciudad = new JLabel("Huesca");
		nav.add(ciudad);
		nav.add(momento);
		

		main_hora = new JPanel(new FlowLayout(FlowLayout.LEFT,30,10));
		JLabel label_hora = new JLabel("Hola: ");
		label_hora.setPreferredSize(new Dimension(80,10));
		main_hora.add(label_hora);
		

		main_temperatura = new JPanel(new FlowLayout(FlowLayout.LEFT,30,10));
		main_temperatura.add(new JLabel("Temperatura: "));

		actualizaDatos();

		main = new JPanel(new GridLayout(2,2,10,10));
		main.add(main_hora);
		main.add(main_temperatura);

		actualizar = new JButton("Actualizar");
		actualizar.addActionListener(this);
		footer = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
		footer.add(actualizar);

		principal = new JPanel(new BorderLayout(30, 30));
		principal.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		principal.add(nav, BorderLayout.NORTH);
		principal.add(main, BorderLayout.CENTER);
		principal.add(footer, BorderLayout.SOUTH);

		this.add(principal);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	public void actualizaDatos() {
		ArrayList<Temperatura> array = DAOFactory.getInstance().getLecturaXml().getArray();
		for (Temperatura t : array) {
			JLabel hora = new JLabel(t.getPeriodo());
			hora.setForeground(new Color(255,0,0));
			JLabel temp = new JLabel(t.getValor());
			temp.setForeground(new Color(0,0,255));
			main_hora.add(hora);
			main_temperatura.add(temp);
		}  
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		DAOFactory.getInstance().getLecturaXml();
		LocalDateTime actual = LocalDateTime.now(ZoneId.systemDefault());
		dia.setText("Fecha: " + actual.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		hora.setText("Hora: " + actual.format(DateTimeFormatter.ofPattern("hh-mm-ss")));
	}

}
