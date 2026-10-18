package br.com.alura.screenmatch.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URLEncoder;

public class ConsultaMyMemory {
    public static String obterTraducao(String text) {
        ObjectMapper mapper = new ObjectMapper();

        ConsumoApi consumo = new ConsumoApi();

        String texto = URLEncoder.encode(text);
        String langpair = URLEncoder.encode("en|pt-br");

        String url = "https://api.mymemory.translated.net/get?q=" + texto + "&langpair=" + langpair;

        String json = consumo.obterDados(url);

        // Extrai apenas o texto traduzido do JSON de retorno
        try {
            var dados = mapper.readTree(json);
            return dados.get("responseData").get("translatedText").asText();
        } catch (JsonProcessingException e) {
            return text; // Se der erro, retorna o texto original
        }
    }
}