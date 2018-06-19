/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author LN710Q
 */
public class Inscripciones {
    private String carnet,nombres,apellidos,universidad;
    private int edad,estado;

    public Inscripciones() {
    }
    public Inscripciones(String carnet, String nombres, String apellidos, int edad, String universidad,int estado) {
        this.carnet = carnet;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.universidad = universidad;
        this.edad = edad;
        this.estado = estado;
    }

    public Inscripciones(String carnet, String nombres, String apellidos, String universidad, int estado) {
        this.carnet = carnet;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.universidad = universidad;
        this.estado = estado;
    }
    
    @Override
    public String toString() {
        return "Inscripciones{" + "carnet=" + carnet + ", nombres=" + nombres + ", apellidos=" + apellidos + ", universidad=" + universidad + ", edad=" + edad + ", estado=" + estado + '}';
    }
    
    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getUniversidad() {
        return universidad;
    }

    public void setUniversidad(String universidad) {
        this.universidad = universidad;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
}
