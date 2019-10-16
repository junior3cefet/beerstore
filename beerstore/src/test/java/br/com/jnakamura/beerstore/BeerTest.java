package br.com.jnakamura.beerstore;

import br.com.jnakamura.beerstore.models.BeerModel;
import br.com.jnakamura.beerstore.models.BeerType;
import br.com.jnakamura.beerstore.repository.BeerRepository;
import br.com.jnakamura.beerstore.service.BeerService;
import br.com.jnakamura.beerstore.service.exception.BeerNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.mockito.MockitoAnnotations;
import static org.hamcrest.CoreMatchers.is;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
public class BeerTest {

    @Mock
    private BeerRepository beerRepository;

    @InjectMocks
    private BeerService beerService;

    List<BeerModel> beerList = new ArrayList<>();

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);

        BeerModel skol = new BeerModel();
        skol.setName("skol");
        skol.setType(BeerType.LAGER);
        skol.setVolume(new BigDecimal(5));
        skol.setId(10L);

        BeerModel eisenbah = new BeerModel();
        eisenbah.setName("eisenbah");
        eisenbah.setType(BeerType.LAGER);
        eisenbah.setVolume(new BigDecimal(7));
        eisenbah.setId(11L);

        BeerModel chop =  new BeerModel();
        chop.setName("chop");
        chop.setType(BeerType.IPA);
        chop.setVolume(new BigDecimal(10));
        chop.setId(12L);

        beerList.add(skol);
        beerList.add(eisenbah);
        beerList.add(chop);
    }


    @Test
    public void should_success_because_has_3_items_in_list(){

        when(beerRepository.findAll()).thenReturn(this.beerList);

        List<BeerModel> result = beerService.listAll();
        assertEquals(3, result.size());
    }

    @Test(expected = BeerNotFoundException.class)
    public void should_fail_because_dont_exist_20_in_list() {

        when(beerRepository.findById(20L))
                .thenReturn(Optional.empty());

        beerService.findById(20L);

    }

    @Test(expected = BeerNotFoundException.class)
    public void should_fail_delete_because_dont_exist_beer() {
        when(beerRepository.findById(20L))
                .thenReturn(Optional.empty());

        beerService.findById(20L);

    }

    @Test(expected = BeerNotFoundException.class)
    public void should_success_delete() {
        BeerModel newBeer = new BeerModel();
        newBeer.setName("teste");
        newBeer.setType(BeerType.LAGER);
        newBeer.setVolume(new BigDecimal(5));
        newBeer.setId(10L);

        when(beerRepository.findById(10L))
                .thenReturn(Optional.of(newBeer));


        beerService.delete(10L);

        when(beerRepository.findById(10L))
                .thenReturn(Optional.empty());

        beerService.findById(10L);

    }

    @Test
    public void should_success_because_exist_10_in_list() {
        BeerModel newBeer = new BeerModel();
        newBeer.setName("teste");
        newBeer.setType(BeerType.LAGER);
        newBeer.setVolume(new BigDecimal(5));
        newBeer.setId(10L);

        when(beerRepository.findById(10L)).thenReturn(Optional.of(newBeer));

        assertThat(beerService.findById(10L), is(newBeer) );

    }


    @Test
    public void should_success_save_new_item(){
        BeerModel newBeer = new BeerModel();
        newBeer.setName("teste");
        newBeer.setType(BeerType.LAGER);
        newBeer.setVolume(new BigDecimal(5));
        newBeer.setId(10L);

        when(beerRepository.save(any(BeerModel.class))).thenReturn(newBeer);

        assertEquals(beerService.save(newBeer), newBeer);

    }

    @Test
    public void should_success_edit_item(){
        BeerModel newBeer = new BeerModel();
        newBeer.setName("teste");
        newBeer.setType(BeerType.LAGER);
        newBeer.setVolume(new BigDecimal(5));
        newBeer.setId(10L);

        when(beerRepository.findById(10L)).thenReturn(Optional.of(this.beerList.get(0)));
        when(beerRepository.save(any(BeerModel.class))).thenReturn(newBeer);

        assertEquals(beerService.edit(10L, newBeer), newBeer);

    }

}
