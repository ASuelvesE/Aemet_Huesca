package modelo;

public class Temperatura {
	
	private String periodo;
	private String valor;
	
	
	public Temperatura(String periodo,String valor) {
		this.periodo = periodo;
		this.valor = valor;
	}
	public String toString() {
		return "\nHora: " + periodo + "\nTemperatura: " + valor + "\n--------------------------";
	}
	public String getPeriodo() {
		return periodo;
	}
	public String getValor() {
		return valor;
	}
	
	
	
}
