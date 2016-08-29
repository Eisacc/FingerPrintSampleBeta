/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import Dao.DaoEmpleado;
import Dao.DaoEmpleadoImplements;
import Model.Empleado;
import comunes.ConexionDb;
import comunes.FingerEnum;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Administrador
 */
public class NewClass {

    public static void main(String[] args) {

        
        System.out.println("Finger Enu "+  FingerEnum.RIGHT_INDEX);
        
        Enum<FingerEnum> enum1 ;
        
        for (FingerEnum e : FingerEnum.values()) {
            System.out.println(""+e.getValue());
        }
        
        /*
        ConexionDb con = ConexionDb.getConnectionDb();
        con.initConnection();
        
        DaoEmpleado daoEmpleados = new DaoEmpleadoImplements();

        Empleado empleado = new Empleado();
        empleado.setCurp(null);
        empleado.setRfc("ROND");
        empleado.setNombre("ROND");
        empleado.setApellidoMaterno("ROND");
        empleado.setApellidoPaterno("PENDEJADAS");
        empleado.setGenero(null);
        empleado.setFechaNacimiento(new Date());
        empleado.setFechaRegistro(null);
        empleado.setFoto(null);
        empleado.setEdad(new Integer("10"));
        Empleado e=daoEmpleados.create(empleado);
        
        System.out.println("Id"+e.getId());
        //d.select();
        
        ConexionDb.destroyConnectionDb();

        /*
         ArregloBynario arregloBynario = new ArregloBynario();
         arregloBynario.setNombre("Najera");
         arregloBynario.setArrayOgBytes(new byte[100]);
         arregloBynario.setDate(new Date(2016, 12, 1));
         arregloBynario.setIn(new InputStream() {

         @Override
         public int read() throws IOException {
         throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         }
         });
         Gson gson = new Gson();
         String json = gson.toJson(arregloBynario);
         System.out.println("JSON:"+json);
        
         ArregloBynario arregloBynario1 = gson.fromJson(json, ArregloBynario.class);     
         System.err.println(arregloBynario1.getDate());
        
         java.util.Date utilDate = new java.util.Date();
         java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
         System.out.println("utilDate:" + utilDate);
         System.out.println("sqlDate:" + sqlDate);
         */
    }
}
