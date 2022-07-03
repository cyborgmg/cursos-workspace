package com.cyborg.timeout;

import com.cyborg.timeout.model.Key;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private static Key matched(String texto) {

        final String CNPJ_REGEX = "(|\\n)(\\d{2}.\\d{3}.\\d{3}/\\d{4}-\\d{2})|(\\b\\w{14}\\b)";
        final String CPF_REGEX = "(|\\n)(\\d{3}.\\d{3}.\\d{3}-\\d{2})|(\\b\\w{11}\\b)";
        final String EMAIL_REGEX = "(|\\n)(\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+)";
        final String TELEPHONE_REGEX = "(|\\n)(\\+\\d+[ ]?\\(?\\d+\\)?[ ]?\\d+[-. ]?\\d+)";
        final String EVP_REGEX = "(|\\n)([\\da-f]{8}-([\\da-f]{4}-){3}[\\da-f]{12})";

        Map<String,Pattern> patterns = new HashMap<String, Pattern>();

        patterns.put("PHONE", Pattern.compile(TELEPHONE_REGEX, Pattern.MULTILINE));
        patterns.put("CNPJ", Pattern.compile(CNPJ_REGEX, Pattern.MULTILINE));
        patterns.put("CPF", Pattern.compile(CPF_REGEX, Pattern.MULTILINE));
        patterns.put("EMAIL", Pattern.compile(EMAIL_REGEX, Pattern.MULTILINE));
        patterns.put("EVP", Pattern.compile(EVP_REGEX, Pattern.MULTILINE));

        for(Map.Entry<String,Pattern> pattern : patterns.entrySet() ) {

            Matcher matcher = pattern.getValue().matcher(texto);
            if (matcher.find()) {
                String chave = String.valueOf(matcher.toMatchResult().group());
                String masked = null;
                //System.out.println("| Tipo: " + pattern.getKey() + " - Chave: " + chave);
                switch (pattern.getKey()) {
                    case "EMAIL":
                        masked = chave.replaceAll("(\\w{1,3})(\\w+)(@.*)", "$1****$3");
                        break;
                    default:
                        String mask = chave.replaceAll("[\\w+]|(?!^)\\+", "*");
                        String masked1 = mask.substring(0, mask.length() - 5);
                        String masked2 = chave.substring(chave.length() - 5);
                        masked = masked1 + masked2;
                        break;
                }
                //System.out.println("| Chave mascarada: " + masked);
                return  Key.builder().tipo(pattern.getKey()).chave(chave).mask(masked).build();
            }

        }

        return null;

    }

    public static void main(String[] args) {

        /*mapKeys.put("EMAIL", "solonbh@gmail.com");
        mapKeys.put("CNPJ_COM_PONTUACAO", "05.058.497/0001-12");
        mapKeys.put("CNPJ_SEM_PONTUACAO", "05058497000112");
        mapKeys.put("CPF_COM_PONTUACAO", "044.267.866-57");
        mapKeys.put("CPF_SEM_PONTUACAO", "04426786657");
        mapKeys.put("TELEFONE_COM_PONTUACAO", "+55(31)99787-9604");
        mapKeys.put("TELEFONE_SEM_PONTUACAO", "+5531997879604");
        mapKeys.put("CHAVE_EVP", "efc5dc2b-8ed4-4207-a0bf-1cff78a1c782");*/

        String texto = "O JDPI recebeu um pedido de Portabilidade da chave para endereçamento efc5dc2b-8ed4-4207-a0bf-1cff78a1c782. " +
                "Isso significa que outro PSP registrou pedido em seu nome para que essa chave seja vinculada a outra conta.\n"+
                " Para conclusão desse processo, é necessária a sua confirmação em até 7 (sete) dias.\n"+
                " Acesse o JDPI e registre a sua decisão.";

        Key key = matched(texto);

        System.out.println(key.getTipo()+" - "+key.getChave()+" - "+key.getMask());

    }


/*    public static void main(String[] args) {

        Map<String, String> mapKeys = new HashMap<>();
        mapKeys.put("EMAIL", "solonbh@gmail.com");
        mapKeys.put("CNPJ_COM_PONTUACAO", "05.058.497/0001-12");
        mapKeys.put("CNPJ_SEM_PONTUACAO", "05058497000112");
        mapKeys.put("CPF_COM_PONTUACAO", "044.267.866-57");
        mapKeys.put("CPF_SEM_PONTUACAO", "04426786657");
        mapKeys.put("TELEFONE_COM_PONTUACAO", "+55(31)99787-9604");
        mapKeys.put("TELEFONE_SEM_PONTUACAO", "+5531997879604");
        mapKeys.put("CHAVE_EVP", "efc5dc2b-8ed4-4207-a0bf-1cff78a1c782");

        for (Map.Entry<String, String> entry: mapKeys.entrySet()) {
                String chave = entry.getValue();
                String texto = "O JDPI recebeu um pedido de Portabilidade da chave para endereçamento "+chave+".\n"+
                        " Isso significa que outro PSP registrou pedido em seu nome para que essa chave seja vinculada a outra conta.\n"+
                        " Para conclusão desse processo, é necessária a sua confirmação em até 7 (sete) dias.\n"+
                        " Acesse o JDPI e registre a sua decisão.";
                System.out.println("|------------------------------------------------------------------------------------|");
                System.out.println("|Teste chave: "+ entry.toString());

                for (Map.Entry<String, Pattern> pattern : getPatterns().entrySet()) {
                    matched(pattern, texto);
                }
            }

    }*/

}
