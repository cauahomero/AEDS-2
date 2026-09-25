import java.util.Comparator;
public class ComparadorPorDescricao implements Comparator<Produto> {
    @Override
    public int compare(Produto a, Produto b) {
        return a.descricao().toLowerCase().compareTo(b.descricao().toLowerCase());
    }
}