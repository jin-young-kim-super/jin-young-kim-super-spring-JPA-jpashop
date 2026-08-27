package jpabook.jpashop.domain;

import jakarta.persistence.Embeddable;

import java.time.LocalDateTime;

/**
 * 임베디드 타입은 반드시 기본 생성자 필요
 */
@Embeddable
public class Period {

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
}
