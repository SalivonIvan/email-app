package me.app.service.dto;

import java.util.Objects;

public record DomainInfoDTO(String name, int reappearance) {

    public String getName() {
        return name;
    }

    public int getReappearance() {
        return reappearance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DomainInfoDTO that = (DomainInfoDTO) o;
        return reappearance == that.reappearance && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, reappearance);
    }
}
