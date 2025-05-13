package br.com.wsp.picpay.client;

import br.com.wsp.picpay.entity.Transfer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(
        name = "NotificationClient",
        url = "${client.notification-service}"
)
public interface NotificationClient {

    @PostMapping
    ResponseEntity<Void> sendNotification(Transfer transfer);

}
