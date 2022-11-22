/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;

import Entidades.Huesped;
import Miselaneos.ConexionMySQL;
import com.mysql.jdbc.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author nejo
 */
public class Huespedes extends ConexionMySQL {
   @Override
    public ArrayList getList(String query) {
        ArrayList<Huesped> huespedesList = new ArrayList<Huesped>();
        try {
            Statement st = (Statement) getCon().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Huesped h = new Huesped();

                h.setId(rs.getInt("id"));
                h.setApellido(rs.getString("apellido"));
                h.setNombres(rs.getString("nombres"));
                h.setProvincia(rs.getString("provincia"));
                h.setCiudad(rs.getString("ciudad"));
                h.setCelular(rs.getString("celular"));
                h.setEmail(rs.getString("email"));
                h.setNro_dni(rs.getString("nro_dni"));
                h.setCuil(rs.getString("cuil"));
                h.setFecha_nacimiento(rs.getDate("fecha_nacimiento"));
                h.setFecha_alta(rs.getDate("fecha_alta"));
                h.setObservaciones(rs.getString("observaciones"));

                huespedesList.add(h);
            }

            rs.close();
            st.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } finally {
            return huespedesList;
        }

    }

    @Override
    public boolean isIngresar(Object obj) {
        boolean ok = false;
        Huesped h = (Huesped) obj;
        try {
            Statement st = (Statement) getCon().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            String query = "SELECT * FROM huespedes WHERE id=0";

            ResultSet rs = st.executeQuery(query);

            rs.moveToInsertRow();

            rs.updateString("apellido", h.getApellido());
            rs.updateString("nombres", h.getNombres());
            rs.updateString("provincia", h.getProvincia());
            rs.updateString("ciudad", h.getCiudad());
            rs.updateString("celular", h.getCelular());
            rs.updateString("email", h.getEmail());
            rs.updateString("nro_dni", h.getNro_dni());
            rs.updateString("cuil", h.getCuil());
            if (h.getFecha_nacimiento() != null) {
                String patron1 = "yyyy-MM-dd";
                SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(patron1);
                String mFechaNac = simpleDateFormat1.format(h.getFecha_nacimiento());
                rs.updateString("fecha_nacimiento", mFechaNac);
            } else {
                rs.updateDate("fecha_nacimiento", null);
            }

            if (h.getFecha_alta() != null) {
                String patron2 = "yyyy-MM-dd";
                SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(patron2);
                String mFechaNac = simpleDateFormat2.format(h.getFecha_alta());
                rs.updateString("fecha_alta", mFechaNac);
            } else {
                rs.updateDate("fecha_alta", null);
            }
            rs.updateString("observaciones", h.getObservaciones());

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
        Huesped h = (Huesped) obj;
        try {
            Statement st = (Statement) getCon().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            String query = "SELECT * FROM huespedes WHERE id=" + h.getId();

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
        Huesped h = (Huesped) obj;
        try {
            Statement st = (Statement) getCon().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            String query = "SELECT * FROM huespedes WHERE id=" + h.getId();

            ResultSet rs = st.executeQuery(query);

            if (rs.next()) {
                rs.updateString("apellido", h.getApellido());
                rs.updateString("nombres", h.getNombres());
                rs.updateString("provincia", h.getProvincia());
                rs.updateString("ciudad", h.getCiudad());
                rs.updateString("celular", h.getCelular());
                rs.updateString("email", h.getEmail());
                rs.updateString("nro_dni", h.getNro_dni());
                rs.updateString("cuil", h.getCuil());
                if (h.getFecha_nacimiento() != null) {
                    String patron1 = "yyyy-MM-dd";
                    SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(patron1);
                    String mFechaNac = simpleDateFormat1.format(h.getFecha_nacimiento());
                    rs.updateString("fecha_nacimiento", mFechaNac);
                } else {
                    rs.updateDate("fecha_nacimiento", null);
                }

                if (h.getFecha_alta() != null) {
                    String patron2 = "yyyy-MM-dd";
                    SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(patron2);
                    String mFechaNac = simpleDateFormat2.format(h.getFecha_alta());
                    rs.updateString("fecha_alta", mFechaNac);
                } else {
                    rs.updateDate("fecha_alta", null);
                }
                rs.updateString("observaciones", h.getObservaciones());
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