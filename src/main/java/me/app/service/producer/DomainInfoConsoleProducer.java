package me.app.service.producer;

import me.app.service.dto.DomainInfoDTO;

import java.util.Comparator;
import java.util.List;

import static me.app.service.property.AppProperties.MAX_DOMAIN_INFO_CONSOLE_ROWS;

public class DomainInfoConsoleProducer implements DomainInfoProducer {

    @Override
    public void produce(List<DomainInfoDTO> domainInfoDTOs) {
        domainInfoDTOs
                .stream()
                .sorted(Comparator.comparing(DomainInfoDTO::getReappearance).reversed())
                .limit(MAX_DOMAIN_INFO_CONSOLE_ROWS)
                .forEach(domainInfoDTO -> System.out.println(domainInfoDTO.getName() + " " + domainInfoDTO.getReappearance()));
    }

}
