package me.app.service.producer;

import me.app.service.dto.DomainInfoDTO;

import java.util.List;

public interface DomainInfoProducer {

    void produce(List<DomainInfoDTO> domainInfoDTOs);

}
