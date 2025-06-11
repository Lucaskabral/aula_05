import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DisciplinaDAOImp implements DisciplinaDAO {

	private Connection connection;

	public DisciplinaDAOImp(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void inserir(Disciplina disciplina) {
		String sql = "INSERT INTO disciplina (nomedisciplina, cargahoraria) VALUES (?, ?)";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, disciplina.getNomeDisciplina());
			stmt.setInt(2, disciplina.getCargaHoraria());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void atualizar(Disciplina disciplina) {
		String sql = "UPDATE disciplina SET nomedisciplina = ?, cargahoraria = ? WHERE iddisciplina = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, disciplina.getNomeDisciplina());
			stmt.setInt(2, disciplina.getCargaHoraria());
			stmt.setInt(3, disciplina.getIdDisciplina());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deletar(int idDisciplina) {
		String sql = "DELETE FROM disciplina WHERE iddisciplina = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, idDisciplina);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Disciplina buscarPorId(int idDisciplina) {
		String sql = "SELECT * FROM disciplina WHERE iddisciplina = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, idDisciplina);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new Disciplina(rs.getInt("iddisciplina"), rs.getString("nomedisciplina"),
						rs.getInt("cargahoraria"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
    public List<Disciplina> listarTodos() {
        List<Disciplina> disciplinas = new ArrayList<>();
        String sql = "SELECT * FROM disciplina";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                disciplinas.add(new Disciplina(
                    rs.getInt("iddisciplina"),
                    rs.getString("nomedisciplina"),
                    rs.getInt("cargahoraria")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return disciplinas;
    }