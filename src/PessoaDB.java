import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PessoaDB {
	public static void inserir(Pessoa pessoa) throws DbExcetion {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConexaoDB.getConexao();
			String verificar = "SELECT COUNT(*) FROM PESSOA WHERE EMAIL = ?";
			stmt = conn.prepareStatement(verificar);
			stmt.setString(1, pessoa.getEmail().toUpperCase());

			rs = stmt.executeQuery();
			rs.next();
			int count = rs.getInt(1);

			if (count > 0) {
				throw new DbExcetion("Esse E-mail já existe!!");
			} else {
				conn = ConexaoDB.getConexao();
				String sql = "INSERT INTO PESSOA (id, nome, sobrenome, idade, email, telefone) "
						+ "VALUES (?, ?, ?, ?, ?, ?)";
				stmt = conn.prepareStatement(sql);

				stmt.setInt(1, pessoa.getId());
				stmt.setString(2, pessoa.getNome().toUpperCase());
				stmt.setString(3, pessoa.getSobrenome().toUpperCase());
				stmt.setInt(4, pessoa.getIdade());
				stmt.setString(5, pessoa.getEmail().toUpperCase());
				stmt.setString(6, pessoa.getTelefone());

				stmt.executeUpdate();
				System.out.println("Coanatato adicionado com sucesso!!");
			}
		} catch (Exception e) {
			e.printStackTrace();

			throw new DbExcetion("Falha ao inserir o contato!!");
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	public static void remover(Integer id) throws DbExcetion {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = ConexaoDB.getConexao();
			String sql = "DELETE FROM PESSOA WHERE id = ?";
			stmt = conn.prepareStatement(sql);

			stmt.setInt(1, id);

			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();

			throw new DbExcetion("Falha ao remover o contato!!");
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}
		}
	}

	public static List<Pessoa> buscaTodos() throws DbExcetion {
		List<Pessoa> resultados = new ArrayList<>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConexaoDB.getConexao();

			stmt = conn.createStatement();
			String sql = "SELECT id, nome, sobrenome, idade, email, telefone FROM Pessoa ORDER BY nome ASC";
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int id = rs.getInt("id");
				int age = rs.getInt("idade");
				String first = rs.getString("nome");
				String last = rs.getString("sobrenome");
				String email = rs.getString("email");
				String telefone = rs.getString("telefone");

				resultados.add(new Pessoa(id, first, last, age, email, telefone));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DbExcetion("Falha ao listar os contatos!!");
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		return resultados;
	}

	public static List<Pessoa> listarSobrenome() throws DbExcetion {
		List<Pessoa> resultados = new ArrayList<>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConexaoDB.getConexao();

			stmt = conn.createStatement();
			String sql = "SELECT id, nome, sobrenome, idade, email, telefone FROM Pessoa ORDER BY sobrenome ASC";
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int id = rs.getInt("id");
				int age = rs.getInt("idade");
				String first = rs.getString("nome");
				String last = rs.getString("sobrenome");
				String email = rs.getString("email");
				String telefone = rs.getString("telefone");

				resultados.add(new Pessoa(id, first, last, age, email, telefone));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DbExcetion("Falha ao listar os contatos!!");
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		return resultados;
	}

	public static List<Pessoa> listarEmail() throws DbExcetion {
		List<Pessoa> resultados = new ArrayList<>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConexaoDB.getConexao();

			stmt = conn.createStatement();
			String sql = "SELECT id, nome, sobrenome, idade, email, telefone FROM Pessoa ORDER BY email ASC";
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int id = rs.getInt("id");
				int age = rs.getInt("idade");
				String first = rs.getString("nome");
				String last = rs.getString("sobrenome");
				String email = rs.getString("email");
				String telefone = rs.getString("telefone");

				resultados.add(new Pessoa(id, first, last, age, email, telefone));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DbExcetion("Falha ao listar os contatos!!");
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		return resultados;
	}

	public static List<Pessoa> procurarNome(String nome) throws DbExcetion {
		List<Pessoa> resultadosNomes = new ArrayList<>();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = ConexaoDB.getConexao();
			String sql = "SELECT * FROM PESSOA WHERE NOME LIKE ?";
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, ("%" + nome.toUpperCase() + "%"));
			boolean contatoEncontrado = false;
			rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				int age = rs.getInt("idade");
				String first = rs.getString("nome");
				String last = rs.getString("sobrenome");
				String email = rs.getString("email");
				String telefone = rs.getString("telefone");
				System.out.println("CONTATO ENCONTRADO:");
				System.out.println("ID: " + id);
				System.out.println("NOME: " + first);
				System.out.println("SOBRENOME: " + last);
				System.out.println("IDADE: " + age);
				System.out.println("E-MAIL: " + email);
				System.out.println("TELEFONE: " + telefone);
				contatoEncontrado = true;
				resultadosNomes.add(new Pessoa(id, first, last, age, email, telefone));
			}
			if (!contatoEncontrado) {
				System.out.println("Nome nao encontrado!!");
			}
		}
		catch (ClassNotFoundException e1) {
			System.err.println(e1.getMessage());
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			System.err.println(e1.getMessage());
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DbExcetion("Falha ao encontrar nome!!");
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
		}

		return resultadosNomes;

	}

	public static List<Pessoa> procurarSobrenome(String sobrenome) throws DbExcetion {
		List<Pessoa> resultadosSobrenomes = new ArrayList<>();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = ConexaoDB.getConexao();
			String sql = "SELECT * FROM PESSOA WHERE SOBRENOME LIKE ?";
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, ("%" + sobrenome.toUpperCase() + "%"));
			boolean contatoEncontrado = false;
			rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				int age = rs.getInt("idade");
				String first = rs.getString("nome");
				String last = rs.getString("sobrenome");
				String email = rs.getString("email");
				String telefone = rs.getString("telefone");
				System.out.println("REGISTRO ENCONTRADO:");
				System.out.println("ID: " + id);
				System.out.println("NOME: " + first);
				System.out.println("SOBRENOME: " + last);
				System.out.println("IDADE: " + age);
				System.out.println("E-MAIL: " + email);
				System.out.println("TELEFONE: " + telefone);
				contatoEncontrado = true;
				resultadosSobrenomes.add(new Pessoa(id, first, last, age, email, telefone));
			}
			if (!contatoEncontrado) {
				System.out.println("Nome nao encontrado!!");
			}
		}
		catch (ClassNotFoundException e1) {
			System.err.println(e1.getMessage());
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			System.err.println(e1.getMessage());
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DbExcetion("Falha ao encontrar sobrenome!!");
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
		}

		return resultadosSobrenomes;

	}

	public static List<Pessoa> sobrenomeAparicoes() throws DbExcetion {
		List<Pessoa> sobrenomeAparicoes = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = ConexaoDB.getConexao();
			String sql = "SELECT SOBRENOME, COUNT(*) AS APARICOES FROM PESSOA GROUP BY SOBRENOME";
			stmt = conn.prepareStatement(sql);

			rs = stmt.executeQuery();

			while (rs.next()) {
				String last = rs.getString("sobrenome");
				Integer aparicoes = rs.getInt("aparicoes");

				System.out.println(last + " " + aparicoes + ", ");
			}
		}
		catch (ClassNotFoundException e1) {
			System.err.println(e1.getMessage());
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			System.err.println(e1.getMessage());
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DbExcetion("Falha ao encontrar sobrenome!!");
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
		}

		return sobrenomeAparicoes;

	}

	public static void editar(Integer id, Pessoa pessoa) throws DbExcetion {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = ConexaoDB.getConexao();
			String sql = "UPDATE PESSOA SET NOME= ?, SOBRENOME = ?,IDADE = ?, TELEFONE = ?, EMAIL = ?  WHERE ID = ?";

			stmt = conn.prepareStatement(sql);

			stmt.setString(1, pessoa.getNome().toUpperCase());
			stmt.setString(2, pessoa.getSobrenome().toUpperCase());
			stmt.setInt(3, pessoa.getIdade());
			stmt.setString(4, pessoa.getTelefone());
			stmt.setString(5, pessoa.getEmail().toUpperCase());
			stmt.setInt(6, id);

			stmt.executeUpdate();
			System.out.println("Contato editado com sucesso!!");
		} catch (Exception e) {
			e.printStackTrace();
			throw new DbExcetion("Falha ao editar contato!!");
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
			}
		}
	}
}
