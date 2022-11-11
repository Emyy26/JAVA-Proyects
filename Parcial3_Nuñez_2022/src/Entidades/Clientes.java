/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;
import Class.Conexion;
import Class.Cliente;
import com.mysql.jdbc.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author emi
 */
public class Clientes extends Conexion{

    @Override
    public ArrayList getList(String query) {
        ArrayList<Cliente> clienteList = new ArrayList<Cliente>();
        try {
            Statement st = (Statement) getCon().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setId(rs.getInt("id"));
                c.setSurname(rs.getString("apellido"));
                c.setName(rs.getString("nombre"));
                c.setAddress(rs.getString("domicilio"));
                c.setDate_nac(rs.getDate("fecha_nac"));
                c.setDate_alta(rs.getDate("fecha_alta"));
                c.setDni(rs.getInt("nro_docu"));
                c.setCuil(rs.getString("cuil"));
                c.setPhone1(rs.getString("celular1"));
                c.setPhone2(rs.getString("celular2"));
                c.setEmail(rs.getString("email"));
                c.setCant_masc(rs.getInt("cant_masc"));
                clienteList.add(c);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } finally {
            return clienteList;
        }
    }

    @Override
    public boolean isCreate(Object obj) {
        boolean ok = false;
        Cliente p = (Cliente) obj;
        try {
            Statement st = (Statement) getCon().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            String query = "SELECT * FROM Clientes WHERE id=0";

            ResultSet rs = st.executeQuery(query);

            rs.moveToInsertRow();

            rs.updateString("apellido", p.getSurname());
            rs.updateString("nombre", p.getName());
            rs.updateString("domicilio", p.getAddress());
            rs.updateInt("nro_docu", p.getDni());

            if (p.getDate_nac() != null) {
                String patron1 = "yyyy-MM-dd";
                SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(patron1);
                String DateNac = simpleDateFormat1.format(p.getDate_nac());
                rs.updateString("fecha_nac", DateNac);
            } else {
                rs.updateDate("fecha_nac", null);
            }
            
            if (p.getDate_alta() != null) {
                String patron1 = "yyyy-MM-dd";
                SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(patron1);
                String DateAlta = simpleDateFormat1.format(p.getDate_alta());
                rs.updateString("fecha_alta", DateAlta);
            } else {
                rs.updateDate("fecha_alta", null);
            }
            

            rs.updateString("celular1", p.getPhone1());
            rs.updateString("celular2", p.getPhone2());
            rs.updateString("cuil", p.getCuil());
            rs.updateString("email", p.getEmail());
            rs.updateInt("cant_masc", p.getCant_masc());

            rs.insertRow();

            rs.close();
            st.close();

            ok = true;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());;

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } finally {
            return ok;
        }
    }

    @Override
    public boolean isDelete(Object obj) {
        boolean ok = false;
        Cliente p = (Cliente) obj;
        try {
            Statement st = (Statement) getCon().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            String query = "SELECT * FROM Clientes WHERE id=" + p.getId();

            ResultSet rs = st.executeQuery(query);

            if (rs.next()) {
                rs.deleteRow();
                ok = true;
            } else {
                ok = false;
            }

            rs.close();
            st.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } finally {
            return ok;
        }
    }

    @Override
    public boolean isModify(Object obj) {
        boolean ok = false;
        Cliente p = (Cliente) obj;
        try {
            Statement st = (Statement) getCon().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            String query = "SELECT * FROM Clientes WHERE id=" + p.getId();

            ResultSet rs = st.executeQuery(query);

            if (rs.next()) {
                rs.updateString("apellido", p.getSurname());
                rs.updateString("nombre", p.getName());
                rs.updateString("domicilio", p.getAddress());
                rs.updateInt("nro_docu", p.getDni());

                if (p.getDate_nac() != null) {
                    String patron1 = "yyyy-MM-dd";
                    SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(patron1);
                    String DateNac = simpleDateFormat1.format(p.getDate_nac());
                    rs.updateString("fecha_nac", DateNac);
                } else {
                    rs.updateDate("fecha_nac", null);
                }

                if (p.getDate_alta() != null) {
                    String patron1 = "yyyy-MM-dd";
                    SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(patron1);
                    String DateAlta = simpleDateFormat1.format(p.getDate_alta());
                    rs.updateString("fecha_alta", DateAlta);
                } else {
                    rs.updateDate("fecha_alta", null);
                }

                rs.updateString("celular1", p.getPhone1());
                rs.updateString("celular2", p.getPhone2());
                rs.updateString("cuil", p.getCuil());
                rs.updateString("email", p.getEmail());
                rs.updateInt("cant_masc", p.getCant_masc());

                rs.updateRow();
                ok = true;
            } else {
                ok = false;
            }

            rs.close();
            st.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ok = false;

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ok = false;
        } finally {
            return ok;
        }
    }

    @Override
    public boolean isUpdate(String update) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
