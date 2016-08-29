/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comunes;

import Constantes.ServerDbConstantes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vidal
 */
public final class ConexionDb {

    /**
     * conexionDb
     */
    private static ConexionDb conexion = null;
    /**
     * con
     */
    private static Connection con;
    /**
     * usuario
     */
    private String usuario;

    /**
     * password
     */
    private String password;

    /**
     * host
     */
    private String host;

    /**
     * db
     */
    private String db;

    /**
     * port
     */
    private String port;

    /**
     * jdbc
     */
    private String jdbc;

    /**
     * jdbcClass
     */
    private String jdbcClass;

    /**
     * fileConection
     */
    private String fileConection;

    /**
     * ConexionDb Constructor privado
     */
    private ConexionDb() {
        fileConection = ServerDbConstantes.FILE_PROPERTIES_SERVER;
    }

    /**
     *
     * @return conexion tipo ConexionDb
     */
    public static ConexionDb getConnectionDb() {
        if (conexion == null) {
            try {
                conexion = new ConexionDb();
                conexion.loadPropertiesConnectionDefault();
            } catch (Exception ex) {

            }

        }
        return conexion;
    }

    /**
     * destroyConnectionDb
     */
    public static void destroyConnectionDb() {
        closeConnection();
        conexion = null;
    }

    /**
     *
     * @throws java.lang.Exception
     */
    public void loadPropertiesConnectionDefault() throws Exception {
        LoadProperties loadProperties = new LoadProperties(fileConection);

        try {
            loadProperties.loadFileProperties();
            Map<String, String> datosConnection = loadProperties.getPropertiesValues();
            usuario = datosConnection.get(ServerDbConstantes.USER_SERVER);
            password = datosConnection.get(ServerDbConstantes.PASSWORD_SERVER);
            host = datosConnection.get(ServerDbConstantes.HOST_SERVER);
            port = datosConnection.get(ServerDbConstantes.PORT_SERVER);
            db = datosConnection.get(ServerDbConstantes.DB_SERVER);
            jdbc = datosConnection.get(ServerDbConstantes.JDBC_SERVER);
            jdbcClass = datosConnection.get(ServerDbConstantes.JDBC_CLASS_SERVER);

            System.out.println(generateStringConnection());

        } catch (Exception e) {
            throw e;
        }

    }

    /**
     * initConnection
     */
    public void initConnection() {
        try {
            Class.forName(jdbcClass);
            con = DriverManager.getConnection(generateStringConnection(), usuario, password);
            System.out.println("Conectado Correctamente");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConexionDb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * closeConnection
     */
    public static void closeConnection() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
                System.out.println("Se ha cerrado correctamente");
            }
        } catch (SQLException ex) {
            System.out.println("Ha ocurrido un error");
        }
    }

    /**
     * generateStringConnection
     *
     * @retu rn
     */
    private String generateStringConnection() {

        String stringConnection = "jdbc:{JDBC}://{HOST}:{PORT}/{DB}";

        stringConnection = stringConnection.replace("{JDBC}", jdbc);
        stringConnection = stringConnection.replace("{HOST}", host);
        stringConnection = stringConnection.replace("{PORT}", port);
        stringConnection = stringConnection.replace("{DB}", db);

        return stringConnection;
    }

    /**
     * @return the con
     */
    public Connection getConexion() {
        return con;
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(final String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(final String password) {
        this.password = password;
    }

    /**
     * @return the host
     */
    public String getHost() {
        return host;
    }

    /**
     * @param host the host to set
     */
    public void setHost(final String host) {
        this.host = host;
    }

    /**
     * @return the db
     */
    public String getDb() {
        return db;
    }

    /**
     * @param db the db to set
     */
    public void setDb(final String db) {
        this.db = db;
    }

    /**
     * @return the port
     */
    public String getPort() {
        return port;
    }

    /**
     * @param port the port to set
     */
    public void setPort(final String port) {
        this.port = port;
    }

    /**
     * @param fileConection the fileConection to set
     */
    public void setFileConection(final String fileConection) {
        this.fileConection = fileConection;
    }

}
