import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class AccesoBDO {
    //Indicamos base de datos, usuario y contraseña
    private static final String URL = "jdbc:mysql://localhost:3306/vuelos";
    private static final String USUARIO = "root";
    private static String CONTRASENA;
    
    static {
    //Cargar la contraseña desde el archivo configuration.properties
    Properties prop = new Properties();
    try (FileInputStream input = new FileInputStream("C:/Users/alvar/OneDrive/Escritorio/Traballos FP/AccesoBDO/src/configuration.properties")) {
            prop.load(input);
            CONTRASENA = prop.getProperty("Contraseña");
            System.out.println("Intentando cargar desde: " + new File("configuration.properties").getAbsolutePath());
            System.out.println("Contraseña cargada: " + CONTRASENA);

     } catch (IOException ex) {
            ex.printStackTrace();
            System.err.println("No se pudo cargar la contraseña. Asegúrate de que configuration.properties existe y contiene la contraseña.");
        }
    }

    public static void main(String[] args) {
        try {
           //Establecemos conexión con la base de datos y con el método getConnection
            Connection conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
            Scanner scanner = new Scanner(System.in);
            
            // Declaración de variables 
            Statement statement;
            ResultSet resultSet;
            PreparedStatement preparedStatement;
            String codVuelo, horaSalida, destino, procedencia, codVueloAnt = null;
            int opcion, plazasFumador, plazasNofumador, plazasPrimera, plazasTurista, filas;
          
            //Menú de selección
            do {
                System.out.println("1. Mostrar información de la base de datos");
                System.out.println("2. Mostrar información de la tabla pasajeros");
                System.out.println("3. Ver información de los pasajeros de un vuelo");
                System.out.println("4. Insertar un vuelo");
                System.out.println("5. Borrar un vuelo");
                System.out.println("6. Modificar vuelos de fumadores a no fumadores");
                System.out.println("0. Salir");

                System.out.print("Selecciona una opción: ");
                opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        //Creamos el objeto statement
                        statement = conexion.createStatement();
                        //Ejecutamos la consulta
                        resultSet = statement.executeQuery("SELECT * FROM pasajeros");
                        ResultSetMetaData rsmd = resultSet.getMetaData();
                        int columnas = rsmd.getColumnCount();
                        rsmd = resultSet.getMetaData();
                        
                        for (int i = 1; i <= columnas; i++) {
                            System.out.println("Información acerca de la columna " + rsmd.getColumnName(i));
                            System.out.println("Tipo de la columna: " + rsmd.getColumnTypeName(i));
                            System.out.println("Precisión: " + rsmd.getPrecision(i));
                        }
                        resultSet = statement.executeQuery("SELECT * FROM vuelos"); 
                     
                        for (int i = 1; i <= columnas; i++) {
                            System.out.println("Información acerca de la columna " + rsmd.getColumnName(i));
                            System.out.println("Tipo de la columna: " + rsmd.getColumnTypeName(i));
                            System.out.println("Precisión: " + rsmd.getPrecision(i));
                        }
                        //Cerrar ResultSet
                        resultSet.close();
                       //Cerrar statement
                        statement.close();
                        
                        break;
                        
                    case 2:
                        statement = conexion.createStatement();
                        resultSet = statement.executeQuery("SELECT * FROM pasajeros");
                        System.out.println("NUM\tCOD_VUELO\tTIPO_PLAZA\tFUMADOR\t");
                        
                        while(resultSet.next()) { 
                        System.out.print(resultSet.getInt("NUM") + "\t");
                        System.out.print(resultSet.getString("COD_VUELO") + "\t");
                        System.out.print(resultSet.getString("TIPO_PLAZA") + "\t\t");
                        System.out.print(resultSet.getString("FUMADOR") + "\t");
                        System.out.println("");
                        }
                        resultSet.close();
                        statement.close();
                      
                        break;
                    case 3:
                        preparedStatement = conexion.prepareStatement("SELECT * FROM pasajeros WHERE COD_VUELO = ?");
                        System.out.println("Inserta el código de vuelo");
                            
                        codVuelo = scanner.next();
                        
                        preparedStatement.setString(1, codVuelo);
                        resultSet = preparedStatement.executeQuery();
                        System.out.println("NUM\tCOD_VUELO\tTIPO_PLAZA\tFUMADOR\t");
                        
                        while(resultSet.next()) { 
                        System.out.print(resultSet.getInt("NUM") + "\t");
                        System.out.print(resultSet.getString("COD_VUELO") + "\t");
                        System.out.print(resultSet.getString("TIPO_PLAZA") + "\t\t");
                        System.out.print(resultSet.getString("FUMADOR") + "\t");
                        System.out.println("");
                        }
                        resultSet.close();
                        preparedStatement.close();
                        
                        break;
                    case 4:
                        preparedStatement = conexion.prepareStatement("INSERT INTO VUELOS values(?,?,?,?,?,?,?,?)");
                        
                        System.out.println("Inserta el código de vuelo");
                        codVuelo = scanner.next();
                        
                        System.out.println("Inserta la hora de salida");
                        horaSalida = scanner.next();
                        
                        System.out.println("Inserta el destino");
                        destino = scanner.next();
                        
                        System.out.println("Inserta la procedencia");
                        procedencia = scanner.next();
                        
                        System.out.println("Inserta las plazas de fumador");
                        plazasFumador = scanner.nextInt();
                        
                        System.out.println("Inserta las plazas de no fumador");
                        plazasNofumador = scanner.nextInt();
                                
                        System.out.println("Inserta las plazas de turista");
                        plazasTurista = scanner.nextInt();
                        
                        System.out.println("Inserta las plazas de primera");
                        plazasPrimera = scanner.nextInt();
                        
                        preparedStatement.setString(1,codVuelo);
                        preparedStatement.setString(2, horaSalida);
                        preparedStatement.setString(3, destino);
                        preparedStatement.setString(4, procedencia);
                        preparedStatement.setInt(5, plazasFumador);
                        preparedStatement.setInt(6, plazasNofumador);
                        preparedStatement.setInt(7, plazasTurista);
                        preparedStatement.setInt(8, plazasPrimera); 
                        
                        filas = preparedStatement.executeUpdate();
                        if(filas > 0) {
                        System.out.println("Se ha añadido el vuelo");
                        codVueloAnt = codVuelo;
                        }
                        preparedStatement.close();
                        break;
                    case 5:
                        if (codVueloAnt != null) {
                        preparedStatement = conexion.prepareStatement("DELETE FROM vuelos WHERE COD_VUELO = ?");    
                    
                        preparedStatement.setString(1, codVueloAnt);
                        filas = preparedStatement.executeUpdate();
                        if(filas > 0){ 
                        System.out.println("Se ha borrado el vuelo");
                         }
                        preparedStatement.close();
                        } else { 
                        System.out.println("Debes insertar un vuelo antes");
                        }
                        break;
                    case 6:
                        statement = conexion.createStatement();
                        filas = statement.executeUpdate("UPDATE vuelos SET PLAZAS_NO_FUMADOR = PLAZAS_NO_FUMADOR + PLAZAS_FUMADOR, PLAZAS_FUMADOR = 0");
                        if(filas > 0){ 
                        System.out.println("Se han modificado los vuelos de fumadores a no fumadores");
                        statement.close();
                        }
                        break;
                    case 0:
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("Opción no válida. Intenta de nuevo.");
                        break;
                }

            } while (opcion != 0);

            // Cerrar conexión
            conexion.close();
            scanner.close();

        } catch (SQLException e) {
        }
    }
}
