package modelo;

import java.util.UUID;

public class ItemOrden {
    
    private String id;
    private int cantidad;
    private Producto producto;
   

    public ItemOrden(int cantidad, Producto producto) {
        this.id = UUID.randomUUID().toString();
        this.cantidad = cantidad;
        this.producto = producto;
    }

    public ItemOrden() {
    }

    public String getId() {
        return id;
    }
    
    

    public int getCantidad() {
        return cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getTotalItem(){
        return cantidad * producto.getPrecio();
    }

    @Override
    public String toString() {
        return  "ItemOrden{" + "Id=" + id + ", cantidad=" + cantidad + ", producto=" + producto + '}';
    }
 
    
    
}
