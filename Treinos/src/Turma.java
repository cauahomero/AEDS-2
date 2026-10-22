import java.util.function.Function;
import java.util.function.Predicate;

public class Turma {
    private String codigo;
    private String disciplina;
    private Lista<Aluno> alunos;

    public Turma(String codigo, String nomeDisciplina) {
        this.codigo = codigo;
        this.disciplina = nomeDisciplina;
        this.alunos = new Lista<Aluno>();
    }

    public String getCodigo() { return codigo; }
    public String getDisciplina() { return disciplina; }
    public Lista<Aluno> getAlunos() { return alunos; }

    public void adicionarAluno(Aluno aluno) {
        this.alunos.adicionar(aluno);
    }

    // Alunos que satisfazem a condição (ex.: frequência acima de 80%)
    public Lista<Aluno> filtrar(Predicate<Aluno> condicao) {
        return alunos.filtrar(condicao);
    }





    // Usa o extrator pra tirar o nome de cada aluno
    public Lista<String> listarNomes(Function<Aluno, String> extrator) {
        Lista<String> nomes = new Lista<>();
        Celula<Aluno> aux = alunos.getPrimeiro().getProximo();

        while (aux != null) {
            Aluno aluno = aux.getItem();
            nomes.adicionar(extrator.apply(aluno));
            aux = aux.getProximo();
        }
        return nomes;
    }

    // Usa o extrator pra tirar o GPA de cada aluno
    public Lista<Double> listarGpas(Function<Aluno, Double> extrator) {
        Lista<Double> gpas = new Lista<>();
        Celula<Aluno> aux = alunos.getPrimeiro().getProximo();

        while (aux != null) {
            Aluno aluno = aux.getItem();
            gpas.adicionar(extrator.apply(aluno));
            aux = aux.getProximo();
        }
        return gpas;
    }

    // Média de um dado numérico dos alunos (ex.: GPA, frequência)
    public double calcularMedia(Function<Aluno, Double> extrator) {
        return alunos.calcularMedia(extrator);
    }

    @Override
    public String toString() {
        return "Turma " + codigo + " - " + disciplina + " (" + alunos.tamanho() + " alunos)\n" + alunos;
    }
}
