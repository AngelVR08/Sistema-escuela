import java.util.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Estudiante> listaEstudiantes = new ArrayList<>();
    private static Map<String, Estudiante> mapaEstudiantes = new HashMap<>();

    public static void main(String[] args) {
        int intentos = 0;
        boolean autenticado = false;
        while (!autenticado && intentos < 3) {
            autenticado = autenticar();
            intentos++;
        }
        if (autenticado) {
            mostrarMenu();
        } else {
            System.out.println("Demasiados intentos fallidos.");
        }
    }

    private static boolean autenticar() {
        System.out.println("Ingrese su nombre de usuario:");
        String usuario = scanner.nextLine();
        if (usuario.equals("admin")) {
            System.out.println("Ingrese su contraseña:");
            String contraseña = scanner.nextLine();
            return contraseña.equals("123456");
        } else {
            System.out.println("Usuario incorrecto.");
            return false;
        }
    }

    private static void mostrarMenu() {
        boolean salir = false;
        while (!salir) {
            System.out.println("\nMenú Principal:");
            System.out.println("1. Agregar estudiante");
            System.out.println("2. Eliminar estudiante");
            System.out.println("3. Buscar estudiante");
            System.out.println("4. Modificar calificaciones de estudiante");
            System.out.println("5. Imprimir lista de estudiantes");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    agregarEstudiante();
                    break;
                case 2:
                    eliminarEstudiante();
                    break;
                case 3:
                    buscarEstudiante();
                    break;
                case 4:
                    modificarCalificaciones();
                    break;
                case 5:
                    imprimirListaEstudiantes();
                    break;
                case 6:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private static void agregarEstudiante() {
        System.out.println("\nIngrese la matrícula del estudiante:");
        String matricula = scanner.nextLine();

        if (matricula.length() != 6) {
            System.out.println("La matrícula ingresada no tiene 6 dígitos.");
            return;
        }

        if (mapaEstudiantes.containsKey(matricula)) {
            System.out.println("El número de matrícula ya existe en la lista.");
            return;
        }

        System.out.println("Ingrese año de ingreso del estudiante:");
        int generacion = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Ingrese apellidos del estudiante:");
        String apellidos = scanner.nextLine();

        System.out.println("Ingrese nombres del estudiante:");
        String nombres = scanner.nextLine();

        Estudiante nuevoEstudiante = new Estudiante(generacion, apellidos, nombres);

        listaEstudiantes.add(nuevoEstudiante);
        mapaEstudiantes.put(matricula, nuevoEstudiante);
        System.out.println("Estudiante agregado exitosamente.");
    }

    private static void eliminarEstudiante() {
        System.out.println("\nIngrese la matrícula del estudiante a eliminar:");
        String matriculaEliminar = scanner.nextLine();

        if (matriculaEliminar.length() != 6) {
            System.out.println("La matrícula ingresada no tiene 6 dígitos.");
            return;
        }

        if (mapaEstudiantes.containsKey(matriculaEliminar)) {
            Estudiante estudianteEliminar = mapaEstudiantes.get(matriculaEliminar);
            listaEstudiantes.remove(estudianteEliminar);
            mapaEstudiantes.remove(matriculaEliminar);
            System.out.println("Estudiante eliminado exitosamente.");
        } else {
            System.out.println("La matrícula ingresada no se encuentra en la lista.");
        }
    }

    private static void buscarEstudiante() {
        System.out.println("\nIngrese la matrícula del estudiante a buscar:");
        String matriculaBuscar = scanner.nextLine();

        if (mapaEstudiantes.containsKey(matriculaBuscar)) {
            Estudiante estudiante = mapaEstudiantes.get(matriculaBuscar);
            estudiante.imprimirInformacion();
        } else {
            System.out.println("La matrícula ingresada no se encuentra en la lista.");
        }
    }

    private static void modificarCalificaciones() {
        System.out.println("\nIngrese la matrícula del estudiante cuyas calificaciones desea modificar:");
        String matriculaModificar = scanner.nextLine();

        if (matriculaModificar.length() != 6) {
            System.out.println("La matrícula ingresada no tiene 6 dígitos.");
            return;
        }

        if (mapaEstudiantes.containsKey(matriculaModificar)) {
            Estudiante estudiante = mapaEstudiantes.get(matriculaModificar);
            System.out.println("Calificaciones actuales del estudiante:");
            estudiante.imprimirInformacion();
            System.out.println("\nIngrese la nueva calificación para cada materia:");
            for (String materia : estudiante.getMaterias().keySet()) {
                System.out.print(materia + ": ");
                int nuevaCalificacion = scanner.nextInt();
                estudiante.setCalificacion(materia, nuevaCalificacion);
            }
            System.out.println("Calificaciones actualizadas exitosamente.");
        } else {
            System.out.println("La matrícula ingresada no se encuentra en la lista.");
        }
    }

    private static void imprimirListaEstudiantes() {
        if (listaEstudiantes.isEmpty()) {
            System.out.println("\nNo hay estudiantes registrados en la lista.");
            return;
        }
    }
}