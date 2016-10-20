/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;
import views.*;
import controllers.*;
/**
 *
 * @author Lenovo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
      View_Proveedor proveedor = new View_Proveedor();
      Controller_Proveedores controller_Proveedores = new Controller_Proveedores(proveedor);
    }
    
}
