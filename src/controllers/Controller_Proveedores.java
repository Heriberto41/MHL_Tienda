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
import views.View_Proveedor;
import libs.Conectar;
/**
 *
 * @author Liz
 */
public class Controller_Proveedores extends javax.swing.JFrame implements ActionListener{
 View_Proveedor proveedores;
 Conectar cc = new Conectar();
 Connection cn = cc.conexion();
 
public Controller_Proveedores (View_Proveedor provedores){
    this.proveedores = provedores;
    this.proveedores.setVisible(true);
    this.proveedores.jtf_id.setVisible(false);
    this.proveedores.jl_id.setVisible(false);
    this.proveedores.setLocationRelativeTo(this);
    this.proveedores.jbtn_buscar_id.addActionListener(this);
    this.proveedores.jbtn_buscar_proveedor.addActionListener(this);
    this.proveedores.jbtn_save.addActionListener(this);
    this.proveedores.jbtn_update.addActionListener(this);
    this.proveedores.jMenueliminar.addActionListener(this);
    
    this.proveedores.jMenumodificar.addActionListener(this);
    this.proveedores.jbtn_todo.addActionListener(this);
    this.proveedores.jbtn_update.setEnabled(false);
} 
void save(){
   try {
       PreparedStatement pst = cn.prepareStatement("INSERT INTO proveedores (proveedor,Nombre,Precio_compra,Precio_venta,Existencia) VALUES (?,?,?,?,?)");
            pst.setString(1, proveedores.jtf_proveedor.getText());
            pst.setString(2, proveedores.jtf_nombre.getText());
            pst.setString(3, proveedores.jtf_rfc.getText());
            pst.setString(4, proveedores.jtf_calle.getText());
            pst.setString(5, proveedores.jtf_numero.getText());
            pst.setString(6, proveedores.jtf_colonia.getText());
            pst.setString(7, proveedores.jtf_ciudad.getText());
            pst.setString(8, proveedores.jtf_estado.getText());
            pst.setString(9, proveedores.jtf_nombredecontacto.getText());
            pst.setString(10, proveedores.jtf_telefono.getText());
            pst.setString(11, proveedores.jtf_email.getText());
            
            pst.executeUpdate();
            Buscarid("");
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        proveedores.jtf_proveedor.setText("");
        proveedores.jtf_nombre.setText("");
        proveedores.jtf_rfc.setText("");
        proveedores.jtf_calle.setText("");
        proveedores.jtf_numero.setText("");
        proveedores.jtf_colonia.setText("");
        proveedores.jtf_ciudad.setText("");
        proveedores.jtf_estado.setText("");
        proveedores.jtf_nombredecontacto.setText("");
        proveedores.jtf_telefono.setText("");
        proveedores.jtf_email.setText("");       
    }
 void Buscarid(String valor){
   DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("RFC");
        modelo.addColumn("CALLE");
        modelo.addColumn("NUMERO");
        modelo.addColumn("COLONIA");
        modelo.addColumn("ESTADO");
        modelo.addColumn("NOMBRE DE CONTACTO");
        modelo.addColumn("TELEFONO");
        modelo.addColumn("EMAIL");
        this.proveedores.jtbl_proveedores.setModel(modelo);
        String sql = "";
        if (valor.equals("")) {
            sql = "SELECT * FROM proveedores";
        } else {
            sql = "SELECT * FROM proveedores WHERE id_Provedor='" + valor + "'";
        }
        
        String[] datos = new String[10];
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
                datos[6] = rs.getString(7);
                datos[7] = rs.getString(8);
                datos[8] = rs.getString(9);
                datos[9] = rs.getString(10);
           
                
                modelo.addRow(datos);
            }
            
            this.proveedores.jtbl_proveedores.setModel(modelo);
        } catch (SQLException ex) {
            Logger.getLogger(Controller_Proveedores.class.getName()).log(Level.SEVERE, null, ex);
        }  
 }
 
 
