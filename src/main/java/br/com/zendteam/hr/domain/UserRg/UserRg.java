package br.com.zendteam.hr.domain.UserRg;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@Entity(name = "user_rg")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class UserRg {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String user_id;
    private String nome_completo;
    private String social_name;
    private String sexo;
    private String raca_cor;
    private String estado_civil;
    private String nacionalidade;
    private String estado_nascimento;
    private String cidade_nascimento;
    private String data_nascimento;
    private String nome_da_mae;
    private String nome_do_pai;
    private String num_rg;
    private String data_expedicao_rg;
    private String orgao_emissor_rg;
    private String uf_emissor_rg;

    public UserRg(UserRgDto userRgDto) {
        this.user_id = userRgDto.user_id();
        this.nome_completo = userRgDto.nome_completo().toUpperCase();
        this.social_name = userRgDto.social_name().toUpperCase();
        this.sexo = userRgDto.sexo().toUpperCase();
        this.raca_cor = userRgDto.raca_cor().toUpperCase();
        this.estado_civil = userRgDto.estado_civil().toUpperCase();
        this.nacionalidade = userRgDto.nacionalidade().toUpperCase();
        this.estado_nascimento = userRgDto.estado_nascimento().toUpperCase();
        this.cidade_nascimento = userRgDto.cidade_nascimento().toUpperCase();
        this.data_nascimento = userRgDto.data_nascimento();
        this.nome_da_mae = userRgDto.nome_da_mae().toUpperCase();
        this.nome_do_pai = userRgDto.nome_do_pai().toUpperCase();
        this.num_rg = userRgDto.num_rg().toUpperCase();
        this.data_expedicao_rg = userRgDto.data_expedicao_rg();
        this.orgao_emissor_rg = userRgDto.orgao_emissor_rg().toUpperCase();
        this.uf_emissor_rg = userRgDto.uf_emissor_rg().toUpperCase();
    }
}
