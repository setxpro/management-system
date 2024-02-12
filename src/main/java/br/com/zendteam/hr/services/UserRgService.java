package br.com.zendteam.hr.services;

import br.com.zendteam.hr.domain.UserRg.UserRg;
import br.com.zendteam.hr.domain.UserRg.UserRgDto;
import br.com.zendteam.hr.domain.UserRg.UserRgRepository;
import br.com.zendteam.hr.domain.dtos.MessageDto;
import br.com.zendteam.hr.infra.CopyNonNull;
import br.com.zendteam.hr.infra.DateUtils;
import br.com.zendteam.hr.infra.validations.Validation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserRgService extends CopyNonNull {

    private final UserRgRepository userRgRepository;
    private final DateUtils dateUtils;

    private final Validation validationFields;

    public UserRgService(UserRgRepository userRgRepository, DateUtils dateUtils, Validation validationFields) {
        this.userRgRepository = userRgRepository;
        this.dateUtils = dateUtils;
        this.validationFields = validationFields;
    }

    public ResponseEntity<MessageDto> insert(UserRgDto userRgDto) {
        try {

            if (!this.validationFields.validateRG(userRgDto.num_rg())) {
                return new ResponseEntity<>(new MessageDto("RG inválido.", false), HttpStatus.BAD_REQUEST);
            }

            Optional<UserRg> userExist = this.userRgRepository.findOneRg(userRgDto.num_rg());

            if (userExist.isPresent()) {
                // Retornar uma mensagem de erro indicando que o usuário já existe
                return new ResponseEntity<>(new MessageDto("Erro: RG já cadastrado.", false), HttpStatus.BAD_REQUEST);
            }

            UserRg user = new UserRg(userRgDto);

            user.setData_expedicao_rg(dateUtils.formatarData(userRgDto.data_expedicao_rg()));
            user.setData_nascimento(dateUtils.formatarData(userRgDto.data_nascimento()));

            this.userRgRepository.save(user);

            // Retornar uma mensagem de sucesso indicando que o usuário foi cadastrado com sucesso
            return new ResponseEntity<>(new MessageDto("RG cadastrado com sucesso.", true), HttpStatus.CREATED);
        } catch (Exception e) {
            // Se ocorrer uma exceção, retornar uma mensagem de erro genérica
            return new ResponseEntity<>(new MessageDto("Erro interno ao processar a solicitação.", false), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> findOne(String user_id) {
        try {

            Optional<UserRg> userRg = this.userRgRepository.findOneRgByUserId(user_id);

            if (userRg.isPresent()) {
                return new ResponseEntity<>(this.userRgRepository.findOneRgByUserId(user_id), HttpStatus.OK);
            }
            return new ResponseEntity<>(new MessageDto("Não há rg cadastrado.", false), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            // Se ocorrer uma exceção, retornar uma mensagem de erro genérica
            return new ResponseEntity<>(new MessageDto("Erro interno ao processar a solicitação.", false), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> update(UserRgDto userRgDto, String user_id) {
        try {
            Optional<UserRg> optionalUserRg = this.userRgRepository.findOneRgByUserId(user_id);
            if (optionalUserRg.isPresent()) {
                UserRg userRg = optionalUserRg.get();
                copyNonNullProperties(userRgDto, userRg);

                if (userRgDto.data_expedicao_rg() != null) {
                    userRg.setData_expedicao_rg(dateUtils.formatarData(userRgDto.data_expedicao_rg()));
                }
                if (userRgDto.data_nascimento() != null) {
                    userRg.setData_nascimento(dateUtils.formatarData(userRgDto.data_nascimento()));
                }

                this.userRgRepository.save(userRg);
                return new ResponseEntity<>(new MessageDto("RG atualizado com sucesso.", true), HttpStatus.OK);
            } else {
                // Se o usuário não for encontrado, retorne uma mensagem de erro
                return new ResponseEntity<>(new MessageDto("Usuário não encontrado.", false), HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e) {
            // Se ocorrer uma exceção, retornar uma mensagem de erro genérica
            return new ResponseEntity<>(new MessageDto("Erro interno ao processar a solicitação.", false), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