void Buscarproducto(String valor){
   DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("RFC");
        modelo.addColumn("CALLE");
        modelo.addColumn("NUMERO");
        modelo.addColumn("COLONIA");
        modelo.addColumn("CIUDAD");
        modelo.addColumn("ESTADO");
        modelo.addColumn("NOMBRE DE CONTACTO");
        modelo.addColumn("TELEFONO");
        modelo.addColumn("EMAIL");
        this.proveedores.jtbl_proveedores.setModel(modelo);
        String sql = "";
        if (valor.equals("")) {
            sql = "SELECT * FROM Proveedores";
        } else {
            sql = "SELECT * FROM Proveedores WHERE proveedor='" + valor + "'";
        }

String[] datos = new String[11];
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
                datos[6] = rs.getString(7);
                datos[7] = rs.getString(8);
                datos[8] = rs.getString(9);
                datos[9] = rs.getString(10);
                datos[10] = rs.getString(11);
                modelo.addRow(datos);
            }
            this.proveedores.jtbl_proveedores.setModel(modelo);
        } catch (SQLException ex) {
            Logger.getLogger(Controller_Producto.class.getName()).log(Level.SEVERE, null, ex);
        }  
 }
void Buscarproveedor(String valor){
   DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("RFC");
        modelo.addColumn("CALLE");
        modelo.addColumn("NUMERO");
         modelo.addColumn("COLONIA");
        modelo.addColumn("CIUDAD");
        modelo.addColumn("ESTADO");
        modelo.addColumn("NUMERO DE CONTACTO");
        modelo.addColumn("TELEFONO");
        modelo.addColumn("EMAIL");
        this.proveedores.jtbl_proveedores.setModel(modelo);
        String sql = "";
        if (valor.equals("")) {
            sql = "SELECT * FROM Proveedores";
        } else {
            sql = "SELECT * FROM Proveedores WHERE Id_proveedor='" + valor + "'";
        }


String[] datos = new String[11];
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
                datos[6] = rs.getString(7);
                datos[7] = rs.getString(8);
                datos[8] = rs.getString(9);
                datos[9] = rs.getString(10);
                datos[10] = rs.getString(11);
                modelo.addRow(datos);
            }
            this.proveedores.jtbl_proveedores.setModel(modelo);
        } catch (SQLException ex) {
            Logger.getLogger(Controller_Proveedores.class.getName()).log(Level.SEVERE, null, ex);
        }  
     }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.proveedores.jbtn_save ){
           if(proveedores.jtf_proveedor.getText().equals("") || proveedores.jtf_nombre.getText().equals("")|| proveedores.jtf_rfc.getText().equals("")|| proveedores.jtf_calle.getText().equals("") || proveedores.jtf_numero.getText().equals("")|| proveedores.jtf_colonia.getText().equals("")|| proveedores.jtf_ciudad.getText().equals("")|| proveedores.jtf_estado.getText().equals("")|| proveedores.jtf_nombredecontacto.getText().equals("")|| proveedores.jtf_telefono.getText().equals("")|| proveedores.jtf_email.getText().equals("")){
              JOptionPane.showMessageDialog(null,"Falta llenar algunos campos, por favor llenalos ");
             }
           else if (e.getSource() == this.proveedores.jbtn_save ){
               save();
           }
        
    }
        else if (e.getSource() == this.proveedores.jbtn_buscar_id){
             if(proveedores.jtxt_busca_id.getText().equals("")){
              JOptionPane.showMessageDialog(null,"Ingresa datos");
             }
             else {
               Buscarid(proveedores.jtxt_busca_id.getText()); 
               proveedores.jtxt_busca_id.setText("");
             }
        }
        
        else if (e.getSource() == this.proveedores.jbtn_buscar_proveedor){
             if(proveedores.jtf_buscaproveedor.getText().equals("")){
              JOptionPane.showMessageDialog(null,"Ingresa datos");
             }
             else {
               Buscarproducto(proveedores.jtf_buscaproveedor.getText()); 
               proveedores.jtf_buscaproveedor.setText("");
             }
        }
        
        else if (e.getSource() == this.proveedores.jbtn_update){
            update();
        }
        
        else if (e.getSource() == this.proveedores.jMenumodificar){
            modifica();
        }
        
        else if (e.getSource() == this.proveedores.jMenueliminar){
           elimina();
        }
        
        else if (e.getSource() == this.proveedores.jbtn_todo){
            Buscarid("");
        }
        
            
    
    }
    void update (){
    boolean desbloquea = true;
    
    proveedores.jbtn_save.setEnabled(desbloquea);
    proveedores.jbtn_update.setEnabled(false);
    try {
        PreparedStatement pst = cn.prepareStatement("UPDATE Proveedores SET  Proveedor='" + proveedores.jtf_proveedor.getText() + "',Nombre='" + proveedores.jtf_nombre.getText() + "',Proveedores='" + proveedores.jtf_rfc.getText() + "',Calle='" + proveedores.jtf_calle.getText() + "',Numero='" + proveedores.jtf_numero.getText() + "',Colonia='" + proveedores.jtf_colonia.getText() + "',Ciudad='" + proveedores.jtf_ciudad.getText() + "',Estado='" + proveedores.jtf_estado.getText() + "',Nombre de contacto='" + proveedores.jtf_nombredecontacto.getText() + "',Telefono='" + proveedores.jtf_telefono.getText() + "',Email='" + proveedores.jtf_email.getText()+ "' WHERE Id_Producto='" + proveedores.jtf_id.getText() + "'");
       pst.executeUpdate();
        Buscarid("");
       } catch (Exception e) {
         System.out.print(e.getMessage());
        } 
        proveedores.jtf_proveedor.setText("");
        proveedores.jtf_nombre.setText("");
        proveedores.jtf_rfc.setText("");
        proveedores.jtf_calle.setText("");
        proveedores.jtf_numero.setText("");
        proveedores.jtf_colonia.setText("");
        proveedores.jtf_ciudad.setText("");
        proveedores.jtf_estado.setText("");
        proveedores.jtf_nombredecontacto.setText("");
        proveedores.jtf_telefono.setText("");
        proveedores.jtf_email.setText("");
}
    
