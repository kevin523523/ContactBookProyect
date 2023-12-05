/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.mavenproject1;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.util.ArrayList;



/**
 *
 * @author super
 */
public class OperacionesArchivo {
    
    private FileOutputStream fout =null;
    private ObjectInputStream in = null;
    
    /*
    public Cliente leerArchivoTarjeta(String archivo)
    {
        Cliente t = null;
            
        try{
            in=new ObjectInputStream(new FileInputStream(archivo));
            t = (Cliente)in.readObject();       
        }catch (IOException ex) {
            System.out.println("Error: "+ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OperacionesArchivo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t; 
    }
    */
    
     public linkedList<Perfil> leerArchivoContactos(String archivo) 
    {
        linkedList<Perfil> contactos = null;
            
        try{
            archivo = "c" + archivo + ".ser";
            in = new ObjectInputStream(new FileInputStream(archivo));
            contactos = new linkedList<>();
            contactos = (linkedList<Perfil>) in.readObject();
        }catch (EOFException ex) {
            try {
                in.close();
            } catch (IOException ex1) {
                ex.printStackTrace();
            }
        } catch (IOException ex)
        {
            contactos = null;
        }
        catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return contactos; 
    }
}
