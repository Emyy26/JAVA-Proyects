/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;

import Entidades.Habitacion;
import Miselaneos.ConexionMySQL;
import com.mysql.jdbc.Blob;
import com.mysql.jdbc.Statement;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author nejo
 */
public class Habitaciones extends ConexionMySQL {

    @Override
    public ArrayList getList(String query) {
        ArrayList<Habitacion> habitacionesList = new ArrayList<Habitacion>();
        try {
            Statement st = (Statement) getCon().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Habitacion h = new Habitacion();
                h.setId(rs.getInt("id"));
                h.setTitulo(rs.getString("titulo"));
                h.setDescripcion(rs.getString("descripcion"));
                h.setCapacidad_pers(rs.getInt("capacidad_pers"));
                h.setTarifa(rs.getString("tarifa"));
                h.setTv(rs.getString("tv"));
                h.setWifi(rs.getString("wifi"));
                h.setAireAcondicionado(rs.getString("aireAcondicionado"));

                String rutaArchivo = this.PATH_TMP
                        + h.getId() + ".png";
                File fDel = new File(rutaArchivo);
                if (fDel.exists()) {
                    fDel.delete();
                }

                Blob imgBinary = (Blob) rs.getBlob("img");
                if (imgBinary != null) {
                    byte[] datosImg = imgBinary.getBytes(1, (int) imgBinary.length());

                    File file = new File(rutaArchivo);
                    OutputStream out = new FileOutputStream(file);
                    out.write(datosImg);
                    out.flush();
                    out.close();

                    h.setImg(new ImageIcon(file.getPath()));

                } else {
                    h.setImg(null);
                }

                habitacionesList.add(h);
            }

            rs.close();
            st.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } finally {
            return habitacionesList;
        }

    }

    @Override
    public boolean isIngresar(Object obj) {
        boolean ok = false;
        Habitacion h = (Habitacion) obj;
        try {
            Statement st = (Statement) getCon().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            String query = "SELECT * FROM habitaciones WHERE id=0";

            ResultSet rs = st.executeQuery(query);

            rs.moveToInsertRow();

            rs.updateString("titulo", h.getTitulo());
            rs.updateString("descripcion", h.getDescripcion());
            rs.updateInt("capacidad_pers", h.getCapacidad_pers());
            rs.updateString("tarifa", h.getTarifa());
            rs.updateString("tv", h.getTv());
            rs.updateString("wifi", h.getWifi());
            rs.updateString("aireAcondicionado", h.getAireAcondicionado());

            if (h.getImg() != null) {
                String pathFile = h.getImg().getDescription().trim();
                File f = new File(pathFile);
                FileInputStream fis = null;
                if (f.exists()) {
                    fis = new FileInputStream(f);
                    rs.updateBinaryStream("img", fis, (int) f.length());
                }
            } else {
                rs.updateBinaryStream("img", null, 0);
            }
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
        Habitacion h = (Habitacion) obj;
        try {
            Statement st = (Statement) getCon().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            String query = "SELECT * FROM habitaciones WHERE id=" + h.getId();

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
        Habitacion h = (Habitacion) obj;
        try {
            Statement st = (Statement) getCon().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            String query = "SELECT * FROM habitaciones WHERE id=" + h.getId();

            ResultSet rs = st.executeQuery(query);

            if (rs.next()) {
                rs.updateString("titulo", h.getTitulo());
                rs.updateString("descripcion", h.getDescripcion());
                rs.updateInt("capacidad_pers", h.getCapacidad_pers());
                rs.updateString("tarifa", h.getTarifa());
                rs.updateString("tv", h.getTv());
                rs.updateString("wifi", h.getWifi());
                rs.updateString("aireAcondicionado", h.getAireAcondicionado());

                if (h.getImg() != null) {
                    String pathFile = h.getImg().getDescription().trim();
                    File f = new File(pathFile);
                    FileInputStream fis = null;
                    if (f.exists()) {
                        fis = new FileInputStream(f);
                        rs.updateBinaryStream("img", fis, (int) f.length());
                    }
                } else {
                    rs.updateBinaryStream("img", null, 0);
                }
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
