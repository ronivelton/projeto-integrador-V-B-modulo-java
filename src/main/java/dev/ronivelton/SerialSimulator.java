package dev.ronivelton;

import java.util.Random;
import org.json.JSONObject;

/**
 * Simula a recepção de dados pela porta serial
 */
public class SerialSimulator {
    private Random random = new Random();

    // Valores base para simulação
    private double tempBase = 25.0;
    private int umidadeBase = 60;
    private int luzBase = 50;

    /**
     * Gera uma leitura simulada de sensores
     */
    public SensorData gerarLeitura() {
        // Gerar valores com pequenas variações aleatórias
        double temperatura = tempBase + (random.nextDouble() * 2 - 1) * 0.5;
        int umidade = umidadeBase + (int)((random.nextDouble() * 2 - 1) * 3);
        int luz = luzBase + (int)((random.nextDouble() * 2 - 1) * 5);

        // Limitar valores dentro de faixas realistas
        temperatura = Math.round(temperatura * 10) / 10.0;  // Arredondar para 1 casa decimal
        umidade = Math.max(0, Math.min(100, umidade));      // Limitar entre 0-100%
        luz = Math.max(0, Math.min(100, luz));              // Limitar entre 0-100%

        // Criar e retornar objeto SensorData
        return new SensorData(temperatura, umidade, luz);
    }

    /**
     * Simula a leitura do formato JSON que viria do Arduino
     */
    public String lerDadosJSON() {
        SensorData dados = gerarLeitura();

        // Criar objeto JSON similar ao que o Arduino enviaria
        JSONObject json = new JSONObject();
        json.put("temperatura", dados.getTemperatura());
        json.put("umidade", dados.getUmidade());
        json.put("luz", dados.getLuz());

        return json.toString();
    }
}