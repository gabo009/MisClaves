package com.example.nacho.misclaves;

public class Cuentas {
    private String nombre, usuario, url, clave, observacion;
    public  Cuentas (){}
    public Cuentas(String nombre, String usuario, String url, String clave, String observacion) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.url = url;
        this.clave = clave;
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
        String encriptada = "";
        int code;
        for (int x = 1; x <= clave.length(); ++x){
            code = clave.codePointAt(x-1) + 1;
            encriptada += Character.toString((char) code);
        }
        this.clave = encriptada;
    }
    public void setClaveEncript(String clave) {
        this.clave = clave;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String desEncript (String claveNum){
        if (claveNum.equals("666")){
            String desencriptada = "";
            int code;
            for(int x = 1; x <= this.clave.length(); ++x){
                code = this.clave.codePointAt(x-1) - 1;
                desencriptada += Character.toString((char) code);
            }
            return desencriptada;
        }
        return null;
    }

    @Override
    public String toString() {
        return nombre + " -> " + usuario;
    }
}
