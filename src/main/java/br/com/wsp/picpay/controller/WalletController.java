package br.com.wsp.picpay.controller;

import br.com.wsp.picpay.dto.WalletRequest;
import br.com.wsp.picpay.dto.WalletResponse;
import br.com.wsp.picpay.service.IWalletService;
import br.com.wsp.picpay.service.impl.WalletService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/wallets")
public class WalletController {

    private final IWalletService service;

    public WalletController(WalletService service) {
        this.service = service;
    }


    @PostMapping
    public ResponseEntity<WalletResponse> save(@RequestBody @Valid WalletRequest request) {


        WalletResponse saved = service.save(request);

        URI location = URI.create("/wallets" + saved.id());

        return ResponseEntity.created(location).body(saved);
    }
}
