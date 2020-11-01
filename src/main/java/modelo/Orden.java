package modelo;

import java.util.Date;

public class Orden {
    private int id;
    private Cliente cliente;
    private ItemOrden item1;
    private ItemOrden item2;
    private Date fechaOrden;
    private double precioEnvio;
    private double total;
    private String tipoEnvio;
    private String estado;
    private int diasEnvio;

public Orden() {
  
    }

    public Orden(Cliente cliente, ItemOrden item1, ItemOrden item2, Date fechaOrden, double precioEnvio, String tipoEnvio, String estado, int diasEnvio) {
        this.total = 0.0;
        this.cliente = cliente;
        this.item1 = item1;
        this.item2 = item2;
        this.fechaOrden = fechaOrden;
        this.precioEnvio = precioEnvio;
        this.tipoEnvio = tipoEnvio;
        this.estado = estado;
        this.diasEnvio = diasEnvio;
        getTotalOrden();
    }

    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public ItemOrden getItem1() {
        return item1;
    }

    public ItemOrden getItem2() {
        return item2;
    }

    public Date getFechaOrden() {
        return fechaOrden;
    }

    public double getPrecioEnvio() {
        return precioEnvio;
    }

    public double getTotal() {
        return total;
    }

    public String getTipoEnvio() {
        return tipoEnvio;
    }

    public String getEstado() {
        return estado;
    }

    public int getDiasEnvio() {
        return diasEnvio;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setItem1(ItemOrden item1) {
        this.item1 = item1;
    }

    public void setItem2(ItemOrden item2) {
        this.item2 = item2;
    }

    public void setFechaOrden(Date fechaOrden) {
        this.fechaOrden = fechaOrden;
    }

    public void setPrecioEnvio(double precioEnvio) {
        this.precioEnvio = precioEnvio;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setTipoEnvio(String tipoEnvio) {
        this.tipoEnvio = tipoEnvio;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setDiasEnvio(int diasEnvio) {
        this.diasEnvio = diasEnvio;
    }
    
    
   
    public void getTotalOrden() {
        
        if (cliente.getTipoCliente().equals("empresa")) {
            total = ((item1.getTotalItem() + item2.getTotalItem()) - getDescuento()) + precioEnvio;
        } else {
            total = item1.getTotalItem() + item2.getTotalItem() + precioEnvio;
        }
       
    }
    
    public double getDescuento(){
        Empresa e = (Empresa) cliente;
        double subtotal = 0.0;
        subtotal = item1.getTotalItem() + item2.getTotalItem();
        return ((subtotal / 100) * e.getDescuento());
    }

    @Override
    public String toString() {
        return "Orden{" + "id=" + id + ", cliente=" + cliente + ", item1=" + item1 + ", item2=" + item2 + ", fechaOrden=" + fechaOrden + ", precioEnvio=" + precioEnvio + ", total=" + total + ", tipoEnvio=" + tipoEnvio + ", estado=" + estado + ", diasEnvio=" + diasEnvio + '}';
    }
    
    
}
