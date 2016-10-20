/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import views.View_Producto;
import libs.Conectar;
/**
 *
 * @author Lenovo
 */
public class Controller_Producto extends javax.swing.JFrame implements ActionListener{
 View_Producto producto;
 Conectar cc = new Conectar();
 Connection cn = cc.conexion();
 
public Controller_Producto (View_Producto producto){
    this.producto = producto;
    this.producto.setVisible(true);
    this.producto.jtxt_id.setVisible(false);
    this.producto.jl_id.setVisible(false);
    this.producto.setLocationRelativeTo(this);
    this.producto.jbtn_buscar_id.addActionListener(this);
    this.producto.jbtn_buscar_producto.addActionListener(this);
    this.producto.jbtn_save.addActionListener(this);
    this.producto.jbtn_update.addActionListener(this);
    this.producto.jMenueliminar.addActionListener(this);
    this.producto.jMenumodificar.addActionListener(this);
    this.producto.jbtn_todo.addActionListener(this);
    this.producto.jbtn_update.setEnabled(false);
 } 
 
void save(){
   try {
       PreparedStatement pst = cn.prepareStatement("INSERT INTO productos (Producto,Descripcion,Precio_compra,Precio_venta,Existencia) VALUES (?,?,?,?,?)");
            pst.setString(1, producto.jtx_producto.getText());
            pst.setString(2, producto.jtxt_descripcion.getText());
            pst.setString(3, producto.jtxt_precio_compra.getText());
            pst.setString(4, producto.jtxt_precio_venta.getText());
            pst.setString(5, producto.jtxt_existencia.getText());
            pst.executeUpdate();
            Buscarid("");
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        producto.jtx_producto.setText("");
        producto.jtxt_descripcion.setText("");
        producto.jtxt_precio_compra.setText("");
        producto.jtxt_precio_venta.setText("");
        producto.jtxt_existencia.setText("");
    }
 
void Buscarid(String valor){
   DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("PRODUCTO");
        modelo.addColumn("DESCRIPCION");
        modelo.addColumn("PRECIO COMPRA");
        modelo.addColumn("PRECIO VENTA");
        modelo.addColumn("EXISTENCIA");
        this.producto.jtbl_productos.setModel(modelo);
        String sql = "";
        if (valor.equals("")) {
            sql = "SELECT * FROM Productos";
        } else {
            sql = "SELECT * FROM Productos WHERE Id_Producto='" + valor + "'";
        }

        String[] datos = new String[6];
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                datos[5] = rs.getString(6);
                modelo.addRow(datos);
            }
            this.producto.jtbl_productos.setModel(modelo);
        } catch (SQLException ex) {
            Logger.getLogger(Controller_Producto.class.getName()).log(Level.SEVERE, null, ex);
        }  
 }

void Buscarproducto(String valor){
   DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("PRODUCTO");
        modelo.addColumn("DESCRIPCION");
        modelo.addColumn("PRECIO COMPRA");
        modelo.addColumn("PRECIO VENTA");
        modelo.addColumn("EXISTENCIA");
        this.producto.jtbl_productos.setModel(modelo);
        String sql = "";
        if (valor.equals("")) {
            sql = "SELECT * FROM Productos";
        } else {
            sql = "SELECT * FROM Productos WHERE Producto='" + valor + "'";
        }

        
        String[] datos = new String[6];
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
        
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                datos[5] = rs.getString(6);
                modelo.addRow(datos);
            }
            this.producto.jtbl_productos.setModel(modelo);
        } catch (SQLException ex) {
            Logger.getLogger(Controller_Producto.class.getName()).log(Level.SEVERE, null, ex);
        }  
 }

