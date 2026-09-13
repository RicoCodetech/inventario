package inventario;

public class Producto {

    private int amount;
    private String name;
    private Section section;

    public Producto(Section section, String name, int amount) {
        this.section = section;
        this.name = name;
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }

    public Section getSection() {
        return section;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}