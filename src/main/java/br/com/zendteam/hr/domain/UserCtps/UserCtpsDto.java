package br.com.zendteam.hr.domain.UserCtps;

import java.util.UUID;

public record UserCtpsDto(
        String user_id,
        String num_carteira,
        String num_serieCarteira,
        String data_emissao,
        String uf_emissor_carteira
) {
}
