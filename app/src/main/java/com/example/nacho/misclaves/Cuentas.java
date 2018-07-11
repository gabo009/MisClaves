package com.example.nacho.misclaves;

public class Cuentas {
    private String nombre, usuario, url, clave, observacion;

    public Cuentas(String nombre, String usuario, String url, String clave, String observacion) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.url = url;
        this.setClave(clave);
        this.observacion = observacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String desEncript (String claveNum){
        if (claveNum.equals("6666")){
            return clave;
        }
        return null;
    }

    @Override
    public String toString() {
        return nombre + " -> " + usuario;
    }
}
