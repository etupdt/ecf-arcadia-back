package fr.ecf.arcadia;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;

import fr.ecf.arcadia.Services.AnimalService;
import fr.ecf.arcadia.pojo.AnimalStatistic;
import fr.ecf.arcadia.repositories.AnimalStatisticRepository;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@ContextConfiguration(locations = "classpath:ArcadiaApplicationTest-context.xml")
class ArcadiaApplicationTest {

	@MockBean
    private AnimalService animalService;

	@Mock
    private AnimalStatisticRepository animalStatisticRepository;

	@Test
	void testAnimalServiceGetAnimalsStatistics() {

		// final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		List<AnimalStatistic> animalStatisticsList = Arrays.asList(
			new AnimalStatistic(
				"Jumbo", 
				LocalDate.parse("2005-10-22"), 
				4L
			),
			new AnimalStatistic(
				"Jumbo", 
				LocalDate.parse("2005-10-20"), 
				4L
			)
		);

        when(animalStatisticRepository
			.findAll(Sort.by("date").ascending()))
			.thenReturn(animalStatisticsList);

		List<AnimalStatistic> result = animalService.getAnimalsStatistics();

        assertNotNull(result);
        assertTrue(result.get(0).getDate().getDayOfYear() < result.get(1).getDate().getDayOfYear());

	}

}
