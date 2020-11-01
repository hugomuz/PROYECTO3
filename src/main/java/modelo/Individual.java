package modelo;

public class Individual extends Cliente{
    private String dpi;

    public Individual(String dpi, String nombre, String direccion, String telefono, String tipoCliente) {
        super(nombre, direccion, telefono, tipoCliente);
        this.dpi = dpi;
    }

    public Individual() {
    }
    
    

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String DPI) {
        this.dpi = DPI;
    }

    @Override
    public String toString() {
        return super.toString() + ", DPI=" + dpi;
    }
    
    
}
