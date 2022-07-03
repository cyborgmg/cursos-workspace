package com.example.demo;

import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Builder
@Data
class PeriudoHashMap {
    Integer position;
    Periudo periudo;
}

class SobrespostoLocalDateTime {
    
    private static boolean between(LocalTime start, LocalTime end, LocalTime time) {
        if (start.isAfter(end)) {
            return !time.isBefore(start) || !time.isAfter(end);
        } else {
            return !time.isBefore(start) && !time.isAfter(end);
        }
    }

    public static boolean superimposedBand(List<Periudo> periudos){

        AtomicInteger i = new AtomicInteger();
        List<PeriudoHashMap> periudoHashMap = periudos.stream().map(p-> PeriudoHashMap.builder().position(i.getAndIncrement()).periudo(p).build() ).collect(Collectors.toList());

        AtomicReference<Boolean> result = new AtomicReference<>(true);

        periudoHashMap.forEach(phf->{
            periudoHashMap.forEach(phr->{
                if(phr.getPosition()!=phf.getPosition()) {
                    if (between(phr.getPeriudo().getIni(), phf.getPeriudo().getIni(), phf.getPeriudo().getFim())) {
                        result.set(false);
                    }
                    if (between(phr.getPeriudo().getFim(), phf.getPeriudo().getIni(), phf.getPeriudo().getFim())) {
                        result.set(false);
                    }
                }
            });
        });

        return result.get();
    }

}