package br.com.zendteam.hr.services;

import br.com.zendteam.hr.domain.UserCtps.UserCtps;
import br.com.zendteam.hr.domain.UserCtps.UserCtpsDto;
import br.com.zendteam.hr.domain.UserCtps.UserCtpsRepository;
import br.com.zendteam.hr.domain.dtos.MessageDto;
import br.com.zendteam.hr.infra.CopyNonNull;
import br.com.zendteam.hr.infra.DateUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCtpsService extends CopyNonNull {

    private final UserCtpsRepository userCtpsRepository;
    private final DateUtils dateUtils;

    public UserCtpsService(UserCtpsRepository userCtpsRepository, DateUtils dateUtils) {
        this.userCtpsRepository = userCtpsRepository;
        this.dateUtils = dateUtils;
    }

    public ResponseEntity<MessageDto> insert(UserCtpsDto userCtpsDto) {
        try {
            Optional<UserCtps> userExist = this.userCtpsRepository.findOneCtps(userCtpsDto.num_carteira());

            if (userExist.isPresent()) {
                // Retornar uma mensagem de erro indicando que o usuário já existe
                return new ResponseEntity<>(new MessageDto("Erro: CTPS já cadastrada.", false), HttpStatus.BAD_REQUEST);
            }

            UserCtps ctps = new UserCtps(userCtpsDto);

            ctps.setData_emissao(dateUtils.formatarData(userCtpsDto.data_emissao()));

            this.userCtpsRepository.save(ctps);

            // Retornar uma mensagem de sucesso indicando que o usuário foi cadastrado com sucesso
            return new ResponseEntity<>(new MessageDto("CTPS cadastrada com sucesso.", true), HttpStatus.CREATED);
        } catch (Exception e) {
            // Se ocorrer uma exceção, retornar uma mensagem de erro genérica
            return new ResponseEntity<>(new MessageDto("Erro interno ao processar a solicitação.", false), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> findOne(String user_id) {
        try {

            Optional<UserCtps> ctps = this.userCtpsRepository.findOneByUserId(user_id);

            if (ctps.isPresent()) {
                return new ResponseEntity<>(this.userCtpsRepository.findOneByUserId(user_id), HttpStatus.OK);
            }
            return new ResponseEntity<>(new MessageDto("Não há ctps cadastrada.", false), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            // Se ocorrer uma exceção, retornar uma mensagem de erro genérica
            return new ResponseEntity<>(new MessageDto("Erro interno ao processar a solicitação.", false), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> update(UserCtpsDto userCtpsDto, String user_id) {
        try {
            Optional<UserCtps> optionalUserRg = this.userCtpsRepository.findOneByUserId(user_id);

            if (optionalUserRg.isPresent()) {
                UserCtps userCtps = optionalUserRg.get();
                copyNonNullProperties(userCtpsDto, userCtps);

                // Implement this method to RG
                if (userCtps.getData_emissao() != null) {
                    userCtps.setData_emissao(dateUtils.formatarData(userCtpsDto.data_emissao()));
                }

                this.userCtpsRepository.save(userCtps);
                return new ResponseEntity<>(new MessageDto("CTPS atualizada com sucesso.", true), HttpStatus.OK);
            } else {
                // Se o usuário não for encontrado, retorne uma mensagem de erro
                return new ResponseEntity<>(new MessageDto("CTPS não encontrada.", false), HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e) {
            // Se ocorrer uma exceção, retornar uma mensagem de erro genérica
            return new ResponseEntity<>(new MessageDto("Erro interno ao processar a solicitação.", false), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
