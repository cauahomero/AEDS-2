
public class Aluno{
    private String matricula;
    private String nome;
    private Lista<Double> notas;
    private double frequencia;

    public Aluno(){}

    public Aluno(String matricula, String nome, Lista<Double> notas, double frequencia) {
        this.matricula = matricula;
        this.nome = nome;
        this.notas = notas;
        this.frequencia = frequencia;
    }

    public String getMatricula() { return matricula; }
    public String getNome() { return nome; }
    public Lista<Double> getNotas() { return notas; }
    public double getFrequencia() { return frequencia; }


    public static Aluno criarDoTexto(String linha) {
        
        String[] partes = linha.split(";");
        
        String matricula = partes[0];
        String nome = partes[1];
        
        // Converte o texto "8.5,9.0,8.0" em List<Double>
        Lista<Double> notas = new Lista<>();
        for (String n : partes[2].split(",")) {
            notas.adicionar(Double.parseDouble(n));
        }
        
        double frequencia = Double.parseDouble(partes[3]);

        return new Aluno(matricula, nome, notas, frequencia);
    }

    @Override
    public String toString() {
        return String.format("%s - %s | Notas: %s | Freq: %.0f%%", 
                matricula, nome, notas, frequencia * 100);
    }
}