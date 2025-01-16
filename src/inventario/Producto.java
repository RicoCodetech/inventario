package inventario;


public class Producto {
private int amount;
private String name;
private Section section;

	Producto(Section section, String name, int amount){
		this.amount = amount;
		this.name = name;
		this.section=section;
	}
	public int getAmount(){
		return amount;
	}
	public String getName() {
		return name;
	}
	public Section getSection() {
		return section;
	}
	
	public void setamount(int amount) {
		this.amount = this.amount + amount;
	}
	
	
}