void modifica(){
    proveedores.jbtn_save.setEnabled(false);
    proveedores.jbtn_update.setEnabled(true);
    int fila = proveedores.jtbl_proveedores.getSelectedRow();
    if (fila >= 0) {
        proveedores.jtf_id.setText(proveedores.jtbl_proveedores.getValueAt(fila, 0).toString());
        proveedores.jtf_proveedor.setText(proveedores.jtbl_proveedores.getValueAt(fila, 1).toString());
        proveedores.jtf_nombre.setText(proveedores.jtbl_proveedores.getValueAt(fila, 2).toString());
        proveedores.jtf_rfc.setText(proveedores.jtbl_proveedores.getValueAt(fila, 3).toString());
        proveedores.jtf_calle.setText(proveedores.jtbl_proveedores.getValueAt(fila, 4).toString());
        proveedores.jtf_numero.setText(proveedores.jtbl_proveedores.getValueAt(fila, 5).toString());
        proveedores.jtf_colonia.setText(proveedores.jtbl_proveedores.getValueAt(fila, 6).toString());
        proveedores.jtf_ciudad.setText(proveedores.jtbl_proveedores.getValueAt(fila, 7).toString());
        proveedores.jtf_estado.setText(proveedores.jtbl_proveedores.getValueAt(fila, 8).toString());
        proveedores.jtf_nombredecontacto.setText(proveedores.jtbl_proveedores.getValueAt(fila, 9).toString());
        proveedores.jtf_telefono.setText(proveedores.jtbl_proveedores.getValueAt(fila, 10).toString());
        proveedores.jtf_email.setText(proveedores.jtbl_proveedores.getValueAt(fila, 11).toString());
    } else {
        JOptionPane.showMessageDialog(null, "No seleciono fila");
    }
}

void elimina (){
    int fila = proveedores.jtbl_proveedores.getSelectedRow();
    String id = "";
    id = proveedores.jtbl_proveedores.getValueAt(fila, 0).toString();
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

 }