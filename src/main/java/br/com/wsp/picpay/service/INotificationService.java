package br.com.wsp.picpay.service;

import br.com.wsp.picpay.entity.Transfer;

public interface INotificationService {

    void sendNotification(Transfer transfer);
}
