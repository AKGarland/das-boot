package com.boot.dasboot;

import com.boot.dasboot.controller.ShipwreckController;
import com.boot.dasboot.model.Shipwreck;
import com.boot.dasboot.repository.ShipwreckRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class ShipwreckControllerTest {
    @InjectMocks
    private ShipwreckController controller;

    @Mock
    private ShipwreckRepository shipwreckRepository;

    private static final Long ID = 1L;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void returnsListOfShipwrecksOnList() {
        List<Shipwreck> list = new ArrayList<>();
        Shipwreck shipwreck = new Shipwreck();
        Shipwreck shipwreck2 = new Shipwreck();
        list.add(shipwreck);
        list.add(shipwreck2);
        when(shipwreckRepository.findAll()).thenReturn(list);

        List<Shipwreck> returnList = controller.list();
        assertEquals(list, returnList);
    }

    @Test
    public void createsShipwreckOnCreate() {
        Shipwreck shipwreck = new Shipwreck();
        when(shipwreckRepository.saveAndFlush(shipwreck)).thenReturn(shipwreck);

        Shipwreck wreck = controller.create(shipwreck);
        assertEquals(shipwreck, wreck);
    }

    @Test
    public void returnsRequestedShipwreckOnGet() {
        Shipwreck shipwreck = new Shipwreck();
        shipwreck.setId(ID);
        when(shipwreckRepository.getOne(ID)).thenReturn(shipwreck);

        Shipwreck wreck = controller.get(ID);
        assertEquals(ID.longValue(), wreck.getId().longValue());
    }

    @Test
    public void updatesRequestedShipwreck() {
        Shipwreck existingShipwreck = new Shipwreck();
        existingShipwreck.setId(ID);
        existingShipwreck.setDepth(1);
        when(shipwreckRepository.getOne(ID)).thenReturn(existingShipwreck);

        Shipwreck shipwreck = new Shipwreck();
        shipwreck.setDepth(2);
        when(shipwreckRepository.saveAndFlush(existingShipwreck)).thenReturn(existingShipwreck);

        Shipwreck wreck = controller.update(ID, shipwreck);
        assertEquals(shipwreck.getDepth(),wreck.getDepth());
    }

    @Test
    public void deletesShipwreck() {
        Shipwreck shipwreck = new Shipwreck();
        shipwreck.setId(ID);
        when(shipwreckRepository.getOne(ID)).thenReturn(shipwreck);

        Shipwreck wreck = controller.delete(ID);
        assertEquals(shipwreck.getId(), wreck.getId());
        // I don't think this actually tests that anything is being deleted...
    }
}
