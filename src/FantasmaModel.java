import java.util.ArrayList;
import java.util.List;

public class FantasmaModel {
	private List<FantasmaModel> lista;
	
	private String producto;
	private int codigoo;
	private Object quantidad;
	private double pcompra;
	private double pvenda;
	private String fornece;
	
	public String getProducto() {
		return producto;
	}
	public FantasmaModel() {
		lista = new ArrayList<FantasmaModel>();
	}
	public List<FantasmaModel> getLista() {
		return lista;
	}
	public void setLista(List<FantasmaModel> lista) {
		this.lista = lista;
	}
	public Object setProducto(String producto) {
		return this.producto = producto;
	}
	public int getCodigoo() {
		return codigoo;
	}
	public Object setCodigoo(int codigoo) {
		return this.codigoo = codigoo;
	}
	public Object getQuantidad() {
		return quantidad;
	}
	public Object setQuantidad(Object object) {
		return this.quantidad = object;
	}
	public double getPcompra() {
		return pcompra;
	}
	public Object setPcompra(double pcompra) {
		return this.pcompra = pcompra;
	}
	public double getPvenda() {
		return pvenda;
	}
	public Object setPvenda(double pvenda) {
		return this.pvenda = pvenda;
	}
	public String getFornece() {
		return fornece;
	}
	public Object setFornece(String fornece) {
		return this.fornece = fornece;
	}
	public void salvarDados(int codigoo, String producto, Object quantidad, Double pcompra, Double pvenda, String fornece) {
		FantasmaModel novo = new FantasmaModel();
		novo.setCodigoo(codigoo);
		novo.setProducto(producto);
		novo.setQuantidad(quantidad);
		novo.setPcompra(pcompra);
		novo.setPvenda(pvenda);
		novo.setFornece(fornece);
		
		lista.add(novo);
	 }
}
