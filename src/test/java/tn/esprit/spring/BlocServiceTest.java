package tn.esprit.spring;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.DAO.Entities.Bloc;
import tn.esprit.spring.DAO.Entities.Chambre;
import tn.esprit.spring.DAO.Repositories.BlocRepository;
import tn.esprit.spring.DAO.Repositories.ChambreRepository;
import tn.esprit.spring.DAO.Repositories.FoyerRepository;
import tn.esprit.spring.Services.Bloc.BlocService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BlocServiceTest {

    @Mock
    private BlocRepository blocRepository;

    @Mock
    private ChambreRepository chambreRepository;

    @Mock
    private FoyerRepository foyerRepository;

    @InjectMocks
    private BlocService blocService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddOrUpdate() {
        // Arrange
        Chambre chambre1 = new Chambre();
        chambre1.setNumeroChambre(101);

        Chambre chambre2 = new Chambre();
        chambre2.setNumeroChambre(102);

        Bloc bloc = new Bloc();
        bloc.setNomBloc("Bloc A");
        bloc.setChambres(Arrays.asList(chambre1, chambre2));

        when(blocRepository.save(any(Bloc.class))).thenReturn(bloc);
        when(chambreRepository.save(any(Chambre.class))).thenReturn(chambre1);

        // Act
        Bloc updatedBloc = blocService.addOrUpdate(bloc);

        // Assert
        assertNotNull(updatedBloc);
        assertEquals("Bloc A", updatedBloc.getNomBloc());
        verify(blocRepository, times(1)).save(bloc);  // Vérifie que le bloc a été sauvegardé
        verify(chambreRepository, times(2)).save(any(Chambre.class));  // Vérifie que chaque chambre a été sauvegardée
    }

    @Test
    void testFindById() {
        // Arrange
        Bloc bloc = new Bloc();
        bloc.setIdBloc(1L);
        bloc.setNomBloc("Bloc A");

        when(blocRepository.findById(1L)).thenReturn(Optional.of(bloc));

        // Act
        Bloc foundBloc = blocService.findById(1L);

        // Assert
        assertNotNull(foundBloc);
        assertEquals(1L, foundBloc.getIdBloc());
        assertEquals("Bloc A", foundBloc.getNomBloc());
        verify(blocRepository, times(1)).findById(1L);  // Vérifie que la méthode findById a été appelée
    }

    @Test
    void testDeleteById() {
        // Arrange
        long blocId = 1L;

        // Act
        blocService.deleteById(blocId);

        // Assert
        verify(blocRepository, times(1)).deleteById(blocId);  // Vérifie que la méthode deleteById a été appelée
    }

    @Test
    void testAffecterChambresABloc() {
        // Arrange
        Bloc bloc = new Bloc();
        bloc.setNomBloc("Bloc A");

        Chambre chambre1 = new Chambre();
        chambre1.setNumeroChambre(101);

        Chambre chambre2 = new Chambre();
        chambre2.setNumeroChambre(102);

        List<Long> numChambres = Arrays.asList(101L, 102L);

        when(blocRepository.findByNomBloc("Bloc A")).thenReturn(bloc);
        when(chambreRepository.findByNumeroChambre(101L)).thenReturn(chambre1);
        when(chambreRepository.findByNumeroChambre(102L)).thenReturn(chambre2);

        // Act
        Bloc result = blocService.affecterChambresABloc(numChambres, "Bloc A");

        // Assert
        assertNotNull(result);
        assertEquals("Bloc A", result.getNomBloc());
        verify(chambreRepository, times(2)).save(any(Chambre.class));  // Vérifie que les chambres ont été mises à jour et sauvegardées
    }
}