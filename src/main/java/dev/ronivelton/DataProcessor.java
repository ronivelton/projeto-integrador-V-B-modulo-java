package dev.ronivelton;

import java.util.ArrayList;
import java.util.List;

/**
 * Processa os dados dos sensores
 */
public class DataProcessor {
    private List<SensorData> historico = new ArrayList<>();

    // Valores para análise
    private double temperaturaMin = Double.MAX_VALUE;
    private double temperaturaMax = Double.MIN_VALUE;
    private int umidadeMin = 100;
    private int umidadeMax = 0;
    private int luzMin = 100;
    private int luzMax = 0;

    /**
     * Processa os dados recebidos e atualiza estatísticas
     */
    public void processarDados(SensorData dados) {
        // Adicionar ao histórico
        historico.add(dados);

        // Atualizar valores mínimos e máximos
        temperaturaMin = Math.min(temperaturaMin, dados.getTemperatura());
        temperaturaMax = Math.max(temperaturaMax, dados.getTemperatura());
        umidadeMin = Math.min(umidadeMin, dados.getUmidade());
        umidadeMax = Math.max(umidadeMax, dados.getUmidade());
        luzMin = Math.min(luzMin, dados.getLuz());
        luzMax = Math.max(luzMax, dados.getLuz());

        // Imprimir dados no console
        System.out.println("Leitura: " + dados);
    }

    /**
     * Mostra as estatísticas dos dados coletados
     */
    public void mostrarEstatisticas() {
        if (historico.isEmpty()) {
            System.out.println("Nenhum dado coletado ainda.");
            return;
        }

        System.out.println("\n=== ESTATÍSTICAS ===");
        System.out.printf("Total de leituras: %d\n", historico.size());
        System.out.printf("Temperatura: %.1f°C a %.1f°C (média: %.1f°C)\n",
                temperaturaMin, temperaturaMax, calcularMediaTemperatura());
        System.out.printf("Umidade: %d%% a %d%% (média: %.1f%%)\n",
                umidadeMin, umidadeMax, calcularMediaUmidade());
        System.out.printf("Luz: %d%% a %d%% (média: %.1f%%)\n",
                luzMin, luzMax, calcularMediaLuz());
        System.out.println("====================\n");
    }

    /**
     * Calcula a média de temperatura
     */
    private double calcularMediaTemperatura() {
        double soma = 0;
        for (SensorData dados : historico) {
            soma += dados.getTemperatura();
        }
        return soma / historico.size();
    }

    /**
     * Calcula a média de umidade
     */
    private double calcularMediaUmidade() {
        double soma = 0;
        for (SensorData dados : historico) {
            soma += dados.getUmidade();
        }
        return soma / historico.size();
    }

    /**
     * Calcula a média de luz
     */
    private double calcularMediaLuz() {
        double soma = 0;
        for (SensorData dados : historico) {
            soma += dados.getLuz();
        }
        return soma / historico.size();
    }
}