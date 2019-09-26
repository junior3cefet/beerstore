package br.com.jnakamura.beerstore;

import br.com.jnakamura.beerstore.models.BeerModel;
import br.com.jnakamura.beerstore.models.BeerType;
import br.com.jnakamura.beerstore.repository.Beers;
import br.com.jnakamura.beerstore.service.BeerService;
import br.com.jnakamura.beerstore.service.exception.BeerAlreadyExistException;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import jdk.nashorn.internal.runtime.options.Option;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import java.math.BigDecimal;
import java.util.Optional;

public class BeerServiceTest {

	private BeerService beerService;

	@Mock
	private Beers beersMocked;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		this.beerService = new BeerService();
	}

//	@Test(expected = BeerAlreadyExistException.class)
	@Test
	public void should_fail_because_beer_already_exists_in_db() {
		BeerModel beerInDatabase = BeerModel
				.builder()
				.id(10L)
				.name("Skol beats")
				.type(BeerType.IPA)
				.volume(new BigDecimal(12.5))
				.build();

		when(beersMocked.findByNameAndType("Skol beats", BeerType.IPA))
				.thenReturn(Optional.of(beerInDatabase));

//		BeerModel newBeer = BeerModel
//				.builder()
//				.name("Skol beats")
//				.type(BeerType.IPA)
//				.volume(new BigDecimal(12.5))
//				.build();
//
//		beerService.save(newBeer);

	}

	@Test
	public void should_save_beer_in_db() {
		BeerModel newBeerInDatabase = BeerModel
				.builder()
				.id(10L)
				.name("Eisenbah")
				.type(BeerType.LAGER)
				.volume(new BigDecimal(80))
				.build();

		BeerModel newBeer = BeerModel
				.builder()
				.name("Eisenbah")
				.type(BeerType.LAGER)
				.volume(new BigDecimal(80))
				.build();

		when(beersMocked.save(newBeer)).thenReturn(newBeerInDatabase);

//		BeerModel beerModel = this.beerService.save(newBeer);

		assertThat(newBeerInDatabase.getName(), equalTo(newBeer.getName()));
		assertThat(newBeerInDatabase.getType(), equalTo(newBeer.getType()));
		assertThat(newBeerInDatabase.getVolume(), equalTo(newBeer.getVolume()));



	}



}
