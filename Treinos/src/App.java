import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Predicate;

public class App {
    public static void main(String[] args) {
        // Força a saída em UTF-8 pra os acentos aparecerem certo no terminal
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out), true, StandardCharsets.UTF_8));

        Turma turmaJava = new Turma("TURMA-01", "Java maluco");

        carregarAlunos("src/alunos.txt", turmaJava);

        System.out.println(turmaJava);

                    listarNomesDaTurma(turmaJava);
        listarGpasDaTurma(turmaJava);
        mostrarMedias(turmaJava);
       listarFrequenciaAcimaDe(turmaJava, 0.75);
       listarFrequenciaAbaixoDe(turmaJava, 0.67);
       listarGpaAcimaDe(turmaJava, 9.0);
       listarAprovados(turmaJava);
       listarReprovados(turmaJava);
    }
    // ---------- Function: extratores ----------
    public static void listarNomesDaTurma(Turma turma) {
        Function<Aluno, String> nome = aluno -> aluno.getNome();

        System.out.println("\nNomes: " + turma.listarNomes(nome));
    }

    public static void listarGpasDaTurma(Turma turma) {
        Function<Aluno, Double> gpa = aluno -> calcularGpa(aluno);

        System.out.println("\nGPAs: " + turma.listarGpas(gpa));
    }

    public static void mostrarMedias(Turma turma) {
        Function<Aluno, Double> gpa = aluno -> calcularGpa(aluno);
        Function<Aluno, Double> frequencia = aluno -> aluno.getFrequencia();

        System.out.println(String.format("\nGPA médio da turma: %.2f", turma.calcularMedia(gpa)));
        System.out.println(String.format("Frequência média da turma: %.2f", turma.calcularMedia(frequencia)));
    }

    // ---------- Predicate: filtros ----------
    // O turma.filtrar testa cada aluno num if e, se der true,
    // coloca ele na lista que volta pra cá.
    public static void listarFrequenciaAcimaDe(Turma turma, double minimo){
        Predicate<Aluno> frequenciaMinima = aluno -> aluno.getFrequencia() >= minimo;

        System.out.println("Frequencia > = " + minimo + ":");
        System.out.println(turma.filtrar(frequenciaMinima));
    }

    public static void listarFrequenciaAbaixoDe(Turma turma, double maximo){
        Predicate<Aluno> frequenciaMinima = aluno -> aluno.getFrequencia() <= maximo;

        System.out.println("Frequencia > = " + maximo + ":");
        System.out.println(turma.filtrar(frequenciaMinima));
    }

        public static void listarGpaAcimaDe(Turma turma, double minimo) {
        Predicate<Aluno> gpaMinimo = aluno -> calcularGpa(aluno) >= minimo;

        System.out.println("\nGPA >= " + minimo + ":");
        System.out.println(turma.filtrar(gpaMinimo));
    }

    public static void listarAprovados(Turma turma) {
        Predicate<Aluno> aprovado = aluno -> aluno.getFrequencia() >= 0.75 && calcularGpa(aluno) >= 7.0;

        System.out.println("\nAprovados (freq >= 75% e GPA >= 7):");
        System.out.println(turma.filtrar(aprovado));
    }

    public static void listarReprovados(Turma turma) {
        Predicate<Aluno> reprovado = aluno -> aluno.getFrequencia() < 0.75 || calcularGpa(aluno) < 7.0;

        System.out.println("\nReprovados:");
        System.out.println(turma.filtrar(reprovado));
    }

    // ---------- Auxiliares ----------

    // GPA = média das notas (o extrator é a própria nota)
    public static double calcularGpa(Aluno aluno) {
        return aluno.getNotas().calcularMedia(nota -> nota);
    }

    // Lê o arquivo linha a linha e adiciona cada aluno na turma.
    // Formato: primeira linha = quantidade de alunos; demais =
    // matricula;nome;n1,n2,n3;frequencia
    public static void carregarAlunos(String caminho, Turma turma) {
        try (Scanner leitor = new Scanner(new File(caminho), "UTF-8")) {

            int quantidade = Integer.parseInt(leitor.nextLine().trim());

            for (int i = 0; i < quantidade && leitor.hasNextLine(); i++) {
                String linha = leitor.nextLine().trim();
                if (linha.isEmpty())
                    continue;

                Aluno aluno = Aluno.criarDoTexto(linha);
                turma.adicionarAluno(aluno);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + new File(caminho).getAbsolutePath());
        }
    }
}
