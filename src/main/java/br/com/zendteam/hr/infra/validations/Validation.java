package br.com.zendteam.hr.infra.validations;

import org.springframework.stereotype.Component;

@Component
public class Validation {
    public boolean validateCpf(String cpf) {
        cpf = cpf.replaceAll("[^\\d]+", "");
        if (cpf.isEmpty()) return false;
        // Elimina CPFs invalidos conhecidos
        if (cpf.length() != 11 ||
                cpf.equals("00000000000") ||
                cpf.equals("11111111111") ||
                cpf.equals("22222222222") ||
                cpf.equals("33333333333") ||
                cpf.equals("44444444444") ||
                cpf.equals("55555555555") ||
                cpf.equals("66666666666") ||
                cpf.equals("77777777777") ||
                cpf.equals("88888888888") ||
                cpf.equals("99999999999")) {
            return false;
        }
        int add = 0;
        for (int i = 0; i < 9; i++) {
            add += Integer.parseInt(String.valueOf(cpf.charAt(i))) * (10 - i);
        }
        int rev = 11 - (add % 11);
        if (rev == 10 || rev == 11) rev = 0;
        if (rev != Integer.parseInt(String.valueOf(cpf.charAt(9)))) return false;
        add = 0;
        for (int i = 0; i < 10; i++) {
            add += Integer.parseInt(String.valueOf(cpf.charAt(i))) * (11 - i);
        }
        rev = 11 - (add % 11);
        if (rev == 10 || rev == 11) rev = 0;
        return rev == Integer.parseInt(String.valueOf(cpf.charAt(10)));
    }
    public boolean validateRG(String rg) {
        rg = rg.replaceAll("[^\\d]+", ""); // Remover caracteres não numéricos
        if (rg.isEmpty()) return false;

        // Verificar se o RG tem o tamanho esperado
        if (rg.length() != 9) return false;

        // Verificar se todos os caracteres são iguais (RG inválido)
        boolean allEqual = true;
        for (int i = 1; i < rg.length(); i++) {
            if (rg.charAt(i) != rg.charAt(0)) {
                allEqual = false;
                break;
            }
        }
        if (allEqual) return false;

//        // Calcular o dígito verificador
//        int soma = 0;
//        for (int i = 0; i < 8; i++) {
//            soma += Character.getNumericValue(rg.charAt(i)) * (9 - i);
//        }
//        int resto = soma % 11;
//        int dv = 11 - resto;
//        if (dv == 10 || dv == 11) dv = 0;
//
//        // Verificar se o dígito verificador está correto
//        int ultimoDigito = Character.getNumericValue(rg.charAt(8));
//        return ultimoDigito == dv && Character.getNumericValue(rg.charAt(8)) > 0; // Verifica se o último dígito é maior que zero
        return allEqual;
    }
}
