package com.api.APIMarcheAvecEliane;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import com.api.APIMarcheAvecEliane.controller.OutingController;
import com.api.APIMarcheAvecEliane.model.Coordinator;
import com.api.APIMarcheAvecEliane.model.Elderly;
import com.api.APIMarcheAvecEliane.model.Outing;
import com.api.APIMarcheAvecEliane.model.Volunteer;
import com.api.APIMarcheAvecEliane.service.OutingService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.Timestamp;
import java.util.UUID;

@RunWith(MockitoJUnitRunner.class)
public class OutingControllerTest {

    @InjectMocks
    //injecte mocks dans cette instance
    private OutingController outingController;

    @Mock
    private OutingService outingService;

    @Mock // Mock de la class Elderly
    private Elderly mockElderly;

    @Mock
    private Coordinator mockCoordinator;

    @Mock
    private Volunteer mockVolunteer;

    @Before
    public void setup() {
        when(mockElderly.getPseudo()).thenReturn("Jasmin-1111");
        when(mockElderly.getLandlineNumber()).thenReturn("01.42.25.14.29");

        when(mockCoordinator.getFirstName()).thenReturn("Sophia");
        when(mockCoordinator.getLastName()).thenReturn("Gicquela");
        when(mockCoordinator.getEmail()).thenReturn("sophia.gicquela@testUnitaire.com");

        when(mockVolunteer.getFirstName()).thenReturn("Æon");
        when(mockVolunteer.getLastName()).thenReturn("Bella");
        when(mockVolunteer.getEmailVolunteer()).thenReturn("aeonBella@testUnitaire.com");
    }

    @Test
    public void testCreateNewOuting() {

        Outing mockOuting = new Outing();

        UUID uuid = UUID.randomUUID();
        mockOuting.setId(uuid);
        mockOuting.setOutingDates(new Timestamp[]{new Timestamp(System.currentTimeMillis())});
        mockOuting.setElderly(mockElderly);
        mockOuting.setCoordinator(mockCoordinator);
        mockOuting.setVolunteer(mockVolunteer);

        // CALL
        when(outingService.createNewOuting(any(Outing.class))).thenReturn(mockOuting);
        // RESPONSE
        ResponseEntity<Outing> responseEntity = outingController.createNewOuting(mockOuting);
        // COMPARE
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        System.out.println("➡️ NEW OUTING.getStatusCode : "+ responseEntity.getStatusCode() );
        // CHECK
        assertEquals(mockOuting, responseEntity.getBody());

        // ⚖️ Response Entity Body: com.api.APIMarcheAvecEliane.model.Outing@5bbc9f97@Override
        // ⬇️ in outing model
        //    @Override
        //    public String toString() {
        //        return "Outing{\n" +
        //                "id → " + id + ",\n" +
        //                "outingDates → " + Arrays.toString(outingDates) + ",\n"
        //                "elderly → " + elderly.getPseudo() + " " + elderly.getLandlineNumber()) + ",\n" +
        //                "volunteer → " + volunteer.getFirstName() + volunteer.getLastName() + volunteer.getEmailVolunteer() + ",\n" +
        //                "coordinator →  " + coordinator.getFirstName()  + coordinator.getLastName()  + coordinator.getEmail() + "\n" +
        //       '}';
        //    }

        System.out.println("1️⃣ Mock Outing created with UUID: " + mockOuting.getId());
        System.out.println("2️⃣ Coordinator: " + mockCoordinator.getFirstName() + " " + mockCoordinator.getLastName() + ", " + mockCoordinator.getEmail());
        System.out.println("3️⃣ Elderly: " + mockElderly.getPseudo() );
        System.out.println("4️⃣ Volunteer: " + mockVolunteer.getEmailVolunteer() );
        System.out.println("5️⃣ Outing Dates: " + mockOuting.getOutingDates()[0]);
        System.out.println("⚖️ Response Entity Body: " + responseEntity.getBody());
    }
}
