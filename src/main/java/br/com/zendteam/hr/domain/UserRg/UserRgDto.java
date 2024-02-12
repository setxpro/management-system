package br.com.zendteam.hr.domain.UserRg;

public record UserRgDto(String user_id,
        String nome_completo,
        String social_name,
        String sexo,
        String raca_cor,
        String estado_civil,
        String nacionalidade,
        String estado_nascimento,
        String cidade_nascimento,
        String data_nascimento,
        String nome_da_mae,
        String nome_do_pai,
        String num_rg,
        String data_expedicao_rg,
        String orgao_emissor_rg,
        String uf_emissor_rg) {
}
