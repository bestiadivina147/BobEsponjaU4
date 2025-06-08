// WorkplaceServiceTest.java
package edu.badpals.bobesponjau4.service;

import edu.badpals.bobesponjau4.model.Workplace;
import edu.badpals.bobesponjau4.repository.WorkplaceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WorkplaceServiceTest {

    @Mock
    private WorkplaceRepository workplaceRepository;

    @InjectMocks
    private WorkplaceService workplaceService;

    private Workplace wp;

    @BeforeEach
    void setUp() {
        wp = new Workplace();
        wp.setId(1);
        wp.setName("Krusty Krab");
    }

    @Test
    void getAllWorkplaces_ReturnsList() {
        when(workplaceRepository.findAll()).thenReturn(List.of(wp));
        List<Workplace> result = workplaceService.getAllWorkplaces();
        assertThat(result).containsExactly(wp);
    }

    @Test
    void getWorkplaceById_Existing() {
        when(workplaceRepository.findById(1)).thenReturn(Optional.of(wp));
        Optional<Workplace> opt = workplaceService.getWorkplaceById(1);
        assertThat(opt).isPresent().get().isEqualTo(wp);
    }

    @Test
    void getWorkplaceById_NotFound() {
        when(workplaceRepository.findById(2)).thenReturn(Optional.empty());
        assertThat(workplaceService.getWorkplaceById(2)).isEmpty();
    }

    @Test
    void saveWorkplace_CallsRepo() {
        when(workplaceRepository.save(wp)).thenReturn(wp);
        Workplace saved = workplaceService.saveWorkplace(wp);
        assertThat(saved).isEqualTo(wp);
        verify(workplaceRepository).save(wp);
    }

    @Test
    void deleteWorkplace_CallsRepoDelete() {
        workplaceService.deleteWorkplace(1);
        verify(workplaceRepository).deleteById(1);
    }
}
