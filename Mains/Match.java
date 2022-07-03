package br.com.cielo.mic.pix.domain.util;

import br.com.cielo.mic.pix.domain.integrations.payload.KeyMatched;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Match {

    public static KeyMatched getKeyMatched(String texto) {

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

        for (Map.Entry<String, Pattern> pattern : patterns.entrySet()) {

            Matcher matcher = pattern.getValue().matcher(texto);
            if (matcher.find()) {
                String chave = String.valueOf(matcher.toMatchResult().group());
                String masked = null;
                switch (pattern.getKey()) {
                    case "EMAIL":
                        masked = chave.replaceAll("(\\w{1,3})(\\w+)(@.*)", "$1****$3");
                        break;
                    case "CPF":
                        masked = chave.replaceAll("(\\d{3})\\.?(\\d{3})\\.?(\\d{3})-?(\\d{2})", "$1.***.***-$4");
                        break;
                    case "CNPJ":
                        masked = chave.replaceAll("(\\d{2})\\.?(\\d{3})\\.?(\\d{3})/?(\\d{4})-?(\\d{2})", "$1.***.***/****-$5");
                        break;
                    case "PHONE":
                        masked = chave.replaceAll("\\+(\\d{1,3})[ -]?\\(?(\\d{2})\\)?[ -]?(\\d{5})[ -]?(\\d{4})", "+[**](**)*****-$4");
                        break;
                    case "EVP":
                        masked = chave.replaceAll("([\\da-f]{8})\\-([\\da-f]{4})\\-([\\da-f]{4})-([\\da-f]{4})\\-([\\da-f]{12})", "********-****-****-****-$5");
                        break;
                    default:
                        String mask = chave.replaceAll("[\\w+]|(?!^)\\+", "*");
                        String masked1 = mask.substring(0, mask.length()-5);
                        String masked2 = chave.substring(chave.length()-5);
                        masked = masked1 + masked2;
                        break;
                }

                return KeyMatched.builder().type(pattern.getKey()).key(chave).mask(masked).build();
            }
        }

        return null;
    }

}
