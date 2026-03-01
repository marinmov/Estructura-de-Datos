package grupo;
/*
Tecnologico Nacional de Mexico
Instituto Tecnologico de Leon, Campus 1
Carrera: Ingenieria en Sistemas Computacionales
Materia: Estructura de Datos (Grupo B)
Horario: 
    Lunes y Miercoles de 8:45 a 10:25
    Viernes de 8:45 a 9:35
Alumno: Marlene Ines Moreno Velazquez
Tarea #3
Fecha: 25 de febrero de 2026

----- ALGORITMO SECUENCIAL -----
1. Meta: Mediante el programa se capturaran los datos de 25 personas (o menos)
        registrando su nombre, numero de control, fecha de nacimiento, peso y estatura.
        Posteriormente se buscara de manera secuencial una persona mediante su nombre.

2. Datos: 
    - Nombre: String nombre
    - Numero de control: String numControl
    - Fecha de nacimiento: DayArslan fecha
    - Peso: double peso
    - Estatura: double estatura
    Herramienta para la introduccion de datos y la salida de prompt: JOptionPane

3. Formulas:
    - grupo[i].getNombre().equalsIgnoreCase(getNombreb()) 
        Ingresar a la posicion i del arreglo "grupo" y compararlo con getNombreb() que es
        el nombre a buscar con equalsIgnoreCase() que no hace distincion por mayusculas o minusculas.
        Esto al ser verdad, devuelve un valor booleano "true" e imprime el mensaje de exito, si nunca
        es verdad, devuelve un valor booleano "false" e imprime un mensaje de "Fracaso" o error.
    - DayArslan.isValidDay(int dd, int mm, int yyyy) : boolean
        Para validar la fecha y darle un formato ordenado se utiliza la clase DayArslan, creada por
        una servidora, donde devuelve un valor booleano si la fecha es valida tomando en cuenta todos los
        aspectos como el numero de dias de cada mes, y si el ano es bisiesto. Si es valido devuelve "true",
        de lo contrario devuelve "false". 

4. Resultados:
    - En caso de imprimir solo a la persona buscada: 
        JOptionPane.showMessageDialog(null, grupo[getPos()].toString(), "ENCONTRADO", 
                                    JOptionPane.INFORMATION_MESSAGE);
    - En caso de imprimir toda la lista registrada:
        JOptionPane.showMessageDialog(null, resultado, "LISTA DE TODAS LAS PERSONAS REGISTRADAS", 
                                    JOptionPane.INFORMATION_MESSAGE);

5. Navegabilidad: 
    String opciones[] = {"Continuar", "Terminar"};
    int stop = JOptionPane.showOptionDialog(null, 
                "Selecciona una opcion", "REGISTRO DE DATOS", 
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, 
                null, opciones, opciones[0]);
    while (cuenta < grupo.length && getStop()!=1)
    
 */
import java.awt.HeadlessException;
import java.io.*;
import javax.swing.JOptionPane;

public class Grupo {
    int cuenta = 0; int stop = 0; int posicion;
    String nombreb;
    boolean encontrado;
    int opcion;
    Persona grupo[] = new Persona[25];

    void meta(){
        String mensaje = """
                Registro de personas mediante teclado y mediante lectura de archivo.
                Usted puede registrar a maximo 25 personas, buscar a una persona en
                especifico mediante su nombre e imprimir sus datos o la lista completa.
                """;
        JOptionPane.showMessageDialog(null, mensaje, "ALGORITMO SECUENCIAL", JOptionPane.PLAIN_MESSAGE);
    }

