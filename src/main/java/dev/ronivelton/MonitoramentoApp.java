package dev.ronivelton;

import java.util.Scanner;
import org.json.JSONObject;

/**
 * Aplicação principal do sistema de monitoramento
 */
public class MonitoramentoApp {
    public static void main(String[] args) {
        System.out.println("=== Sistema de Monitoramento Ambiental ===");

        // Criar componentes do sistema
        SerialSimulator simulador = new SerialSimulator();
        DataProcessor processador = new DataProcessor();
        Scanner scanner = new Scanner(System.in);

        boolean executando = true;

        // Menu principal
        while (executando) {
            exibirMenu();
            String comando = scanner.nextLine().trim();

            switch (comando) {
                case "1":
                    // Iniciar/continuar monitoramento (10 leituras)
                    realizarLeituras(simulador, processador, 10);
                    break;

                case "2":
                    // Mostrar estatísticas
                    processador.mostrarEstatisticas();
                    break;

                case "3":
                    // Sair
                    executando = false;
                    System.out.println("Encerrando o programa...");
                    break;

                default:
                    System.out.println("Comando inválido!");
            }
        }

        scanner.close();
    }

    /**
     * Exibe o menu de opções
     */
    private static void exibirMenu() {
        System.out.println("\nEscolha uma opção:");
        System.out.println("1 - Iniciar/continuar monitoramento (10 leituras)");
        System.out.println("2 - Mostrar estatísticas");
        System.out.println("3 - Sair");
        System.out.print("Comando: ");
    }

    /**
     * Realiza um número específico de leituras
     */
    private static void realizarLeituras(SerialSimulator simulador, DataProcessor processador, int numLeituras) {
        System.out.println("\nIniciando " + numLeituras + " leituras...\n");

        for (int i = 0; i < numLeituras; i++) {
            // Simular recebimento de dados
            String dadosJSON = simulador.lerDadosJSON();

            try {
                // Converter JSON para objeto
                JSONObject json = new JSONObject(dadosJSON);

                // Extrair valores
                double temperatura = json.getDouble("temperatura");
                int umidade = json.getInt("umidade");
                int luz = json.getInt("luz");

                // Criar objeto de dados
                SensorData dados = new SensorData(temperatura, umidade, luz);

                // Processar os dados
                processador.processarDados(dados);

                // Pequeno delay para simular o tempo de leitura (500ms)
                Thread.sleep(500);

            } catch (Exception e) {
                System.err.println("Erro ao processar dados: " + e.getMessage());
            }
        }

        System.out.println("\nLeituras concluídas. Pressione ENTER para continuar...");
        new Scanner(System.in).nextLine();
    }
}