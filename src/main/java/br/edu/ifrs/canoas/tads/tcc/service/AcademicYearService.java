package br.edu.ifrs.canoas.tads.tcc.service;

import br.edu.ifrs.canoas.tads.tcc.domain.*;
import br.edu.ifrs.canoas.tads.tcc.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;

import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

/**
 * Created by cassiano on 3/21/18.
 */
@Service
@AllArgsConstructor
public class AcademicYearService {

    AcademicYearRepository academicYearRepository;

    public Iterable<AcademicYear> findAllByOrderByIdAsc(){
        return academicYearRepository.findAllByOrderByIdAsc();
    }

}
