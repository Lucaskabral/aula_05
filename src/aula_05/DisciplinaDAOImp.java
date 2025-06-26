import java.util.List;

public interface DisciplinaDAO {
	void inserir(Disciplina disciplina);

	void atualizar(Disciplina disciplina);

	void deletar(int idDisciplina);

	Disciplina buscarPorId(int idDisciplina);

	List<Disciplina> listarTodos();
}