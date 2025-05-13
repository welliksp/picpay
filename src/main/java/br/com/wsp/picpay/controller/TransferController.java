package br.com.wsp.picpay.controller;

import br.com.wsp.picpay.dto.TransferRequest;
import br.com.wsp.picpay.service.ITransferService;
import br.com.wsp.picpay.service.impl.TransferService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/transfers")
public class TransferController {

    private final ITransferService service;

    public TransferController(TransferService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid TransferRequest transferRequest) {

        log.info("Calling TransferService to create the transfer: {}", transferRequest);
        service.save(transferRequest);

        return ResponseEntity.ok().build();
    }

}
