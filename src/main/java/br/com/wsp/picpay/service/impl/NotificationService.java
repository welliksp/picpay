package br.com.wsp.picpay.service.impl;

import br.com.wsp.picpay.client.NotificationClient;
import br.com.wsp.picpay.entity.Transfer;
import br.com.wsp.picpay.exception.SendNotificationException;
import br.com.wsp.picpay.service.INotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NotificationService implements INotificationService {

    private final NotificationClient notificationClient;

    public NotificationService(NotificationClient notificationClient) {
        this.notificationClient = notificationClient;
    }

    @Override
    public void sendNotification(Transfer transfer) {

        try {
            log.info("Sending notification for transfer: {}", transfer);
            var response = notificationClient.sendNotification(transfer);

            if (response.getStatusCode().isError()) {
                log.error("Error notification: {}", response.getStatusCode());
                throw new SendNotificationException("Error sending notification");
            }
        } catch (Exception ex) {
            log.error("Error sending notification: {}", ex.getMessage());
            throw new SendNotificationException("Error sending notification");
        }

    }
}
