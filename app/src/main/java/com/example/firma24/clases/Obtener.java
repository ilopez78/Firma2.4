package com.example.firma24.clases;


public class Obtener{
    public int id;
    public  String descripcion;
    public byte[] firmadigital;
    public Obtener(int id,String descripcion, byte[] firmadigital){
        this.id=id;
        this.descripcion=descripcion;
        this.firmadigital=firmadigital;
    }
    public Obtener(){

    }

    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public byte[] getFirmadigital() {
        return firmadigital;
    }
}