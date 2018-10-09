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

import static org.mockito.Mockito.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

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
        assertThat(returnList, is(list));
    }

    @Test
    public void createsShipwreckOnCreate() {
        Shipwreck shipwreck = new Shipwreck();
        when(shipwreckRepository.saveAndFlush(shipwreck)).thenReturn(shipwreck);

        Shipwreck wreck = controller.create(shipwreck);
        assertThat(wreck, is(shipwreck));
    }

    @Test
    public void returnsRequestedShipwreckOnGet() {
        Shipwreck shipwreck = new Shipwreck();
        shipwreck.setId(ID);
        when(shipwreckRepository.getOne(ID)).thenReturn(shipwreck);

        Shipwreck wreck = controller.get(ID);
        assertThat(wreck.getId().longValue(), is(ID.longValue()));
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
        assertThat(wreck.getDepth(), is(shipwreck.getDepth()));
    }

    @Test
    public void deletesShipwreck() {
        Shipwreck shipwreck = new Shipwreck();
        shipwreck.setId(ID);
        when(shipwreckRepository.getOne(ID)).thenReturn(shipwreck);

        Shipwreck wreck = controller.delete(ID);
        assertThat(wreck.getId(), is(shipwreck.getId()));
        // I don't think this actually tests that anything is being deleted...
    }
}