    //2. DATA -> ENTRADA (registro de datos por teclado)
    void entrada(){
        String opciones[] = {"Continuar", "Terminar"};
        setStop(0); boolean correct;

        while(cuenta<grupo.length && getStop()!=1){
            grupo[cuenta] = new Persona();
            String nombre, numControl;

            double estatura, peso;
            correct = false;
            while(!correct){
                nombre = JOptionPane.showInputDialog("Deme el nombre de la persona " + (cuenta+1));
                correct = grupo[cuenta].setNombre(nombre);
                if(!correct){
                    JOptionPane.showMessageDialog(null, "Campo OBLIGATORIO!",
                            "ATENCION", JOptionPane.WARNING_MESSAGE);
                }
            }
            correct = false;
            while(!correct){
                numControl = JOptionPane.showInputDialog("Deme el numero de control de la persona " + (cuenta+1));
                correct = grupo[cuenta].setNControl(numControl);
                if(!correct){
                    JOptionPane.showMessageDialog(null, "Campo OBLIGATORIO!",
                            "ATENCION", JOptionPane.WARNING_MESSAGE);
                }
            }
            correct = false;
            while(!correct){
                try {
                    int dia = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el dia de nacimiento",
                            "FECHA DE NACIMIENTO", JOptionPane.INFORMATION_MESSAGE));
                    int mes = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el mes de nacimiento numerico",
                            "FECHA DE NACIMIENTO", JOptionPane.INFORMATION_MESSAGE));
                    int year = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el anio de nacimiento",
                            "FECHA DE NACIMIENTO", JOptionPane.INFORMATION_MESSAGE));
                    correct = grupo[cuenta].setFecha(dia, mes, year);
                    if(!correct) {
                        JOptionPane.showMessageDialog(null, "Asegurese de introducir correctamente la fecha con numeros.",
                                "FECHA INCORRECTA", JOptionPane.WARNING_MESSAGE);}
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Asegurese de introducir correctamente la fecha.\nUtilice UNICAMENTE numeros.",
                            "FECHA INVALIDA", JOptionPane.WARNING_MESSAGE);
                    correct = false;
                }
            }
            correct = false;
            while(!correct){
                try {
                    estatura = Double.parseDouble(JOptionPane.showInputDialog("Deme la estatura de la persona "
                            + (cuenta+1)));
                    correct = grupo[cuenta].setEstatura(estatura);
                } catch (NumberFormatException e) {
                    correct = false;
                    JOptionPane.showMessageDialog(null, "Campo OBLIGATORIO!",
                            "ATENCION", JOptionPane.WARNING_MESSAGE);
                }
            }
            correct = false;
            while(!correct){
                try {
                    peso = Double.parseDouble(JOptionPane.showInputDialog("Deme el peso de la persona " + (cuenta+1)));
                    correct = grupo[cuenta].setPeso(peso);
                } catch (HeadlessException | NumberFormatException e) {
                    correct = false;
                    JOptionPane.showMessageDialog(null, "Campo OBLIGATORIO!",
                            "ATENCION", JOptionPane.WARNING_MESSAGE);
                }
            }
            cuenta++;
            stop = JOptionPane.showOptionDialog(null,
                    "Selecciona una opcion", "REGISTRO DE DATOS",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, opciones, opciones[0]);
        }
        setCuenta(cuenta);
    }

    //2. DATA -> ENTRADA (registro de datos por lectura de archivo)
    void archivo(String archivoNombre){
        try (BufferedReader br = new BufferedReader(new FileReader(archivoNombre))) {
            String line;
            JOptionPane.showMessageDialog(null, "Leyendo de archivo...");
            while ((line = br.readLine()) != null && getCuenta()<grupo.length){
                String[] datos = line.split(",");
                if (datos.length == 5){
                    String nombre = datos[0].trim();
                    String numControl = datos[1].trim();
                    String fecha = datos[2].trim();
                    double peso = Double.parseDouble(datos[3].trim());
                    double estatura = Double.parseDouble(datos[4].trim());
                    grupo[cuenta] = new Persona(nombre, numControl, fecha, peso, estatura);
                    cuenta++;
                }
                else {
                    JOptionPane.showMessageDialog(null, "Formato incorrecto en linea: "
                            + line, "ADVERTENCIA", JOptionPane.ERROR_MESSAGE);
                }
            }
            setCuenta(cuenta);
            JOptionPane.showMessageDialog(null, "Se han leido " + cuenta
                    + " personas desde el archivo.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al leer el archivo: " + e.getMessage(),
                    "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    //3. FORMULAS -> BUSCAR (mediante operador logico encontrar a la persona con su nombre)
    void buscar(){
        int i = 0; encontrado = false;
        nombreb = JOptionPane.showInputDialog("Deme el nombre de la persona a buscar");
        if(nombreb == null) return;
        setNombreB(nombreb);
        while(i<cuenta && !encontrado){
            if(grupo[i].getNombre().equalsIgnoreCase(getNombreb())){
                JOptionPane.showMessageDialog(null, "Persona encontrada.\nNumero de preguntas realizadas: "
                        + (i+1), "MENSAJE DE TERMINAL", JOptionPane.INFORMATION_MESSAGE);
                encontrado = true;
            }
            else{
                i++;
            }
        }
        setPos(i); setEncontrado(encontrado);
        //4. RESULTADO -> Impresion (imprimir los datos de la persona buscada y numero de intentos realizados)
        if(getEncontrado()){
            JOptionPane.showMessageDialog(null, grupo[getPos()].toString(), "ENCONTRADO",
                    JOptionPane.INFORMATION_MESSAGE);
        } else if(!getEncontrado()){
            JOptionPane.showMessageDialog(null, "No fue encontrado en el grupo.",
                    "ENCONTRADO", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    //4. RESULTADOS -> Impresion (imprimir la lista completa)
    void imprimir(){
        if(getCuenta() == 0){
            JOptionPane.showMessageDialog(null, "No hay personas registradas aún.");
            return;
        }
        int i = 0; String resultado ="";
        while(i<getCuenta()){
            resultado += "Persona " + (i+1)
                    + "\n" + grupo[i].toString() + "\n";
            i++;
        }
        JOptionPane.showMessageDialog(null, resultado,
                "LISTA DE TODAS LAS PERSONAS REGISTRADAS", JOptionPane.INFORMATION_MESSAGE);

    }
    //5. NAVEGABILIDAD -> Menu (solicitar al usuario seleccionar la accion deseada)
    int menu(){
        String mensajeOpciones = ("""
                Usted puede:
                1. Introducir datos de personas manualmente por teclado
                2. Leer datos de un archivo de texto
                3. Buscar alguna persona por nombre
                4. Imprimir toda la lista
                5. Salir del programa

                Eliga la opcion que desee...
                """);
        String opciones[] = {"Introducir datos", "Leer datos de archivo", "Buscar persona",
                "Imprimir lista", "SALIR"};
        int i = 0; encontrado = false;
        setOpcion(JOptionPane.showOptionDialog(null,
                mensajeOpciones, "OPCIONES",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, opciones, opciones[0]));
        return getOpcion();
    }

    public static void main(String[] args) {
        Grupo mn = new Grupo(); mn.setOpcion(0);
        mn.meta();
        int sw = mn.menu();
        while(sw != -1 && sw != 4){
            switch (sw) {
                case 0 -> mn.entrada();
                case 1 -> mn.archivo("C:\\Users\\marle\\Documents\\Estructura de Datos\\grupo\\Personas.txt");
                case 2 -> mn.buscar();
                case 3 -> mn.imprimir();
            }
            sw = mn.menu();
        }
    }

    //SETTERS Y GETTERS para datos y centinelas usados en los procesos
    void setCuenta(int cuenta){this.cuenta=cuenta;}
    int getCuenta(){return cuenta;}
    void setPos(int posicion){this.posicion = posicion;}
    int getPos(){return posicion;}
    void setNombreB(String nombreb){this.nombreb = nombreb;}
    String getNombreb(){return nombreb;}
    void setEncontrado(boolean encontrado){this.encontrado = encontrado;}
    boolean getEncontrado(){return encontrado;}
    void setOpcion(int opcion){this.opcion = opcion;}
    int getOpcion(){return opcion;}
    void setStop(int stop){this.stop = stop;}
    int getStop(){return stop;}
} 
