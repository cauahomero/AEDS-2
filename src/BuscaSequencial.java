import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class BuscaSequencial<T extends Comparable<T>> implements IBuscador<T> {

    private T[] dados;
    private int comparacoes;
    private LocalDateTime inicio, termino;
    private Comparator<T> comparador;

    private void init(T[] dados, Comparator<T> comparador) {
        this.comparador = comparador;
        this.dados = dados;
    }

    public BuscaSequencial(T[] dados) {
        init(dados, T::compareTo);
    }

    public BuscaSequencial(T[] dados, Comparator<T> comparador) {
        init(dados, comparador);
    }

    @Override
    public long getComparacoes() {
        return comparacoes;
    }

    @Override
    public double getTempo() {
        return Duration.between(inicio, termino).toNanos();
    }

    @Override
    public T buscar(T dado) {

        comparacoes = 0;
        inicio = LocalDateTime.now();

        for (int i = 0; i < dados.length; i++) {
            comparacoes++;
            if (comparador.compare(dados[i], dado) == 0) {
                termino = LocalDateTime.now();
                return dados[i];
            }
        }

        termino = LocalDateTime.now();
        throw new NoSuchElementException("Elemento não encontrado");
    }
}
