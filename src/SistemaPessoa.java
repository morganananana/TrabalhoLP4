
import java.util.List;
import java.util.Scanner;

public class SistemaPessoa {
	public static void main(String[] args) throws DbExcetion {
		Scanner teclado = new Scanner(System.in);

		int opcao = 0;

		while (opcao != 11) {
			imprimirMenu();

			System.out.println("Informe uma opção");
			opcao = teclado.nextInt();

			switch (opcao) {
			case 1:
				inserir();
				break;
			case 2:
				remover();
				break;
			case 3:
				procurarNome();
				break;
			case 4:
				procurarSobrenome();
				break;
			case 5:
				listarNome();
				break;
			case 6:
				listarEmail();
				break;
			case 7:
				listarSobrenome();
				break;
			case 8:
				sobrenomeAparicoes();
				break;
			case 9:
				editar();
				break;
			case 10:
				System.out.println("TchauTchau!!");
				break;
			default:
				System.err.println("Opcao invalida!!");
				break;
			}
		}
	}

	public static void listarNome() {
		try {
			List<Pessoa> pessoas = PessoaDB.buscaTodos();

			for (Pessoa pessoa : pessoas) {
				System.out.println(">> " + pessoa.getNome());
			}
		} catch (DbExcetion e) {
			System.err.println(e.getMessage());
		}
	}

	public static void listarEmail() {
		try {
			List<Pessoa> pessoas = PessoaDB.listarEmail();

			for (Pessoa pessoa : pessoas) {
				System.out.println(">> " + pessoa.getEmail());
			}
		} catch (DbExcetion e) {
			System.err.println(e.getMessage());
		}
	}

	public static void listarSobrenome() {
		try {
			List<Pessoa> pessoas = PessoaDB.listarSobrenome();

			for (Pessoa pessoa : pessoas) {
				System.out.println(">> " + pessoa.getSobrenome());
			}
		} catch (DbExcetion e) {
			System.err.println(e.getMessage());
		}
	}

	public static void remover() {
		Scanner teclado = new Scanner(System.in);
		System.out.println("Digite o ID do contato que voce quer remover:");
		Integer id = teclado.nextInt();

		try {
			PessoaDB.remover(id);
			System.out.println("Contato removido com sucesso!!");
		} catch (DbExcetion e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		}
	}

	public static void procurarNome() {
		Scanner teclado = new Scanner(System.in);
		System.out.println("Digite o nome que voce esta procurando:");
		String nome = teclado.nextLine();

		try {
			PessoaDB.procurarNome(nome);
		} catch (DbExcetion e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		}

	}

	public static void procurarSobrenome() {
		Scanner teclado = new Scanner(System.in);
		System.out.println("Digite o sobrenome que voce esta procurando:");
		String sobrenome = teclado.nextLine();

		try {
			PessoaDB.procurarSobrenome(sobrenome);
		} catch (DbExcetion e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		}

	}

	public static void sobrenomeAparicoes() {
		try {
			PessoaDB.sobrenomeAparicoes();
		} catch (DbExcetion e) {
			System.err.println(e.getMessage());
		}
	}

	public static void inserir() {
		Scanner teclado = new Scanner(System.in);

		System.out.println("Informe um nome: ");
		String nome = teclado.next();

		System.out.println("Informe um sobrenome: ");
		String sobrenome = teclado.next();

		System.out.println("Informe a idade: ");
		Integer idade = teclado.nextInt();

		teclado.nextLine();

		System.out.println("Informe o e-mail:");
		String email = teclado.nextLine();

		System.out.println("Informe o telefone:");
		String telefone = teclado.nextLine();

		System.out.println("Informe o ID: ");
		Integer id = teclado.nextInt();

		Pessoa pessoa = new Pessoa(id, nome, sobrenome, idade, email, telefone);

		try {
			PessoaDB.inserir(pessoa);
		} catch (DbExcetion e) {
			System.err.println(e.getMessage());
		}
	}

	public static void editar() throws DbExcetion {
		Scanner teclado = new Scanner(System.in);
		System.out.println("Digite o ID do contato que voce deseja editar:");
		Integer id = teclado.nextInt();
		try {
			System.out.println("Informe um nome: ");
			String nome = teclado.next();

			System.out.println("Informe um sobrenome: ");
			String sobrenome = teclado.next();

			System.out.println("Informe a idade: ");
			Integer idade = teclado.nextInt();

			teclado.nextLine();

			System.out.println("Informe o e-mail:");
			String email = teclado.nextLine();

			System.out.println("Informe o telefone:");
			String telefone = teclado.nextLine();

			Pessoa pessoa = new Pessoa(id, nome, sobrenome, idade, email, telefone);

			PessoaDB.editar(id, pessoa);
		} catch (DbExcetion e) {
			System.err.println(e.getMessage());
		}
	}

	public static void imprimirMenu() {
		System.out.println("1 - Inserir");
		System.out.println("2 - Remover");
		System.out.println("3 - Procurar por nome");
		System.out.println("4 - Procurar por sobrenome");
		System.out.println("5 - Listar os nomes em ordem alfabetica");
		System.out.println("6 - Listar e-mails em ordem crescente");
		System.out.println("7 - Listar sobrenome em ordem crescente");
		System.out.println("8 - Listar sobrenome e o total de aparições");
		System.out.println("9 - Editar");
		System.out.println("10 - Sair");
	}
}