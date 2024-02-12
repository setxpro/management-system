package br.com.zendteam.hr.infra;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DateUtils {
    public String formatarData(String dataString) {
        try {
            // Formato da data de entrada
            SimpleDateFormat formatoEntrada = new SimpleDateFormat("dd/MM/yyyy");
            // Formato da data de saída
            SimpleDateFormat formatoSaida = new SimpleDateFormat("yyyyMMdd");

            // Parse da data de entrada para um objeto Date
            Date data = formatoEntrada.parse(dataString);

            // Formatar a data para o formato desejado
            return formatoSaida.format(data);
        } catch (ParseException e) {
            // Tratamento de exceção para o caso em que a data de entrada não está no formato esperado
            e.printStackTrace();
            return null;
        }
    }
}
