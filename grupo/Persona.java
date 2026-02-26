package grupo;

public class Persona {
    private String nombre;
    private String numControl;
    private String fecha;
    private double peso, estatura;
    private int cuenta;

    public Persona(){
        this.nombre = "";
        this.numControl = "";
        this.fecha = "";
        this.peso = 0.0;
        this.estatura = 0.0;    
    }

    public Persona(String nombre, String numControl, String fecha, double peso, double estatura){
        this.nombre = nombre;
        this.numControl = numControl;
        this.fecha = fecha;
        this.peso = peso;
        this.estatura = estatura;
    }

    public boolean setNombre(String nombre){
        if(nombre == null || nombre.isEmpty() || nombre.isBlank()){
            return false;
        } else{
            this.nombre = nombre;
            return true;
        }
    }
    public boolean setNControl(String numControl){
        if(numControl == null || numControl.isEmpty() || numControl.isBlank()){
            return false;
        } else{
            this.numControl = numControl;
            return true;
        }
    }
    public boolean setFecha(String fecha){
        if(fecha == null || fecha.isEmpty() || fecha.isBlank()){
            return false;
        } else{
            this.fecha = fecha;
            return true;
        }
    }
    public boolean setPeso(double peso){
        if(peso <= 0){
            return false;
        } else{
            this.peso = peso;
            return true;
        }
    }
    public boolean setEstatura(double estatura){
        if(estatura <= 0){
            return false;
        } else{
            this.estatura = estatura;
            return true;
        }
    }

    public String toString(){
        
        return "Nombre: " + nombre
            +"\nNumero de control: " + numControl
            +"\nFecha de Nacimiento: " + fecha
            +"\nPeso en Kg: " + peso + "kg"
            +"\nEstatura en mts: " + estatura + "m";
    }
    

    public String getNombre(){return nombre;}
    public String getNControl(){return numControl;}
    public String getFechaN() { return fecha; } 
    public void setFechaN(String fecha) { this.fecha = fecha; }
    public double getPeso(){return peso;}
    public double getEstatura(){return estatura;}
}
