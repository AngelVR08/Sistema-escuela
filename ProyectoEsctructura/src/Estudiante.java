import java.util.*;

public class Estudiante {
    private int generacion;
    private String matricula;
    private String apellidos;
    private String nombres;
    private Map<String, Integer> materias;

    public Estudiante(int anoingreso, String apellidos, String nombres) {
        this.generacion = anoingreso;
        this.matricula = generarMatricula(anoingreso);
        this.apellidos = apellidos;
        this.nombres = nombres;
        this.materias = new HashMap<>();
        materias.put("Inglés", 0);
        materias.put("POO", 0);
        materias.put("Estructura de datos", 0);
        materias.put("Cálculo diferencial", 0);
    }

    private String generarMatricula(int añoIngreso) {
        Random rand = new Random();
        int randomNum = rand.nextInt(10000);
        return String.format("%02d%04d", añoIngreso % 100, randomNum);
    }

    public String getMatricula() {
        return matricula;
    }

    public void imprimirInformacion() {
        System.out.println("Matrícula: " + matricula);
        System.out.println("Nombre completo: " + nombres + " " + apellidos);
        System.out.println("Promedio general: " + calcularPromedioGeneral());
    }

    public double calcularPromedioGeneral() {
        double suma = 0;
        for (int calificacion : materias.values()) {
            suma += calificacion;
        }
        return suma / materias.size();
    }

    public void setCalificacion(String materia, int calificacion) {
        materias.put(materia, calificacion);
    }

    public Map<String, Integer> getMaterias() {
        return materias;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Estudiante estudiante = (Estudiante) obj;
        return Objects.equals(matricula, estudiante.matricula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricula);
    }
}