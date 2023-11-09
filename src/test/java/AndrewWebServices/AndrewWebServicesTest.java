package AndrewWebServices;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

public class AndrewWebServicesTest {
    Database database;
    RecSys recommender;
    PromoService promoService;
    AndrewWebServices andrewWebService;

    @Before
    public void setUp() {
        // You need to use some mock objects here
        database = new Database(); // We probably don't want to access our real database...
        recommender = new RecSys();
        promoService = new PromoService();

        andrewWebService = new AndrewWebServices(database, recommender, promoService);
    }

    @Test
    public void testLogIn() {
        // This is taking way too long to test
        assertTrue(andrewWebService.logIn("Scotty", 17214));
        database = new InMemoryDatabase();
        andrewWebService = new AndrewWebServices(database, recommender, promoService);
        boolean loggedIn = andrewWebService.logIn("user1", 1234);
        assertTrue("Login should succeed with correct credentials", loggedIn);
        loggedIn = andrewWebService.logIn("user1", 124);
        assertFalse(loggedIn);
    }

    @Test
    public void testGetRecommendation() {
        // This is taking way too long to test
        // assertEquals("Animal House", andrewWebService.getRecommendation("Scotty"));
        RecSys recommender = mock(RecSys.class);
        when(recommender.getRecommendation("Snoopy")).thenReturn("Peanuts");
        andrewWebService = new AndrewWebServices(database, recommender, promoService);
        String recommendedMovie = andrewWebService.getRecommendation("Snoopy");
        assertEquals("Peanuts", recommendedMovie);
    }

    @Test
    public void testSendEmail() {
        // How should we test sendEmail() when it doesn't have a return value?
        // Hint: is there something from Mockito that seems useful here?
        promoService = mock(PromoService.class);
        andrewWebService = new AndrewWebServices(database, recommender, promoService);
        String email = "test@example.com";
        andrewWebService.sendPromoEmail(email);
        verify(promoService).mailTo(email);
    }

    @Test
    public void testNoSendEmail() {
        // How should we test that no email has been sent in certain situations (like
        // right after logging in)?
        // Hint: is there something from Mockito that seems useful here?
        promoService = mock(PromoService.class);
        andrewWebService = new AndrewWebServices(database, recommender, promoService);
        verify(promoService, never()).mailTo(anyString());
    }
}
