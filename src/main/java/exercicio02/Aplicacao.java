package exercicio02;

import java.util.List;
import java.util.Scanner;

public class Aplicacao {
    public static void main(String[] args) {
        carroDAO carroDAO = new carroDAO();
        Scanner scanner = new Scanner(System.in);
        
        boolean sair = false;
        
        while (!sair) {
            System.out.println("Escolha uma operação:");
            System.out.println("1) Inserir");
            System.out.println("2) Listar");
            System.out.println("3) Atualizar");
            System.out.println("4) Excluir");
            System.out.println("5) Sair");
            
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer
            
            switch (opcao) {
                case 1:
                    inserircarro(carroDAO, scanner);
                    break;
                case 2:
                    listarcarros(carroDAO);
                    break;
                case 3:
                    atualizarcarro(carroDAO, scanner);
                    break;
                case 4:
                    excluircarro(carroDAO, scanner);
                    break;
                case 5:
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
            }
        }
        
        // Fechar recursos
        carroDAO.finalize();
        scanner.close();
    }
    
    private static void inserircarro(carroDAO carroDAO, Scanner scanner) {
        System.out.println("Digite o código do carro:");
        int codigo = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer
        
        System.out.println("Digite o nome do carro:");
        String nome = scanner.nextLine();
        
        System.out.println("Digite a marca do carro:");
        String marca = scanner.nextLine();
        
        System.out.println("Digite a nota do carro:");
        char nota = scanner.nextLine().charAt(0);
        
        carro novocarro = new carro(codigo, nome, marca, nota);
        boolean inserido = carroDAO.insert(novocarro);
        if (inserido) {
            System.out.println("carro inserido com sucesso.");
        } else {
            System.out.println("Falha ao inserir o carro.");
        }
    }
    
    private static void listarcarros(carroDAO carroDAO) {
        List<carro> carros = carroDAO.get();
        if (carros.isEmpty()) {
            System.out.println("Não há carros cadastrados.");
        } else {
            System.out.println("Lista de carros:");
            for (carro carro : carros) {
                System.out.println(carro);
            }
        }
    }
    
    private static void atualizarcarro(carroDAO carroDAO, Scanner scanner) {
        System.out.println("Digite o código do carro que deseja atualizar:");
        int codigo = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer
        
        carro carroExistente = carroDAO.get(codigo);
        if (carroExistente == null) {
            System.out.println("carro não encontrado.");
            return;
        }
        
        System.out.println("Digite o novo nome do carro:");
        String novoNome = scanner.nextLine();
        
        System.out.println("Digite a nova marca do carro:");
        String novaMarca = scanner.nextLine();
        
        System.out.println("Digite a nova nota do carro:");
        char novaNota = scanner.nextLine().charAt(0);
        
        carroExistente.setNome(novoNome);
        carroExistente.setMarca(novaMarca);
        carroExistente.setNota(novaNota);
        
        boolean atualizado = carroDAO.update(carroExistente);
        if (atualizado) {
            System.out.println("carro atualizado com sucesso.");
        } else {
            System.out.println("Falha ao atualizar o carro.");
        }
    }
    
    private static void excluircarro(carroDAO carroDAO, Scanner scanner) {
        System.out.println("Digite o código do carro que deseja excluir:");
        int codigo = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer
        
        boolean excluido = carroDAO.delete(codigo);
        if (excluido) {
            System.out.println("carro excluído com sucesso.");
        } else {
            System.out.println("Falha ao excluir o carro.");
        }
    }
}
