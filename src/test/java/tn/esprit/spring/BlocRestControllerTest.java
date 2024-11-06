package tn.esprit.spring;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tn.esprit.spring.DAO.Entities.Bloc;
import tn.esprit.spring.Services.Bloc.IBlocService;
import tn.esprit.spring.RestControllers.BlocRestController;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class BlocRestControllerTest {

    @Mock
    private IBlocService blocService;

    @InjectMocks
    private BlocRestController blocController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(blocController).build();
    }

    // Test to retrieve all Blocs
    @Test
    public void testFindAllBlocs() throws Exception {
        Bloc bloc1 = new Bloc();
        bloc1.setIdBloc(1L);
        bloc1.setNomBloc("Bloc A");
        bloc1.setCapaciteBloc(100);

        Bloc bloc2 = new Bloc();
        bloc2.setIdBloc(2L);
        bloc2.setNomBloc("Bloc B");
        bloc2.setCapaciteBloc(200);

        List<Bloc> blocs = Arrays.asList(bloc1, bloc2);
        when(blocService.findAll()).thenReturn(blocs);

        mockMvc.perform(get("/bloc/findAll")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].nomBloc").value("Bloc A"))
                .andExpect(jsonPath("$[1].nomBloc").value("Bloc B"));
    }

    // Test to retrieve a Bloc by ID
    @Test
    public void testFindById() throws Exception {
        Long blocId = 1L;
        Bloc bloc = new Bloc();
        bloc.setIdBloc(blocId);
        bloc.setNomBloc("Bloc A");
        bloc.setCapaciteBloc(150);

        when(blocService.findById(blocId)).thenReturn(bloc);

        mockMvc.perform(get("/bloc/findById")
                        .param("id", String.valueOf(blocId))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nomBloc").value("Bloc A"));
    }

    // Test to add or update a Bloc
    @Test
    public void testAddOrUpdateBloc() throws Exception {
        Bloc bloc = new Bloc();
        bloc.setNomBloc("Bloc C");
        bloc.setCapaciteBloc(250);

        when(blocService.addOrUpdate(any(Bloc.class))).thenReturn(bloc);

        mockMvc.perform(post("/bloc/addOrUpdate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(bloc)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nomBloc").value("Bloc C"));
    }

    // Test to delete a Bloc by ID
    @Test
    public void testDeleteById() throws Exception {
        Long blocId = 1L;

        doNothing().when(blocService).deleteById(blocId);

        mockMvc.perform(delete("/bloc/deleteById")
                        .param("id", String.valueOf(blocId))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(blocService, times(1)).deleteById(blocId);
    }

    // Test to affect rooms to a Bloc
    @Test
    public void testAffecterChambresABloc() throws Exception {
        Bloc bloc = new Bloc();
        bloc.setNomBloc("Bloc A");

        List<Long> roomNumbers = Arrays.asList(101L, 102L);

        when(blocService.affecterChambresABloc(roomNumbers, "Bloc A")).thenReturn(bloc);

        mockMvc.perform(put("/bloc/affecterChambresABloc")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(roomNumbers))
                        .param("nomBloc", "Bloc A"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nomBloc").value("Bloc A"));
    }

    // Test to affect a Bloc to a Foyer
    @Test
    public void testAffecterBlocAFoyer() throws Exception {
        Bloc bloc = new Bloc();
        bloc.setNomBloc("Bloc A");

        when(blocService.affecterBlocAFoyer("Bloc A", "Foyer 1")).thenReturn(bloc);

        mockMvc.perform(put("/bloc/affecterBlocAFoyer")
                        .param("nomBloc", "Bloc A")
                        .param("nomFoyer", "Foyer 1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nomBloc").value("Bloc A"));
    }
}
