/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;

import Entidades.Huesped;
import Entidades.Personal;
import Miselaneos.ConexionMySQL;
import com.mysql.jdbc.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author nejito
 */
public class Personales extends ConexionMySQL {

    @Override
    public ArrayList getList(String query) {
        ArrayList<Personal> personalesList = new ArrayList<Personal>();
        try {
            Statement st = (Statement) getCon().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Personal p = new Personal();

                p.setId(rs.getInt("id"));
                p.setApellido(rs.getString("apellido"));
                p.setNombres(rs.getString("nombres"));
                p.setNro_dni(rs.getString("nro_dni"));
                p.setCuil(rs.getString("cuil"));
                p.setCelular(rs.getString("celular"));
                p.setEmail(rs.getString("email"));
                p.setFecha_alta(rs.getDate("fecha_alta"));
                p.setTipoPersonal(rs.getString("tipo_personal"));
                p.setSexo(rs.getString("sexo"));
                p.setObservaciones(rs.getString("observaciones"));

                personalesList.add(p);
            }

            rs.close();
            st.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } finally {
            return personalesList;
        }

    }

    @Override
    public boolean isIngresar(Object obj) {
        boolean ok = false;
        Personal p = (Personal) obj;
        try {
            Statement st = (Statement) getCon().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            String query = "SELECT * FROM personal WHERE id=0";

            ResultSet rs = st.executeQuery(query);

            rs.moveToInsertRow();

            rs.updateString("apellido", p.getApellido());
            rs.updateString("nombres", p.getNombres());
            rs.updateString("nro_dni", p.getNro_dni());
            rs.updateString("cuil", p.getCuil());
            rs.updateString("celular", p.getCelular());
            rs.updateString("email", p.getEmail());
            if (p.getFecha_alta() != null) {
                String patron2 = "yyyy-MM-dd";
                SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(patron2);
                String mFechaNac = simpleDateFormat2.format(p.getFecha_alta());
                rs.updateString("fecha_alta", mFechaNac);
            } else {
                rs.updateDate("fecha_alta", null);
            }
            rs.updateString("sexo", p.getSexo());
            rs.updateString("tipo_personal", p.getTipoPersonal());
            rs.updateString("observaciones", p.getObservaciones());

            rs.insertRow();
            rs.close();
            st.close();

            ok = true;

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
    public boolean isEliminar(Object obj) {
        boolean ok = false;
        Personal p = (Personal) obj;
        try {
            Statement st = (Statement) getCon().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            String query = "SELECT * FROM personal WHERE id=" + p.getId();

            ResultSet rs = st.executeQuery(query);

            if (rs.next()) {
                rs.deleteRow();
                ok = true;
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
    public boolean isModificar(Object obj) {
        boolean ok = false;
        Personal p = (Personal) obj;
        try {
            Statement st = (Statement) getCon().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            String query = "SELECT * FROM personal WHERE id=" + p.getId();

            ResultSet rs = st.executeQuery(query);

            if (rs.next()) {
                rs.updateString("apellido", p.getApellido());
                rs.updateString("nombres", p.getNombres());
                rs.updateString("nro_dni", p.getNro_dni());
                rs.updateString("cuil", p.getCuil());
                rs.updateString("celular", p.getCelular());
                rs.updateString("email", p.getEmail());
                if (p.getFecha_alta() != null) {
                    String patron2 = "yyyy-MM-dd";
                    SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(patron2);
                    String mFechaNac = simpleDateFormat2.format(p.getFecha_alta());
                    rs.updateString("fecha_alta", mFechaNac);
                } else {
                    rs.updateDate("fecha_alta", null);
                }
                rs.updateString("sexo", p.getSexo());
                rs.updateString("tipo_personal", p.getTipoPersonal());
                rs.updateString("observaciones", p.getObservaciones());
                rs.updateRow();
                ok = true;
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
    public boolean isActualizar(String update) {
        boolean ok = false;
        try {
            Statement st = (Statement) getCon().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            int reg = st.executeUpdate(update); //Cantidad de registros afectados
            ok = reg > 0;
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

}
