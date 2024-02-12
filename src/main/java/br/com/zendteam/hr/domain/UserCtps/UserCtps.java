package br.com.zendteam.hr.domain.UserCtps;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@Entity(name = "user_ctps")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class UserCtps {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String user_id;
    private String num_carteira;
    private String num_serieCarteira;
    private String data_emissao;
    private String uf_emissor_carteira;

    public UserCtps(UserCtpsDto userCtpsDto) {
        this.user_id = userCtpsDto.user_id();
        this.num_carteira = userCtpsDto.num_carteira().toUpperCase();
        this.num_serieCarteira = userCtpsDto.num_serieCarteira().toUpperCase();
        this.data_emissao = userCtpsDto.data_emissao();
        this.uf_emissor_carteira = userCtpsDto.uf_emissor_carteira().toUpperCase();
    }
}
