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
import views.View_Cliente;
import libs.Conectar;
/**
 *
 * @author Liz
 */
public class Controller_Clientes extends javax.swing.JFrame implements ActionListener{
 View_Cliente cliente;
 Conectar cc = new Conectar();
 Connection cn = cc.conexion();
 
public Controller_Clientes (View_Cliente cliente){
    this.cliente = cliente;
    this.cliente.setVisible(true);
    this.cliente.jtf_id.setVisible(false);
    this.cliente.jl_id.setVisible(false);
    this.cliente.setLocationRelativeTo(this);
    this.cliente.jbtn_buscar_id.addActionListener(this);
    this.cliente.jbtn_buscar_proveedor.addActionListener(this);
    this.cliente.jbtn_save.addActionListener(this);
    this.cliente.jbtn_update.addActionListener(this);
    this.cliente.jMenueliminar.addActionListener(this);
    
    this.cliente.jMenumodificar.addActionListener(this);
    this.cliente.jbtn_todo.addActionListener(this);
    this.cliente.jbtn_update.setEnabled(false);
} 
void save(){
   try {
       PreparedStatement pst = cn.prepareStatement("INSERT INTO cliente (cliente,Nombre,Precio_compra,Precio_venta,Existencia) VALUES (?,?,?,?,?)");
            pst.setString(1, cliente.jtf_id.getText());
            pst.setString(2, cliente.jtf_nombre.getText());
            pst.setString(3, cliente.jtf_rfc.getText());
            pst.setString(4, cliente.jtf_calle.getText());
            pst.setString(5, cliente.jtf_numero.getText());
            pst.setString(6, cliente.jtf_colonia.getText());
            pst.setString(7, cliente.jtf_ciudad.getText());
            pst.setString(8, cliente.jtf_estado.getText());
            pst.setString(10, cliente.jtf_telefono.getText());
            pst.setString(11, cliente.jtf_email.getText());
            
            pst.executeUpdate();
            Buscarid("");
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        cliente.jtf_id.setText("");
        cliente.jtf_nombre.setText("");
        cliente.jtf_rfc.setText("");
        cliente.jtf_calle.setText("");
        cliente.jtf_numero.setText("");
        cliente.jtf_colonia.setText("");
        cliente.jtf_ciudad.setText("");
        cliente.jtf_estado.setText("");
        cliente.jtf_telefono.setText("");
        cliente.jtf_email.setText("");       
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
        modelo.addColumn("TELEFONO");
        modelo.addColumn("EMAIL");
        this.cliente.jtbl_cliente.setModel(modelo);
        String sql = "";
        if (valor.equals("")) {
            sql = "SELECT * FROM cliente";
        } else {
            sql = "SELECT * FROM cliente WHERE id_cliente='" + valor + "'";
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
            
            this.cliente.jtbl_cliente.setModel(modelo);
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
        modelo.addColumn("TELEFONO");
        modelo.addColumn("EMAIL");
        this.cliente.jtbl_cliente.setModel(modelo);
        String sql = "";
        if (valor.equals("")) {
            sql = "SELECT * FROM Cliente";
        } else {
            sql = "SELECT * FROM cliente WHERE proveedor='" + valor + "'";
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
            this.cliente.jtbl_cliente.setModel(modelo);
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
        this.cliente.jtbl_cliente.setModel(modelo);
        String sql = "";
        if (valor.equals("")) {
            sql = "SELECT * FROM Cliente";
        } else {
            sql = "SELECT * FROM Cliente WHERE Id_cliente='" + valor + "'";
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
            this.cliente.jtbl_cliente.setModel(modelo);
        } catch (SQLException ex) {
            Logger.getLogger(Controller_Clientes.class.getName()).log(Level.SEVERE, null, ex);
        }  
     }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.cliente.jbtn_save ){
           if( cliente.jtf_nombre.getText().equals("")|| cliente.jtf_rfc.getText().equals("")|| cliente.jtf_calle.getText().equals("") || cliente.jtf_numero.getText().equals("")|| cliente.jtf_colonia.getText().equals("")|| cliente.jtf_ciudad.getText().equals("")|| cliente.jtf_estado.getText().equals("")|| cliente.jtf_telefono.getText().equals("")|| cliente.jtf_email.getText().equals("")){
              JOptionPane.showMessageDialog(null,"Falta llenar algunos campos, por favor llenalos ");
             }
           else if (e.getSource() == this.cliente.jbtn_save ){
               save();
           }
        
    }
        else if (e.getSource() == this.cliente.jbtn_buscar_id){
             if(cliente.jtxt_busca_id.getText().equals("")){
              JOptionPane.showMessageDialog(null,"Ingresa datos");
             }
             else {
               Buscarid(cliente.jtxt_busca_id.getText()); 
               cliente.jtxt_busca_id.setText("");
             }
        }
        
        else if (e.getSource() == this.cliente.jbtn_buscar_proveedor){
             if(cliente.jtf_buscacliente.getText().equals("")){
              JOptionPane.showMessageDialog(null,"Ingresa datos");
             }
             else {
               Buscarproducto(cliente.jtf_buscacliente.getText()); 
               cliente.jtf_buscacliente.setText("");
             }
        }
        
        else if (e.getSource() == this.cliente.jbtn_update){
            update();
        }
        
        else if (e.getSource() == this.cliente.jMenumodificar){
            modifica();
        }
        
        else if (e.getSource() == this.cliente.jMenueliminar){
           elimina();
        }
        
        else if (e.getSource() == this.cliente.jbtn_todo){
            Buscarid("");
        }
        
            
    
    }
    void update (){
    boolean desbloquea = true;
    
    cliente.jbtn_save.setEnabled(desbloquea);
    cliente.jbtn_update.setEnabled(false);
    try {
        PreparedStatement pst = cn.prepareStatement("UPDATE Cliente SET  "'id='" + cliente.jtf_id.getText() + "',Nombre='" + cliente.jtf_nombre.getText() + "',Calle='" + cliente.jtf_calle.getText() + "',Numero='" + cliente.jtf_numero.getText() + "',Colonia='" + cliente.jtf_colonia.getText() + "',Ciudad='" + cliente.jtf_ciudad.getText() + "',Estado='" + cliente.jtf_estado.getText() + "',Telefono='" + cliente.jtf_telefono.getText() + "',Email='" cliente.jtf_email.getText()+ "' WHERE Id_Producto='" + cliente.jtf_id.getText() + "'");
       pst.executeUpdate();
        Buscarid("");
       } catch (Exception e) {
         System.out.print(e.getMessage());
        } 
       
        cliente.jtf_nombre.setText("");
        cliente.jtf_rfc.setText("");
        cliente.jtf_calle.setText("");
        cliente.jtf_numero.setText("");
        cliente.jtf_colonia.setText("");
        cliente.jtf_ciudad.setText("");
        cliente.jtf_estado.setText("");
  
        cliente.jtf_telefono.setText("");
        cliente.jtf_email.setText("");
}
    
void modifica(){
    cliente.jbtn_save.setEnabled(false);
    cliente.jbtn_update.setEnabled(true);
    int fila = cliente.jtbl_cliente.getSelectedRow();
    if (fila >= 0) {
        cliente.jtf_id.setText(cliente.jtbl_cliente.getValueAt(fila, 0).toString());
        cliente.jtf_nombre.setText(cliente.jtbl_cliente.getValueAt(fila, 1).toString());
        cliente.jtf_rfc.setText(cliente.jtbl_cliente.getValueAt(fila, 2).toString());
        cliente.jtf_calle.setText(cliente.jtbl_cliente.getValueAt(fila, 3).toString());
        cliente.jtf_numero.setText(cliente.jtbl_cliente.getValueAt(fila, 4).toString());
        cliente.jtf_colonia.setText(cliente.jtbl_cliente.getValueAt(fila, 5).toString());
        cliente.jtf_ciudad.setText(cliente.jtbl_cliente.getValueAt(fila, 6).toString());
        cliente.jtf_estado.setText(cliente.jtbl_cliente.getValueAt(fila, 7).toString());
        cliente.jtf_telefono.setText(cliente.jtbl_cliente.getValueAt(fila, 8).toString());
        cliente.jtf_email.setText(cliente.jtbl_cliente.getValueAt(fila, 9).toString());
    } else {
        JOptionPane.showMessageDialog(null, "No seleciono fila");
    }
}

void elimina (){
    int fila = cliente.jtbl_cliente.getSelectedRow();
    String id = "";
    id = cliente.jtbl_cliente.getValueAt(fila, 0).toString();
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