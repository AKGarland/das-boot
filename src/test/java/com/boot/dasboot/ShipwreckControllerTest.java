package com.boot.dasboot;

import com.boot.dasboot.controller.ShipwreckController;
import com.boot.dasboot.model.Shipwreck;
import com.boot.dasboot.repository.ShipwreckRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class ShipwreckControllerTest {
    @InjectMocks
    private ShipwreckController controller;

    @Mock
    private ShipwreckRepository shipwreckRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void returnsRequestedShipwreckOnGet() {
        Shipwreck shipwreck = new Shipwreck();
        shipwreck.setId(1L);
        when(shipwreckRepository.getOne(1L)).thenReturn(shipwreck);

        Shipwreck wreck = controller.get(1L);
        assertEquals(1L, wreck.getId().longValue());
    }

    @Test
    public void createsShipwreckOnPost() {
        Shipwreck shipwreck = new Shipwreck();
        when(shipwreckRepository.saveAndFlush(shipwreck)).thenReturn(shipwreck);

        Shipwreck wreck = controller.create(shipwreck);
        assertEquals(shipwreck, wreck);

    }

}
