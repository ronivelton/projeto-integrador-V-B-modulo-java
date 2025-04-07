package dev.ronivelton;

import java.util.Date;

/**
 * Modelo que representa uma leitura dos sensores
 */
public class SensorData {
    private double temperatura;
    private int umidade;
    private int luz;
    private Date timestamp;

    public SensorData(double temperatura, int umidade, int luz) {
        this.temperatura = temperatura;
        this.umidade = umidade;
        this.luz = luz;
        this.timestamp = new Date();
    }

    // Getters
    public double getTemperatura() { return temperatura; }
    public int getUmidade() { return umidade; }
    public int getLuz() { return luz; }
    public Date getTimestamp() { return timestamp; }

    @Override
    public String toString() {
        return String.format("[%tT] Temperatura: %.1f°C | Umidade: %d%% | Luz: %d%%",
                timestamp, temperatura, umidade, luz);
    }
}