void update (){
    boolean desbloquea = true;
    
    producto.jbtn_save.setEnabled(desbloquea);
    producto.jbtn_update.setEnabled(false);
    try {
       PreparedStatement pst = cn.prepareStatement("UPDATE Productos SET  Producto='" + producto.jtx_producto.getText() + "',Descripcion='" + producto.jtxt_descripcion.getText() + "',Precio_compra='" + producto.jtxt_precio_compra.getText() + "',Precio_venta='" + producto.jtxt_precio_venta.getText() + "',Existencia='" + producto.jtxt_existencia.getText()  + "' WHERE Id_Producto='" + producto.jtxt_id.getText() + "'");
       pst.executeUpdate();
        Buscarid("");
       } catch (Exception e) {
         System.out.print(e.getMessage());
        } 
        producto.jtx_producto.setText("");
        producto.jtxt_descripcion.setText("");
        producto.jtxt_precio_compra.setText("");
        producto.jtxt_precio_venta.setText("");
        producto.jtxt_existencia.setText("");
}

void modifica(){
    producto.jbtn_save.setEnabled(false);
    producto.jbtn_update.setEnabled(true);
    int fila = producto.jtbl_productos.getSelectedRow();
    if (fila >= 0) {
        producto.jtxt_id.setText(producto.jtbl_productos.getValueAt(fila, 0).toString());
        producto.jtx_producto.setText(producto.jtbl_productos.getValueAt(fila, 1).toString());
        producto.jtxt_descripcion.setText(producto.jtbl_productos.getValueAt(fila, 2).toString());
        producto.jtxt_precio_compra.setText(producto.jtbl_productos.getValueAt(fila, 3).toString());
        producto.jtxt_precio_venta.setText(producto.jtbl_productos.getValueAt(fila, 4).toString());
        producto.jtxt_existencia.setText(producto.jtbl_productos.getValueAt(fila, 5).toString());
    } else {
        JOptionPane.showMessageDialog(null, "No seleciono fila");
    }
}

void elimina (){
    int fila = producto.jtbl_productos.getSelectedRow();
    String id = "";
    id = producto.jtbl_productos.getValueAt(fila, 0).toString();
    int p =JOptionPane.showConfirmDialog(null,"Estas seguro de eliminar","Eliminar",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
    if (p ==0){
    try {
      PreparedStatement pst = cn.prepareStatement("DELETE FROM Productos WHERE  Id_Producto='" + id + "'");
        
        pst.executeUpdate();
        Buscarid("");
    
    } catch (Exception e) {
    }
    }
}

    @Override
    public void actionPerformed(ActionEvent e) {
         if (e.getSource() == this.producto.jbtn_save ){
           if(producto.jtx_producto.getText().equals("") || producto.jtxt_descripcion.getText().equals("")|| producto.jtxt_precio_compra.getText().equals("")|| producto.jtxt_precio_venta.getText().equals("") || producto.jtxt_existencia.getText().equals("")){
              JOptionPane.showMessageDialog(null,"Falta llenar algunos campos, por favor llenalos ");
             }
           else if (e.getSource() == this.producto.jbtn_save ){
               save();
           }
           
        }
        
        else if (e.getSource() == this.producto.jbtn_buscar_id){
             if(producto.jtxt_busca_id.getText().equals("")){
              JOptionPane.showMessageDialog(null,"Ingresa datos");
             }
             else {
               Buscarid(producto.jtxt_busca_id.getText()); 
               producto.jtxt_busca_id.setText("");
             }
        }
        
        else if (e.getSource() == this.producto.jbtn_buscar_producto){
             if(producto.jtxt_busca_producto.getText().equals("")){
              JOptionPane.showMessageDialog(null,"Ingresa datos");
             }
             else {
               Buscarproducto(producto.jtxt_busca_producto.getText()); 
               producto.jtxt_busca_producto.setText("");
             }
        }
        
        else if (e.getSource() == this.producto.jbtn_update){
            update();
        }
        
        else if (e.getSource() == this.producto.jMenumodificar){
            modifica();
        }
        
        else if (e.getSource() == this.producto.jMenueliminar){
           elimina();
        }
        
        else if (e.getSource() == this.producto.jbtn_todo){
            Buscarid("");
        }
        
            
     }


}
